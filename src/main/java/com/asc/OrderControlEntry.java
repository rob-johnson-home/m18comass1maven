package com.asc;

import com.asc.data.Customer;
import com.asc.data.Order;
import com.asc.data.OrderLineItem;
import com.asc.data.OrderList;
import com.asc.persistence.StaffRepo;
import com.asc.persistence.OrderRepo;
import com.asc.persistence.StockRepo;
import com.asc.ui.StaffOrderControlUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author rob johnson
 */
public class OrderControlEntry {

    // Database credentials
    String dbUrl = "jdbc:derby://localhost:1527/m18comass1db2"; // home
    //String dbUrl = "jdbc:derby://localhost:1527/h://m18comass1db2"; // school
    String dbUser = "johns418";
    String dbPassword = "johns418";
    
    
    /**
     * Default constructor
     */
    public OrderControlEntry() {

        // initalise database
        
        StaffRepo sr;
        OrderRepo or;
        StockRepo str;
        try {
            System.out.println("Initialise persistent data");
            sr = new StaffRepo(dbUrl, dbPassword, dbUser);
            sr.read();
            or = new OrderRepo(dbUrl, dbPassword, dbUser);
            or.read();
            str = new StockRepo(dbUrl, dbPassword, dbUser);
            str.read();
        } catch (Exception e) {
            System.out.println("Failed to access database");
            System.out.println("Exception : " + e);
            System.out.println("Exception : " + e.getStackTrace());
            System.exit(0);
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        OrderControlEntry e = new OrderControlEntry();
        
            
        e.introduceOrderToSystem();
        //e.CreateOrderJson();
        
        StaffOrderControlUI orderControlUI = new StaffOrderControlUI(); //
        
        orderControlUI.executeTask();
        
        e.writeAndCloseDb();
        
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

        System.out.println("From Order : " + o.getOrderId());
        System.out.println("From Order : " + o.getCustomer().getName());
        
        o = g.fromJson(order2JSON, Order.class);
        ol.add(o);

        o = g.fromJson(order3JSON, Order.class);
        ol.add(o);
    }

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
        ArrayList<OrderLineItem> items = new ArrayList<OrderLineItem>();
        items.add(oli);
                
        Order o = new Order();
        o.setOrderId(1001);
        o.setCollectDate(null);
        o.setFullfillDate(null);
        o.setOrderDate( LocalDate.of(2018,05,01));
        o.setFulfilled(false);
        o.setCollected(false);
        o.setPaid(false);
        o.setCustomer(c);
        o.setStaffMember(null);
        
        o.setItems(items);

        System.out.println("From Order : " + o.getOrderDate());

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(o));

    }

    private void writeAndCloseDb() {
        OrderRepo or;
        try {
            System.out.println("Write persistent data");
            or = new OrderRepo(dbUrl, dbPassword, dbUser);
            or.write();
        } catch (Exception e) {
            System.out.println("Failed to access database");
            System.out.println("Exception : " + e);
        }
    }
}
