package com.example.artwebsitebe.service.checkout;

import com.example.artwebsitebe.dto.checkout.QrInfoDTO;

public interface PaymentFlowService {
    QrInfoDTO getQrInfo(String email, Long orderId);
    void confirmPaid(String email, Long orderId);
}