package com.block.framework.metric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.BufferPoolMetricSet;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.google.common.base.Function;
import com.google.common.collect.MigrateMap;


public class MetricCenter {

	private static String domain = "com.block.metrics";

	private static ConcurrentHashMap<String,MetricRegistry> map = new ConcurrentHashMap<String,MetricRegistry>();
	
	private static MetricRegistry metricRegistry = getRegistry(domain);
	
	static{
		MBeanServer mbeanserver = MBeanServerFactory.createMBeanServer();
		metricRegistry.register(domain+".jvm.gc", new GarbageCollectorMetricSet());
		metricRegistry.register(domain+".jvm.memory", new MemoryUsageGaugeSet());
		metricRegistry.register(domain+".jvm.bufferpool", new BufferPoolMetricSet(mbeanserver));
		metricRegistry.register(domain+".jvm.thread", new ThreadStatesGaugeSet());
	}
	
	private static MetricRegistry getRegistry(String domain){
		MetricRegistry registry = pool.get(domain);
		return registry;
	}
	
	private static Map<String,MetricRegistry> pool=MigrateMap.makeComputingMap(new Function<String,MetricRegistry>(){

		@Override
		public MetricRegistry apply(String domain) {
			MetricRegistry registry = new MetricRegistry();
			MetricReporter.register(domain, registry);
			return registry;
		}
	});
	
	public static Meter meter(String name){
		return new Meter(metricRegistry.meter(name));
	}
	
	public static Counter counter(String name){
		return new Counter(metricRegistry.counter(name));
	}
}
