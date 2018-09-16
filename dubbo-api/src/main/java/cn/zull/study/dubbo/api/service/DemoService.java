package cn.zull.study.dubbo.api.service;

import cn.zull.study.dubbo.api.dto.ReqDto;
import cn.zull.study.dubbo.api.dto.RespDto;
/**
 * @author zurun
 * @date 2018/9/16 23:07:59
 */
public interface DemoService {

    String sayHello(String name);

    RespDto getInfo(ReqDto reqDto);
}
