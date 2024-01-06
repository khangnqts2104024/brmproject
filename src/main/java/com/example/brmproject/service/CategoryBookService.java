package com.example.brmproject.service;

import com.example.brmproject.domain.dto.CategoryBookDTO;
import com.example.brmproject.domain.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryBookService {

    public CategoryBookDTO add(CategoryBookDTO categoryBookDTO);

    Page<CategoryBookDTO> findBooksByCategoryId(int id, int page, int size);
}
