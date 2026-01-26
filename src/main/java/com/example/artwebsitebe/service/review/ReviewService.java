package com.example.artwebsitebe.service.review;

import com.example.artwebsitebe.dto.review.RatingSummaryDTO;
import com.example.artwebsitebe.dto.review.ReviewDTO;
import com.example.artwebsitebe.dto.review.ReviewUpsertRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    RatingSummaryDTO getRatingSummary(Long productId);
    List<ReviewDTO> getReviewsByProduct(Long productId);

    ReviewDTO upsertMyReview(Long productId, String email, ReviewUpsertRequestDTO req);
    void deleteMyReview(Long productId, String email);
    Optional<ReviewDTO> getMyReview(Long productId, String email);
}