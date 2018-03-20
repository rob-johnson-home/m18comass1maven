package com.asc.data;

import com.asc.data.Order;
import java.util.*;

/**
 * @author rob johnson
 */
public class OrderList {

    private static OrderList instance = null;

    /**
     * Default constructor
     */
    protected OrderList() {
        orders = new ArrayList<Order>();
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
    private ArrayList<Order> orders;

    /**
     * @param order
     */
    public void add(Order order) {
        orders.add(order);
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
     */
    public Order get(int orderId) {
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                return o;
            }
        }
        return null;
    }

    /**
     * get list of all open (uncollected) orders
     *
     * @return ArrayList of orders
     */
    public ArrayList<Order> getOpen() {
        ArrayList<Order> rv = new ArrayList<>();
        for (Order o : orders) {
            if (!o.isCollected()) {
                rv.add(o);
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
        for (Order o : orders) {

            rv.add(o);
        }
        return rv;
    }

}
