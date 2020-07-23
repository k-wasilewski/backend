package com.shop.backend.repos;

import com.shop.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByNameAndAge(String name, int age);
    List<Order> findAllByUsername(String username);
}
