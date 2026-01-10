package com.example.artwebsitebe.service.review;

import com.example.artwebsitebe.dto.review.RatingSummaryDTO;
import com.example.artwebsitebe.dto.review.ReviewDTO;

import java.util.List;

public interface ReviewService {

    RatingSummaryDTO getRatingSummary(Long productId);

    List<ReviewDTO> getReviewsByProduct(Long productId);
}