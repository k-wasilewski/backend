package com.shop.backend.auth;

import com.shop.backend.entities.Order;
import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetOrdersController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/auth/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
