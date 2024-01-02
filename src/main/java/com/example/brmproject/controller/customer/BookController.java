package com.example.brmproject.controller.customer;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.CustomerDTO;
import com.example.brmproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class BookController {

    BookService service;
    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }
    @GetMapping("/books/showAll")
    public String showAll(Model model)
    {
        List<BookDTO> list=service.findAll();
        model.addAttribute("books",list);
        return "customerTemplate/showAllBook";
    }

}
