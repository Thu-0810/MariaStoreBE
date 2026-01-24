package com.example.artwebsitebe.repository.commission;

import com.example.artwebsitebe.entity.CommissionRequest;
import com.example.artwebsitebe.enums.CommissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommissionRequestRepository
        extends JpaRepository<CommissionRequest, Long> {

    List<CommissionRequest> findByUserId(Long userId);

    List<CommissionRequest> findBySellerId(Long sellerId);

    List<CommissionRequest> findByStatus(CommissionStatus status);

    List<CommissionRequest> findBySellerIdAndStatus(
            Long sellerId, CommissionStatus status
    );
}