package com.vinogradov;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public OrderService() {
        System.out.println("OrderService constructor now called");
    }

    public String createOrder() {
        return "Order";
    }
}