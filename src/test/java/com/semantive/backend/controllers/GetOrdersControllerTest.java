package com.semantive.backend.controllers;

import com.semantive.backend.entities.Order;
import com.semantive.backend.repos.OrderRepository;
import org.junit.Test;
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
public class GetOrdersControllerTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GetOrdersController getOrdersController;

    @Test
    public void getOrders_shouldReturnEmptyListToString_whenThereAreNoOrders() {
        //given
        List<Order> emptyOrderList = new ArrayList<>();

        //when
        String result = getOrdersController.getOrders();

        //then
        assertEquals(emptyOrderList.toString(), result);
    }

    @Test
    public void getOrders_shouldReturnOrderListToString_whenThereAreOrders() {
        //given
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
        List<Order> testOrdersList = new ArrayList<>();
        testOrdersList.add(testOrder);

        //when
        String result = getOrdersController.getOrders();

        //then
        assertEquals(testOrdersList.toString(), result);
    }
}
