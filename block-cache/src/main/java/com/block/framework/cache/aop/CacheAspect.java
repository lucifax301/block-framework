package com.block.framework.cache.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.block.framework.cache.BlockCache;
import com.block.framework.cache.BlockCacheFactory;
import com.block.framework.cache.annotation.DataCache;

//@Aspect
//@Component
public class CacheAspect {

	private static Logger logger = LoggerFactory.getLogger(CacheAspect.class);
	
	//@Pointcut("@annotation(com.block.framework.cache.annotation.DataCache)")  
    public  void cacheAspect() {  
    } 
    
	@Autowired
    private BlockCacheFactory cacheFactory;
	
	private BlockCache getCache(){
		return cacheFactory.getCache("redis");
	}
    
    //@Around("cacheAspect()")
    public Object get(ProceedingJoinPoint call)throws Throwable {
    	System.out.println("docache---------------------------------------");
    	DataCache anno=getAnnotation(call,DataCache.class);
        
        String key = anno.key();
        String namespace=anno.namespace();
        key = getKeyNameFromParam(key,call);
        
        Object value=getCache().get(namespace+"-"+key);
        
        if(value == null){//缓存为空
            value = call.proceed();
            if(value != null){
        		getCache().set(namespace+"-"+key,value);
            }
            
        }
  
        return value;
    }
    
    
    private <T  extends Annotation> T getAnnotation(ProceedingJoinPoint jp,Class<T> clazz){
         MethodSignature joinPointObject = (MethodSignature) jp.getSignature();  
         Method method = joinPointObject.getMethod();
         return method.getAnnotation(clazz);  
    }
    
    private Class<?> getType(ProceedingJoinPoint jp){
        MethodSignature joinPointObject = (MethodSignature) jp.getSignature();  
        Method method = joinPointObject.getMethod();
        return method.getReturnType();
    }
    
    
     /**
     * 
     * @Title: getKeyNameFromParam
     * @Description: 获得组合后的KEY值
     * @param @param key
     * @param @param jp
     * @param @return
     * @return String
     * @throws
     */
    private String getKeyNameFromParam(String key,ProceedingJoinPoint jp){
        if(!key.contains("$")){
            return key;
        }
        
        String regexp = "\\$\\{[^\\}]+\\}";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(key);
        List<String> names = new ArrayList<String>();
        try{
            while(matcher.find()){
                names.add(matcher.group());
            }
            key = executeNames(key,names,jp);
        }catch (Exception e) {
            logger.error("Regex Parse Error!", e);
        }
        
        
        return key;
    }
    
    /**
     * 
     * @Title: executeNames
     * @Description: 对KEY中的参数进行替换
     * @param @param key
     * @param @param names
     * @param @param jp
     * @param @return
     * @param @throws OgnlException
     * @return String
     * @throws
     */
    private String executeNames(String key, List<String> names,ProceedingJoinPoint jp) throws OgnlException {
        
        Method method = ((MethodSignature)jp.getSignature()).getMethod();
        
        //形参列表
        List<String> param = MethodParamNamesScaner.getParamNames(method);
        
        if(names==null||names.size()==0){
            return key;
        }
        
        Object[] params = jp.getArgs();
        
        Map<String,Object> map = new HashMap<String,Object>();
        for(int i=0;i<param.size();i++){
            map.put(param.get(i), params[i]);
        }
        
        for(String name:names){
            String temp = name.substring(2);
            temp = temp.substring(0,temp.length()-1);
            key = myReplace(key,name, Ognl.getValue(temp, map)+"");
        }
        
        return key;
    }
    
    /**
     * 
     * @Title: myReplace
     * @Description: 不依赖Regex的替换，避免$符号、{}等在String.replaceAll方法中当做Regex处理时候的问题。
     * @param @param src
     * @param @param from
     * @param @param to
     * @param @return
     * @return String
     * @throws
     */
    private String myReplace(String src,String from,String to){
        int index = src.indexOf(from);
        if(index == -1){
            return src;
        }
        
        return src.substring(0,index)+to+src.substring(index+from.length());
    }
}
