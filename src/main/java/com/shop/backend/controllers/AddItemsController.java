package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.ItemCounter;
import com.shop.backend.repos.ItemCounterRepository;
import com.shop.backend.repos.ItemRepository;
import com.shop.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddItemsController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @PostMapping("/addItems")
    @CrossOrigin(origins = "http://localhost:3000")
    public String addItems(@RequestBody List<Item> items) {
        int orderId=0;
        for(Item item : items) {
            orderId = item.getOrder().getId();
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
        String name = orderRepository.findById(orderId).get().getName();
        int age = orderRepository.findById(orderId).get().getAge();

        List<ItemCounter> counterList = itemCounterRepository.findAll();
        for (ItemCounter ic : counterList) {
            ic.setTemporaryCount(ic.getCount());
            itemCounterRepository.save(ic);
        }

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
