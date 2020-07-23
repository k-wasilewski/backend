package com.shop.backend.controllers;

import com.shop.backend.auth.GetOrdersController;
import com.shop.backend.entities.Order;
import com.shop.backend.repos.OrderRepository;
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
        List<Order> result = getOrdersController.getOrders();

        //then
        assertEquals(emptyOrderList, result);
    }

    @Test
    public void getOrders_shouldReturnOrderListToString_whenThereAreOrders() {
        //given
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
        List<Order> testOrdersList = new ArrayList<>();
        testOrdersList.add(testOrder);

        //when
        List<Order> result = getOrdersController.getOrders();

        //then
        assertEquals(testOrdersList, result);
    }
}
