package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersEntityRepository extends JpaRepository<OrdersEntity, Integer> {
}