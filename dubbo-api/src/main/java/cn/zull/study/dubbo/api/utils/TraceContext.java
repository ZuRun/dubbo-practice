package cn.zull.study.dubbo.api.utils;


import cn.zull.study.dubbo.api.bo.TraceBO;

/**
 * @author zurun
 * @date 2018/9/17 09:25:01
 */
public interface TraceContext {

    TraceBO getTraceBo();

    /**
     * 将bo新增到threadLocal入口
     * @param traceBO
     */
    void addTraceBo(TraceBO traceBO);
}
