package com.ai.opt.sys.api.gnsubject.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectRelatedParamVO;
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
@Path("/subjectmanage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IGnSubjectManageSV {
	
	/**
     * 添加账单科目/详单科目
     * @param insertRequest
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectmanage/addGnSubject
     */
    @GET
    @Path("/addGnSubject")
	public BaseResponse addGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	@interface addGnSubject{}

	/**
     * 删除账单科目/详单科目
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectmanage/delGnSubject
     */
    @GET
    @Path("/delGnSubject")
	public BaseResponse delGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;
	@interface delGnSubject{}

	/**
     * 更新账单科目/详单科目
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectmanage/updateGnSubject
     */
    @GET
    @Path("/updateGnSubject")
	public BaseResponse updateGnSubject(GnSubjectInfoVo vo) throws BusinessException, SystemException;

    /**
     * 账单科目管理>>修改关联详单科目
     * @param vo
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangzd
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectmanage/updateRelatedGnSubject
     */
    @GET
    @Path("/updateRelatedGnSubject")
	public void updateRelatedGnSubject(GnSubjectRelatedParamVO vo) throws BusinessException,SystemException;
	@interface UpdateRelatedGnSubject{}
}
