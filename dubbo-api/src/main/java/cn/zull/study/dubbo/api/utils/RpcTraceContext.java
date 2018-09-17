package cn.zull.study.dubbo.api.utils;


import cn.zull.study.dubbo.api.bo.TraceBO;

/**
 * @author zurun
 * @date 2018/9/17 10:20:06
 */
public interface RpcTraceContext extends TraceContext {

    /**
     * 调用远程rpc接口,将threadLocal中TraceBO赋值给rpcContext
     */
    void addRpcContext();

    /**
     * 从rpcContext读取
     * @return
     */
    TraceBO getTraceBoByRpcContext();

}
