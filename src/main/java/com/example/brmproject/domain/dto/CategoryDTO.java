package com.example.brmproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private int id;

    private String name;

    private String description;
//    private Collection<CategoryBookDTO> categoryBooksById;

}
