package cn.zull.study.dubbo.consumer.aspect;

import cn.zull.study.dubbo.api.utils.UUIDUtils;
import cn.zull.tracing.core.RestTraceContext;
import cn.zull.tracing.core.dto.TraceDTO;
import cn.zull.tracing.core.log.CollectingLogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

    @Around("point()")
    public Object before(ProceedingJoinPoint pjp) {
        logger.info("controller - aspect");

        TraceDTO traceDto = traceContext.consumer(traceDTO -> {
            if ("0.0".equals(traceDTO.getSpanId())) {
                Map<String, String> map = new HashMap<>();
                map.put("sequenceNo", UUIDUtils.genRandomNum(10));
                traceDTO.setProperties(map);
            }
        });
        return CollectingLogUtils.collectionLog(traceDto, traceLog -> {
            try {
                traceLog.setTraceType("aspect");
                return pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return pjp;
            }
        });
    }
}
