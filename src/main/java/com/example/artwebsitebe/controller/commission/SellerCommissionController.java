package com.example.artwebsitebe.controller.commission;

import com.example.artwebsitebe.dto.commission.ApproveCommissionDTO;
import com.example.artwebsitebe.dto.commission.CommissionRequestResponseDTO;
import com.example.artwebsitebe.entity.User;
import com.example.artwebsitebe.security.util.SecurityUtils;
import com.example.artwebsitebe.service.commission.CommissionService;
import com.example.artwebsitebe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller/commissions")
@RequiredArgsConstructor
public class SellerCommissionController {

    private final CommissionService service;
    private final UserService userService;

    @GetMapping
    public List<CommissionRequestResponseDTO> pending() {
        String email = SecurityUtils.getCurrentEmail();
        User seller = userService.getUserEntityByEmail(email);
        return service.getPendingForSeller(seller);
    }

    @PostMapping("/{id}/approve")
    public void approve(
            @PathVariable Long id,
            @RequestBody ApproveCommissionDTO dto
    ) {
        String email = SecurityUtils.getCurrentEmail();
        User seller = userService.getUserEntityByEmail(email);
        service.approve(id, dto.getFinalPrice(), seller);
    }

    @PostMapping("/{id}/reject")
    public void reject(@PathVariable Long id) {
        String email = SecurityUtils.getCurrentEmail();
        User seller = userService.getUserEntityByEmail(email);
        service.reject(id, seller);
    }
}