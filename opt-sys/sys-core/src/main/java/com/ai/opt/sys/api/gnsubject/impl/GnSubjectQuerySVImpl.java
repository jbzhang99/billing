package com.ai.opt.sys.api.gnsubject.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailViewResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedRequest;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;
import com.ai.opt.sys.service.business.interfaces.IGnSubjectBusiSV;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
@Service(validation = "true")
@Component
public class GnSubjectQuerySVImpl implements IGnSubjectQuerySV {
	@Autowired
	private IGnSubjectBusiSV gnSubjectBusiSV;
	
	
	@Override
	public PageInfo<GnSubjectListResponse> getGnSubjectList(GnSubjectQueryVo vo)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return this.gnSubjectBusiSV.getGnSubjectList(vo);
	}


	@Override
	public GnSubjectInfoVo getGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(null == vo.getSubjectId()){
			throw new BusinessException("empty","产品id不能为空");
		}
		if(null == vo.getTenantId()){
			throw new BusinessException("empty","租户id不能为空");
		}
		if(null == vo.getIndustryCode()){
			throw new BusinessException("empty","行业类型编码不能为空");
		}
		return this.gnSubjectBusiSV.getGenSubject(vo);
	}


	@Override
	public List<GnSubjectListResponse> getGnSubjectListMayRelated(GnSubjectDetailVo vo)
			throws BusinessException, SystemException {
		GnSubjectInfoVo response = new GnSubjectInfoVo();
		
		if(null == vo.getTenantId()){
			
			throw new BusinessException("tenantId is not null","租户id不能为空");
		}
		if(null == vo.getSubjectType()){
			throw new BusinessException("SubjectType is not null","科目类型不能为空");
		}
//		if(null == vo.getIndustryCode()){
//			throw new BusinessException("IndustryCode is not null","行业类型不能为空");
//		}
		return this.gnSubjectBusiSV.getGnSubjectListMayRelated(vo);
	}


	@Override
	public List<GnSubjectListResponse> getGnSubjectListRelated(GnSubjectRelatedDetailVo vo)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return this.gnSubjectBusiSV.getGnSubjectListRelated(vo);
		
	}


	@Override
	public GnSubjectRelatedDetailViewResponse getRelatedBillInfo(String tenantId, Long drSubjectId,String industryCode)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return this.gnSubjectBusiSV.getRelatedBillInfo(tenantId, drSubjectId, industryCode);
	}


	@Override
	public GnSubjectRelatedDetailViewResponse queryRelatedGnSubject(GnSubjectRelatedRequest request) throws BusinessException, SystemException {

		return this.gnSubjectBusiSV.queryRelatedGnSubject(request);
	}


	@Override
	public GnSubjectInfoVo getGnSubjectByTenantIdSubjectId(GnSubjectTenantIdSubjectIdRequest vo)
			throws BusinessException, SystemException {
		if(StringUtil.isBlank(vo.getTenantId())){
			throw new BusinessException("empty", "租户编号不能为空");
		}
		if(StringUtil.isBlank(vo.getSubjectId())){
			throw new BusinessException("empty", "科目编号不能为空");
		}
		return this.gnSubjectBusiSV.getGnSubjectByTenantIdSubjectId(vo);
		
	}

}
