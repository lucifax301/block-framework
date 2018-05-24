package com.block.framework.core.trace.reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import com.block.framework.core.trace.InnerTrace;
import com.block.framework.core.trace.TraceQueueFullException;
import com.block.framework.core.trace.TraceReporter;
import com.block.framework.core.trace.TraceSender;

public abstract class AsyncTraceReporter implements TraceReporter {

	private TraceSender sender = getSender();
	
	@Override
	public boolean report(InnerTrace innerTrace) {
		boolean flag = enterQueue(innerTrace);
		if(!flag){
			throw new TraceQueueFullException("trace 发送等待队列已满");
		}
		return true;
	}

	public abstract TraceSender getSender();
	
	private static int queueSize = 1024;
	
	private static int batchSize = 100;
	//unit ms
	private static int waitTime = 1000;
	
	static{
		String size = System.getProperty("TRACE_SENDER_QUEUE_SIZE", "1024");
		try{
			queueSize = Integer.parseInt(size);
		}catch(Exception ex){}
	}
	
	private LinkedBlockingQueue<InnerTrace> queue = new LinkedBlockingQueue(1024);
	
	private boolean enterQueue(InnerTrace trace){
		return queue.offer(trace);
	}
	
	@PostConstruct  
	public void init(){
		TraceSenderConsumer consumer = new TraceSenderConsumer();
		consumer.setDaemon(true);
		consumer.start();
	}
	
	class TraceSenderConsumer extends Thread{
		
		@Override
		public void run(){
			List<InnerTrace> list = new ArrayList<InnerTrace>();
			long calTime = 0;
			long startTime = 0;
			while(true){
				try{
					//InnerTrace trace = queue.take();
					startTime=System.currentTimeMillis();
					InnerTrace trace = queue.poll();
					if(trace!=null)list.add(trace);
					if(list.size()>=batchSize||(calTime+=System.currentTimeMillis()-startTime)>=waitTime){
						calTime=0;
						if(list.size()>0){
							sender.send(list);
							list.clear();
						}
					}
				}catch(Throwable e){
					e.printStackTrace();
				}
			}
		}
	}
}
