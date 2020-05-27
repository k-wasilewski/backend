package com.semantive.backend.repos;

import com.semantive.backend.entities.Item;
import com.semantive.backend.entities.ItemCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCounterRepository extends JpaRepository<ItemCounter, Integer> {
}
