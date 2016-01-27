package net.omusico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) {
		int num = 208;
		int counter = 0;
		List<DataObject> mArr = new ArrayList<>();
		Main.buildObject(mArr);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BlockingQueue<DataObject> queue = new ArrayBlockingQueue<DataObject> (10000);
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
	    List<Future<String>> list = new ArrayList<Future<String>>();
	    for (int i = 0; i < 4; i++) {
	      Callable<String> worker = new WriterThread(queue);
	      Future<String> submit = executor.submit(worker);
	      list.add(submit);
	    }
	    
	    Iterator<DataObject> cursor = mArr.iterator();
	    while(cursor.hasNext() && counter < num) {
	    	System.out.println("count = " + counter);
	    	DataObject data = (DataObject) cursor.next();
		    try {
				queue.offer(data, 10, TimeUnit.SECONDS);
				counter++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    executor.shutdown();

	}
	
	
	
	
	private static void buildObject(List<DataObject> mArr){
		
		for(int i = 0;i < 209;i++){
			DataObject data = new DataObject();
			data.put("key", i +"");
			data.put(i + "", i + "-data");
			mArr.add(data);
		}
		
	}

}
