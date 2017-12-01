package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialSingle;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcFundSerialMapper;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundSerialAtomSV;
import com.ai.baas.amc.util.AmountUtil;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 交易订单原子服务
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class AmcFundSerialAtomSVImpl implements IAmcFundSerialAtomSV {

    @Override
    public AmcFundSerial getAmcFundSerial(String tableMonth, String peerSerialCode, String tenantId, String systemId)
            throws SystemException {
        AmcFundSerialCriteria sql = new AmcFundSerialCriteria();
        sql.setTableMonth(tableMonth);
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andSystemIdEqualTo(systemId);
        criteria.andPeerSerialCodeEqualTo(peerSerialCode);
        List<AmcFundSerial> amcFundSerials = MapperFactory.getAmcFundSerialMapper().selectByExample(sql);
        if(CollectionUtil.isEmpty(amcFundSerials)) {
            return null;
        }
        
        return amcFundSerials.get(0);
    }

    @Override
    public int saveAmcFundSerial(String tableMonth, AmcFundSerial amcFundSerial) throws SystemException {
        return MapperFactory.getAmcFundSerialMapper().insertSelective(tableMonth, amcFundSerial);
    }

    @Override
    public PageInfo<AmcFundSerial> queryAmcFundSerialList(PaymentLogQueryRequest request)
            throws SystemException {
        AmcFundSerialCriteria sql = new AmcFundSerialCriteria();
        Criteria criteria = sql.createCriteria();
        String tenantId = request.getTenantId();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andOptTypeEqualTo(AmcConstants.FunFundSerial.OptType.DEPOSIT);
        if(!StringUtil.isBlank(request.getPaySerialCode())) {
            criteria.andPaySerialCodeEqualTo(request.getPaySerialCode()); 
        }
        if(!StringUtil.isBlank(request.getCustName())) {
            criteria.andCustNameLike("%" + request.getCustName().trim() + "%");
        }
        if(!StringUtil.isBlank(request.getCustGrade())) {
            criteria.andCustGradeEqualTo(request.getCustGrade());
        }
        if(request.getBottomAmount() != null) {
            criteria.andTotalAmountGreaterThanOrEqualTo(AmountUtil.changeYuanToLi(request.getBottomAmount().doubleValue()));
        }
        if(request.getTopAmount() != null) {
            criteria.andTotalAmountLessThanOrEqualTo(AmountUtil.changeYuanToLi(request.getTopAmount().doubleValue()));
        }
        String tableMonth = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMM);
        if(request.getStartTime() != null) {
            tableMonth = DateUtil.getDateString(request.getStartTime(), DateUtil.YYYYMM);
            criteria.andCreateTimeGreaterThanOrEqualTo(request.getStartTime());
        }        
        if(request.getEndTime() != null) {
            tableMonth = DateUtil.getDateString(request.getEndTime(), DateUtil.YYYYMM);
            criteria.andCreateTimeLessThanOrEqualTo(request.getEndTime());
        }
        sql.setTableMonth(tableMonth);
        PageInfo<AmcFundSerial> pageInfo = new PageInfo<AmcFundSerial>();
        AmcFundSerialMapper mapper = MapperFactory.getAmcFundSerialMapper();
        pageInfo.setCount(mapper.countByExample(sql));
        sql.setLimitStart(request.getPageInfo().getStartRowIndex());
        sql.setLimitEnd(request.getPageInfo().getPageSize());
        pageInfo.setResult(mapper.selectByExample(sql));
        pageInfo.setPageNo(request.getPageInfo().getPageNo());
        pageInfo.setPageSize(request.getPageInfo().getPageSize());
        return pageInfo;
    }

    @Override
    public int saveAmcFundSerialSingle(AmcFundSerialSingle amcFundSerial) throws SystemException {
        return MapperFactory.getAmcFundSerialSingleMapper().insertSelective( amcFundSerial);
    }

}
