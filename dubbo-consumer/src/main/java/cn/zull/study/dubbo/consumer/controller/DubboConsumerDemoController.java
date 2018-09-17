package cn.zull.study.dubbo.consumer.controller;

import cn.zull.study.dubbo.api.dto.ReqDto;
import cn.zull.study.dubbo.api.dto.RespDto;
import cn.zull.study.dubbo.consumer.service.DubboConsumerDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zurun
 * @date 2018/9/16 23:19:44
 */
@RestController
public class DubboConsumerDemoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DubboConsumerDemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }

    @PostMapping("getInfo")
    public RespDto getInfo(@RequestBody ReqDto reqDto) {
        return demoService.getInfo(reqDto);
    }
}
