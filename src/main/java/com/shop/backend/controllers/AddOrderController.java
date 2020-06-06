package com.shop.backend.controllers;

import com.shop.backend.entities.Order;
import com.shop.backend.repos.ItemCounterRepository;
import com.shop.backend.repos.ItemRepository;
import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AddOrderController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @PostMapping("/addOrder")
    @CrossOrigin(origins = "http://localhost:3000")
    public int addOrder(@RequestBody Order order) {
        String name = order.getName();
        int age = order.getAge();

        if (name.contains(" ") || !name.matches("^[A-Z]([a-z]*)$")) {
            throw new IllegalArgumentException();
        }
        if (age<18 || age>100) throw new IllegalArgumentException();

        order.setCreated(new Date());
        orderRepository.save(order);
        return order.getId();
    }
}
