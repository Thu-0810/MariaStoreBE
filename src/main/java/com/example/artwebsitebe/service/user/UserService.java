package com.example.artwebsitebe.service.user;

import com.example.artwebsitebe.dto.user.UserProfileDTO;
import com.example.artwebsitebe.entity.User;
import com.example.artwebsitebe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileDTO getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileDTO(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPhone(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getAvatarUrl(),
                user.getAddress(),
                user.getIsVerified(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toSet())
        );
    }

    public User getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
