package com.example.brmproject.domain.dto;

import com.example.brmproject.domain.entities.BookEntity;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookshelfCaseDTO {
    private int id;

    private String bookshelfCode;

    private Integer caseNumber;

    private Integer capacity;

    private Integer availableBlank;

//    private Collection<BookDTO> booksById;
}
