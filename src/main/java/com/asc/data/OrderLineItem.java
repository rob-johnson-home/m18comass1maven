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

    /**
     *
     * @return
     */
    public int getId() {
        return Id;
    }

    /**
     *
     * @param Id
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public int getItemId() {
        return itemId;
    }

    /**
     *
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    //price, name , description
    
    public double getPrice() throws NonExistentItemException {
        return StockList.getInstance().get(itemId).getPrice();
    }
    
    public String getName() throws NonExistentItemException {
        return StockList.getInstance().get(itemId).getName();
    }
    
    public String getDescription() throws NonExistentItemException {
        return StockList.getInstance().get(itemId).getDescription();
    }


}