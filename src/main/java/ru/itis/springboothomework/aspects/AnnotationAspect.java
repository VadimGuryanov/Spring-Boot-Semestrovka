package ru.itis.springboothomework.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspect {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(ru.itis.springboothomework.aspects.ExceptionAnnotation)")
    public void calAnno() {
    }

    @Before("calAnno()")
    public void beforeAnno(JoinPoint jp) {
        logger.error(jp.getSignature().getName() + " " + jp.toLongString());
    }

    @After("calAnno()")
    public void afterAnno(JoinPoint jp) {
        logger.error(jp.getSignature().getName() + " " + jp.toLongString());
    }

}
