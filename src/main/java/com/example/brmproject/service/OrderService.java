package com.example.brmproject.service;

import com.example.brmproject.domain.dto.OrderDetailDTO;
import com.example.brmproject.domain.dto.OrdersDTO;

import java.util.List;

public interface OrderService {

    OrdersDTO createOrder(OrdersDTO order,List<Integer> bookIdList);
    List<OrdersDTO> findAll();

    OrdersDTO updateOrder(OrdersDTO ordersDTO);

    OrdersDTO findById(Integer id);



    void rentOrder(Integer orderId);
    void completedOrder(Integer orderId);



}
