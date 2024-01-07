package com.example.brmproject.controller.customer;


import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.CategoryBookDTO;
import com.example.brmproject.domain.dto.CategoryDTO;
import com.example.brmproject.domain.dto.ReviewRatingDTO;
import com.example.brmproject.service.BookService;
import com.example.brmproject.service.CategoryBookService;
import com.example.brmproject.service.CategoryService;
import com.example.brmproject.service.ReviewRatingService;
import com.example.brmproject.ultilities.StaticFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ReviewRatingService reviewRatingService;

    @GetMapping("/books")
    public String getAllBooksCustomer(Model model,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "4") int size,@ModelAttribute("alertMessage") String alertMessage,
                                      @ModelAttribute("alertError") String alertError)
    {
        //alert

        StaticFunction.showAlert(model,alertMessage,alertError);

        Page<BookDTO> listBooks = bookService.getAllBooks(page - 1, size);
        List<CategoryDTO> categoryList = categoryService.findAll();
        int totalPages = listBooks.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        for (BookDTO book: listBooks) {
            ReviewRatingDTO reviewRatingDTO = reviewRatingService.getReviewRatingByBook(book.getId());
            Double bookRating = reviewRatingDTO.getAvrRating();
            if (Double.isNaN(bookRating)) {
                book.setRating(0.0);
            } else {
                book.setRating(reviewRatingDTO.getAvrRating());
            }
        }

        model.addAttribute("books",listBooks);
        model.addAttribute("categories", categoryList);
        return "customerTemplate/books/showAllBook";
    }

    @GetMapping("/books/categoryId/{categoryId}")
    public String getBooksByCategoryId(@PathVariable Integer categoryId,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "4") int size,
                                       Model model,@ModelAttribute("alertMessage") String alertMessage,@ModelAttribute("alertError") String alertError)
     {
//alert
         StaticFunction.showAlert(model,alertMessage,alertError);

         Page<CategoryBookDTO> categoryBooks = categoryBookService
                .findBooksByCategoryId(categoryId, page - 1, size);
        List<BookDTO> listBooks = categoryBooks
                .stream()
                .map(categoryBook -> categoryBook.getBookByBookId())
                .collect(Collectors.toList());
//
//        List<BookDTO> listBooks = listBooksId
//                .stream()
//                .map(bookId -> bookService.findBookById(bookId))
//                .collect(Collectors.toList());

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

    @GetMapping("/books/detail/{bookId}")
    public String getBookDetail(@PathVariable Integer bookId,
                                Model model, @ModelAttribute("alertMessage") String alertMessage, @ModelAttribute("alertError") String alertError)
     {
//alert
         StaticFunction.showAlert(model,alertMessage,alertError);

         BookDTO bookDTO = bookService.findBookById(bookId);
        model.addAttribute("book", bookDTO);
        return "customerTemplate/books/bookDetail";
    }
}
