package com.asc.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author rob johnson
 */
public class Menu {

    /**
     * Default constructor
     *
     * @param titleIn menu title.
     *
     * @param menuIn menu contents
     *
     */
    public Menu(String titleIn, HashMap<String,String> menuIn) {

        title = titleIn;
        menu = menuIn;

        keyboard = new  BufferedReader(new InputStreamReader(System.in));

    }

    /**
     * keyboard input reader
     */
    private BufferedReader keyboard;

    /**
     * Menu title
     */
    public String title;

    /**
     * Menu contents
     */
    public HashMap<String,String> menu;

    /**
     * display the menu and accept a choice from the keyboard
     *
     * @return the menu choice
     */
    public String MenuChoice() {

        System.out.println(title);
        Iterator i = menu.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry)i.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        boolean invalidChoice = true;
        while (invalidChoice) {
            String menuChoice = "";
            try {
                String input = keyboard.readLine();
                if (input.length()>0){
                    menuChoice = input.toUpperCase();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (menu.containsKey(menuChoice)) {
                return menuChoice;
            }
        }

        return null;
    }
    
    /**
     * ask the user for input (int)
     * @param prompt
     * @return integer
     */
    public int demandInt(String prompt) {
        System.out.println(prompt + " : ");
        boolean invalidEntry = true;
        int i = 0;
        while (invalidEntry) {
            String in = "";
            try {
                String input = keyboard.readLine();
                if (input.length()>0){
                    i = Integer.parseUnsignedInt(input);
                    invalidEntry = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("only a number please!");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}