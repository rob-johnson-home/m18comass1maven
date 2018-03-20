package com.asc.control;


import com.asc.data.Order;
import com.asc.data.OrderList;
import com.asc.data.StaffList;
import com.asc.data.StockList;
import com.asc.ui.OrderPrintUI;
import com.asc.ui.StaffOrderControlUI;
import java.time.LocalDate;
import java.util.*;

/**
 * @author rob johnson
 */
public class StaffOrderController {

    /**
     * Default constructor
     */
    public StaffOrderController() {
        
    }
    /**
     * 
     */
    private OrderPrintUI orderPrintUI;

    /**
     * 
     */
    private OrderList orderList;

    /**
     * 
     */
    private StockList stockList;

    /**
     * 
     */
    private StaffList staffList;

    /**
     * 
     */
    public void checkOrder() {
        // TODO implement here
    }

    /**
     * 
     */
    public void printOrder() {
        // TODO implement here
    }

    /**
     * set fulfilled to true and add todays date
     * @param orderId the order id
     */
    public void fulfillOrder(int orderId) {
        OrderList ol = OrderList.getInstance();
        Order o = ol.get(orderId);
        o.setFulfilled(true);
        o.setFullfillDate(LocalDate.now());
    }

    /**
     * 
     */
    public void viewOrders() {
        OrderList ol = OrderList.getInstance();
        ArrayList<Order> orders = ol.getOpen();
        for (Order o : orders) {
            System.out.println("Order id : " + o.getOrderId() + 
                    " Order Date : " + o.getOrderDate() +
                    " Customer Name : " + o.getCustomer().getName()) ;
        }
        
    }

    public void viewOrder(int id) {
        Order o = OrderList.getInstance().get(id);
        System.out.println("Order id : " + o.getOrderId() + 
                    " Order Date : " + o.getOrderDate() +
                    " Customer Name : " + o.getCustomer().getName()) ;
        System.out.println("Paid : " + o.isPaid());
        System.out.println("Fulfilled : " + o.isFulfilled()+  " FulFill Date : " + o.getFullfillDate());
        System.out.println("Collected : " + o.isCollected() +  " COllected Date : " + o.getCollectDate());
        System.out.println("Customer Details : ");
        System.out.println("Name : " + o.getCustomer().getName());
        System.out.println("Address : " + o.getCustomer().getAddress());
        System.out.println("Phone : " + o.getCustomer().getPhone());
        System.out.println("Email : " + o.getCustomer().getEmail());
        System.out.println("Staff Member Assigned : " + (( o.getStaffMember() != null) ? o.getStaffMember().getName() : "Not Assigned"));
    }

    public void checkOrder(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printOrderLabel(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printOrder(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}