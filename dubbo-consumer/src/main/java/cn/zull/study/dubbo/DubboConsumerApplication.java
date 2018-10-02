package cn.zull.study.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zurun
 * @date 2018/9/16 23:23:34
 */
@SpringBootApplication
public class DubboConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(DubboConsumerApplication.class, args);

    }

}
