package com.block.framework.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.block.framework.common.util.ApplicationContextUtil;

public class ProxyInvocationHandler implements InvocationHandler {

	 // 目标对象   
    private Class<?> targetCls;  
      
    /** 
     * 构造方法 
     * @param target 目标对象  
     */  
    public ProxyInvocationHandler(Class<?> cls) {  
        super();  
        this.targetCls = cls;  
    }  
  
  
    /** 
     * 执行目标对象的方法 
     */  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
          
        // 在目标对象的方法执行之前简单的打印一下  
        System.out.println("------------------before------------------");  
         
        Object target = ApplicationContextUtil.getBean(targetCls);
        // 执行目标对象的方法  
        Object result = method.invoke(target, args);  
          
        // 在目标对象的方法执行之后简单的打印一下  
        System.out.println("-------------------after------------------");  
          
        return result;  
    }  
  
    /** 
     * 获取目标对象的代理对象 
     * @return 代理对象 
     */  
    public Object getProxy() {  
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),   
                new Class<?>[]{targetCls}, this);  
    }  
}
