package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface OrdersEntityRepository extends JpaRepository<OrdersEntity, Integer> {
    @Query("SELECT o FROM OrdersEntity o WHERE o.orderStatus LIKE :orderStatus")
    List<OrdersEntity> findByorderStatus(String orderStatus);
    @Query("SELECT o FROM OrdersEntity o WHERE o.customerId = :userId")
    List<OrdersEntity> findAllByUserId(int userId);
}
