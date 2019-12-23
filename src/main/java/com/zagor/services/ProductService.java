package com.zagor.services;

import com.zagor.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductsWithFilter(String regExp);
}
