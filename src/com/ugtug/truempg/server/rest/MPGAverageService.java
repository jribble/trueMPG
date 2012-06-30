package com.ugtug.truempg.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.ugtug.truempg.server.model.MPGAverage;


@Path("/mpgas")
public class MPGAverageService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MPGAverage> getMPGAverages(
			@QueryParam("year") Long year,
			@QueryParam("make") String make,
			@QueryParam("model") String model) {
		Objectify ofy = ObjectifyService.begin();
		Query<MPGAverage> q = ofy.query(MPGAverage.class);
		if ( year != null ) q.filter("year", year);
		if ( make != null ) q.filter("make", make);
		if ( model != null ) q.filter("model", model);
		Iterable<MPGAverage> mpgIt = q.fetch();
		ArrayList<MPGAverage> mpgas = new ArrayList<MPGAverage> ( );
		for (MPGAverage mpga : mpgIt) {
			mpgas.add(mpga);
		}
		
		return mpgas;
		
	}
	
}
