package com.zagor.dao;

import com.zagor.entities.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();
}
