package com.ai.baas.batch.client.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AdapterUtil {
    private static String ORG_QUERY_PATH = "http://10.1.130.84:10883/adapter-url/usrmgt/service_org";//供应商查询地址
    private static String SELECT_TYPE = "8";//查询类型
    private static String ORG_ID = "6ba76566-0251-4e1a-a188-64e1b2d26be2";//供应商ID TODO
    private static String TOTAL_BILL_REST_PATH = "adapter-url/suppliers_service/total_bill/:576203a86ae6ca04e1459582";
    private static String CONSUME_DETAIL_PATH = "adapter-url/suppliers_service/consume_detail";

    
    
    /**
     * 余额查询
     * @return
     * @author LiangMeng
     */
    public static String queryAliBalance(){
        String url= querySuppliderUrl()+CONSUME_DETAIL_PATH;
        Map<String,String> parameters=new HashMap<String,String>();
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        parameters.put("start_date", today);
        parameters.put("end_date", today);
        String json=HttpClientUtil.sendGet(url, parameters);
        JSONObject jsonReturn = JSONObject.parseObject(json);
        String returnCode = jsonReturn.getString("resultCode");
        if(!ExceptCodeConstants.Special.SUCCESS.equals(returnCode)){
            throw new BusinessException("余额接口查询异常：["+returnCode+"]");
        }
        String data = jsonReturn.getString("data");
        JSONObject jsonData = JSONObject.parseObject(data);
        
        String balance = jsonData.getString("balance");
        return balance;
    }
    
    /**
     * 查询阿里总账
     * @return
     * @author LiangMeng
     */
    public static String queryAliTotalBill(){
        String url= querySuppliderUrl()+TOTAL_BILL_REST_PATH;
        Map<String,String> parameters=new HashMap<String,String>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);// 月份减1
        Date resultDate = cal.getTime(); // 结果  
        String preMonth = new SimpleDateFormat("yyyyMM").format(resultDate);
        parameters.put("start_date", preMonth);
        parameters.put("end_date", preMonth);
        parameters.put("grain_size", "month");
        String json=HttpClientUtil.sendGet(url, parameters);
        JSONObject jsonReturn = JSONObject.parseObject(json);
        String returnCode = jsonReturn.getString("resultCode");
        if(!ExceptCodeConstants.Special.SUCCESS.equals(returnCode)){
            throw new BusinessException("总账接口查询异常：["+returnCode+"]");
        }
        String totalBill="0";
        String data = jsonReturn.getString("data");
        JSONObject jsonData = JSONObject.parseObject(data);        
        JSONArray totalArray = jsonData.getJSONArray("bill");
        if(totalArray!=null&&totalArray.size()>0){
            totalBill = totalArray.getJSONObject(0).getString("amount");
        }
        return totalBill;
    }
    /**
     * 获取供应商url
     * @return
     * @author LiangMeng
     */
    private static String querySuppliderUrl(){
      init();
      Map<String,String> parameters=new HashMap<String,String>();
        parameters.put("select_type", SELECT_TYPE);
      String json=HttpClientUtil.sendGet(ORG_QUERY_PATH, parameters);
      JSONObject jsonReturn = JSONObject.parseObject(json);
      String data = jsonReturn.getString("data");
      JSONObject jsonData = JSONObject.parseObject(data);
      JSONArray jsonArr = jsonData.getJSONArray("orgs");
      String url = null;
      for(Object jsonsub :jsonArr){
          JSONObject jsonele = (JSONObject)jsonsub;
          if(ORG_ID.equals(jsonele.getString("org_id"))){
              url = jsonele.getString("uri");
              url = url.replaceAll("&", ":");
          }
      }
      return url;
//        return "http://10.6.186.5:8088";
    }
    /**
     * 获取订单
     * @return
     * @author LiangMeng
     */
    private static String queryOrderUrl(){
      init();
      Map<String,String> parameters=new HashMap<String,String>();
        parameters.put("select_type", SELECT_TYPE);
      String json=HttpClientUtil.sendGet(ORG_QUERY_PATH, parameters);
      JSONObject jsonReturn = JSONObject.parseObject(json);
      String data = jsonReturn.getString("data");
      JSONObject jsonData = JSONObject.parseObject(data);
      JSONArray jsonArr = jsonData.getJSONArray("orgs");
      String url = null;
      for(Object jsonsub :jsonArr){
          JSONObject jsonele = (JSONObject)jsonsub;
          if(ORG_ID.equals(jsonele.getString("org_id"))){
              url = jsonele.getString("uri");
              url = url.replaceAll("&", ":");
          }
      }
      return url;
//        return "http://10.6.186.5:8088";
    }
    
    private static void init(){
        IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams param = new QueryInfoParams();
        param.setParamType("ADAPTER_PARAM");
        param.setTradeSeq(System.currentTimeMillis()+"");
        BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
        List<BaseCode> baseCodes = info.getParamList();
        if(baseCodes!=null){
            for(BaseCode baseCode : baseCodes){
                if("ORG_QUERY_PATH".equals(baseCode.getParamCode())){
                    ORG_QUERY_PATH = baseCode.getDefaultValue();
                }
                if("SELECT_TYPE".equals(baseCode.getParamCode())){
                    SELECT_TYPE = baseCode.getDefaultValue();
                }
                if("ORG_ID".equals(baseCode.getParamCode())){
                    ORG_ID = baseCode.getDefaultValue();
                }
                if("TOTAL_BILL_REST_PATH".equals(baseCode.getParamCode())){
                    TOTAL_BILL_REST_PATH = baseCode.getDefaultValue();
                }
                if("CONSUME_DETAIL_PATH".equals(baseCode.getParamCode())){
                    CONSUME_DETAIL_PATH = baseCode.getDefaultValue();
                }
            }
        }
    }
}
