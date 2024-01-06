package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();

    List<BookDTO> getListBookByBookId(List<Integer> bookIds);

    //
    BookDTO addNewBook(BookDTO bookDTO);

    BookDTO findBookById(Integer bookId);

    List<BookDTO> findAllBooks();

    List<BookDTO> findAvailableBook();

    // Pagination
    Page<BookDTO> getAllBooks(int page, int size);

    void addQuantity(Integer number);

}
