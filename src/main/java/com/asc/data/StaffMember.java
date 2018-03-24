package com.asc.data;

import java.util.*;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class StaffMember {
    private static final Logger LOGGER = Logger.getLogger( StaffMember.class.getName() );

    /**
     *
     */
    public int id;

    /**
     * Default constructor
     */
    public StaffMember() {
    }

    /**
     * 
     */
    private String name;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }



}