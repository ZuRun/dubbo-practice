package cn.zull.study.dubbo.product.service.impl;

import cn.zull.study.dubbo.api.dto.ReqDto;
import cn.zull.study.dubbo.api.dto.RespDto;
import cn.zull.study.dubbo.api.service.DemoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zurun
 * @date 2018/9/16 23:10:42
 */
@Service(version = "${dubbo.service.version}")
public class DemoServiceImpl implements DemoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }

    @Override
    public RespDto getInfo(ReqDto reqDto) {
        logger.info("ReqDto: {}", reqDto);
        RespDto respDto = new RespDto();
        respDto.setId(reqDto.getId());
        respDto.setNo("no_" + reqDto.getName());
        return respDto;
    }
}
