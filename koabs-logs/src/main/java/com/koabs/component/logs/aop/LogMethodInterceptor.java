package com.koabs.component.logs.aop;

import com.koabs.component.logs.internal.ILogInternalFacade;
import com.koabs.component.logs.internal.LogInternalFacade;
import com.koabs.component.logs.pojo.LogInfo;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

/**
 * Author: koabs
 * 2/16/17.
 */
public class LogMethodInterceptor implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LogMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        MethodInvocationProceedingJoinPoint ponint = new MethodInvocationProceedingJoinPoint((ProxyMethodInvocation) invocation);
            LogInfo logInfo = null;
            Throwable caughtThrowable = null;
            Object returnValueOfMethodCall = null;
            long startTime = System.currentTimeMillis();
            try {
                returnValueOfMethodCall = ponint.proceed();
            } catch (Throwable throwable) {
                caughtThrowable = throwable;
                throw throwable;
            } finally {
                long costTime= System.currentTimeMillis() - startTime;
                try {
                    ILogInternalFacade logInternalFacade = LogInternalFacade.getInstance();
                    if (logInternalFacade.isEnabled()){
                        logInfo = logInternalFacade.handleForMethodCall(ponint, returnValueOfMethodCall,
                                caughtThrowable, costTime);
                    }

                    if(caughtThrowable != null) {
                        logger.error("LogAspect catched Throwable. Details: {}", caughtThrowable);
                    }
                } catch (Throwable throwable) {
                    logger.error("Error occurs when doAroundMethodCall method of LogAspect called.", throwable);
                }

            }
            return returnValueOfMethodCall;
    }
}
