package com.semantive.backend.controllers;

import com.semantive.backend.entities.Item;
import com.semantive.backend.entities.Order;
import com.semantive.backend.repos.ItemCounterRepository;
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
class CheckAvailabilityControllerTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CheckAvailabilityController checkAvailabilityController;

    @Test
    void checkAvailability() {
        assertEquals("success", checkAvailabilityController
                .checkAvailability("blue", "s"));

        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
        for (int i = 0; i < 5; i++) {
            itemRepository.save(new Item("blue", "s", testOrder));
            checkAvailabilityController.checkAvailability("blue", "s");
        }

        assertEquals("fail", checkAvailabilityController
                .checkAvailability("blue", "s"));
    }

    @Test
    void restoreAvailability() {
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
        for (int i = 0; i < 5; i++) {
            checkAvailabilityController.checkAvailability("blue", "s");
        }

        assertEquals("fail", checkAvailabilityController
                .checkAvailability("blue", "s"));

        checkAvailabilityController.restoreAvailability();

        assertEquals("success", checkAvailabilityController
                .checkAvailability("blue", "s"));
    }
}