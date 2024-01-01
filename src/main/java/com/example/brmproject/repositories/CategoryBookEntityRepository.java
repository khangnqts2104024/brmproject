package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.CategoryBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryBookEntityRepository extends JpaRepository<CategoryBookEntity, Integer> {
}