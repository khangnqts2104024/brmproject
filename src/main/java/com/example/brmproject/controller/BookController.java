package com.example.brmproject.controller;

import com.example.brmproject.domain.dto.*;
import com.example.brmproject.domain.entities.BookEntity;
import com.example.brmproject.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listBook(Model theModel) {
        List<BookDTO> books = bookService.findAll();
        theModel.addAttribute("books", books);
        return "/book/list-books";
    }

    @GetMapping("/showFormForAddBook")
    public String showFormForAdd(Model theModel) {
        BookDTO theBook = new BookDTO();
        theModel.addAttribute("book", theBook);
        return "book/book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute(name = "book") BookDTO theBook) {
        bookService.createBook(theBook);
        return "redirect:/book/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam(name = "bookId") int bookId, Model theModel) {
        Optional<BookEntity> theBook = bookService.findById(bookId);
        theModel.addAttribute("book", theBook);
        return "book/book-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "bookId") int bookId) {
        bookService.deleteById(bookId);
        return "redirect:/books/list";
    }






}
