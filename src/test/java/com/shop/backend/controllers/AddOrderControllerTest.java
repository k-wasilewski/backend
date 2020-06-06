package com.shop.backend.controllers;

import com.shop.backend.entities.Order;
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
    void addOrder_shouldSaveOrderAndReturnItsId_whenAgeAndNameAreCorrect() {
        //given
        Order testOrder = new Order("Kuba", 30);

        //when
        int testOrderId = addOrderController.addOrder(testOrder);

        //then
        assertEquals(testOrderId, orderRepository.findByNameAndAge(
                testOrder.getName(), testOrder.getAge()));

        assertEquals("Kuba", orderRepository.findAll().get(0).getName());
        assertEquals(30, orderRepository.findAll().get(0).getAge());
    }
}