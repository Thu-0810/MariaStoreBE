package com.example.artwebsitebe.controller.user;

import com.example.artwebsitebe.dto.user.UserProfileDTO;
import com.example.artwebsitebe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserProfileDTO getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserProfile(email);
    }
}