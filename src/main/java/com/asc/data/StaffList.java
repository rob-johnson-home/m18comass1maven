package com.asc.data;

import com.asc.data.StaffMember;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author rob johnson
 */
public class StaffList {
    private static final Logger LOGGER = Logger.getLogger( StaffList.class.getName() );

    private static StaffList instance = null;
    /**
     * Default constructor
     */
    protected StaffList() {
        
        staff = new ArrayList<StaffMember>();
    }

    /**
     *
     * @return
     */
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
     * @param staffMember
     */
    public void add(StaffMember staffMember) {
        staff.add(staffMember);
    }

    /**
     * @param id
     * @return StaffMember
     * @throws NonExistentStaffException is staffId not found
     */
    public StaffMember get(int id) throws NonExistentStaffException {
        if (staff.indexOf(id) == -1) {
            throw new NonExistentStaffException();
        }
        
        return staff.get(id);
    }

}