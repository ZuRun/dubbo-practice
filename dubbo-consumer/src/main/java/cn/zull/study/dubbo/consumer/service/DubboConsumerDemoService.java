package cn.zull.study.dubbo.consumer.service;

import cn.zull.study.dubbo.api.dto.ReqDto;
import cn.zull.study.dubbo.api.dto.RespDto;
import cn.zull.study.dubbo.api.service.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zurun
 * @date 2018/9/17 11:23:57
 */
@Service
public class DubboConsumerDemoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "${demo.service.version}")
    private DemoService demoService;

    public String sayHello(String name) {
        return demoService.sayHello(name);
    }


    public RespDto getInfo(ReqDto reqDto) {
        RespDto respDto = demoService.getInfo(reqDto);
        logger.info("respDto:{}", respDto);
        return respDto;
    }
}
