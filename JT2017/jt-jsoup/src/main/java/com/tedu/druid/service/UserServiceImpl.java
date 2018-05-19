package com.tedu.druid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.druid.mapper.UserMapper;
import com.tedu.druid.pojo.User;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

}
