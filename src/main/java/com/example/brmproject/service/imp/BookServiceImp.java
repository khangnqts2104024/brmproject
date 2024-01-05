package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.BookDTO;
import com.example.brmproject.domain.entities.BookEntity;
import com.example.brmproject.exception.ResourceNotFoundException;
import com.example.brmproject.repositories.BookEntityRepository;
import com.example.brmproject.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {

    private ModelMapper modelMapper;
    private BookEntityRepository bookRepository;

    @Autowired
    public BookServiceImp(ModelMapper modelMapper, BookEntityRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> list = bookRepository.findAll()
                .stream()
                .map(books -> mapToDTO(books))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Optional<BookEntity> findById(int id) {
        Optional<BookEntity> bookDTO = bookRepository.findById(id);
        return bookDTO;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity book = bookRepository.save(mapToEntity(bookDTO));
        BookEntity newBook = bookRepository
                .findById(book.getId())
                .orElseThrow(()->new ResourceNotFoundException("Book","Id",String.valueOf(book.getId())));

        return mapToDTO(newBook);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    //map to dto
    public BookDTO mapToDTO(BookEntity book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

    public BookEntity mapToEntity(BookDTO bookDTO) {
        BookEntity book = modelMapper.map(bookDTO, BookEntity.class);
        return book;

    }
}
