package com.ugtug.truempg.web.shared;

/**
 * Simple POJO for a vehicle.
 * 
 * <p>Title: Real MPG.</p>
 * <p>Description: Calculates your real MPG for your vehicles.</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @author Rene Dupre
 * @version 1.0
 */
public class Vehicle {

    private String vehicleID;               // unique database id for vehicle
    private String vehicleYear;             // year of vehicle
    private String make;                    // make
    private String model;
    private String vin;                     // vehicle identification number
    
    /**
     * Default constructor.
     */
    public Vehicle() {
        
    }

    /**
     * Constructor with data.
     */
    public Vehicle(String inID, String inYear, String inMake, String inModel, String inVIN){
        setVehicleID(inID);
        setVehicleYear(inYear);
        this.make = inMake;
        this.model = inModel;
        this.vin = inVIN;
    }

    /**
     * Return display name of vehicle.
     * @return String full name Year+model+make.
     */
    public String getVehicleName()
    {
       return vehicleYear + " " + make + " " + model; 
    }  
    
    /**
     * Gets the vehicleID.
     * @return String the vehicleID.
     */
    public String getVehicleID() {
        return vehicleID;
    }

    /**
     * Gets the vehicleYear.
     * @return String the vehicleYear.
     */
    public String getVehicleYear() {
        return vehicleYear;
    }

    /**
     * Gets the make.
     * @return String the make.
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the model.
     * @return String the model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the vin.
     * @return String the vin.
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets the vehicleID.
     * @param vehicleID the vehicleID to set.
     */
    public void setVehicleID(String vehicleID) {
        // if there is a decimal point in year, remove it
        int dot = vehicleID.indexOf('.');
        if (dot != -1)
        {
            vehicleID = vehicleID.substring(0, dot);
        }
        this.vehicleID = vehicleID;
    }

    /**
     * Sets the vehicleYear.
     * @param vehicleYear the vehicleYear to set.
     */
    public void setVehicleYear(String vehicleYear) {
        // if there is a decimal point in year, remove it
        int dot = vehicleYear.indexOf('.');
        if (dot != -1)
        {
            vehicleYear = vehicleYear.substring(0, dot);
        }
        this.vehicleYear = vehicleYear;
    }

    /**
     * Sets the make.
     * @param make the make to set.
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Sets the model.
     * @param model the model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the vin.
     * @param vin the vin to set.
     */
    public void setVin(String vin) {
        this.vin = vin;
    }
}
