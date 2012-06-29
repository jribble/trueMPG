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
import com.ugtug.truempg.server.model.MPGRecord;


@Path("/mpgs")
public class MPGRecordService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MPGRecord> getMPGRecords(
			@QueryParam("vehicleId") Long vehicleId,
			@QueryParam("year") Long year,
			@QueryParam("make") String make,
			@QueryParam("model") String model) {
		Objectify ofy = ObjectifyService.begin();
		Query<MPGRecord> q = ofy.query(MPGRecord.class);
		if ( vehicleId != null ) q.filter("vehicleId", vehicleId);
		if ( year != null ) q.filter("year", year);
		if ( make != null ) q.filter("make", make);
		if ( model != null ) q.filter("model", model);
		Iterable<MPGRecord> mpgIt = q.fetch();
		ArrayList<MPGRecord> mpgrs = new ArrayList<MPGRecord> ( );
		for (MPGRecord mpgr : mpgIt) {
			mpgrs.add(mpgr);
		}
		
		return mpgrs;
		
	}
	
}
