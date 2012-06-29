package com.ugtug.truempg.web.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Holds vehicles for a user.
 * 
 * <p>Title: Real MPG.</p>
 * <p>Description: Calculates your real MPG for your vehicles.</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @author Rene Dupre
 * @version 1.0
 */
public class VehicleList implements IsSerializable {
    
    private ArrayList<Vehicle> myList;               // vehicle list
    private String userID;                      // user of list
    
    public VehicleList()
    {
        myList = new ArrayList<Vehicle>();
    }
    
    /**
     * Adds a vehicle to the list.
     * @param Vehicle incoming vehicle.
     */
    public void addVehicle(Vehicle inCar)
    {
        myList.add(inCar);
    }
    
    /**
     * Returns number of vehicles in list.
     * @return # of Vehicles in list.
     */
    public int getVehicleCount()
    {
        return myList.size();
    }
    
    /**
     * Gets the myList.
     * @return List<Vehicle> the myList.
     */
    public List<Vehicle> getMyList() {
        return myList;
    }
    /**
     * Gets the userID.
     * @return String the userID.
     */
    public String getUserID() {
        return userID;
    }
    /**
     * Sets the myList.
     * @param myList the myList to set.
     */
    public void setMyList(ArrayList<Vehicle> myList) {
        this.myList = myList;
    }
    /**
     * Sets the userID.
     * @param userID the userID to set.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
