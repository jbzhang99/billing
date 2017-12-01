package com.ai.baas.omc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.baas.omc.util.BusinessUtil;
import com.ai.baas.omc.util.DshmUtil;
import com.ai.baas.omc.constants.OmcCacheConstant;
import com.ai.baas.omc.dao.mapper.bo.OmcAvoidScout;
import com.ai.baas.omc.dao.mapper.bo.OmcAvoidScoutCriteria;
import com.ai.baas.omc.dao.mapper.factory.MapperFactory;
import com.ai.baas.omc.service.atom.interfaces.IOmcAvoidScoutAtom;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.alibaba.fastjson.JSON;

@Service
public class OmcAvoidScoutAtomImpl implements IOmcAvoidScoutAtom {

	@Override
	public void addOmcAvoidScout(OmcAvoidScout avoidScout) throws SystemException{
		// TODO Auto-generated method stub
		MapperFactory.getOmcAvoidScoutMapper().insert(avoidScout);
	}

	@Override
    public void addDshmData(OmcAvoidScout avoidScout) {
        int result = DshmUtil.getIdshmSV().initLoader(OmcCacheConstant.Dshm.TableName.OMC_AVOIDSCOUT,
                JSON.toJSONString(BusinessUtil.assebleDshmData(avoidScout)),
                OmcCacheConstant.Dshm.OptType.INSERT);
        if (OmcCacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("信控免催停信息写入缓存失败");
        }
    }

	@Override
	public void updateOmcAvoidScout(OmcAvoidScout avoidScout) throws SystemException{
		// TODO Auto-generated method stub
		OmcAvoidScoutCriteria omcAvoidScoutCriteria = new OmcAvoidScoutCriteria();
		OmcAvoidScoutCriteria.Criteria criteria = omcAvoidScoutCriteria.createCriteria();
		criteria.andAvoidSeqEqualTo(avoidScout.getAvoidSeq());

		MapperFactory.getOmcAvoidScoutMapper().updateByExampleSelective(avoidScout, omcAvoidScoutCriteria);
	}

	@Override
    public void updDshmData(OmcAvoidScout avoidScout) {
        int result = DshmUtil.getIdshmSV().initLoader(OmcCacheConstant.Dshm.TableName.OMC_AVOIDSCOUT,
                JSON.toJSONString(BusinessUtil.assebleDshmData(avoidScout)),
                OmcCacheConstant.Dshm.OptType.UPDATE);
        if (OmcCacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("信控免催停信息更新缓存失败");
        }
    }
	
	@Override
	public void deleteOmcAvoidScout(OmcAvoidScout avoidScout) throws SystemException{
		// TODO Auto-generated method stub
		OmcAvoidScoutCriteria omcAvoidScoutCriteria = new OmcAvoidScoutCriteria();
		OmcAvoidScoutCriteria.Criteria criteria = omcAvoidScoutCriteria.createCriteria();
		criteria.andAvoidSeqEqualTo(avoidScout.getAvoidSeq());

		MapperFactory.getOmcAvoidScoutMapper().deleteByExample(omcAvoidScoutCriteria);
	}

	@Override
    public void delDshmData(OmcAvoidScout avoidScout) {
        int result = DshmUtil.getIdshmSV().initdel(OmcCacheConstant.Dshm.TableName.OMC_AVOIDSCOUT,
                JSON.toJSONString(BusinessUtil.assebleDshmData(avoidScout)));
        if (OmcCacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("信控免催停信息删除缓存失败");
        }
    }

	@Override
	public List<OmcAvoidScout> queryByData(OmcAvoidScout avoidScout) throws SystemException{
		OmcAvoidScoutCriteria omcAvoidScoutCriteria = new OmcAvoidScoutCriteria();
		OmcAvoidScoutCriteria.Criteria criteria = omcAvoidScoutCriteria.createCriteria();
		criteria.andTenantIdEqualTo(avoidScout.getTenantId())
		        .andOwnerIdEqualTo(avoidScout.getOwnerId())
		        .andOwnerTypeEqualTo(avoidScout.getOwnerType())
		        .andSpeTypeEqualTo(avoidScout.getSpeType());
		
		return MapperFactory.getOmcAvoidScoutMapper().selectByExample(omcAvoidScoutCriteria);
	}
}
