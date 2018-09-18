package cn.zull.study.mq.constants;

/**
 * @author zurun
 * @date 2018/9/18 11:20:35
 */
public enum Tag implements ConsumerTag {
    TAG("tag-test", Topic.TOPIC);

    private String tag;
    private ConsumerTopic consumerTopic;

    Tag(String tag, ConsumerTopic consumerTopic) {
        this.tag = tag;
        this.consumerTopic = consumerTopic;
    }

    @Override
    public ConsumerTopic getConsumerTopic() {
        return this.consumerTopic;
    }

    @Override
    public String getTag() {
        return this.tag;
    }
}
