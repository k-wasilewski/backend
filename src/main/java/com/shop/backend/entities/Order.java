package com.shop.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonManagedReference
    private List<Item> items;
    private String name;
    private int age;
    private String username;
    private Date created;

    public Order() {}

    public Order(String name, int age) {
        this.name=name;
        this.age=age;
        created = new Date();
    }

    public Order(String name, int age, String username) {
        this.name=name;
        this.age=age;
        this.username=username;
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

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {return created;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
