package com.ugtug.truempg.server.model;

public class VehicleMPG {

	Double lastMpg;
	Double vehicleAverageMpg;
	
	public VehicleMPG ( ) {
		lastMpg = null;
		vehicleAverageMpg = null;
	}

	public Double getLastMpg() {
		return lastMpg;
	}

	public void setLastMpg(Double lastMpg) {
		this.lastMpg = lastMpg;
	}

	public Double getVehicleAverageMpg() {
		return vehicleAverageMpg;
	}

	public void setVehicleAverageMpg(Double vehicleAverageMpg) {
		this.vehicleAverageMpg = vehicleAverageMpg;
	}
	
	
}
