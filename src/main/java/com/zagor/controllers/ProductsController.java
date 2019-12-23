package com.zagor.controllers;

import com.zagor.entities.Product;
import com.zagor.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@RestController
public class ProductsController {
    @Autowired
    ProductService productService;

    @GetMapping("/shop/product")
    public List<Product> getProducts(@RequestParam("nameFilter") String nameFilter){
        if(nameFilter.trim().length() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Required parameter nameFilter not presented!", null);
        }else{
            try {
                Pattern.compile(nameFilter);
            }catch (PatternSyntaxException e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad regular expresion presented in nameFilter!", null);
            }
        }

        return productService.getAllProductsWithFilter(nameFilter.trim());
    }


}
