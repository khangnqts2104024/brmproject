package com.example.brmproject.controller;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.BookDetailDTO;
import com.example.brmproject.domain.entities.BookDetailEntity;
import com.example.brmproject.domain.entities.BookEntity;
import com.example.brmproject.repositories.BookDetailEntityRepository;
import com.example.brmproject.service.BookDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookDetail")
public class BookDetailController {

    private final BookDetailService bookDetailService;

    public BookDetailController(BookDetailService bookDetailService) {
        this.bookDetailService = bookDetailService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BookDetailDTO> listDetail = bookDetailService.findAll();
        model.addAttribute("listDetail", listDetail);
        return "/book/list-bookDetails";
    }

    @GetMapping("/showFormForAddDetail")
    public String showFormForAddBookDetail(Model theModel) {
        BookDetailDTO bookDetail = new BookDetailDTO();
        theModel.addAttribute("bookDetail", bookDetail);
        return"/book/book_detail-form";
    }

    @PostMapping("/saveBookDetail")
    public String saveBookDetail(@ModelAttribute(name = "bookDetail") BookDetailDTO bookDetail) {
        bookDetailService.createBook(bookDetail);
        return "redirect:/bookDetail/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(name = "bookDetailId") int bookDetailId, Model theModel) {
        Optional<BookDetailEntity> bookDetail = bookDetailService.findById(bookDetailId);
        theModel.addAttribute("bookDetail", bookDetail);
        return "/book/book_detail-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "bookDetailId") int bookDetailId) {
        bookDetailService.deleteById(bookDetailId);
        return "redirect:/bookDetail/list";
    }
}
