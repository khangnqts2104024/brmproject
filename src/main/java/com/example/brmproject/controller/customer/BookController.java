package com.example.brmproject.controller.customer;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.CustomerDTO;
import com.example.brmproject.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class BookController {

    private   BookService service;

    private HttpServletRequest request;
    @Autowired
    public BookController(BookService service, HttpServletRequest request) {
        this.service = service;
        this.request = request;
    }

    @GetMapping("/books/showAll")
    public String showAll(Model model, @ModelAttribute BookDTO book)
    {
        List<BookDTO> list=service.findAll();
        int timeout = request.getSession().getMaxInactiveInterval();
        System.out.println("Session Timeout (seconds): " + timeout);

        model.addAttribute("books",list);
        model.addAttribute("time",timeout);
        return "customerTemplate/showAllBook";
    }





}
