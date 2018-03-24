package com.asc.ui;

import com.asc.data.NonExistentOrderException;
import com.asc.control.StaffOrderController;
import com.asc.data.Item;
import com.asc.data.NonExistentStaffException;
import com.asc.ui.Menu;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class StaffOrderControlUI {
    private static final Logger LOGGER = Logger.getLogger( StaffOrderControlUI.class.getName() );

    private final Menu ui;
    private HashMap<String, String> menuChoices;
    private String title;
    private final StaffOrderController staffOrderController;

    /**
     * Default constructor, create an order controller process, populate the menu
     *
     *
     */
    public StaffOrderControlUI() {

        staffOrderController = new StaffOrderController();
        title = "Staff Order Control Menu";
        menuChoices = new HashMap<String,String>();
        menuChoices.put("A","Check Order");
        menuChoices.put("B","View Order");
        menuChoices.put("C","View All Open Orders");
        menuChoices.put("D","Fulfill Order");
        menuChoices.put("E","Print Order Pick List");
        menuChoices.put("F","Print Order Label");
        menuChoices.put("Z","Quit");

        ui = new Menu(title,menuChoices);
    }


    /**
     * Execute a task selected from the menu
     */
    public void executeTask() {
        Boolean done = false;
        while (!done) {
            String choice = ui.MenuChoice();

            switch (choice) {
                case "Z":
                    done = true;
                    break;
//                case "A":
//                    {
//                    int id = ui.demandInt("Please enter the order id");
//                    staffOrderController.checkOrder(id);
//                    break;
//                    }
                case "B":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    try {
                        staffOrderController.viewOrder(id);
                    } catch (NonExistentOrderException ex) {
                        System.out.println( "That order ID does not exist");
                    }
                    break;
                    }
                case "C":
                    staffOrderController.viewOrders();
                    break;
                case "D":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    int staffId = ui.demandInt("Please choose the staff member");
                    try {
                        staffOrderController.fulfillOrder(id,staffId);
                        staffOrderController.getOrderLabel(id);
                    } catch (NonExistentOrderException ex) {
                        System.out.println( "That order ID does not exist");
                    } catch (NonExistentStaffException ex) {
                        System.out.println( "That staff ID does not exist");
                    }
                    break;
                    }
                case "E":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    try {
                        OrderPickList opl = staffOrderController.getOrderPickList(id);
                        OrderPrintUI printer = new OrderPrintUI();
                        printer.print(opl);
                    } catch (NonExistentOrderException ex) {
                        System.out.println( "That order does not exist");
                    }
                    break;
                    }
                case "F":
                {
                    int id = ui.demandInt("Please enter the order id");
                    try {
                        OrderLabel ol = staffOrderController.getOrderLabel(id);
                        OrderPrintUI printer = new OrderPrintUI();
                        printer.print(ol);
                    } catch (NonExistentOrderException ex) {
                        System.out.println( "That order does not exist");
                    }
                    break;
                }
            }
        }
    }

    /**
     * 
     */
    public void input() {
        // TODO implement here
    }

    /**
     * 
     */
    public void displayResults() {
        // TODO implement here
    }

}