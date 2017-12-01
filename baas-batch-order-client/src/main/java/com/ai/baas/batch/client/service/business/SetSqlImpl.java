package com.ai.baas.batch.client.service.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;

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
import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfo;
import com.ai.baas.batch.client.prepareflow.checkutil.ServiceCheck;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.bmc.api.pricemaking.params.Cost;
import com.ai.baas.bmc.api.pricemaking.params.ElementInfo;
import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfo;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;


public class SetSqlImpl {


    private  SqlSessionTemplate sqlSessionTemplate;
    
    private CpCunitpriceInfoMapper aCpCunitpriceInfoMapper ;
    private CpCunitpriceDetailMapper aCpCunitpriceDetailMapper ;
    private CpStepInfoMapper cpStepInfoMapper;
    private CpFactorInfoMapper cpFactorInfoMapper;
    private CpExtInfoMapper cpExtInfoMapper;
    private CpPriceDetailMapper aCpPriceDetailMapper;
    private CpPriceInfoMapper aCpPriceInfoMapper;
    private CpPackageInfoMapper aCpPackageInfoMapper;
    
    
    private static final Log LOG = LogFactory.getLog(SetSqlImpl.class);
    
    private  String CUNIT_PRICE_CODE;   
    private  Long CUNIT_PRICE_DETAIL_ID;
    private  Long CUNIT_PRICE_INFO_ID;
    private  Long PACKAGE_ID;
    private  String PRICE_CODE;    
    private  String DETAIL_CODE;

    private  Long PRICE_INFO_ID;
    private  Long DETAIL_ID;
    private  String TENANT_ID;
    private  String FACTOR_CODE;
    private  Long EXT_CODE;

    
    private  String INSTANCE_ID;
    private  String REGION_ID;
    
    private  String priceType;
    

    public SetSqlImpl(SqlSessionTemplate sqlSessionTemplate2) {
        this.sqlSessionTemplate = sqlSessionTemplate2;
    }

    public  List<String> writeSQLMsg(PriceElementInfo priceElement, PricemakingResponseZX priceResZX, String instance_id, String regionId ){
        try{
            return writeSQLMsg2(priceElement,priceResZX,instance_id,regionId);
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("【产品表沉淀错误】");
            
        }
    }
    public  List<String> writeSQLMsg2(PriceElementInfo priceElement, PricemakingResponseZX priceResZX, String instance_id, String regionId ) {
    	ServiceCheck serviceCheck = new ServiceCheck();
        priceType = priceElement.getOrderTypeList().get(0).getPriceType();
        
        INSTANCE_ID = instance_id; 
        TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");
        REGION_ID = regionId;
        
        
        cpStepInfoMapper = sqlSessionTemplate.getMapper(CpStepInfoMapper.class);
        cpFactorInfoMapper = sqlSessionTemplate.getMapper(CpFactorInfoMapper.class);
        cpExtInfoMapper = sqlSessionTemplate.getMapper(CpExtInfoMapper.class);
        aCpPriceDetailMapper = sqlSessionTemplate.getMapper(CpPriceDetailMapper.class);
        aCpPriceInfoMapper = sqlSessionTemplate.getMapper(CpPriceInfoMapper.class);
        aCpPackageInfoMapper = sqlSessionTemplate.getMapper(CpPackageInfoMapper.class);
        aCpCunitpriceInfoMapper = sqlSessionTemplate.getMapper(CpCunitpriceInfoMapper.class);
        aCpCunitpriceDetailMapper = sqlSessionTemplate.getMapper(CpCunitpriceDetailMapper.class);

        List<String> priceCodeList = new ArrayList<>(); 
        
        if(serviceCheck.isPackage(priceType)){
            List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
            for(Cost c : costList){
                int i = 1;
                String subjectCode = priceType+"0"+i;
                String priceUnit = c.getCost_unit();  
                String priceValue = c.getCost_value();
                LOG.info("【"+subjectCode+"】"); 
                writePackageInfo(priceUnit,priceValue,priceElement, priceResZX,subjectCode);
                insertCpPrice(priceElement, priceResZX,"PACKAGE");     
                priceCodeList.add(PRICE_CODE);
                i++;
            }
            return priceCodeList;
        }
         else if(serviceCheck.isDoubleUsage(priceType)){
          List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
          for(Cost c : costList){
              String priceUnit = c.getCost_unit();  
              String priceValue = c.getCost_value();
              String subjectCode;
              if(priceUnit.equals("h")){
                  subjectCode = priceType+"01";
              }else if(priceUnit.equals("gb")){
                  subjectCode = priceType+"02";
              }else{
                  throw new BusinessException(priceType+"产品单位错误");
              }
              LOG.info("【"+subjectCode+"】"); 
              insertCpCunitDetail(priceElement, priceResZX);
              insertCpCunitInfo(priceUnit,priceValue,priceElement, priceResZX,subjectCode);
              insertCpPrice(priceElement, priceResZX,"CUNIT");     
              priceCodeList.add(PRICE_CODE);
          }
          return priceCodeList;
      }
        else if(serviceCheck.isSingleUsage(priceType)){
          checkRegionId(REGION_ID);
          List<Cost> costList = priceResZX.getDetail_costs().get(0).getCost();
          for(Cost c : costList){
              String subjectCode = priceType+"01";
              String priceUnit = c.getCost_unit();  
              String priceValue = c.getCost_value();
              LOG.info("【"+subjectCode+"】"); 
              insertCpCunitDetail(priceElement, priceResZX);
              insertCpCunitInfo(priceUnit,priceValue,priceElement, priceResZX,subjectCode);
              insertCpPrice(priceElement, priceResZX,"CUNIT");     
              priceCodeList.add(PRICE_CODE);
          }       
          return priceCodeList;
      }else if (priceType.equals("OSS")) {
            checkRegionId(REGION_ID);
            LOG.info("【OSS】"); 
            SetOSS setOSS = new SetOSS();
//            priceCodeList = setOSS.writeOssProduct(sqlSessionTemplate,REGION_ID,INSTANCE_ID);
            return priceCodeList;
        }
        return null;
    }
    
    

   private void writePackageInfo(String priceUnit, String priceValue, PriceElementInfo priceElement,PricemakingResponseZX priceResZX, String subjectCode){
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

       aCpPackageInfoMapper.insertSelective(cpPackageInfo);   
     //redis
       SetDshmImpl.CpPackageInfoDshm(cpPackageInfo);
       //CP_FACTOR_INFO表 
       //实例ID 
       CpFactorInfo cpFactorInfo = new CpFactorInfo();
       cpFactorInfo.setFactorCode(FACTOR_CODE);
       cpFactorInfo.setFactorName("instance_id");
       cpFactorInfo.setFactorValue(INSTANCE_ID);
       cpFactorInfo.setTenantId(TENANT_ID);
       cpFactorInfoMapper.insertSelective(cpFactorInfo);
       SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
   }


    

    
    public void writeZXuserinfo(String instance_id, String serviceId, String userId, String productIds){
        try{
            BlUserinfoZxMapper aBlUserinfoZxMapper = sqlSessionTemplate.getMapper(BlUserinfoZxMapper.class);
            BlUserinfoZx blUserinfoZx = new BlUserinfoZx();
            blUserinfoZx.setInstanceId(instance_id);
            blUserinfoZx.setServiceId(serviceId);
            blUserinfoZx.setUserId(userId);
            blUserinfoZx.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));
            blUserinfoZx.setProductId(productIds); 
            aBlUserinfoZxMapper.insert(blUserinfoZx);
            SetDshmImpl.BlUserInfoZX(blUserinfoZx); 
        }catch(Exception e){
            e.printStackTrace();
            throw new BusinessException("BlUserinfoZx错误，instance_id : "+instance_id+", serviceId  : "+serviceId+",userId : "+userId+"操作时间: "+System.currentTimeMillis());
        }
    }

    
    private void insertCpCunitDetail(PriceElementInfo priceElement, PricemakingResponseZX priceResZX) {
//        CpCunitpriceDetailMapper aCpCunitpriceDetailMapper = sqlSessionTemplate.getMapper(CpCunitpriceDetailMapper.class);
        CUNIT_PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
        
        priceType = priceElement.getOrderTypeList().get(0).getPriceType();
        List<ElementInfo> elementInfoList = new ArrayList<>();
        elementInfoList = priceElement.getOrderTypeList().get(0).getElementInfoList();
        for(ElementInfo el : elementInfoList){
            CUNIT_PRICE_DETAIL_ID = SeqUtil.getNewId("CP_CUNITPRICE_DETAIL$ID$SEQ");
            CpCunitpriceDetail cUnitDetail = new CpCunitpriceDetail();
            cUnitDetail.setId(CUNIT_PRICE_DETAIL_ID);
            cUnitDetail.setFactorName(el.getName());
            cUnitDetail.setFactorValue(el.getValue());
            cUnitDetail.setCunitPriceCode(CUNIT_PRICE_CODE);
            cUnitDetail.setPriceProductType(priceType);//priceType :ECS MSG
            System.out.println("插入数据,CunitDetail : "+JSON.toJSONString(cUnitDetail));
            aCpCunitpriceDetailMapper.insertSelective(cUnitDetail);
            //redis
            SetDshmImpl.CpCunitPriceDetailDshm(cUnitDetail);
        }     
    }
    
    private  void insertCpCunitInfo(String priceUnit, String priceValue, PriceElementInfo priceElement,PricemakingResponseZX priceResZX, String subjectCode) {
//        CpCunitpriceInfoMapper aCpCunitpriceInfoMapper = sqlSessionTemplate.getMapper(CpCunitpriceInfoMapper.class);
//        CpFactorInfoMapper cpFactorInfoMapper = sqlSessionTemplate.getMapper(CpFactorInfoMapper.class);
        
        //复合单价 会有两个值的情况
//        String priceValue = priceResZX.getDetail_costs().get(0).getCost().get(0).getCost_value();
//        String priceUnit = priceResZX.getDetail_costs().get(0).getCost().get(0).getCost_unit();
        CUNIT_PRICE_INFO_ID = SeqUtil.getNewId("CP_CUNITPRICE_INFO$ID$SEQ");
        FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
        Double price = Double.parseDouble(priceValue);
        
        CpCunitpriceInfo cUnitInfo = new CpCunitpriceInfo();
        cUnitInfo.setId(CUNIT_PRICE_INFO_ID); 
        cUnitInfo.setCunitPriceCode(CUNIT_PRICE_CODE);
//        cUnitInfo.setExtCode(extCode);
        cUnitInfo.setFactorCode(FACTOR_CODE);
        cUnitInfo.setPriceName("中信复合单价");
        cUnitInfo.setPriceProductType(priceType);
        cUnitInfo.setPriceValue(price);
        cUnitInfo.setUnitType(priceUnit);       
        cUnitInfo.setSubjectCode(subjectCode);
        aCpCunitpriceInfoMapper.insertSelective(cUnitInfo);   
      //redis
        SetDshmImpl.CpCunitPriceInfoDshm(cUnitInfo);
        //CP_FACTOR_INFO表 
        //实例ID 
        CpFactorInfo cpFactorInfo = new CpFactorInfo();
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("instance_id");
        cpFactorInfo.setFactorValue(INSTANCE_ID);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoMapper.insertSelective(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
        //地区 
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("region_id");
        cpFactorInfo.setFactorValue(REGION_ID);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoMapper.insertSelective(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
        
        //ECS增加一条   报文类型: TIME,STREAM
        if(subjectCode.equals("ECS01")){
            cpFactorInfo.setFactorCode(FACTOR_CODE);
            cpFactorInfo.setFactorName("record_type");
            cpFactorInfo.setFactorValue("TIME");
            cpFactorInfo.setTenantId(TENANT_ID);
            cpFactorInfoMapper.insertSelective(cpFactorInfo);
            SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
        }else if(subjectCode.equals("ECS02")){
            cpFactorInfo.setFactorCode(FACTOR_CODE);
            cpFactorInfo.setFactorName("record_type");
            cpFactorInfo.setFactorValue("STREAM");
            cpFactorInfo.setTenantId(TENANT_ID);
            
            cpFactorInfoMapper.insertSelective(cpFactorInfo);
            SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
        }
               
    }
    
    private  void insertCpPrice(PriceElementInfo priceElement,PricemakingResponseZX priceResZX, String chargeType) {
        
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
        aCpPriceDetailMapper.insertSelective(cpPriceDetail);
      //redis
        SetDshmImpl.CpPriceDetailDshm(cpPriceDetail); 
        
        CpPriceInfo cpPriceInfo = new CpPriceInfo();
        cpPriceInfo.setPriceInfoId(PRICE_INFO_ID); 
        cpPriceInfo.setActiveStatus("ACTIVE");
        cpPriceInfo.setChargeType(chargeType);
        cpPriceInfo.setComments("中信订购");
        cpPriceInfo.setPriceCode(PRICE_CODE);
        cpPriceInfo.setProductType(priceType);
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
        aCpPriceInfoMapper.insertSelective(cpPriceInfo);
      //redis
        SetDshmImpl.CpPriceInfoDshm(cpPriceInfo); 
    }
    
//    private String writeCpStepInfoTopicFree() {
////      CpStepInfoMapper cpStepInfoMapper = sqlSessionTemplate.getMapper(CpStepInfoMapper.class);
////      CpFactorInfoMapper cpFactorInfoMapper = sqlSessionTemplate.getMapper(CpFactorInfoMapper.class);
////      CpExtInfoMapper cpExtInfoMapper = sqlSessionTemplate.getMapper(CpExtInfoMapper.class);
////      CpPriceDetailMapper aCpPriceDetailMapper = sqlSessionTemplate.getMapper(CpPriceDetailMapper.class);
////      CpPriceInfoMapper aCpPriceInfoMapper = sqlSessionTemplate.getMapper(CpPriceInfoMapper.class);
//      String subjectCode = "ONS02";
//      SetONS setOns = new SetONS();
//      CpStepInfo cpStepInfo = new CpStepInfo();
//      TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");
//      
//      EXT_CODE = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
//      FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
//      DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
//      PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
//      DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
//      PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
//      //CP_STEP_INFO表
//      cpStepInfo = setOns.setTopicFree(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      //CP_FACTOR_INFO表
//      CpFactorInfo cpFactorInfo = new CpFactorInfo();
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("instance_id");
//      cpFactorInfo.setFactorValue(INSTANCE_ID);
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("region_id");
//      cpFactorInfo.setFactorValue(REGION_ID);
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("record_type");
//      cpFactorInfo.setFactorValue("TOPIC");
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//    //CP_PRICE_DETAIL表
//      CpPriceDetail cpPriceDetail = new CpPriceDetail();
//      cpPriceDetail.setDetailId(DETAIL_ID);
//      cpPriceDetail.setActiveTime(DateUtil.getTimestamp("20150101120000",
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceDetail.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceDetail.setDetailCode(DETAIL_CODE);
//      cpPriceDetail.setPriceCode(PRICE_CODE);
//      cpPriceDetail.setChargeType("STEP");
//      aCpPriceDetailMapper.insertSelective(cpPriceDetail);
//      SetDshmImpl.CpPriceDetailDshm(cpPriceDetail); 
//      //CP_PRICE_INFO表
//      CpPriceInfo cpPriceInfo = new CpPriceInfo();
//      cpPriceInfo.setPriceInfoId(PRICE_INFO_ID); 
//      cpPriceInfo.setActiveStatus("ACTIVE");
//      cpPriceInfo.setChargeType("STEP");
//      cpPriceInfo.setComments("中信订购");
//      cpPriceInfo.setPriceCode(PRICE_CODE);
//      cpPriceInfo.setProductType("");
//      cpPriceInfo.setTenantId(TENANT_ID);
//      cpPriceInfo.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));       
//      cpPriceInfo.setActiveTime(DateUtil.getTimestamp("20150101120000",
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceInfo.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
//              DateUtil.YYYYMMDDHHMMSS));
//      aCpPriceInfoMapper.insertSelective(cpPriceInfo);
//      SetDshmImpl.CpPriceInfoDshm(cpPriceInfo); 
//      
//      return PRICE_CODE;
//
//  }
//
//  private String writeCpStepInfoTopic() {
////      CpStepInfoMapper cpStepInfoMapper = sqlSessionTemplate.getMapper(CpStepInfoMapper.class);
////      CpFactorInfoMapper cpFactorInfoMapper = sqlSessionTemplate.getMapper(CpFactorInfoMapper.class);
////      CpExtInfoMapper cpExtInfoMapper = sqlSessionTemplate.getMapper(CpExtInfoMapper.class);
////      CpPriceDetailMapper aCpPriceDetailMapper = sqlSessionTemplate.getMapper(CpPriceDetailMapper.class);
////      CpPriceInfoMapper aCpPriceInfoMapper = sqlSessionTemplate.getMapper(CpPriceInfoMapper.class);
//      String subjectCode = "ONS02";
//      SetONS setOns = new SetONS();
//      CpStepInfo cpStepInfo = new CpStepInfo();
//      TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");
//      
////      EXT_CODE = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
//      FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
//      DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
//      PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
//      DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
//      PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
//      //CP_STEP_INFO表
//      cpStepInfo = setOns.setTopic1(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setOns.setTopic2(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setOns.setTopic3(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setOns.setTopic4(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      //CP_FACTOR_INFO表
//      CpFactorInfo cpFactorInfo = new CpFactorInfo();
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("instance_id");
//      cpFactorInfo.setFactorValue(INSTANCE_ID);
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("region_id");
//      cpFactorInfo.setFactorValue(REGION_ID);
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("record_type");
//      cpFactorInfo.setFactorValue("TOPIC");
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//    //CP_PRICE_DETAIL表
//      CpPriceDetail cpPriceDetail = new CpPriceDetail();
//      cpPriceDetail.setDetailId(DETAIL_ID);
//      cpPriceDetail.setActiveTime(DateUtil.getTimestamp("20150101120000",
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceDetail.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceDetail.setDetailCode(DETAIL_CODE);
//      cpPriceDetail.setPriceCode(PRICE_CODE);
//      cpPriceDetail.setChargeType("STEP");
//      aCpPriceDetailMapper.insertSelective(cpPriceDetail);
//      SetDshmImpl.CpPriceDetailDshm(cpPriceDetail); 
//      //CP_PRICE_INFO表
//      CpPriceInfo cpPriceInfo = new CpPriceInfo();
//      cpPriceInfo.setPriceInfoId(PRICE_INFO_ID); 
//      cpPriceInfo.setActiveStatus("ACTIVE");
//      cpPriceInfo.setChargeType("STEP");
//      cpPriceInfo.setComments("中信订购");
//      cpPriceInfo.setPriceCode(PRICE_CODE);
//      cpPriceInfo.setProductType("");
//      cpPriceInfo.setTenantId(TENANT_ID);
//      cpPriceInfo.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));       
//      cpPriceInfo.setActiveTime(DateUtil.getTimestamp("20150101120000",
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceInfo.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
//              DateUtil.YYYYMMDDHHMMSS));
//      aCpPriceInfoMapper.insertSelective(cpPriceInfo);
//      SetDshmImpl.CpPriceInfoDshm(cpPriceInfo); 
//      
//      return PRICE_CODE;
//  }
//
//  private String writeCpStepInfoAPI() {
//      CpStepInfoMapper cpStepInfoMapper = sqlSessionTemplate.getMapper(CpStepInfoMapper.class);
//      CpFactorInfoMapper cpFactorInfoMapper = sqlSessionTemplate.getMapper(CpFactorInfoMapper.class);
//      CpExtInfoMapper cpExtInfoMapper = sqlSessionTemplate.getMapper(CpExtInfoMapper.class);
//      CpPriceDetailMapper aCpPriceDetailMapper = sqlSessionTemplate.getMapper(CpPriceDetailMapper.class);
//      CpPriceInfoMapper aCpPriceInfoMapper = sqlSessionTemplate.getMapper(CpPriceInfoMapper.class);
//      String subjectCode = "ONS01";
//      SetONS setONS = new SetONS();
//      CpStepInfo cpStepInfo = new CpStepInfo();
//      TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");
//      
////      EXT_CODE = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
//      FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
//      DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
//      PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
//      DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
//      PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
//      //CP_STEP_INFO表
//      
//      cpStepInfo = setONS.setAPI1(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setONS.setAPI2(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setONS.setAPI3(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setONS.setAPI4(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setONS.setAPI5(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      cpStepInfo = setONS.setAPI6(EXT_CODE,FACTOR_CODE,DETAIL_CODE,subjectCode);
//      cpStepInfoMapper.insertSelective(cpStepInfo);
//      SetDshmImpl.CpStepInfoDshm(cpStepInfo);
//      //CP_FACTOR_INFO表
//      CpFactorInfo cpFactorInfo = new CpFactorInfo();
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("instance_id");
//      cpFactorInfo.setFactorValue(INSTANCE_ID);
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("region_id");
//      cpFactorInfo.setFactorValue(REGION_ID);
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      cpFactorInfo.setFactorCode(FACTOR_CODE);
//      cpFactorInfo.setFactorName("record_type");
//      cpFactorInfo.setFactorValue("API");
//      cpFactorInfo.setTenantId(TENANT_ID);
//      cpFactorInfoMapper.insertSelective(cpFactorInfo);
//      SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
//      
//      //CP_EXT_INFO表 
////      CpExtInfo cpExtInfo = new CpExtInfo();
////      WriteDshm.CpExtInfoDshm(cpExtInfo);
//      
//      //CP_PRICE_DETAIL表
//      CpPriceDetail cpPriceDetail = new CpPriceDetail();
//      cpPriceDetail.setDetailId(DETAIL_ID);
//      cpPriceDetail.setActiveTime(DateUtil.getTimestamp("20150101120000",
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceDetail.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceDetail.setDetailCode(DETAIL_CODE);
//      cpPriceDetail.setPriceCode(PRICE_CODE);
//      cpPriceDetail.setChargeType("STEP");
//      aCpPriceDetailMapper.insertSelective(cpPriceDetail);
//      SetDshmImpl.CpPriceDetailDshm(cpPriceDetail); 
//      //CP_PRICE_INFO表
//      CpPriceInfo cpPriceInfo = new CpPriceInfo();
//      cpPriceInfo.setPriceInfoId(PRICE_INFO_ID); 
//      cpPriceInfo.setActiveStatus("ACTIVE");
//      cpPriceInfo.setChargeType("STEP");
//      cpPriceInfo.setComments("中信订购");
//      cpPriceInfo.setPriceCode(PRICE_CODE);
//      cpPriceInfo.setProductType("");
//      cpPriceInfo.setTenantId(TENANT_ID);
//      cpPriceInfo.setCreateTime(DateUtil.getTimestamp(System.currentTimeMillis()));       
//      cpPriceInfo.setActiveTime(DateUtil.getTimestamp("20150101120000",
//              DateUtil.YYYYMMDDHHMMSS));
//      cpPriceInfo.setInactiveTime(DateUtil.getTimestamp( "20300101120000" ,
//              DateUtil.YYYYMMDDHHMMSS));
//      aCpPriceInfoMapper.insertSelective(cpPriceInfo);
//      SetDshmImpl.CpPriceInfoDshm(cpPriceInfo); 
//      
//      return PRICE_CODE;
//  }
  
    
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
