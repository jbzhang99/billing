package com.ai.opt.sys.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.dao.mapper.bo.GnFunc;
import com.ai.opt.sys.dao.mapper.bo.GnFuncCriteria;

public interface IGnFuncAtomSV {
    
    List<GnFunc> queryFuncInfoList(GnFuncCriteria criteria) throws SystemException;
    
    int queryFuncCount(GnFuncCriteria criteria) throws SystemException;
    
    GnFunc queryFuncById(long funcId) throws SystemException;
    
    List<GnFunc> querySonFuncById(long funcId) throws SystemException;
    
    List<GnFunc> querySonFuncById(List<Long> funcIdList) throws SystemException;
    
    int updateFuncById(GnFunc func) throws SystemException;
    
    int insertFunc(GnFunc func) throws SystemException;
    
    int deleteFuncById(long funcId) throws SystemException;
    
    int deleteFuncByListId(List<Long> funcIdList) throws SystemException;
}
