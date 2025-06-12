package com.ednaldo.aula_jpa.dto;

import com.ednaldo.aula_jpa.entities.Category;
import com.ednaldo.aula_jpa.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        categories = entity.getCategories().stream().map(CategoryDTO::new).toList();
    }
}
