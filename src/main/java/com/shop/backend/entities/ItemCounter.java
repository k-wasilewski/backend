package com.shop.backend.entities;

import javax.persistence.*;

@Entity
@Table(name="item_counters")
public class ItemCounter {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String color;

    private String size;

    private int count = 5;

    private int temporaryCount = count;

    private int available = 1;

    public ItemCounter() {}

    public ItemCounter(String color, String size) {
        this.color=color;
        this.size=size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void decrementCount() {
        this.count--;
        this.temporaryCount--;
    }

    public int isAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setTemporaryCount(int temporaryCount) {
        this.temporaryCount=temporaryCount;
    }

    public void decrementTemporaryCount() {
        this.temporaryCount--;
    }

    public int getTemporaryCount() {return this.temporaryCount;}
}
