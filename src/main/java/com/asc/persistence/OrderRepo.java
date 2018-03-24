package com.asc.persistence;

import com.asc.data.StaffMember;
import com.asc.data.Order;
import com.asc.data.Customer;
import com.asc.data.Item;
import com.asc.data.NonExistentStaffException;
import com.asc.data.OrderLineItem;
import com.asc.data.OrderList;
import com.asc.data.OrderList;
import com.asc.data.StaffList;
import com.asc.data.StockList;

import java.sql.*;
import java.text.DateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class OrderRepo implements Repo {
    private static final Logger LOGGER = Logger.getLogger( OrderRepo.class.getName() );

    Connection conn;

    /**
     * Default constructor
     *
     * @param dbUrl
     * @param dbPassword
     * @param dbUser
     * @throws SQLException if we cannot connect to the database
     */
    public OrderRepo(String dbUrl, String dbPassword, String dbUser) throws SQLException {

        LOGGER.setLevel(Level.FINE);
        // Connect to DB
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        LOGGER.log( Level.FINE, "Connected to database...");

    }

    /**
     *
     */
    @Override
    public void read() {
        OrderList orderList = OrderList.getInstance();
        LOGGER.log( Level.FINE, "Reading from the orders database... ");
        try {
            Statement st = conn.createStatement();

            ResultSet rs = null;
            String sql = "SELECT * FROM ORDERS";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("ID"));
                if (rs.getDate("COLLECTDATE") != null) order.setCollectDate(rs.getDate("COLLECTDATE").toLocalDate());
                order.setCollected(rs.getBoolean("ISCOLLECTED"));
                order.setFulfilled(rs.getBoolean("ISFULFILLED"));
                if (rs.getDate("FULFILLDATE") != null) order.setFullfillDate(rs.getDate("FULFILLDATE").toLocalDate());
                order.setPaid(rs.getBoolean("ISPAID"));
                if (rs.getDate("ORDERDATE") != null) order.setOrderDate(rs.getDate("ORDERDATE").toLocalDate());
                StaffList staffList = StaffList.getInstance();
                try {
                    StaffMember staff = staffList.get(rs.getInt("STAFFID"));
                    order.setStaffMember(staff);
                } catch (NonExistentStaffException ex) {
                    order.setStaffMember(null);
                }
                
                Customer customer = new Customer();
                customer.setAddress(rs.getString("CUSTOMERADDRESS"));
                customer.setEmail(rs.getString("CUSTOMEREMAIL"));
                customer.setName(rs.getString("CUSTOMERNAME"));
                customer.setPhone(rs.getString("CUSTOMERPHONE"));
                order.setCustomer(customer);
                readLineItems(order);
                orderList.add(order);

            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, "SQLException failed ! : " + ex);
        }
     
    }
    
    private void readLineItems(Order order) {
        LOGGER.log( Level.FINE, "Reading from the orderlineitems database... ");
        try {
            Statement st = conn.createStatement();

            ResultSet rs = null;
            String sql = "SELECT * FROM ORDERLINEITEMS WHERE ORDERID=" + order.getOrderId();
            rs = st.executeQuery(sql);

            ArrayList<OrderLineItem> items = new ArrayList<OrderLineItem>();
            while (rs.next()) {
                OrderLineItem oli = new OrderLineItem();
                oli.setId(rs.getInt("ID"));
                oli.setItemId(rs.getInt("ITEMID"));
                oli.setQuantity(rs.getInt("QUANTITY"));
                oli.setOrderId(rs.getInt("ORDERID"));
                items.add(oli);

            }
            order.setItems(items);
            rs.close();
            st.close();

        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, "SQLException failed ! : " + ex);
        }
    }

    /**
     *
     */
    @Override
    public void write() {
        Statement st;
        try {
            st = conn.createStatement();
            // delete contents of ORDER table
            String sql = "DELETE FROM ORDERS";
            st.executeUpdate(sql);
            // DELETE CONTENTS OF ORDERLINEITEMS table
            st = conn.createStatement();
            sql = "DELETE FROM ORDERLINEITEMS";
            st.executeUpdate(sql);

            st.close();
        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, "SQLException error ");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Order> orders = OrderList.getInstance().get();
        for (Order order : orders) {
            try {
                st = conn.createStatement();
                String sql = "INSERT INTO ORDERS VALUES ("
                        + order.getOrderId() + ",";
                if (order.getCollectDate() != null) {
                    sql = sql + " '" + formatter.format(order.getCollectDate()) + "' ,";
                } else {
                    sql = sql + "null" + " ,";
                }
                sql = sql + order.isCollected() + " , "
                        + order.isFulfilled() + " ,";
                
                if (order.getFullfillDate() != null) {
                    sql = sql + " '" + formatter.format(order.getFullfillDate()) + "' ,";
                } else {
                    sql = sql + "null" + " ,";
                }
                
                sql = sql + order.isPaid() + " , ";
                if (order.getOrderDate() != null) {
                    sql = sql + " '" + formatter.format(order.getOrderDate()) + "' ,";
                } else {
                    sql = sql + "null" + " ,";
                }
                if (order.getStaffMember() != null) {
                    sql = sql +
                    order.getStaffMember().getId() + " , ";
                } else {
                    sql = sql + "null" + " , '";
                }
                if (order.getCustomer() != null) {
                    sql = sql
                            + order.getCustomer().getAddress() + "' , '"
                            + order.getCustomer().getEmail() + "' , '"
                            + order.getCustomer().getName() + "' , '"
                            + order.getCustomer().getPhone() + "')";
                } else {
                    sql = sql
                            + "\"\"" + "' , '"
                            + "\"\"" + "' , '"
                            + "\"\"" + "' , '"
                            + "\"\"" + "')";
                }
                st.executeUpdate(sql);
                
                // update ORDERLINEITEMS table
                st = conn.createStatement();
                
                for (OrderLineItem oli : order.getItems()) {
                    LOGGER.log(Level.FINE,"SAving Items");
                    LOGGER.log(Level.FINE,"getId : "+ oli.getId() );
                    LOGGER.log(Level.FINE,"getItemId : " + oli.getItemId());
                    LOGGER.log(Level.FINE,"getQuantity : " + oli.getQuantity());
                    LOGGER.log(Level.FINE,"getOrderId : " + order.getOrderId());
                    sql = "INSERT INTO ORDERLINEITEMS VALUES (" +
                    oli.getId() + " , " +
                    oli.getItemId() + " , " +
                    oli.getQuantity() + " , " +
                    order.getOrderId() + " ) ";
                    
                            
                }
                st.executeUpdate(sql);
                st.close();
            } catch (SQLException ex) {
                LOGGER.log( Level.SEVERE, "SQLException error ");
                LOGGER.log( Level.SEVERE, ex.getMessage());
            }
        }

    }
}
