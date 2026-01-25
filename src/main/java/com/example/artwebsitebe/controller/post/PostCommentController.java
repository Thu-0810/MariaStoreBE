package com.example.artwebsitebe.controller.post;

import com.example.artwebsitebe.dto.post.CommentResponseDTO;
import com.example.artwebsitebe.entity.PostComment;
import com.example.artwebsitebe.enums.NotificationType;
import com.example.artwebsitebe.repository.post.PostCommentRepository;
import com.example.artwebsitebe.repository.post.PostRepository;
import com.example.artwebsitebe.repository.user.UserRepository;
import com.example.artwebsitebe.service.notifications.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentRepository commentRepo;
    private final UserRepository userRepo;

    public record CreateCommentReq(String content) {}

    private final PostRepository postRepo;
    private final NotificationService notificationService;

    // Public: xem danh sách comment
    @GetMapping("/{postId}/comments")
    public Page<CommentResponseDTO> list(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return commentRepo.findComments(postId, pageable);
    }

    // Authenticated: thêm comment
    @PostMapping("/{postId}/comments")
    public CommentResponseDTO create(
            @PathVariable Long postId,
            @RequestBody CreateCommentReq req
    ) {
        if (req.content() == null || req.content().isBlank()) {
            throw new IllegalArgumentException("Nội dung comment bắt buộc");
        }

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));

        PostComment c = new PostComment();
        c.setPostId(postId);
        c.setUserId(user.getId());
        c.setContent(req.content().trim());

        PostComment saved = commentRepo.save(c);

        var post = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post không tồn tại"));

        if (post.getUser() != null) {
            notificationService.create(
                    post.getUser().getId(),
                    user.getId(),
                    NotificationType.POST_NEW_COMMENT,
                    "Bài viết có bình luận mới",
                    user.getFullName() + " vừa bình luận bài viết của bạn.",
                    "/posts/" + postId,
                    """
                    {"postId":%d,"commentId":%d}
                    """.formatted(postId, saved.getId())
            );
        }


        return new CommentResponseDTO(
                saved.getId(),
                saved.getPostId(),
                saved.getContent(),
                saved.getCreatedAt(),
                user.getId(),
                user.getFullName(),
                user.getAvatarUrl()
        );
    }
}