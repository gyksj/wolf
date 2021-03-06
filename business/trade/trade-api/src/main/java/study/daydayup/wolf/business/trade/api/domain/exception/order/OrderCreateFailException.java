package study.daydayup.wolf.business.trade.api.domain.exception.order;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.trade.api.domain.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class OrderCreateFailException extends SystemException {
    public OrderCreateFailException() {
       this("create order fail");
    }

    public OrderCreateFailException(String msg) {
        super(160007, msg);
    }
}
