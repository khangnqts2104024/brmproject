package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookDTO addNewBook(BookDTO bookDTO);

    BookDTO findBookById(Integer bookId);

    List<BookDTO> findAvailableBook();

    // Pagination
    Page<BookDTO> getAllBooks(int page, int size);

    List<BookDTO> findAllBooks();

    void addQuantity(Integer number);

}
