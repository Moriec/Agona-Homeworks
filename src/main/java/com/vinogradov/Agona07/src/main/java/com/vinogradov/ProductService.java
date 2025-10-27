package com.vinogradov;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductService() {
        System.out.println("ProductService constructor now called");
    }

    public String getProduct() {
        return "Product";
    }
}