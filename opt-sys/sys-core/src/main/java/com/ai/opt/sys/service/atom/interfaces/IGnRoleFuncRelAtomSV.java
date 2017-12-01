package com.ai.opt.sys.service.atom.interfaces;

import java.util.List;

import com.ai.opt.base.exception.SystemException;

public interface IGnRoleFuncRelAtomSV {
    int deleteFuncRole(Long funcId,Long roleId) throws SystemException;
    
    int deleteFuncRole(List<Long> funcIdList, List<Long> roleIdList) throws SystemException;
}
