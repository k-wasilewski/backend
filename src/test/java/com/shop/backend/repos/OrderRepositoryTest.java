package com.shop.backend.repos;

import com.shop.backend.entities.Order;
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
    void save_shouldSaveOrderToDatabase() {
        //given
        Order testOrder = new Order("Kuba", 30);

        //when
        orderRepository.save(testOrder);

        //then
        assertEquals(testOrder, orderRepository.findAll().get(0));
    }

    @Test
    void findAllByUsername_shouldReturnAllOrdersAccordinglySavedToDatabase() {
        //given
        Order testOrder = new Order("Kuba", 30, "kuba");

        //when
        orderRepository.save(testOrder);

        //then
        assertEquals(testOrder, orderRepository.findAllByUsername("kuba").get(0));
        assertEquals(1, orderRepository.findAllByUsername("kuba").size());
    }
}