package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.dto.OrdersDTO;
import com.example.brmproject.domain.entities.BookEntity;
import com.example.brmproject.domain.entities.OrdersEntity;
import com.example.brmproject.exception.ResourceNotFoundException;
import com.example.brmproject.repositories.BookEntityRepository;
import com.example.brmproject.service.BookService;
import com.example.brmproject.ultilities.SD.BookDetailStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImp  implements BookService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookEntityRepository bookRepo;

    @Override
    public BookDTO addNewBook(BookDTO bookDTO) {
        BookEntity newBook = bookRepo.save(mapToEntity(bookDTO));
        return mapToDTO(newBook);
    }

    @Override
    public BookDTO findBookById(Integer bookId) {
        Optional<BookEntity> bookEntity = bookRepo.findById(bookId);
        if (bookEntity.isPresent()) {
            return mapToDTO(bookEntity.get());
        } else {
            throw new ResourceNotFoundException("Book", "Id", String.valueOf(bookEntity));
        }
    }

    @Override
    public List<BookDTO> findAvailableBook() {
        return bookRepo.findAll()
                .stream()
                .map(book -> countAvailable(book))
                .collect(Collectors.toList());
    }

    // Pagination
    @Override
    public Page<BookDTO> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> bookPage = bookRepo.findAll(pageable);
        return bookPage.map(this::mapToDTO);
    }

    @Override
    public List<BookDTO> findAllBooks() {
        return bookRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addQuantity(Integer number) {

    }

    public BookDTO mapToDTO(BookEntity book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public BookEntity mapToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, BookEntity.class);
    }

    public BookDTO countAvailable(BookEntity book) {
        Long availableBook = book.getBookDetailsById()
                .stream()
                .filter(b ->
                        b.getStatus()
                                .equalsIgnoreCase(String.valueOf(BookDetailStatus.AVAILABLE)))
                .count();
        //add count to book dto
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        bookDTO.setAvailableBook(availableBook);
        return bookDTO;
    }
}
