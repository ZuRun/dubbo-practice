package cn.zull.study.dubbo.api.bo;

import lombok.Data;

/**
 * @author zurun
 * @date 2018/9/17 09:27:38
 */
@Data
public class TraceBO {
    private String traceId;
    private String sequenceNo;
    private String spanId;

}
