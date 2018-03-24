package com.asc.persistence;

import com.asc.data.Item;
import com.asc.data.StockList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class StockRepo implements Repo {
    private static final Logger LOGGER = Logger.getLogger( StockRepo.class.getName() );

    Connection conn;

    /**
     * Default constructor
     *
     * @param dbUrl
     * @param dbPassword
     * @param dbUser
     * @throws SQLException if we cannot connect to the database
     */
    public StockRepo(String dbUrl, String dbPassword, String dbUser) throws SQLException {

        // Connect to DB
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        LOGGER.log( Level.FINE, "Connected to database...");

    }

    /**
     *
     */
    public void read() {
        StockList stockList = StockList.getInstance();

        LOGGER.log( Level.FINE, "Reading from the stock database... ");

        try {
            Statement st = conn.createStatement();

            ResultSet rs = null;
            String sql = "SELECT * FROM STOCK";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("ID"));

                item.setPrice(rs.getDouble("PRICE"));

                item.setName(rs.getString("NAME"));

                item.setDescription(rs.getString("DESCRIPTION"));

                stockList.add(item);
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            LOGGER.log( Level.SEVERE, "SQLException failed ! : " + ex);
        }
        LOGGER.log( Level.FINE, "staff..." + stockList);
    }

    /**
     *
     */
    public void write() {
        throw new UnsupportedOperationException();
    }

}
