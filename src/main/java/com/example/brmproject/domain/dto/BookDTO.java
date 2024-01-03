package com.example.brmproject.domain.dto;

import com.example.brmproject.domain.entities.BookDetailEntity;
import com.example.brmproject.domain.entities.CategoryBookEntity;
import com.example.brmproject.domain.entities.OrderDetailEntity;
import com.example.brmproject.ultilities.SD.BookDetailStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private int id;

    private String title;

    private String author;

    private String preview;

    private Double pricePerDay;

    private String photo;

    private Integer bookshelfId;


    private Collection<BookDetailDTO> bookDetailsById;

    //show availableBook.
    private Long avalableBook;

    private Integer numberOfImport;
//    private BookshelfCaseDTO bookshelfCaseByBookshelfId;




//    private Collection<OrderDetailDTO> orderDetailsById;
}
