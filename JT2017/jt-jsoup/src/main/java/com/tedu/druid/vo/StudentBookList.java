package com.tedu.druid.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tedu.druid.pojo.StudentBook;

//说明 该对象是json和实体对象的桥梁

@JsonIgnoreProperties(ignoreUnknown=true)	//表示忽略未知属性
public class StudentBookList {
	
	//@JsonProperty(value="list")	
	//注解说明 表示为属性重命名
	private List<StudentBook>  list = 
			new ArrayList<StudentBook>();

	public List<StudentBook> getList() {
		return list;
	}

	public void setList(List<StudentBook> list) {
		this.list = list;
	}

	
	
	
}
