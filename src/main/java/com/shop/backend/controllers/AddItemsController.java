package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.Order;
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
    //public int addOrders(@RequestBody Order order) {
    public String addItems(@RequestBody List<Item> items) {
        /*String name = order.getName();
        int age = order.getAge();

        if (name.contains(" ") || !name.matches("^[A-Z]([a-z]*)$")) {
            throw new IllegalArgumentException();
        }
        if (age<18 || age>100) throw new IllegalArgumentException();

        //Order order = new Order(name, age);
        orderRepository.save(order);*/

        /*List<Item> items = order.getItems();
        for (Item item : items) {
            System.out.println(item);
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
        return "is all ok!";*/
        //return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
        System.out.println(items);
        int orderId=0;
        for(Item item : items) {
            orderId = item.getOrder().getId();
            itemRepository.save(item);
        }
        String name = orderRepository.findById(orderId).get().getName();
        int age = orderRepository.findById(orderId).get().getAge();

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
