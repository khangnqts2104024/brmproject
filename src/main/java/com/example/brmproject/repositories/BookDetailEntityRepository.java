package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.BookDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailEntityRepository extends JpaRepository<BookDetailEntity, Integer> {
}