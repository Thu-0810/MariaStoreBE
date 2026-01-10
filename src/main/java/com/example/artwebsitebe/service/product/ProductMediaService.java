package com.example.artwebsitebe.service.product;

import com.example.artwebsitebe.dto.product.ProductMediaDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProductMediaService {

    ProductMediaDTO uploadProductImage(
            Long productId,
            MultipartFile file,
            boolean isPrimary
    );

    void deleteImage(Long imageId);

    void setPrimary(Long imageId);
}