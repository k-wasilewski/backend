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
    public void getOrders() {
        List<Order> emptyOrderList = new ArrayList<>();
        assertEquals(emptyOrderList.toString(), getOrdersController.getOrders());

        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);

        List<Order> testOrdersList = new ArrayList<>();
        testOrdersList.add(testOrder);

        assertEquals(testOrdersList.toString(), getOrdersController.getOrders());
    }
}
