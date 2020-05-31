package com.semantive.backend.controllers;

import com.semantive.backend.entities.Order;
import com.semantive.backend.repos.ItemRepository;
import com.semantive.backend.repos.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AddOrdersControllerTest {
    @Autowired
    AddOrdersController addOrdersController;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void addOrders_incorrect_name() {
        Order testOrder = new Order("fdDff!#$&%g323f", 30);
        String items = "0,blue,s,1,lightblue,m";

        assertThrows(IllegalArgumentException.class, () ->
                addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items));
    }

    @Test
    void addOrders_incorrect_age() {
        Order testOrder = new Order("Kuba", 13);
        String items = "0,blue,s,1,lightblue,m";

        assertThrows(IllegalArgumentException.class, () ->
                addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items));
    }

    @Test
    void addOrders_available() {
        Order testOrder = new Order("Kuba", 30);
        String items = "0,blue,s,1,lightblue,m";

        String resp = addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items);

        assertEquals("Zamówienie dla [Kuba, 30] zostało przyjęte", resp);

        assertEquals("Kuba", orderRepository.findAll().get(0).getName());
        assertEquals(30, orderRepository.findAll().get(0).getAge());

        assertEquals("blue", itemRepository.findAll().get(0).getColor());
        assertEquals("s", itemRepository.findAll().get(0).getSize());
        assertEquals("lightblue", itemRepository.findAll().get(1).getColor());
        assertEquals("m", itemRepository.findAll().get(1).getSize());
    }

    @Test
    void addOrders_unavailable() {
        Order testOrder = new Order("Kuba", 30);
        String items = "0,blue,s,1,blue,s,2,blue,s,3,blue,s,4,blue,s,5,blue,s";

        String resp = addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items);

        assertEquals("error: Towar [blue, s] chwilowo niedostępny", resp);
    }
}