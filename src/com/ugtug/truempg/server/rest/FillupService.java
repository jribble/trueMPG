package com.ugtug.truempg.server.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


@Path("/fillups")
public class FillupService {
	private static final SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd" );
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fillup> getVehicleFillups(@QueryParam("vehicleId") Long vehicleId) {
		Objectify ofy = ObjectifyService.begin();
		Query<Fillup> q = ofy.query(Fillup.class);
		if ( vehicleId != null ) q.filter("vehicleId", vehicleId);
		q.order("-date");
		Iterable<Fillup> FillupIt = q.fetch();
		ArrayList<Fillup> Fillups = new ArrayList<Fillup> ( );
		for (Fillup v : FillupIt) {
			Fillups.add(v);
		}
		
		return Fillups;
		
	}
	
	@GET
	@Path("/{fillupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fillup getFillup(@PathParam("fillupId") Long fillupId) {
		Objectify ofy = ObjectifyService.begin();
		return ofy.get(Fillup.class, fillupId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Fillup createFillup(@FormParam("vehicleId") Long vehicleId,
			@FormParam("date") String dateAsString,
			@FormParam("quantity") Double quantity,
			@FormParam("mileage") Long mileage,
			@FormParam("latitude") Double latitude,
			@FormParam("longitude") Double longitude) {

	    Date date = new Date ();
		try {
			if ( dateAsString != null ) {
				date = sdf.parse(dateAsString);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Fillup fillup = new Fillup ( vehicleId, date, quantity, mileage, latitude, longitude );
		Objectify ofy = ObjectifyService.begin();
		Key<Fillup> key = ofy.put(fillup);
		return ofy.get(key);
		
	}

}
