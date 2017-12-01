package com.ai.baas.batch.client.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.interfaces.BlUserinfoZxMapper;
import com.ai.baas.batch.client.dao.interfaces.CpCunitpriceDetailMapper;
import com.ai.baas.batch.client.dao.interfaces.CpCunitpriceInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpExtInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpFactorInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPackageInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPriceDetailMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPriceInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpStepInfoMapper;
import com.ai.baas.batch.client.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpFactorInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPackageInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;
import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.prepareflow.checkutil.ServiceCheck;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.batch.client.service.atom.interfaces.IBlUserinfoZxAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpCunitpriceInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpExtInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpFactorInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPackageInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceDetailAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpStepInfoAtom;
import com.ai.baas.batch.client.service.business.SetDshmImpl;
import com.ai.baas.batch.client.service.business.SetLOG;
import com.ai.baas.batch.client.service.business.SetOSS;
import com.ai.baas.batch.client.service.business.SetSqlImpl;
import com.ai.baas.batch.client.service.business.interfaces.IOriginPriceSV;
import com.ai.baas.batch.client.util.BatchConstants;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.bmc.api.pricemaking.params.Cost;
import com.ai.baas.bmc.api.pricemaking.params.ElementInfo;
import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfo;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@Component
public class OriginPriceSVImpl implements IOriginPriceSV{

	@Autowired
	@Qualifier("blUserinfoZxAtom")
	IBlUserinfoZxAtom blUserinfoZxAtom;
	@Autowired
	@Qualifier("cpCunitpriceInfoAtom")
	ICpCunitpriceInfoAtom cpCunitpriceInfoAtom;
	@Autowired
	@Qualifier("cpPackageInfoAtom")
	ICpPackageInfoAtom cpPackageInfoAtom;
	@Autowired
	@Qualifier("cpPriceInfoAtom")
	ICpPriceInfoAtom cpPriceInfoAtom;
	@Autowired
	@Qualifier("cpPriceDetailAtom")
	ICpPriceDetailAtom cpPriceDetailAtom;
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
	SetOSS setOSS;
    @Autowired
    SetLOG setLOG;
    private static final Log LOG = LogFactory.getLog(SetSqlImpl.class);
    
    private String CUNIT_PRICE_CODE;   
    private Long CUNIT_PRICE_DETAIL_ID;
    private Long CUNIT_PRICE_INFO_ID;
    private Long PACKAGE_ID;
    private String PRICE_CODE;    
    private String DETAIL_CODE;
    private Long PRICE_INFO_ID;
    private Long DETAIL_ID;
    private String TENANT_ID;
    private String FACTOR_CODE;
    private Long EXT_CODE;
    private String INSTANCE_ID;
    private String REGION_ID;
    private String serviceType;
    private String billingMode; 

    @Override
    public  List<String> insertMsg(Shopping shopping,String userId) throws Exception{
        
    	serviceType = shopping.getServiceType();
    	billingMode = shopping.getBillingMode();
    	LOG.info("insert priceInfo");
    	LOG.info("billingMode: "+billingMode);
    	LOG.info("serviceType: "+serviceType);
        List<String>productIdLists =  writeSQLMsg2(shopping.getPricemaking(),shopping.getInstance_id(),shopping);
        /*
         * bl_userinfo_zx 沉淀
         */
        String productIds = collectProductID(productIdLists);
        LOG.info("productIds: "+productIds);
        LOG.info("insert zx_info");
        writeZXuserinfo(shopping.getInstance_id(),shopping.getServiceId(),userId,productIds);
        return productIdLists;

    }
    public  List<String> writeSQLMsg2(PricemakingResponseZX priceResZX, String instance_id,Shopping shopping) throws BatchException {
    	/*
    	 * 去掉serviceCheck,固定为 billingMode来区分
    	 */
//    	ServiceCheck serviceCheck = new ServiceCheck();

        INSTANCE_ID = instance_id; 
        TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");

        List<String> priceCodeList = new ArrayList<>(); 
        
        if(billingMode.equals(BatchConstants.Billing.PACKAGE)){
            List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
            for(Cost c : costList){
                int i = 1;
                String subjectCode = serviceType+"0"+i;
                String priceUnit = c.getCost_unit();  
                String priceValue = c.getCost_value();
                LOG.info("【"+subjectCode+"】"); 
                packageInfoBusiAssem(priceUnit,priceValue, priceResZX,subjectCode);
                cpPriceInfoBusiAssem(priceResZX,"PACKAGE");     
                priceCodeList.add(PRICE_CODE);
                i++;
            }
            return priceCodeList;
        }else if(billingMode.equals(BatchConstants.Billing.USAGE)){
        	/*
        	 * 科目相关的变量
        	 */
        	 String subjectCode = null;
	    	 String recordType = null;
	    	 
	         if(!shopping.getPmBasedataCode().getSubjectType().isEmpty()){
		          List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
		          for(Cost c : costList){
		              String priceUnit = c.getCost_unit();  
		              String priceValue = c.getCost_value();	              
		              PmBasedataCode basedataCode = shopping.getPmBasedataCode();
			      	  JSONObject root = JSON.parseObject(basedataCode.getSubjectType());
			      	  if(!root.containsKey(priceUnit)){
			      		 throw new BatchException("000010",serviceType+" unit match fail");
			      	  }
			    	  JSONObject subject = root.getJSONObject(priceUnit);
			    	  subjectCode = subject.getString(BatchConstants.Subject.SUBJECT_CODE);
			    	  recordType = subject.getString(BatchConstants.Subject.RECORD_TYPE);
		    		
		              LOG.info("["+subjectCode+"]"); 
		              cpCunitpriceBusiAssem(priceUnit,priceValue, priceResZX,subjectCode,recordType);
		              cpPriceInfoBusiAssem(priceResZX,"CUNIT");     
		              priceCodeList.add(PRICE_CODE);
		          }
		          return priceCodeList;
	         }
	         else if (shopping.getPmBasedataCode().getSubjectType().isEmpty()){
		          List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
		          for(Cost c : costList){
		              String priceUnit = c.getCost_unit();  
		              String priceValue = c.getCost_value();
		              subjectCode = serviceType+"01";
		              
		              LOG.info("["+subjectCode+"]"); 
		              cpCunitpriceBusiAssem(priceUnit,priceValue, priceResZX,subjectCode,recordType);
		              cpPriceInfoBusiAssem( priceResZX,"CUNIT");     
		              priceCodeList.add(PRICE_CODE);
		          }       
		          return priceCodeList;
	          }
         }else if (billingMode.equals(BatchConstants.Billing.STEP)){
//            checkRegionId(REGION_ID);
        	switch (serviceType) {
			case "OSS":
				LOG.info("【OSS】"); 
				priceCodeList = setOSS.writeOssProduct(INSTANCE_ID);
				break;
			case "LOG":
				LOG.info("【LOG】"); 
				priceCodeList = setLOG.writeLogProduct(INSTANCE_ID);
				break;
			default:
				break;
			}
            
            
            return priceCodeList;
        }
        return null;
    }
    
   private String collectProductID(List<String> priceCodeList) {
        StringBuilder builder = new StringBuilder();       
        for(String productId : priceCodeList){
            builder.append(productId);
            builder.append(";");
        }
        return builder.toString();
   }
    

   private void packageInfoBusiAssem(String priceUnit, String priceValue ,PricemakingResponseZX priceResZX, String subjectCode){
       PACKAGE_ID = SeqUtil.getNewId("CP_PACKAGE_INFO$PACKAGE_ID");
       FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
       CUNIT_PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
       Double price = Double.parseDouble(priceValue);
       
       CpPackageInfo cpPackageInfo = new CpPackageInfo();
       cpPackageInfo.setAmount((double) 0);
       cpPackageInfo.setPriceValue((double) 0);
       cpPackageInfo.setTotalPriceValue(price);
       cpPackageInfo.setCycleInterval(1);//取值间隔
       cpPackageInfo.setCycleType("ONCE"); //取值范围：ONCE:一次   HOUR:小时   DAY:每天  MONTH:每月   YEAR:每年
       
       cpPackageInfo.setIsTotalPrice("YES");//YES：按照组合总价批价       NO：按照产品使用量批价
       cpPackageInfo.setDetailCode(CUNIT_PRICE_CODE);
       cpPackageInfo.setExceedType("D");  //D：单价；T：透支
       cpPackageInfo.setFactorCode(FACTOR_CODE);
       cpPackageInfo.setPackageId(PACKAGE_ID);

       cpPackageInfo.setSubjectCode(subjectCode);
//       cpPackageInfo.setUnitpriceCode(unitpriceCode);
//       pPackageInfo.setServiceType(serviceType);//业务类型
//       cpPackageInfo.setExtCode(extCode);
       cpPackageInfo.setUnitCode(" ");
       cpPackageInfo.setUnitType(" ");

       cpPackageInfoAtom.insert(cpPackageInfo);   
       SetDshmImpl.CpPackageInfoDshm(cpPackageInfo);
       //CP_FACTOR_INFO表 
       //实例ID 
       CpFactorInfo cpFactorInfo = new CpFactorInfo();
       cpFactorInfo.setFactorCode(FACTOR_CODE);
       cpFactorInfo.setFactorName("instance_id");
       cpFactorInfo.setFactorValue(INSTANCE_ID);
       cpFactorInfo.setTenantId(TENANT_ID);
       cpFactorInfoAtom.insert(cpFactorInfo);
       SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
   }


    

    
    public void writeZXuserinfo(String instance_id, String serviceId, String userId, String productIds) throws BatchException{
        try{
            BlUserinfoZx blUserinfoZx = new BlUserinfoZx();
            blUserinfoZx.setInstanceId(instance_id);
            blUserinfoZx.setServiceId(serviceId);
            blUserinfoZx.setUserId(userId);
            blUserinfoZx.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));
            blUserinfoZx.setProductId(productIds); 
            blUserinfoZxAtom.insert(blUserinfoZx);
            SetDshmImpl.BlUserInfoZX(blUserinfoZx); 
        }catch(Exception e){
            e.printStackTrace();
            throw new BatchException("000001","BlUserinfoZx错误，instance_id : "+instance_id,e);
        }
    }

    
//    private void insertCpCunitDetail(PriceElementInfo priceElement, PricemakingResponseZX priceResZX) {
//        CUNIT_PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
//        
//        serviceType = priceElement.getOrderTypeList().get(0).getPriceType();
//        List<ElementInfo> elementInfoList = new ArrayList<>();
//        elementInfoList = priceElement.getOrderTypeList().get(0).getElementInfoList();
//        for(ElementInfo el : elementInfoList){
//            CUNIT_PRICE_DETAIL_ID = SeqUtil.getNewId("CP_CUNITPRICE_DETAIL$ID$SEQ");
//            CpCunitpriceDetail cUnitDetail = new CpCunitpriceDetail();
//            cUnitDetail.setId(CUNIT_PRICE_DETAIL_ID);
//            cUnitDetail.setFactorName(el.getName());
//            cUnitDetail.setFactorValue(el.getValue());
//            cUnitDetail.setCunitPriceCode(CUNIT_PRICE_CODE);
//            cUnitDetail.setPriceProductType(serviceType);//priceType :ECS MSG
//            System.out.println("插入数据,CunitDetail : "+JSON.toJSONString(cUnitDetail));
//            aCpCunitpriceDetailMapper.insertSelective(cUnitDetail);
//            //redis
//            SetDshmImpl.CpCunitPriceDetailDshm(cUnitDetail);
//        }
//    }
    
    private  void cpCunitpriceBusiAssem(String priceUnit, String priceValue ,PricemakingResponseZX priceResZX, String subjectCode,String recordType) {
    	CUNIT_PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
        CUNIT_PRICE_INFO_ID = SeqUtil.getNewId("CP_CUNITPRICE_INFO$ID$SEQ");
        FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
        Double price = Double.parseDouble(priceValue);
        
        CpCunitpriceInfo cUnitInfo = new CpCunitpriceInfo();
        cUnitInfo.setId(CUNIT_PRICE_INFO_ID); 
        cUnitInfo.setCunitPriceCode(CUNIT_PRICE_CODE);
        cUnitInfo.setFactorCode(FACTOR_CODE);
        cUnitInfo.setPriceName("中信复合单价");
        cUnitInfo.setPriceProductType(serviceType);
        cUnitInfo.setPriceValue(price);
        cUnitInfo.setUnitType(priceUnit);       
        cUnitInfo.setSubjectCode(subjectCode);
        cpCunitpriceInfoAtom.insert(cUnitInfo);   

        SetDshmImpl.CpCunitPriceInfoDshm(cUnitInfo);
        //CP_FACTOR_INFO表 
        //实例ID 
        CpFactorInfo cpFactorInfo = new CpFactorInfo();
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("instance_id");
        cpFactorInfo.setFactorValue(INSTANCE_ID);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoAtom.insert(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
    
        //报文类型: TIME,STREAM
        if(recordType != null){
        	cpFactorInfo.setFactorCode(FACTOR_CODE);
            cpFactorInfo.setFactorName(BatchConstants.Subject.RECORD_TYPE);
            cpFactorInfo.setFactorValue(recordType);
            cpFactorInfo.setTenantId(TENANT_ID);
            cpFactorInfoAtom.insert(cpFactorInfo);
            SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
        }          
    }
    
    private  void cpPriceInfoBusiAssem(PricemakingResponseZX priceResZX, String chargeType) {
        
        DETAIL_CODE=CUNIT_PRICE_CODE;        
        PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
        
        DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
        PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
//        CpPriceDetailMapper aCpPriceDetailMapper = sqlSessionTemplate.getMapper(CpPriceDetailMapper.class);
//        CpPriceInfoMapper aCpPriceInfoMapper = sqlSessionTemplate.getMapper(CpPriceInfoMapper.class);
        CpPriceDetail cpPriceDetail = new CpPriceDetail();
        //
        cpPriceDetail.setDetailId(DETAIL_ID);
        cpPriceDetail.setActiveTime(DateUtil.getTimestamp("20150101120000",
                DateUtil.YYYYMMDDHHMMSS));
        cpPriceDetail.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
                DateUtil.YYYYMMDDHHMMSS));
        cpPriceDetail.setDetailCode(DETAIL_CODE);
        cpPriceDetail.setPriceCode(PRICE_CODE);
        cpPriceDetail.setChargeType(chargeType);
        cpPriceDetailAtom.insert(cpPriceDetail);
        SetDshmImpl.CpPriceDetailDshm(cpPriceDetail); 
        
        CpPriceInfo cpPriceInfo = new CpPriceInfo();
        cpPriceInfo.setPriceInfoId(PRICE_INFO_ID); 
        cpPriceInfo.setActiveStatus("ACTIVE");
        cpPriceInfo.setChargeType(chargeType);
        cpPriceInfo.setComments("中信订购");
        cpPriceInfo.setPriceCode(PRICE_CODE);
        cpPriceInfo.setProductType(serviceType);
        cpPriceInfo.setTenantId(TENANT_ID);
        cpPriceInfo.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));
        cpPriceInfo.setActiveTime(DateUtil.getTimestamp("20150101120000",
                DateUtil.YYYYMMDDHHMMSS));
        cpPriceInfo.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
                DateUtil.YYYYMMDDHHMMSS));
        int productPriority = 100;
        if(chargeType.equals("PACKAGE")){
            productPriority = 90;
        }
        cpPriceInfo.setProductPriority(productPriority);
        cpPriceInfoAtom.insert(cpPriceInfo);
      //redis
        SetDshmImpl.CpPriceInfoDshm(cpPriceInfo); 
    }
    
  
    
    private String checkRegionId(String regionId) {
        switch(regionId){
        case "cn-beijing": //华北2

            return null;     
        case "cn-shanghai"://华东2

            return null;
        case "cn-shenzhen"://华南1

            return null;
        case "cn-hangzhou"://华东1

            return null;
        case "cn-qingdao"://华北1

            return null;  
        case "ap-southeast-1"://新加坡

            return null;                
        case "cn-hongkong"://香港
          
            return null;
            
        case "us-west-1"://美国
      
            return null;
            
        }
        throw new BusinessException("RegionId 有误 : "+regionId);
    }

}
