package com.asc.persistence;

import com.asc.data.StaffMember;
import com.asc.data.Order;
import com.asc.data.Customer;
import com.asc.data.Item;
import com.asc.data.OrderList;
import com.asc.data.OrderList;
import com.asc.data.StaffList;
import com.asc.data.StockList;

import java.sql.*;
import java.text.DateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author rob johnson
 */
public class OrderRepo implements Repo {

    Connection conn;

    /**
     * Default constructor
     *
     * @throws SQLException if we cannot connect to the database
     */
    public OrderRepo(String dbUrl, String dbPassword, String dbUser) throws SQLException {

        // Connect to DB
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        System.out.println("Connected to database...");

    }

    /**
     *
     */
    @Override
    public void read() {
        OrderList orderList = OrderList.getInstance();
        System.out.println("Reading from the orders database... ");
        try {
            Statement st = conn.createStatement();

            ResultSet rs = null;
            String sql = "SELECT * FROM ORDERS";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("ID"));
                System.out.println("order repo 1");
                if (rs.getDate("COLLECTDATE") != null) order.setCollectDate(rs.getDate("COLLECTDATE").toLocalDate());
                order.setCollected(rs.getBoolean("ISCOLLECTED"));
                order.setFulfilled(rs.getBoolean("ISFULFILLED"));
                System.out.println("order repo 2");
                if (rs.getDate("FULFILLDATE") != null) order.setFullfillDate(rs.getDate("FULFILLDATE").toLocalDate());
                order.setPaid(rs.getBoolean("ISPAID"));
                System.out.println("order repo 3 + " + rs.getDate("ORDERDATE").toLocalDate());
                if (rs.getDate("ORDERDATE") != null) order.setOrderDate(rs.getDate("ORDERDATE").toLocalDate());
                System.out.println("order repo 4");
                StaffList staffList = StaffList.getInstance();
                StaffMember staff = staffList.get(rs.getInt("STAFFID"));
System.out.println("order repo 5");
                order.setStaffMember(staff);
System.out.println("order repo 6");
                Customer customer = new Customer();
                customer.setAddress(rs.getString("CUSTOMERADDRESS"));
                customer.setEmail(rs.getString("CUSTOMEREMAIL"));
                customer.setName(rs.getString("CUSTOMERNAME"));
                customer.setPhone(rs.getString("CUSTOMERPHONE"));
                order.setCustomer(customer);
System.out.println("order repo 7");
                orderList.add(order);

            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            System.out.println("SQLException failed ! : " + ex);
        }
        System.out.println("orders..." + orderList);
    }

    /**
     *
     */
    @Override
    public void write() {
        Statement st;
        System.out.println("1");
        try {
            st = conn.createStatement();
            String sql = "DELETE FROM ORDERS";
            st.executeUpdate(sql);

            st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException error ");
        }
        System.out.println("2");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Order> orders = OrderList.getInstance().get();
        System.out.println("3");
        for (Order order : orders) {
            System.out.println("4");
            try {
                st = conn.createStatement();
                System.out.println("5");
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
                System.out.println("5.5");
                System.out.println("SQL  =  " + sql);
                st.executeUpdate(sql);
                System.out.println("6");
                st.close();
            } catch (SQLException ex) {
                System.out.println("SQLException error ");
                System.out.println(ex.getMessage());
            }
        }

    }
}
