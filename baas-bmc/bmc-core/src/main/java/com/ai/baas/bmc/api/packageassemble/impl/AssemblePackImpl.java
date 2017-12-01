package com.ai.baas.bmc.api.packageassemble.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.api.packageassemble.impl.atom.AccquireDshm;
import com.ai.baas.bmc.api.packageassemble.impl.output.MDSOutput;
import com.ai.baas.bmc.api.packageassemble.impl.output.OutputConstants;
import com.ai.baas.bmc.util.DshmUtil;
import com.ai.baas.bmc.util.TableConstants;
import com.ai.baas.bmc.util.TableConstants.PriceDetail;
import com.ai.baas.bmc.util.TableConstants.SubsComm;
import com.ai.baas.bmc.util.TableConstants.UserInfo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AssemblePackImpl extends BaseFlow{
    private static Logger logger = LoggerFactory.getLogger(AssemblePackImpl.class);
    private String inputMessage;

    public AssemblePackImpl(String message){
        this.inputMessage = message;
    }

    @Override
    public void process() {      
        try{ 
//            Map<String, Object> output = new HashMap<>();
            JSONObject output = new JSONObject();
            JSONObject jobObject = JSONObject.parseObject(inputMessage);
            String priceCode = jobObject.getString("priceCode");
            
            Map<String, String> blSubsCommExt = AccquireDshm.getBlSubsCommExt(priceCode);
            Map<String, String> blSubsComm = AccquireDshm.getBlSubsComm(priceCode);
            Map<String, String> blUserinfo = AccquireDshm.getBlUserinfo(blSubsComm.get(SubsComm.SUBS_ID),blSubsComm.get(SubsComm.TENANT_ID));
            Map<String, String> priceDetail = AccquireDshm.getPriceDetail(blSubsComm.get(SubsComm.PRODUCT_ID));
            Map<String, String> packageInfo = AccquireDshm.getPackageinfo(priceDetail.get(PriceDetail.DETAIL_CODE)); 
            
            //fee_info
            JSONObject fee = new JSONObject();
            fee.put("apportion_list", blSubsCommExt.get("ext_value")); 
            
            BigDecimal decimal =new BigDecimal(packageInfo.get("total_price_value"));
            String price = decimal.multiply(new BigDecimal(Double.valueOf(1000))).toString();
            fee.put("fee", price);
            fee.put("subject",packageInfo.get("subject_code") );
            fee.put("priceCode", priceDetail.get("price_code"));
            JSONArray fee_info = new JSONArray();      
            fee_info.add(fee);        
            //判断是否传入账期，如果没有，就获取系统月份为账期
            String accountPeriod = null;
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
            String sysDate = sft.format(c.getTime());
            if(jobObject.containsKey("account_period")){
                logger.info("Manually Set AccountPeriod");
                accountPeriod = jobObject.getString("account_period");
            }else{
                accountPeriod = sysDate.substring(0, 6);
            }
            output.put("fee_info", fee_info);
            output.put("account_period",accountPeriod);
            output.put("arrival_time",sysDate);
            output.put("start_time",sysDate);
            output.put("acct_id",blUserinfo.get(UserInfo.ACCT_ID));
            output.put("cust_id",blUserinfo.get(UserInfo.CUST_ID));
            output.put("subs_id",blUserinfo.get(UserInfo.SUBS_ID));
            output.put("service_id", blUserinfo.get(UserInfo.SERVICE_ID));
            output.put("product_id",blSubsComm.get(SubsComm.PRODUCT_ID));
    //        output.put("instance_id",);
    //        output.put("record_type",);
    //        output.put("region_id",);
            
            output.put("tenant_id",blUserinfo.get(UserInfo.TENANT_ID));
    //      output.put("service_id",);
    //      output.put("service_type",);
    //        output.put("usage_amount",);
    //        output.put("source",);
            
            logger.info("Package Calculation Success. Sending MSG: "+output.toJSONString());
            MDSOutput mds = new MDSOutput();
            mds.sendMsg(output); 
        }catch(Exception e){
            logger.error("Package Calculation Failure", e);
        }
        
    }
    


}
