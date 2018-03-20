package com.asc.data;

import com.asc.data.Item;

import java.util.*;

/**
 * @author rob johnson
 */
public class StockList {

    private static StockList instance = null;
    /**
     * Default constructor
     */
    protected StockList() {
        
        stock = new ArrayList<Item>();
                
    }

    public static StockList getInstance() {
        if (instance == null) {
            instance = new StockList();
        }
        return instance;
    }
    /**
     * 
     */
    private ArrayList<Item> stock;


    /**
     * @param name 
     * @param description 
     * @param price
     */
    public void add(String name, String description, double price) {
        // TODO implement here
    }
    
    public void add(Item item) {
        stock.add(item);
    }

    /**
     * @param id
     */
    public void get(String id) {
        // TODO implement here
    }
    
    public ArrayList<Item> get() {
        
        return stock;
    }
    
}