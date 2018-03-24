package com.asc.ui;

import com.asc.data.Item;
import com.asc.data.NonExistentItemException;
import com.asc.data.Order;
import com.asc.data.OrderLineItem;
import com.asc.ui.PrintableOrder;

import java.util.*;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class OrderPickList implements PrintableOrder {
    private static final Logger LOGGER = Logger.getLogger( OrderPickList.class.getName() );

    private String orderDescription;
    private String customerName;
    private String customerPhone;
    private String orderContent;

    /**
     * Default constructor
     */
    public OrderPickList(Order o) {
        orderDescription = "Order Pick List - Please Bag all Items and Confirm when complete";
        customerName = o.getCustomer().getName();
        customerPhone = o.getCustomer().getPhone();
        orderContent = "";
        if (o.getItems() != null) {
            for (OrderLineItem oli : o.getItems()) {
                orderContent += oli.getQuantity();
                try {
                    orderContent += " : " + oli.getName() + " : " + oli.getDescription();
                } catch (NonExistentItemException ex) {
                    orderContent += "Invalid Item";
                }
            }
        }
    }

    /**
     * 
     */
    private String OrderPicklist;

    @Override
    public void print() {
        System.out.println("########### Redirect to printer###########");
        System.out.println(orderContent);
        System.out.println("########### End of Print       ###########");
    }

}