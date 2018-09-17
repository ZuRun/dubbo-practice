package cn.zull.study.dubbo.product.trace;

import cn.zull.study.dubbo.api.utils.AbstractRpcTraceContext;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zurun
 * @date 2018/9/17 10:28:07
 */
@Component
public class ProductTraceContext extends AbstractRpcTraceContext {
    @Override
    public void addRpcContext() {

    }

    @Override
    public Map<String, String> rpcValues() {
        return RpcContext.getContext().getAttachments();
    }
}
