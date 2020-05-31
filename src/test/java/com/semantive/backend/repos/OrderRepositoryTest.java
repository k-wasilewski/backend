package com.semantive.backend.repos;

import com.semantive.backend.entities.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Test
    void save_findAll() {
        Order testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);

        assertEquals(testOrder, orderRepository.findAll().get(0));
    }
}