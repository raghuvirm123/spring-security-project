package com.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.org.model.UserDtls;
import com.org.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
//	for password encription
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDtls createUser(UserDtls user) {
		
		String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		return userRepo.save(user);
	}

//	check wether email alreday present or not in db
	@Override
	public boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}

//	To remove message stored in session
	@Override
	public void removeSessionMessage() {
		
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

	

}
