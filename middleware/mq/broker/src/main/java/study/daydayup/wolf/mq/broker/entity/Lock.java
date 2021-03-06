package study.daydayup.wolf.mq.broker.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.mq.broker.entity
 *
 * @author Wingle
 * @since 2019/11/29 6:34 下午
 **/
@Data
public class Lock {
    private int id;
    private String topic;
    private String consumer;
    private byte shard;
    private int offset;
}
