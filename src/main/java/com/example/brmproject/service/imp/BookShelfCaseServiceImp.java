package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.BookshelfCaseDTO;
import com.example.brmproject.domain.entities.BookshelfCaseEntity;
import com.example.brmproject.exception.ResourceNotFoundException;
import com.example.brmproject.repositories.BookshelfCaseEntityRepository;
import com.example.brmproject.service.BookSheltCaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookShelfCaseServiceImp implements BookSheltCaseService {

    private ModelMapper modelMapper;
    private BookshelfCaseEntityRepository bookshelfCaseRepository;

    @Autowired
    public BookShelfCaseServiceImp(ModelMapper modelMapper, BookshelfCaseEntityRepository bookshelfCaseRepository) {
        this.modelMapper = modelMapper;
        this.bookshelfCaseRepository = bookshelfCaseRepository;
    }

    @Override
    public List<BookshelfCaseDTO> findAll() {
        List<BookshelfCaseDTO> list = bookshelfCaseRepository.findAll()
                .stream()
                .map(bookshelfCase -> mapToDTO(bookshelfCase))
                .collect(Collectors.toList());
        return list;
    }


    @Override
    public BookshelfCaseDTO createBookShelf(BookshelfCaseDTO bookshelfCaseDTO) {
        BookshelfCaseEntity bookshelfCase = bookshelfCaseRepository.save(mapToEntity(bookshelfCaseDTO));
        BookshelfCaseEntity newBookshelfCase = bookshelfCaseRepository
                .findById(bookshelfCase.getId())
                .orElseThrow(() -> new ResourceNotFoundException("BookshelfCase", "Id", String.valueOf(bookshelfCase.getId())));

        return mapToDTO(newBookshelfCase);
    }

    @Override
    public Optional<BookshelfCaseEntity> findById(int id) {
       Optional<BookshelfCaseEntity> bookshelfCase = bookshelfCaseRepository.findById(id);
       return bookshelfCase;
    }

    @Override
    public void deleteById(int id) {
        bookshelfCaseRepository.deleteById(id);
    }


    //map to dto
    public BookshelfCaseDTO mapToDTO(BookshelfCaseEntity bookshelfCase) {
        BookshelfCaseDTO bookshelfCaseDTO = modelMapper.map(bookshelfCase, BookshelfCaseDTO.class);
        return bookshelfCaseDTO;

    }

    public BookshelfCaseEntity mapToEntity(BookshelfCaseDTO bookshelfCaseDTO) {
        BookshelfCaseEntity bookshelfCase = modelMapper.map(bookshelfCaseDTO, BookshelfCaseEntity.class);
        return bookshelfCase;

    }
}
