package net.omusico;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class WriterThread implements Callable<String> {
	
	BlockingQueue<DataObject> myQueue;
	
	public WriterThread(BlockingQueue<DataObject> bq) {
		myQueue = bq;
	}

	@Override
	public String call() throws Exception {
		while (true){
			Thread.sleep(1000);
			DataObject data = this.myQueue.poll(30, TimeUnit.SECONDS);
			if (data == null) {
				String s1 = "1";
				
				String s2 = "2";
				return s1 +"\n" + s2;		
			}
			
			System.out.println(data.get("key"));
			
			
			
		}
		
	}


}
