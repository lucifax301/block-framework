package com.block.framework.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.RedissonClient;
import org.redisson.core.RLock;
import org.springframework.beans.factory.annotation.Autowired;

public class DistributedRedisLock {

	@Autowired
	private RedissonClient redissonClient;
	
	
	
	/**
	 * 
	 * @param lockName
	 * @param waitTime
	 * @param leaseTime
	 * @param timeUnit
	 * @return
	 * @throws InterruptedException
	 */
	public boolean tryAcquire(String lockName,int waitTime,int leaseTime, TimeUnit timeUnit) throws InterruptedException{  
        
        RLock lock = redissonClient.getLock(lockName);  
        // 尝试加锁，最多等待2秒，上锁以后10秒自动解锁
        boolean hasLock = lock.tryLock(2, 10, TimeUnit.SECONDS);
        return hasLock;
        //lock.lock(time, timeUnit); //lock提供带timeout参数，timeout结束强制解锁，防止死锁  
        //return  true;  
    }
	
	/**
	 * 如果锁已经被其他人占用，会一直hold住
	 * @param lockName
	 * @param leaseTime
	 * @param timeUnit
	 * @return
	 * @throws InterruptedException
	 */
	public boolean acquire(String lockName,int leaseTime, TimeUnit timeUnit) throws InterruptedException{  
        
        RLock lock = redissonClient.getLock(lockName);  
        lock.lock(leaseTime, timeUnit); //lock提供带timeout参数，timeout结束强制解锁，防止死锁  
        return  true;  
    }  
  
	public void release(String lockName){  
        RLock lock = redissonClient.getLock(lockName);  
        lock.unlock();  
    }  
    
    public RLock getLock(String lockName){
		return redissonClient.getLock(lockName);  
	}
    
    public void release(RLock lock){
    	if(lock!=null) lock.unlock();  
    }  
}
