package com.example.AopExample.aspect;

import org.aspectj.lang.annotation.Pointcut;

/*
public class JoinPointConfig {

    @Pointcut("execution(* io.datajek.springaop.movierecommenderaop.data.*.*(..))")
    public void dataLayerPointcut() {}

    @Pointcut("execution(* io.datajek.springaop.movierecommenderaop.business.*.*(..))")
    public void businessLayerPointcut() {}

    //to intercept method calls for both layers:
    @Pointcut("io.datajek.springaop.movierecommenderaop.aspect.JoinPointConfig.dataLayerPointcut() || "
            + "io.datajek.springaop.movierecommenderaop.aspect.JoinPointConfig.businessLayerPointcut()")
    public void allLayersPointcut() {}

    //for a particular bean
    @Pointcut("bean(movie*)")
    public void movieBeanPointcut() {}
}
*/

import com.example.AopExample.aspect.MeasureTime;

public class JoinPointConfig {

    // Correct the package for the data layer pointcut
    @Pointcut("execution(* com.example.AopExample.data.*.*(..))")
    public void dataLayerPointcut() {}

    // Correct the package for the business layer pointcut
    @Pointcut("execution(* com.example.AopExample.business.*.*(..))")
    public void businessLayerPointcut() {}

    @Pointcut("com.example.AopExample.aspect.JoinPointConfig.dataLayerPointcut() || "
            + "com.example.AopExample.aspect.JoinPointConfig.businessLayerPointcut()")
    public void allLayersPointcut() {}

    @Pointcut("bean(movie*)")
    public void movieBeanPointcut() {}

    // Correct the package for the custom annotation pointcut
    @Pointcut("@annotation(com.example.AopExample.aspect.MeasureTime)")
    public void measureTimeAnnotation() {}
}
