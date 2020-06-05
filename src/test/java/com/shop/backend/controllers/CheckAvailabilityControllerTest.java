package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.Order;
import com.shop.backend.repos.ItemRepository;
import com.shop.backend.repos.OrderRepository;
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
    void checkAvailability_shouldReturnSuccess_whenItemsAreAvailable() {
        //given
        String color = "blue";
        String size = "s";

        //when, then
        assertEquals("success", checkAvailabilityController
                .checkAvailability(color, size));
    }

    @Test
    void checkAvailability_shouldReturnFail_whenItemsAreUnavailable() {
        //given
        String color = "blue";
        String size = "s";

        //when
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
        for (int i = 0; i < 5; i++) {
            itemRepository.save(new Item("blue", "s", testOrder));
            checkAvailabilityController.checkAvailability("blue", "s");
        }

        //then
        assertEquals("fail", checkAvailabilityController
                .checkAvailability("blue", "s"));
    }

    @Test
    void restoreAvailability_shouldRestoreTemporaryCountToCount() {
        //given
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
        for (int i = 0; i < 5; i++) {
            checkAvailabilityController.checkAvailability("blue", "s");
        }

        assertEquals("fail", checkAvailabilityController
                .checkAvailability("blue", "s"));

        //when
        checkAvailabilityController.restoreAvailability();

        //then
        assertEquals("success", checkAvailabilityController
                .checkAvailability("blue", "s"));
    }
}