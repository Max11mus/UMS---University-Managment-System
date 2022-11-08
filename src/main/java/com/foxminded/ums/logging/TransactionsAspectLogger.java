package com.foxminded.ums.logging;

import com.foxminded.ums.exeptions.ServerErrorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Aspect
public class TransactionsAspectLogger {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionsAspectLogger.class);

    @Pointcut("within(com.foxminded.ums.service.*)")
    public void anyTransaction(){
    }

    @Around("anyTransaction()")
    public Object logTransaction(ProceedingJoinPoint point) throws Throwable {
        LOGGER.info("Start Transaction");

        LOGGER.info("Method: ");
        LOGGER.info(point.getSignature().toLongString());

        LOGGER.info("Arguments: ");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            LOGGER.info(arg.toString());
        }

        Object result = null;
        try {
            result = point.proceed();
            LOGGER.info("Result: ");
            LOGGER.info(result.toString());
            LOGGER.info("End Transaction");
            return result;
        } catch (Throwable e) {
            LOGGER.info(e.getMessage(), e);
            LOGGER.info("Unroll Transaction");
            if (e instanceof NoSuchElementException) {



            } else {
                throw new ServerErrorException(e.getMessage(), e);
            }
        }
    }

}
