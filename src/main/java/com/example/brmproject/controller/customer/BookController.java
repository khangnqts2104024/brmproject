package com.example.brmproject.controller.customer;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.CategoryBookDTO;
import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.dto.CustomerDTO;
import com.example.brmproject.repositories.CategoryEntityRepository;
import com.example.brmproject.service.BookService;
import com.example.brmproject.service.CategoryBookService;
import com.example.brmproject.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryBookService categoryBookService;

    @GetMapping("admin/books/new")
    public String getCreateBookPage(Model model) {
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "adminTemplate/books/addNewBook";
    }

    @PostMapping("admin/books/new")
    public String createNewBook(@ModelAttribute BookDTO bookDTO) {
        BookDTO newBook = bookService.addNewBook(bookDTO);
        for (Integer categoryId : bookDTO.getCategoriesId()) {
            CategoryBookDTO categoryBookDTO = new CategoryBookDTO();
            categoryBookDTO.setCategoryByBookId(categoryService.findById(categoryId));
            categoryBookDTO.setBookByBookId(newBook);
            categoryBookService.add(categoryBookDTO);
        }
        return "redirect:/admin/books";
    }

    @GetMapping("admin/books")
    public String getAllBooksAdmin(Model model) {
        List<BookDTO> list = bookService.findAllBooks();
        model.addAttribute("books",list);
        return "adminTemplate/books/showAllBook";
    }

    @GetMapping("customers/books")
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

    @GetMapping("admin/books/update/{bookId}")
    public String getUpdateBookPage(@PathVariable Integer bookId,
                                    Model model) {
        BookDTO findBook = bookService.findBookById(bookId);
        List<CategoryDTO> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("book", findBook);
        return "adminTemplate/books/updateBook";
    }

    @PostMapping("admin/books/update/{bookId}")
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
        }
        bookService.addNewBook(findBook);
        return "redirect:/admin/books";
    }

    @GetMapping("customers/books/categoryId/{categoryId}")
    public String getBooksByCategoryId(@PathVariable  Integer categoryId,
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
