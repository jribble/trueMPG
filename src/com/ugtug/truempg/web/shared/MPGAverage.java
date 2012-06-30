package com.ugtug.truempg.web.shared;

public class MPGAverage {

	Integer year;
	String make;
	String model;
	Double mpgAverage;
	
	public MPGAverage () {}
	
	public MPGAverage (Integer year, String make, String model, Double mpgAverage ) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.mpgAverage = mpgAverage;
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

	public Double getMpgAverage() {
		return mpgAverage;
	}

	public void setMpgAverage(Double mpgAverage) {
		this.mpgAverage = mpgAverage;
	}
	
	
}
