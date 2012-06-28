package com.ugtug.hackathon.truempg.rest;

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
import com.ugtug.hackathon.truempg.model.Vehicle;


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

}
