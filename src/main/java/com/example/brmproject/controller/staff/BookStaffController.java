package com.example.brmproject.controller.staff;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.BookDetailDTO;
import com.example.brmproject.domain.dto.CategoryBookDTO;
import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.service.BookDetailService;
import com.example.brmproject.service.BookService;
import com.example.brmproject.service.CategoryBookService;
import com.example.brmproject.service.CategoryService;
import com.example.brmproject.ultilities.SD.BookDetailStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class BookStaffController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryBookService categoryBookService;

    @Autowired
    private BookDetailService bookDetailService;

    @GetMapping("/books/new")
    public String getCreateBookPage(Model model) {
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("book", new BookDTO());
        model.addAttribute("categories", categories);
        return "adminTemplate/books/addNewBook";
    }

    @PostMapping("/books/new")
    public String createNewBook(@ModelAttribute("book") @Valid BookDTO bookDTO,
                                BindingResult bindingResult,
                                Model model,
                                @RequestParam(name = "categoriesId", required = false) List<Integer> categoriesId) {

        if(bindingResult.hasErrors()) {
            List<CategoryDTO> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("book", bookDTO);
            return "adminTemplate/books/addNewBook";
        }
        BookDTO newBook = bookService.addNewBook(bookDTO);

        for (Integer categoryId : categoriesId) {
            CategoryBookDTO categoryBookDTO = new CategoryBookDTO();
            categoryBookDTO.setCategoryByBookId(categoryService.findById(categoryId));
            categoryBookDTO.setBookByBookId(newBook);
            categoryBookService.add(categoryBookDTO);
        }
        for (int i = 0; i < bookDTO.getQuantityBooks(); i++) {
            BookDetailDTO bookDetailDTO = new BookDetailDTO();
            bookDetailDTO.setBookByBookId(newBook);
            bookDetailDTO.setStatus(String.valueOf(BookDetailStatus.AVAILABLE));
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            bookDetailDTO.setImportDate(LocalDateTime.now().format(formatter));
            bookDetailService.addBookDetails(bookDetailDTO);
        }
        return "redirect:/staff/books";
    }

    @GetMapping("/books/showAll")
    public String showAll(Model model, @ModelAttribute BookDTO book) {
        List<BookDTO> list = bookService.findAvailableBook();
        model.addAttribute("books",list);
        return "customerTemplate/showAllBook";
    }

    @GetMapping("/books")
    public String getAllBooksAdmin(Model model, @ModelAttribute BookDTO book) {
        List<BookDTO> list = bookService.findAvailableBook();
        model.addAttribute("books",list);
        return "adminTemplate/books/showAllBook";
    }

    @GetMapping("/books/update/{bookId}")
    public String getUpdateBookPage(@PathVariable Integer bookId,
                                    Model model) {
        BookDTO findBook = bookService.findBookById(bookId);
        List<CategoryDTO> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("book", findBook);
        return "adminTemplate/books/updateBook";
    }

    @PostMapping("/books/update/{bookId}")
    public String updateBook(Model model,
                             @ModelAttribute("newBook") BookDTO bookDTO,
                             @PathVariable Integer bookId) {
        BookDTO findBook = bookService.findBookById(bookId);
        if (!bookDTO.getTitle().trim().isEmpty()) {
            findBook.setTitle(bookDTO.getTitle());
        }
        if (!bookDTO.getAuthor().trim().isEmpty()) {
            findBook.setAuthor(bookDTO.getAuthor());
        }
        if (!bookDTO.getPreview().trim().isEmpty()) {
            findBook.setPreview(bookDTO.getPreview());
        }
        if (bookDTO.getPricePerDay() > 0) {
            findBook.setPricePerDay(bookDTO.getPricePerDay());
        }
        if (!bookDTO.getPhoto().trim().isEmpty()) {
            findBook.setPhoto(bookDTO.getPhoto());
        }
        if (bookDTO.getQuantityBooks() != null && bookDTO.getQuantityBooks() >= 0) {
            int newQuantity = findBook.getQuantityBooks() != null ? findBook.getQuantityBooks() : 0;
            newQuantity += bookDTO.getQuantityBooks();
            findBook.setQuantityBooks(newQuantity);

            for (int i = 0; i < bookDTO.getQuantityBooks(); i++) {
                BookDetailDTO bookDetailDTO = new BookDetailDTO();
                bookDetailDTO.setBookByBookId(findBook);
                bookDetailDTO.setStatus(String.valueOf(BookDetailStatus.AVAILABLE));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                bookDetailDTO.setImportDate(LocalDateTime.now().format(formatter));
                bookDetailService.addBookDetails(bookDetailDTO);
                }
        }
        bookService.addNewBook(findBook);
        return "redirect:/staff/books";
    }
}
