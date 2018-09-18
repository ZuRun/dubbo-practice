package cn.zull.study.dubbo.consumer.mq;

import cn.zull.study.mq.AbstractRocketMqProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2018/9/18 16:17:09
 */
@Component
public class DemoMqProducer extends AbstractRocketMqProducer {

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.producerGroup}")
    private String producerGroup;

    @Override
    protected String producerGroup() {
        return producerGroup;
    }

    @Override
    protected String namesrvAddr() {
        return namesrvAddr;
    }
}
