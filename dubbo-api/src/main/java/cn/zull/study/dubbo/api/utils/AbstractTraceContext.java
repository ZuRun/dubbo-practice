package cn.zull.study.dubbo.api.utils;

import cn.zull.study.dubbo.api.bo.TraceBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zurun
 * @date 2018/9/17 09:49:14
 */
public abstract class AbstractTraceContext implements TraceContext {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final ThreadLocal<TraceBO> context = ThreadLocal.withInitial(TraceBO::new);

    public void print() {
        logger.info("traceBo:{}", context.get());
    }

    @Override
    public void addTraceBo(TraceBO traceBO) {
        context.set(traceBO);
        print();
    }
}
