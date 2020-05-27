package com.semantive.backend.controllers;

import com.semantive.backend.entities.Order;
import com.semantive.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetOrdersController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getOrders() {
        System.out.println(orderRepository.findAll());
        return orderRepository.findAll().toString();
    }
}
