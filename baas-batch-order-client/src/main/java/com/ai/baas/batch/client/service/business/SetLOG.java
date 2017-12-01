package com.ai.baas.batch.client.service.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpFactorInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfo;
import com.ai.baas.batch.client.service.atom.interfaces.ICpCunitpriceInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpExtInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpFactorInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceDetailAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpStepInfoAtom;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.bmc.api.pricemaking.params.Cost;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Component
public class SetLOG {
	@Autowired
	@Qualifier("cpFactorinfoAtom") 
	ICpFactorInfoAtom cpFactorInfoAtom;
	@Autowired
	@Qualifier("cpStepInfoAtom") 
	ICpStepInfoAtom cpStepInfoAtom;
	@Autowired
	@Qualifier("cpExtInfoAtom") 
	ICpExtInfoAtom cpExtInfoAtom;
	@Autowired
	@Qualifier("cpPriceInfoAtom")
	ICpPriceInfoAtom cpPriceInfoAtom;
	@Autowired
	@Qualifier("cpPriceDetailAtom")
	ICpPriceDetailAtom cpPriceDetailAtom;
	@Autowired
	@Qualifier("cpCunitpriceInfoAtom")
	ICpCunitpriceInfoAtom cpCunitpriceInfoAtom;
	private  String PRICE_CODE;    
    private  String DETAIL_CODE;
    private  Long PRICE_INFO_ID;
    private  Long DETAIL_ID;
    private  String TENANT_ID;
    private  String FACTOR_CODE;
    private  String INSTANCE_ID;
    private Long CUNIT_PRICE_INFO_ID;
    
    public List<String> writeLogProduct(String instance_id) {
        INSTANCE_ID = instance_id;        
        TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");   
        String logJson = PropertiesUtil.getValue("batch.order.step.log");   
        List<String> priceCodeList = new ArrayList<>(); 

        JSONObject rootObject = (JSONObject)JSONObject.parse(logJson);
        JSONObject priceRoot = rootObject.getJSONObject("price");
        JSONArray priceArray = priceRoot.getJSONArray("price_lists");   
        for(int i=0;i<priceArray.size();i++){
        	
        	JSONObject priceObject = priceArray.getJSONObject(i);
        	String billing_type = priceObject.getString("billing_type");
        	String record_type = priceObject.getString("record_type");
        	String subject_code = priceObject.getString("subject_code");
        	//-------------判断是step 还是 cunit--------------
        	getNewId();
        	priceCodeList.add(PRICE_CODE);
        	insertPriceInfoDetail();
        	insertFactorInfo(record_type);
        	switch (billing_type) {
			case "cunit":
				System.out.println("cunit");
	          	PricemakingResponseZX priceResZX = new PricemakingResponseZX();
				JSONObject detail_cost =new JSONObject();
                detail_cost.put("detail_costs", priceObject.getJSONArray("detail_costs"));
                priceResZX = JSON.parseObject(StringEscapeUtils.unescapeJava(detail_cost.toJSONString()), PricemakingResponseZX.class);
                List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
                for(Cost c : costList){
                	String priceUnit = c.getCost_unit();  
		            String priceValue = c.getCost_value();	      
		            insertCpCunitpriceInfo(priceValue, "LOG", priceUnit, subject_code);
                }
//				insertCpCunitpriceInfo(priceValue, "LOG", priceUnit, subject_code);
				break;
			case "step":
				System.out.println("step"); 
				JSONArray stepInfoList = priceObject.getJSONArray("step_info");
				for(int j=0;j<stepInfoList.size();j++){
	        		JSONObject stepInfo = stepInfoList.getJSONObject(j);
	        		String sectionA = stepInfo.getString("section_a");
	        		String sectionB = stepInfo.getString("section_b");
	        		String costValue = stepInfo.getString("cost_value");
	        		String costUnit = stepInfo.getString("cost_unit");
	        		insertStep(sectionA, sectionB, costValue, costUnit,subject_code,j+1);
	        	}
				break;
			}
        	
        }
        return priceCodeList;
    }
    
    private void insertStep(String sectionA,String sectionB,String value,String unit,String subject_code,int stepSeq){
	 	CpStepInfo cpStepInfo = new CpStepInfo();
	    cpStepInfo.setCalType("C"); //F:固定值 C:累计值
        cpStepInfo.setDetailCode(DETAIL_CODE);
//	        cpStepInfo.setExtCode("");
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(subject_code);
        cpStepInfo.setUnitType(unit); //单位
        cpStepInfo.setSectionA(Double.valueOf(sectionA));//0~50TB
        cpStepInfo.setSectionB(Double.valueOf(sectionB));
        cpStepInfo.setPriceValue(Double.parseDouble(value));
        cpStepInfo.setStepSeq((long)stepSeq);
        cpStepInfo.setStepGroup("1");
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
	}
    
    private void insertPriceInfoDetail(){
        //CP_PRICE_DETAIL表
        CpPriceDetail cpPriceDetail = new CpPriceDetail();
        cpPriceDetail.setDetailId(DETAIL_ID);
        cpPriceDetail.setActiveTime(DateUtil.getTimestamp("20150101120000",DateUtil.YYYYMMDDHHMMSS));
        cpPriceDetail.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,DateUtil.YYYYMMDDHHMMSS));
        cpPriceDetail.setDetailCode(DETAIL_CODE);
        cpPriceDetail.setPriceCode(PRICE_CODE);
        cpPriceDetail.setChargeType("STEP");
        cpPriceDetailAtom.insert(cpPriceDetail);
        SetDshmImpl.CpPriceDetailDshm(cpPriceDetail); 
        //CP_PRICE_INFO表
        CpPriceInfo cpPriceInfo = new CpPriceInfo();
        cpPriceInfo.setPriceInfoId(PRICE_INFO_ID); 
        cpPriceInfo.setActiveStatus("ACTIVE");
        cpPriceInfo.setChargeType("STEP");
        cpPriceInfo.setComments("中信订购");
        cpPriceInfo.setPriceCode(PRICE_CODE);
        cpPriceInfo.setProductType("LOG");
        cpPriceInfo.setTenantId(TENANT_ID);
        cpPriceInfo.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));       
        cpPriceInfo.setActiveTime(DateUtil.getTimestamp("20150101120000",DateUtil.YYYYMMDDHHMMSS));
        cpPriceInfo.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,DateUtil.YYYYMMDDHHMMSS));
        
        int productPriority = 100;
//        if(chargeType.equals("PACKAGE")){
//            productPriority = 90;
//        }
        cpPriceInfo.setProductPriority(productPriority);
        
        cpPriceInfoAtom.insert(cpPriceInfo);
        SetDshmImpl.CpPriceInfoDshm(cpPriceInfo); 
    }
    
    
    private void insertFactorInfo(String recordType){
        CpFactorInfo cpFactorInfo = new CpFactorInfo();
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("instance_id");
        cpFactorInfo.setFactorValue(INSTANCE_ID);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoAtom.insert(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
          
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("record_type");
        cpFactorInfo.setFactorValue(recordType);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoAtom.insert(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
    }
    
    private  void insertCpCunitpriceInfo(String priceValue,String serviceType,String priceUnit,String subjectCode) {
        CUNIT_PRICE_INFO_ID = SeqUtil.getNewId("CP_CUNITPRICE_INFO$ID$SEQ");
        
        Double price = Double.parseDouble(priceValue);
        
        CpCunitpriceInfo cUnitInfo = new CpCunitpriceInfo();
        cUnitInfo.setId(CUNIT_PRICE_INFO_ID); 
        cUnitInfo.setCunitPriceCode(DETAIL_CODE);
        cUnitInfo.setFactorCode(FACTOR_CODE);
        cUnitInfo.setPriceName("中信复合单价");
        cUnitInfo.setPriceProductType(serviceType);
        cUnitInfo.setPriceValue(price);
        cUnitInfo.setUnitType(priceUnit);       
        cUnitInfo.setSubjectCode(subjectCode);
        cpCunitpriceInfoAtom.insert(cUnitInfo);   
        SetDshmImpl.CpCunitPriceInfoDshm(cUnitInfo);
       
    }
    
    private void getNewId(){
    	FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
        DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
        PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
        
        DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
        PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
    }
}
