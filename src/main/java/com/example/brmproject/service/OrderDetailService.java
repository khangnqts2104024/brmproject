package com.example.brmproject.service;

import com.example.brmproject.domain.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    OrderDetailDTO createOrderDetail(Integer bookId,Integer orderId);
    List<OrderDetailDTO> getOrdersDetail(int orderId);

    OrderDetailDTO markAsLost(Integer orderDetailId);



}
