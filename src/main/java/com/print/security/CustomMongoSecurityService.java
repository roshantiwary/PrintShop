package com.print.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.print.repo.PrintShopDao;

public class CustomMongoSecurityService implements UserDetailsService{

	@Autowired
	PrintShopDao printShopDao;
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
		SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		
		return null;
	}

	
	
}
