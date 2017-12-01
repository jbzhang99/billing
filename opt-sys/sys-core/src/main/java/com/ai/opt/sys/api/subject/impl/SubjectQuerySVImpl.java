package com.ai.opt.sys.api.subject.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.subject.interfaces.ISubjectQuerySV;
import com.ai.opt.sys.api.subject.param.Subject;
import com.ai.opt.sys.api.subject.param.SubjectFund;
import com.ai.opt.sys.api.subject.param.SubjectFundQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectIdParam;
import com.ai.opt.sys.api.subject.param.SubjectNameQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryByTypeResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectTypeParam;
import com.ai.opt.sys.constants.ExceptCodeConstants;
import com.ai.opt.sys.dao.mapper.bo.GnSubject;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFund;
import com.ai.opt.sys.dao.mapper.bo.GnTenant;
import com.ai.opt.sys.service.atom.interfaces.IGnSubjectAtomSV;
import com.ai.opt.sys.service.atom.interfaces.IGnSubjectFundAtomSV;
import com.ai.opt.sys.service.business.interfaces.ITenantBusiSV;
import com.ai.opt.sys.util.SubjectUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 科目查询服务.<br>
 *
 * Date: 2016年3月28日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Service
@Component
public class SubjectQuerySVImpl implements ISubjectQuerySV {

    private static final Logger LOG = LogManager.getLogger(SubjectQuerySVImpl.class);
    
    @Autowired
    private  ITenantBusiSV tenantBusiSV;
    
    @Autowired
    private IGnSubjectFundAtomSV gnSubjectFundAtomSV;
    
    @Autowired
    private IGnSubjectAtomSV gnSubjectAtomSV;

    /**
     * 查询科目名称
     */
    @Override
    public SubjectNameQueryResponse getSubjectName(SubjectIdParam param) throws BusinessException,
            SystemException {
        LOG.info("科目名称查询开始");
        if (param == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(param.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (param.getSubjectId() == 0l) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "科目ID不能为空");
        }
        String industryCode = this.getIndustryCode(param);
        if (StringUtil.isBlank(industryCode)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取租户所在行业出错， 请检查租户配置表！");
        }
        String subjectName = SubjectUtil.getGnSubjectName(industryCode, param.getTenantId(),
                param.getSubjectId());
        SubjectNameQueryResponse response = new SubjectNameQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        response.setSubjectName(subjectName);
        LOG.info("科目名称查询结束");
        return response;
    }

    /**
     * 查询科目定义[全部科目] 
     */
    @Override
    public SubjectQueryResponse getSubject(SubjectIdParam param) throws BusinessException,
            SystemException {
        LOG.info("科目查询开始");
        if (param == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(param.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (param.getSubjectId() == 0l) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "科目ID不能为空");
        }
        String industryCode = this.getIndustryCode(param);
        if (StringUtil.isBlank(industryCode)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取租户所在行业出错， 请检查租户配置表！");
        }
        JSONObject subjectJson = SubjectUtil.getGnSubject(industryCode, param.getTenantId(),
                param.getSubjectId());
        SubjectQueryResponse response = new SubjectQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        if (!subjectJson.isEmpty()) {
            response.setSubject(JSON.parseObject(subjectJson.toJSONString(), Subject.class));
        }
        LOG.info("科目查询结束");
        return response;
    }

    /**
     * 根据科目类型查询科目[全部科目]
     */
    @Override
    public SubjectQueryByTypeResponse querySubjectByType(SubjectTypeParam param)
            throws BusinessException, SystemException {
        LOG.info("根据科目类型查询科目开始");
        if (param == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(param.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (StringUtil.isBlank(param.getSubjectType())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "科目类型不能为空");
        }
        String industryCode = this.getIndustryCode(param);
        if (StringUtil.isBlank(industryCode)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取租户所在行业出错， 请检查租户配置表！");
        }
        JSONArray subjectArray = SubjectUtil.getGnSubject(industryCode, param.getTenantId(),
                param.getSubjectType());
        SubjectQueryByTypeResponse response = new SubjectQueryByTypeResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        List<Subject> subjectList = new ArrayList<Subject>();
        if (!subjectArray.isEmpty()) {
            subjectList = JSON.parseArray(subjectArray.toJSONString(), Subject.class);
        }
        response.setSubjectList(subjectList);
        LOG.info("根据科目类型查询科目结束");
        return response;
    }

    /**
     * 根据科目ID查询[资金科目]
     */
    @Override
    public SubjectFundQueryResponse getSubjectFund(SubjectIdParam param) throws BusinessException,
            SystemException {
        LOG.info("资金科目查询开始");
        if (param == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(param.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if (param.getSubjectId() == 0l) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "资金科目ID不能为空");
        }
        String industryCode = this.getIndustryCode(param);
        if (StringUtil.isBlank(industryCode)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取租户所在行业出错， 请检查租户配置表！");
        }

        JSONObject subjectFundJson = SubjectUtil.getGnSubjectFund(industryCode,
                param.getTenantId(), param.getSubjectId());
        
        SubjectFundQueryResponse response = new SubjectFundQueryResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setResponseHeader(responseHeader);
        if (!subjectFundJson.isEmpty()) {
            response.setSubjectFund(JSON.parseObject(subjectFundJson.toJSONString(),
                    SubjectFund.class));
        }
        /**增加逻辑：如果换成中没取到，则取数据库*/
        else{
            LOG.info("===========未从缓存中取到科目信息，从数据库中直接获取=========");
            GnSubjectFund funSubjectFund = gnSubjectFundAtomSV.queryGnSubjectFund(param.getTenantId(), industryCode, param.getSubjectId());
            SubjectFund returnJson = JSON.parseObject(JSON.toJSONString(funSubjectFund),SubjectFund.class);
            GnSubject gnSubject= gnSubjectAtomSV.queryGnSubject(param.getTenantId(), industryCode,  param.getSubjectId());
            if(gnSubject!=null){
                returnJson.setSubjectName(gnSubject.getSubjectName());
                returnJson.setSubjectType(gnSubject.getSubjectType());
            }
            response.setSubjectFund(returnJson);
        }
        LOG.info("资金科目查询结束");
        return response;
    }
    
    private  String getIndustryCode(BaseInfo tenantInfo) {
      GnTenant tenant  = tenantBusiSV.queryByTenantId(tenantInfo.getTenantId());
      if(tenant != null ) {
          return tenant.getIndustryCode();
      }
      return null;
  }
}
