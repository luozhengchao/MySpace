package com.tedu.druid.service;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.druid.mapper.StudentBookMapper;
import com.tedu.druid.mapper.StudentSectionMapper;
import com.tedu.druid.pojo.StudentBook;
import com.tedu.druid.pojo.StudentSection;
import com.tedu.druid.vo.StudentBookList;

@Service
public class StudentBookServiceImpl implements StudentBookService {
	
	@Autowired
	private StudentBookMapper studentMapper;
	@Autowired
	private StudentSectionMapper sectionMapper;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	/**
	 * 1.获取目标url 爬取数据
	 * 2.解析返回的JSON数据
	 * 3.将数据进行封装
	 * 4.实现入库操作
	 */
	@Override
	public void insert(String url, int status) {
		try {
			String json = 
					Jsoup.connect(url).ignoreContentType(true).execute().body();
			//将JSOn数据转化为对象
			StudentBookList bookList = 
			objectMapper.readValue(json, StudentBookList.class);
			
			//获取需要的数据
			List<StudentBook> lists = bookList.getList();
			
			//将课程信息循环入库
			for (StudentBook studentBook : lists) {
				
				//获取章节信息
				List<StudentSection> sectionlist = 
						studentBook.getSections();
				
				//判断集合是否为null
				if(sectionlist !=null){
					for (StudentSection studentSection : sectionlist) {
						//需要定义外键关联  添加课程号
						studentSection.setBookId(studentBook.getId());
						sectionMapper.insert(studentSection);
					}
				}
				
				String imageUrl = 
				"http://www.tmooc.cn/web/library/tu_new/"+
				studentBook.getBookImg();
				
				studentBook.setBookImg(imageUrl);
				
				//实现数据的入库操作
				studentMapper.insert(studentBook);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
