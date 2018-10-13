package org.ifool.leranjava.wordcount;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCount2 {

	public static void main(String[] args) throws InterruptedException {

		//假设有10000个transid
		final String[] trans = new String[10000];
		for(int i = 0; i < trans.length; i++) {
			trans[i] = "trans"+i;
		}
		
		//随机调用100万次交易，处理线程为1000个
		ExecutorService pool = Executors.newFixedThreadPool(500);
		Random rand = new Random();
		final Filter filter = new Filter1();
		
		//Thread.sleep(10000);
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000;i++) {
			final int transIndex = rand.nextInt(10000);
			
			pool.execute(new Runnable() {
				@Override
				public void run() {
					filter.doFilter(trans[transIndex]);
				}});
		}
		
		pool.shutdown();
		
		long end = System.currentTimeMillis();
		
		
		
		pool.awaitTermination(10000, TimeUnit.SECONDS);
		System.out.println("time:"+(end-start));
		filter.printTotalCount();
		

	}

}

interface Filter {
	public void doFilter(String url); //http://127.0.0.1/login.do， 为了简化，只传login
	public void printTotalCount();
}

class Filter1 implements Filter{
	public Filter1() {
		transCount = new ConcurrentHashMap<String,AtomicInteger>();
	}
	private Map<String,AtomicInteger> transCount;	
	
	public void doFilter(String url) { 
		
			String transId = url;
			AtomicInteger count = transCount.get(transId);
			if(count == null) {
				synchronized (this) {
					count = transCount.get(transId);
					if(count == null) {
						transCount.put(transId, new AtomicInteger(1));
					} else {
						count.incrementAndGet();
					}
				}
				
			} else {
				count.incrementAndGet();
				//int a = 0; a++;
				
				//transCount.put(transId,count.count++);
			}		
	}

	@Override
	public void printTotalCount() {
		int count = 0;
		Iterator<AtomicInteger> iter = transCount.values().iterator();
		while(iter.hasNext()) {
			count = count + iter.next().get();
		}
		System.out.println( "TotalCount:" + count);	
	}
	
}
class Filter2 implements Filter{
	public Filter2() {
		transCount = new ConcurrentHashMap<String,Integer>();
	}
	private Map<String,Integer> transCount;	
	
	public void doFilter(String url) { 

		
			
		
		String transId = url;
		Integer count = transCount.get(transId);
		if(count == null) {
			transCount.put(transId, 1);
		} else {
			transCount.put(transId,count+1);
		}	
		
	}

	@Override
	public void printTotalCount() {
		int count = 0;
		Iterator<Integer> iter = transCount.values().iterator();
		while(iter.hasNext()) {
			count = count + iter.next();
		}
		System.out.println( "TotalCount:" + count);	
	}
	
}

class Filter3 implements Filter{
	public Filter3() {
		transCount = new ConcurrentHashMap<String,Integer>();
	}
	private Map<String,Integer> transCount;	
	
	public void doFilter(String url) { 	
		String transId = url;
		Integer count = transCount.get(transId);
		if(count == null) {
			synchronized (transCount) {
				count = transCount.get(transId);
				if(count == null) {
					transCount.put(transId, 1);
				} else {
					
				}
			}
			
		} else {
			transCount.put(transId,count+1);
		}	
	}

	@Override
	public void printTotalCount() {
		int count = 0;
		Iterator<Integer> iter = transCount.values().iterator();
		while(iter.hasNext()) {
			count = count + iter.next();
		}
		System.out.println( "TotalCount:" + count);	
	}
	
}
class Filter6 implements Filter{
	public Filter6() {
		transCount = new ConcurrentHashMap<String,MutableInteger>();
	}
	private Map<String,MutableInteger> transCount;	
	
	public void doFilter(String url) { 	
		String transId = url;
		MutableInteger count = transCount.get(transId);
		if(count == null) {
			transCount.put(transId, new MutableInteger(1));
		} else {
			count.add(1);
		}			
	}

	@Override
	public void printTotalCount() {
		int count = 0;
		Iterator<MutableInteger> iter = transCount.values().iterator();
		while(iter.hasNext()) {
			count = count + iter.next().count;
		}
		System.out.println( "TotalCount:" + count);	
	}
	
}
class Filter4 implements Filter{
	public Filter4() {
		transCount = new ConcurrentHashMap<String,MutableInteger>();
	}
	private Map<String,MutableInteger> transCount;	
	
	public void doFilter(String url) { 	
		String transId = url;
		MutableInteger count = transCount.get(transId);
		if(count == null) {
			transCount.put(transId, new MutableInteger(1));
		} else {
			count.add(1);
		}			
	}

	@Override
	public void printTotalCount() {
		int count = 0;
		Iterator<MutableInteger> iter = transCount.values().iterator();
		while(iter.hasNext()) {
			count = count + iter.next().count;
		}
		System.out.println( "TotalCount:" + count);	
	}
	
}

class MutableInteger {
	public MutableInteger(int num) {
		this.count = num;
	}
	public int count = 0;
	public void add(int num) {
		count += num;
	}
}