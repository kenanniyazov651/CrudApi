package com.rest.app.repository;

import com.rest.app.model.Product;

import java.util.List;

public interface ProductRepository {
    boolean createProductTable();

    boolean save(Product product);

    boolean saveBatch(List<Product> productList);

    Product findById(int id);

    List<Product> findProducts();

}