package com.asc.data;

import com.asc.data.StaffMember;
import java.util.*;

/**
 * @author rob johnson
 */
public class StaffList {

    private static StaffList instance = null;
    /**
     * Default constructor
     */
    protected StaffList() {
        
        staff = new ArrayList<StaffMember>();
    }

    public static StaffList getInstance() {
        if (instance == null) {
            instance = new StaffList();
        }
        return instance;
    }
    /**
     * 
     */
    private ArrayList<StaffMember> staff;

    /**
     * @param name
     */
    public void add(StaffMember staffMember) {
        staff.add(staffMember);
    }

    /**
     * @param id
     */
    public StaffMember get(int id) {
        // TODO implement here
        return null;
    }

}