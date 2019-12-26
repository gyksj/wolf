package study.daydayup.wolf.business.trade.api.event.loan.loan;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.event.AbstractTradeEvent;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;

/**
 * study.daydayup.wolf.business.trade.api.event.loan
 *
 * @author Wingle
 * @since 2019/12/16 6:38 下午
 **/
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class LoanSuccessEvent extends AbstractTradeEvent implements TradeEvent {
}
