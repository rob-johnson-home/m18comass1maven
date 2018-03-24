package com.asc.data;

import java.util.*;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class Item {
    private static final Logger LOGGER = Logger.getLogger( Item.class.getName() );


    /**
     *
     */
    private double price;

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
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
