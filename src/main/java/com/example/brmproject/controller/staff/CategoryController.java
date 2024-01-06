package com.example.brmproject.controller.staff;

import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.entities.CategoryEntity;
import com.example.brmproject.repositories.CategoryEntityRepository;
import com.example.brmproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("staff")
public class CategoryController {

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/new")
    public String getNewCategoryPage() {
        return "adminTemplate/category/addNewCategory";
    }

    @PostMapping("/categories/new")
    public String addNewCategory(@ModelAttribute CategoryDTO categoryDTO) {
        CategoryDTO newCategory = categoryService.addNewCategory(categoryDTO);
        return "redirect:/staff/categories";
    }

    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "adminTemplate/category/showAllCategories";
    }


    @GetMapping("/categories/update/{categoryID}")
    public String getUpdateCategoryPage(@PathVariable("categoryID") Integer categoryId, Model model) {
        CategoryDTO categoryDTO = categoryService.findById(categoryId);
        model.addAttribute("category", categoryDTO);
        return "adminTemplate/category/updateCategory";
    }

    @PostMapping("/categories/update/{categoryID}")
    public String updateCategory(Model model, @ModelAttribute("newCategory") CategoryDTO categoryDTO,
                                 @PathVariable Integer categoryID
                                 ) {
        if (categoryEntityRepository.findById(categoryID).isPresent()) {
            CategoryDTO findCategoryDTO = categoryService.findById(categoryID);
            if(!categoryDTO.getName().trim().isEmpty()) {
                findCategoryDTO.setName(categoryDTO.getName());
            }
            if (!categoryDTO.getDescription().trim().isEmpty()) {
                findCategoryDTO.setDescription(categoryDTO.getDescription());
            }
            categoryService.addNewCategory(findCategoryDTO);
        }
        return "redirect:/staff/categories";
    }
}
