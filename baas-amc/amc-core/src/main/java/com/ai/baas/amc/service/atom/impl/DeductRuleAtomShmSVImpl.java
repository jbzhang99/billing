package com.ai.baas.amc.service.atom.impl;

import com.ai.baas.amc.service.atom.interfaces.IDeductRuleAtomSV;
import com.ai.baas.amc.util.CacheClientUtil;
import com.ai.baas.amc.vo.DeductRuleVo;
import com.ai.opt.base.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * 从共享内存中获取销账规则
 * Created by jackieliu on 16/3/31.
 */
@Component
public class DeductRuleAtomShmSVImpl implements IDeductRuleAtomSV {
    private static Logger LOGGER = LoggerFactory.getLogger(DeductRuleAtomShmSVImpl.class);
    private static final CacheClientUtil cacheClient = CacheClientUtil.getInstance();
    //销账规则表表名
    private static final String SHM_TABLE_NAME = "amc_deduct_rule";


    @Override
    public List<DeductRuleVo> query(String tenantId, Long subjectId) {
        try{
            Map<String, String> params = new TreeMap<String, String>();
            params.put("FUND_SUBJECT",subjectId.toString());
            params.put("TENANT_ID",tenantId);

            List<Map<String, String>> result = cacheClient.doQuery(SHM_TABLE_NAME, params);
            if(result!=null){
            	LOGGER.info("获取到缓存中的销账规则信息：["+result.size()+"]条");
            }
            if(result == null || result.size()==0){
                throw new BusinessException("AMC-SUBS0001B",
                        "subs_user表没有找到用户信息!" + params.toString());
            }
            List<DeductRuleVo> resultList = getObject(result);
            if (resultList==null || resultList.isEmpty()){
            	throw new BusinessException("AMC-SUBS0003B",
                        SHM_TABLE_NAME+"表没有找到有效的销账规则信息!" + params.toString());
            }
            return resultList;
        }catch (Exception e){
            LOGGER.error("",e);
            throw new BusinessException("OMC-RULE0001B",e.getMessage());
        }
    }

    private List<DeductRuleVo> getObject(List<Map<String, String>> result){
        List<DeductRuleVo> objList = new ArrayList<DeductRuleVo>();
        
        for(Map<String, String> map : result){
          DeductRuleVo deductRule = new DeductRuleVo();
          deductRule.setTenantId(map.get("tenant_id"));
          deductRule.setFeeSubject(Long.parseLong(map.get("fee_subject")));
          deductRule.setFundSubject(Long.parseLong(map.get("fund_subject")));
          objList.add(deductRule);
        }

        if (objList.isEmpty()){
            objList = Collections.emptyList();
        }
        return objList;
    }
}
