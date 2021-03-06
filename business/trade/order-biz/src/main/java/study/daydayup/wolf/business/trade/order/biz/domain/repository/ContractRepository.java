package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.converter.ContractConverter;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.ContractDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;
import study.daydayup.wolf.business.trade.order.biz.domain.factory.ContractFactory;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.contract.*;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.framework.layer.context.RpcContext;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class ContractRepository extends AbstractRepository implements Repository {
    @Resource
    protected ConsignTermRepository consignTermRepository;
    @Resource
    protected InstallmentTermRepository installmentTermRepository;
    @Resource
    protected LoanTermRepository loanTermRepository;
    @Resource
    protected ObjectsTermRepository objectsTermRepository;
    @Resource
    protected PaymentTermRepository paymentTermRepository;
    @Resource
    protected PostageTermRepository postageTermRepository;
    @Resource
    protected RepaymentTermRepository repaymentTermRepository;
    @Resource
    protected TaxTermRepository taxTermRepository;
    @Resource
    protected TradeStateLogRepository stateLogRepository;
    @Resource
    protected ContractDAO contractDAO;
    @Resource
    protected RpcContext rpcContext;
    @Resource
    protected ContractConverter converter;

    public void add(Contract contract) {
        if (contract == null) {
            return;
        }
        insertContract(contract);

        consignTermRepository.add(contract.getConsignTerm());
        installmentTermRepository.add(contract.getInstallmentTermList());
        loanTermRepository.add(contract.getLoanTerm());
        objectsTermRepository.add(contract.getObjectsTerm());
        paymentTermRepository.add(contract.getPaymentTerm());
        postageTermRepository.add(contract.getPostageTerm());
        repaymentTermRepository.add(contract.getRepaymentTerm());
        taxTermRepository.add(contract.getTaxTerm());
    }

    public void save(@NonNull Contract key, @NonNull Contract changes) {
        updateContract(key, changes);

        stateLogRepository.log(changes);
//        consignTermRepository.save(key.getConsignTerm(), changes.getConsignTerm());
        installmentTermRepository.save(key.getInstallmentTermList(), changes.getInstallmentTermList());
//        loanTermRepository.save(key.getLoanTerm(), changes.getLoanTerm());
//        objectsTermRepository.save(key.getObjectsTerm(), changes.getObjectsTerm());
//        paymentTermRepository.save(key.getPaymentTerm(), changes.getPaymentTerm());
//        postageTermRepository.save(key.getPostageTerm(), changes.getPostageTerm());
//        repaymentTermRepository.save(key.getRepaymentTerm(), changes.getRepaymentTerm());
//        taxTermRepository.save(key.getTaxTerm(), changes.getTaxTerm());
    }

    public Contract find(TradeId tradeId) {
        return find(tradeId, null);
    }

    public Contract find(TradeId tradeId, ContractOption option) {
        ContractDO contractDO = findContract(tradeId);

        return findTermsByContract(contractDO, tradeId);
    }

    /**
     *
     * @param contractDO dataObject
     * @param tradeId tradeId
     * @return init(Contract)
     */
    protected Contract findTermsByContract(ContractDO contractDO, TradeId tradeId) {
        if (contractDO == null) {
            return null;
        }

        Contract contract = converter.toModel(contractDO);
        contract.setConsignTerm(consignTermRepository.find(tradeId));
        contract.setInstallmentTermList(installmentTermRepository.find(tradeId));
        contract.setLoanTerm(loanTermRepository.find(tradeId));
        contract.setObjectsTerm(objectsTermRepository.find(tradeId));
        contract.setPaymentTerm(paymentTermRepository.find(tradeId));
        contract.setPostageTerm(postageTermRepository.find(tradeId));
        contract.setRepaymentTerm(repaymentTermRepository.find(tradeId));
        contract.setTaxTerm(taxTermRepository.find(tradeId));

        return ContractFactory.create(contract).getModel();
    }

    private ContractDO findContract(TradeId tradeId) {
        tradeId.valid();

        return contractDAO.selectByTradeNo(tradeId.getTradeNo(), tradeId.getBuyerId(), tradeId.getSellerId());
    }

    private void insertContract(@Validated Contract contract) {
        ContractDO contractDO = converter.toDo(contract);
        contractDO.setCreatedAt(rpcContext.getRequestTime());

        if (null != contract.getState()) {
            contractDO.setState(contract.getState().getCode());
        } else {
            StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(contract.getTradeType());
            TradeState initState = stateMachine.getInitState();
            contractDO.setState(initState.getCode());
        }

        contractDAO.insertSelective(contractDO);
    }

    private void updateContract(@Validated Contract key, Contract changes) {
        if (changes == null || null == key) {
            return ;
        }

        ContractDO keyDO = converter.toDo(key);
        if (key.getState() != null) {
            keyDO.setState(key.getState().getCode());
            changes.setState(key.getState());
        }

        ContractDO changesDO = converter.toDo(changes);
        changesDO.setUpdatedAt(rpcContext.getRequestTime());

        if (null != changes.getStateEvent()) {
            TradeState state = Tsm.getStateByEvent(key.getTradeType(), key.getState(), changes.getStateEvent());
            if (state == null) {
                return;
            }
            changesDO.setState(state.getCode());
        }

        contractDAO.updateByKey(changesDO, keyDO);
    }
}
