package com.org.service;

import com.org.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);
	
	public void removeSessionMessage();
}
