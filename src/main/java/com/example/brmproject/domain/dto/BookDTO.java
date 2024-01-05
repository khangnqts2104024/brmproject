package com.example.brmproject.domain.dto;

import com.example.brmproject.domain.entities.BookDetailEntity;
import com.example.brmproject.domain.entities.CategoryBookEntity;
import com.example.brmproject.domain.entities.OrderDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    //private Integer categoryId;

    private BookshelfCaseDTO bookshelfCaseByBookshelfId;

    private Collection<BookDetailDTO> bookDetailsById;

//    private Collection<CategoryBookDTO> categoryBooksById;

//    private Collection<OrderDetailDTO> orderDetailsById;


//    ------------------------------------------

    /*public BookshelfCaseDTO getBookshelfCaseByBookshelfId() {
        return bookshelfCaseByBookshelfId;
    }

    public void setBookshelfCaseByBookshelfId(BookshelfCaseDTO bookshelfCaseByBookshelfId) {
        this.bookshelfCaseByBookshelfId = bookshelfCaseByBookshelfId;
    }*/

// --------------------------------

    /*public Collection<BookDetailDTO> getBookDetailsById() {
        return bookDetailsById;
    }

    public void setBookDetailsById(Collection<BookDetailDTO> bookDetailsById) {
        this.bookDetailsById = bookDetailsById;
    }

    public void addBookDetail (BookDetailDTO bookDetailDTO) {
        if (bookDetailsById == null) {
            bookDetailsById = new ArrayList<BookDetailDTO>();
        }
        bookDetailsById.add(bookDetailDTO);
    }*/

//    ------------------------------------------

   /* public Collection<CategoryBookDTO> getCategoryBooksById() {
        return categoryBooksById;
    }

    public void setCategoryBooksById(Collection<CategoryBookDTO> categoryBooksById) {
        this.categoryBooksById = categoryBooksById;
    }

    public void addCategory (CategoryBookDTO categoryBookDTO) {
        if (categoryBooksById == null) {
            categoryBooksById = new ArrayList<CategoryBookDTO>();
        }
        categoryBooksById.add(categoryBookDTO);
    }*/
}
