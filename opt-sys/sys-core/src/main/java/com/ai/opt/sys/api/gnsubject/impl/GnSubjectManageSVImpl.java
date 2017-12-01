package com.ai.opt.sys.api.gnsubject.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectManageSV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedParamVO;
import com.ai.opt.sys.constants.ExceptCodeConstants;
import com.ai.opt.sys.service.business.interfaces.IGnSubjectBusiSV;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
@Service(validation = "true")
@Component
public class GnSubjectManageSVImpl implements IGnSubjectManageSV {
	@Autowired
	private IGnSubjectBusiSV gnSubjectBusiSV; 
	
	@Override
	public BaseResponse addGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = null;
		if(null == vo.getSubjectName()){
			throw new BusinessException("empty","科目名称不能为空");
		}
		if(null != vo.getSubjectName() && vo.getSubjectName().length() > 64){
			throw new BusinessException("max_long_64","科目名称长度不能超过64");
		}
		if(null == vo.getSubjectType()){
			throw new BusinessException("empty","科目类型不能为空");
		}
		if(null == vo.getIndustryCode()){
			throw new BusinessException("empty","行业编码不能为空");
		}
		
		
		if(null != vo.getSubjectDesc() && vo.getSubjectDesc().length() > 255){
			throw new BusinessException("max_long_255","科目描述长度不能超过255");
		}
		
		int total = this.gnSubjectBusiSV.getGnSubjectName(vo.getTenantId(), 000000, vo.getSubjectName());
		if(total > 0){
			responseHeader = new ResponseHeader(false,"false","当前租户下科目名称已存在");
			response.setResponseHeader(responseHeader);
			//
			return response;
		}
		this.gnSubjectBusiSV.addGnSubject(vo);
		responseHeader = new ResponseHeader(true,"success","添加成功");
		response.setResponseHeader(responseHeader);
		//
		return response;
	}

	@Override
	public BaseResponse delGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = null;
		if(null == vo.getSubjectId()){
			throw new BusinessException("empty","产品id不能为空");
		}
		if(null == vo.getTenantId()){
			throw new BusinessException("empty","租户id不能为空");
		}
		if(null == vo.getIndustryCode()){
			throw new BusinessException("empty","行业类型编码不能为空");
		}
		this.gnSubjectBusiSV.delGnSubject(vo);
		
		responseHeader = new ResponseHeader(true,"success","删除成功");
		response.setResponseHeader(responseHeader);
		//
		return response;

	}

	@Override
	public BaseResponse updateGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = null;
		if(!StringUtil.isBlank(vo.getSubjectDesc()) && vo.getSubjectDesc().length() > 255){
			throw new BusinessException("max_long_255","科目描述长度不能超过255");
		}
		int total = this.gnSubjectBusiSV.getGnSubjectName(vo.getTenantId(), vo.getSubjectId(), vo.getSubjectName());
		if(total > 0){
			responseHeader = new ResponseHeader(false,"false","当前租户下科目名称已存在");
			response.setResponseHeader(responseHeader);
			//
			return response;
		}
		this.gnSubjectBusiSV.updateGnSubject(vo);
		responseHeader = new ResponseHeader(true,"success","修改成功");
		response.setResponseHeader(responseHeader);
		//
		return response;
	}

	@Override
	public void updateRelatedGnSubject(GnSubjectRelatedParamVO vo) throws BusinessException, SystemException {
		this.gnSubjectBusiSV.updateRelatedGnSubject(vo);
	}

}
