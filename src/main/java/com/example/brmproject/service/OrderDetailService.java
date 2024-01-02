package com.example.brmproject.service;

import com.example.brmproject.domain.dto.OrderDetailDTO;

public interface OrderDetailService {

    OrderDetailDTO createOrderDetail(Integer bookId,Integer orderId);




}
