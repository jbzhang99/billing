package com.ai.baas.amc.service.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoKey;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcInvoiceYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcOweInfoAtomSV;
import com.ai.baas.amc.service.business.interfaces.ITotalBillBusiSV;
import com.ai.baas.amc.util.AdapterUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.util.StringUtil;
@Service
@Transactional
public class TotalBillBusiSVImpl implements ITotalBillBusiSV {

    private static Logger LOG = LoggerFactory.getLogger(TotalBillBusiSVImpl.class);
    @Autowired
    IAmcChargeYyyyddAtomSV amcChargeAtomSV;
    @Autowired
    IAmcInvoiceYyyyddAtomSV amcInvoiceAtomSV;
    @Autowired
    IAmcOweInfoAtomSV amcOweInfoAtomSV;
    @Override
    public void totalBill(String tenantId, String billMonth) {
        LOG.info("租户["+tenantId+"]集团补贴账单生成开始......");
        long totalBillLocal = amcChargeAtomSV.queryChargeSum(tenantId, billMonth,AmcConstants.TotalBill.ACCT_ID);
        long totalDrBillLocal = amcChargeAtomSV.queryChargeDrSum(tenantId, billMonth,AmcConstants.TotalBill.ACCT_ID);
        long totalBillRemote = this.queryRemoteTotalBill(tenantId, billMonth);
        long jtDrTotalBill = totalBillRemote-totalDrBillLocal;
        long jtTotalBill = totalBillRemote-totalBillLocal;
        LOG.info("["+billMonth+"]月产生总账单：["+totalBillLocal+"]；供应商总账单：["+totalBillRemote+"]；集团账单：["+jtTotalBill+"]");
        if(jtTotalBill>0){
            LOG.info("生成charge数据");
            //this.saveOrUpdateChargeBean(AmcConstants.TotalBill.ACCT_ID,AmcConstants.TotalBill.CUST_ID,jtTotalBill, tenantId, billMonth,AmcConstants.TotalBill.SUBJECT_ID);
            this.saveOrUpdateChargeBean(AmcConstants.TotalBill.ACCT_ID,AmcConstants.TotalBill.CUST_ID,jtTotalBill, jtDrTotalBill, tenantId, billMonth,AmcConstants.TotalBill.SUBJECT_ID);
            LOG.info("生成invoice数据");
            this.saveOrUpdateInvoiceBean(AmcConstants.TotalBill.ACCT_ID, tenantId, AmcConstants.TotalBill.CUST_ID, jtTotalBill, billMonth);
            LOG.info("生成oweinfo数据");
            this.updateOwnInfo(AmcConstants.TotalBill.ACCT_ID, tenantId, jtTotalBill);
            LOG.info("总账单结束");
        }
        
        
    }
    /**
     * 查询远程总账接口
     * @param tenantId
     * @param billMonth
     * @return
     * @author LiangMeng
     */
    private long queryRemoteTotalBill(String tenantId, String billMonth){
        String totalBill = AdapterUtil.queryAliTotalBill();
        long totalBillLong = 0;
        if(!StringUtil.isBlank(totalBill)){
            totalBillLong = Long.parseLong(totalBill);
        }
        return totalBillLong;
    }
    
    /**
     * 初始化
     * @param amcChargeBean
     * @param data
     * @param fee
     * @param subjectId
     * @author LiangMeng
     */
//    private void saveOrUpdateChargeBean(String acctId ,long custId,long fee,String tenantId,String billMonth,long subjectId) {
    private void saveOrUpdateChargeBean(String acctId ,long custId,long fee, long drFee, String tenantId,String billMonth,long subjectId) {
        AmcChargeYyyydd amcChargeBean = amcChargeAtomSV.queryChargeBySubjectId(acctId, tenantId, billMonth, subjectId+"");
        
        if (amcChargeBean ==null||amcChargeBean.getChargeSeq() == null || amcChargeBean.getChargeSeq() == 0) {
            amcChargeBean = new AmcChargeYyyydd();
            String chargeSeq = SeqUtil
                    .getNewId(AmcConstants.SeqName.AMC_CHARGE$SERIAL_CODE$SEQ, 10);
            amcChargeBean.setBillMonth(billMonth);
            amcChargeBean.setTenantId(tenantId);
            amcChargeBean.setAcctId(acctId);
            amcChargeBean.setSubjectId(subjectId);
            amcChargeBean.setCustId(custId);
            amcChargeBean.setDiscTotalAmount(0l);
            amcChargeBean.setAdjustAfterwards(0l);
            amcChargeBean.setLastPayDate(new Timestamp(new Date().getTime()));
            amcChargeBean.setPayStatus(1l);
            amcChargeBean.setChargeSeq(Long.parseLong(chargeSeq));
            amcChargeBean.setBalance(fee);
            amcChargeBean.setTotalAmount(fee);
            amcChargeBean.setDrTotalAmount(drFee);
            amcChargeAtomSV.addAmcCharge(amcChargeBean);
        }else{
            amcChargeBean.setBalance(fee);
            amcChargeBean.setTotalAmount(fee);
            amcChargeBean.setDrTotalAmount(drFee);
            amcChargeBean.setBillMonth(billMonth);
            amcChargeAtomSV.updateAmcCharge(amcChargeBean);
        }
        
    }
    /**
     * 更新账单总表
     * @param acctId
     * @param tenantId
     * @param custId
     * @param fee
     * @param billMonth
     * @author LiangMeng
     */
    private void saveOrUpdateInvoiceBean(String acctId,String tenantId,long custId, long fee,String billMonth) {
        List<AmcInvoiceYyyydd> amcinvoiceBeans = amcInvoiceAtomSV.queryInvoiceByAcctId(acctId, tenantId, billMonth);
        AmcInvoiceYyyydd amcinvoiceBean;
        if(CollectionUtil.isEmpty(amcinvoiceBeans)){
            String invoiceSeq = SeqUtil
                    .getNewId(AmcConstants.SeqName.AMC_INVOICE$SERIAL_CODE$SEQ, 10);
            amcinvoiceBean = new AmcInvoiceYyyydd();
            amcinvoiceBean.setAcctId(acctId);
            amcinvoiceBean.setCustId(custId);
            amcinvoiceBean.setAdjustAfterwards(0l);
            amcinvoiceBean.setBalance(fee);
            amcinvoiceBean.setBillMonth(billMonth);
            amcinvoiceBean.setDiscTotalAmount(0l);
            amcinvoiceBean.setInvoiceSeq(Long.parseLong(invoiceSeq));
            amcinvoiceBean.setLastPayDate(new Timestamp(System.currentTimeMillis()));
            amcinvoiceBean.setPayStatus(0l);
            amcinvoiceBean.setPrintTimes(0l);
            amcinvoiceBean.setTenantId(tenantId);
            amcinvoiceBean.setTotalAmount(fee);
            amcInvoiceAtomSV.addAmcInvoice(amcinvoiceBean);
        }else if(amcinvoiceBeans.size()>1){
            throw new BusinessException(ExceptCodeConstants.Special.NO_RESULT, "账单总表获取异常，大于1条");
        }else{
            amcinvoiceBean = amcinvoiceBeans.get(0);
            amcinvoiceBean.setBillMonth(billMonth);
            amcinvoiceBean.setBalance(fee);
            amcinvoiceBean.setTotalAmount(fee);
            amcInvoiceAtomSV.updateAmcInvoice(amcinvoiceBean);
        }        
    }
    /**
     * 更新欠费表
     * @param acctId
     * @param tenantId
     * @param fee
     * @param billMonth
     * @author LiangMeng
     */
    private void updateOwnInfo(String acctId,String tenantId, long fee){
        AmcOweInfoKey oweInfoKey = new AmcOweInfoKey();
        oweInfoKey.setAcctId(acctId);
        oweInfoKey.setTenantId(tenantId);
        AmcOweInfo oweInfo = amcOweInfoAtomSV.selectByInfoKey(oweInfoKey);
        if(oweInfo==null){
            throw new BusinessException(ExceptCodeConstants.Special.NO_RESULT, "欠费信息异常");
        }
        oweInfo.setBalance(BigDecimal.valueOf(fee));
        amcOweInfoAtomSV.updateOweInfo(oweInfo);
    }

}
