package com.ai.baas.batch.client.service.business;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.interfaces.CpExtInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpFactorInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPriceDetailMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPriceInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpStepInfoMapper;
import com.ai.baas.batch.client.dao.mapper.bo.CpExtInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpFactorInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfo;
import com.ai.baas.batch.client.service.atom.interfaces.ICpExtInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpFactorInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceDetailAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPriceInfoAtom;
import com.ai.baas.batch.client.service.atom.interfaces.ICpStepInfoAtom;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.DateUtil;

@Component
public class SetOSS {
    
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
	    
    private  String PRICE_CODE;    
    private  String DETAIL_CODE;
    private  Long PRICE_INFO_ID;
    private  Long DETAIL_ID;
    private  String TENANT_ID;
    private  String FACTOR_CODE;
    private  String INSTANCE_ID;
    
    private static String storeSubjectCode = "OSS01";
    private static String flowSubjectCode = "OSS02";
    private static String apiSubjectCode = "OSS03";
    
    public String storeUnitType = PropertiesUtil.getValue("batch.order.unit.type.oss.store");
    public String flowUnitType = PropertiesUtil.getValue("batch.order.unit.type.oss.flow");
    public String apiUnitType = PropertiesUtil.getValue("batch.order.unit.type.oss.api");
    

    public List<String> writeOssProduct(String instance_id) {
        INSTANCE_ID = instance_id;        
        TENANT_ID = PropertiesUtil.getValue("batch.order.tenant.id");   
        List<String> priceCodeList = new ArrayList<>(); 
        String p1 = null;
        String p2 = null;
        String p3 = null;
        
        //都按照北京的阶梯来生成产品
        p1 = storeHuaBei2();
        priceCodeList.add(p1);
        p2 = flowHuaBei2();
        priceCodeList.add(p2);
        p3 = apiChina();
        priceCodeList.add(p3);
       
            
        return priceCodeList;
    }
    

    private void insertFactorInfo(String match){
        CpFactorInfo cpFactorInfo = new CpFactorInfo();
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("instance_id");
        cpFactorInfo.setFactorValue(INSTANCE_ID);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoAtom.insert(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
        
       /* cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("region_id");
        cpFactorInfo.setFactorValue(REGION_ID);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoMapper.insertSelective(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);*/
        
        String recordType;
        if(match.equals("flow")){
            recordType = "STREAM";
        }else if(match.equals("store")){
            recordType ="STORAGE";
        }else if(match.equals("api")){
            recordType = "API";
        }else{
            throw new BusinessException("recordType匹配错误");
        }
        cpFactorInfo.setFactorCode(FACTOR_CODE);
        cpFactorInfo.setFactorName("record_type");
        cpFactorInfo.setFactorValue(recordType);
        cpFactorInfo.setTenantId(TENANT_ID);
        cpFactorInfoAtom.insert(cpFactorInfo);
        SetDshmImpl.CpFactorInfoDshm(cpFactorInfo);
    }

    private String flowHuaBei2() {
        
        FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
        DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
        PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
        
        DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
        PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
        CpStepInfo cpStepInfo = new CpStepInfo();
        //CP_STEP_INFO表   ----忙时
        Long extCodeBusy = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
        cpStepInfo = flowHuaBei2Step1Busy(extCodeBusy);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = flowHuaBei2Step2Busy(extCodeBusy);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = flowHuaBei2Step3Busy(extCodeBusy);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = flowHuaBei2Step4Busy(extCodeBusy);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        //CP_EXT_INFO表  ----忙时
        CpExtInfo cpExtInfo = new CpExtInfo();
        cpExtInfo.setExtCode(""+extCodeBusy);
        cpExtInfo.setExtName(PropertiesUtil.getValue("batch.order.extname.oss.timeseg"));
        cpExtInfo.setExtValue("080000;240000");
        cpExtInfo.setTenantId(TENANT_ID);
        cpExtInfo.setExtOwner("STEP");
//        cpExtInfo.setExtType(extType);
        cpExtInfoAtom.insert(cpExtInfo);
        SetDshmImpl.CpExtInfoDshm(cpExtInfo);
        
      //CP_STEP_INFO表   ----闲时
        Long extCodeIdle = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
        cpStepInfo = flowHuaBei2Step1Idle(extCodeIdle);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = flowHuaBei2Step2Idle(extCodeIdle);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = flowHuaBei2Step3Idle(extCodeIdle);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = flowHuaBei2Step4Idle(extCodeIdle);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        //CP_EXT_INFO表  ----闲时
        cpExtInfo.setExtCode(""+extCodeIdle);
        cpExtInfo.setExtName(PropertiesUtil.getValue("batch.order.extname.oss.timeseg"));
        cpExtInfo.setExtValue("000000;080000");
        cpExtInfo.setTenantId(TENANT_ID);
        cpExtInfo.setExtOwner("STEP");
//        cpExtInfo.setExtType(extType);
        cpExtInfoAtom.insert(cpExtInfo);
        SetDshmImpl.CpExtInfoDshm(cpExtInfo);
        
        
      //CP_FACTOR_INFO表
        insertFactorInfo("flow");
        //Price_info  
        insertPriceInfoDetail();
        return PRICE_CODE;
    }

   
    private String storeHuaBei2() {
       
//        EXT_CODE = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
        FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
        DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
        PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
        
        DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
        PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
        CpStepInfo cpStepInfo = new CpStepInfo();
        //CP_STEP_INFO表        
        cpStepInfo = storeHuaBei2Step1();
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = storeHuaBei2Step2();
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        cpStepInfo = storeHuaBei2Step3();
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
      //CP_FACTOR_INFO表
        insertFactorInfo("store");
        
        insertPriceInfoDetail();
        return PRICE_CODE;
    }

    private String apiChina() {
        FACTOR_CODE = ""+SeqUtil.getNewId("CP_FACTOR_INFO$FACTOR_CODE$SEQ");
        DETAIL_CODE = ""+ SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_CODE$SEQ");
        PRICE_CODE = ""+SeqUtil.getNewId("CP_PRICE_INFO$PRICE_CODE$SEQ");
        
        DETAIL_ID = SeqUtil.getNewId("CP_PRICE_DETAIL$DETAIL_ID$SEQ");
        PRICE_INFO_ID = SeqUtil.getNewId("CP_PRICE_INFO$PRICE_INFO_ID$SEQ");
        
        //PUT类型
        Long extCodePut = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo = apiChinaStep1Put(extCodePut);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshmOld(cpStepInfo);      
        
        cpStepInfo = apiChinaStep2Put(extCodePut);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshmOld(cpStepInfo);      

        CpExtInfo cpExtInfo = new CpExtInfo();
        cpExtInfo.setExtCode(""+extCodePut);
        cpExtInfo.setExtName(PropertiesUtil.getValue("batch.order.extname.oss.reqtype"));
        cpExtInfo.setExtValue("put");
        cpExtInfo.setTenantId(TENANT_ID);
        cpExtInfo.setExtOwner("STEP");
//        cpExtInfo.setExtType(extType);
        cpExtInfoAtom.insert(cpExtInfo);
        SetDshmImpl.CpExtInfoDshm(cpExtInfo);
        
      //GET类型 
        Long extCodeGet = SeqUtil.getNewId("CP_EXT_INFO$EXT_CODE$SEQ");
        cpStepInfo = apiChinaStep1Get(extCodeGet);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);
        
        cpStepInfo = apiChinaStep2Get(extCodeGet);
        cpStepInfoAtom.insert(cpStepInfo);
        SetDshmImpl.CpStepInfoDshm(cpStepInfo);

        cpExtInfo.setExtCode(""+extCodeGet);
        cpExtInfo.setExtName(PropertiesUtil.getValue("batch.order.extname.oss.reqtype"));
        cpExtInfo.setExtValue("get");
        cpExtInfo.setTenantId(TENANT_ID);
        cpExtInfo.setExtOwner("STEP");
//        cpExtInfo.setExtType(extType);
        cpExtInfoAtom.insert(cpExtInfo); 
        SetDshmImpl.CpExtInfoDshm(cpExtInfo);
        
        
      //CP_FACTOR_INFO表
        insertFactorInfo("api");

        //Price_info  
        insertPriceInfoDetail();
        return PRICE_CODE;
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
        cpPriceInfo.setProductType("");
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
    
    
    private CpStepInfo storeHuaBei2Step1() {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.store"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
//        cpStepInfo.setExtCode(EXT_CODE);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(storeSubjectCode );
        cpStepInfo.setUnitType(storeUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.store.huabei2.step1.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.store.huabei2.step1.B")));
        cpStepInfo.setPriceValue((double) 0.00021944);
        cpStepInfo.setStepSeq((long) 1);
//        cpStepInfo.setStepGroup(stepGroup);

        return cpStepInfo;
    }
    private CpStepInfo storeHuaBei2Step2() {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.store"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
//        cpStepInfo.setExtCode(EXT_CODE);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(storeSubjectCode );
        cpStepInfo.setUnitType(storeUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.store.huabei2.step2.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.store.huabei2.step2.B")));
        cpStepInfo.setPriceValue((double) 0.00021944);
        cpStepInfo.setStepSeq((long) 2);
//        cpStepInfo.setStepGroup(stepGroup);

        return cpStepInfo;
    }
    
    private CpStepInfo storeHuaBei2Step3() {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.store"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
//        cpStepInfo.setExtCode(EXT_CODE);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(storeSubjectCode );
        cpStepInfo.setUnitType(storeUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.store.huabei2.step3.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.store.huabei2.step3.B")));
        cpStepInfo.setPriceValue((double) 0.00021944);
        cpStepInfo.setStepSeq((long) 3);
//        cpStepInfo.setStepGroup(stepGroup);

        return cpStepInfo;
    }
    
    
   /**
    * flow
    * @param 
    * @return
    * @author 
    * @ApiDocMethod
    */
    
    private CpStepInfo flowHuaBei2Step1Busy(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step1.busy.A")));//0~50TB
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step1.busy.B")));
        cpStepInfo.setPriceValue((double) 0.75);
        cpStepInfo.setStepSeq((long) 1);
        cpStepInfo.setStepGroup("1");

        return cpStepInfo;
    }
    
    private CpStepInfo flowHuaBei2Step2Busy(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step2.busy.A")));//0~50TB
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step2.busy.B")));
        cpStepInfo.setPriceValue((double) 0.7);
        cpStepInfo.setStepSeq((long) 2);
        cpStepInfo.setStepGroup("1");

        return cpStepInfo;
    }
   
    private CpStepInfo flowHuaBei2Step3Busy(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step3.busy.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step3.busy.B")));
        cpStepInfo.setPriceValue((double) 0.65);
        cpStepInfo.setStepSeq((long) 3);
        cpStepInfo.setStepGroup("1");

        return cpStepInfo;
    }
    
    private CpStepInfo flowHuaBei2Step4Busy(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step4.busy.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step4.busy.B"))); 
        cpStepInfo.setPriceValue((double) 0.6);
        cpStepInfo.setStepSeq((long) 4);
        cpStepInfo.setStepGroup("1");

        return cpStepInfo;
    }
    private CpStepInfo flowHuaBei2Step1Idle(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step1.busy.A")));//0~50TB
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step1.busy.B")));
        cpStepInfo.setPriceValue((double) 0.375);
        cpStepInfo.setStepSeq((long) 1);
        cpStepInfo.setStepGroup("2");

        return cpStepInfo;
    }
    
    private CpStepInfo flowHuaBei2Step2Idle(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step2.busy.A")));//0~50TB
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step2.busy.B")));
        cpStepInfo.setPriceValue((double) 0.35);
        cpStepInfo.setStepSeq((long) 2);
        cpStepInfo.setStepGroup("2");

        return cpStepInfo;
    }
   
    private CpStepInfo flowHuaBei2Step3Idle(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step3.busy.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step3.busy.B")));
        cpStepInfo.setPriceValue((double) 0.325);
        cpStepInfo.setStepSeq((long) 3);
        cpStepInfo.setStepGroup("2");

        return cpStepInfo;
    }
    
    private CpStepInfo flowHuaBei2Step4Idle(Long extCode) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.flow"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCode);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(flowSubjectCode);
        cpStepInfo.setUnitType(flowUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step4.busy.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.flow.huabei2.step4.busy.B")));
        cpStepInfo.setPriceValue((double) 0.3);
        cpStepInfo.setStepSeq((long) 4);
        cpStepInfo.setStepGroup("2");

        return cpStepInfo;
    }

    private CpStepInfo apiChinaStep1Get(Long extCodeGet) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.api"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCodeGet);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(apiSubjectCode );
        cpStepInfo.setUnitType(apiUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step1.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step1.B")));
        cpStepInfo.setPriceValue((double) 0);
        cpStepInfo.setStepSeq((long) 1);
        cpStepInfo.setStepGroup("1");

        return cpStepInfo;
    }
    private CpStepInfo apiChinaStep2Get(Long extCodeGet) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.api"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCodeGet);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(apiSubjectCode );
        cpStepInfo.setUnitType(apiUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step2.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step2.B")));
        cpStepInfo.setPriceValue((double) 0.01);
        cpStepInfo.setStepSeq((long) 2);
        cpStepInfo.setStepGroup("1");

        return cpStepInfo;
    }

    private CpStepInfo apiChinaStep1Put(Long extCodePut) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.api"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCodePut);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(apiSubjectCode );
        cpStepInfo.setUnitType(apiUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step1.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step1.B")));
        cpStepInfo.setPriceValue((double) 0);
        cpStepInfo.setStepSeq((long) 1);
        cpStepInfo.setStepGroup("2");

        return cpStepInfo;
    }
    private CpStepInfo apiChinaStep2Put(Long extCodePut) {
        CpStepInfo cpStepInfo = new CpStepInfo();
        cpStepInfo.setCalType(PropertiesUtil.getValue("batch.order.cal.type.oss.api"));
        cpStepInfo.setDetailCode(DETAIL_CODE);
        cpStepInfo.setExtCode(extCodePut);
        cpStepInfo.setFactorCode(FACTOR_CODE);
        cpStepInfo.setServiceType("STEP");
        cpStepInfo.setSubjectCode(apiSubjectCode );
        cpStepInfo.setUnitType(apiUnitType); //单位
//        cpStepInfo.setIsPriceEqual("");
//        cpStepInfo.setIsTotalPrice("");
//        cpStepInfo.setTotalPriceValue("");
        cpStepInfo.setSectionA(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step2.A")));
        cpStepInfo.setSectionB(Double.valueOf(PropertiesUtil.getValue("batch.order.num.oss.api.get.china.step2.B")));
        cpStepInfo.setPriceValue((double) 0.01);
        cpStepInfo.setStepSeq((long) 2);
        cpStepInfo.setStepGroup("2");

        return cpStepInfo;
    }

    
}
