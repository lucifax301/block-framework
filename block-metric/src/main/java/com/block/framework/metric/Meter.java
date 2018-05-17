package com.block.framework.metric;

public class Meter implements Metered {

	private com.codahale.metrics.Meter inner;
	
	public Meter(com.codahale.metrics.Meter inner){
		this.inner = inner;
	}
	
	public void mark(){
		inner.mark();
	}
	
	public void mark(long n){
		inner.mark(n);
	}
	
	@Override
	public long getCount() {
		return inner.getCount();
	}

	@Override
	public double getFifteenMinuteRate() {
		return inner.getFifteenMinuteRate();
	}

	@Override
	public double getFiveMinuteRate() {
		return inner.getFiveMinuteRate();
	}

	@Override
	public double getMeanRate() {
		return inner.getMeanRate();
	}

	@Override
	public double getOneMinuteRate() {
		return inner.getOneMinuteRate();
	}

}
