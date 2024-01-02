package com.example.brmproject.controller.customer;
import com.example.brmproject.domain.dto.*;
import com.example.brmproject.service.BookDetailService;
import com.example.brmproject.service.OrderService;
import com.example.brmproject.ultilities.SD.BookDetailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("session")
@RequestMapping("/customers")
public class OrderController {

     private OrderService service;
     private BookDetailService bdService;
    @Autowired
    public OrderController(OrderService service, BookDetailService bdService) {
        this.service = service;
        this.bdService = bdService;
    }
    @ModelAttribute("session")
    public MySession addSessionToModel() {
        return new MySession(new ArrayList<>());
    }
    @GetMapping("/addItem/{bookId}")
    public String addItemToOrder(@ModelAttribute("session")MySession session, @PathVariable Integer bookId, Model model) {
        //check dublicate
        BookDTO bookDTO=bdService.countAvailable(bookId);
        if(session.getSession().contains(bookId)){
           model.addAttribute("error","This book was added to your list!");
        }
        //check max orderdetail
        else if(session.getSession().size()>5)
        {
            model.addAttribute("error","Sorry! Maximum number of books to rend is 5!");
        }
        //check available
        else if(bookDTO.getAvalableBook()<=0)
        {
            model.addAttribute("error","Sorry! This book is not available now!");
        }
        else{
            BookDetailDTO bookDetailDTO= bookDTO.getBookDetailsById().stream().findAny().get();
            bdService.updateStatus(bookDetailDTO, BookDetailStatus.BOOKING.toString());
            session.getSession().add(bookId);
        }
        //check available
        return "customerTemplate/testAddProduct";
    }

    @GetMapping("/createOrder")
    public String createOrder(@ModelAttribute("session")MySession session,Model model)
    {
        List<Integer> bookIds =session.getSession();
        model.addAttribute("success","booking success!");
        return "testView";
    }
    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute("session")MySession session, @ModelAttribute OrdersDTO order,Model model)
    {
        if(session.getSession().isEmpty())
        {
            model.addAttribute("error","You have to choose book first!");
            return "testView";
        }

        OrdersDTO newOrders= service.createOrder(order,session.getSession());
        //update bookdetail status.
        model.addAttribute("success","booking success!");
        return "testView";
    }
}
