package com.entor.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entor.dao.UserDao;
import com.entor.entity.User;
@Repository("userDao")
public class UserDaoimpl  extends BaseDaoimpl<User> implements UserDao{
	@Override
	public User login(String username, String password) {
	Map<String, String>map=new HashMap<>();
	map.put("username", username);
	map.put("password", password);
	return getSqlSession().selectOne("User.login",map);
	}
}
