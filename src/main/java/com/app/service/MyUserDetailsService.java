package com.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.otomoto.entity.Role;
import com.otomoto.entity.User;
import com.otomoto.repository.UserRepository;

public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		  User user = userRepository.findByLogin(login);
	        if (user == null) {
	            throw new UsernameNotFoundException(
	              "No user found with username: "+ login);
	        }
	        boolean enabled = true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
	        
	        
	        return  new org.springframework.security.core.userdetails.User
	          (user.getEmail(), 
	          user.getPassword().toLowerCase(), enabled, accountNonExpired, 
	          credentialsNonExpired, accountNonLocked,null );
	}

}
