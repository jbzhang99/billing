package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.baas.amc.constants.OwnerType;
import com.ai.baas.amc.dao.mapper.bo.*;
import com.ai.baas.amc.service.atom.interfaces.*;
import com.ai.baas.amc.service.business.interfaces.ICashSimulateBusiSV;
import com.ai.baas.amc.util.DateUtils;
import com.ai.baas.amc.vo.*;
import com.ai.opt.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 现金业务模拟流程
 */
@Service
@Transactional
public class CashSimulateBusiSVImpl implements ICashSimulateBusiSV {
    private static Logger logger = LoggerFactory.getLogger(CashSimulateBusiSVImpl.class);
    private BalanceQueryRequest owner;
    //欠费汇总
    private AmcOweInfo amcOweInfo;
    private CashAccountFeeFund accountFeeFund;
    private CycleVo cycle;
    @Autowired
    public IAmcOweInfoAtomSV amcOweInfoAtomSV;
    @Autowired
    public IUserAtomSV userBusiSV;
    @Autowired
    public IAmcInvoiceAtomSV amcInvoiceAtomSV;
    @Autowired
    public IAmcChargeAtomSV amcChargeAtomSV;
    @Autowired
    public IAmcFundBookAtomSV amcFundBookAtomSV;

    @Autowired
    public IDeductRuleAtomSV deductRuleAtomSV;
    @Autowired
    public ISubjectAtomSV subjectAtomSV;

    @Override
    public Boolean init(BalanceQueryRequest owner, double extbalance) throws BusinessException {
        this.owner = getOwner(owner);
        //获取欠费总汇
        AmcOweInfoKey amcOweInfoKey = new AmcOweInfo();
        amcOweInfoKey.setAcctId(this.owner.getOwerId());
        amcOweInfoKey.setTenantId(this.owner.getTenantId());
        amcOweInfo = amcOweInfoAtomSV.selectByInfoKey(amcOweInfoKey);
        if (amcOweInfo==null){
            throw new BusinessException("","模拟冲销未获取到欠费总汇,属主id:"+owner.getOwerId()
                    +",租户id:"+owner.getTenantId());
        }
        //根据欠费汇总信息的账单确认时间获取账期
        cycle = new CycleVo(amcOweInfo);
        accountFeeFund = new CashAccountFeeFund(this.owner,cycle,extbalance);
        accountFeeFund.setAmcOweInfo(amcOweInfo);
        return true;
    }

    @Override
    public VdRealTimeBalance process() throws BusinessException {
    	logger.debug("开始模拟销账处理");
        //账单汇总
        getAccInvoice(cycle,amcOweInfo.getMonth());
        //账单明细
        getOwnCharge(cycle,amcOweInfo.getMonth());
        //账本
        getFundBook(amcOweInfo.getMonth());
        //实时费用,从账单表和账单明细表中获取
        getRealCharge(cycle);
        return accountFeeFund.process();
    }

    /**
     * 获取账单总汇
     *
     * @param cycle 账期信息
     * @param unsettledMonths 最后未销账月
     * @throws BusinessException
     */
    private void getAccInvoice(CycleVo cycle,String unsettledMonths) throws BusinessException{
        String fromMonth = unsettledMonths;
        String toMonth = cycle.getLastCycle();
        if (cycle.getTwoMonthsReal()) {
            toMonth = cycle.getLast2Cycle();
        }

        while (fromMonth.compareTo(toMonth) <= 0) {
            List<AmcInvoice> accInvoices = amcInvoiceAtomSV.query(
                    owner.getOwerId(),owner.getTenantId(), fromMonth);
            logger.info("获取amc_invoice：" + fromMonth + "," + accInvoices.size());
            if ((accInvoices != null) && (!accInvoices.isEmpty())) {
                for (AmcInvoice accInvoice : accInvoices) {
                	logger.info(accInvoice.toString());
                    accountFeeFund.addSummary(fromMonth, accInvoice);
                }
            }
            fromMonth = DateUtils.monthsAdd(fromMonth, 1);
        }
    }

    /**
     * 汇总账单明细
     *
     * @param cycle 账期
     * @param unsettledMonths
     * @throws BusinessException
     */
    private void getOwnCharge(CycleVo cycle, String unsettledMonths) throws BusinessException {
        String fromMonth = unsettledMonths;
        String toMonth = cycle.getLastCycle();
        if (cycle.getTwoMonthsReal()) {
            toMonth = cycle.getLast2Cycle();
        }
        while (fromMonth.compareTo(toMonth) <= 0) {
            List<AmcCharge> charges = amcChargeAtomSV.query(
                    owner.getOwerId(), owner.getTenantId(), fromMonth);
            logger.info("amcchare:" + fromMonth + "," + charges.size());
            for (int i = 0; charges != null && i < charges.size(); i++) {
                AmcCharge charge = charges.get(i);
                logger.info(charge.toString());
                AmcChargeOrderExt chargeOrderExt = new AmcChargeOrderExt();
                BeanUtils.copyProperties(charge, chargeOrderExt);
                //获取科目的消费定义
                logger.info("" + charge.getSubjectId());
                SubjectFeeVo subjectFeeVo = subjectAtomSV.getFeeBySubjectId(charge.getSubjectId());
                if (subjectFeeVo != null){
                    chargeOrderExt.setFeeSettlePri(subjectFeeVo.getSettlePri());}
                accountFeeFund.addDetail(fromMonth, chargeOrderExt);
            }
            fromMonth = DateUtils.monthsAdd(fromMonth, 1);
        }
    }

    /**
     * 获取账户余额,账本类型，结算关系
     * @Title: getFundBook
     * @Description:
     * @param @return    设定文件
     * @return FundInfo    返回类型
     * @throws
     */
    private void getFundBook(String unsettledMonths) {
        Map<Long, SubjectFundVo> subjectFundMap = new HashMap<Long, SubjectFundVo>();
        Map<Long, List<DeductRuleVo>> settleRuelMap = new HashMap<Long, List<DeductRuleVo>>();

        List<AmcFundBook> fundBookList = amcFundBookAtomSV.selectByTenantAndAccountAfterExpire(
                owner.getTenantId(), owner.getOwerId(),DateUtils.getSartOfMonth(unsettledMonths));
        //根据租户id和账户id,查询所有账本记录
        for (int i = 0; fundBookList != null && i < fundBookList.size(); i++) {
            AmcFundBook fundBook = fundBookList.get(i);
            //资金科目id
            Long subjectfundid = fundBook.getSubjectId();
            SubjectFundVo subjectfund = subjectAtomSV.getFundByTenantAndSubject(owner.getTenantId(),subjectfundid);
            if (subjectfund == null) {
                throw new BusinessException("getSubjectFund",
                        "没取到对应账本信息 Tenantid:" + owner.getTenantId()
                                + "subjectfundid:" + Long.toString(subjectfundid));
            }
            if (subjectFundMap.get(subjectfund.getSubjectId()) != null){
                continue;}

            subjectFundMap.put(subjectfund.getSubjectId(), subjectfund);
            //如果是专用资金科目且未查询过对应销账规则,则查询对应的销账规则
            if ("0".equals(subjectfund.getCanSettleAll())
                    && !settleRuelMap.containsKey(subjectfund.getSubjectId())) {
                //从内存中查询销账规则
                List<DeductRuleVo> settleRules = deductRuleAtomSV.query(
                        owner.getTenantId(),subjectfund.getSubjectId());
                if (settleRules == null || settleRules.isEmpty()) {
                    throw new BusinessException("getSubjectFund",
                            "没取到销账关系信息 Tenantid:" + owner.getTenantId()
                                    + "subjectfundid:" + Long.toString(subjectfund.getSubjectId()));
                }
                settleRuelMap.put(subjectfund.getSubjectId(), settleRules);
            }
        }
        accountFeeFund.addFundinfo(fundBookList, subjectFundMap, settleRuelMap);
    }

    /**
     * 获取实时费用
     *
     * @param cycle
     */
    private void getRealCharge(CycleVo cycle) {
        //查询当前账期的账单明细
        List<AmcCharge> realCharges = amcChargeAtomSV.query(
                owner.getOwerId(),owner.getTenantId(),cycle.getCurrCycle());
        if (realCharges==null){
            realCharges = new ArrayList<AmcCharge>();
        }
        if (cycle.getTwoMonthsReal()) {
            List<AmcCharge> realCharges2 = amcChargeAtomSV.query(
                    owner.getOwerId(),owner.getTenantId(),cycle.getLastCycle());
            if ((realCharges2 != null) && (!realCharges2.isEmpty())) {
                realCharges.addAll(realCharges2);
            }
        }

        if (realCharges.isEmpty()){
            return;}

        for (AmcCharge amcCharge:realCharges) {
            AmcChargeOrderExt chargeOrderExt = new AmcChargeOrderExt();
            BeanUtils.copyProperties(amcCharge, chargeOrderExt);
            //获取科目的消费定义
            SubjectFeeVo subjectFeeVo = subjectAtomSV.getFeeBySubjectId(amcCharge.getSubjectId());
            if (subjectFeeVo!=null){
                chargeOrderExt.setFeeSettlePri(subjectFeeVo.getSettlePri());}
            accountFeeFund.addRealCharge(cycle.getCurrCycle(), chargeOrderExt);
        }

    }

    /**
     * 将属主变更为账户类型
     * @param omcObj
     * @return
     */
    private BalanceQueryRequest getOwner(BalanceQueryRequest omcObj){
        if (OwnerType.ACC_TYPE.equals(omcObj.getOwerType())){
            return omcObj;
        //如果是用户类型,则需要获取账户id
        }else if (OwnerType.USER_TYPE.equals(omcObj.getOwerType())){
            UserVo userVo = userBusiSV.selectById(omcObj.getTenantId(),omcObj.getOwerId());
            BalanceQueryRequest accOmc = new BalanceQueryRequest();
            BeanUtils.copyProperties(omcObj, accOmc);
            accOmc.setOwerType(OwnerType.ACC_TYPE);
            accOmc.setOwerId(userVo.getAccountid());
            return accOmc;
        //不支持属主类型
        }else {
            throw new BusinessException("","不支持的属主类型:"+omcObj.getOwerType());
        }
    }
}
