package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.baas.amc.constants.FeeSource;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoice;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.service.business.interfaces.IAccountFeeFundBusiSV;
import com.ai.baas.amc.util.CalUtil;
import com.ai.baas.amc.util.DateUtils;
import com.ai.baas.amc.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 * 现金模拟销账流程
 * Created by jackieliu on 16/3/30.
 */
public class CashAccountFeeFund implements IAccountFeeFundBusiSV {
    private static Logger logger = LoggerFactory.getLogger(CashAccountFeeFund.class);
    private BalanceQueryRequest owner;
    //账单明细 <月份,<科目id,账单明细list>> 账单明细list是按照消费科目优先级已经排序
    private SortedMap<String,SortedMap<Long,List<AmcChargeOrderExt>>> ownDetailMap = new TreeMap<String,SortedMap<Long,List<AmcChargeOrderExt>>>();
    //账单汇总 <月份,账单信息list>
    private SortedMap<String,List<AmcInvoice>> ownsummaryMap = new TreeMap<String,List<AmcInvoice>>();
    //实时费用 <月份,账单明细> 账单明细list是按照消费科目优先级已经排序
    private SortedMap<String,List<AmcChargeOrderExt>> realChargesMap =  new TreeMap<String,List<AmcChargeOrderExt>>();
    //账本科目
    private Map<Long, SubjectFundVo> subjectFundMap;
    //抵扣关系
    private Map<Long,List<DeductRuleVo>> fundSettleRuleMap;
    //账本集合
//    private List<AmcFundBook> fundBookList;
    private List<Fund> fundList;
    //
    private CycleVo cycle;
    //账户未销账总述
    private AmcOweInfo amcOweInfo;
    //抵扣前情况
    private VdRealTimeBalance realTimeBalanceBeforeDecuct;
    //抵扣后情况
    private VdRealTimeBalance realTimeBalanceAfterDecuct;
    //外部费用
    private BigDecimal extBalance;

    public CashAccountFeeFund(BalanceQueryRequest owner, CycleVo cyclepara, Double extbalance){
        this.owner = owner;
        this.cycle = cyclepara;
        this.extBalance = CalUtil.BigDecimalFromDouble(extbalance, FeeSource.FROMCHARGE);
    }
    @Override
    public VdRealTimeBalance process() {
        //抵扣前费用
        realTimeBalanceBeforeDecuct = caclRealInfo(false);
        logger.info("Ower:["+owner.toString() + "]虚扣前账本费用情况\r"+ realTimeBalanceBeforeDecuct.toString());
        //抵扣欠费
        processOwn();
        //抵扣实时费用
        processRealCharge();
        //抵扣后情况
        realTimeBalanceAfterDecuct = caclRealInfo(true);
        logger.info("Ower:["+owner.toString() + "]虚扣后账本费用情况\r"+ realTimeBalanceAfterDecuct.toString());
        return realTimeBalanceAfterDecuct;
    }

    public void setAmcOweInfo(AmcOweInfo amcOweInfo){
        this.amcOweInfo = amcOweInfo;
    }


    /**
     * 累计账单信息
     * @param acctmonth
     * @param accInvoice
     */
    public void addSummary(String acctmonth,AmcInvoice accInvoice){
        List<AmcInvoice> accInvoices = ownsummaryMap.get(acctmonth);
        if (accInvoices == null){
            accInvoices = new ArrayList<AmcInvoice>();
            accInvoices.add(accInvoice);
            ownsummaryMap.put(acctmonth, accInvoices);
        }else{
            accInvoices.add(accInvoice);
        }
    }

    /**
     * 累计账单明细信息
     * @param acctmonth
     * @param charge
     */
    public void addDetail(String acctmonth,AmcChargeOrderExt charge){
        SortedMap<Long,List<AmcChargeOrderExt>>  cMap = ownDetailMap.get(acctmonth);
        if (cMap == null){
            cMap = new TreeMap<Long,List<AmcChargeOrderExt>>();
            List<AmcChargeOrderExt> charges = new ArrayList<AmcChargeOrderExt>();
            charges.add(charge);
            cMap.put(charge.getSubsId(), charges);
        }else{
            List<AmcChargeOrderExt> charges = cMap.get(charge.getSubsId());
            if (charges == null){
                charges = new ArrayList<AmcChargeOrderExt>();
                charges.add(charge);
                cMap.put(charge.getSubsId(), charges);
            }else {
                charges.add(charge);
                amcChangeSort(charges);
            }
        }
        ownDetailMap.put(acctmonth, cMap);
    }

    /**
     * 添加实时账单明细
     * @param acctmonth
     * @param charge
     */
    public void addRealCharge(String acctmonth,AmcChargeOrderExt charge){
        List<AmcChargeOrderExt> charges = realChargesMap.get(acctmonth);
        if (charges == null){
            charges = new ArrayList<AmcChargeOrderExt>();
            charges.add(charge);
            realChargesMap.put(acctmonth, charges);
        }else{
            charges.add(charge);
            amcChangeSort(charges);
        }
    }

    /**
     * 添加账本信息
     * @param fundBookList 账本
     * @param subjectFund 资金科目
     * @param funSettleRule 销账规则
     */
    public void addFundinfo(
            List<AmcFundBook> fundBookList, Map<Long, SubjectFundVo> subjectFund,
            Map<Long, List<DeductRuleVo>> funSettleRule){
        List<Fund> funds = new ArrayList<>();
        if (fundBookList!=null&&fundBookList.size()>0){
            for (AmcFundBook fundBook:fundBookList){
                funds.add(convert(fundBook));
            }
        }
//        this.fundBookList = fundBookList;
        this.fundList = funds;
        this.subjectFundMap = subjectFund;
        this.fundSettleRuleMap = funSettleRule;
    }

    /**
     * 对账户余额和费用情况进行汇总
     *
     * @param isProcess 是否完成模拟销账
     * @return RealTimeBalance  返回类型
     * @throws
     */
    private VdRealTimeBalance caclRealInfo(boolean isProcess){
        VdRealTimeBalance realTimeBalance = new VdRealTimeBalance();
        realTimeBalance.setOwner(owner);
        realTimeBalance.setUnIntoBill(extBalance);
        //当前账期
        realTimeBalance.setAcctMonth(cycle.getCurrCycle());
        logger.info("OptAccountFeeFund类的caclRealInfo的accAccountInfo"+amcOweInfo.toString());
        //最早欠费月份
        realTimeBalance.setFstUnsettLemon(amcOweInfo.getMonth());
        //欠费账单
        realTimeBalance.setUnSettleBill(amcOweInfo.getBalance());
        //欠费月数
        realTimeBalance.setUnsettLemons(DateUtils.monthDiffs(amcOweInfo.getMonth(),cycle.getCurrCycle()));
        logger.info("OptAccountFeeFund类的caclRealInfo的fundBooks:"+fundList.size());

        //实时费用求和
        BigDecimal sumRealBill = new BigDecimal("0.00");
        for (String key : realChargesMap.keySet()) {
            List<AmcChargeOrderExt> charges = realChargesMap.get(key);
            if(charges!=null){
            	logger.info("OptAccountFeeFund类的caclRealInfo的AccChargeInfo:" + charges.size());
                for (int i = 0; i < charges.size(); i++) {
                    AmcChargeOrderExt charge = charges.get(i);
                    sumRealBill = sumRealBill.add(CalUtil.BigDecimalFromLong(charge.getBalance(), FeeSource.FROMCHARGE));
                }
            }
        }
        realTimeBalance.setRealBill(sumRealBill);
        //欠费账单求和
        BigDecimal sumUnsettleBill =  new BigDecimal("0.00");
        for (String key : ownsummaryMap.keySet()) {
            List<AmcInvoice> accInvoices = ownsummaryMap.get(key);
            if(accInvoices!=null){
            	logger.info("OptAccountFeeFund类的caclRealInfo的AccInvoiceInfo:" + accInvoices.size());
                for (int i = 0; accInvoices != null && i < accInvoices.size(); i++) {
                    AmcInvoice accInvoice = accInvoices.get(i);
                    logger.info("OptAccountFeeFund类的caclRealInfo的AccInvoiceInfo:" + accInvoice.toString());
                    sumUnsettleBill = sumUnsettleBill.add(CalUtil.BigDecimalFromLong(accInvoice.getBalance(),FeeSource.FROMCHARGE));
                }
            }
        }
        realTimeBalance.setUnSettleBill(sumUnsettleBill);

        //若已冲销且,实时欠费和欠费账单和大于0,则使用欠费和的反数
        BigDecimal totalUnSettle = sumRealBill.add(sumUnsettleBill);
        if (isProcess && totalUnSettle.signum()>0){
            realTimeBalance.setRealBalance(totalUnSettle.negate(MathContext.UNLIMITED));
            realTimeBalance.setBalance(realTimeBalanceBeforeDecuct.getBalance());
        }else {
            //当前余额
            BigDecimal sumBalance = new BigDecimal(0.00,MathContext.UNLIMITED);
            //获得当前账户下账本的总额
            for (Fund fund: fundList) {
                sumBalance = sumBalance.add(fund.getBalance(), MathContext.UNLIMITED);
            }
            realTimeBalance.setRealBalance(CalUtil.BigDecimalFromBigDecimal(sumBalance, FeeSource.FROMCHARGE));
            realTimeBalance.setBalance(
                    isProcess?realTimeBalanceBeforeDecuct.getBalance():realTimeBalance.getRealBalance());
        }
        realTimeBalance.setCreditLine(new BigDecimal(0.0));
        realTimeBalance.setExpandInfo("{}");
        return realTimeBalance;
    }

    /**
     * 抵扣欠费
     */
    private void processOwn(){
        //若没有账本信息,则直接返回
        if (fundList==null || fundList.isEmpty()){
            return;}
        for (Fund fundBook:fundList){ 
            //若账本余额小于等于0,则不处理
            if (fundBook.getBalance().signum()<=0){
                continue;}
            //对欠费进行销账,按月份从小到大 key为月份 格式YYYYMM
            for(String key:ownsummaryMap.keySet()){
                List<AmcInvoice> accInvoices = ownsummaryMap.get(key);
                for (Iterator<AmcInvoice> it = accInvoices.iterator(); it.hasNext();) {
                	
                    AmcInvoice accInvoice = it.next();
                    logger.info("开始抵扣：" + accInvoice.toString());
                    if (accInvoice.getBalance() <= 0){
                        continue;
                    }
                    SortedMap<Long,List<AmcChargeOrderExt>> chargeMap = ownDetailMap.get(key);
                    //不存在明细信息,直接处理下一个
                    if(chargeMap==null || chargeMap.isEmpty()){
                        continue;}
                    //账单明细
                    List<AmcChargeOrderExt> charges = chargeMap.get(accInvoice.getSubsId());
                    logger.info("获取到的 charges：" + charges.size());
                    BigDecimal sumdeduct = new BigDecimal(0.00);
                    for (Iterator<AmcChargeOrderExt> itcharge = charges.iterator(); itcharge.hasNext();) {
                        AmcChargeOrderExt charge = itcharge.next();
                        if (charge.getBalance() == 0){
                            continue;
                        }
                        //对账本明细进行抵扣
                        BwoParaForFundFee bwoParaForFundFee = deduction(fundBook,key,charge);
                        //抵扣成功,则修改账单的余额
                        if (bwoParaForFundFee.getDeducteD()){
//                            BigDecimal total = new BigDecimal(charge.getTotalAmount());
//                            charge.setBalance(total.subtract(bwoParaForFundFee.getDeductecharge()).longValueExact());
//                            fundBook.setBalance(fundBook.getBalance().subtract(bwoParaForFundFee.getDeductecharge()));
//                            BigDecimal invoiceBalance = CalUtil.BigDecimalFromLong(accInvoice.getBalance(),FeeSource.FROMBALANCE);
                            BigDecimal invoiceBalance = new BigDecimal(accInvoice.getBalance());
                            accInvoice.setBalance(invoiceBalance.subtract(bwoParaForFundFee.getDeductecharge()).longValueExact());
                            sumdeduct = sumdeduct.add(bwoParaForFundFee.getDeductecharge());
                        }
                    }
                }
            }
        }
    }

    /**
     * 抵扣实时欠费
     */
    private void processRealCharge(){
        //若没有账本信息,则直接返回
        if (fundList==null || fundList.isEmpty()){
            return;}
        for (Fund fundBook:fundList){
            //若账本余额小于等于0,则不处理
            if (fundBook.getBalance().signum()<=0){
                continue;}
            //先对欠费进行销账
            //按月份从小到大
            for(String key:realChargesMap.keySet()){
                List<AmcChargeOrderExt> charges  = realChargesMap.get(key);
                BigDecimal sumdeduct = new BigDecimal("0.00");
                for (Iterator<AmcChargeOrderExt> itcharge = charges.iterator(); itcharge.hasNext();) {
                    AmcChargeOrderExt charge = itcharge.next();
                    if (charge.getBalance()==0){
                        continue;
                    }
                    //进行抵扣
                    BwoParaForFundFee bwoParaForFundFee = deduction(fundBook,key,charge);
                    if (bwoParaForFundFee.getDeducteD()){
//                        BigDecimal total = new BigDecimal(charge.getTotalAmount());
//                        charge.setBalance(total.subtract(bwoParaForFundFee.getDeductecharge()).longValueExact());
//                        fundBook.setBalance(fundBook.getBalance().subtract(bwoParaForFundFee.getDeductecharge()));
                        sumdeduct = sumdeduct.add(bwoParaForFundFee.getDeductecharge()) ;
                    }
                }
            }
        }
    }

    /**
     * 余额抵扣
     *
     * @param fundBook 账本记录
     * @param currCycle  计算账期 YYYYMM
     * @param charge 账单明细
     * @return BwoParaForFundFee    成功销账后返回销账后的 账本 和 费用，没有销账返回 null
     * @throws
     */
    private BwoParaForFundFee deduction(Fund fundBook,String currCycle, AmcChargeOrderExt charge){
        Boolean isCanUse = true;
        
        logger.debug("销账中：" + fundBook.getSubsId());
        logger.debug("销账中：" + charge.getBillMonth()  + "," + charge.getSubsId() + "," + charge.getBalance()) ;
        if (isCanUse){
            //若账本余额为0或账单明细未销账余额为0,则不需要进行处理
            if ((fundBook.getBalance().signum() == 0)||(charge.getBalance() == 0)){
                isCanUse = false;
            }
        }

        //1.账单明细是否在账本有效范围内
        if (isCanUse){
            if ((DateUtils.format(fundBook.getEffectDate(), "YYYYMM").compareTo(currCycle) > 0)
                    ||(DateUtils.format(fundBook.getExpireDate(), "YYYYMM").compareTo(currCycle) < 0)) {
                isCanUse = false;
            }
        }

        //2.比较用户专款, 账本为指定科目,且账单明细不属于账本指定科目,则不继续执行
        // 账本没有指定科目或账本科目与账本明细科目一直,则继续执行
  
       
        if (isCanUse
                && (!"0".equals(fundBook.getSubsId()))
                && (!charge.getSubsId().equals(Long.parseLong(fundBook.getSubsId())))){
            isCanUse = false;
        }
        //3.比较科目
        if (isCanUse){
            //获取资金科目信息
            SubjectFundVo subjectFund = subjectFundMap.get(fundBook.getSubjectId());
            //资金为专用款
            if ("0".equals(subjectFund.getCanSettleAll())){
                //资金科目对应销账规则
                List<DeductRuleVo> funSettleRules = fundSettleRuleMap.get(fundBook.getSubjectId());
                //若销账规则为空
                if ((funSettleRules == null)||(funSettleRules.isEmpty())){
                    isCanUse = false;
                }else{
                    //判断账单明细科目是否适配资金科目对应的账单科目
                    Boolean bFind = false;
                    for (DeductRuleVo funSettleRule: funSettleRules) {
                        if (charge.getSubjectId().equals(funSettleRule.getFeeSubject())){
                            bFind = true;
                            break;
                        }
                    }
                    //明细科目未对应销账规则中账单科目,则不继续执行
                    if (!bFind){
                        isCanUse = false;
                    }
                }
            }
        }
        //4.余额和费用比较
        BigDecimal decuct = new BigDecimal(0.00);
        Boolean decucted = false;
        if (isCanUse){
            //账单明细费用
            decuct = new BigDecimal(charge.getBalance());
            //进行销账
            //如果账本金额大于等于账单明细金额,则在账本中减去账单明细金额,并将账单明细金额设置为0
            //如果账本金额小于账单明细金额,则将账本设置为0,并将账单明细金额减去账本金额
            if (fundBook.getBalance().compareTo(decuct) >= 0 ){
                fundBook.setBalance(fundBook.getBalance().subtract(decuct));
                charge.setBalance(0l);
            }else{
                charge.setBalance(decuct.subtract(fundBook.getBalance()).longValueExact());
                decuct = fundBook.getBalance();
                fundBook.setBalance(new BigDecimal(0.00));
            }
            decucted = true;
        }
        BwoParaForFundFee bwoParaForFundFee = new BwoParaForFundFee();
        bwoParaForFundFee.setCharge(charge);
        bwoParaForFundFee.setFund(fundBook);
        bwoParaForFundFee.setDeducteD(decucted);
        bwoParaForFundFee.setDeductecharge(decuct);
        return bwoParaForFundFee;
    }

    private Fund convert(AmcFundBook fundBook){
        Fund fund = new Fund();
        //将余额转换为固定精度
        fund.setBalance(CalUtil.formBigDecimalFromLong(fundBook.getBalance(),FeeSource.FROMBALANCE));
        fund.setBookId(Long.toString(fundBook.getBookId()));
        fund.setEffectDate(fundBook.getEffectDate());
        fund.setExpireDate(fundBook.getExpireDate());
        fund.setSubjectId(fundBook.getSubjectId());
        fund.setSubsId(Long.toString(fundBook.getSubsId()));
        return fund;
    }

    /**
     * 对账单明细按照消费科目优先级进行排序,保证高优先级的先处理
     * @param list
     */
    private void amcChangeSort(List<AmcChargeOrderExt> list){
        Collections.sort(list,new Comparator<AmcChargeOrderExt>(){
            @Override
            public int compare(AmcChargeOrderExt o1, AmcChargeOrderExt o2) {
                if (o1.getFeeSettlePri()>o2.getFeeSettlePri()){
                    return -1;
                }else if (o1.getFeeSettlePri()<o2.getFeeSettlePri()){
                    return 1;
                }
                return 0;
            }
        });
    }
}
