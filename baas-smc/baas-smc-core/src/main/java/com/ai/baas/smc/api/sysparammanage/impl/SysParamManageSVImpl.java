package com.ai.baas.smc.api.sysparammanage.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.smc.api.sysparammanage.interfaces.ISysParamManageSV;
import com.ai.baas.smc.api.sysparammanage.param.AddSysParamInfo;
import com.ai.baas.smc.api.sysparammanage.param.AddSysParamResponse;
import com.ai.baas.smc.api.sysparammanage.param.DeleteSysParam;
import com.ai.baas.smc.api.sysparammanage.param.DeleteSysParamResponse;
import com.ai.baas.smc.api.sysparammanage.param.QuerySysParamInfo;
import com.ai.baas.smc.api.sysparammanage.param.QuerySysParamResponse;
import com.ai.baas.smc.api.sysparammanage.param.SysParamInfo;
import com.ai.baas.smc.api.sysparammanage.param.UpdateSysParamResponse;
import com.ai.baas.smc.constants.SmcExceptCodeConstant;
import com.ai.baas.smc.service.busi.interfaces.ISysParamManageBusiSV;
import com.ai.baas.smc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class SysParamManageSVImpl implements ISysParamManageSV {

    @Autowired
    private ISysParamManageBusiSV iSysParamManageBusiSV;

    @Override
    public AddSysParamResponse addSysParam(AddSysParamInfo addSysParamInfo)
            throws BusinessException {
        BusinessUtil.checkBaseInfo(addSysParamInfo);
        if (StringUtil.isBlank(addSysParamInfo.getTypeCode())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "参数对应表名不可为空");
        }
        if (StringUtil.isBlank(addSysParamInfo.getParamCode())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "参数对应的字段不可为空");
        }
        if (StringUtil.isBlank(addSysParamInfo.getParamCode())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "参数对应的字段不可为空");
        }
        if (StringUtil.isBlank(addSysParamInfo.getColumnValue())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "参数编码不可为空");
        }
        if (StringUtil.isBlank(addSysParamInfo.getColumnDesc())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "参数显示值不可为空");
        }
        if (addSysParamInfo.getDispord() == null) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "显示顺序不可为空");
        }
        return iSysParamManageBusiSV.addSysParam(addSysParamInfo);
    }

    @Override
    public UpdateSysParamResponse updateSysParam(SysParamInfo sysParamInfo)
            throws BusinessException {
        BusinessUtil.checkBaseInfo(sysParamInfo);
        if (StringUtil.isBlank(sysParamInfo.getGuidkey())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "流水主键不可为空");
        }
        if ("".equals(sysParamInfo.getTypeCode())) {
            throw new BusinessException(SmcExceptCodeConstant.NO_DATA_OR_CACAE_ERROR, "必填字段不可置为空");
        }
        if ("".equals(sysParamInfo.getParamCode())) {
            throw new BusinessException(SmcExceptCodeConstant.NO_DATA_OR_CACAE_ERROR, "必填字段不可置为空");
        }
        if ("".equals(sysParamInfo.getColumnDesc())) {
            throw new BusinessException(SmcExceptCodeConstant.NO_DATA_OR_CACAE_ERROR, "必填字段不可置为空");
        }
        if ("".equals(sysParamInfo.getColumnValue())) {
            throw new BusinessException(SmcExceptCodeConstant.NO_DATA_OR_CACAE_ERROR, "必填字段不可置为空");
        }
        return iSysParamManageBusiSV.updateSysParam(sysParamInfo);
    }

    @Override
    public DeleteSysParamResponse deleteSysParam(DeleteSysParam deleteSysParam)
            throws BusinessException {
        BusinessUtil.checkBaseInfo(deleteSysParam);
        if (StringUtil.isBlank(deleteSysParam.getGuidkey())) {
            throw new BusinessException(SmcExceptCodeConstant.PARAM_IS_NULL, "流水主键不可为空");
        }
        return iSysParamManageBusiSV.deleteSysParam(deleteSysParam);
    }

    @Override
    public QuerySysParamResponse querySysParam(QuerySysParamInfo querySysParamInfo)
            throws BusinessException {
        BusinessUtil.checkBaseInfo(querySysParamInfo);
        return iSysParamManageBusiSV.querySysParam(querySysParamInfo);
    }

}
