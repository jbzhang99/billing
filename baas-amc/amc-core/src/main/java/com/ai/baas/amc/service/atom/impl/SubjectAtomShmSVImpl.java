package com.ai.baas.amc.service.atom.impl;

import com.ai.baas.amc.service.atom.interfaces.ISubjectAtomSV;
import com.ai.baas.amc.util.CacheClientUtil;
import com.ai.baas.amc.vo.SubjectFeeVo;
import com.ai.baas.amc.vo.SubjectFundVo;
import com.ai.baas.amc.vo.SubjectVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * 从共享内存中获取消费科目定义
 * Created by jackieliu on 16/4/1.
 */
@Component
public class SubjectAtomShmSVImpl implements ISubjectAtomSV {
    private static Logger LOGGER = LoggerFactory.getLogger(SubjectAtomShmSVImpl.class);
    private static final CacheClientUtil cacheClient = CacheClientUtil.getInstance();

    /**
     * 科目定义(编码)表表名
     */
    private static final String SUBJECT_TABLE = "gn_subject";
    /**
     * 消费科目定义表表名
     */
    private static final String SUBJECT_FEE_TABLE = "gn_subject_fee";
    /**
     * 资金科目定义表表名
     */
    private static final String SUBJECT_FUND_TABLE = "gn_subject_fund";

    @Override
    public SubjectVo getSubjectByTenantAndSubject(String tenantId, Long subjectId) {
        List<Map<String, String>> result = getByTenantAndSubject(tenantId, subjectId);
        return getSubjectObject(result);
    }

    @Override
    public SubjectFeeVo getFeeBySubjectId(Long subjectId) {
        try{
            Map<String, String> params = new TreeMap<String, String>();
            params.put("SUBJECT_ID",subjectId.toString());

            List<Map<String, String>> result = cacheClient.doQuery(SUBJECT_FEE_TABLE, params);
            return getObject(result);
        }catch (Exception e){
            LOGGER.error("从共享内存中获取消费科目定义异常",e);
            throw new SystemException("OMC-RULE0001B",e.getMessage());
        }
    }

    @Override
    public SubjectFundVo getFundByTenantAndSubject(String tenantId, Long subjectId) {
        try{
            Map<String, String> params = new TreeMap<String, String>();
            params.put("SUBJECT_ID",subjectId.toString());
            params.put("TENANT_ID",tenantId);

            List<Map<String, String>> result = cacheClient.doQuery(SUBJECT_FUND_TABLE, params);
            return getFunObject(result);
        }catch (Exception e){
            LOGGER.error("从共享内存中获取资金科目定义异常",e);
            throw new SystemException("OMC-RULE0001B",e.getMessage());
        }
    }

    private List<Map<String, String>> getByTenantAndSubject(String tenantId, Long subjectId){
        try{
            Map<String, String> params = new TreeMap<String, String>();
            params.put("SUBJECT_ID",subjectId.toString());
            params.put("TENANT_ID",tenantId);

            return cacheClient.doQuery(SUBJECT_TABLE, params);
        }catch (Exception e){
            LOGGER.error("从共享内存中获取科目编码定义异常",e);
            throw new SystemException("OMC-RULE0001B",e.getMessage());
        }
    }

    /**
     * 转换为科目定义对象
     *
     * @param result
     * @return
     */
    private SubjectVo getSubjectObject(List<Map<String, String>> result){
        //如果共享内存中不包含数据,则list包含一个空的map,此时也应该返回null
        if (result==null||result.isEmpty()||result.get(0).isEmpty()){
            return null;
        }
        String[] subjectIds =	StringUtils.split(result.get(0).get("subject_id"),"#");
        String[] tenantIds = StringUtils.split(result.get(0).get("tenant_id"),"#");
        String[] subjectNames = StringUtils.split(result.get(0).get("subject_name"),"#");
        String[] subjectDescs = StringUtils.split(result.get(0).get("subject_desc"),"#");
        String[] subjectTypes = StringUtils.split(result.get(0).get("subject_type"),"#");
        String[] goodsTypes = StringUtils.split(result.get(0).get("goods_type"),"#");
        String[] resSubjectPris = StringUtils.split(result.get(0).get("res_subject_pri"),"#");
        String[] unitNames = StringUtils.split(result.get(0).get("unit_name"),"#");
        String[] createTimes = StringUtils.split(result.get(0).get("create_time"),"#");
        String[] createOperIds = StringUtils.split(result.get(0).get("create_oper_id"),"#");
        String[] chnlIds = StringUtils.split(result.get(0).get("chnl_id"),"#");
        String[] taxTypes = StringUtils.split(result.get(0).get("tax_type"),"#");
        SubjectVo subjectVo = null;
        if (subjectIds.length>0){
            subjectVo = new SubjectVo();
            subjectVo.setSubjectId(Long.parseLong(subjectIds[0]));
            subjectVo.setChnlId(chnlIds[0]);
            subjectVo.setCreateOperId(createOperIds[0]);
            String createTime = StringUtils.isBlank(createTimes[0])?"1900-01-01 00:00:00.000":createTimes[0];
            subjectVo.setCreateTime(Timestamp.valueOf(createTime));
            subjectVo.setGoodsType(goodsTypes[0]);
            subjectVo.setResSubjectPri(resSubjectPris[0]);
            subjectVo.setSubjectDesc(subjectDescs[0]);
            subjectVo.setSubjectName(subjectNames[0]);
            subjectVo.setSubjectType(subjectTypes[0]);
            if (taxTypes[0]!=null && !"NULL".equalsIgnoreCase(taxTypes[0])){
                subjectVo.setTaxType(Integer.parseInt(taxTypes[0]));}
            subjectVo.setTenantId(tenantIds[0]);
            subjectVo.setUnitName(unitNames[0]);
        }
        return subjectVo;
    }

    /**
     * 转换为消费科目对象
     * @param result
     * @return
     */
    private SubjectFeeVo getObject(List<Map<String, String>> result){
        //如果共享内存中不包含数据,则list包含一个空的map,此时也应该返回null
        if (result==null||result.isEmpty()||result.get(0).isEmpty()){
            return null;
        }
        String[] subjectIds =	StringUtils.split(result.get(0).get("subject_id"),"#");
        String[] settlePris = StringUtils.split(result.get(0).get("settle_pri"),"#");
        String[] calScores = StringUtils.split(result.get(0).get("cal_score"),"#");
        SubjectFeeVo subjectFeeVo = null;
        if (subjectIds.length>0){
            subjectFeeVo = new SubjectFeeVo();
            subjectFeeVo.setSubjectId(Long.parseLong(subjectIds[0]));
            subjectFeeVo.setSettlePri(Long.parseLong(settlePris[0]));
            subjectFeeVo.setCalScore(calScores[0]);
        }

        return subjectFeeVo;
    }

    /**
     * 转换为资金科目对象
     * @param result
     * @return
     */
    private SubjectFundVo getFunObject(List<Map<String, String>> result){
        //如果共享内存中不包含数据,则list包含一个空的map,此时也应该返回null
        if (result==null||result.isEmpty()||result.get(0).isEmpty()){
            return null;}
        String[] subjectIds =	StringUtils.split(result.get(0).get("subject_id"),"#");
        String[] tenantIds = StringUtils.split(result.get(0).get("tenant_id"),"#");
        String[] fundTypes = StringUtils.split(result.get(0).get("fund_type"),"#");
        String[] isCashs = StringUtils.split(result.get(0).get("is_cash"),"#");
        String[] useModes = StringUtils.split(result.get(0).get("use_mode"),"#");
        String[] canSettleAlls = StringUtils.split(result.get(0).get("can_settle_all"),"#");
        String[] validTypes = StringUtils.split(result.get(0).get("valid_type"),"#");
        String[] usePris = StringUtils.split(result.get(0).get("use_pri"),"#");
        String[] refundRates = StringUtils.split(result.get(0).get("refund_rate"),"#");
        String[] canTranses = StringUtils.split(result.get(0).get("can_trans"),"#");
        String[] canCleanFunds = StringUtils.split(result.get(0).get("can_clean_fund"),"#");
        String[] canDelBooks = StringUtils.split(result.get(0).get("can_del_book"),"#");
        String[] calScores = StringUtils.split(result.get(0).get("cal_score"),"#");
        String[] printModes = StringUtils.split(result.get(0).get("print_mode"),"#");
        SubjectFundVo subjectFundVo = null;
        if (subjectIds.length>0 && tenantIds.length>0){
            SubjectVo subjectVo = getSubjectByTenantAndSubject(tenantIds[0],Long.parseLong(subjectIds[0]));
            if (subjectVo == null) {
                LOGGER.error("未在共享内存中获取科目编码定义,租户id:{0},科目id:{1}",tenantIds[0],subjectIds[0]);
                throw new BusinessException("OMC-RULE0001B",
                        "获取科目编码定义错误,租户id:"+tenantIds[0]+",科目id:"+subjectIds[0]);
            }
            subjectFundVo = new SubjectFundVo();
            subjectFundVo.setTenantId(tenantIds[0]);
            subjectFundVo.setSubjectId(Long.parseLong(subjectIds[0]));
            subjectFundVo.setSubjectName(subjectVo.getSubjectName());
            subjectFundVo.setSubjectDesc(subjectVo.getSubjectDesc());
            subjectFundVo.setSubjectType(subjectVo.getSubjectType());
            subjectFundVo.setUnitName(subjectVo.getUnitName());
            subjectFundVo.setCreateTime(subjectVo.getCreateTime());
            subjectFundVo.setCreateOperId(subjectVo.getCreateOperId());
            subjectFundVo.setChnlId(subjectVo.getChnlId());
            subjectFundVo.setTaxType(subjectVo.getTaxType());
            subjectFundVo.setFundType(fundTypes[0]);
            subjectFundVo.setIsCash(isCashs[0]);
            subjectFundVo.setUseMode(useModes[0]);
            subjectFundVo.setCanSettleAll(canSettleAlls[0]);
            subjectFundVo.setValidType(validTypes[0]);
            subjectFundVo.setUsePri(Long.parseLong(usePris[0]));
            subjectFundVo.setRefundRate(Long.parseLong(refundRates[0]));
            subjectFundVo.setCanTrans(canTranses[0]);
            subjectFundVo.setCanCleanFund(canCleanFunds[0]);
            subjectFundVo.setCanDelBook(canDelBooks[0]);
            subjectFundVo.setCalScore(calScores[0]);
            subjectFundVo.setPrintMode(printModes[0]);
        }

        return subjectFundVo;
    }

}
