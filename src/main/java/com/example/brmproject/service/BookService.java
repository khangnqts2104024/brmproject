package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public List<BookDTO> findAll();

    public Optional<BookEntity> findById(int id);

    public BookDTO createBook(BookDTO bookDTO);

    public void deleteById(int id);
}


