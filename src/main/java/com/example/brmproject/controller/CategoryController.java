package com.example.brmproject.controller;

import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.entities.CategoryEntity;
import com.example.brmproject.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "/book/list-category";
    }

    @GetMapping("/showForm") // link : /category/showForm
    public String showForm(Model model) {
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categoryDTO", categoryDTO);
        return "/book/category-form";
    }

    @PostMapping("/addCategory") // link : /category/addCategory
    public String saveCategory(@ModelAttribute(name = "categoryDTO")
                                           CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return "redirect:/category/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(name = "categoryId") int categoryId, Model theModel) {
        Optional<CategoryEntity> categoryDTO = categoryService.findById(categoryId);
        theModel.addAttribute("categoryDTO", categoryDTO);
        return "/book/category-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "categoryId") int categoryId) {
        categoryService.deleteById(categoryId);
        return "redirect:/category/list";
    }

}
