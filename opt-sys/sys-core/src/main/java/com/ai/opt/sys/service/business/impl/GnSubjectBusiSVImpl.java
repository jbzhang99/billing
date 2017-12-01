package com.ai.opt.sys.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectManageSV;
import com.ai.baas.amc.api.amcdrbillsubject.interfaces.IAmcDrBillSubjectQuerySV;
import com.ai.baas.amc.api.amcdrbillsubject.param.AmcDrBillSubjectRelatedParamVO;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectParamVO;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectResponse;
import com.ai.baas.amc.api.amcdrbillsubject.param.QueryDrSubjectParamVO;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDrSubjectIdParamVO;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailViewResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedParamVO;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedRequest;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;
import com.ai.opt.sys.constants.ExceptCodeConstants;
import com.ai.opt.sys.dao.mapper.bo.GnSubject;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectKey;
import com.ai.opt.sys.service.atom.interfaces.IGnSubjectAtomSV;
import com.ai.opt.sys.service.business.interfaces.IGnSubjectBusiSV;
import com.esotericsoftware.minlog.Log;
@Service
@Transactional
public class GnSubjectBusiSVImpl implements IGnSubjectBusiSV {
	@Autowired
	private IGnSubjectAtomSV gnSubjectAtomSV; 
	public static final String BILL_SUBJECT_TYPE = "21";
	public static final String DR_SUBJECT_TYPE = "2";
	@Override
	public PageInfo<GnSubjectListResponse> getGnSubjectList(GnSubjectQueryVo vo)
			throws BusinessException, SystemException {
		
		String tenantId = vo.getTenantId();
		String industryCode = vo.getIndustryCode();
		Long  subjectId = vo.getSubjectId();
		String subjectName = vo.getSubjectName();
		Integer pageNo = null == vo.getPageNo() ? 1 : vo.getPageNo();
		Integer pageSize = null == vo.getPageSize() ? 10 : vo.getPageSize();
		String subjectType = vo.getSubjectType();
		
		// TODO Auto-generated method stub
		PageInfo<GnSubject> gnSubjectPageInfo = this.gnSubjectAtomSV.queryGnSubject(tenantId, industryCode, subjectId, subjectType, subjectName, pageNo, pageSize);
		List<GnSubject> gnSubjectList = gnSubjectPageInfo.getResult();
		//
		List<GnSubjectListResponse> gnSubjectListResponseList = new ArrayList<GnSubjectListResponse>();
		GnSubjectListResponse gnSubjectListResponse = null;
		//
		for(GnSubject gnSubject : gnSubjectList){
			gnSubjectListResponse = new GnSubjectListResponse();
			gnSubjectListResponse.setSubjectId(gnSubject.getSubjectId());
			gnSubjectListResponse.setSubjectName(gnSubject.getSubjectName());
			gnSubjectListResponse.setSubjectDesc(gnSubject.getSubjectDesc());
			gnSubjectListResponse.setTenantId(gnSubject.getTenantId());
			gnSubjectListResponse.setIndustryCode(gnSubject.getIndustryCode());
			//
			gnSubjectListResponseList.add(gnSubjectListResponse);
		}
		//
		PageInfo<GnSubjectListResponse> gnSubjectListResponsePageInfo = new PageInfo<GnSubjectListResponse>();
		gnSubjectListResponsePageInfo.setCount(gnSubjectPageInfo.getCount());
		gnSubjectListResponsePageInfo.setPageCount(gnSubjectPageInfo.getPageCount());
		gnSubjectListResponsePageInfo.setPageNo(gnSubjectPageInfo.getPageNo());
		gnSubjectListResponsePageInfo.setPageSize(gnSubjectPageInfo.getPageSize());
		gnSubjectListResponsePageInfo.setResult(gnSubjectListResponseList);
		//
		return gnSubjectListResponsePageInfo;
	}

	@Override
	public void addGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		//if(StringUtil.isBlank(vo.getSubjectId().toString())){
			//throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL,"账单科目编号不能为空");
		//}
		if(StringUtil.isBlank(vo.getSubjectName())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL,"账单科目名称不能为空");
		}
		
		//
		GnSubject gnSubject = new GnSubject();
		gnSubject.setTenantId(vo.getTenantId());
		gnSubject.setSubjectType(vo.getSubjectType());
		gnSubject.setUnitName(StringUtil.isBlank(vo.getUnitName())?"":vo.getUnitName());
		gnSubject.setIndustryCode(vo.getIndustryCode());
		gnSubject.setSubjectId(new Long(SeqUtil.getNewId("GN_SUBJECT$SUBJECT_ID$SEQ")));
		Log.info("seq:"+SeqUtil.getNewId("GN_SUBJECT$SUBJECT_ID$SEQ"));
		gnSubject.setSubjectName(vo.getSubjectName());
		gnSubject.setSubjectDesc(vo.getSubjectDesc());
		gnSubject.setCreateTime(new Timestamp(System.currentTimeMillis()));
		//
		try{
			
			this.gnSubjectAtomSV.addSubject(gnSubject);
		//
		}catch(Exception e){
			e.getStackTrace();
			//throw new BusinessException(ExceptCodeConstants.FAILED,"操作失败");
		}
		
	}

	@Override
	public void delGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		GnSubjectKey gnSubjectKey = new GnSubjectKey();
		gnSubjectKey.setSubjectId(vo.getSubjectId());
		gnSubjectKey.setTenantId(vo.getTenantId());
		gnSubjectKey.setIndustryCode(vo.getIndustryCode());
		//
		try{
			this.gnSubjectAtomSV.delSubject(gnSubjectKey);
		}catch(Exception e){
			//e.getStackTrace();
			throw new BusinessException(ExceptCodeConstants.FAILED,"操作失败");
		}
	}

	@Override
	public void updateGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		GnSubject gnSubject = new GnSubject();
		//
		gnSubject.setTenantId(vo.getTenantId());
		gnSubject.setIndustryCode(vo.getIndustryCode());
		gnSubject.setSubjectId(vo.getSubjectId());
		//
		gnSubject.setSubjectName(vo.getSubjectName());
		gnSubject.setSubjectDesc(vo.getSubjectDesc());
		//
		
		this.gnSubjectAtomSV.updateByPrimaryKeySelective(gnSubject);
		
		
	}

	@Override
	public GnSubjectInfoVo getGenSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException {
		GnSubjectKey gnSubjectKey = new GnSubjectKey();
		gnSubjectKey.setSubjectId(vo.getSubjectId());
		gnSubjectKey.setTenantId(vo.getTenantId());
		gnSubjectKey.setIndustryCode(vo.getIndustryCode());
		//
		GnSubject gnSubject = this.gnSubjectAtomSV.selectByPrimaryKey(gnSubjectKey);
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		if(null == gnSubject){
			return gnSubjectInfoVo;
		}else{
		//
			
			gnSubjectInfoVo.setTenantId(gnSubject.getTenantId());
			gnSubjectInfoVo.setSubjectId(gnSubject.getSubjectId());
			gnSubjectInfoVo.setIndustryCode(gnSubject.getIndustryCode());
			gnSubjectInfoVo.setSubjectDesc(gnSubject.getSubjectDesc());
			gnSubjectInfoVo.setSubjectName(gnSubject.getSubjectName());
			gnSubjectInfoVo.setSubjectType(gnSubject.getSubjectType());
		}
		//
		return gnSubjectInfoVo;
	}

	@Override
	public List<GnSubjectListResponse> getGnSubjectListMayRelated(GnSubjectDetailVo vo)
			throws BusinessException, SystemException {
		GnSubject gnSubject = new GnSubject();
		gnSubject.setTenantId(vo.getTenantId());
		gnSubject.setIndustryCode(vo.getIndustryCode());
		gnSubject.setSubjectType(vo.getSubjectType());
		//
		List<GnSubject> gnSubjectList = this.gnSubjectAtomSV.getGnSubjectListMayRelated(gnSubject);
		//
		List<GnSubjectListResponse> gnSubjectMayRelatedList = new ArrayList<GnSubjectListResponse>();
		GnSubjectListResponse  gnSubjectListResponse = null;
		for(GnSubject gnSubjectNew : gnSubjectList){
			gnSubjectListResponse = new GnSubjectListResponse();
			gnSubjectListResponse.setSubjectId(gnSubjectNew.getSubjectId());
			gnSubjectListResponse.setIndustryCode(gnSubjectNew.getIndustryCode());
			gnSubjectListResponse.setSubjectName(gnSubjectNew.getSubjectName());
			gnSubjectListResponse.setTenantId(gnSubjectNew.getTenantId());
			//
			gnSubjectMayRelatedList.add(gnSubjectListResponse);
		}
		//
		return gnSubjectMayRelatedList;
	}

	@Override
	public List<GnSubjectListResponse> getGnSubjectListRelated(GnSubjectRelatedDetailVo vo)
			throws BusinessException, SystemException {

		List<String> subjetIdList = vo.getDrSubjectId();
		List<Long> newSubjectIdList = new ArrayList<Long>();
		for(String str : subjetIdList){
			newSubjectIdList.add(new Long(str));
		}
		//
		List<GnSubject> gnSubjectList = new ArrayList<GnSubject>();
		if(!StringUtil.isBlank(vo.getTenantId()) && !CollectionUtil.isEmpty(vo.getDrSubjectId())){
		
			gnSubjectList = this.gnSubjectAtomSV.getGnSubjectListRelated(vo.getTenantId(), newSubjectIdList);
		}
		//
		List<GnSubjectListResponse> gnSubjectRelatedList = new ArrayList<GnSubjectListResponse>();
		GnSubjectListResponse  gnSubjectListResponse = null;
		for(GnSubject gnSubjectNew : gnSubjectList){
			gnSubjectListResponse = new GnSubjectListResponse();
			gnSubjectListResponse.setSubjectId(gnSubjectNew.getSubjectId());
			gnSubjectListResponse.setIndustryCode(gnSubjectNew.getIndustryCode());
			gnSubjectListResponse.setSubjectName(gnSubjectNew.getSubjectName());
			gnSubjectListResponse.setTenantId(gnSubjectNew.getTenantId());
			//
			gnSubjectRelatedList.add(gnSubjectListResponse);
		}
		//
		return gnSubjectRelatedList;
		
	}

	@Override
	public GnSubjectRelatedDetailViewResponse getRelatedBillInfo(String tenantId, Long drSubjectId,String industryCode)
			throws BusinessException, SystemException {
		//1、调用amc的服务，返回账单id-----------待调用
		
		//2、根据返回的账单id查询账单信息
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		gnSubjectInfoVo.setIndustryCode(industryCode);
		gnSubjectInfoVo.setSubjectId(drSubjectId);
		gnSubjectInfoVo.setTenantId(tenantId);
		//
		GnSubjectInfoVo gnSubjectInfoVoNew = this.getGenSubject(gnSubjectInfoVo);
		Long billSubjectId = gnSubjectInfoVoNew.getSubjectId();
		String subjectName = gnSubjectInfoVoNew.getSubjectName();
		String subjectDesc = gnSubjectInfoVoNew.getSubjectDesc();
		
		//3、根据租户id和行业编码和科目类型查询可以选择的详单列表
		GnSubjectDetailVo gnSubjectDetailVo = new GnSubjectDetailVo();
		gnSubjectDetailVo.setTenantId(tenantId);
		gnSubjectDetailVo.setIndustryCode(industryCode);
		gnSubjectDetailVo.setSubjectType(gnSubjectInfoVoNew.getSubjectType());
		//
		List<GnSubjectListResponse> mayRelatedList = this.getGnSubjectListMayRelated(gnSubjectDetailVo);
		
		//4、根据租户id和账单id查询详单id列表信息-----------待调用
		List<String> drSubjectIdAmc = new ArrayList<String>();
		
		//5、根据详单id列表和租户id查询详单信息
		GnSubjectRelatedDetailVo gnSubjectRelatedDetailVo = new GnSubjectRelatedDetailVo();
		gnSubjectRelatedDetailVo.setTenantId(tenantId);
		gnSubjectRelatedDetailVo.setDrSubjectId(drSubjectIdAmc);
		
		List<GnSubjectListResponse> relatedList = this.getGnSubjectListRelated(gnSubjectRelatedDetailVo);
		
		//返回整体信息
		GnSubjectRelatedDetailViewResponse gnSubjectRelatedDetailViewResponse = new GnSubjectRelatedDetailViewResponse();
		gnSubjectRelatedDetailViewResponse.setBillSubjectId(billSubjectId);
		gnSubjectRelatedDetailViewResponse.setTenantId(tenantId);
		gnSubjectRelatedDetailViewResponse.setSubjectName(subjectName);
		gnSubjectRelatedDetailViewResponse.setSubjectDesc(subjectDesc);
		gnSubjectRelatedDetailViewResponse.setMayRelatedList(mayRelatedList);
		gnSubjectRelatedDetailViewResponse.setRelatedList(relatedList);
		
		
		return gnSubjectRelatedDetailViewResponse;
	}

	@Override
	public GnSubjectRelatedDetailViewResponse queryRelatedGnSubject(GnSubjectRelatedRequest request) throws BusinessException, SystemException {
		String industryCode = request.getIndustryCode();
		String subjectId = request.getSubjectId();
		String tenantId = request.getTenantId();
		
		//2、根据返回的账单id查询账单信息
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		gnSubjectInfoVo.setIndustryCode(industryCode);
		gnSubjectInfoVo.setSubjectId(new Long(subjectId));
		gnSubjectInfoVo.setTenantId(tenantId);
		//
		GnSubjectInfoVo gnSubjectInfoVoNew = this.getGenSubject(gnSubjectInfoVo);
		Long billSubjectId = gnSubjectInfoVoNew.getSubjectId();
		String subjectName = gnSubjectInfoVoNew.getSubjectName();
		String subjectDesc = gnSubjectInfoVoNew.getSubjectDesc();
		
		//3、根据租户id和行业编码和科目类型查询可以选择的详单列表
		GnSubjectDetailVo gnSubjectDetailVo = new GnSubjectDetailVo();
		gnSubjectDetailVo.setTenantId(tenantId);
		gnSubjectDetailVo.setIndustryCode(industryCode);
		gnSubjectDetailVo.setSubjectType(DR_SUBJECT_TYPE);
		//
		List<GnSubjectListResponse> mayRelatedList = this.getGnSubjectListMayRelated(gnSubjectDetailVo);
		
		//4、根据租户id和账单id查询详单id列表信息-----------待调用
		List<String> drSubjectIdAmc = new ArrayList<String>();
		//
		QueryDrSubjectParamVO queryDrSubjectParamVO = new QueryDrSubjectParamVO();
		queryDrSubjectParamVO.setBillSubjectId(billSubjectId.toString());
		queryDrSubjectParamVO.setTenantId(tenantId);
		//
		List<DrSubjectResponse> drSubjectResponseList = DubboConsumerFactory.getService(IAmcDrBillSubjectQuerySV.class).queryDrSubjectIdList(queryDrSubjectParamVO);
		for(DrSubjectResponse drSubjectResponse : drSubjectResponseList){
			drSubjectIdAmc.add(drSubjectResponse.getDrSubjectId());
		}
		
		//5、根据详单id列表和租户id查询详单信息
		GnSubjectRelatedDetailVo gnSubjectRelatedDetailVo = new GnSubjectRelatedDetailVo();
		gnSubjectRelatedDetailVo.setTenantId(tenantId);
		gnSubjectRelatedDetailVo.setDrSubjectId(drSubjectIdAmc);
		
		List<GnSubjectListResponse> relatedList = this.getGnSubjectListRelated(gnSubjectRelatedDetailVo);
		
		//返回整体信息
		GnSubjectRelatedDetailViewResponse gnSubjectRelatedDetailViewResponse = new GnSubjectRelatedDetailViewResponse();
		gnSubjectRelatedDetailViewResponse.setBillSubjectId(billSubjectId);
		gnSubjectRelatedDetailViewResponse.setTenantId(tenantId);
		gnSubjectRelatedDetailViewResponse.setSubjectName(subjectName);
		gnSubjectRelatedDetailViewResponse.setSubjectDesc(subjectDesc);
		gnSubjectRelatedDetailViewResponse.setMayRelatedList(mayRelatedList);
		gnSubjectRelatedDetailViewResponse.setRelatedList(relatedList);
		//
		return gnSubjectRelatedDetailViewResponse;
	}

	@Override
	@Transactional
	public void updateRelatedGnSubject(GnSubjectRelatedParamVO vo) {
		AmcDrBillSubjectRelatedParamVO amcDrBillSubjectRelatedParamVO = new AmcDrBillSubjectRelatedParamVO();
		amcDrBillSubjectRelatedParamVO.setTenantId(vo.getTenantId());
		amcDrBillSubjectRelatedParamVO.setBillSubjectId(vo.getBillSubjectId());
		
		List<GnSubjectDrSubjectIdParamVO> gnSubjectDrSubjectIdParamVOList = vo.getDrSubjectParamVOList();
		//
		List<DrSubjectParamVO> drSubjectParamVOList = new ArrayList<DrSubjectParamVO>();
		DrSubjectParamVO drSubjectParamVO = null;
		if(!CollectionUtil.isEmpty(gnSubjectDrSubjectIdParamVOList)){
			for(GnSubjectDrSubjectIdParamVO gnSubjectDrSubjectIdParamVODb : gnSubjectDrSubjectIdParamVOList){
				drSubjectParamVO = new DrSubjectParamVO();
				drSubjectParamVO.setDrSubjectId(gnSubjectDrSubjectIdParamVODb.getDrSubjectId());
				//
				drSubjectParamVOList.add(drSubjectParamVO);
			}
			//
			amcDrBillSubjectRelatedParamVO.setDrSubjectParamVOList(drSubjectParamVOList);
		}
		//
		
		//删除原有数据
		//根据租户id和账单id查询详单id列表信息 amc服务中 已关联的详单
		List<String> drSubjectIdAmc = new ArrayList<String>();
		//
		QueryDrSubjectParamVO queryDrSubjectParamVO = new QueryDrSubjectParamVO();
		queryDrSubjectParamVO.setBillSubjectId(vo.getBillSubjectId());
		queryDrSubjectParamVO.setTenantId(vo.getTenantId());
		//
		List<DrSubjectResponse> drSubjectResponseList = DubboConsumerFactory.getService(IAmcDrBillSubjectQuerySV.class).queryDrSubjectIdList(queryDrSubjectParamVO);
		for(DrSubjectResponse drSubjectResponse : drSubjectResponseList){
			drSubjectIdAmc.add(drSubjectResponse.getDrSubjectId());
		}
		//需要删除原来的信息
		AmcDrBillSubjectRelatedParamVO amcDrBillSubjectRelatedParamVODel = new AmcDrBillSubjectRelatedParamVO();
		amcDrBillSubjectRelatedParamVODel.setTenantId(vo.getTenantId());
		amcDrBillSubjectRelatedParamVODel.setBillSubjectId(vo.getBillSubjectId());
		
		//
		List<DrSubjectParamVO> drSubjectParamVOListDel = new ArrayList<DrSubjectParamVO>();
		DrSubjectParamVO drSubjectParamVODel = null;
		if(!CollectionUtil.isEmpty(drSubjectIdAmc)){
			for(String drSubjectId : drSubjectIdAmc){
				drSubjectParamVODel = new DrSubjectParamVO();
				drSubjectParamVODel.setDrSubjectId(drSubjectId);
				//
				drSubjectParamVOListDel.add(drSubjectParamVODel);
			}
			//
			amcDrBillSubjectRelatedParamVODel.setDrSubjectParamVOList(drSubjectParamVOListDel);
		}
		
		//
		DubboConsumerFactory.getService(IAmcDrBillSubjectManageSV.class).delAmcDrBillSubject(amcDrBillSubjectRelatedParamVODel);
		//
		if(!CollectionUtil.isEmpty(vo.getDrSubjectParamVOList())){
			DubboConsumerFactory.getService(IAmcDrBillSubjectManageSV.class).addAmcDrBillSubject(amcDrBillSubjectRelatedParamVO);
		}
	}

	@Override
	public GnSubjectInfoVo getGnSubjectByTenantIdSubjectId(GnSubjectTenantIdSubjectIdRequest vo)
			throws BusinessException, SystemException {
		String tenantId = vo.getTenantId();
		String subjectId = vo.getSubjectId();
		//
		GnSubjectInfoVo gnSubjectInfoVo = new GnSubjectInfoVo();
		GnSubject gnSubject = this.gnSubjectAtomSV.getGnSubject(tenantId, new Long(subjectId));
		//
		if(null != gnSubject){
			gnSubjectInfoVo.setIndustryCode(gnSubject.getIndustryCode());
			gnSubjectInfoVo.setSubjectDesc(gnSubject.getSubjectDesc());
			gnSubjectInfoVo.setSubjectId(gnSubject.getSubjectId());
			gnSubjectInfoVo.setSubjectName(gnSubject.getSubjectName());
			gnSubjectInfoVo.setSubjectType(gnSubject.getSubjectType());
			gnSubjectInfoVo.setTenantId(gnSubject.getTenantId());
			gnSubjectInfoVo.setUnitName(gnSubject.getUnitName());
		}
		//
		return gnSubjectInfoVo;
	}
	
	public int getGnSubjectName(String tenantId,long subjectId,String subjectName){
		return this.gnSubjectAtomSV.getGnSubjectName(tenantId, subjectId, subjectName);
	}
	 
}
