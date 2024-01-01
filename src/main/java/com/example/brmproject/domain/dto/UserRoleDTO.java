package com.example.brmproject.domain.dto;

import com.example.brmproject.domain.entities.RoleEntity;
import com.example.brmproject.domain.entities.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    private UserDTO userByUserId;
    private RoleDTO roleByRoleId;

}
