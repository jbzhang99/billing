package com.ai.baas.job.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.job.constants.BaasJobCacheConstants;
import com.ai.baas.job.dao.mapper.bo.BlCustinfo;
import com.ai.baas.job.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.job.util.BusinessUtil;
import com.ai.baas.job.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;

@Component
public class BlCustinfoAtomSVImpl implements IBlCustinfoAtomSV {

    @Override
    public void addDshmData(BlCustinfo blCustinfo) {
        int result = DshmUtil.getIdshmSV().initLoader(BaasJobCacheConstants.Dshm.TableName.BL_CUSTINFO,
                JSON.toJSONString(BusinessUtil.assebleDshmData(blCustinfo)),
                BaasJobCacheConstants.Dshm.OptType.INSERT);// redis 0更新 1插入
        if (BaasJobCacheConstants.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("客户信息写入缓存失败");
        }
    }


}
