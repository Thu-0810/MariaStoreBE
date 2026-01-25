package com.example.artwebsitebe.service.checkout;

import com.example.artwebsitebe.dto.checkout.QrInfoDTO;
import com.example.artwebsitebe.entity.Order;
import com.example.artwebsitebe.entity.Payment;
import com.example.artwebsitebe.enums.NotificationType;
import com.example.artwebsitebe.enums.OrderStatus;
import com.example.artwebsitebe.repository.order.OrderRepository;
import com.example.artwebsitebe.repository.payment.PaymentRepository;
import com.example.artwebsitebe.service.cart.CartService;
import com.example.artwebsitebe.service.notifications.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentFlowServiceImpl implements PaymentFlowService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final CartService cartService;
    private final NotificationService notificationService;


    @Override
    @Transactional(readOnly = true)
    public QrInfoDTO getQrInfo(String email, Long orderId) {
        Order o = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!o.getUser().getEmail().equalsIgnoreCase(email)) {
            throw new RuntimeException("Forbidden");
        }

        Payment p = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        String orderCode = "#AA" + String.format("%08d", o.getId());

        String qrValue = "BANK://pay?amount=" + o.getTotalAmount() + "&ref=" + p.getTransactionId();

        return new QrInfoDTO(
                o.getId(),
                orderCode,
                p.getTransactionId(),
                o.getTotalAmount(),
                qrValue
        );
    }

    @Override
    public void confirmPaid(String email, Long orderId) {
        Order o = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!o.getUser().getEmail().equalsIgnoreCase(email)) {
            throw new RuntimeException("Forbidden");
        }

        Payment p = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if ("PAID".equalsIgnoreCase(p.getPaymentStatus())) {
            return;
        }

        p.setPaymentStatus("PAID");
        p.setPaidAt(LocalDateTime.now());
        o.setStatus(OrderStatus.COMPLETED);

        paymentRepository.save(p);
        orderRepository.save(o);

        Long recipientId = o.getUser().getId();

        notificationService.create(
                recipientId,
                null,
                NotificationType.PAYMENT_PAID,
                "Thanh toán thành công",
                "Đơn " + o.getId() + " đã thanh toán thành công.",
                "/orders/" + o.getId(),
                """
                {"orderId":%d,"paymentId":%d,"status":"PAID"}
                """.formatted(o.getId(), p.getId())
        );

        notificationService.create(
                recipientId,
                null,
                NotificationType.ORDER_STATUS_CHANGED,
                "Đơn hàng cập nhật",
                "Đơn " + o.getId() + " chuyển sang: " + o.getStatus().name(),
                "/orders/" + o.getId(),
                """
                {"orderId":%d,"status":"%s"}
                """.formatted(o.getId(), o.getStatus().name())
        );

        cartService.clearCart(email);
    }

}