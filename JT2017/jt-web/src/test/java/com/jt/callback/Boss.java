package com.jt.callback;


//定义一个老板对象
public class Boss implements Callback{

	@Override
	public void callback() {
		
		System.out.println("请员工k歌");
	}
}
