package com.ai.baas.amc.service.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.fundquery.param.FundBook;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoKey;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleLog;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcInvoiceYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcOweInfoAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcSettleDetailAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcSettleLogAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IDeductAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IDeductRuleAtomSV;
import com.ai.baas.amc.service.business.interfaces.IFundQueryBusiSV;
import com.ai.baas.amc.service.business.interfaces.IWriteOffBusiSV;
import com.ai.baas.amc.util.AmountUtil;
import com.ai.baas.amc.util.DateUtils;
import com.ai.baas.amc.vo.DeductRuleVo;
import com.ai.baas.amc.vo.DeductVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.CollectionUtil;
@Service
@Transactional
public class WriteOffSVImpl implements IWriteOffBusiSV {
    private static Logger LOG = LoggerFactory.getLogger(WriteOffSVImpl.class);
    @Autowired
    private IFundQueryBusiSV fundQueryBusiSV;
    @Autowired
    private IAmcOweInfoAtomSV amcOweInfoAtomSV;
    @Autowired
    private IDeductAtomSV deductAtomSV;
    @Autowired
    IAmcChargeYyyyddAtomSV amcChargeYyyyddAtomSV;
    @Autowired
    IAmcInvoiceYyyyddAtomSV amcInvoiceYyyyddAtomSV;
    @Autowired
    IDeductRuleAtomSV deductRuleAtomSV;
    @Autowired
    IAmcSettleDetailAtomSV amcSettleDetailAtomSV;
    @Autowired
    IAmcSettleLogAtomSV amcSettleLogAtomSV;
    @Override
    public int writeCore(String acctId, String tenantId) {
        /* 1.查询账本列表 */
        try {
            LOG.info("销账业务处理开始...");
            long total =0;
            FundBookQueryRequest fundBookQueryRequest = new FundBookQueryRequest();
            fundBookQueryRequest.setAccountId(acctId);
            fundBookQueryRequest.setTenantId(tenantId);
            FundBookQueryResponse fundBookQueryResponse = fundQueryBusiSV.queryFund(fundBookQueryRequest);
            List<FundBook> fundBooks = fundBookQueryResponse.getFundBooks();
            if(CollectionUtil.isEmpty(fundBooks)){
                LOG.info("没有查询到账本:tenant_id：["+tenantId+"];acct_id:["+acctId+"]");
            }
            /* 2.查询可销账月份 */
            AmcOweInfoKey amcOweInfoKey = new AmcOweInfoKey();
            amcOweInfoKey.setAcctId(acctId);
            amcOweInfoKey.setTenantId(tenantId);
            AmcOweInfo amcOweInfo = amcOweInfoAtomSV.selectByInfoKey(amcOweInfoKey);
            String lastUnsettleMonth = amcOweInfo.getMonth();
            List<Map<String,Object>> monthList = DateUtils.getPerMonth(lastUnsettleMonth);
            if(CollectionUtil.isEmpty(monthList)){
                LOG.info("未获取到最后未销账月份:tenant_id：["+tenantId+"];acct_id:["+acctId+"]");
                throw new BusinessException(ExceptCodeConstants.OweInfo.OWE_INFO_NOT_FOUND, "未获取到最后未销账月份");
            }
            /* 3.一层寻缘遍历账本列表，先处理专款 */
            T1:for (FundBook fundBook : fundBooks) {
                Timestamp effectDate = fundBook.getEffectDate();// 失效日期
                Timestamp expireDate = fundBook.getExpireDate();// 生效日期
                Long subjectId = fundBook.getSubjectId();
                Long bookId = fundBook.getBookId();
                
                Long subsId = fundBook.getSubsId();
                long fundBookBalance = fundBook.getBalance();
                
                /*查询*/
                List<DeductRuleVo> settleRules = deductRuleAtomSV.query(
                        tenantId,subjectId);
                if (CollectionUtil.isEmpty(settleRules)) {
                    throw new BusinessException("getSubjectFund",
                            "没取到销账关系信息 Tenantid:" + tenantId
                                    + "subjectfundid:" + Long.toString(subjectId));
                }
                T2:for (Map<String, Object> map : monthList) {
                    long perMonthTotal = 0;
                    String billMonth = (String) map.get("yyyyMM");
                    Integer effectMonth = Integer.parseInt(new SimpleDateFormat("yyyyMM")
                            .format(effectDate.getTime()));
                    Integer expireMonth = Integer.parseInt(new SimpleDateFormat("yyyyMM")
                            .format(expireDate.getTime()));
                    Integer billMonthInt = Integer.parseInt(billMonth);
                    if (billMonthInt < effectMonth || effectMonth > expireMonth) {
                        LOG.info("账本[" + bookId + "]不可销["+billMonthInt+"]账期的账");
                        continue T2;
                    } else {
                        LOG.info("账本[" + bookId + "]可销["+billMonthInt+"]账期的账");
                        /* 查询账单列表 */
                        List<AmcChargeYyyydd> chargeList = amcChargeYyyyddAtomSV.queryChargeByAcctId(acctId, tenantId, billMonth);
                        if(CollectionUtil.isEmpty(chargeList)){
                            LOG.info("账单查询为空：acctId["+acctId+"],tenantId["+tenantId+"],billMonth["+billMonth+"]");
                        }else{
                            T3:for (AmcChargeYyyydd chargeBean : chargeList) {
                                long chargeBalance = chargeBean.getBalance();
                                String feeSubject = chargeBean.getSubjectId() + "";
                                //add by hzf 20170327 看能否相同subs_id
                                if(!(subsId.longValue() == 0) &&  subsId.longValue() != chargeBean.getSubsId().longValue())
                                {
                                	continue;
                                }
                                
                                LOG.info("获取到的规则有：["+settleRules.size()+"]条");
                                for(DeductRuleVo vo :settleRules){
                                    LOG.info("账单科目：["+feeSubject+"]，规则科目：["+vo.getFeeSubject()+"]");
                                    if (feeSubject.equals(vo.getFeeSubject()+"")) {
                                        LOG.info("开始销科目["+feeSubject+"]的账");
                                        LOG.info("账本["+subjectId+"]金额为["+fundBookBalance+"]");
                                        if (fundBookBalance == 0) {
                                            /* 3.4.1 账本余额为0，则什么都不处理 */
                                            LOG.info("账本["+subjectId+"]金额为0，继续遍历账本");
                                            /*更新账单总表*/
                                            if(perMonthTotal>0){
                                                this.updateInvoiceInfo(tenantId, acctId, perMonthTotal, billMonth);
                                            }
                                            continue T1;
                                        }
                                        if (chargeBalance == 0) {
                                            /* 3.4.1 账本余额为0，则什么都不处理 */
                                            LOG.info("账单["+feeSubject+"]金额为0，继续遍历账单");
                                            continue T3;
                                        }
                                        /* 扣减账本 ，处理账单*/
                                        DeductVo deductVo = new DeductVo();
                                        deductVo.setAccountId(acctId);

                                        NumberFormat nf=NumberFormat.getInstance();
                                        nf.setGroupingUsed(false);
                                        if (fundBookBalance >= chargeBalance) {
                                            LOG.info("销账金额：["+chargeBalance+"]");
                                            total += chargeBalance;
                                            perMonthTotal += chargeBalance;
                                            deductAtomSV.deductFundBook(acctId, bookId, chargeBalance);
                                            deductVo.setBusiDesc("销账金额：["+nf.format(AmountUtil.changeLiToYuan(chargeBalance))+"]");
                                            deductVo.setTotalAmount(chargeBalance);
                                            deductVo.addTransSummary(bookId, subjectId, chargeBalance);
                                            chargeBean.setBalance(chargeBean.getBalance()-chargeBalance);
                                            chargeBean.setBillMonth(billMonth);
                                            chargeBean.setPayStatus(1l);//全部销账
                                            amcChargeYyyyddAtomSV.updateAmcCharge(chargeBean);
                                            this.saveSettleDetail(fundBook, chargeBean, chargeBalance);
                                            fundBookBalance = fundBookBalance - chargeBalance;
                                            chargeBalance = 0;
                                        }else{
                                            LOG.info("销账金额：["+fundBookBalance+"]");
                                            total += fundBookBalance;
                                            perMonthTotal += fundBookBalance;
                                            deductAtomSV.deductFundBook(acctId, bookId, fundBookBalance);

                                            deductVo.setBusiDesc("销账金额：["+nf.format(AmountUtil.changeLiToYuan(fundBookBalance))+"]");
                                            deductVo.setTotalAmount(fundBookBalance);
                                            deductVo.addTransSummary(bookId, subjectId, fundBookBalance);
                                            chargeBean.setBalance(chargeBean.getBalance()-fundBookBalance);
                                            chargeBean.setBillMonth(billMonth);
                                            chargeBean.setPayStatus(2l);//部分销账
                                            amcChargeYyyyddAtomSV.updateAmcCharge(chargeBean);
                                            this.saveSettleDetail(fundBook, chargeBean, fundBookBalance);
                                            //如果未销完，则最后未销账日期置为当前未销完月
                                            lastUnsettleMonth = billMonth;
                                            chargeBalance = chargeBalance - fundBookBalance;
                                            fundBookBalance = 0;
                                        }
                                        String paySerialCode = SeqUtil.getNewId(AmcConstants.SeqName.AMC_FUND_SERIAL$PAY_SERIAL_CODE$SEQ,10);
                                        deductVo.setPaySerialCode(paySerialCode);
                                        deductVo.setTenantId(tenantId);
                                        deductVo.setSystemId(paySerialCode);
                                        deductVo.setExternalId(chargeBean.getChargeSeq()+"");
                                        String paySerialCodeReturn = deductAtomSV.savePaySerial(deductVo);
                                        LOG.info("扣款成功，返回扣款流水号：" + paySerialCodeReturn);
                                        
                                    }
                                }
                            }
                        }
                        LOG.info("账期["+billMonth+"]销账总额为:["+perMonthTotal+"]");
                        /*更新账单总表*/
                        if(perMonthTotal>0){
                            this.updateInvoiceInfo(tenantId, acctId, perMonthTotal, billMonth);
                        }
                    }
                }
            }
            /*4.保存销账记录，欠费表*/
            if(total>0){
                String nowMonth = new SimpleDateFormat("yyyyMM").format(new Date());
                /*保存销账记录*/
                this.saveSettleLog(acctId, nowMonth, tenantId, total);                
                /*更新欠费总表*/
                this.updateOweInfo(amcOweInfo,tenantId, acctId, total, lastUnsettleMonth);
            }
        } catch (BusinessException e) {
            LOG.error("销账错误：["+e.getErrorMessage()+"]",e);
            throw e;
        } catch (SystemException e) {
            LOG.error("销账错误：["+e.getErrorMessage()+"]",e);
            throw e;
        } catch (Exception e) {
            LOG.error("销账错误：["+e.getMessage()+"]",e);
            throw new SystemException("销账错误：["+e.getMessage()+"]",e);
        }
        return 0;
    }
    /**
     * 保存销账记录
     * @param fundBook
     * @param chargeBean
     * @param totalAmount
     * @author LiangMeng
     */
    private void saveSettleLog(String acctId,String billMonth,String tenantId,long total){
        AmcSettleLog settleLog = new AmcSettleLog();
        String serialCode = SeqUtil.getNewId(AmcConstants.SeqName.AMC_SETTLE_LOG$SERIAL_CODE$SEQ,10);
        
        settleLog.setAcctId(acctId);
        settleLog.setBillMonth(billMonth);
        settleLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        settleLog.setLastStatusDate(new Timestamp(System.currentTimeMillis()));
        settleLog.setSerialCode(serialCode);
        settleLog.setSettleMode(AmcConstants.AmcSettleLog.SettleMode.PTXZ);
        settleLog.setSettleType(AmcConstants.AmcSettleLog.SettleType.XZ);
        settleLog.setStatus(AmcConstants.AmcSettleLog.Status.XZ);
        settleLog.setTenantId(tenantId);
        settleLog.setTotal(total);
        amcSettleLogAtomSV.addSettleLog(settleLog);
    }
    /**
     * 保存销账明细
     * @param fundBook
     * @param chargeBean
     * @param totalAmount
     * @author LiangMeng
     */
    private void saveSettleDetail(FundBook fundBook,AmcChargeYyyydd chargeBean,Long totalAmount){
        AmcSettleDetail detail = new AmcSettleDetail();
        String serialCode = SeqUtil.getNewId(AmcConstants.SeqName.AMC_SETTLE_DETAIL$SERIAL_CODE$SEQ,10);
        
        detail.setAcctId(Long.parseLong(chargeBean.getAcctId()));
        detail.setBillMonth(chargeBean.getBillMonth());
        detail.setBookId(fundBook.getBookId());
        detail.setChargeSeq(chargeBean.getChargeSeq());
        detail.setCreateTime(new Timestamp(System.currentTimeMillis()));
        detail.setCycleMonth(chargeBean.getBillMonth());
        detail.setFeeSubjectId(chargeBean.getSubjectId());
        detail.setFundSubjectId(fundBook.getSubjectId());
        detail.setLastStatusDate(new Timestamp(System.currentTimeMillis()));
        detail.setSerialCode(serialCode);
        detail.setSettleMode(AmcConstants.AmcSettleLog.SettleMode.PTXZ);
        detail.setSettleOrder(0l);
        detail.setSettleType(AmcConstants.AmcSettleLog.SettleType.XZ);
        detail.setStatus(AmcConstants.AmcSettleLog.Status.XZ);
        detail.setSubsId(chargeBean.getSubsId());
        detail.setTenantId(chargeBean.getTenantId());
        detail.setTotal(totalAmount);
        amcSettleDetailAtomSV.addSettleDetail(detail);
    }
    /**
     * 
     * @param tenantId
     * @param acctId
     * @param balance
     * @param billMonth
     * @author LiangMeng
     */
    private void updateOweInfo(AmcOweInfo oweInfo,String tenantId, String acctId, long fee,String billMonth){
        oweInfo.setMonth(billMonth);
        BigDecimal balance = oweInfo.getBalance();
        LOG.info("欠费更新前：["+balance+"]");
        oweInfo.setBalance(balance.subtract(BigDecimal.valueOf(fee)));
        LOG.info("欠费更新后：["+oweInfo.getBalance()+"]");
        amcOweInfoAtomSV.updateOweInfo(oweInfo);
    }
    /**
     * 更新账单总表
     * @param tenantId
     * @param acctId
     * @param balance
     * @param billMonth
     * @author LiangMeng
     */
    private void updateInvoiceInfo(String tenantId, String acctId, long balance,String billMonth){
        
        List<AmcInvoiceYyyydd> invoices =amcInvoiceYyyyddAtomSV.queryInvoiceByAcctId(acctId, tenantId, billMonth);
        AmcInvoiceYyyydd invoice;
        if(CollectionUtil.isEmpty(invoices)){
            throw new BusinessException(com.ai.opt.sdk.constants.ExceptCodeConstants.Special.NO_RESULT, "账单总表获取失败");
        }else{
            invoice = invoices.get(0);
        }
        if(invoice.getBalance()==balance){
            invoice.setPayStatus(1l);
        }else{
            invoice.setPayStatus(2l);
        }
        invoice.setBalance(invoice.getBalance()-balance);
        invoice.setBillMonth(billMonth);
        amcInvoiceYyyyddAtomSV.updateAmcInvoice(invoice);
    }
}
