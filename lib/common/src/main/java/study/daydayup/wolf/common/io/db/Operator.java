package study.daydayup.wolf.common.io.db;

import study.daydayup.wolf.common.io.db.aggregator.AggregatorGateway;
import study.daydayup.wolf.common.io.db.mapper.MapperGateway;
import study.daydayup.wolf.common.io.db.matcher.MatcherGateway;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/11 11:22 上午
 **/
public class Operator {
    private MapperGateway mapper;
    private MatcherGateway matcher;
    private AggregatorGateway aggregator;
    private Statistics statistics;

    public void operate(Row row) {

    }

    public MatcherGateway match() {
        if (null == matcher) {
            matcher = new MatcherGateway();
        }

        return matcher;
    }

    public AggregatorGateway aggregate() {
        if (aggregator == null) {
            aggregator = new AggregatorGateway();
        }

        return aggregator;
    }
}
