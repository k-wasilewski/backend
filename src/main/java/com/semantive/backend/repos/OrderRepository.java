package com.semantive.backend.repos;

import com.semantive.backend.entities.Item;
import com.semantive.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAll();
}
