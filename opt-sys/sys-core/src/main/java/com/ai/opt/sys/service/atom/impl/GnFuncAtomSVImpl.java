package com.ai.opt.sys.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.constants.SysConstants;
import com.ai.opt.sys.dao.mapper.bo.GnFunc;
import com.ai.opt.sys.dao.mapper.bo.GnFuncCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnFuncCriteria.Criteria;
import com.ai.opt.sys.dao.mapper.factory.MapperFactory;
import com.ai.opt.sys.dao.mapper.interfaces.GnFuncMapper;
import com.ai.opt.sys.service.atom.interfaces.IGnFuncAtomSV;
@Component
public class GnFuncAtomSVImpl implements IGnFuncAtomSV {

    @Override
    public GnFunc queryFuncById(long funcId) {
        GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
        return funcMapper.selectByPrimaryKey(funcId);
    }

    @Override
    public int updateFuncById(GnFunc func) throws SystemException {
        GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
        return funcMapper.updateByPrimaryKeySelective(func);
    }

    @Override
    public int insertFunc(GnFunc func) throws SystemException {
        GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
        return funcMapper.insertSelective(func);
    }

    @Override
    public int deleteFuncById(long funcId) throws SystemException {
        GnFunc func = queryFuncById(funcId);
        if(func==null){
            return -1;
        }else{
            GnFunc gnFunc = new GnFunc();
            gnFunc.setFuncId(funcId);
            gnFunc.setState(SysConstants.GnFunc.FUNC_INVALID_STATE);
           return MapperFactory.getGnFuncMapper().updateByPrimaryKeySelective(gnFunc);
        }
    }

    @Override
    public List<GnFunc> querySonFuncById(long funcId) {
        GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
        GnFuncCriteria sql = new GnFuncCriteria();
        GnFuncCriteria.Criteria criteria = sql.createCriteria();
        criteria.andParentFuncIdEqualTo(funcId);
        return funcMapper.selectByExample(sql);
    }

	@Override
	public List<GnFunc> queryFuncInfoList(GnFuncCriteria criteria) throws SystemException {
		GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
		return funcMapper.selectByExample(criteria);
	}

	@Override
	public int queryFuncCount(GnFuncCriteria criteria) throws SystemException {
		GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
		return funcMapper.countByExample(criteria);
	}

	@Override
	public List<GnFunc> querySonFuncById(List<Long> funcIdList) throws SystemException {
		if(funcIdList == null || funcIdList.size()==0){
			return null;
		}
		GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
		GnFuncCriteria example=new GnFuncCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andParentFuncIdIn(funcIdList);
		return funcMapper.selectByExample(example);
	}

	@Override
	public int deleteFuncByListId(List<Long> funcIdList) throws SystemException {
		if(funcIdList == null || funcIdList.size()==0){
			return 0;
		}
		GnFuncMapper funcMapper = MapperFactory.getGnFuncMapper();
		GnFuncCriteria example=new GnFuncCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andFuncIdIn(funcIdList);
		return funcMapper.deleteByExample(example);
	}

}
