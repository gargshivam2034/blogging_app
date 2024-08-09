package com.BlogApp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BlogApp.Respository.UserRepo;
import com.BlogApp.entites.User;
import com.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "Email"+username, 0));
		return user;
	}
//	

}
