package com.example.artwebsitebe.repository.review;

import com.example.artwebsitebe.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductIdOrderByCreatedAtDesc(Long productId);

    long countByProductIdAndRating(Long productId, Integer rating);

    long countByProductId(Long productId);
}