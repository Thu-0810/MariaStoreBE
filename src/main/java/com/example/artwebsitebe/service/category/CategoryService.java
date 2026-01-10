package com.example.artwebsitebe.service.category;


import com.example.artwebsitebe.dto.category.CategoryResponseDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAll();
}
