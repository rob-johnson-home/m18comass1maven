package com.asc.data;

import com.asc.data.Order;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class OrderList {
    private static final Logger LOGGER = Logger.getLogger( OrderList.class.getName() );

    private static OrderList instance = null;

    /**
     * Default constructor
     */
    protected OrderList() {
        orders = new Hashtable<Integer,Order>();
    }

    /**
     *
     * @return
     */
    public static OrderList getInstance() {
        if (instance == null) {
            instance = new OrderList();
        }
        return instance;
    }

    /**
     *
     */
    private Hashtable<Integer,Order> orders;

    /**
     * @param order
     * @throws NullPointerException is id is null
     */
    public void add(Order order) {
        orders.put(order.getOrderId(), order);
    }

    /**
     * @param order
     */
    public void remove(Order order) {
        // TODO implement here
    }

    /**
     * return an order from the order id
     *
     * @param orderId
     * @return order or null if no order found
     * @throws NonExistentOrderException if id is not found
     */
    public Order get(int orderId) throws NonExistentOrderException{
        if (orders.containsKey(orderId)) {
            return orders.get(orderId);
        }
        throw new NonExistentOrderException();
    }

    /**
     * get list of all open (uncollected) orders
     *
     * @return ArrayList of orders
     */
    public ArrayList<Order> getOpen() {
        ArrayList<Order> rv = new ArrayList<>();
        for (Integer i : orders.keySet()) {
            if (!orders.get(i).isCollected()) {
                rv.add(orders.get(i));
            }
        }
        return rv;
    }

    /**
     * get list of all orders
     *
     * @return ArrayList of orders
     */
    public ArrayList<Order> get() {
        ArrayList<Order> rv = new ArrayList<>();
        rv.addAll(orders.values());
        return rv;
    }

}
