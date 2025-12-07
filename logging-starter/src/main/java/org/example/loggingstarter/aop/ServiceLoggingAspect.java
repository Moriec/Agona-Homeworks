package org.example.loggingstarter.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ServiceLoggingAspect {
    @Around("@within(org.springframework.stereotype.Service) && execution(* *(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Logger log = LoggerFactory.getLogger(pjp.getSignature().getDeclaringTypeName());
        long start = System.nanoTime();
        Object[] args = pjp.getArgs();
        Object result = pjp.proceed();
        long timeMs = (System.nanoTime() - start) / 1_000_000;
        org.slf4j.MDC.put("execTime", String.valueOf(timeMs));
        if (log.isDebugEnabled()) log.debug("args={} result={}", java.util.Arrays.toString(args), result);
        org.slf4j.MDC.remove("execTime");
        return result;
    }
}
