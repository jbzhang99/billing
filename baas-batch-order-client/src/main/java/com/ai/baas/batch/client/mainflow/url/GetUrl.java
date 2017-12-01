package com.ai.baas.batch.client.mainflow.url;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.batch.client.util.BatchConstants;
import com.ai.baas.batch.client.util.DshmUtil;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.orderinfo.interfaces.IOrderInfoSV;
import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;
import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfoZX;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.baas.bmc.api.pricemaking.params.ShoppingList;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfo;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetUrl implements IGetUrl{

	private static final Log LOG = LogFactory.getLog(GetUrl.class);
	
	public String queryExtCustId(String userId){
        LOG.info("userID "+userId); 
        if(StringUtils.isBlank(userId)){
            throw new BusinessException("userId cannot be nusll");
        }
        UserInfoQueryVo uvo = new UserInfoQueryVo();
        UserQueryResponse uqr = new UserQueryResponse();
        uvo.setSelectId(userId);
        uvo.setSelectType("7");
        ICiticRestReqWrapperSV citicSv = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
        uqr = citicSv.searchUser(uvo);
        LOG.info("UserQueryResponse:"+JSON.toJSONString(uqr));
        List<UserInfo>userinfoList = new ArrayList<>();
        userinfoList = uqr.getUsers();
        
        if(userinfoList == null||userinfoList.isEmpty()){
            throw new BusinessException("[org_id match failed. user_id:"+userId+ "]");
        }
        UserInfo ui = userinfoList.get(0);
//        String extCustId = ui.getOrgId();
        String extCustId = ui.getTenant();
        if(extCustId == null || extCustId.isEmpty()){
        	throw new BusinessException("[org_id match failed. user_id:"+userId+ "]");
        }
        LOG.info("[ext_cust_id : "+extCustId+"]");
        return extCustId;
    }
	
	public PricemakingResponseZX queryPricemaking(Shopping shopping) {
      PriceElementInfoZX request = new PriceElementInfoZX();
      List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
      ShoppingList bmcShopping = new ShoppingList();
      bmcShopping.setList_id(shopping.getListId());
      bmcShopping.setService_id(shopping.getServiceId());
      Map<String, String> map = shopping.getParameterMap();
      
      bmcShopping.setParameters(map);
      shoppingLists.add(bmcShopping);
      request.setShopping_lists(shoppingLists);
      
      String PRICE_MAKE_PATH = null;
      IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
      QueryInfoParams param = new QueryInfoParams();
      param.setParamType("ADAPTER_PARAM");
      param.setTradeSeq(System.currentTimeMillis()+"");
      param.setTenantId("PUB"); 
      BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
      List<BaseCode> baseCodes = info.getParamList();
      if(baseCodes!=null){
          for(BaseCode baseCode : baseCodes){
              if("PRICE_MAKE_PATH".equals(baseCode.getParamCode())){
                  PRICE_MAKE_PATH = baseCode.getDefaultValue();
              }                
          }
      }
      LOG.info("PRICE_MAKE_PATH :"+PRICE_MAKE_PATH);
      
    //--------------模拟---------------- 
//      PRICE_MAKE_PATH = "http://10.1.130.84:10884/baas-bmc/pricemaking/queryPricemakingZX";
//      PRICE_MAKE_PATH = "http://billing.citicdao.com/baas-bmc/pricemaking/queryPricemakingZX";
      String s = HttpClientUtil.sendPost( PRICE_MAKE_PATH, JSON.toJSONString(request));
      //加判断是否获取成功
      Map<String, String> m  = (Map<String, String>) JSON.parse(s);
      LOG.info(m.get("data")); 
      PricemakingResponseZX sss = JSON.parseObject(m.get("data"), PricemakingResponseZX.class);       
      return sss;
  }
	
	
	public List<Map<String, String>> queryRatio(String instance_id) throws Exception {
//      String jsonUsage = "{\"costs\":[{\"cost_detail\":[{\"cost_center_id\":\"22\",\"ratio\":30},{\"cost_center_id\":\"33\",\"ratio\":70}],\"instance_id\":\"cffe8d3c-7628-4378-a07f-a7bf6c3871a0\"},{\"cost_detail\":[{\"cost_center_id\":\"88\",\"ratio\":100}],\"instance_id\":\"cffe8d3c-7628-4378-a07f-a7bf6c3871a1\"}]}";
   
      String jsonUsage = recvRatioJson(instance_id);
          
      LOG.info("[ratio msg:]: "+jsonUsage); 
      
      JSONObject rootObject = (JSONObject)JSONObject.parse(jsonUsage);
      JSONArray costArray = rootObject.getJSONArray("costs");
      for(int i=0;i<costArray.size();i++){
          JSONObject costObject = costArray.getJSONObject(i);
          if(costObject.getString("instance_id").equals(instance_id)){
              JSONArray detailArray = costObject.getJSONArray("cost_detail");
              
              /*
               * 返回信息判空
               */
              String checkFlag = PropertiesUtil.getValue("batch.order.ratio.check"); 
              if(checkFlag.equals("0")){
	              if(detailArray.size() == 0){ 
	            	  throw new Exception("ratio can not be null");
	              }
              }
              
//              List<Ratio> ratioList = new ArrayList<>();
              List<Map<String, String>> mapList = new ArrayList<>();
              for(int j=0;j<detailArray.size();j++){
                  detailArray.getJSONObject(j).put("method", "ratio");
                  
                  JSONObject newJson = new JSONObject();
                                     
                  BigDecimal num = new BigDecimal(detailArray.getJSONObject(j).getString("ratio"));
                  num = num.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                  String costCenterId = detailArray.getJSONObject(j).getString("cost_center_id");
                  
                  /*
                   * 根据分摊信息查询对应的acctId
                   */
                  String acctId = null;
                  
                  try{
//                      acctId = searchAcctId(costCenterId);
                  }catch(Exception e){
                      throw new BusinessException("分摊机构:"+costCenterId+" 对应的账户信息不存在", e);
                  }
                  
//                  newJson.put("acct_id", acctId);
                  newJson.put("cost_center_id", costCenterId);
                  newJson.put("value",num.toString());
                  newJson.put("method", "ratio");

                  Map<String, String> map = (Map<String, String>) JSON.parse(newJson.toJSONString());
                  mapList.add(map);                      

//                Ratio ratio = new Ratio();
//                ratio.setCostCenterId(detailArray.getJSONObject(j).getString("cost_center_id"));
//                ratio.setRatio(detailArray.getJSONObject(j).getString("ratio"));
//                ratioList.add(ratio);
             }
              return mapList;
          }
      }
      throw new BusinessException("【未找到instance_id:"+instance_id+ " 对应的分摊信息】");        
  }
	
	public static String recvRatioJson(String instance_id){

        String COST_QUERY_PATH=null;
        IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams param = new QueryInfoParams();
        param.setParamType("ADAPTER_PARAM");
        param.setTradeSeq(System.currentTimeMillis()+"");
        param.setTenantId("PUB");
        BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
        List<BaseCode> baseCodes = info.getParamList();
        if(baseCodes!=null){
            for(BaseCode baseCode : baseCodes){
                if("COST_QUERY_PATH".equals(baseCode.getParamCode())){
                    COST_QUERY_PATH = baseCode.getDefaultValue();
                }                
            }
        }
        
//     COST_QUERY_PATH= "http://10.1.130.84:10883/adapter-url/service_instance/cost";
        Map<String,String> parameters = new HashMap<>();
        JSONArray jsonA = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("instance_id",instance_id);
        jsonA.add(json1);       
        parameters.put("instances_ids", JSON.toJSONString(jsonA));
        
        String json=HttpClientUtil.sendGet(COST_QUERY_PATH, parameters);
        
        JSONObject data = JSONObject.parseObject(json);
        String ratio = data.getString("data");
        
        LOG.info("【ratioJSON】: "+ratio );
        return ratio;
        
    }
	
	private static String searchAcctId(String costCenterId) {
        boolean isTenant;
        String json = searchOrgId(costCenterId,"3");
        
        LOG.info("JSON : "+json); 
        String extCustId = null;
        do{
            Map<String, String> m  = (Map<String, String>) JSON.parse(json);
            JSONObject jsonObject = JSONObject.parseObject(m.get("data")); 
            JSONArray jsonArray = JSONArray.parseArray((jsonObject.getString("orgs")));
            if (CollectionUtil.isEmpty(jsonArray)) {
                throw new BusinessException("未找到costCenterId:"+costCenterId+" 对应的机构信息, 返回结果: "+json);       
            }
            JSONObject orgJson = jsonArray.getJSONObject(0);
            LOG.info("orgJson : "+JSON.toJSONString(orgJson)); 
            if(orgJson.get("is_tenant").equals("0")){
                isTenant = false;
            }else{
                isTenant = true;
                extCustId = orgJson.getString("org_id");
                System.out.println("ext_cust_id:"+extCustId);
                break;
            }
            json=searchOrgId(orgJson.getString("superior"),"5");
        }while(isTenant);
        
        return getAcctId(extCustId);
    }
	
	private static String getAcctId(String extCustId) {
        IDshmClient client = new DshmClient();
        Map<String, String>params = new TreeMap<String, String>();
        params.put("ext_cust_id", extCustId);
        params.put("tenant_id", "ECITIC");
        List<Map<String, String>> result = client.list(BatchConstants.BL_CUST_INFO)
                .where(params).executeQuery(DshmUtil.getCacheClient());
        if (CollectionUtil.isEmpty(result)) {
            throw new BusinessException("未找到extCustId对应的客户信息: "+extCustId);       
        }
        String custId = result.get(0).get("cust_id");
        
        Map<String, String>custParams = new TreeMap<String, String>();
        custParams.put("cust_id", custId);
        custParams.put("tenant_id", "ECITIC");
        List<Map<String, String>> acctResult = client.list(BatchConstants.BL_USERINFO)
                .where(custParams).executeQuery(DshmUtil.getCacheClient());
        if (CollectionUtil.isEmpty(acctResult)) {
            throw new BusinessException("未找到extCustId对应的账户信息: "+extCustId);       
        }
        return acctResult.get(0).get("acct_id"); 
    }


    private static String searchOrgId(String costCenterId,String selectType) {
        String ORG_QUERY_PATH=null;
        IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams param = new QueryInfoParams();
        param.setParamType("ADAPTER_PARAM");
        param.setTradeSeq(System.currentTimeMillis()+"");
        param.setTenantId("PUB");
        BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
        List<BaseCode> baseCodes = info.getParamList();
        if(baseCodes!=null){
            for(BaseCode baseCode : baseCodes){
                if("ORG_QUERY_PATH".equals(baseCode.getParamCode())){
                    ORG_QUERY_PATH = baseCode.getDefaultValue();
                }                
            }
        }
//        String url = "http://user.citicdao.com/usrmgt/service_org.do";
        Map<String,String> costCenterParam = new HashMap<>();
        costCenterParam.put("select_type", selectType);
        costCenterParam.put("select_id", costCenterId);
        String json=HttpClientUtil.sendGet(ORG_QUERY_PATH, costCenterParam);
        return json;
    }
    
	@Override
	public String dubboOrderinfo(OrderInfoParams orderinfo) {
        LOG.info("orderinfo : "+JSON.toJSONString(orderinfo));
        //调用订购接口
        BaseResponse baseResponse = new BaseResponse();
        try{
            IOrderInfoSV orderSv = DubboConsumerFactory.getService(IOrderInfoSV.class);
            baseResponse = orderSv.orderInfo(orderinfo);
            LOG.info("baseResponse : "+JSON.toJSONString(baseResponse));
                                  
        }catch(Exception e){
            LOG.error("【调用订购接口失败，订购信息:"+JSONObject.toJSONString(orderinfo)+"】",e);
            
            return "产品订购失败";
            //写错单表
        }
        LOG.info("【订购接口调用结束，返回信息:"+JSONObject.toJSONString(baseResponse)+"  】");
        if(baseResponse.getResponseHeader().getIsSuccess()){
           return "1";
        }else{

           return "产品订购失败";
        }

	}
}
