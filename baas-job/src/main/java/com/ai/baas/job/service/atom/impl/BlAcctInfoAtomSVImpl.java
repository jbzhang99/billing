package com.ai.baas.job.service.atom.impl;

import org.springframework.stereotype.Service;

import com.ai.baas.job.constants.BaasJobCacheConstants;
import com.ai.baas.job.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.job.service.atom.interfaces.IBlAcctInfoAtomSV;
import com.ai.baas.job.util.BusinessUtil;
import com.ai.baas.job.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;

@Service
public class BlAcctInfoAtomSVImpl implements IBlAcctInfoAtomSV {

    @Override
    public void addDshmData(BlAcctInfo blAcctInfo) {
        int result = DshmUtil.getIdshmSV().initLoader(BaasJobCacheConstants.Dshm.TableName.BL_ACCT_INFO,
                JSON.toJSONString(BusinessUtil.assebleDshmData(blAcctInfo)),
                BaasJobCacheConstants.Dshm.OptType.INSERT);
        if (BaasJobCacheConstants.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("账户信息写入缓存失败");
        }
    }


}
