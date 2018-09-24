package cn.zull.study.dubbo.consumer.aspect;

import cn.zull.study.dubbo.api.utils.UUIDUtils;
import cn.zull.tracing.core.RestTraceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

        traceContext.consumer(traceDTO -> {
            if ("0.0".equals(traceDTO.getSpanId())) {
                Map<String, String> map = new HashMap<>();
                map.put("sequenceNo", UUIDUtils.genRandomNum(10));
                traceDTO.setProperties(map);
            }
//            if (StringUtils.isEmpty(traceDTO.getTraceId())) {
//                traceDTO.setTraceId(UUIDUtils.simpleUUID());
//                traceDTO.setSpanId("0.1");
//            }
        });
    }
}
