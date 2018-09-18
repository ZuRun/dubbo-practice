package cn.zull.study.dubbo.api.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author zurun
 * @date 2018/9/16 23:43:27
 */
@Data
public class ReqDto {
    private String id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
