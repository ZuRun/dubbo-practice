package cn.zull.study.mq.consumer.mq;

import cn.zull.study.mq.AbstractRocketMqConsumer;
import cn.zull.study.mq.constants.ConsumerTag;
import cn.zull.study.mq.constants.Tag;
import cn.zull.tracing.core.RestTemplateFactory;
import cn.zull.tracing.core.log.CollectingLogUtils;
import cn.zull.tracing.rocketmq.RocketmqTraceContext;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author zurun
 * @date 2018/9/18 11:06:53
 */
@Component
public class DemoMqConsumer extends AbstractRocketMqConsumer {
    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumerGroup}")
    private String consumerGroup;

    RestTemplate restTemplate = RestTemplateFactory.getSingleton();

    @Autowired
    private RocketmqTraceContext traceContext;

    @Override
    @PostConstruct
    public void init() throws MQClientException {
        super.setNamesrvAddr(namesrvAddr);
        super.setConsumerGroup(consumerGroup);
        super.init();
    }

    @Override
    public void subscribeTopicTags(Set<ConsumerTag> set) {
        set.add(Tag.TAG);
    }

    @Override
    public boolean consumeMsg(MessageExt messageExt) {
        logger.info("messageExt:{}", messageExt);
        CollectingLogUtils.collectionLog(traceContext.consumer(traceDTO -> {
        }, messageExt), traceLog -> {
            traceLog.setTraceType("rocketmq-consumer");
            return null;
        });
        String body = new String(messageExt.getBody());
        logger.info("rest " + restTemplate.getForObject("http://localhost:8080/sayHello?name=" + "张三", String.class));
        return true;
    }
}
