package com.ai.baas.amc.service.atom.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBookCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBookCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.bo.CustBalanceInfo;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcFundBookMapper;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundBookAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账本原子服务操作类
 *
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class AmcFundBookAtomSVImpl implements IAmcFundBookAtomSV {

    @Override
    public List<AmcFundBook> getSubsValidFundBooksBySubjectId(String tenantId, String accountId,
            long subjectId, long subsId) {
        Timestamp sysdate = DateUtil.getSysDate();
        AmcFundBookCriteria sql = new AmcFundBookCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andBookStatusEqualTo(AmcConstants.FunFundBook.BookStatus.VALID);
        criteria.andSubjectIdEqualTo(subjectId);
        criteria.andSubsIdEqualTo(subsId);
        criteria.andEffectDateLessThanOrEqualTo(sysdate);
        criteria.andExpireDateGreaterThanOrEqualTo(sysdate);
        return MapperFactory.getAmcFundBookMapper().selectByExample(sql);
    }

    @Override
    public int saveAmcFundBook(AmcFundBook amcFundBook) throws SystemException {
        return MapperFactory.getAmcFundBookMapper().insertSelective(amcFundBook);
    }

    @Override
    public int depositBalance(String accountId, long bookId, long amount, Timestamp effectTime,
            Timestamp expireTime) throws SystemException {
        return MapperFactory.getAmcFundBookAttachMapper().depositBalance(accountId, bookId, amount, effectTime, expireTime);
    }

    @Override
    public List<AmcFundBook> queryAmcFundBooks(String tenantId, String accountId,
            List<String> fundTypeList, List<String> statusList) {
        Timestamp sysdate = DateUtil.getSysDate();
        AmcFundBookCriteria sql = new AmcFundBookCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andBookStatusIn(statusList);
        criteria.andSubjectTypeIn(fundTypeList);
        criteria.andEffectDateLessThanOrEqualTo(sysdate);
        criteria.andExpireDateGreaterThanOrEqualTo(sysdate);
        return MapperFactory.getAmcFundBookMapper().selectByExample(sql);
    }

    @Override
    public List<AmcFundBook> queryAmcFundBooks(String tenantId, String accountId,
            List<String> statusList, Long subjectId) {
        Timestamp sysdate = DateUtil.getSysDate();
        AmcFundBookCriteria sql = new AmcFundBookCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andSubjectIdEqualTo(subjectId);
        criteria.andBookStatusIn(statusList);
        criteria.andEffectDateLessThanOrEqualTo(sysdate);
        criteria.andExpireDateGreaterThanOrEqualTo(sysdate);
        return MapperFactory.getAmcFundBookMapper().selectByExample(sql);
    }

    @Override
    public AmcFundBook getAmcFundBookByBookId(String tenantId, String accountId, long bookId) {
        Timestamp sysdate = DateUtil.getSysDate();
        AmcFundBookCriteria sql = new AmcFundBookCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andBookIdEqualTo(bookId);
        criteria.andEffectDateLessThanOrEqualTo(sysdate);
        criteria.andExpireDateGreaterThanOrEqualTo(sysdate);
        List<AmcFundBook> fundBookList = MapperFactory.getAmcFundBookMapper().selectByExample(sql);
        if(CollectionUtil.isEmpty(fundBookList)) {
            return null;            
        }
        
        return fundBookList.get(0);
    }

    @Override
    public int deductBalance(String accountId, long bookId, long amount) {
        return MapperFactory.getAmcFundBookAttachMapper().deductBalance(accountId, bookId, amount);
    }

    @Override
    public List<AmcFundBook> selectByTenantAndAccount(String tenantId, String accountId) {
        AmcFundBookCriteria example = new AmcFundBookCriteria();
        AmcFundBookCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andBookStatusEqualTo("1");//账本为有效状态
        criteria.andEffectDateLessThan(DateUtil.getSysDate());//在生效之后
        criteria.andExpireDateGreaterThanOrEqualTo(DateUtil.getSysDate());//在失效之前
        return MapperFactory.getAmcFundBookMapper().selectByExample(example);
    }

    @Override
    public List<AmcFundBook> selectByTenantAndAccountAfterExpire(String tenantId, String accountId, Timestamp expireTime) {
        AmcFundBookCriteria example = new AmcFundBookCriteria();
        AmcFundBookCriteria.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andBookStatusEqualTo("1");//账本为有效状态
        criteria.andExpireDateGreaterThanOrEqualTo(expireTime);//在失效日期在指定时间之后
        return MapperFactory.getAmcFundBookMapper().selectByExample(example);
    }

    @Override
    public List<AmcFundBook> getSubsValidFundBooksByFundType(String tenantId, String accountId,
            List<String> fundType, long subsId) {
        Timestamp sysdate = DateUtil.getSysDate();
        AmcFundBookCriteria sql = new AmcFundBookCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(accountId);
        criteria.andBookStatusEqualTo(AmcConstants.FunFundBook.BookStatus.VALID);
        criteria.andSubjectTypeIn(fundType);
        criteria.andSubsIdEqualTo(subsId);
        criteria.andEffectDateLessThanOrEqualTo(sysdate);
        criteria.andExpireDateGreaterThanOrEqualTo(sysdate);
        return MapperFactory.getAmcFundBookMapper().selectByExample(sql);
    
    }

    @Override
    public PageInfo<CustBalanceInfo> queryCustBalanceInfoList(UsableBalanceQueryRequest request) {
        Timestamp sysdate = DateUtil.getSysDate();
        AmcFundBookCriteria sql = new AmcFundBookCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        if(!StringUtil.isBlank(request.getCustName())) {
            criteria.andCustNameLike("%" + request.getCustName().trim() + "%");
        }
        if(!StringUtil.isBlank(request.getCustGrade())) {
            criteria.andCustGradeEqualTo(request.getCustGrade());
        }
        // 资金类型:现金,通信现金,通信赠款
        List<String> fundTypeList = Arrays.asList(new String[] {
                AmcConstants.FunFundBook.FundType.CASH,
                AmcConstants.FunFundBook.FundType.TELE_CASH,
                AmcConstants.FunFundBook.FundType.GRANT });
        criteria.andBookStatusEqualTo(AmcConstants.FunFundBook.BookStatus.VALID);
        criteria.andSubjectTypeIn(fundTypeList);
        criteria.andEffectDateLessThanOrEqualTo(sysdate);
        criteria.andExpireDateGreaterThanOrEqualTo(sysdate);
        PageInfo<CustBalanceInfo> pageInfo = new PageInfo<CustBalanceInfo>();
        AmcFundBookMapper mapper = MapperFactory.getAmcFundBookMapper();
        pageInfo.setCount(mapper.getCustBalanceInfoCount(sql));
        sql.setLimitStart(request.getPageInfo().getStartRowIndex());
        sql.setLimitEnd(request.getPageInfo().getPageSize());
        pageInfo.setResult(mapper.getCustBalanceInfoListByPageInfo(sql));
        pageInfo.setPageNo(request.getPageInfo().getPageNo());
        pageInfo.setPageSize(request.getPageInfo().getPageSize());
        return pageInfo;
    }

}
