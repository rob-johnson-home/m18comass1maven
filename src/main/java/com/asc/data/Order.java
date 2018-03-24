package com.asc.data;

import com.asc.ui.PrintableOrder;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class Order {
    private static final Logger LOGGER = Logger.getLogger( Order.class.getName() );

    /**
     *
     * @return
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param orderDate
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *
     * @return
     */
    public LocalDate getFullfillDate() {
        return fullfillDate;
    }

    /**
     *
     * @param fullfillDate
     */
    public void setFullfillDate(LocalDate fullfillDate) {
        this.fullfillDate = fullfillDate;
    }

    /**
     *
     * @return
     */
    public LocalDate getCollectDate() {
        return collectDate;
    }

    /**
     *
     * @param collectDate
     */
    public void setCollectDate(LocalDate collectDate) {
        this.collectDate = collectDate;
    }

    /**
     *
     * @return
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     *
     * @param paid
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     *
     * @return
     */
    public boolean isFulfilled() {
        return isFulfilled;
    }

    /**
     *
     * @param fulfilled
     */
    public void setFulfilled(boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    /**
     *
     * @return
     */
    public boolean isCollected() {
        return isCollected;
    }

    /**
     *
     * @param collected
     */
    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    /**
     *
     * @return
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     *
     * @param orderId
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return
     */
    public StaffMember getStaffMember() {
        return staffMember;
    }

    /**
     *
     * @param staffMember
     */
    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    /**
     *
     * @return
     */
    public ArrayList<OrderLineItem> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(ArrayList<OrderLineItem> items) {
        this.items = items;
    }

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * 
     */
    private LocalDate orderDate;

    /**
     * 
     */
    private LocalDate fullfillDate;

    /**
     * 
     */
    private LocalDate collectDate;

    /**
     * 
     */
    private boolean isPaid;

    /**
     * 
     */
    private boolean isFulfilled;

    /**
     * 
     */
    private boolean isCollected;

    /**
     * 
     */
    public int orderId;


        /**
     * 
     */
    private Customer customer;

    /**
     * 
     */
    private StaffMember staffMember;

    /**
     * 
     */
    private ArrayList<OrderLineItem> items;

    /**
     * @param printType 
     * @return
     */
    public PrintableOrder printOrder(PrintableOrder printType) {
        // TODO implement here
        return null;
    }

    

    /**
     * @param staff
     */
    public void takeOwnership( StaffMember staff) {
        // TODO implement here
    }

}