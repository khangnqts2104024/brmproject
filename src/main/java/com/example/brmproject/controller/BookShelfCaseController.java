package com.example.brmproject.controller;

import com.example.brmproject.domain.dto.BookshelfCaseDTO;
import com.example.brmproject.domain.entities.BookshelfCaseEntity;
import com.example.brmproject.service.BookSheltCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookshelfCase")
public class BookShelfCaseController {

    private final BookSheltCaseService bookShelfCaseService;

    public BookShelfCaseController(BookSheltCaseService bookShelfCaseService) {
        this.bookShelfCaseService = bookShelfCaseService;
    }

    @GetMapping("/showFormBookShelfCase")//=> bookshelfCase/showFormBookSheltCase
    public String showFromBookShelfCase(Model model) {
        BookshelfCaseDTO bookshelfCaseDTO = new BookshelfCaseDTO();
        model.addAttribute("bookshelfCaseDTO", bookshelfCaseDTO);
        return "/book/bookshelfCase-form";
    }

    @PostMapping("/addBookShelfCase") // => bookshelfCase/addBookSheltCase
    public String saveBookshelfCase(@ModelAttribute(name = "bookshelfCaseDTO")
                                            BookshelfCaseDTO bookshelfCaseDTO) {
        bookShelfCaseService.createBookShelf(bookshelfCaseDTO);

        return "redirect:/bookshelfCase/list";
    }

    @GetMapping("/list")
    public String listBookShelf(Model theModel) {
        List<BookshelfCaseDTO> listBookShelf = bookShelfCaseService.findAll();
        theModel.addAttribute("listBookShelf", listBookShelf);
        return "/book/list-bookshelfCase";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(name = "bookshelfCaseId") int bookshelfCaseId, Model theModel) {
        Optional<BookshelfCaseEntity> bookshelfCaseDTO = bookShelfCaseService.findById(bookshelfCaseId);
        theModel.addAttribute("bookshelfCaseDTO", bookshelfCaseDTO);
        return "book/bookshelfCase-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "bookshelfCaseId") int bookshelfCaseId) {
        bookShelfCaseService.deleteById(bookshelfCaseId);
        return "redirect:/bookshelfCase/list";
    }
}
