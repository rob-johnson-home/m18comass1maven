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

    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param item
     */
    public void add(Item item) {
        stock.add(item);
    }

    /**
     * @param id
     * @throws NonExsistentItem if id is null or does not exist
     */
    public Item get(int id) throws NonExistentItemException {
        if (stock.indexOf(id) == -1) throw new NonExistentItemException();
        return stock.get(id);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Item> get() {
        
        return stock;
    }
    
}