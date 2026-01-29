package com.example.artwebsitebe.repository.order;

import com.example.artwebsitebe.dto.order.AdminOrderItemDTO;
import com.example.artwebsitebe.dto.order.MyOrderItemDTO;
import com.example.artwebsitebe.entity.OrderItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
    select new com.example.artwebsitebe.dto.order.AdminOrderItemDTO(
        p.id,
        oi.productNameSnapshot,
        oi.thumbnailUrlSnapshot,
        oi.fileFormatSnapshot,
        oi.quantity,
        oi.price
    )
    from OrderItem oi
    join oi.product p
    where oi.order.id = :orderId
    """)
    List<AdminOrderItemDTO> findAdminItems(@Param("orderId") Long orderId);

    @Query("""
    select new com.example.artwebsitebe.dto.order.MyOrderItemDTO(
        oi.id,
        p.id,
        oi.productNameSnapshot,
        oi.thumbnailUrlSnapshot,
        oi.fileFormatSnapshot,
        oi.quantity,
        oi.price,
        oi.downloadName
    )
    from OrderItem oi
    join oi.product p
    where oi.order.id = :orderId
    """)
    List<MyOrderItemDTO> findMyItems(@Param("orderId") Long orderId);


    Optional<OrderItem> findByIdAndOrderId(Long id, Long orderId);
}