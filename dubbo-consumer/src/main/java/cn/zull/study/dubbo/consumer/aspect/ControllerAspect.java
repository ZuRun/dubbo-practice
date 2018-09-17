package cn.zull.study.dubbo.consumer.aspect;

import cn.zull.study.dubbo.api.bo.TraceBO;
import cn.zull.study.dubbo.api.utils.TraceContext;
import cn.zull.study.dubbo.api.utils.UUIDUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2018/9/17 11:11:09
 */
@Aspect
@Component
public class ControllerAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TraceContext traceContext;

    @Pointcut("execution(* cn.zull.study.dubbo.consumer.controller.*.*(..))")
    public void point(){}

    @Before("point()")
    public void before(JoinPoint jp) {
        logger.info("controller - aspect");
        TraceBO traceBO = new TraceBO();
        traceBO.setTraceId(UUIDUtils.simpleUUID());
        traceBO.setSequenceNo("2018-09-17 11:18:27");
        traceContext.addTraceBo(traceBO);
    }
}
