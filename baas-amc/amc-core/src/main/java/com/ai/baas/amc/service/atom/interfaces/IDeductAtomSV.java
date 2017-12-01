package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.vo.DeductVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.opt.base.exception.SystemException;

/**
 * 扣款原子服务接口定义
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IDeductAtomSV {

    /**
     * 科目校验 <br>
     * 
     * @param vo
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    Map<Long, SubjectFundVo> validateSubject(DeductVo vo);
    
    /**
     * 账本校验
     * 
     * @param acctId
     * @param tenantId
     * @param bookId
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    void validateFundBook(String acctId, String tenantId, long bookId);
    
    /**
     * 单账本扣款
     * @param acctId
     * @param bookId
     * @param amount
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    void deductFundBook(String acctId, long bookId, long amount);
    
    /**
     * 多账本扣款
     * @param tenantId
     * @param accountId
     * @param bookList
     * @param totalAmount
     * @return Map<FunFundBook,amount> 每个账本扣减金额
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    Map<AmcFundBook,Long> deductFundBook(String tenantId, String accountId, List<AmcFundBook> bookList, long totalAmount);
    
    /**
     * 保存资金交易信息并记录账本交易明细
     * @param vo
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    String savePaySerial(DeductVo vo) throws SystemException;
}
