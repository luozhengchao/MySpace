package com.jt.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedu.druid.service.StudentBookService;

public class TestJSOUP {
	
	/**
	 * 获取tmooc中的标题标签
	 * 1.定位目标网站
	 * 2.爬取数据
	 * 3.数据解析
	 * 4.实现数据落地
	 * @throws IOException 
	 */
	
	@Test
	public void test01() throws IOException{
		
		String url = "http://www.tmooc.cn/web/index_new.html?tedu";
		
		//获取页面对象
		Document document = Jsoup.connect(url).get();
		
		//获取页面元素
		Element element = document.select(".b_search").get(0);
		
		Element element2 = element.select("div h2").get(0);
		
		System.out.println(element2.text());	
	}
	
	//能够获取静态页面数据
	//获取页面的人数
	@Test
	public void test02() throws IOException{
		String url = "http://www.tmooc.cn/web/index_new.html?tedu";
		//获取页面对象
		Document document = Jsoup.connect(url).get();
		String num  = document.select("#user_num").get(0).text();
		
		System.out.println("获取页面的num:"+num);
	}
	
	//获取页面的动态信息
	@Test
	public void test03() throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String url = "http://www.tmooc.cn/commonData/getCommonNum";
		
		//发出请求
		String json = 
		Jsoup.connect(url).ignoreContentType(true).execute().body();
		
		System.out.println("获取服务器的JSON数据"+json);
		
		//{"result":"1","obj":{"userNum":415930,"bookNum":987,"dirNum":17}}
		
		//通过objectMapper解析json串   JSONNode表示节点信息(不是属性)
		JsonNode jsonNode = objectMapper.readTree(json);
		
		//通过jsonNode获取json数据
		String num = jsonNode.get("obj").get("userNum").asText();
		
		System.out.println("获取学生的返回值:"+num);
	}
	
	@Test
	public void getHotBook() throws IOException{
		String url = "http://www.tmooc.cn/book_test/getHotBook";
		int status = 1;	//表示热门课程
		
		//通过spring容器获取Service对象.调用业务方法
		ApplicationContext context = 
		new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		
		//获取业务层service
		StudentBookService bookService = 
		context.getBean(StudentBookService.class);
		
		//调用业务方法 实现课程入库操作
		bookService.insert(url,status);
		
		System.out.println("爬虫数据入库完成");
	}
	
	
	//获取最新课程
	@Test
	public void getNewBook(){
		String url = "http://www.tmooc.cn/book/getNewBook";
		int status = 2;	//表示最新课程
		
		ApplicationContext context = 
		new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
				
		//获取业务层service
		StudentBookService bookService = 
				context.getBean(StudentBookService.class);
				
		//调用业务方法 实现课程入库操作
		bookService.insert(url,status);
		System.out.println("最新课程新增完成!!!!!!");
	}
	
	
	
	
	
	
	
	
	
}


