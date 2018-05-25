package com.block.framework.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.SystemUtils;

public class NetAddressUtil {

	private static String localIp;
	private static String localHostName;
	private static InetAddress inetAddress;
	
	public static String getLocalIpAddress(){
		init();
		return localIp;
	}
	
	public static String getLocalLocalHostName(){
		init();
		return localHostName;
	}
	
	private static boolean isValidNI(NetworkInterface ni) throws SocketException{
		return (!ni.isLoopback())&&ni.isUp()&&(!ni.getName().startsWith("docker"));
	}
	
	private static boolean isValidAddress(InetAddress address){
		return ((!(address.isAnyLocalAddress())) && (!(address.isLinkLocalAddress()))
				&& (!(address.isLoopbackAddress()))// && (address.isReachable(timeoutMs))
				&& (address instanceof Inet4Address));
	}
	
	public static final String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		if (ip == null || ip.length() == 0)
		{
			ip = "unknown";
		}
		
		//logger.info(String.format("请求HttpServletRequest的ip地址为:%s",ip));
		
		return ip;
	}
	
	private static void init(){
		if(inetAddress!=null){
			return;
		}
		try{
			InetAddress address = null;
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			
			if(!(SystemUtils.IS_OS_WINDOWS)){
				List<NetworkInterface> netIFs = Collections.list(networkInterfaces);
				Collections.reverse(netIFs);
				networkInterfaces = Collections.enumeration(netIFs);
			}
			
			while(networkInterfaces.hasMoreElements()){
				NetworkInterface ni = networkInterfaces.nextElement();
				
				if(!isValidNI(ni))
					continue;
				Enumeration<InetAddress> addresses = ni.getInetAddresses();
				while(addresses.hasMoreElements()){
					address =  addresses.nextElement();
					if(isValidAddress(address)){
						inetAddress=address;
						break;
					}
				}
			}
			
			if(inetAddress==null){
				inetAddress = InetAddress.getLocalHost();	
			}
			localIp=inetAddress.getHostAddress();
			localHostName=inetAddress.getHostName();
		}catch(Exception ex){
			throw new Error("Can't get ip addres.");
		}
	}
}
