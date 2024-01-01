package com.example.brmproject.domain.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int id;

    private String name;

    private String phone;

    private String address;

    private String email;

    private Double debit;

    private Integer userId;
}
