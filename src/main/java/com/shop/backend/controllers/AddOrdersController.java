package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.ItemCounter;
import com.shop.backend.entities.Order;
import com.shop.backend.repos.ItemCounterRepository;
import com.shop.backend.repos.ItemRepository;
import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AddOrdersController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public String addOrders(@RequestParam("name") String name, @RequestParam("age") int age,
                            @RequestParam("items") List<Item> items) {
        if (name.contains(" ") || !name.matches("^[A-Z]([a-z]*)$")) {
            throw new IllegalArgumentException();
        }
        if (age<18 || age>100) throw new IllegalArgumentException();

        Order order = new Order(name, age);
        orderRepository.save(order);

        for (Item item : items) {
            String color = item.getColor();
            String size = item.getSize();

            ItemCounter counter = itemCounterRepository.findByColorAndSize(color, size);
            if (counter.getCount()<1) counter.setAvailable(0);
            if (counter.isAvailable()==1) {
                counter.decrementCount();
            } else {
                return "error: Towar ["+color+", "+size+"] chwilowo niedostępny";
            }
            itemCounterRepository.save(counter);
        }

        List<ItemCounter> counterList = itemCounterRepository.findAll();
        for (ItemCounter ic : counterList) {
            ic.setTemporaryCount(ic.getCount());
            itemCounterRepository.save(ic);
        }

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
