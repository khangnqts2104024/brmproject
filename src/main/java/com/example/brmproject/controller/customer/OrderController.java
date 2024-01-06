package com.example.brmproject.controller.customer;


import com.example.brmproject.domain.dto.*;
import com.example.brmproject.service.BookDetailService;
import com.example.brmproject.service.OrderDetailService;
import com.example.brmproject.service.BookService;
import com.example.brmproject.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("session")
@RequestMapping("customers")
public class OrderController {

     private OrderService service;
     private OrderDetailService odService;
     private BookDetailService bdService;
     private BookService bookService;
    @Autowired
    public OrderController(OrderService service, BookDetailService bdService, OrderDetailService odService, BookService bookService) {
        this.service = service;
        this.bdService = bdService;
        this.bookService = bookService;
        this.odService = odService;
    }

    @ModelAttribute("session")
    public MySession addSessionToModel() {
        return new MySession(new ArrayList<>());
    }

    @GetMapping("/addItem/{bookId}")
    public String addItemToOrder(@ModelAttribute("session")MySession session, Model model, @PathVariable String bookId) {
        //check dublicate

        BookDTO bookDTO=bdService.countAvailable(Integer.parseInt(bookId));
        if(session.getBookIdList().contains(Integer.parseInt(bookId))){
           model.addAttribute("error","This book was added to your list!");
        }
        //check max orderdetail
        else if(session.getBookIdList().size()>5)
        {
            model.addAttribute("error","Sorry! Maximum number of books to rend is 5!");
        }
        //check available
        else if(bookDTO.getAvailableBook()<=0)
        {
            model.addAttribute("error","Sorry! This book is not available now!");
        }
        else {
//            BookDetailDTO bookDetailDTO = bookDTO.getBookDetailsById().stream()
//                    .filter(bd -> bd.getStatus().equals(BookDetailStatus.AVAILABLE.toString())).findFirst().orElse(null);
//            if (bookDetailDTO != null) {
//                bdService.updateStatus(bookDetailDTO, BookDetailStatus.BOOKING.toString());
//                //add bd to session
//                session.getBookDetailList().add(bookDetailDTO);

                session.getBookIdList().add(Integer.parseInt(bookId));
            }

        //check available
        return "redirect:/customers/books/showAll";
    }
    @GetMapping("/removeBook/{id}")
    public String removeBook(@ModelAttribute("session")MySession session,@PathVariable Integer id)
    {
        //find bd in session have bookid=id.
//        BookDetailDTO bookDetailDTO= session.getBookDetailList().stream().filter(bookdt->bookdt.getBookId().equals(id)).findFirst().get();
//        bdService.updateStatus(bookDetailDTO, BookDetailStatus.AVAILABLE.toString());
//
//        session.getBookDetailList().remove(bookDetailDTO);
        session.getBookIdList().remove(Integer.valueOf(id));
        return "redirect:/customers/showCart";
    }
    @GetMapping("/removeAllBook")
    public String removeAllBook(@ModelAttribute("session")MySession session)
    {
        //change book detail status
//        for (BookDetailDTO bd:session.getBookDetailList())
//        {
//            bdService.updateStatus(bd, BookDetailStatus.AVAILABLE.toString());
//
//        }
//        //clear all session
//        session.getBookDetailList().clear();
        session.getBookIdList().clear();

        return "redirect:/customers/books/showAll";
    }


    @GetMapping("/showCart")
    public String showCart(@ModelAttribute("session")MySession session,Model model)
    {
        List<Integer> bookIds =session.getBookIdList();
        List<BookDTO> list= bookService.getListBookByBookId(bookIds);
        model.addAttribute("books",list);
        model.addAttribute("orderForm",new OrderFormDTO());
        return "customerTemplate/orders/cart";
    }
    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute("orderForm") @Valid OrderFormDTO orderForm,BindingResult bindingResult,@ModelAttribute("session")MySession session , Model model, RedirectAttributes redirectAttributes)
    {
    try{
    if (bindingResult.hasErrors()) {

        List<Integer> bookIds =session.getBookIdList();
        List<BookDTO> list= bookService.getListBookByBookId(bookIds);
        model.addAttribute("books",list);
        model.addAttribute("orderForm",orderForm);
        return "customerTemplate/orders/cart" ;
    }
    if(session.getBookIdList().isEmpty())
    {
        model.addAttribute("error","You have to choose book first!");
        return "redirect:/customers/books/showAll";
    }
    //check stock
    for (Integer bookId: session.getBookIdList())
    {
        BookDTO availableBook=bdService.countAvailable(bookId);
        if(availableBook==null || availableBook.getAvailableBook()<=0)
        {
            session.getBookIdList().remove(Integer.valueOf(bookId));
            model.addAttribute("error",availableBook.getTitle()+" not available anymore!");
            return "redirect:/customers/books/showAll";
        }
    }
    OrdersDTO myOrderDTO=new OrdersDTO();
    myOrderDTO.setRentDayAmount(orderForm.getRentDays());
    //gang cung test
    myOrderDTO.setCustomerId(1);
//null
    OrdersDTO dto= service.createOrder(session.getBookIdList(),myOrderDTO);
    //update bookdetail status.
    if(dto!=null)
    {
        model.addAttribute("success", "booking success!");

        //check book detail

        //change bookdetail status to Booked
//            for (BookDetailDTO bd:session.getBookDetailList())
//            {
//                bdService.updateStatus(bd, BookDetailStatus.BOOKED.toString());
//            }
//            session.getBookDetailList().clear();
        session.getBookIdList().clear();
    }
    return "redirect:/customers/books/showAll";

        }catch ( Exception e) {
            model.addAttribute("message",e.getMessage());
        return "/error";
            }

    }

    //staff


    @GetMapping("/orders")
    public String orders(Model model) {
        int userId = 1;
        List<OrdersDTO> orderList = service.getAllOrdersOfUser(userId);
        model.addAttribute("orders", orderList);
        model.addAttribute("amount", orderList.size());

        return "/customerTemplate/orders/orderList";
    }

    @GetMapping("/orders/{orderId}")
    public String order(@PathVariable("orderId") int orderId, Model model) {
        List<OrderDetailDTO> orderDetailList = odService.getOrdersDetail(orderId);
        model.addAttribute("orderDetails", orderDetailList);

        int order = orderDetailList.get(0).getOrderId();
        model.addAttribute("orderId", order);

        return "/customerTemplate/orders/orderDetail";
    }
}
