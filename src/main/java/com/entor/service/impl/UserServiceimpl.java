package com.entor.service.impl;


import org.springframework.stereotype.Service;

import com.entor.dao.UserDao;
import com.entor.entity.User;
import com.entor.service.UserService;

@Service("userService")
public class UserServiceimpl extends BaseServiceimpl<User> implements UserService {
	private UserDao UserDao;
	@Override
	public User login(String username, String password) {
	
		return UserDao.login(username,password);
	}

	
}
