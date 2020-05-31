package com.semantive.backend.repos;

import com.semantive.backend.entities.ItemCounter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ItemCounterRepositoryTest {
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @Test
    void save() {
        ItemCounter testItemCounter = new ItemCounter("red", "g");
        itemCounterRepository.save(testItemCounter);

        assertEquals(testItemCounter, itemCounterRepository
                .findByColorAndSize("red", "g"));
    }

    @Test
    void findByColorAndSize() {
        ItemCounter itemCounter = new ItemCounter("blue", "s");

        assertEquals(itemCounter.getCount(), itemCounterRepository
                .findByColorAndSize("blue", "s").getCount());

        assertEquals(itemCounter.getTemporaryCount(), itemCounterRepository
                .findByColorAndSize("blue", "s").getTemporaryCount());

        assertEquals(itemCounter.getColor(), itemCounterRepository
                .findByColorAndSize("blue", "s").getColor());

        assertEquals(itemCounter.getSize(), itemCounterRepository
                .findByColorAndSize("blue", "s").getSize());
    }

    @Test
    void findAll() {
        assertEquals(12, itemCounterRepository.findAll().size());
    }
}