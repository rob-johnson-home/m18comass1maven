package com.asc.ui;

import com.asc.control.StaffOrderController;
import com.asc.ui.Menu;
import java.util.*;

/**
 * @author rob johnson
 */
public class StaffOrderControlUI {

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
        menuChoices.put("E","Print Order");
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
                case "A":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    staffOrderController.checkOrder(id);
                    break;
                    }
                case "B":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    staffOrderController.viewOrder(id);
                    break;
                    }
                case "C":
                    staffOrderController.viewOrders();
                    break;
                case "D":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    staffOrderController.fulfillOrder(id);
                    staffOrderController.printOrderLabel(id);
                    break;
                    }
                case "E":
                    {
                    int id = ui.demandInt("Please enter the order id");
                    staffOrderController.printOrder(id);
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