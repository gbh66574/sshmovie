package com.entor.dao.impl;


import org.springframework.stereotype.Repository;

import com.entor.dao.UserDao;
import com.entor.entity.User;
@Repository("userDao")
public class UserDaoimpl  extends BaseDaoimpl<User> implements UserDao{

	
}
