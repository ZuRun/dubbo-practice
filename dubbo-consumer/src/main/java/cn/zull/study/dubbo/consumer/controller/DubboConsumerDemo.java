package cn.zull.study.dubbo.consumer.controller;

import cn.zull.study.dubbo.api.dto.ReqDto;
import cn.zull.study.dubbo.api.dto.RespDto;
import cn.zull.study.dubbo.api.service.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author zurun
 * @date 2018/9/16 23:19:44
 */
@RestController
public class DubboConsumerDemo {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference(version = "${demo.service.version}",
            url = "dubbo://localhost:12345")
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }

    @PostMapping("getInfo")
    public RespDto getInfo(@RequestBody ReqDto reqDto) {
        RespDto respDto = demoService.getInfo(reqDto);
        logger.info("respDto:{}", respDto);
        return respDto;
    }
}
