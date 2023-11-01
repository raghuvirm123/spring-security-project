package com.org.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.org.model.UserDtls;
import com.org.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		UserDtls user=userRepo.findByEmail(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		else 
		{
			return new CustomUser(user);
		}
		
	}

}
