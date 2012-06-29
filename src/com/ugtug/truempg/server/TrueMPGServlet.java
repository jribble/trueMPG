package com.ugtug.truempg.server;

import javax.servlet.ServletException;

import com.googlecode.objectify.ObjectifyService;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import com.ugtug.truempg.server.model.Fillup;
import com.ugtug.truempg.server.model.Vehicle;

public class TrueMPGServlet extends ServletContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		ObjectifyService.register(Vehicle.class);
		ObjectifyService.register(Fillup.class);
	}
	
	

}
