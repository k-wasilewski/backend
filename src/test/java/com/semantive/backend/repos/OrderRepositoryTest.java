package com.semantive.backend.repos;

import com.semantive.backend.entities.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void save() {
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);

        assertEquals(testOrder, orderRepository.getOne(0));
    }

    @Test
    void findAll() {
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);

        assertEquals(testOrder, orderRepository.findAll().get(0));
    }
}