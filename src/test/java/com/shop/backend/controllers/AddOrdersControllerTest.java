package com.shop.backend.controllers;

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
class AddOrdersControllerTest {
    @Autowired
    AddOrdersController addOrdersController;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void addOrders_shouldThrowException_whenIncorrectNameIsPassed() {
        //given
        Order testOrder = new Order("fdDff!#$&%g323f", 30);
        String items = "0,blue,s,1,lightblue,m";

        //when, then
        assertThrows(IllegalArgumentException.class, () ->
                addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items));
    }

    @Test
    void addOrders_shouldThrowException_whenIncorrectAgeIsPassed() {
        //given
        Order testOrder = new Order("Kuba", 13);
        String items = "0,blue,s,1,lightblue,m";

        //when, then
        assertThrows(IllegalArgumentException.class, () ->
                addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items));
    }

    @Test
    void addOrders_shouldReturnSuccessMsgAndSaveItems_whenItemsAreAvailable() {
        //given
        Order testOrder = new Order("Kuba", 30);
        String items = "0,blue,s,1,lightblue,m";

        //when
        String resp = addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items);

        //then
        assertEquals("Zamówienie dla [Kuba, 30] zostało przyjęte", resp);

        assertEquals("Kuba", orderRepository.findAll().get(0).getName());
        assertEquals(30, orderRepository.findAll().get(0).getAge());

        assertEquals("blue", itemRepository.findAll().get(0).getColor());
        assertEquals("s", itemRepository.findAll().get(0).getSize());
        assertEquals("lightblue", itemRepository.findAll().get(1).getColor());
        assertEquals("m", itemRepository.findAll().get(1).getSize());
    }

    @Test
    void addOrders_shoudReturnErrorMsg_whenItemsAreUnavailable() {
        //given
        Order testOrder = new Order("Kuba", 30);
        String items = "0,blue,s,1,blue,s,2,blue,s,3,blue,s,4,blue,s,5,blue,s";

        //when
        String resp = addOrdersController.addOrders(testOrder.getName(), testOrder.getAge(), items);

        //then
        assertEquals("error: Towar [blue, s] chwilowo niedostępny", resp);
    }
}