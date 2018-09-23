package cn.zull.study.dubbo.consumer.aspect;

import cn.zull.study.dubbo.api.utils.UUIDUtils;
import cn.zull.tracing.core.RestTraceContext;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zurun
 * @date 2018/9/17 11:11:09
 */
@Aspect
@Component
public class ControllerAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RestTraceContext traceContext;

    @Pointcut("execution(* cn.zull.study.dubbo.consumer.controller.*.*(..))")
    public void point() {
    }

    @Before("point()")
    public void before(JoinPoint jp) {
        logger.info("controller - aspect");

        traceContext.product(traceDTO -> {
            if (StringUtils.isEmpty(traceDTO.getTraceId())) {
                traceDTO.setTraceId(UUIDUtils.simpleUUID());
                traceDTO.setSpanId("0.1");
            }
        });
    }
}
