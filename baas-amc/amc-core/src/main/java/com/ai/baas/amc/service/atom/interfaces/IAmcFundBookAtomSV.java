package com.ai.baas.amc.service.atom.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.dao.mapper.bo.CustBalanceInfo;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账本原子服务接口定义
 *
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IAmcFundBookAtomSV {
    
    /**
     * 账本查询，通过账本ID
     * @param tenantId
     * @param bookId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    AmcFundBook getAmcFundBookByBookId(String tenantId, String accountId, long bookId);
    
    /**
     * 账本查询，根据账户ID,账本类型,账本状态
     * @param tenantId
     * @param accountId
     * @param fundType
     * @param status
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    List<AmcFundBook> queryAmcFundBooks(String tenantId, String accountId, List<String> fundTypeList,
            List<String> statusList);
    
    /**
     * 账本查询，根据账户ID,科目ID,账本状态
     * @param tenantId
     * @param accountId
     * @param status
     * @param subjectId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    List<AmcFundBook> queryAmcFundBooks(String tenantId, String accountId, List<String> statusList,
            Long subjectId);
    
    /**
     * 用户有效账本查询(subsId=0为非用户专款)，根据科目类型
     * @param tenantId
     * @param accountId
     * @param fundType
     * @param subsId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    List<AmcFundBook> getSubsValidFundBooksByFundType(String tenantId, String accountId,
            List<String> fundType, long subsId);
    
    /**
     * 用户有效账本查询(subsId=0为非用户专款)，根据科目ID
     * 
     * @param accountId
     * @param subjectId
     * @return
     * @author lilg
     */
    List<AmcFundBook> getSubsValidFundBooksBySubjectId(String tenantId, String accountId,
            long subjectId, long subsId);

    /**
     * 新增账本
     * @param amcFundBook
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    int saveAmcFundBook(AmcFundBook amcFundBook) throws SystemException;
    
    /**
     * 
     * 给指定账本增加金额,并修改有效期 <br>
     * 返回修改记录数 <br>
     * 
     * @param accountId
     * @param bookId
     * @param amount
     * @return
     * @author lilg
     */
    int depositBalance(String accountId, long bookId, long amount, Timestamp effectTime, Timestamp expireTime) throws SystemException;

    /**
     * 从指定账本扣除金额，金额不可为负<br>
     * 返回修改记录数 <br>
     * 
     * @param accountId
     * @param bookId
     * @param amount
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    int deductBalance(String accountId, long bookId, long amount);

    /**
     * 查询指定租户中指定账户的账本
     *
     * @param tenantId
     * @param accountId
     * @return
     */
    public List<AmcFundBook> selectByTenantAndAccount(String tenantId, String accountId);

    /**
     * 查询指定租户中指定账户,且失效日期在指定时间之后的账本
     *
     * @param tenantId
     * @param accountId
     * @return
     */
    public List<AmcFundBook> selectByTenantAndAccountAfterExpire(String tenantId, String accountId,Timestamp expireTime);
    
    /**
     * 分页查询客户下账本可用余额
     * @param request
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<CustBalanceInfo> queryCustBalanceInfoList(UsableBalanceQueryRequest request); 
}
