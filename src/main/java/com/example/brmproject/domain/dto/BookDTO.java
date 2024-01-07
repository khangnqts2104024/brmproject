package com.example.brmproject.domain.dto;

import com.example.brmproject.ultilities.SD.BookDetailStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private int id;

    @NotNull(message = "Title required")
    @Length(max = 100, message = "Max title is 100 letters")
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
