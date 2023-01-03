package com.baeldung.multipledb.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "products")
@Data
public class Product {

    @Id
    private int id;

    private String name;

    private double price;

    public Product() {
        super();
    }

}
