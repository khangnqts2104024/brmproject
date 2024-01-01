package com.example.brmproject.repositories;

import com.example.brmproject.domain.entities.UserRoleEntity;
import com.example.brmproject.domain.entities.UserRoleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity, UserRoleEntityPK> {
}