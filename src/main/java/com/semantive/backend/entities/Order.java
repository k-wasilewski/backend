package com.semantive.backend.entities;

import javax.persistence.*;
import java.util.Date;
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
    private Date created;

    public Order() {}

    public Order(String name, int age) {
        this.name=name;
        this.age=age;
        created = new Date();
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

    public Date getCreated() {return created;}

    public String toString() {
        return "<"+this.name+", "+this.age+", " +
                ""+this.created+": "+this.items+">";
    }
}
