package com.ednaldo.aula_jpa.repositories;

import com.ednaldo.aula_jpa.entities.Category;
import com.ednaldo.aula_jpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
