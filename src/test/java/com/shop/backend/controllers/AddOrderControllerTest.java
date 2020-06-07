package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.Order;
import com.shop.backend.repos.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AddOrderControllerTest {
    @Autowired
    AddOrderController addOrderController;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void addOrder_shouldThrowException_whenIncorrectNameIsPassed() {
        //given
        Order testOrder = new Order("fdDff!#$&%g323f", 30);

        //when, then
        assertThrows(IllegalArgumentException.class, () ->
                addOrderController.addOrder(testOrder));
    }

    @Test
    void addOrder_shouldThrowException_whenIncorrectAgeIsPassed() {
        //given
        Order testOrder = new Order("Kuba", 13);

        //when, then
        assertThrows(IllegalArgumentException.class, () ->
                addOrderController.addOrder(testOrder));
    }

    @Test
    void addOrder_shouldSaveOrderAndReturnSuccessMsg_whenAgeAndNameAreCorrectAndItemsAreAvailable() {
        //given
        String name = "Kuba";
        int age = 30;
        Order testOrder = new Order(name, age);

        //when
        String resp = addOrderController.addOrder(testOrder);

        //then
        assertEquals(testOrder, orderRepository.findByNameAndAge(
                testOrder.getName(), testOrder.getAge()));

        assertEquals("Zamówienie dla ["+name+", "+age+"] zostało przyjęte",
                resp);

        assertEquals("Kuba", orderRepository.findAll().get(0).getName());
        assertEquals(30, orderRepository.findAll().get(0).getAge());
    }

    @Test
    void addOrder_shouldReturnErrorMsg_whenItemsAreUnavailable() {
        //given
        String name = "Kuba";
        int age = 30;
        Order testOrder = new Order(name, age);
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Item testItem = new Item();
            testItem.setColor("blue");
            testItem.setSize("s");
            testItem.setOrder(testOrder);
            items.add(testItem);
        }

        //when
        String resp = addOrderController.addOrder(testOrder);

        //then
        assertEquals(null, orderRepository.findByNameAndAge(
                testOrder.getName(), testOrder.getAge()));

        assertEquals("error: Towar [blue, s] chwilowo niedostępny", resp);
    }
}