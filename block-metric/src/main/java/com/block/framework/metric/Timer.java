package com.block.framework.metric;

public class Timer implements Metered {

	private com.codahale.metrics.Timer inner;
	
	public Timer(com.codahale.metrics.Timer inner){
		this.inner=inner;
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
