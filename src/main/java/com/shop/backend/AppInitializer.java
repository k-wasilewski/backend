package com.shop.backend;

import com.shop.backend.entities.ItemCounter;
import com.shop.backend.repos.ItemCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppInitializer {
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @PostConstruct
    private void init() {
        itemCounterRepository.save(new ItemCounter("blue", "s"));
        itemCounterRepository.save(new ItemCounter("lightblue", "s"));
        itemCounterRepository.save(new ItemCounter("darkblue", "s"));

        itemCounterRepository.save(new ItemCounter("blue", "m"));
        itemCounterRepository.save(new ItemCounter("lightblue", "m"));
        itemCounterRepository.save(new ItemCounter("darkblue", "m"));

        itemCounterRepository.save(new ItemCounter("blue", "l"));
        itemCounterRepository.save(new ItemCounter("lightblue", "l"));
        itemCounterRepository.save(new ItemCounter("darkblue", "l"));

        itemCounterRepository.save(new ItemCounter("blue", "xl"));
        itemCounterRepository.save(new ItemCounter("lightblue", "xl"));
        itemCounterRepository.save(new ItemCounter("darkblue", "xl"));
    }
}
