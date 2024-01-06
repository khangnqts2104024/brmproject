package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.entities.CategoryEntity;
import com.example.brmproject.repositories.CategoryEntityRepository;
import com.example.brmproject.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO addNewCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryEntityRepository.save(mapToEntity(categoryDTO));
        return mapToDTO(categoryEntity);
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryEntityRepository.findAll().stream()
                .map(categoryEntity -> mapToDTO(categoryEntity)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Integer id) {
        CategoryEntity categoryEntity = categoryEntityRepository.findById(id).orElse(null);
        return mapToDTO(categoryEntity);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = categoryEntityRepository.findById(categoryDTO.getId()).orElse(null);

        return null;
    }

    public CategoryDTO mapToDTO(CategoryEntity categoryEntity) {
        return modelMapper.map(categoryEntity, CategoryDTO.class);
    }

    public CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);

    }
}
