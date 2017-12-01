package com.ai.opt.sys.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.dao.mapper.bo.GnFunc;

public interface IGnFuncBusiSV {
    PageInfo<GnFunc> queryFuncInfo(QueryGnFuncPageRequest request) throws SystemException;
    
    GnFunc queryFuncInfo(QueryGnFuncInfoRequest queryRequest) throws SystemException;
    
    int updateFuncById(GnFunc func) throws SystemException;
    
    int insertFunc(GnFunc func) throws SystemException;
    
    int deleteFuncById(long funcId) throws BusinessException;
    
    List<GnFunc> queryChilderFuncInfo(long funcId) throws BusinessException;
    
    List<GnFunc> querySonFuncInfo(long funcId) throws BusinessException;
}
