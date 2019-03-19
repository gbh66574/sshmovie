package com.entor.service;


import com.entor.entity.User;

public interface UserService extends BaseService<User> {
	 // 通过用户名及密码核查用户登录
    public User checkLogin(String username, String password);
	
	
}
