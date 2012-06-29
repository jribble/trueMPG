package com.ugtug.truempg.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.ugtug.truempg.server.model.Fillup;
import com.ugtug.truempg.server.model.Vehicle;
import com.ugtug.truempg.server.model.VehicleMPG;


@Path("/vehicles")
public class VehicleService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> getUserVehicles(@QueryParam("userId") String userId) {
		Objectify ofy = ObjectifyService.begin();
		Query<Vehicle> q = ofy.query(Vehicle.class);
		if ( userId != null ) q.filter("userId", userId);
		Iterable<Vehicle> vehicleIt = q.fetch();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle> ( );
		for (Vehicle v : vehicleIt) {
			vehicles.add(v);
		}
		
		return vehicles;
		
	}
	
	@GET
	@Path("/{vehicleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vehicle getVehicle(@PathParam("vehicleId") Long vehicleId) {
		Objectify ofy = ObjectifyService.begin();
		return ofy.get(Vehicle.class, vehicleId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Vehicle createVehicle(@FormParam("userId") String userId,
			@FormParam("year") Integer year,
			@FormParam("make") String make,
			@FormParam("model") String model,
			@FormParam("vin") String vin) {
		
		Vehicle vehicle = new Vehicle ( userId, year, make, model, vin );
		Objectify ofy = ObjectifyService.begin();
		Key<Vehicle> key = ofy.put(vehicle);
		return ofy.get(key);
		
	}
	
	@GET
	@Path("/{vehicleId}/mpg")
	@Produces(MediaType.APPLICATION_JSON)
	public VehicleMPG calculateVehicleMPG(@PathParam("vehicleId") Long vehicleId) {
		VehicleMPG vmpg = new VehicleMPG ( );
		
		// get all of the fillups, sorted descending by date
		List<Fillup> fillups = (new FillupService()).getVehicleFillups(vehicleId);
		
		// if there are less than 2, return null for mpgs because we don't know
		if ( fillups.size() >= 2 ) {
			// calc last mpgs between fillups putting onto list
			List<Double> mpgs = new ArrayList<Double> ( fillups.size() );
			for ( int i=0;i<fillups.size()-1;i++) {
				Fillup f2 = fillups.get(i);
				Fillup f1 = fillups.get(i+1);
				Long miles = f2.getMileage() - f1.getMileage();
				Double quantity = f2.getQuantity();
				Double mpg = miles/quantity;
				mpgs.add(mpg);
			}
			// the first one is the last mpg
			vmpg.setLastMpg(mpgs.get(0));
			
			// average all of them
			Double mpgTotal = 0.0;
			for ( Double mpg : mpgs ) {
				mpgTotal = mpgTotal + mpg;
			}
			vmpg.setVehicleAverageMpg(mpgTotal/mpgs.size());
		}
		// return the results
		return vmpg;
	}
	

}
