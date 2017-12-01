package com.ai.baas.amc.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.oweinfoquery.param.ChargeDetailInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryResponse;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfo;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcOweInfoAtomSV;
import com.ai.baas.amc.service.business.interfaces.IOweInfoQueryBusiSV;
import com.ai.baas.amc.util.CustInfoUtil;
import com.ai.baas.amc.util.FunSubjectUtil;
import com.ai.baas.amc.vo.CustInfo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;

/**
 * 欠费查询服务业务实现类
 *
 * Date: 2016年4月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class OweInfoQueryBusiSVImpl implements IOweInfoQueryBusiSV {
    
    private static final Logger LOG = LogManager.getLogger(OweInfoQueryBusiSVImpl.class);
    
    @Autowired
    private IAmcOweInfoAtomSV amcOweInfoAtomSV;
    
    @Autowired
    private IAmcChargeAtomSV amcChargeAtomSV;
        
    @Override
    public PageInfo<OweInfo> queryOweInfoList(OweInfoListQueryRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        PageInfo<CustOweInfo> custOwePageInfo = amcOweInfoAtomSV.queryCustOweInfoList(request);
        PageInfo<OweInfo> pageInfo = new PageInfo<OweInfo>();
        List<OweInfo> infoList = new ArrayList<OweInfo>();
        if(!CollectionUtil.isEmpty(custOwePageInfo.getResult())) {
            for(CustOweInfo custOweInfo : custOwePageInfo.getResult()) {
                String custId = custOweInfo.getCustId();
                CustInfo custInfo = CustInfoUtil.getCustInfo(tenantId, custId);
                if(custInfo == null) {
                    throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取不到对应的客户信息[" + custId + "]");
                }
                OweInfo oweInfo = new OweInfo();
                oweInfo.setTenantId(tenantId);
                oweInfo.setCustId(custId);
                oweInfo.setCustName(custInfo.getCustName());
                oweInfo.setCustGrade(custInfo.getCustGrade());
                oweInfo.setUnsettledMonth(custOweInfo.getUnsettledMonth());
                long ownFee = (long) Double.parseDouble(custOweInfo.getOwnFee());
                oweInfo.setBalance(ownFee);
                infoList.add(oweInfo);
            }
        }
        
        pageInfo.setCount(custOwePageInfo.getCount());
        pageInfo.setPageNo(custOwePageInfo.getPageNo());
        pageInfo.setPageSize(custOwePageInfo.getPageSize());
        pageInfo.setResult(infoList);
        return pageInfo;
    }
    
    @Override
    public OweDetailInfoQueryResponse queryOweDetailInfo(OweDetailInfoQueryRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        String custId = request.getCustId();
        CustInfo custInfo = CustInfoUtil.getCustInfo(tenantId, custId); 
        if(custInfo == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "欠费明细查询失败，获取不到对应的客户信息[" + custId + "]");
        }
        OweDetailInfoQueryResponse response = new OweDetailInfoQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        response.setTenantId(tenantId);
        response.setCustName(custInfo.getCustName());
        response.setCustGrade(custInfo.getCustGrade());
        CustOweInfo custOweInfo = this.amcOweInfoAtomSV.getCustOweInfo(tenantId, custId);
        if(custOweInfo == null) {
            LOG.info("此客户["+ custId + "]未欠费！");
            return response;
        }
        String unsettledMonth = custOweInfo.getUnsettledMonth();
        long ownFee = (long) Double.parseDouble(custOweInfo.getOwnFee());
        response.setUnsettledMonth(unsettledMonth);
        response.setBalance(ownFee);
        List<OweDetailInfo> oweDetailInfos = this.getOweDetailInfoList(tenantId, custId, unsettledMonth);
        response.setOweDetailInfos(oweDetailInfos);
        LOG.info("欠费明细查询成功");
        return response;
    }
    
    /**
     * 获取账单明细列表
     * @param tenantId
     * @param custId
     * @param unsettledMonth
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private List<OweDetailInfo> getOweDetailInfoList(String tenantId, String custId,
            String unsettledMonth) {
        List<OweDetailInfo> result = new ArrayList<OweDetailInfo>();
        Timestamp time = DateUtil.getSysDate();
        while (true) {
            String date = DateUtil.getDateString(time, DateUtil.YYYYMM);
            List<OweDetailInfo> infoList = this.getOweDetailInfoListWithAccDate(tenantId, custId, date);
            result.addAll(infoList);
            if(date.equals(unsettledMonth)) {
                break;
            }
            time = DateUtil.getOffsetMonthsTime(time, -1);
        }
        return result;
    }
    
    /**
     * 获取指定月欠费信息列表(按服务号码汇总)
     * @param tenantId
     * @param custId
     * @param unsettledMonth
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private List<OweDetailInfo> getOweDetailInfoListWithAccDate(String tenantId, String custId,
            String accDate) {
        List<OweDetailInfo> oweDetailInfoList = new ArrayList<OweDetailInfo>();
        List<AmcCharge> amcChargeList = amcChargeAtomSV.queryAmcChargeList(tenantId, custId, accDate);
        if(CollectionUtil.isEmpty(amcChargeList)) {
            return oweDetailInfoList;
        }
        Set<String> acctSet = new HashSet<String>();
        Map<String, List<ChargeDetailInfo>> chargeDetailInfoMap = new HashMap<String, List<ChargeDetailInfo>>();     
        for(AmcCharge amcCharge : amcChargeList) {
            if(amcCharge.getBalance() <= 0) {
                continue;
            }
            ChargeDetailInfo chargeDetailInfo = this.assemableChargeDetailInfo(amcCharge);
            String acctId = amcCharge.getAcctId();
            List<ChargeDetailInfo> chargeDetailInfoList = chargeDetailInfoMap.get(acctId);
            if(chargeDetailInfoList == null) {
                chargeDetailInfoList = new ArrayList<ChargeDetailInfo>();
                chargeDetailInfoMap.put(acctId, chargeDetailInfoList);
            }
            if(!acctSet.contains(acctId)) {
                OweDetailInfo info = new OweDetailInfo();
                info.setAccDate(accDate);
                info.setAcctId(acctId);
                info.setChargeDetailInfos(chargeDetailInfoList);
                info.setServiceNum(amcCharge.getServiceId());
                acctSet.add(acctId);
                oweDetailInfoList.add(info);
            }
            chargeDetailInfoList.add(chargeDetailInfo);
        }
        
        return oweDetailInfoList;
    }
    
    /**
     * 封装科目账单明细信息
     * @param record
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private ChargeDetailInfo assemableChargeDetailInfo(AmcCharge record) {
        ChargeDetailInfo info = new ChargeDetailInfo();
        info.setSubjectId(record.getSubjectId());
        String subjectName = FunSubjectUtil.getSubjectName(record.getTenantId(), record.getSubjectId());
        info.setSubjectName(subjectName);
        info.setBalance(record.getBalance());
        return info;
    }

}
