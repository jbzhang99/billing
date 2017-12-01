package com.ai.baas.cust.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.baas.cust.api.acctinfo.params.AcctQueryRequest;
import com.ai.baas.cust.constants.BmcCacheConstant;
import com.ai.baas.cust.dao.interfaces.BlAcctInfoMapper;
import com.ai.baas.cust.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.cust.dao.mapper.bo.BlAcctInfoCriteria;
import com.ai.baas.cust.service.atom.interfaces.IBlAcctInfoAtomSV;
import com.ai.baas.cust.util.BusinessUtil;
import com.ai.baas.cust.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.alibaba.fastjson.JSON;

@Service
public class BlAcctInfoAtomSVImpl implements IBlAcctInfoAtomSV {
    @Autowired
    private BlAcctInfoMapper blAcctInfoMapper;

    @Override
    public void addDshmData(BlAcctInfo blAcctInfo) {
        int result = DshmUtil.getIdshmSV().initLoader(BmcCacheConstant.Dshm.TableName.BL_ACCT_INFO,
                JSON.toJSONString(BusinessUtil.assebleDshmData(blAcctInfo)),
                BmcCacheConstant.Dshm.OptType.INSERT);
        if (BmcCacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("账户信息写入缓存失败");
        }
    }

    @Override
    public List<BlAcctInfo> queryBlAcctinfo(AcctQueryRequest acctQueryRequest) {
        BlAcctInfoCriteria blAcctInfoCriteria = new BlAcctInfoCriteria();
        BlAcctInfoCriteria.Criteria criteria = blAcctInfoCriteria.createCriteria();
        criteria.andTenantIdEqualTo(acctQueryRequest.getTenantId());
        if (!CollectionUtil.isEmpty(acctQueryRequest.getCustIDs())) {
            criteria.andOwnerIdIn(acctQueryRequest.getCustIDs());
        }
        List<BlAcctInfo> acctInfoList = blAcctInfoMapper.selectByExample(blAcctInfoCriteria);
        if (CollectionUtil.isEmpty(acctInfoList)) {
            return null;
        } else {
            return acctInfoList;
        }

    }

    @Override
    public PageInfo<BlAcctInfo> queryBlAcctinfoPageInfo(AcctQueryRequest acctQueryRequest) {
        PageInfo<BlAcctInfo> pageInfo = new PageInfo<BlAcctInfo>();
        BlAcctInfoCriteria example = new BlAcctInfoCriteria();
        BlAcctInfoCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(acctQueryRequest.getTenantId());
        if (!CollectionUtil.isEmpty(acctQueryRequest.getCustIDs())) {
            criteria.andOwnerIdIn(acctQueryRequest.getCustIDs());
        }
        if(!CollectionUtil.isEmpty(acctQueryRequest.getAcctIDs())){
        	criteria.andAcctIdIn(acctQueryRequest.getAcctIDs());
        }
        if (acctQueryRequest.getPageNo() != null && acctQueryRequest.getPageSize() != null) {
            example.setLimitStart((acctQueryRequest.getPageNo() - 1)
                    * acctQueryRequest.getPageSize());
            example.setLimitEnd(acctQueryRequest.getPageSize());
        }
        int count = blAcctInfoMapper.countByExample(example);
        pageInfo.setResult(blAcctInfoMapper.selectByExample(example));
        pageInfo.setCount(count);
        pageInfo.setPageNo(acctQueryRequest.getPageNo());
        pageInfo.setPageSize(acctQueryRequest.getPageSize());
        pageInfo.setPageCount((pageInfo.getCount()+pageInfo.getPageSize()-1)/pageInfo.getPageSize());

        return pageInfo;
    }
}
