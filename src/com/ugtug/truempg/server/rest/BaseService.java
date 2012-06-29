package com.ugtug.truempg.server.rest;

import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.oauth.OAuthService;
import com.google.appengine.api.oauth.OAuthServiceFactory;
import com.google.appengine.api.users.User;

public class BaseService {

	User getUser ( ) {
		User user = null;
		try {
			OAuthService oauth = OAuthServiceFactory.getOAuthService();
			user = oauth.getCurrentUser();
		}
		catch ( OAuthRequestException e ) {
			// TODO: handle this
		}
		return user;
	}
}
