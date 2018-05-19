package com.jt.callback;

public class TestCallback {
	
	public static void main(String[] args) {
		
		Boss boss = new Boss();
		Worker worker = new Worker();
		
		worker.work(boss);
	}
}
