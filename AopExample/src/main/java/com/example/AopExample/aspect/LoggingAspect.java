package com.example.AopExample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Correct the package for the business layer pointcut in @AfterReturning
    @AfterReturning(value = "execution(* com.example.AopExample.business.*.*(..))", returning = "result")
    public void logAfterExecution(JoinPoint joinPoint, Object result) {
        logger.info("{} returned with: {}", joinPoint, result);
    }

    // Correct the package for the business layer pointcut in @AfterThrowing
    @AfterThrowing(value = "execution(* com.example.AopExample.business.*.*(..))", throwing = "exception")
    public void logAfterException(JoinPoint joinPoint, Object exception) {
        logger.info("Exception in {} returned with: {}", joinPoint, exception);
    }

    // Correct the package for the business layer pointcut in @After
    @After(value = "execution(* com.example.AopExample.business.*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        logger.info("After {}", joinPoint);
    }
}