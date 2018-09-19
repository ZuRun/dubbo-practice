package cn.zull.study.mq.consumer.mq;

import cn.zull.study.mq.AbstractRocketMqConsumer;
import cn.zull.study.mq.constants.ConsumerTag;
import cn.zull.study.mq.constants.Tag;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author zurun
 * @date 2018/9/18 11:06:53
 */
@Component
public class DemoMqConsumer extends AbstractRocketMqConsumer {
    @Value("${rocketmq.namesinvokervAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumerGroup}")
    private String consumerGroup;

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
        return true;
    }
}
