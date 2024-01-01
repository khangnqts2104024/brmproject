package com.example.brmproject.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_detail", schema = "brmproject", catalog = "")
public class OrderDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "book_id", nullable = true, insertable = false, updatable = false)
    private Integer bookId;
    @Basic
    @Column(name = "order_id", nullable = true, insertable = false, updatable = false)
    private Integer orderId;
    @Basic
    @Column(name = "review", nullable = true, length = 225)
    private String review;
    @Basic
    @Column(name = "rating", nullable = true)
    private Integer rating;

    @Basic
    @Column(name = "is_rating",nullable = false, columnDefinition = "TINYINT(1)", length = 1)
    private boolean isRating;
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity bookByBookId;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrdersEntity ordersByOrderId;




    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    public OrdersEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrdersEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }
}
