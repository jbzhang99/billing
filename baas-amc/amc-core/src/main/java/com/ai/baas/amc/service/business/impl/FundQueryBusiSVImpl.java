package com.ai.baas.amc.service.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.fundquery.param.FundBook;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.amc.api.fundquery.param.SpecialFundBookQueryRequest;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundBookAtomSV;
import com.ai.baas.amc.service.business.interfaces.IFundQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.ResponseHeader;

/**
 * 账本查询具体实现类
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Transactional
public class FundQueryBusiSVImpl implements IFundQueryBusiSV {
        
    @Autowired
    private IAmcFundBookAtomSV amcFundBookAtomSV;

    @Override
    public FundBookQueryResponse queryFund(FundBookQueryRequest request) throws BusinessException {
        String tenantId = request.getTenantId();
        String accountId = request.getAccountId();
        // 账本状态：有效,冻结
        List<String> statusList = new ArrayList<String>();
        statusList.add(AmcConstants.FunFundBook.BookStatus.VALID);
        statusList.add(AmcConstants.FunFundBook.BookStatus.FREEZE);
        // 资金类型:现金,通信现金,通信赠款
        List<String> fundTypeList = Arrays.asList(new String[] {
                AmcConstants.FunFundBook.FundType.CASH,
                AmcConstants.FunFundBook.FundType.TELE_CASH,
                AmcConstants.FunFundBook.FundType.GRANT });
        List<AmcFundBook> fundBookList = amcFundBookAtomSV.queryAmcFundBooks(tenantId, accountId,
                fundTypeList, statusList);

        return this.constructFundBookQueryRes(tenantId, accountId, fundBookList);
    }

    @Override
    public FundBookQueryResponse queryUsableFund(FundBookQueryRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        String accountId = request.getAccountId();
        // 账本状态：有效
        List<String> statusList = new ArrayList<String>();
        statusList.add(AmcConstants.FunFundBook.BookStatus.VALID);
        // 资金类型:现金,通信现金,通信赠款
        List<String> fundTypeList = Arrays.asList(new String[] {
                AmcConstants.FunFundBook.FundType.CASH,
                AmcConstants.FunFundBook.FundType.TELE_CASH,
                AmcConstants.FunFundBook.FundType.GRANT });
        List<AmcFundBook> fundBookList = amcFundBookAtomSV.queryAmcFundBooks(tenantId, accountId,
                fundTypeList, statusList);

        return this.constructFundBookQueryRes(tenantId, accountId, fundBookList);
    }

    @Override
    public FundBookQueryResponse queryFundBySubjectId(SpecialFundBookQueryRequest request)
            throws BusinessException {
        String tenantId = request.getTenantId();
        String accountId = request.getAccountId();
        // 账本状态：有效
        List<String> statusList = new ArrayList<String>();
        statusList.add(AmcConstants.FunFundBook.BookStatus.VALID);
        List<AmcFundBook> fundBookList = amcFundBookAtomSV.queryAmcFundBooks(tenantId, accountId,
                statusList, request.getSubjectId());
        return this.constructFundBookQueryRes(tenantId, accountId, fundBookList);
    }
    
    /**
     * 构造余额查询结果
     * @param tenantId
     * @param accountId
     * @param fundBookList
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    private FundBookQueryResponse constructFundBookQueryRes(String tenantId, String accountId,
            List<AmcFundBook> fundBookList) {
        FundBookQueryResponse response = new FundBookQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        long totalBalance = 0l;
        response.setTenantId(tenantId);
        response.setAccountId(accountId);
        List<FundBook> fundBooks = new ArrayList<FundBook>();
        for(AmcFundBook funFundBook : fundBookList) {
            totalBalance += funFundBook.getBalance();
            FundBook fundBook = new FundBook();
            fundBook.setBookId(funFundBook.getBookId());
            fundBook.setBalance(funFundBook.getBalance());
            fundBook.setSubjectId(funFundBook.getSubjectId());
            fundBook.setSubsId(funFundBook.getSubsId() == null ? 0 : funFundBook.getSubsId());
            fundBook.setEffectDate(funFundBook.getEffectDate());
            fundBook.setExpireDate(funFundBook.getExpireDate());
            fundBooks.add(fundBook);
        }
        response.setBalance(totalBalance);
        response.setFundBooks(fundBooks);
        response.setResponseHeader(responseHeader);
        return response;
    }

}
