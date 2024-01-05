package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.entities.CategoryEntity;
import com.example.brmproject.exception.ResourceNotFoundException;
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

    private ModelMapper modelMapper;
    private CategoryEntityRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(ModelMapper modelMapper, CategoryEntityRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> list = categoryRepository.findAll()
                .stream()
                .map(category -> mapToDTO(category))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = categoryRepository.save(mapToEntity(categoryDTO));
        CategoryEntity newCategory = categoryRepository
                .findById(category.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(category.getId())));
        return mapToDTO(newCategory);
    }

    @Override
    public Optional<CategoryEntity> findById(int id) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);

        return category;
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    //map to dto
    public CategoryDTO mapToDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    public CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
        CategoryEntity category = modelMapper.map(categoryDTO, CategoryEntity.class);
        return category;
    }
}
