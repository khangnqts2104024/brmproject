package com.example.brmproject.service;

import com.example.brmproject.domain.dto.CustomerDTO;
import com.example.brmproject.domain.entities.CustomerEntity;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customer);

    List<CustomerDTO> findAll();
}
