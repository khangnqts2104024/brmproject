package com.example.brmproject.service.imp;

import com.example.brmproject.domain.dto.BookDetailDTO;
import com.example.brmproject.domain.entities.BookDetailEntity;
import com.example.brmproject.exception.ResourceNotFoundException;
import com.example.brmproject.repositories.BookDetailEntityRepository;
import com.example.brmproject.service.BookDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookDetailServiceImp implements BookDetailService {

    private ModelMapper modelMapper;
    private BookDetailEntityRepository bookDetailRepository;

    @Autowired
    public BookDetailServiceImp(ModelMapper modelMapper, BookDetailEntityRepository bookDetailRepository) {
        this.modelMapper = modelMapper;
        this.bookDetailRepository = bookDetailRepository;
    }

    @Override
    public List<BookDetailDTO> findAll() {
        List<BookDetailDTO> list = bookDetailRepository.findAll()
                .stream()
                .map(bookDetails -> mapToDTO(bookDetails))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Optional<BookDetailEntity> findById(int id) {
        Optional<BookDetailEntity> bookDetail = bookDetailRepository.findById(id);
        return bookDetail;
    }

    @Override
    public BookDetailDTO createBook(BookDetailDTO bookDetailDTO) {
        BookDetailEntity bookDetail = bookDetailRepository
                .save(mapToEntity(bookDetailDTO));

        BookDetailEntity newBookDetail = bookDetailRepository.findById(bookDetail.getId())
                .orElseThrow(() ->new ResourceNotFoundException("BookDetail", "Id", String.valueOf(bookDetail.getId())));

        return mapToDTO(newBookDetail);
    }

    @Override
    public void deleteById(int id) {
        bookDetailRepository.deleteById(id);
    }

    //map to dto
    public BookDetailDTO mapToDTO(BookDetailEntity bookDetail) {
        BookDetailDTO bookDetailDTO = modelMapper.map(bookDetail, BookDetailDTO.class);
        return bookDetailDTO;
    }

    public BookDetailEntity mapToEntity(BookDetailDTO bookDetailDTO) {
        BookDetailEntity bookDetail = modelMapper.map(bookDetailDTO, BookDetailEntity.class);
        return bookDetail;
    }
}
