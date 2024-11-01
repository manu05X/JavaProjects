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

    // Correct the package for the method execution in the pointcut expression
      @Before("execution(* com.example.AopExample.business..*.*Filtering(..)) || execution(String com.example.AopExample..*.*(..))")
//    @Before("execution(String com.example.AopExample.business..*.*(String))")
//    @Before("execution(* com.example.AopExample.business..*.*Filtering(..))")
//    @Before("execution(String com.example.AopExample.business..*.*(..))")
//    @Before("execution(* com.example.AopExample.business.*.*(..))")
    public void before(JoinPoint joinPoint) {
          //	logger.info("Intercepted method call");
        logger.info("Intercepted call before execution of: {}", joinPoint);
        //access check logic
    }
}
/*
@Aspect
@Configuration
public class AccessCheckAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* io.datajek.springaop.movierecommenderaop..*.*Filtering(..)) || execution(String io.datajek.springaop.movierecommenderaop..*.*(..))")
    //@Before("execution(String io.datajek.springaop.movierecommenderaop..*.*(String))")
    //@Before("execution(* io.datajek.springaop.movierecommenderaop..*.*Filtering(..))")
    //@Before("execution(String io.datajek.springaop.movierecommenderaop..*.*(..))")
    //@Before("execution(* io.datajek.springaop.movierecommenderaop.business.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //	logger.info("Intercepted method call");
        logger.info("Intercepted call before execution of: {}", joinPoint);

        //access check logic
    }
}

*/

/*
1. Define aspect class.
2. Write methods containing the advice to be executed when method calls are intercepted.
3. Write pointcut expressions for intercepting method calls.

_______________________________________________________________________________________________

Intercepting all method calls in a package : @Before("execution(* io.datajek.springaop.movierecommenderaop.business.*.*(..))")
Intercepting all method calls :              @Before("execution(* io.datajek.springaop.movierecommenderaop..*.*(..))")
Intercepting calls using return type :       @Before("execution(String io.datajek.springaop.movierecommenderaop..*.*(..))")
Intercepting calls to a specific method :    @Before("execution(String io.datajek.springaop.movierecommenderaop..*.*Filtering(..))")
Intercepting calls with specific method arguments :
                                             @Before("execution(* io.datajek.springaop.movierecommenderaop..*.*(String))")
                                             @Before("execution(*io.datajek.springaop.movierecommenderaop..*.*(String,..))")
Combining pointcut expressions :
                                             @Before ("execution(* io.datajek.springaop.movierecommenderaop..*.*Filtering(..))
                                                    || execution(String io.datajek.springaop.movierecommenderaop..*.*(..))")
* */