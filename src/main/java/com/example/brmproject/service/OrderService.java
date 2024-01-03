package com.example.brmproject.service;

import com.example.brmproject.domain.dto.OrderDetailDTO;
import com.example.brmproject.domain.dto.OrdersDTO;

import java.util.List;

public interface OrderService {

    OrdersDTO createOrder(List<Integer> bookIdList,OrdersDTO ordersDTO);
    List<OrdersDTO> findAll();

    OrdersDTO updateOrder(OrdersDTO ordersDTO);

    OrdersDTO findById(Integer id);



    void rentOrder(Integer orderId);
    void completedOrder(Integer orderId);



}
