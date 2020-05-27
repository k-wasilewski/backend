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

import java.util.Arrays;
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

        System.out.println("array"+Arrays.toString(itemsArr));

        Order o = new Order("fefr", 23);
        orderRepository.save(o);
        itemRepository.save(new Item("frefe", "fefre", o));

        for (String item : itemsArr) {
            if (item.charAt(item.length()-1)==',') {
                item = item.substring(0, item.length() - 1);
            }
            System.out.println("item: "+item);

            Matcher idMatcher = idRegex.matcher(item);
            Matcher colorMatcher = colorRegex.matcher(item);
            Matcher sizeMatcher = sizeRegex.matcher(item);

            while (idMatcher.find()) System.out.println("id="+idMatcher.group(1));
            while (colorMatcher.find()) System.out.println("color="+colorMatcher.group(1));
            while (sizeMatcher.find()) System.out.println("size="+sizeMatcher.group(1));
        }

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
