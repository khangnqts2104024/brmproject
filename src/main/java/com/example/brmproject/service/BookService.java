package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();

}
