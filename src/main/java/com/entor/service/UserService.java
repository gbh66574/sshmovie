package com.entor.service;


import com.entor.entity.User;

public interface UserService extends BaseService<User> {
	 // ͨ���û���������˲��û���¼
    public User checkLogin(String username, String password);
	
	
}
