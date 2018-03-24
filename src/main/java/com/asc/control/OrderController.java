package com.asc.control;

import com.asc.data.Order;
import com.asc.data.OrderList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger( OrderController.class.getName() );
    
    /**
     * Default constructor
     */
    public OrderController() {
        LOGGER.setLevel(Level.FINE);
    }

    /**
     * @param orderJson JSON string containing order details
     * @return boolean true if order accepted, false otherwise
     */
    public Boolean newOrder(String orderJson) {
        try {
            Gson g = new Gson();
            Order o = g.fromJson(orderJson, Order.class);
            LOGGER.log( Level.FINE,"New Order : " + o.getItems());
            OrderList.getInstance().add(o);
            
            return true;
        } catch (JsonSyntaxException ex) {
            // probably couldn't parse it properly
            return false;
        }
        
        
    }

    /**
     * @param orderId 
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, String orderStatus) {
        // TODO implement here
    }

}