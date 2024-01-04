package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersEntityRepository extends JpaRepository<OrdersEntity, Integer> {

    List<OrdersEntity> findByorderStatus(String orderStatus);}