package com.shop.backend.repos;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.Order;
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
    void save_shouldSaveItemToDatabase() {
        //given
        Item testItem = new Item("blue", "s", testOrder);

        //when
        itemRepository.save(testItem);

        //then
        assertEquals(testItem, itemRepository.findAll().get(0));
        assertEquals(itemRepository.findAll().get(0).getOrder(), testOrder);
    }

    @Test
    void findAll_shouldReturnAllItemsSavedToDatabase() {
        //given
        Item testItem = new Item("blue", "s", testOrder);

        //when
        itemRepository.save(testItem);

        //then
        assertEquals(testItem, itemRepository.findAll().get(0));
        assertEquals(1, itemRepository.findAll().size());
    }
}