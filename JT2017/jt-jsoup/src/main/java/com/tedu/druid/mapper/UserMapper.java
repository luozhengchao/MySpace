package com.tedu.druid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tedu.druid.pojo.User;

public interface UserMapper {
	//注解形式
	@Select("select * from user")
	List<User> findAll();
}
