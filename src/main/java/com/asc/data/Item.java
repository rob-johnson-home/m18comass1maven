package com.asc.data;

import java.util.*;

/**
 * @author rob johnson
 */
public class Item {


    /**
     *
     */
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     */
    private int id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String description;
    /**
     * Default constructor
     */
    public Item() {
    }

}
