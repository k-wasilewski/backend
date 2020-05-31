package com.semantive.backend.repos;

import com.semantive.backend.entities.Item;
import com.semantive.backend.entities.Order;
import org.junit.Before;
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
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    Order testOrder;

    @Before
    void init() {
        testOrder = new Order("Kuba", 30);
        orderRepository.save(testOrder);
    }

    @Test
    void save_findAll() {
        Item testItem = new Item("blue", "s", testOrder);
        itemRepository.save(testItem);

        assertEquals(testItem, itemRepository.findAll().get(0));
        assertEquals(itemRepository.findAll().get(0).getOrder(), testOrder);
    }
}