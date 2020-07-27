package com.shop.backend.controllers;

import com.shop.backend.entities.Order;
import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetOrdersController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/auth/list")
    @CrossOrigin(origins = "https://localhost:3000")
    public List<Order> getOrders(@RequestParam String username) {
        return orderRepository.findAllByUsername(username);
    }
}
