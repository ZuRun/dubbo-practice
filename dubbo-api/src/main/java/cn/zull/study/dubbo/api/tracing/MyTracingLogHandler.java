package cn.zull.study.dubbo.api.tracing;

import cn.zull.tracing.core.log.TracingLogHandler;
import cn.zull.tracing.core.model.TraceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2018/10/2 16:49:42
 */
@Component
public class MyTracingLogHandler implements TracingLogHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handler(TraceLog traceLog) {
        logger.info("日志处理实现类 traceInfo:{}", traceLog);
    }
}
