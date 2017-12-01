package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoKey;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 欠费汇总信息原子服务
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author liutong5
 */
public interface IAmcOweInfoAtomSV {

    /**
     * 根据欠费汇总key获取欠费汇总信息
     *
     * @param amcOweInfoKey
     * @return
     */
    public AmcOweInfo selectByInfoKey(AmcOweInfoKey amcOweInfoKey) throws BusinessException,SystemException;
    
    /**
     * 分页查询客户欠费信息
     * @param param
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<CustOweInfo> queryCustOweInfoList(OweInfoListQueryRequest request) throws SystemException;
    
    /**
     * 按客户查询欠费信息
     * @param tenantId
     * @param custId
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    List<AmcOweInfo> queryAmcOweInfoListByCustId(String tenantId, String custId) throws SystemException;
    
    /**
     * 客户欠费查询
     * @param tenantId
     * @param custId
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    CustOweInfo getCustOweInfo(String tenantId, String custId) throws SystemException;
    
    int updateOweInfo(AmcOweInfo amcOweInfo)throws SystemException;
}
