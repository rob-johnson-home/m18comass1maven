package com.asc.persistence;

import com.asc.data.Assistant;
import com.asc.data.Item;
import com.asc.data.Manager;
import com.asc.data.StaffList;
import com.asc.data.StaffMember;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class StaffRepo implements Repo {
    private static final Logger LOGGER = Logger.getLogger( StaffRepo.class.getName() );

    Connection conn;
    /**
     * Default constructor
     *
     * @param dbUrl
     * @param dbPassword
     * @param dbUser
     * @throws SQLException if we cannot connect to the database
     */
    public StaffRepo( String dbUrl, String dbPassword, String dbUser) throws SQLException {

        // Connect to DB
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        LOGGER.log( Level.FINE, "Connected to database...");

    }

    /**
     * 
     */
    public void read() {
        StaffList staffList = StaffList.getInstance();
        LOGGER.log( Level.FINE, "staff  list = " + staffList);
        LOGGER.log( Level.FINE, "Reading from the staff database... ");

        try {
        Statement st = conn.createStatement();

        ResultSet rs = null;
        String sql = "SELECT * FROM STAFF";
        rs = st.executeQuery(sql);
        StaffMember staff = null;

        while (rs.next()) {
            if (rs.getBoolean("ISMANAGER")) {
                staff = new Manager();
                staff.setId(rs.getInt("ID"));
                staff.setName(rs.getString("NAME"));
            } else {
                staff = new Assistant();
                staff.setId(rs.getInt("ID"));
                staff.setName(rs.getString("NAME"));
            }
            staffList.add(staff);
        }

        
        rs.close();
        st.close();
        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, "SQLException failed ! : " + ex);
        }
        LOGGER.log( Level.FINE, "staff..." + staffList);
    }

    /**
     * 
     */
    public void write() {
        // TODO implement here
    }

}