package com.ugtug.truempg.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

	String userId;
	@Id Long vehicleId;
	Integer year;
	String make;
	String model;
	String vin;
	
	@SuppressWarnings("unused")
	private Vehicle () {}
	
	public Vehicle ( String userId, Integer year, String make, String model, String vin ) {
		this.userId = userId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.vin = vin;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	
}
