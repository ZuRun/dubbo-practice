package cn.zull.study.mq;

import cn.zull.study.mq.constants.ConsumerTag;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;

/**
 * @author zurun
 * @date 2018/3/2 00:20:52
 */
public abstract class AbstractRocketMqProducer {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private DefaultMQProducer producer;

    protected AbstractRocketMqProducer() {
    }

    /**
     * producerGroup
     *
     * @return
     */
    protected abstract String producerGroup();

    /**
     * namesrvAddr
     *
     * @return
     */
    protected abstract String namesrvAddr();

    /**
     * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
     * 注意：ProducerGroupName需要由应用来保证唯一<br>
     * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
     * 因为服务器会回查这个Group下的任意一个Producer
     */
    @PostConstruct
    protected void init() throws MQClientException {

        this.producer = new DefaultMQProducer();
        //指定NameServer地址，多个地址以 ; 隔开
        this.producer.setNamesrvAddr(namesrvAddr());
        this.producer.setProducerGroup(producerGroup());
        //TODO-zurun 其他参数

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        this.producer.start();
    }

    public SendResult send(ConsumerTag tag, String keys, String message) {
        return send(new Message(tag.getConsumerTopic().getTopic(), tag.getTag(), keys, message.getBytes()));
    }

    /**
     * TODO-zurun 这个地方的异常需要处理下,可以考虑将message存到redis中,或者抛异常,分情况考虑吧
     *
     * @param message
     * @return
     */
    public SendResult send(Message message) {
        try {
            return producer.send(message);
        } catch (Exception e) {
            logger.error("send msg error : {}", e.getMessage());
            return null;
        }
    }

    public Boolean sendMsg(Message message) {
        SendResult sendResult = send(message);
        return sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK;
    }

    /**
     * 单向（oneway）发送特点为只负责发送消息，
     * 不等待服务器回应且没有回调函数触发，即只发送请求不等待应答。
     * 此方式发送消息的过程耗时非常短，一般在微秒级别。
     *
     * @param msg msg
     */
    public void sendOneWayMsg(Message msg) {
        try {
            producer.sendOneway(msg);
        } catch (Exception e) {
            logger.error("send msg error", e);
        }
    }


    /**
     * 发送延迟消息.
     *
     * @param msg        content
     * @param delayLevel 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * @return send result
     */
    public boolean sendDelayMsg(Message msg, int delayLevel) {
        msg.setDelayTimeLevel(delayLevel);
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg);
        } catch (Exception e) {
            logger.error("send msg error", e);
        }
        return sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK;
    }


    /**
     * 销毁
     */
    @PreDestroy
    private void destroy() {
        if (Objects.nonNull(producer)) {
            logger.info("producer shutdown");
            producer.shutdown();
            logger.info("producer has shutdown");
        }
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

}
