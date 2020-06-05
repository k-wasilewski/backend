package com.shop.backend.controllers;

import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetOrdersController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getOrders() {
        return orderRepository.findAll().toString();
    }
}
