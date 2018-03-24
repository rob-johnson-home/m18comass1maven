package com.asc.ui;

import com.asc.data.Order;
import java.util.*;

/**
 * @author rob johnson
 */
public class OrderPrintUI {

    /**
     * Default constructor
     */
    public OrderPrintUI() {
    }


    /**
     * @param order
     */
    public void print(PrintableOrder printable) {
        printable.print();;
    }

    

}