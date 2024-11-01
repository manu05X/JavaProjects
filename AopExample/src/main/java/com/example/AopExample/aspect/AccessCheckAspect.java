package com.example.AopExample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Configuration
public class AccessCheckAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* io.datajek.springaop.movierecommenderaop.business.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //	logger.info("Intercepted method call");
        logger.info("Intercepted call before execution of: {}", joinPoint);

        //access check logic
    }
}

/*
1. Define aspect class.
2. Write methods containing the advice to be executed when method calls are intercepted.
3. Write pointcut expressions for intercepting method calls.
* */