package com.example.brmproject.domain.dto;

import com.example.brmproject.domain.entities.BookEntity;
import com.example.brmproject.domain.entities.OrdersEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private int id;

    private Integer bookId;
    private Integer orderId;
    private String review;
    private Integer rating;

    private boolean isRating;
    private BookDTO bookByBookId;
    private OrdersDTO ordersByOrderId;

}
