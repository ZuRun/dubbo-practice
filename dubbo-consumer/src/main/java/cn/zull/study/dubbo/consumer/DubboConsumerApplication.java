package cn.zull.study.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zurun
 * @date 2018/9/16 23:23:34
 */
@SpringBootApplication(scanBasePackages = "cn.zull.study.dubbo.consumer.controller")
public class DubboConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(DubboConsumerApplication.class, args);

    }

}
