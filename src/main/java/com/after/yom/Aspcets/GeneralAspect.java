package com.after.yom.Aspcets;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class GeneralAspect {

    Logger logger= Logger.getLogger("Aspect");


    @Pointcut("execution(* com.after.yom.Controllers.Admin*.*(..))")
    public void pointCut1(){}

    @Pointcut("execution(* com.after.yom.Controllers.Manager*.*(..))")
    public void pointCut2(){}

    @After(value = "pointCut1() || pointCut2()")
    public void runAPIController(JoinPoint joinPoint) throws Throwable {
        logger.info("Name:" + joinPoint.getSignature().getName());
    }

//    @Around(value = "execution(* com.after.yom.Controllers.*.*(..))")
//    public Object runAroundAPIController(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("Name:" + joinPoint.getSignature().getName());
//        Object x = joinPoint.proceed();
//        logger.info("result: " + x);
//        return x;
//    }




}
