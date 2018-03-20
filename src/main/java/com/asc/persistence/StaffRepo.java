package com.asc.persistence;

import com.asc.data.Assistant;
import com.asc.data.Manager;
import com.asc.data.StaffList;
import com.asc.data.StaffMember;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author rob johnson
 */
public class StaffRepo implements Repo {

    Connection conn;
    /**
     * Default constructor
     *
     * @throws SQLException if we cannot connect to the database
     */
    public StaffRepo( String dbUrl, String dbPassword, String dbUser) throws SQLException {

        // Connect to DB
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        System.out.println("Connected to database...");

    }

    /**
     * 
     */
    public void read() {
        StaffList staffList = StaffList.getInstance();
        System.out.println("staff  list = " + staffList);
        System.out.println("Reading from the staff database... ");

        try {
        Statement st = conn.createStatement();

        ResultSet rs = null;
        String sql = "SELECT * FROM STAFF";
        rs = st.executeQuery(sql);
System.out.println("1");
        StaffMember staff = null;

        while (rs.next()) {
            System.out.println("2");
            if (rs.getBoolean("ISMANAGER")) {
                staff = new Manager();
                staff.setId(rs.getInt("ID"));
                staff.setName(rs.getString("NAME"));
            } else {
                staff = new Assistant();
                staff.setId(rs.getInt("ID"));
                staff.setName(rs.getString("NAME"));
            }
            System.out.println("Adding to stafflist");
            staffList.add(staff);
            System.out.println("Added to stafflist");
        }

        
System.out.println("3");
        rs.close();
System.out.println("4");
        st.close();
        } catch (SQLException ex) {
            System.out.println("SQLException failed ! : " + ex);
        }
System.out.println("5");
        System.out.println("staff..." + staffList);
    }

    /**
     * 
     */
    public void write() {
        // TODO implement here
    }

}