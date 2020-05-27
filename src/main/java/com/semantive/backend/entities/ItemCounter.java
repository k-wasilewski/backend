package com.semantive.backend.entities;

import javax.persistence.*;
import java.util.NoSuchElementException;

@Entity
@Table(name="item_counters")
public class ItemCounter {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String color;

    private String size;

    private int count = 101;

    public ItemCounter() {}

    public ItemCounter(String color, String size) {
        this.color=color;
        this.size=size;
        this.count--;

        if (this.count<0) throw new NoSuchElementException();
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

    public void setCount(int count) {
        this.count = count;
    }
}
