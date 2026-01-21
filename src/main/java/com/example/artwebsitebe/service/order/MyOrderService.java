package com.example.artwebsitebe.service.order;

import com.example.artwebsitebe.dto.order.MyOrderDTO;
import com.example.artwebsitebe.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyOrderService {
    Page<MyOrderDTO> myOrders(String email, OrderStatus status, Pageable pageable);
    MyOrderDTO myOrderDetail(String email, Long orderId);
}