package com.ai.baas.pkgfee.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.baas.pkgfee.util.BusinessUtil;
import com.ai.baas.pkgfee.util.DshmUtil;
import com.ai.baas.pkgfee.constants.CacheConstant;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageTaskLogCriteria;
import com.ai.baas.pkgfee.dao.mapper.factory.MapperFactory;
import com.ai.baas.pkgfee.dao.mapper.interfaces.CpPackageTaskLogMapper;
import com.ai.baas.pkgfee.service.atom.interfaces.ICpPackageTaskLogAtom;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageTaskLog;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.alibaba.fastjson.JSON;

@Service
public class CpPackageTaskLogAtomImpl implements ICpPackageTaskLogAtom {

	@Override
	public void addCpPackageTaskLog(CpPackageTaskLog log)  throws SystemException{
		// TODO Auto-generated method stub
		 MapperFactory.getCpPackageTaskLogMapper().insert(log);
	}

	@Override
    public void addDshmData(CpPackageTaskLog log) {
        int result = DshmUtil.getIdshmSV().initLoader(CacheConstant.Dshm.TableName.CP_PKG_TASK_LOG,
                JSON.toJSONString(BusinessUtil.assebleDshmData(log)),
                CacheConstant.Dshm.OptType.INSERT);
        if (CacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("包年包月日志表信息写入缓存失败");
        }
    }
}
