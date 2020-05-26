package com.semantive.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class AddOrdersController {

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public String addOrders(@RequestParam("name") String name, @RequestParam("age") int age,
                            @RequestParam("orders") String orders) {
        String[] ordersArr = orders.split("(?<=[0-9])(?=[0-9])|(?<=[0-9])(?= )");
        System.out.println(orders);
        System.out.println(Arrays.toString(ordersArr));

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
