package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    BookDTO addNewBook(BookDTO bookDTO);

    List<BookDTO> findAvailableBook();

    BookDTO findBookById(Integer bookId);

    List<BookDTO> getListBookByBookId(List<Integer> bookIds);

    List<BookDTO> findAllBooks();

    // Pagination
    Page<BookDTO> getAllBooks(int page, int size);

    void addQuantity(Integer number);
}
