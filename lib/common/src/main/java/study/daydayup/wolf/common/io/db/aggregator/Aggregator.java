package study.daydayup.wolf.common.io.db.aggregator;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.common.io.db.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 11:39 上午
 **/
public interface Aggregator {
    void aggregate(Row row);
}