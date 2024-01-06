package com.example.brmproject.controller.customer;


import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.CategoryBookDTO;
import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.service.BookService;
import com.example.brmproject.service.CategoryBookService;
import com.example.brmproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("customers")
public class BookCustomersController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryBookService categoryBookService;

    @GetMapping("/books")
    public String getAllBooksCustomer(Model model,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "4") int size) {
        Page<BookDTO> listBooks = bookService.getAllBooks(page - 1, size);
        List<CategoryDTO> categoryList = categoryService.findAll();
        int totalPages = listBooks.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("books",listBooks);
        model.addAttribute("categories", categoryList);
        return "customerTemplate/books/showAllBook";
    }

    @GetMapping("books/categoryId/{categoryId}")
    public String getBooksByCategoryId(@PathVariable Integer categoryId,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "4") int size,
                                       Model model) {
        Page<CategoryBookDTO> categoryBooks = categoryBookService
                .findBooksByCategoryId(categoryId, page - 1, size);
        List<Integer> listBooksId = categoryBooks
                .stream()
                .map(categoryBook -> categoryBook.getBookId())
                .collect(Collectors.toList());
        List<BookDTO> listBooks = listBooksId
                .stream()
                .map(bookId -> bookService.findBookById(bookId))
                .collect(Collectors.toList());
        List<CategoryDTO> categoryList = categoryService.findAll();
        int totalPages = categoryBooks.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryBooks", categoryBooks);
        model.addAttribute("books", listBooks);
        model.addAttribute("categories", categoryList);
        return "customerTemplate/books/showListBooksByCategoryID";
    }
}
