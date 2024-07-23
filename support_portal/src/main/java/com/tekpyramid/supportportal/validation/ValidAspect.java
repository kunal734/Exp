package com.tekpyramid.supportportal.validation;

import io.micrometer.common.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ValidAspect {
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        for (Object o : Arrays.stream(proceedingJoinPoint.getArgs()).toArray()) {
            if (StringUtils.isBlank(o.toString())){
                throw new NullPointerException("necessary details are not provided");
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
