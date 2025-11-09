package com.example.demo.config.aspects;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private static Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Defines a pointcut that matches any public method in classes under
    // com.example.demo.controller package
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void allControllerMethods() {
    }

    // define a advice that will be executed at join point matching the pointcut expressions.
    @Before("allControllerMethods()")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        log.info("Before Method: {}", joinPoint.getSignature().getName());

        // Access HttpServletRequest
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // Log all the request parameters
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                log.info("Request parameter: {} = {}", paramName, paramValue);
            }

        } else {
            log.warn("HttpServletRequest not found. unable to log request parameters.");
        }

    }

    // Defines an advice that runs after execution of methods matched by the pointcut
    // regardless of the method outcome.
    @After("allControllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After Method: {}", joinPoint.getSignature().getName());
    }

    @Around("allControllerMethods()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around Method - Before: {}", proceedingJoinPoint.getSignature().getName());

        Object result = proceedingJoinPoint.proceed(); // please proceed with actual method execution

        log.info("Around Method - After: {}", proceedingJoinPoint.getSignature().getName());
        return result;
    }

    // This advice will run as a step after @After advice.
    // This is used to inform about successful resultant of the method
    @AfterReturning(pointcut = "allControllerMethods()", returning = "result")
    public void logAfterSucccesReturn(JoinPoint joinPoint, Object result) {
        log.info("Method returned: {}", result);
    }

    // Executed whenever there is an exception in the code.
    @AfterThrowing(pointcut = "allControllerMethods()", throwing = "error")
    public void logAfterThrowingException(JoinPoint joinPoint, Throwable error) {
        log.info("Method threw exception: ", error);
    }
}
