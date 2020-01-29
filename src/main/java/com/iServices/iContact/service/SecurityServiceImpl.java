package com.iServices.iContact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationManager authManager;
	@Override
	public boolean login(String name, String password) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(name);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
		
		authManager.authenticate(token);
		
		boolean  result = token.isAuthenticated();
		
		if(result)
		{
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		
		return result;
	}

}
