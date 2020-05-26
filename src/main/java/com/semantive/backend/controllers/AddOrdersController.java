package com.semantive.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AddOrdersController {

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public String addOrders(@RequestParam("name") String name, @RequestParam("age") int age,
                            @RequestParam("orders") String orders) {
        String[] ordersArr = orders.split("(?=[0-9])");
        Pattern idRegex = Pattern.compile("^(\\d),");
        Pattern colorRegex = Pattern.compile(",(.*),");
        Pattern sizeRegex = Pattern.compile(",(\\w)$");

        System.out.println("array"+Arrays.toString(ordersArr));

        for (String order : ordersArr) {
            if (order.charAt(order.length()-1)==',') {
                order = order.substring(0, order.length() - 1);
            }
            System.out.println("order: "+order);
            Matcher idMatcher = idRegex.matcher(order);
            Matcher colorMatcher = colorRegex.matcher(order);
            Matcher sizeMatcher = sizeRegex.matcher(order);

            while (idMatcher.find()) System.out.println("id="+idMatcher.group(1));
            while (colorMatcher.find()) System.out.println("color="+colorMatcher.group(1));
            while (sizeMatcher.find()) System.out.println("size="+sizeMatcher.group(1));
        }

        return "Zamówienie dla ["+name+", "+age+"] zostało przyjęte";
    }
}
