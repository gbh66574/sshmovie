package com.entor.service;


import com.entor.entity.User;

public interface UserService extends BaseService<User> {
	public User login(String username,String password);

	
	
}
