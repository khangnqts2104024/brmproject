package com.example.brmproject.service;

import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<CategoryDTO> findAll();

    public Optional<CategoryEntity> findById(int id);

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public void deleteById(int id);
}
