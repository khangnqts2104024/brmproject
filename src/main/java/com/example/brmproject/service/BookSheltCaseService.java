package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookshelfCaseDTO;
import com.example.brmproject.domain.entities.BookshelfCaseEntity;

import java.util.List;
import java.util.Optional;

public interface BookSheltCaseService {

    public List<BookshelfCaseDTO> findAll();

    public Optional<BookshelfCaseEntity> findById(int id);

    public BookshelfCaseDTO createBookShelf(BookshelfCaseDTO bookshelfCaseDTO);

    public void deleteById(int id);
}
