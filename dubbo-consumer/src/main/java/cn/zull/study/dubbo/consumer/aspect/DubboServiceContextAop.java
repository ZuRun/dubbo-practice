package cn.zull.study.dubbo.consumer.aspect;

import cn.zull.study.dubbo.consumer.trace.ConsumerTraceContext;
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
 * @date 2018/9/17 09:20:10
 */
@Aspect
@Component
public class DubboServiceContextAop {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ConsumerTraceContext traceContext;

    @Pointcut("execution(* cn.zull.study.dubbo.consumer.service.*.*(..))")
    public void serviceApi() {
    }

    @Before("serviceApi()")
    public void dubboContext(JoinPoint jp) {
        logger.info("dubbo - aspect");
        traceContext.addRpcContext();
//        RpcContext.getContext().setAttachments(context);
    }
}
