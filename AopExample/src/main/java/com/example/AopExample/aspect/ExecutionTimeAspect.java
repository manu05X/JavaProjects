package com.example.AopExample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExecutionTimeAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Around("execution(* com.example.AopExample..*.*(..))")
    //@Around("com.example.AopExample.aspect.JoinPointConfig.businessLayerPointcut()")
    @Around("com.example.AopExample.aspect.JoinPointConfig.measureTimeAnnotation()")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //note start time
        long startTime = System.currentTimeMillis();
        //allow method call to execute
        Object returnValue = joinPoint.proceed();
        //time taken = end time - start time
        long timeTaken = System.currentTimeMillis() - startTime;

        logger.info("Time taken by {} to complete execution is: {}", joinPoint, timeTaken);
        return returnValue;
    }
}
