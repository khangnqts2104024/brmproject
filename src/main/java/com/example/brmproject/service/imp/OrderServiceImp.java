package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.CustomerDTO;
import com.example.brmproject.domain.dto.OrderDetailDTO;
import com.example.brmproject.domain.dto.OrdersDTO;
import com.example.brmproject.domain.entities.BookEntity;
import com.example.brmproject.domain.entities.CustomerEntity;
import com.example.brmproject.domain.entities.OrderDetailEntity;
import com.example.brmproject.domain.entities.OrdersEntity;
import com.example.brmproject.exception.ResourceNotFoundException;
import com.example.brmproject.repositories.*;
import com.example.brmproject.service.OrderDetailService;
import com.example.brmproject.service.OrderService;
import com.example.brmproject.ultilities.SD.OrderStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

   private OrdersEntityRepository orderRepository;
   private CustomerEntityRepository customerRepostory;
   private OrderDetailEntityRepository ODRepository;
   private BookEntityRepository bookRepository;

    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImp(OrdersEntityRepository orderRepository, CustomerEntityRepository customerRepostory, OrderDetailEntityRepository ODRepository, BookEntityRepository bookRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.customerRepostory = customerRepostory;
        this.ODRepository = ODRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }







//create order with orderdetail, check debit....
    @Override
    public OrdersDTO createOrder(OrdersDTO ordersDTO, List<Integer> bookIdList) {

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime bookingDateTime = LocalDateTime.now();
        String formattedNow = bookingDateTime.format(formatter);
        ordersDTO.setOrderStatus(OrderStatus.BOOKING);
        ordersDTO.setBookingDate(formattedNow);
        //total amount
        ordersDTO.setTotalAmount(countTotalAmount(bookIdList,ordersDTO.getRentDayAmount()));

        //get debit
        CustomerEntity customer=customerRepostory.findById(ordersDTO.getCustomerId()).get();
        double debit= customer.getDebit();

        if(debit>=ordersDTO.getTotalAmount())
        {
            OrdersEntity order= orderRepository.save(mapToEntity(ordersDTO));
            OrdersEntity newOrder= orderRepository.findById(order.getId()).orElseThrow(()->new ResourceNotFoundException("Order","Id",String.valueOf(order.getId())));

            createOrderDetail(bookIdList,newOrder.getId());
            //add all list/

            customer.setDebit(debit - ordersDTO.getTotalAmount());
            customerRepostory.save(customer);

            return mapToDTO(newOrder);
        }

        return null;
    }

    @Override
    public List<OrdersDTO> findAll() {

        List<OrdersDTO>list=orderRepository.findAll().stream().map(orders->mapToDTO(orders)).collect(Collectors.toList());

        return list;

    }



    @Override
    public OrdersDTO updateOrder(OrdersDTO ordersDTO) {
        Integer orderId=ordersDTO.getId();
       OrdersEntity order= orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order","id",String.valueOf(orderId)));

       return mapToDTO(order);
    }

    @Override
    public OrdersDTO findById(Integer id) {
        if(id!=null){
            OrdersEntity order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order","Id",String.valueOf(id)));
                return mapToDTO(order);
        }
        return null;
    }

    @Override
    public void rentOrder(Integer orderId) {

        OrdersEntity order= orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order","id",String.valueOf(orderId)));
        OrdersDTO ordersDTO= mapToDTO(order);
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime bookingDateTime = LocalDateTime.now();
            String formattedNow = bookingDateTime.format(formatter);
        if(ordersDTO.getOrderStatus().equals(OrderStatus.BOOKING)) {
            ordersDTO.setOrderStatus(OrderStatus.RENT);
            ordersDTO.setRentDate(formattedNow);

           orderRepository.save(mapToEntity(ordersDTO));
        }

    }

    @Override
    public void completedOrder(Integer orderId) {

            OrdersEntity order= orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order","id",String.valueOf(orderId)));
            OrdersDTO ordersDTO= mapToDTO(order);
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime rentTime = LocalDateTime.now();
            String formattedNow = rentTime.format(formatter);
            if(ordersDTO.getOrderStatus().equals(OrderStatus.RENT)||ordersDTO.getOrderStatus().equals(OrderStatus.OVERDUE) )
            {
                ordersDTO.setOrderStatus(OrderStatus.RENT);
                ordersDTO.setReturnDate(formattedNow);

                orderRepository.save(mapToEntity(ordersDTO));
            }

    }

    //create orderDetail
    public void createOrderDetail(List<Integer> bookIdList,Integer orderId)
    {

        for (Integer bookId:bookIdList) {
            if(bookId!=null){
            OrderDetailDTO orderDetail= new OrderDetailDTO();
            orderDetail.setBookId(bookId);
            orderDetail.setOrderId(orderId);
            ODRepository.save(mapODToEntity(orderDetail));

          }
        }

    }

    //get rent amount
    public Double countTotalAmount(List<Integer> bookIdList,Integer numberOfDays)
    {
        double totalAmount=0.0;
        for (Integer bookId:bookIdList)
        {
            if(bookId!=null)
            {
                BookEntity myBook=new BookEntity();
               myBook= bookRepository.findById(bookId).orElse(null);
               if(myBook!=null)
               {
               totalAmount+= myBook.getPricePerDay()*numberOfDays;
                }
            }

        }
        return totalAmount;
    }





    public OrdersDTO mapToDTO(OrdersEntity order) {
        OrdersDTO ordersDTO = modelMapper.map(order, OrdersDTO.class);
        return ordersDTO;

    }

    public OrdersEntity mapToEntity(OrdersDTO ordersDTO) {
        OrdersEntity order = modelMapper.map(ordersDTO, OrdersEntity.class);
        return order;

    }
    public OrderDetailDTO mapODToDTO(OrderDetailEntity orderDetail) {
        OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetail, OrderDetailDTO.class);
        return orderDetailDTO;

    }

    public OrderDetailEntity mapODToEntity(OrderDetailDTO ordersDDTO) {
        OrderDetailEntity orderDetail = modelMapper.map(ordersDDTO, OrderDetailEntity.class);
        return orderDetail;

    }
}
