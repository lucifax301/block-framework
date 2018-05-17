package com.block.framework.metric;

public class Counter implements Counting, Metric {

	private com.codahale.metrics.Counter inner;
	
	public Counter(com.codahale.metrics.Counter inner){
		this.inner = inner;
	}
	
	@Override
	public long getCount() {
		return inner.getCount();
	}

	public void inc(){
		inner.inc();
	}
	
	public void inc(long n){
		inner.inc(n);
	}
	
	public void dec(){
		inner.dec();
	}
	
	public void dec(long n){
		inner.dec(n);
	}
}
