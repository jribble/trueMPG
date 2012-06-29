package com.ugtug.truempg.server.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MPGRecord {

	@Id Long mpgRecordId;
	Long vehicleId;
	Integer year;
	String make;
	String model;
	Date startDate;
	Date endDate;
	Long startMileage;
	Long endMileage;
	Double quantity;
	Double mpg;
	Double startLatitude;
	Double endLatitude;
	Double startLongitude;
	Double endLongitude;
	
	@SuppressWarnings("unused")
	private MPGRecord () {}
	
	public MPGRecord ( Vehicle vehicle, Fillup startFillup, Fillup endFillup ) {
		this.vehicleId = vehicle.getVehicleId();
		this.year = vehicle.getYear();
		this.make = vehicle.getMake();
		this.model = vehicle.getModel();
		this.startDate = startFillup.getDate();
		this.endDate = endFillup.getDate();
		this.startMileage = startFillup.getMileage();
		this.endMileage = endFillup.getMileage();
		this.quantity = endFillup.getQuantity();
		if ( this.startMileage != null && this.endMileage != null && this.quantity != null ) {
			this.mpg = (this.endMileage-this.startMileage)/this.quantity;
		}
		this.startLatitude = startFillup.getLatitude();
		this.endLatitude = endFillup.getLatitude();
		this.startLongitude = startFillup.getLongitude();
		this.endLongitude = endFillup.getLongitude();
	}

	public Long getMpgRecordId() {
		return mpgRecordId;
	}

	public void setMpgRecordId(Long mpgRecordId) {
		this.mpgRecordId = mpgRecordId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getStartMileage() {
		return startMileage;
	}

	public void setStartMileage(Long startMileage) {
		this.startMileage = startMileage;
	}

	public Long getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(Long endMileage) {
		this.endMileage = endMileage;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getMpg() {
		return mpg;
	}

	public void setMpg(Double mpg) {
		this.mpg = mpg;
	}

	public Double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Double getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(Double endLatitude) {
		this.endLatitude = endLatitude;
	}

	public Double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}

	public Double getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(Double endLongitude) {
		this.endLongitude = endLongitude;
	}
	
	
}
