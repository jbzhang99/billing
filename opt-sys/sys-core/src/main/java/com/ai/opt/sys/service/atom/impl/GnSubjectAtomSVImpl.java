package com.ai.opt.sys.service.atom.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.dao.mapper.bo.GnSubject;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectKey;
import com.ai.opt.sys.dao.mapper.factory.MapperFactory;
import com.ai.opt.sys.dao.mapper.interfaces.GnSubjectMapper;
import com.ai.opt.sys.service.atom.interfaces.IGnSubjectAtomSV;
import com.ai.paas.ipaas.util.StringUtil;


@Component
public class GnSubjectAtomSVImpl implements IGnSubjectAtomSV {

    @Override
    public GnSubject queryGnSubject(String tenantId, String industryCode, long subjectId) {
        GnSubjectKey key = new GnSubjectKey();
        key.setIndustryCode(industryCode);
        key.setSubjectId(subjectId);
        key.setTenantId(tenantId);
        return MapperFactory.getGnSubjectMapper().selectByPrimaryKey(key);
    }

    @Override
    public List<GnSubject> queryGnSubject() {
        return MapperFactory.getGnSubjectMapper().selectByExample(new GnSubjectCriteria());
    }

    @Override
    public int addSubject(GnSubject vo) throws SystemException {
        return MapperFactory.getGnSubjectMapper().insertSelective(vo);
    }

    @Override
    public int delSubject(GnSubjectKey key) throws SystemException {
        return MapperFactory.getGnSubjectMapper().deleteByPrimaryKey(key);
    }

    @Override
    public PageInfo<GnSubject> queryGnSubject(String tenantId, String industryCode, Long subjectId,
            String subjectType, String subjectName, Integer pageNo, Integer pageSize) {
        GnSubjectCriteria example = new GnSubjectCriteria();
        GnSubjectCriteria.Criteria criteria = example.createCriteria();
        if (!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if (!StringUtil.isBlank(industryCode)) {
            criteria.andIndustryCodeEqualTo(industryCode);
        }
        if (subjectId != null) {
            criteria.andSubjectIdEqualTo(subjectId);
        }
        if (!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if (!StringUtil.isBlank(subjectType)) {
            criteria.andSubjectTypeEqualTo(subjectType);
        }
        if (!StringUtil.isBlank(subjectName)) {
            criteria.andSubjectNameLike("%" + subjectName + "%");
        }
        example.setOrderByClause("subject_id desc");
        if (pageNo != null && pageSize != null) {
            example.setLimitStart((pageNo - 1) * pageSize);
            example.setLimitEnd(pageSize);
        }
        
        PageInfo<GnSubject> pageInfo = new PageInfo<GnSubject>();
        GnSubjectMapper mapper = MapperFactory.getGnSubjectMapper();
        pageInfo.setResult(mapper.selectByExample(example));
        pageInfo.setCount(mapper.countByExample(example));
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }

	@Override
	public void updateByPrimaryKeySelective(GnSubject gnSubject) throws SystemException {
		// TODO Auto-generated method stub
		 MapperFactory.getGnSubjectMapper().updateByPrimaryKeySelective(gnSubject);
	}

	@Override
	public GnSubject selectByPrimaryKey(GnSubjectKey key) throws SystemException {
		// TODO Auto-generated method stub
		return MapperFactory.getGnSubjectMapper().selectByPrimaryKey(key);
	}

	@Override
	public List<GnSubject> getGnSubjectListMayRelated(GnSubject gnSubject) {
		// TODO Auto-generated method stub
		GnSubjectCriteria gnSubjectCriteria = new GnSubjectCriteria();
		GnSubjectCriteria.Criteria criteria = gnSubjectCriteria.createCriteria();
		//if(!StringUtil.isBlank(gnSubject.getTenantId())){
			criteria.andTenantIdEqualTo(gnSubject.getTenantId());
		//}
		if(!StringUtil.isBlank(gnSubject.getIndustryCode())){
			criteria.andIndustryCodeEqualTo(gnSubject.getIndustryCode());
		}
		//if(!StringUtil.isBlank(gnSubject.getSubjectType())){
			criteria.andSubjectTypeEqualTo(gnSubject.getSubjectType());
		//}
		return MapperFactory.getGnSubjectMapper().selectByExample(gnSubjectCriteria);
		//return MapperFactory.getGnSubjectMapper().getGnSubjectListMayRelated(gnSubject);
	}

	@Override
	public GnSubject getGnSubject(String tenantId, long subjectId) {
		GnSubjectCriteria example = new GnSubjectCriteria();
		GnSubjectCriteria.Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andSubjectIdEqualTo(subjectId);
		//
		List<GnSubject> gnSubjectList = MapperFactory.getGnSubjectMapper().selectByExample(example);
		GnSubject gnSubject = new GnSubject();
		if(!CollectionUtil.isEmpty(gnSubjectList)){
			gnSubject = gnSubjectList.get(0);
		}
		return gnSubject;
	}
	
	public int getGnSubjectName(String tenantId,long subjectId,String subjectName){
		int total = 0;
		//
		GnSubjectCriteria example = new GnSubjectCriteria();
		GnSubjectCriteria.Criteria criteria = example.createCriteria();
		//当前租户编号下的所有信息
		criteria.andTenantIdEqualTo(tenantId);
		//不包含当前科目编号的信息
		if(!StringUtil.isBlank(String.valueOf(subjectId))){
			criteria.andSubjectIdNotEqualTo(subjectId);
		}
		//根据科目名称查询
		criteria.andSubjectNameEqualTo(subjectName);
		
		List<GnSubject> gnSubjectList = MapperFactory.getGnSubjectMapper().selectByExample(example);
		
		if(!CollectionUtil.isEmpty(gnSubjectList)){
			total = gnSubjectList.size();
		}
		//
		return total;
	}

	@Override
	public List<GnSubject> getGnSubjectListRelated(String tenantId,List<Long> subjectIdList) {
		GnSubjectCriteria example = new GnSubjectCriteria();
		GnSubjectCriteria.Criteria criteria = example.createCriteria();
		criteria.andTenantIdEqualTo(tenantId);
		criteria.andSubjectIdIn(subjectIdList);
		
		return MapperFactory.getGnSubjectMapper().selectByExample(example);
	}
}
