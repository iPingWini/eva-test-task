package com.zagor.services.impl;

import com.zagor.dao.ProductDao;
import com.zagor.entities.Product;
import com.zagor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    @Cacheable(cacheNames="productsWithFilter")
    public List<Product> getAllProductsWithFilter(String regExp) {
        System.out.println("Service is working");
        return productDao.getProducts().stream().filter(product -> !product.getName().matches(regExp) ).collect(Collectors.toList());
    }

}
