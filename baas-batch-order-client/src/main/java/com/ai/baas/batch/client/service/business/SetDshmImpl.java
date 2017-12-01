package com.ai.baas.batch.client.service.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.baas.batch.client.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpExtInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpFactorInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPackageInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfo;
import com.ai.baas.batch.client.util.BatchConstants;
import com.ai.baas.batch.client.util.BusinessUtil;
import com.ai.baas.batch.client.util.DshmUtil;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

public class SetDshmImpl {

    private static final Log LOG = LogFactory.getLog(SetDshmImpl.class);
    
    public static void CpPriceInfoDshm(CpPriceInfo cpPriceInfo){
//        JSONObject json = new JSONObject();
//        SimpleDateFormat dfor=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        json.put(BatchConstants.ACTIVE_STATUS, cpPriceInfo.getActiveStatus());
//        json.put(BatchConstants.ACTIVE_TIME, dfor.format(cpPriceInfo.getActiveTime()));
//        json.put(BatchConstants.CHARGE_TYPE, cpPriceInfo.getChargeType());
//        json.put(BatchConstants.COMMENTS, cpPriceInfo.getComments());
//        json.put(BatchConstants.CREATE_TIME, dfor.format(cpPriceInfo.getCreateTime()));
//        json.put(BatchConstants.INACTIVE_TIME, dfor.format(cpPriceInfo.getInactiveTime()));
//        json.put(BatchConstants.PRICE_CODE, cpPriceInfo.getPriceCode());
//        json.put(BatchConstants.PRODUCT_TYPE, cpPriceInfo.getProductType());
//        json.put(BatchConstants.TENANT_ID, cpPriceInfo.getTenantId());
        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_PRICE_INFO, JSON.toJSONString(BusinessUtil.assebleDshmData(cpPriceInfo)), 1);
  
    }
    public static void CpPriceDetailDshm(CpPriceDetail cpPriceDetail){
//        JSONObject json = new JSONObject();
//        SimpleDateFormat dfor=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
//        json.put(BatchConstants.ACTIVE_TIME, dfor.format(cpPriceDetail.getActiveTime()));
//        json.put(BatchConstants.INACTIVE_TIME, dfor.format(cpPriceDetail.getInactiveTime()));
//        json.put(BatchConstants.CHARGE_TYPE, cpPriceDetail.getChargeType());
//        json.put(BatchConstants.COMMENTS, cpPriceDetail.getComments());
//        json.put(BatchConstants.DETAIL_CODE, cpPriceDetail.getDetailCode());
//        json.put(BatchConstants.PRICE_CODE, cpPriceDetail.getPriceCode());
//        json.put(BatchConstants.SERVICE_TYPE, cpPriceDetail.getServiceType());
//        json.put(BatchConstants.DETAIL_ID, cpPriceDetail.getDetailId().toString());
        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_PRICE_DETAIL,JSON.toJSONString(BusinessUtil.assebleDshmData(cpPriceDetail)), 1);
        
    }
    public static void CpCunitPriceInfoDshm(CpCunitpriceInfo cpCunitInfo){
//        JSONObject json = new JSONObject();
//        json.put("cunit_price_code",cpCunitInfo.getCunitPriceCode());
//        json.put("price_product_type", cpCunitInfo.getPriceProductType());
//        json.put("price_value", cpCunitInfo.getPriceValue());
//        json.put("unit_type", cpCunitInfo.getUnitType());
//        json.put("subject_code", cpCunitInfo.getSubjectCode());
//        json.put("price_name", cpCunitInfo.getPriceName());
//        json.put("factor_code", cpCunitInfo.getFactorCode());
//        json.put("ext_code", cpCunitInfo.getExtCode());
//        json.put("id", cpCunitInfo.getId());
        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_CUNITPRICE_INFO, JSON.toJSONString(BusinessUtil.assebleDshmData(cpCunitInfo)), 1);
    }
    
    public static void CpCunitPriceDetailDshm(CpCunitpriceDetail cpCunitDetail){
//        JSONObject json = new JSONObject();      
//        json.put("factor_name", cpCunitDetail.getFactorName());
//        json.put("factor_value", cpCunitDetail.getFactorValue());
//        json.put("price_product_type", cpCunitDetail.getPriceProductType());
//        json.put("cunit_price_code", cpCunitDetail.getCunitPriceCode());        
////        DshmUtil.getIdshmSV().initDel(TableCon.BL_USERINFO, json.toString());
//        // 如果subsId为空则认为需要插入，否则执行更新
//        json.toString();
         DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_CUNITPRICE_DETAIL, JSON.toJSONString(BusinessUtil.assebleDshmData(cpCunitDetail)),1);        
    }
    
    public static void CpStepInfoDshmOld(CpStepInfo cpStepInfo){
          JSONObject json = new JSONObject();      
          json.put("cal_type", cpStepInfo.getCalType());
          json.put("detail_code", cpStepInfo.getDetailCode());
          json.put("ext_code", cpStepInfo.getExtCode());
          json.put("factor_code", cpStepInfo.getFactorCode());
          json.put("is_price_equal", cpStepInfo.getIsPriceEqual());   
          json.put("is_total_price", cpStepInfo.getIsTotalPrice());   
          json.put("price_value", cpStepInfo.getPriceValue());   
          json.put("section_a", cpStepInfo.getSectionA());   
          json.put("section_b", cpStepInfo.getSectionB());   
          json.put("service_type", cpStepInfo.getServiceType());    
          json.put("step_group", cpStepInfo.getStepGroup());   
          json.put("step_seq", cpStepInfo.getStepSeq());   
          json.put("subject_code", cpStepInfo.getSubjectCode());   
          json.put("total_price_value", cpStepInfo.getTotalPriceValue());  
          json.put("unit_type", cpStepInfo.getUnitType());   
          json.toString();
          LOG.info("【cp_step_info, old】"+json.toString());
          DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_STEP_INFO,  json.toString(),1);        
      
  }
    public static void CpStepInfoDshm(CpStepInfo cpStepInfo){
        JSONObject json = new JSONObject();      
        json.put("cal_type", cpStepInfo.getCalType());
        json.put("detail_code", cpStepInfo.getDetailCode());
        json.put("ext_code", cpStepInfo.getExtCode());
        json.put("factor_code", cpStepInfo.getFactorCode());
        json.put("is_price_equal", cpStepInfo.getIsPriceEqual());   
        json.put("is_total_price", cpStepInfo.getIsTotalPrice());   
        json.put("price_value", cpStepInfo.getPriceValue());   
        json.put("section_a", cpStepInfo.getSectionA());   
        json.put("section_b", cpStepInfo.getSectionB());   
        json.put("service_type", cpStepInfo.getServiceType());    
        json.put("step_group", cpStepInfo.getStepGroup());   
        json.put("step_seq", cpStepInfo.getStepSeq());   
        json.put("subject_code", cpStepInfo.getSubjectCode());   
        json.put("total_price_value", cpStepInfo.getTotalPriceValue());  
        json.put("unit_type", cpStepInfo.getUnitType());   
        json.toString();
        LOG.info("【cp_step_info, old】"+json.toString());
        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_STEP_INFO,  json.toString(),1);   
        
        
        
        //自动匹配刷缓存字段
//         LOG.info("【cp_step_info】"+JSON.toJSONString(BusinessUtil.assebleDshmData(cpStepInfo)));
//         DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_STEP_INFO,  JSON.toJSONString(BusinessUtil.assebleDshmData(cpStepInfo)),1);        
        
    }
    public static void CpFactorInfoDshm(CpFactorInfo cpFactorInfo) {
//        JSONObject json = new JSONObject();      
//        json.put("factor_code", cpFactorInfo.getFactorCode());
//        json.put("factor_info_id", cpFactorInfo.getFactorInfoId());
//        json.put("factor_name", cpFactorInfo.getFactorName());
//        json.put("factor_value", cpFactorInfo.getFactorValue());
//        json.put("tenant_id", cpFactorInfo.getTenantId());
        
        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_FACTOR_INFO,  JSON.toJSONString(BusinessUtil.assebleDshmData(cpFactorInfo)),1);     
    }
    
    
    
    public static void CpExtInfoDshm(CpExtInfo cpExtInfo) {
//        JSONObject json = new JSONObject();      
//        json.put("ext_code", cpExtInfo.getExtCode());
//        json.put("ext_name", cpExtInfo.getExtName());
//        json.put("ext_owner", cpExtInfo.getExtOwner());
//        json.put("ext_type", cpExtInfo.getExtType());
//        json.put("ext_value", cpExtInfo.getExtValue());
//        json.put("tenant_id", cpExtInfo.getTenantId());
//        json.put("id", cpExtInfo.getId());      
        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_EXT_INFO,  JSON.toJSONString(BusinessUtil.assebleDshmData(cpExtInfo)),1);     
    }
    public static void BlUserInfoZX(BlUserinfoZx blUserinfoZx) {
//        JSONObject json = new JSONObject();
//        SimpleDateFormat dfor=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
//        json.put("create_time", dfor.format(blUserinfoZx.getCreateTime()));
//        json.put("instance_id", blUserinfoZx.getInstanceId());
//        json.put("service_id", blUserinfoZx.getServiceId());
//        json.put("user_id", blUserinfoZx.getUserId());
        DshmUtil.getIdshmSV().initLoader("bl_userinfo_zx", JSON.toJSONString(BusinessUtil.assebleDshmData(blUserinfoZx)),1);     
    }
    public static void CpPackageInfoDshm(CpPackageInfo cpPackageInfo) {   
//        JSONObject json = new JSONObject();      
//        json.put("amount", cpPackageInfo.getAmount());
//        json.put("cycle_interval", cpPackageInfo.getCycleInterval());
//        json.put("cycle_type", cpPackageInfo.getCycleType());
//        json.put("detail_code", cpPackageInfo.getDetailCode());
//        json.put("exceed_type", cpPackageInfo.getExceedType());
//        json.put("ext_code", cpPackageInfo.getExtCode());
//        json.put("factor_code", cpPackageInfo.getFactorCode());
//        json.put("is_total_price", cpPackageInfo.getIsTotalPrice());
//        json.put("package_id", cpPackageInfo.getPackageId());
//        json.put("price_value", cpPackageInfo.getPriceValue());
//        json.put("service_type", cpPackageInfo.getServiceType());
//        json.put("subject_code", cpPackageInfo.getSubjectCode());
//        json.put("total_price_value", cpPackageInfo.getTotalPriceValue());
//        json.put("unit_code", cpPackageInfo.getUnitCode());
//        json.put("unit_price_code", cpPackageInfo.getUnitpriceCode());
//        json.put("unit_type", cpPackageInfo.getUnitType());

        DshmUtil.getIdshmSV().initLoader(BatchConstants.CP_PACKAGE_INFO, JSON.toJSONString(BusinessUtil.assebleDshmData(cpPackageInfo)),1);        
    }
}
