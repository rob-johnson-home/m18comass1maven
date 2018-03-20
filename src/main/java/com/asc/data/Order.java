package com.asc.data;

import com.asc.ui.PrintableOrder;
import java.time.LocalDate;
import java.util.*;

/**
 * @author rob johnson
 */
public class Order {
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getFullfillDate() {
        return fullfillDate;
    }

    public void setFullfillDate(LocalDate fullfillDate) {
        this.fullfillDate = fullfillDate;
    }

    public LocalDate getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(LocalDate collectDate) {
        this.collectDate = collectDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StaffMember getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    public ArrayList<OrderLineItem> getItems() {
        return items;
    }

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
     * @param StaffMember staff
     */
    public void takeOwnership( StaffMember staff) {
        // TODO implement here
    }

}