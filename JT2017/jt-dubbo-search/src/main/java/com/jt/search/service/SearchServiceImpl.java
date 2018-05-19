package com.jt.search.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.service.DubboSearchService;

public class SearchServiceImpl implements DubboSearchService{
	
	@Autowired
	private HttpSolrServer httpSolrServer;
	@Override
	public List<Item> findItemListBykey(String keyword) {
		//利用全完检索的方式查询
		
		//准备全完检索的对象
		SolrQuery query = new SolrQuery(keyword);
		
		//可是实现分页
		query.setStart(0);
		query.setRows(20);	//每次20行
		
		try {
			//获取一个返回值对象
			QueryResponse response = httpSolrServer.query(query);
			
			//获取返回值结果
			List<Item> itemList = response.getBeans(Item.class);
			
			return itemList;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
