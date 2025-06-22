package com.ednaldo.aula_jpa.repositories;

import com.ednaldo.aula_jpa.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "categories")
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories")
    List<Product> searchAll();

//    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj IN :products")
//    List<Product> listPageProductCategories(@Param("products") List<Product> products);
}
