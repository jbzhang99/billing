package com.ai.opt.sys.api.subject.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
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
 * @author fanpw
 */
@Path("/subjectquery")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface ISubjectQuerySV {
    
	/**
     * 查询科目名称，翻译科目ID[全部科目] <br>
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectquery/getSubjectName
     */
    @GET
    @Path("/getSubjectName")
    SubjectNameQueryResponse getSubjectName(SubjectIdParam param) throws BusinessException, SystemException;
    
    /**
     * 查询科目定义[全部科目] <br>
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectquery/getSubject
     */
    @GET
    @Path("/getSubject")
    SubjectQueryResponse getSubject(SubjectIdParam param) throws BusinessException, SystemException;
    
    /**
     * 根据科目类型查询科目[全部科目]<br>
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectquery/querySubjectByType
     */
    @GET
    @Path("/querySubjectByType")
    SubjectQueryByTypeResponse querySubjectByType(SubjectTypeParam param) throws BusinessException, SystemException;

    /**
     * 根据科目ID查询[资金科目] <br>
     * @param param
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     * @RestRelativeURL subjectquery/getSubjectFund
     */
    @GET
    @Path("/getSubjectFund")
    SubjectFundQueryResponse getSubjectFund(SubjectIdParam param) throws BusinessException, SystemException;
}
