package com.ai.baas.amc.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceVo;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoice;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.CustBalanceInfo;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfo;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundBookAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcInvoiceAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcOweInfoAtomSV;
import com.ai.baas.amc.service.business.interfaces.ICustBalanceQueryBusiSV;
import com.ai.baas.amc.util.AmountUtil;
import com.ai.baas.amc.util.CustInfoUtil;
import com.ai.baas.amc.vo.CustInfo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;

/**
 * 可用余额（客户级别）分页查询具体实现类
 *
 * Date: 2016年4月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class CustBalanceQueryBusiSVImpl implements ICustBalanceQueryBusiSV {
    
    private static final Logger LOG = LogManager.getLogger(CustBalanceQueryBusiSVImpl.class);
   
    @Autowired
    private IAmcFundBookAtomSV amcFundBookAtomSV;
    
    @Autowired
    private IAmcOweInfoAtomSV amcOweInfoAtomSV;
    
    @Autowired
    private IAmcInvoiceAtomSV amcInvoiceAtomSV; 

    @Override
    public PageInfo<UsableBalanceVo> queryUsableBalance(UsableBalanceQueryRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        PageInfo<CustBalanceInfo> custBalancePageInfo = amcFundBookAtomSV.queryCustBalanceInfoList(request);
        PageInfo<UsableBalanceVo> pageInfo = new PageInfo<UsableBalanceVo>();
        List<UsableBalanceVo> usableBalanceVoList = new ArrayList<UsableBalanceVo>();
        Timestamp queryTime = DateUtil.getSysDate();
        if(!CollectionUtil.isEmpty(custBalancePageInfo.getResult())) {
            for(CustBalanceInfo custBalanceInfo : custBalancePageInfo.getResult()) {
                String custId = custBalanceInfo.getCustId();
                CustInfo custInfo = CustInfoUtil.getCustInfo(tenantId, custId);
                if(custInfo == null) {
                    throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取不到对应的客户信息[" + custId + "]");
                }
                UsableBalanceVo vo = new UsableBalanceVo();
                vo.setTenantId(tenantId);
                vo.setCustId(custId);
                vo.setCustName(custInfo.getCustName());
                vo.setCustGrade(custInfo.getCustGrade());
                vo.setUsableAmount(this.getUsableAmount(tenantId, custBalanceInfo));
                vo.setQueryTime(queryTime);
                usableBalanceVoList.add(vo);
            }
        }
        
        pageInfo.setCount(custBalancePageInfo.getCount());
        pageInfo.setPageNo(custBalancePageInfo.getPageNo());
        pageInfo.setPageSize(custBalancePageInfo.getPageSize());
        pageInfo.setResult(usableBalanceVoList);
        return pageInfo;
    }
    
    /**
     * 计算指定客户的可用余额
     * @param custBalanceInfo
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private double getUsableAmount(String tenantId, CustBalanceInfo custBalanceInfo) {
        String custId = custBalanceInfo.getCustId();
        //账本可用余额
        long totalBalance = custBalanceInfo.getTotalBalance();
        //获取客户欠费金额
        long ownFee = this.getCustOwnFee(tenantId, custId);
        //获取实时话费
        long realCharge = this.getRealCharge(tenantId, custId);
        long usableBalance = totalBalance - ownFee - realCharge;
        return AmountUtil.changeLiToYuan(usableBalance);
    }
    
    /**
     * 获取客户欠费金额
     * @param tenantId
     * @param custId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private long getCustOwnFee(String tenantId, String custId) {
        CustOweInfo custOweInfo = this.amcOweInfoAtomSV.getCustOweInfo(tenantId, custId);
        if(custOweInfo == null) {
            LOG.info("此客户["+ custId + "]未欠费！");
            return 0l;
        }
        
        return (long) Double.parseDouble(custOweInfo.getOwnFee());
    }
    
    /**
     * 获取实时费用
     * @param tenantId
     * @param custId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private long getRealCharge(String tenantId, String custId) {
        List<AmcOweInfo> amcOweInfoList = this.amcOweInfoAtomSV.queryAmcOweInfoListByCustId(
                tenantId, custId);
        if (CollectionUtil.isEmpty(amcOweInfoList)) {
            LOG.info("此客户[" + custId + "]查询不到对应的欠费数据记录！");
            return 0l;
        }

        long realCharge = 0l;
        for (AmcOweInfo amcOweInfo : amcOweInfoList) {
            realCharge += this.getRealChargeByAcctId(amcOweInfo);
        }
        return realCharge;
    }
    
    /**
     * 计算实时费用（账户粒度）
     * @param oweInfo
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private long getRealChargeByAcctId(AmcOweInfo amcOweInfo) {
        String currentCyle = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMM);        
        AmcInvoice invoice = amcInvoiceAtomSV.getAmcInvoice(amcOweInfo.getTenantId(), amcOweInfo.getAcctId(), currentCyle);
        if(invoice == null) {
            return 0l;
        }
        long totalAmount = 0l;
        if(invoice.getTotalAmount() != null) {
            totalAmount = invoice.getTotalAmount().longValue();
        }
        long adjustAmount = 0l;
        if(invoice.getAdjustAfterwards() != null) {
            adjustAmount = invoice.getAdjustAfterwards().longValue();
        }
        long discountAmount = 0l;
        if(invoice.getDiscTotalAmount() != null) {
            discountAmount = invoice.getDiscTotalAmount().longValue();
        }
        return totalAmount - adjustAmount - discountAmount;
    }

}
