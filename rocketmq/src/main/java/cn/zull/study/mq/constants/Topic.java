package cn.zull.study.mq.constants;

/**
 * @author zurun
 * @date 2018/9/18 11:22:43
 */
public enum Topic implements ConsumerTopic {
    TOPIC("topic-test");

    private String topic;

    Topic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getTopic() {
        return this.topic;
    }
}
