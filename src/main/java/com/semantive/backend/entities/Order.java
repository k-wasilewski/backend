package com.semantive.backend.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @OneToMany(mappedBy = "order")
    private List<Item> items;
    private String name;
    private int age;

    public Order() {}

    public Order(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return this.name+", "+this.age+": "+this.items;
    }
}
