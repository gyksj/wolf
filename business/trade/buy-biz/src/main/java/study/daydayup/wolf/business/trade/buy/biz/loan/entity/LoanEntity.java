package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.event.loan.ApproveEvent;
import study.daydayup.wolf.business.trade.api.event.loan.RefuseEvent;
import study.daydayup.wolf.business.trade.api.event.loan.loan.LoanBeginEvent;
import study.daydayup.wolf.business.trade.api.event.loan.loan.LoanSuccessEvent;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.vo.contract.RepaymentTerm;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.entity
 *
 * @author Wingle
 * @since 2019/12/23 10:16 上午
 **/
public class LoanEntity extends AbstractEntity<Contract> implements Entity  {
    public LoanEntity(Contract model) {
        this.model = model;
        this.locker = Contract.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();
    }

    public LoanEntity(TradeId tradeId) {
        tradeId.valid();
        Contract contract = Contract.builder()
                .tradeNo(tradeId.getTradeNo())
                .buyerId(tradeId.getBuyerId())
                .sellerId(tradeId.getSellerId())
                .build();

        model = contract;
        locker = contract;
        isNew = false;
    }

    public void approve() {
        //loan.state:approved
        ApproveEvent event = ApproveEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();
        changes.setStateEvent(event);
    }
    public void refuse() {
        //loan.state:refused
        RefuseEvent event = RefuseEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();
        changes.setStateEvent(event);
    }

    public void startLoan() {
        LoanBeginEvent event = LoanBeginEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .currency(model.getLoanTerm().getCurrency())
                .build();

        changes.setStateEvent(event);

        //fire loan order create event
        fire(event);
    }
    public void completeLoan() {
        // pay notify -> order state:paid -> loan order paid
        // loan.service subscribe(loan order paid)
        // Loan.finishLoan
        LoanSuccessEvent event = LoanSuccessEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();
        changes.setStateEvent(event);

        //loan.installment.effect
    }

    public void due() {
        // loan.service.scan: due loan  -> fire loan due event
        // order.service.subscribe(loan due event)
        // order.create()
    }
    public void repay() {
        // order.pay -> order.state:paid -> fire order paid event
        // loan.service.subscribe(order paid event)
        // loan.state:change...
    }

    public void overdue() {

    }

    public void markAsLoss() {}

}
