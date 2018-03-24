package com.asc.control;


import com.asc.TestJsonOrderClient;
import com.asc.data.NonExistentOrderException;
import com.asc.data.NonExistentStaffException;
import com.asc.data.Order;
import com.asc.data.OrderList;
import com.asc.data.StaffList;
import com.asc.data.StaffMember;
import com.asc.data.StockList;
import com.asc.ui.OrderLabel;
import com.asc.ui.OrderPickList;
import com.asc.ui.OrderPrintUI;
import com.asc.ui.StaffOrderControlUI;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class StaffOrderController {

        private static final Logger LOGGER = Logger.getLogger( StaffOrderController.class.getName() );

    /**
     * Default constructor
     */
    public StaffOrderController() {
        LOGGER.setLevel(Level.FINE);
    }

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
     * set fulfilled to true and add todays date
     * @param orderId the order id
     * @param staffId the id of the staff member to assign
     * @throws NonExistentOrderException if id is not in OrderList
     * @throws NonExistentStaffException if id is not in StaffList
     */
    public void fulfillOrder(int orderId, int staffId) throws NonExistentOrderException,NonExistentStaffException{
        OrderList ol = OrderList.getInstance();
        Order o = ol.get(orderId);
        
        StaffList sl = StaffList.getInstance();
        StaffMember s = sl.get(staffId);
        
        o.setFulfilled(true);
        o.setStaffMember(s);
        o.setFullfillDate(LocalDate.now());
    }

    /**
     * view list of open orders
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

    /**
     * view of order
     * @param id
     * @throws NonExistentOrderException if id is not in OrderList
     */
    public void viewOrder(int id) throws NonExistentOrderException {
        Order o = OrderList.getInstance().get(id);
        if (o == null) {
            throw new NonExistentOrderException();
        }
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

   

    /**
     * get an order label to be kept with fulfilled order
     * @param id
     * @throws NonExistentOrderException if id is not in OrderList
     */
    public OrderLabel getOrderLabel(int id) throws NonExistentOrderException {
        Order o = OrderList.getInstance().get(id);
        if (o == null) {
            throw new NonExistentOrderException();
        }
        LOGGER.log(Level.FINE, "creating label");
        OrderLabel ol = new OrderLabel(o);
        return ol;
    }

    /**
     * 
     * @param id id of Order
     * @throws NonExistentOrderException if id is not in OrderList
     * 
     */
    public OrderPickList getOrderPickList(int id) throws NonExistentOrderException {
        Order o = OrderList.getInstance().get(id);
        if (o == null) {
            throw new NonExistentOrderException();
        }
        OrderPickList opl = new OrderPickList(o);
        return opl;
    }
}