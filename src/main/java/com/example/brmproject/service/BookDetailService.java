package com.example.brmproject.service;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.BookDetailDTO;


public interface BookDetailService {

    void updateStatus(BookDetailDTO bookDetailDTO,String status);

    void updateStatusByBookId(Integer bookId,String status);

    void updateStatusByid(Integer bookDetailId,String status);

    BookDTO countAvailable(Integer bookId);

    BookDetailDTO addBookDetails(BookDetailDTO bookDetailDTO);



}
