package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.ItemCounter;
import com.shop.backend.entities.Order;
import com.shop.backend.repos.ItemCounterRepository;
import com.shop.backend.repos.ItemRepository;
import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public String addOrder(@RequestBody Order order) {
        String name = order.getName();
        int age = order.getAge();

        if (name.contains(" ") || !name.matches("^[A-Z]([a-z]*)$")) {
            throw new IllegalArgumentException();
        }
        if (age<18 || age>100) throw new IllegalArgumentException();

        order.setCreated(new Date());
        orderRepository.save(order);

        List<Item> items = order.getItems();
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
            itemRepository.save(item);
        }

        List<ItemCounter> counterList = itemCounterRepository.findAll();
        for (ItemCounter ic : counterList) {
            ic.setTemporaryCount(ic.getCount());
            itemCounterRepository.save(ic);
        }

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
