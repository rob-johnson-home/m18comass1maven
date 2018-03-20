package com.asc.data;

import java.util.*;

/**
 * @author rob johnson
 */
public class OrderLineItem {

    /**
     * Default constructor
     */
    public OrderLineItem() {
    }

    /**
     * 
     */
    private int Id;

    /**
     * 
     */
    private int quantity;
    
    /**
     * 
     */
    private int itemId;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }



}