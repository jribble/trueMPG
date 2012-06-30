package com.ugtug.truempg.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MPGAverage {

	@Id Long mpgAverageId;
	Integer year;
	String make;
	String model;
	Double totalMiles;
	Double totalQuantity;
	Double averageMpg;

	public MPGAverage () {}

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

	public Double getTotalMiles() {
		return totalMiles;
	}

	public void setTotalMiles(Double totalMiles) {
		this.totalMiles = totalMiles;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getAverageMpg() {
		return averageMpg;
	}

	public void setAverageMpg(Double averageMpg) {
		this.averageMpg = averageMpg;
	}
	
	
}
