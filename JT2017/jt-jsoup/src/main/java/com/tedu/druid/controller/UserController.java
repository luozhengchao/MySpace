package com.tedu.druid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.druid.pojo.User;
import com.tedu.druid.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findAll")
	@ResponseBody	//直接返回JSON数据
	public List<User> findAll(){
		
		return userService.findAll();
	}
	
	
	
	
	
	
	
}
