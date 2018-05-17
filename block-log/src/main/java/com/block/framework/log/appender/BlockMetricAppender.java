package com.block.framework.log.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

import com.block.framework.metric.Meter;
import com.block.framework.metric.MetricCenter;

public class BlockMetricAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	private static String prefix="com.block.metrics.log.";
	
	private Meter all;
	private Meter debug;
	private Meter info;
	private Meter warn;
	private Meter error;
	
	@Override
	public void start() {
		all=MetricCenter.meter(prefix+"all");
		debug=MetricCenter.meter(prefix+"debug");
		info=MetricCenter.meter(prefix+"info");
		warn=MetricCenter.meter(prefix+"warn");
		error=MetricCenter.meter(prefix+"error");
		super.start();
	}
	
	@Override
	protected void append(ILoggingEvent eventObject) {
		all.mark();
		if(eventObject.getLevel().toInt()==Level.DEBUG.toInt()){
			debug.mark();
		}else if(eventObject.getLevel().toInt()==Level.INFO.toInt()){
			info.mark();
		}else if(eventObject.getLevel().toInt()==Level.WARN.toInt()){
			warn.mark();
		}else if(eventObject.getLevel().toInt()==Level.ERROR.toInt()){
			error.mark();
		}
	}

}
