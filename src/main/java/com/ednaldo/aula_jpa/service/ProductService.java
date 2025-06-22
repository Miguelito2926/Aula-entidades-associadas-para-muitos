package com.ednaldo.aula_jpa.service;

import com.ednaldo.aula_jpa.dto.CategoryDTO;
import com.ednaldo.aula_jpa.dto.ProductDTO;
import com.ednaldo.aula_jpa.entities.Category;
import com.ednaldo.aula_jpa.entities.Product;
import com.ednaldo.aula_jpa.repositories.CategoryRepository;
import com.ednaldo.aula_jpa.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        for (CategoryDTO cat : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(cat.getId());
            product.getCategories().add(category);
        }

        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public Page<ProductDTO> listPageProductCategories(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return page.map(ProductDTO::new);
    }


    @Transactional
    public List<ProductDTO> getProduct() {
        List<Product> all = productRepository.searchAll();
        return all.stream().map(ProductDTO::new).toList();
    }
}
