package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.constants.FeeSource;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoKey;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfo;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfoQueryParam;
import com.ai.baas.amc.dao.mapper.bo.OweInfoQueryParam;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcOweInfoMapper;
import com.ai.baas.amc.service.atom.interfaces.IAmcOweInfoAtomSV;
import com.ai.baas.amc.util.AmountUtil;
import com.ai.baas.amc.util.CalUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;







import org.springframework.stereotype.Component;

/**
 * 欠费汇总原子服务
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
@Component
public class AmcOweInfoAtomSVImpl implements IAmcOweInfoAtomSV {
    
    @Override
    public AmcOweInfo selectByInfoKey(AmcOweInfoKey amcOweInfoKey)
            throws BusinessException, SystemException {
        AmcOweInfo amcOweInfo = MapperFactory.getAmcOweInfoMapper().selectByPrimaryKey(amcOweInfoKey);
        if (amcOweInfo==null){
            throw new BusinessException("amc_owe_info","获取欠费汇总AmcOweInfo异常");
        }
//        amcOweInfo.setBalance(
//                CalUtil.BigDecimalFromDouble(Double.parseDouble(amcOweInfo.getBalance().toString()), FeeSource.FROMCHARGE));
        return amcOweInfo;
    }

    @Override
    public PageInfo<CustOweInfo> queryCustOweInfoList(OweInfoListQueryRequest request)
            throws SystemException {
        CustOweInfoQueryParam param = new CustOweInfoQueryParam();
        param.setTenantId(request.getTenantId());
        param.setCustName(request.getCustName());
        param.setCustGrade(request.getCustGrade());
        param.setStartDate(request.getStartDate());
        param.setEndDate(request.getEndDate());
        if(request.getBottomAmount() != null) {
            param.setBottomBalance(AmountUtil.changeYuanToLi(request.getBottomAmount().doubleValue()));
        }
        if(request.getTopAmount() != null) {
            param.setTopBalance(AmountUtil.changeYuanToLi(request.getTopAmount().doubleValue()));
        }
        PageInfo<CustOweInfo> pageInfo = new PageInfo<CustOweInfo>();
        AmcOweInfoMapper mapper = MapperFactory.getAmcOweInfoMapper();
        pageInfo.setCount(mapper.getOweInfoCount(param));
        param.setLimitStart(request.getPageInfo().getStartRowIndex());
        param.setLimitEnd(request.getPageInfo().getPageSize());
        pageInfo.setResult(mapper.getOweInfoListByPageInfo(param));
        pageInfo.setPageNo(request.getPageInfo().getPageNo());
        pageInfo.setPageSize(request.getPageInfo().getPageSize());
        return pageInfo;
    }

    @Override
    public CustOweInfo getCustOweInfo(String tenantId, String custId) throws SystemException {
        OweInfoQueryParam param = new OweInfoQueryParam();
        param.setTenantId(tenantId);
        param.setCustId(custId);
        return MapperFactory.getAmcOweInfoMapper().getCustOweInfo(param);
    }

    @Override
    public List<AmcOweInfo> queryAmcOweInfoListByCustId(String tenantId, String custId)
            throws SystemException {
        AmcOweInfoCriteria sql = new AmcOweInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andCustIdEqualTo(custId);
        return MapperFactory.getAmcOweInfoMapper().selectByExample(sql);
    }

    @Override
    public int updateOweInfo(AmcOweInfo amcOweInfo) throws SystemException {
        AmcOweInfoCriteria sql = new AmcOweInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(amcOweInfo.getTenantId());
        criteria.andAcctIdEqualTo(amcOweInfo.getAcctId());
        return MapperFactory.getAmcOweInfoMapper().updateByExampleSelective(amcOweInfo, sql);
    }
}
