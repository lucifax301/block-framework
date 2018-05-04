package com.block.framework.core.interceptor.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.block.framework.core.trace.TraceCGLibUtil;
import com.block.framework.eventdriver.BlockService;
import com.block.framework.eventdriver.ServiceEvent;
import com.block.framework.eventdriver.ServiceEventRegistry;
import com.block.framework.eventdriver.annotation.EventProcessor;

/**
 * 在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。

	这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免上面提到的问题，我们可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理
 * @author devil
 * 
 */
public class InstantiationServiceBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		if(context.getParent() == null){
			String[] names = context.getBeanDefinitionNames();
			for(String name:names){
				System.out.println("##########bean:"+name);
				Object bean = context.getBean(name);
				Class cls = bean.getClass();
				Service service = (Service) cls.getAnnotation(Service.class);
				if(service!=null){
//					Class<?> cls = context.getBean(name).getClass();
//					System.out.println("@@@@@@@bean"+cls);
//					Object newbean = TraceCGLibUtil.createBean(cls);
					EventProcessor eventProcessor = (EventProcessor) cls.getAnnotation(EventProcessor.class);
					if(eventProcessor != null){
						Class<?>[] interfaces = cls.getInterfaces();
						for(Class<?> intf:interfaces){
							if(intf.isAssignableFrom(BlockService.class)){
								Class<?> eventcls = eventProcessor.event();
								if(eventcls.isAssignableFrom(ServiceEvent.class)){
									ServiceEventRegistry.register((Class<ServiceEvent>)eventcls, (BlockService)bean);
									break;
								}
							}
						}
						
						
					}
					
				}
				
			}
		}
		
	}

}
