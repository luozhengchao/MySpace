package com.jt.callback;

public class Worker {
	
	//员工有干活的方法
	public void work(Callback callback){
		
		System.out.println("员工开始干活");
		
		System.out.println("活干完了");
		
		callback.callback();
	}
}
