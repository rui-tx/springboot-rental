package com.ruitx.babyboot.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.ruitx.babyboot.controller.*.*(..))")
    public Object logControllerAction(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //String logMessage = "%s took %d ms to execute.\n";
        //System.out.printf(logMessage, methodName, (System.currentTimeMillis() - start));
        logger.info("Method {} took {} ms to execute", methodName, System.currentTimeMillis() - start);
        return result;
    }
}
