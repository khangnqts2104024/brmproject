package com.example.brmproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

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

    private List<Integer> categoriesId;

    private Integer quantityBooks;

    private Collection<BookDetailDTO> bookDetailsById;

    //show availableBook.
    private Long availableBook;

    private Integer numberOfImport;

    private BookshelfCaseDTO bookshelfCaseByBookshelfId;

//    private Collection<OrderDetailDTO> orderDetailsById;
}
