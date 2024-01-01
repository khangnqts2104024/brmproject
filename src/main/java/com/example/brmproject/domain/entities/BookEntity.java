package com.example.brmproject.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "book", schema = "brmproject", catalog = "")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = true, length = 100)
    private String title;
    @Basic
    @Column(name = "author", nullable = true, length = 45)
    private String author;
    @Basic
    @Column(name = "preview", nullable = true, length = -1)
    private String preview;
    @Basic
    @Column(name = "price_per_day", nullable = true, precision = 2)
    private Double pricePerDay;
    @Basic
    @Column(name = "photo", nullable = true, length = -1)
    private String photo;
    @Basic
    @Column(name = "bookshelf_id", nullable = true, insertable = false, updatable = false)
    private Integer bookshelfId;
    @Basic
    @Column(name = "category_id", nullable = true, insertable = false, updatable = false)
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "bookshelf_id", referencedColumnName = "id")
    private BookshelfCaseEntity bookshelfCaseByBookshelfId;
    @OneToMany(mappedBy = "bookByBookId")
    private Collection<BookDetailEntity> bookDetailsById;
    @OneToMany(mappedBy = "bookByBookId")
    private Collection<CategoryBookEntity> categoryBooksById;
    @OneToMany(mappedBy = "bookByBookId")
    private Collection<OrderDetailEntity> orderDetailsById;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(preview, that.preview) && Objects.equals(pricePerDay, that.pricePerDay) && Objects.equals(photo, that.photo) && Objects.equals(bookshelfId, that.bookshelfId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, preview, pricePerDay, photo, bookshelfId, categoryId);
    }

    public BookshelfCaseEntity getBookshelfCaseByBookshelfId() {
        return bookshelfCaseByBookshelfId;
    }

    public void setBookshelfCaseByBookshelfId(BookshelfCaseEntity bookshelfCaseByBookshelfId) {
        this.bookshelfCaseByBookshelfId = bookshelfCaseByBookshelfId;
    }

    public Collection<BookDetailEntity> getBookDetailsById() {
        return bookDetailsById;
    }

    public void setBookDetailsById(Collection<BookDetailEntity> bookDetailsById) {
        this.bookDetailsById = bookDetailsById;
    }

    public Collection<CategoryBookEntity> getCategoryBooksById() {
        return categoryBooksById;
    }

    public void setCategoryBooksById(Collection<CategoryBookEntity> categoryBooksById) {
        this.categoryBooksById = categoryBooksById;
    }

    public Collection<OrderDetailEntity> getOrderDetailsById() {
        return orderDetailsById;
    }

    public void setOrderDetailsById(Collection<OrderDetailEntity> orderDetailsById) {
        this.orderDetailsById = orderDetailsById;
    }
}
