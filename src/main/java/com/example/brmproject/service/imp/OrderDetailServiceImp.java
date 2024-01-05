package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.OrderDetailDTO;
import com.example.brmproject.domain.dto.OrdersDTO;
import com.example.brmproject.domain.entities.OrderDetailEntity;
import com.example.brmproject.domain.entities.OrdersEntity;
import com.example.brmproject.repositories.OrderDetailEntityRepository;
import com.example.brmproject.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImp implements OrderDetailService {


    private OrderDetailEntityRepository ODRepository;
    private ModelMapper modelMapper;
    @Autowired
    public OrderDetailServiceImp(OrderDetailEntityRepository ODRepository, ModelMapper modelMapper) {
        this.ODRepository = ODRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDetailDTO createOrderDetail(Integer bookId, Integer orderId) {
        OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
        orderDetailDTO.setBookId(bookId);
        orderDetailDTO.setOrderId(orderId);
        return null;
    }


    public OrderDetailDTO mapToDTO(OrderDetailEntity orderDetail) {
        OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetail, OrderDetailDTO.class);
        return orderDetailDTO;

    }

    public OrderDetailEntity mapToEntity(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity orderDetail = modelMapper.map(orderDetailDTO, OrderDetailEntity.class);
        return orderDetail;

    }
}
