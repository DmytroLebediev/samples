package com.lear.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Дмитрий on 12.07.2017.
 */
@Aspect
public class MethodLogger
{
    private Logger logger = null;
    private Map<Class, Logger> loggers = new HashMap<Class, Logger>();

    @Pointcut("within(com.lear.messaging.*)")
    public void messagingMethods() {}

    @Around("messagingMethods()")
    public Object doBasicProfiling(ProceedingJoinPoint joinPoint) throws Throwable
    {
        logger = getLogger(joinPoint.getTarget().getClass());

        if (logger.isInfoEnabled())
        {
            logMethodStart(joinPoint);
        }
        Object retVal = joinPoint.proceed();
        if (logger.isInfoEnabled())
        {
            logMethodEnd(joinPoint, retVal);
        }
        return retVal;
    }

    private void logMethodStart(JoinPoint joinPoint)
    {
        logger.info(String.format("%s started", joinPoint.getSignature().getName()));
    }

    private void logMethodEnd(JoinPoint joinPoint, Object methodResult)
    {
        if (null == methodResult)
        {
            logger.info(String.format("%s ended", joinPoint.getSignature().getName()));
        }
        else
        {
            logger.info(String.format("%s retruned %s",
                    joinPoint.getSignature().getName(),
                    methodResult.toString()));
        }
    }

    private Logger getLogger(Class target)
    {
        Logger logger = loggers.get(target);
        if (null == logger)
        {
            logger = LogManager.getLogger(target);
            loggers.put(target, logger);
        }
        return logger;
    }
}
