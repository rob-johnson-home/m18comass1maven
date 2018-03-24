package com.asc;

import com.asc.control.OrderController;
import com.asc.data.Customer;
import com.asc.data.Order;
import com.asc.data.OrderLineItem;
import com.asc.data.OrderList;
import com.asc.persistence.StaffRepo;
import com.asc.persistence.OrderRepo;
import com.asc.persistence.StockRepo;
import com.asc.ui.StaffOrderControlUI;
import com.asc.ui.WebOrderControlUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class OrderControlEntry {

    private static final Logger LOGGER = Logger.getLogger(OrderControlEntry.class.getName());

    // Database credentials
    String dbUrl = "jdbc:derby://localhost:1527/m18comass1db2"; // home
    //String dbUrl = "jdbc:derby://localhost:1527/h://m18comass1db2"; // school
    String dbUser = "johns418";
    String dbPassword = "johns418";

    static WebOrderControlUI wo;

    /**
     * Default constructor
     */
    public OrderControlEntry() {

        // initalise database
        StaffRepo sr;
        OrderRepo or;
        StockRepo str;
        try {
            LOGGER.setLevel(Level.FINE);
            LOGGER.log(Level.FINE, "Initialise persistent data");

            sr = new StaffRepo(dbUrl, dbPassword, dbUser);
            sr.read();
            or = new OrderRepo(dbUrl, dbPassword, dbUser);
            or.read();
            str = new StockRepo(dbUrl, dbPassword, dbUser);
            str.read();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to access database");
            LOGGER.log(Level.SEVERE, "Exception : " + e);
            LOGGER.log(Level.SEVERE, "Exception : " + e.getStackTrace());
            System.exit(0);
        }

        // lauch web interface in background
        wo = new WebOrderControlUI();
        Thread th = new Thread(wo);
        th.start();

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // all Logging handlers need to accept all levels of messages
        Handler[] handlers
                = Logger.getLogger("").getHandlers();
        for (int index = 0; index < handlers.length; index++) {
            handlers[index].setLevel(Level.FINE);
        }

        

        OrderControlEntry e = new OrderControlEntry();

        //e.introduceOrderToSystem();
        //e.CreateOrderJson();
        try {
            StaffOrderControlUI orderControlUI = new StaffOrderControlUI(); //

            orderControlUI.executeTask();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception in main program, exiting");
            LOGGER.log(Level.SEVERE, ex.toString());
        } finally {
            e.writeAndCloseDb();
            wo.setStop(Boolean.TRUE);
        }

    }

    /**
     *
     * Simple add order to system
     *
     */
    public void introduceOrderToSystem() {

        OrderList ol = OrderList.getInstance();

        ////String order1JSON = "{\"orderDate\":\"Jan 5, 2018 12:00:00 AM\",\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false,\"orderId\":1004,\"customer\":{\"name\":\"Rob\",\"email\":\"rob@gmail.com\",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":1}]}";
        //String order2JSON = "{\"orderDate\":\"Jan 6, 2018 12:00:00 AM\",\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false,\"orderId\":1005,\"customer\":{\"name\":\"Dave\",\"email\":\"rob@gmail.com\",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":1}]}";
        //String order3JSON = "{\"orderDate\":\"Jan 7, 2018 12:00:00 AM\",\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false,\"orderId\":1006,\"customer\":{\"name\":\"Fred\",\"email\":\"rob@gmail.com\",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":1}]}";
        String order1JSON = "{\"orderDate\":{\"year\":2018,\"month\":5,\"day\":1},\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false,\"orderId\":1001,\"customer\":{\"name\":\"Rob\",\"email\":\"rob@gmail.com\",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":1}]}";
        String order2JSON = "{\"orderDate\":{\"year\":2018,\"month\":5,\"day\":2},\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false,\"orderId\":1002,\"customer\":{\"name\":\"Rob\",\"email\":\"rob@gmail.com\",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":2}]}";
        String order3JSON = "{\"orderDate\":{\"year\":2018,\"month\":5,\"day\":3},\"isPaid\":false,\"isFulfilled\":false,\"isCollected\":false,\"orderId\":1003,\"customer\":{\"name\":\"Rob\",\"email\":\"rob@gmail.com\",\"address\":\"1 Broadway\",\"phone\":\"01242808063\"},\"items\":[{\"Id\":1,\"quantity\":1,\"itemId\":3}]}";

        Gson g = new Gson();
        Order o = g.fromJson(order1JSON, Order.class);
        ol.add(o);

        LOGGER.log(Level.FINE, "From Order : " + o.getOrderId());
        LOGGER.log(Level.FINE, "From Order : " + o.getCustomer().getName());

        o = g.fromJson(order2JSON, Order.class);
        ol.add(o);

        o = g.fromJson(order3JSON, Order.class);
        ol.add(o);
    }

    /**
     *
     */
    public void CreateOrderJson() {
        Customer c = new Customer();
        c.setName("Rob");
        c.setEmail("rob@gmail.com");
        c.setAddress("1 Broadway");
        c.setPhone("01242808063");

        OrderLineItem oli = new OrderLineItem();
        oli.setId(1);
        oli.setItemId(1);
        oli.setQuantity(1);
        oli.setOrderId(1001);
        ArrayList<OrderLineItem> items = new ArrayList<OrderLineItem>();
        items.add(oli);

        Order o = new Order();
        o.setOrderId(1001);
        o.setCollectDate(null);
        o.setFullfillDate(null);
        o.setOrderDate(LocalDate.of(2018, 05, 01));
        o.setFulfilled(false);
        o.setCollected(false);
        o.setPaid(false);
        o.setCustomer(c);
        o.setStaffMember(null);

        o.setItems(items);

        LOGGER.log(Level.FINE, "From Order : " + o.getOrderDate());

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        LOGGER.log(Level.FINE, gson.toJson(o));

    }

    private void writeAndCloseDb() {
        OrderRepo or;
        try {
            LOGGER.log(Level.FINE, "Write persistent data");
            or = new OrderRepo(dbUrl, dbPassword, dbUser);
            or.write();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to access database");
            LOGGER.log(Level.SEVERE, "Exception : " + e);
        }
    }
}
