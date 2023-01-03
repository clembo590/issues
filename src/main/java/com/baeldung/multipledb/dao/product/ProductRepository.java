package com.baeldung.multipledb.dao.product;

import com.baeldung.multipledb.model.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPrice(double price, Pageable pageable);
}
