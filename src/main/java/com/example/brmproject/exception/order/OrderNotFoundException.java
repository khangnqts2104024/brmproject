package com.example.brmproject.exception.order;

import java.io.Serial;


public class OrderNotFoundException  extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String message) {
        super(message);
    }
}
