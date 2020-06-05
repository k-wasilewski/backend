package com.shop.backend.repos;

import com.shop.backend.entities.ItemCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCounterRepository extends JpaRepository<ItemCounter, Integer> {
    ItemCounter findByColorAndSize(String color, String size);
}
