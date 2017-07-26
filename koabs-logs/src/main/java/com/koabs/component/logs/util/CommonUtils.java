package com.koabs.component.logs.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	
	public static String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

    public static String getRawClassName(Object object) {
        if(object != null) {
            if(!AopUtils.isAopProxy(object)) {
                if(AopUtils.isCglibProxy(object.getClass())) {
                    return AopUtils.getTargetClass(object).getSimpleName();
                }

                return object.getClass().getSimpleName();
            }

            String v = AopUtils.getTargetClass(object).getSimpleName();
            if(!v.contains("$Proxy") && !v.contains("$$")) {
                return v;
            }

            try {
                Object o = ((Advised)object).getTargetSource().getTarget();
                return getRawClassName(o);
            } catch (Throwable var3) {
                ;
            }
        }

        return "UnknowClass";
    }

    public static String getMethodNameWithClassName(String className, String methodName) {
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(className)) {
            sb.append(className);
            sb.append(".");
        }

        if(StringUtils.isNotBlank(methodName)) {
            sb.append(methodName);
        }

        return sb.toString();
    }

    public static String getStackTrace(Throwable throwable) {
        return  throwable.getMessage();
    }
}
