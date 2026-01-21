package com.example.artwebsitebe.controller.order;

import com.example.artwebsitebe.dto.order.AdminOrderDetailDTO;
import com.example.artwebsitebe.dto.order.AdminOrderRowDTO;
import com.example.artwebsitebe.dto.order.AdminOrderUpdateRequestDTO;
import com.example.artwebsitebe.enums.OrderStatus;
import com.example.artwebsitebe.service.order.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {

    private final AdminOrderService service;

    @GetMapping("/paged")
    public Page<AdminOrderRowDTO> paged(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) OrderStatus status
    ) {
        return service.paged(page, size, sort, keyword, status);
    }

    @GetMapping("/{id}/detail")
    public AdminOrderDetailDTO detail(@PathVariable Long id) {
        return service.detail(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody AdminOrderUpdateRequestDTO req) {
        service.update(id, req);
    }

    @DeleteMapping
    public void deleteMany(@RequestBody List<Long> ids) {
        service.softDeleteMany(ids);
    }
}