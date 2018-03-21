package com.asc.ui;

import com.asc.data.NonExistentItemException;
import com.asc.data.Order;
import com.asc.data.OrderLineItem;
import com.asc.ui.PrintableOrder;

import java.util.*;

/**
 * @author rob johnson
 */
public class OrderLabel implements PrintableOrder {

    /**
     * Default constructor
     */
    public OrderLabel(Order o) {
        orderDescription = "Order";
        customerName = o.getCustomer().getName();
        customerPhone = o.getCustomer().getPhone();
        orderContent = "";
        for (OrderLineItem oli : o.getItems()) {
            orderContent += oli.getQuantity() ;
            try {
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
}