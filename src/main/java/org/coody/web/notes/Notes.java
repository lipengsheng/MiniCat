package org.coody.web.notes;

import java.util.concurrent.atomic.AtomicLong;

import org.coody.framework.minicat.threadpool.MiniCatThreadPool;


public class Notes {

	public static AtomicLong input=new AtomicLong(0l);
	
	public static AtomicLong output=new AtomicLong(0l);
	
	
	
	static{
		MiniCatThreadPool.MINICAT_POOL.execute(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("input:"+input+",output:"+output);
					input.set(0);
					output.set(0);
				}
			}
		});

	}

}
