package com.asc.ui;

import com.asc.data.Item;
import com.asc.data.NonExistentItemException;
import com.asc.data.Order;
import com.asc.data.OrderLineItem;
import com.asc.ui.PrintableOrder;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class OrderLabel implements PrintableOrder {
    private static final Logger LOGGER = Logger.getLogger( OrderLabel.class.getName() );

    /**
     * Default constructor
     */
    public OrderLabel(Order o) {
        LOGGER.setLevel(Level.FINE);
        orderDescription = "Order Label to be attached to Bag";
        customerName = o.getCustomer().getName();
        customerPhone = o.getCustomer().getPhone();
        orderContent = "";
        LOGGER.log(Level.FINE, "Adding items to label " + o.getItems());
        for (OrderLineItem oli : o.getItems()) {
            try {
                //LOGGER.log(Level.FINE,oli.getQuantity());
                LOGGER.log(Level.FINE,oli.toString());
                LOGGER.log(Level.FINE,oli.getName());
                LOGGER.log(Level.FINE,oli.getDescription());
                orderContent += oli.getQuantity() ;
                orderContent += " : " + oli.getName() + " : " + oli.getDescription();
            } catch (NonExistentItemException ex) {
                orderContent += "Invalid Item";
            }
        }
    }

    
    /**
     * 
     */
    private String orderDescription;
    private String customerName;
    private String customerPhone;
    private String orderContent;

    @Override
    public void print() {
        System.out.println("########### Redirect to printer###########");
        System.out.println(orderDescription);
        System.out.println(customerName);
        System.out.println(customerPhone);
        System.out.println(orderContent);
        System.out.println("########### End of Print       ###########");
    }
}