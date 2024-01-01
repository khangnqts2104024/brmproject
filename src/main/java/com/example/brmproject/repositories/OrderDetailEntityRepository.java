package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailEntityRepository extends JpaRepository<OrderDetailEntity, Integer> {
}