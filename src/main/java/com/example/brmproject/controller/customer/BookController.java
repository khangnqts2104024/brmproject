package com.example.brmproject.controller.customer;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.service.BookService;
import com.example.brmproject.ultilities.StaticFunction;
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

 private BookService service;

 @Autowired
 public BookController(BookService service) {
 this.service = service;

 }


    @GetMapping("/books/showAll")
    public String showAll(Model model, @ModelAttribute BookDTO book,@ModelAttribute("alertMessage") String alertMessage,@ModelAttribute("alertError") String alertError)
    {
        StaticFunction.showAlert(model,alertMessage,alertError);

        List<BookDTO> list=service.findAll();
        model.addAttribute("books",list);
        return "customerTemplate/showAllBook";
    }

}
