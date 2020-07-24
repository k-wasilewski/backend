package com.shop.backend;

import com.shop.backend.entities.ItemCounter;
import com.shop.backend.repos.ItemCounterRepository;
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
class AppInitializerTest {
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @Test
    void init_shouldSave12ItemCounterEntities() {
        //given, when, then
        assertEquals(12, itemCounterRepository.findAll().size());
    }
}
