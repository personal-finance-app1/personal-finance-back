package com.revature.personalfinance.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Before("within(com.revature.personalfinance.model.*)")
    public void logModelMethods(JoinPoint joinPoint) {
        String message = String.format("%s invoked %s", joinPoint.getTarget(), joinPoint.getSignature());
        log.info(message);
    }
}
