package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDetailDTO;
import com.example.brmproject.domain.entities.BookDetailEntity;

import java.util.List;
import java.util.Optional;

public interface BookDetailService {

    public List<BookDetailDTO> findAll();

    public Optional<BookDetailEntity> findById(int id);

    public BookDetailDTO createBook(BookDetailDTO bookDetailDTO);

    public void deleteById(int id);
}
