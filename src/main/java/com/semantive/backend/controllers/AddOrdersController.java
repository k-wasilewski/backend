package com.semantive.backend.controllers;

import com.semantive.backend.entities.Item;
import com.semantive.backend.entities.Order;
import com.semantive.backend.repos.ItemRepository;
import com.semantive.backend.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AddOrdersController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public String addOrders(@RequestParam("name") String name, @RequestParam("age") int age,
                            @RequestParam("items") String items) {
        String[] itemsArr = items.split("(?=[0-9])");
        Pattern idRegex = Pattern.compile("^(\\d),");
        Pattern colorRegex = Pattern.compile(",(.*),");
        Pattern sizeRegex = Pattern.compile(",((\\w)|(\\w\\w))$");

        Order order = new Order(name, age);
        orderRepository.save(order);

        String id;
        String color="";
        String size="";

        for (String item : itemsArr) {
            if (item.charAt(item.length()-1)==',') {
                item = item.substring(0, item.length() - 1);
            }
            Matcher idMatcher = idRegex.matcher(item);
            Matcher colorMatcher = colorRegex.matcher(item);
            Matcher sizeMatcher = sizeRegex.matcher(item);

            while (idMatcher.find()) id = idMatcher.group(1);
            while (colorMatcher.find()) color = colorMatcher.group(1);
            while (sizeMatcher.find()) size = sizeMatcher.group(1);

            itemRepository.save(new Item(color, size, order));
        }

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
