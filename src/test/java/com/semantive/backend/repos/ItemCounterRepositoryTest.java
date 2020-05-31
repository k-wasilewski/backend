package com.semantive.backend.repos;

import com.semantive.backend.entities.ItemCounter;
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
    void save_shouldSaveItemCounterToDatabase() {
        //given
        ItemCounter testItemCounter = new ItemCounter("red", "g");

        //when
        itemCounterRepository.save(testItemCounter);

        //then
        assertEquals(testItemCounter, itemCounterRepository
                .findByColorAndSize("red", "g"));
    }

    @Test
    void findByColorAndSize_shouldReturnItemCounterSavedInAppInitializator() {
        //given
        ItemCounter itemCounter = new ItemCounter("blue", "s");

        //when, then
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
    void findAll_shouldReturnTwelveItemCountersSavedInAppInitializator() {
        //given
        int NUMBER_OF_ITEMCOUNTERS = 12;

        //when
        int result = itemCounterRepository.findAll().size();

        //then
        assertEquals(NUMBER_OF_ITEMCOUNTERS, result);
    }
}