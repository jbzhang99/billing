package com.ai.opt.sys.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.dao.mapper.bo.GnRoleFuncRelCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnRoleFuncRelCriteria.Criteria;
import com.ai.opt.sys.dao.mapper.factory.MapperFactory;
import com.ai.opt.sys.dao.mapper.interfaces.GnRoleFuncRelMapper;
import com.ai.opt.sys.service.atom.interfaces.IGnRoleFuncRelAtomSV;
@Component
public class GnRoleFuncRelAtomSVImpl implements IGnRoleFuncRelAtomSV {

    @Override
    public int deleteFuncRole(Long funcId, Long roleId) throws SystemException {
        GnRoleFuncRelCriteria example = new GnRoleFuncRelCriteria();
        Criteria criteria = example.createCriteria();
        if(funcId != null){
        	criteria.andFuncIdEqualTo(funcId);
        }
        if(roleId != null){
        	criteria.andRoleIdEqualTo(roleId);
        }
		return MapperFactory.getGnRoleFuncRelMapper().deleteByExample(example);
    }

	@Override
	public int deleteFuncRole(List<Long> funcIdList, List<Long> roleIdList) throws SystemException {
		GnRoleFuncRelMapper gnRoleFuncRelMapper = MapperFactory.getGnRoleFuncRelMapper();
		GnRoleFuncRelCriteria example = new GnRoleFuncRelCriteria();
		Criteria criteria = example.createCriteria();
		if(funcIdList != null && funcIdList.size()>0){
			criteria.andFuncIdIn(funcIdList);
		}
		if(roleIdList != null && roleIdList.size()>0){
			criteria.andRoleIdIn(roleIdList);
		}
		return gnRoleFuncRelMapper.deleteByExample(example );
	}

}
