package com.shop.backend.controllers;

import com.shop.backend.entities.Item;
import com.shop.backend.entities.Order;
import com.shop.backend.repos.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddItemsControllerTest {
    @Autowired
    AddItemsController addItemsController;
    @Autowired
    ItemRepository itemRepository;
    @Test
    void addItems_shouldReturnSuccessMsgAndSaveItems_whenItemsAreAvailable() {
        //given
        Order testOrder = new Order("Kuba", 30);
        Item testItem = new Item();
        testItem.setColor("blue");
        testItem.setSize("s");
        testItem.setOrder(testOrder);
        List<Item> items = Arrays.asList(testItem);

        //when
        String resp = addItemsController.addItems(items);

        //then
        assertEquals("Zamówienie dla [Kuba, 30] zostało przyjęte", resp);

        assertEquals("blue", itemRepository.findAll().get(0).getColor());
        assertEquals("s", itemRepository.findAll().get(0).getSize());
        assertEquals("lightblue", itemRepository.findAll().get(1).getColor());
        assertEquals("m", itemRepository.findAll().get(1).getSize());
    }

    @Test
    void addItems_shoudReturnErrorMsg_whenItemsAreUnavailable() {
        //given
        Order testOrder = new Order("Kuba", 30);
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Item testItem = new Item();
            testItem.setColor("blue");
            testItem.setSize("s");
            testItem.setOrder(testOrder);
            items.add(testItem);
        }

        //when
        String resp = addItemsController.addItems(items);

        //then
        assertEquals("error: Towar [blue, s] chwilowo niedostępny", resp);
    }
}