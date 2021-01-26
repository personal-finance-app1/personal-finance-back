package com.revature.personalfinance.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Before("within(com.revature.personalfinance.model.*)")
    public void logModelMethods(JoinPoint joinPoint) {
        String message = String.format("%s invoked %s", joinPoint.getTarget(), joinPoint.getSignature());
        log.debug(message);
    }
    
    
//    @After("within(com.revature.personalfinance.service.*)")
//    public void logAuthenticationMethod(JoinPoint jp, Exception ex) {
//		log.warn(jp.getTarget() + " invoked " + jp.getSignature() + " throwing " + ex.getClass(), ex);
//		System.out.println(jp.getTarget() + " invoked " + jp.getSignature() + " throwing " + ex.getClass());
//    }
    
    @AfterThrowing(pointcut="execution(public * getUserId(..))", throwing="ex")
    public void logUserIdMethod(JoinPoint jp, Exception ex) {
		log.warn(jp.getTarget() + " invoked " + jp.getSignature() + " throwing " + ex.getClass(), ex);
		System.out.println(jp.getTarget() + " invoked " + jp.getSignature() + " throwing " + ex.getClass());
    }
    
//    @AfterReturning(pointcut="execution(ResponseEntity<Account> updateAccount(..))", returning="returnedObject")
//    public void logController(JoinPoint joinPoint, Object returnedObject) {
//    	String message = String.format("%s invoked %s returning %s", joinPoint.getTarget(), joinPoint.getSignature(), returnedObject);
//        log.debug(message);
//    }
//    
//    @AfterReturning(pointcut="execution(Account updateAccount(..))", returning="returnedObject")
//    public void logService(JoinPoint joinPoint, Object returnedObject) {
//    	String message = String.format("%s invoked %s returning %s", joinPoint.getTarget(), joinPoint.getSignature(), returnedObject);
//        log.debug(message);
//    }
    
}
