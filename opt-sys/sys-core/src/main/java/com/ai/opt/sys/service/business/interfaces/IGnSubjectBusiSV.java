package com.ai.opt.sys.service.business.interfaces;

import java.util.List;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailViewResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedParamVO;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedRequest;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;

public interface IGnSubjectBusiSV {
	PageInfo<GnSubjectListResponse> getGnSubjectList(GnSubjectQueryVo vo) throws BusinessException, SystemException;
	public void addGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	public void delGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	public void updateGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	public GnSubjectInfoVo getGenSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	public List<GnSubjectListResponse> getGnSubjectListMayRelated(GnSubjectDetailVo vo) throws BusinessException, SystemException;
	public List<GnSubjectListResponse> getGnSubjectListRelated(GnSubjectRelatedDetailVo vo)throws BusinessException, SystemException;
	public GnSubjectRelatedDetailViewResponse getRelatedBillInfo(String tenantId, Long drSubjectId,String industryCode)throws BusinessException, SystemException;
	public GnSubjectRelatedDetailViewResponse queryRelatedGnSubject(GnSubjectRelatedRequest request)throws BusinessException, SystemException;
	
	public void updateRelatedGnSubject(GnSubjectRelatedParamVO vo);
	public GnSubjectInfoVo getGnSubjectByTenantIdSubjectId(GnSubjectTenantIdSubjectIdRequest vo)
			throws BusinessException, SystemException;
	public int getGnSubjectName(String tenantId,long subjectId,String subjectName);
}
