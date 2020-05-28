package com.semantive.backend.controllers;

import com.semantive.backend.entities.ItemCounter;
import com.semantive.backend.repos.ItemCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckAvailabilityController {
    @Autowired
    ItemCounterRepository itemCounterRepository;

    @PostMapping("/check")
    @CrossOrigin(origins = "http://localhost:3000")
    public String checkAvailability(@RequestParam("color") String color,
                                    @RequestParam("size") String size) {
        ItemCounter counter = itemCounterRepository.findByColorAndSize(color, size);
        int temporaryCount = counter.getTemporaryCount();

        if (temporaryCount<1) {
            return "fail";
        } else {
            counter.decrementTemporaryCount();
        }
        itemCounterRepository.save(counter);

        return "success";
    }

    @PostMapping("/restore")
    @CrossOrigin(origins = "http://localhost:3000")
    public String restoreAvailability() {
        List<ItemCounter> counterList = itemCounterRepository.findAll();
        for (ItemCounter ic : counterList) {
            ic.setTemporaryCount(ic.getCount());
            itemCounterRepository.save(ic);
        }

        return "success";
    }
}