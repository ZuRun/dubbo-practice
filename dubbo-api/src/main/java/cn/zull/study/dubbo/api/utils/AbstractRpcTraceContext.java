package cn.zull.study.dubbo.api.utils;

import cn.zull.study.dubbo.api.bo.TraceBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zurun
 * @date 2018/9/17 10:21:45
 */
public abstract class AbstractRpcTraceContext extends AbstractTraceContext implements RpcTraceContext {

    @Override
    public TraceBO getTraceBo() {
        return getTraceBoByRpcContext();
    }

    @Override
    public TraceBO getTraceBoByRpcContext() {

        TraceBO traceBO = map2TraceBo(rpcValues());
        if (traceBO != null) {
            context.set(traceBO);
        }
        return context.get();
    }

    protected Map<String, String> traceBo2Map(TraceBO traceBO) {
        Map<String, String> map = new HashMap(3);
        map.put("traceId", traceBO.getTraceId());
        map.put("sequenceNo", traceBO.getSequenceNo());
        map.put("spanId", traceBO.getSpanId());
        return map;
    }

    protected TraceBO map2TraceBo(Map<String, String> map) {
        TraceBO traceBO = new TraceBO();
        traceBO.setSequenceNo(map.get("sequenceNo"));
        traceBO.setTraceId(map.get("traceId"));
        traceBO.setSpanId(map.get("spanId"));
        return traceBO;
    }

    /**
     * RpcContext values
     *
     * @return
     */
    public abstract Map<String, String> rpcValues();
}
