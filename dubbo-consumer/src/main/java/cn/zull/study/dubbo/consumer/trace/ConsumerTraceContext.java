package cn.zull.study.dubbo.consumer.trace;

import cn.zull.study.dubbo.api.bo.TraceBO;
import cn.zull.study.dubbo.api.utils.AbstractRpcTraceContext;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zurun
 * @date 2018/9/17 10:03:32
 */
@Component
public class ConsumerTraceContext extends AbstractRpcTraceContext {


    @Override
    public void addRpcContext() {
        TraceBO traceBO = context.get();
        RpcContext.getContext().setAttachments(traceBo2Map(traceBO));
    }

    @Override
    public Map<String, String> rpcValues() {
        return RpcContext.getContext().getAttachments();
    }
}
