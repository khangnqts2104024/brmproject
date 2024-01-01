package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.BookshelfCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookshelfCaseEntityRepository extends JpaRepository<BookshelfCaseEntity, Integer> {
}