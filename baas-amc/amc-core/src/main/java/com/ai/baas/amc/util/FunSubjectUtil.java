package com.ai.baas.amc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.baas.amc.vo.SubjectVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.api.subject.param.Subject;
import com.ai.opt.sys.api.subject.param.SubjectFund;
import com.ai.opt.sys.api.subject.param.SubjectFundQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectIdParam;
import com.ai.opt.sys.api.subject.param.SubjectNameQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryByTypeResponse;
import com.ai.opt.sys.api.subject.param.SubjectQueryResponse;
import com.ai.opt.sys.api.subject.param.SubjectTypeParam;

/**
 * 科目工具类
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public final class FunSubjectUtil {

    private FunSubjectUtil() {
        
    }
    
    /**
     * 获取科目
     * @param tenantId
     * @param subjectId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static SubjectVo getSubject(String tenantId, Long subjectId) {
        SubjectIdParam param = new SubjectIdParam();
        param.setTenantId(tenantId);
        param.setSubjectId(subjectId);
        SubjectQueryResponse response = DubboUtil.getSubjectQuerySV().getSubject(param);
        if(response != null && response.getResponseHeader() != null && response.getResponseHeader().isSuccess()) {
            Subject subject = response.getSubject();
            if(subject == null) {
                return null;
            }
            SubjectVo vo = new SubjectVo();
            BeanUtils.copyProperties(vo, subject);
            return vo;
        }
        
        return null;
    }
    
    /**
     * 按照科目类型查询科目列表 <br>
     * 
     * @param tenantId
     * @param subjectType
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static List<SubjectVo> getSubjectByType(String tenantId, String subjectType) {
        SubjectTypeParam param = new SubjectTypeParam();
        param.setTenantId(tenantId);
        param.setSubjectType(subjectType);
        SubjectQueryByTypeResponse response = DubboUtil.getSubjectQuerySV().querySubjectByType(param);
        List<SubjectVo> subjectVoList = new ArrayList<SubjectVo>();
        if(response != null && response.getResponseHeader() != null && response.getResponseHeader().isSuccess()) {
            List<Subject> subjectList = response.getSubjectList();
            if(CollectionUtil.isEmpty(subjectList)){
                return subjectVoList;
            }
            
            for(Subject subject:subjectList){
                SubjectVo vo = new SubjectVo();
                BeanUtils.copyProperties(vo, subject);
                subjectVoList.add(vo);
            }
        }
        
        return subjectVoList;
    }
    
    /**
     * 获取资金科目对象.<br>
     * 缓存对象融合了FUN_SUBJECT和FUN_SUBJECT_FUND
     * @param tenantId
     * @param subjectId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static SubjectFundVo getSubjectFund(String tenantId, Long subjectId) {
        SubjectIdParam param = new SubjectIdParam();
        param.setTenantId(tenantId);
        param.setSubjectId(subjectId);
        SubjectFundQueryResponse response = DubboUtil.getSubjectQuerySV().getSubjectFund(param);
        if(response != null && response.getResponseHeader() != null && response.getResponseHeader().isSuccess()) {
            SubjectFund subjectFund = response.getSubjectFund();
            if(subjectFund == null) {
                return null;
            }
            SubjectFundVo vo = new SubjectFundVo();
            BeanUtils.copyProperties(vo, subjectFund);
            return vo;
        }
        
        return null;
    }
    
    /**
     * 获取科目名称<br>
     * 
     * @param tenantId
     * @param subjectId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static String getSubjectName(String tenantId, Long subjectId) {
        SubjectIdParam param = new SubjectIdParam();
        param.setTenantId(tenantId);
        param.setSubjectId(subjectId);
        SubjectNameQueryResponse response = DubboUtil.getSubjectQuerySV().getSubjectName(param);
        if(response != null && response.getResponseHeader() != null && response.getResponseHeader().isSuccess()) {
            return response.getSubjectName();
        }
        
        return null;
    }
    
    private static final CacheClientUtil cacheClient = CacheClientUtil.getInstance();
    
    /**
     * 科目定义(编码)表表名
     */
    private static final String SUBJECT_TABLE = "gn_subject";

    /**
     * 翻译科目名称
     * @param subjectId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static String getSubjectNameFromDSHM(String tenantId, long subjectId) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("SUBJECT_ID", String.valueOf(subjectId));
        params.put("TENANT_ID", tenantId);
        List<Map<String, String>> resultList = null;
        try {
            resultList = cacheClient.doQuery(SUBJECT_TABLE, params);
        } catch(Exception ex) {
            throw new SystemException("从共享内存中获取科目名称失败！", ex);
        }
        
        if(CollectionUtil.isEmpty(resultList)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取不到对应的科目名称[" + subjectId + "]");
        }
        Map<String, String> subjectInfoMap = resultList.get(0);
        if(subjectInfoMap.isEmpty()) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "获取不到对应的科目名称[" + subjectId + "]");
        }
        return subjectInfoMap.get("subject_name");
    }
    
}
