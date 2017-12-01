package com.ai.opt.sys.api.gnsubject.interfaces;

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
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedRequest;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;
import com.ai.opt.sys.api.subject.param.SubjectFundQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectIdParam;
import com.ai.opt.sys.api.subject.param.SubjectNameQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryByTypeResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectTypeParam;

/**
 * 科目查询服务.<br>
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author zhangzd
 */
public interface IGnSubjectQuerySV {
	/**
	 * 分页查询账单科目/详单科目
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode  SYS_1001
	 */
	PageInfo<GnSubjectListResponse> getGnSubjectList(GnSubjectQueryVo vo) throws BusinessException, SystemException;
	
	/**
	 * 查询单个账单科目/详单科目
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode SYS_1002
	 */
	GnSubjectInfoVo getGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	/**
	 * 根据租户id和科目id查询科目信息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode SYS_1011
	 */
	GnSubjectInfoVo getGnSubjectByTenantIdSubjectId(GnSubjectTenantIdSubjectIdRequest vo) throws BusinessException, SystemException;
	/**
	 * 查询可以关联的详单科目列表
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode SYS_1006
	 */
	List<GnSubjectListResponse> getGnSubjectListMayRelated(GnSubjectDetailVo vo) throws BusinessException, SystemException;
	@interface getGnSubjectListMayRelated{}
	/**
	 * 
	 * @param 查询已关联的详单信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode SYS_1007
	 */
	List<GnSubjectListResponse> getGnSubjectListRelated(GnSubjectRelatedDetailVo vo) throws BusinessException, SystemException;
	/**
	 * 
	 * @return 此方法作废
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode SYS_1008
	 */
	GnSubjectRelatedDetailViewResponse getRelatedBillInfo(String tenantId,Long drSubjectId,String industryCode) throws BusinessException, SystemException;
	/**
	 * 账单科目管理>>关联详单科目
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangzd
	 * @ApiDocMethod
	 * @ApiCode SYS_1009
	 */
	GnSubjectRelatedDetailViewResponse queryRelatedGnSubject(GnSubjectRelatedRequest request)throws BusinessException,SystemException;
	@interface QueryRelatedGnSubject{}
}
