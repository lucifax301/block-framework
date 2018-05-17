package com.block.framework.metric;

import java.util.ArrayList;
import java.util.List;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

public class MetricReporter {

	private static List<MetricRegistry> registrys = new ArrayList<MetricRegistry>();
	
	public static void register(String domain,MetricRegistry registry){
		registrys.add(registry);
		reportJMX(domain,registry);
	}
	
	private static void reportJMX(String domain,MetricRegistry registry){
		JmxReporter reporter = JmxReporter.forRegistry(registry).inDomain(domain).build();
		reporter.start();
	}
}
