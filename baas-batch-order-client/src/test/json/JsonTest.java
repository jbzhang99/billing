package json;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.batch.client.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.batch.client.params.priceMaking.PriceElementInfoZX;
import com.ai.baas.batch.client.params.priceMaking.PricemakingResponseZX;
import com.ai.baas.batch.client.params.priceMaking.ShoppingList;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfo;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class JsonTest {

    @Test
    public void toLowerCase(){
        String json = "{\"orders\":[{\"id\":\"0c743573-3f76-479b-9b90-9c5fcc499fd6\",\"state_id\":\"4\",\"approvInfo\":\"\",\"user_id\":\"be1f7c9c-1c55-429e-9dcf-48f66dbb06e2\",\"shopping_lists\":[{\"service_id\":\"5762107c6ae6ca04e14595b8\",\"parameters\":{\"engine\":\"MySQL\",\"engineVersion\":\"5.6\",\"dBInstanceClass\":\"rds.mysql.s2.large\",\"dBInstanceStorage\":\"10\",\"dBInstanceNetType\":\"Internet\",\"securityIPList\":\"0.0.0.0/0\",\"regionId\":\"cn-beijing\",\"zoneId\":\"cn-beijing-c\",\"payType\":\"Postpaid\",\"instanceNetworkType\":\"VPC\",\"vpcId\":\"vpc-jrwfspj5s\",\"vSwitchId\":\"vsw-9dlv1ldos\"},\"service_name\":\"云数据库RDS\",\"instances\":[{\"Instance_id\":\"f1549750-9ed9-4ab5-8b51-b53bf66c0732\",\"is_deleted\":\"0\",\"app_base_line\":\"1\",\"serviceId\":\"5762107c6ae6ca04e14595b8\",\"instanceStatusId\":\"2\",\"supplyInstanceId\":\"rm-2jd103cr86gb94a6x\",\"use_id\":\"2\"}],\"list_id\":\"a643f557-af92-4677-8291-dc03acbb9a5d\"}],\"state_name\":\"以完成\",\"last_oper_time\":\"2016-08-0410: 34: 52\"},{\"id\":\"35a57764-caea-49ac-9553-85a1d4831e0e\",\"state_id\":\"4\",\"approvInfo\":\"\",\"user_id\":\"be1f7c9c-1c55-429e-9dcf-48f66dbb06e2\",\"shopping_lists\":[{\"service_id\":\"57721e052fa45f06e1c013da\",\"parameters\":{\"ServiceCode\":\"ons\",\"StatusKey1\":\"enabled\",\"StatusValue1\":\"true\"},\"service_name\":\"消息队列\",\"instances\":[{\"Instance_id\":\"af9f8408-fa7a-4bf3-84ff-b224afc9d74a\",\"is_deleted\":\"0\",\"app_base_line\":\"1\",\"serviceId\":\"57721e052fa45f06e1c013da\",\"instanceStatusId\":\"2\",\"supplyInstanceId\":\"1596769687493741\",\"use_id\":\"2\"}],\"list_id\":\"e9c96fb2-26c6-434c-8808-f271df6cb45e\"}],\"state_name\":\"以完成\",\"last_oper_time\":\"2016-08-0410: 35: 34\"}]}";
    }
    
	@Test
	public void jsonTest(){
		String msg ="{\"orders\":[{\"id\":\"6ba76566-0251-4e1a-a188-64e1b2d26be2\",\"state_id\":\"1\",\"user_id\":\"fd634834-59a1-416d-a124-f74fb99069d9\",\"shopping_lists\":[{\"list_id\":\"b334ffaf-fcc9-45d5-a78c-fb51afac0c74\",\"service_id\":\"cffe8d3c-7628-4378-a07f-a7bf6c3871a0\",\"parameters\":{\"cost_center\":\"zhongxinyinghang\",\"app_base_line\":\"001\",\"instance_num\":\"2\",\"property\":\"vm\",\"regionId\":\"huabei1\",\"ZoneId\":\"kyq1\",\"HostName\":\"mylinux\",\"SystemDisk.Size\":\"50\",\"InternetMaxBandwidthIn\":\"1\",\"InstanceType\":\"1c2\"},\"instances\":[{\"Instance_id\":\"1171f5db-929e-4f2e-b664-4f2781111111\",\"use\":\"vm\",\"app_base_line\":\"001\",\"is_deleted\":\"0\",},{\"Instance_id\":\"1171f5db-929e-4f2e-b664-4f2781111112\",\"use\":\"vm\",\"app_base_line\":\"001\",\"is_deleted\":\"0\",}]},{\"list_id\":\"1171f5db-929e-4f2e-b664-4f2781c94ebc\",\"service_id\":\"cffe8d3c-7628-4378-a07f-a7bf6c3871a0\",\"parameters\":{\"cost_center\":\"zhongxinyinghang\",\"app_base_line\":\"001\",\"instance_num\":\"1\",\"property\":\"vm\",\"regionId\":\"huabei2\",\"ZoneId\":\"kyq2\",\"HostName\":\"mywindows\",\"SystemDisk.Size\":\"100\",\"InternetMaxBandwidthIn\":\"10\",\"InstanceType\":\"4c16\"},\"instances\":[{\"Instance_id\":\"1171f5db-929e-4f2e-b664-4f2781111113\",\"use\":\"vm\",\"app_base_line\":\"001\",\"is_deleted\":\"0\"}]}]}]}";
		JSONObject object = new JSONObject();

		System.err.println(msg); 
		JSONObject json = (JSONObject)JSONObject.parse(msg);
        JSONArray jsonArray = json.getJSONArray("orders");
        for(int i=0;i<jsonArray.size();i++){
            JSONObject json2 = jsonArray.getJSONObject(i);
            System.out.println("user_id:  "+json2.get("user_id"));
            System.out.println("shopping_lists:  "+json2.get("shopping_lists"));
            JSONArray jsonArray2 = json2.getJSONArray("shopping_lists");
            for(int j=0;j<jsonArray2.size();j++){
                JSONObject json3 = jsonArray2.getJSONObject(j);
                System.out.println("");
                System.out.println("list_id:   "+json3.get("list_id"));
                System.out.println("parameters:   "+json3.get("parameters"));
                System.out.println("instances:   "+json3.get("instances"));
            }
        }
//        
//        
//		Orders orders = (Orders)MyJsonUtil.JSONToObj(msg, Orders.class);
//		Root root = (Root)MyJsonUtil.JSONToObj(msg, Root.class);
//		System.err.println(com.alibaba.fastjson.JSONObject.toJSONString(root));
	}
	
	@Test
	public void getOrderTime(){
	    Calendar   c   =   Calendar.getInstance();   
	    System.err.println(c.toString());
	    c.add(Calendar.DAY_OF_MONTH, -1);  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String mDateTime=formatter.format(c.getTime());  
	    System.err.println(mDateTime.toString());
	    String strStart=mDateTime.substring(0, 16);//2007-10-29 09:30  
	    System.err.println(strStart.toString()); 
	    String strDate = mDateTime.substring(0, 11);
	    System.err.println("strDate"+strDate);
	    String aaa = strDate+"00:00:00";
	    System.err.println(aaa); 
	}
	@Test
	public void TimeTest(){
	    String a = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
	    System.out.println(a); 
	    
	    System.out.println(DateUtil.getTimestamp("20150101120000",
                DateUtil.YYYYMMDDHHMMSS));
	    CpPriceInfo cpPriceInfo = new CpPriceInfo();

	    cpPriceInfo.setActiveTime(DateUtil.getTimestamp("20150101120000",
                DateUtil.YYYYMMDDHHMMSS)); 

	    System.out.println(JSON.toJSONString(cpPriceInfo)); 
	    
//	    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 
//
//	    String myTime = sdFormat.format(currentTime);
	}
	@Test
	public void usageTest(){
	    String jsonUsage = null;

	    jsonUsage = ""+SeqUtil.getNewId("ORDERINFOSEQ");
	    System.out.println(jsonUsage);
	}
	@Test
	public void dshmTest(){
//	    String factorCode = "999666";
//	    CacheProxy cacheProxy = CacheProxy.getInstance();
//	    IDshmClient dshmClient = new DshmClient();
//	    //cp_factor_info表
//        Map<String, String>factorParams = new TreeMap<String,String>();
//        factorParams.put("factor_code", factorCode);
        List<Map<String, String>> factorResults =  new ArrayList<>();
        Map<String, String> factorMap =  new HashMap();
        factorMap.put("factor_code", "1");
        factorMap.put("factor_value", "CUIOT;CMIOT;CMIOTBYDA.SN");
        factorMap.put("tenant_id", "VIV-BYD");
        factorMap.put("system_id", "100333");
        factorMap.put("factor_name", "apn_code");
        factorMap.put("factor_info_id", "108");
        factorResults.add(factorMap);
        if(factorResults == null || factorResults.size()==0){
            System.out.println("empty");
        }
        
        String factorName = null;
        String factorValue = null;
        for(Map<String, String>result:factorResults){
            factorName = result.get("factor_name");
            if(factorName.equals("apn_code")){
                factorValue = result.get("factor_value");
                break;
            }
        }
        if(factorValue == null){
            System.out.println("参考因素表中，未找到对应的apn_code");       
        }       
        System.out.println(factorMap.get("aaa"));
        //比较，apnMatch存入data
        String dataApn = "CUIOT";
        String[] apnValues;
        String apnMatch = "0";
        apnValues = StringUtils.splitPreserveAllTokens(factorValue,";");
        for(String apnValue : apnValues){
            if(apnValue.equals(dataApn)){
                apnMatch = "1";
                break;
            }
        }
        System.out.println("apnMatch   "+apnMatch);
    }
	
	@Test
	public void userTest(){
	    String userId = "fd634834-59a1-416d-a124-f74fb99069d9";
        UserInfoQueryVo uvo = new UserInfoQueryVo();
        UserQueryResponse uqr = new UserQueryResponse();
        uvo.setSelectId("7");
        uvo.setSelectType(userId);
        ICiticRestReqWrapperSV citicSv = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
        uqr = citicSv.searchUser(uvo);
        System.out.println("UserQueryResponse:"+JSON.toJSONString(uqr));
        List<UserInfo>userinfoList = new ArrayList<>();
        userinfoList = uqr.getUsers();
        if(userinfoList == null){
            throw new BusinessException("未找到user_id:"+userId+ " 对应的用户信息");
        }
        UserInfo ui = userinfoList.get(0);
        String extCustId = ui.getOrgId();
        System.out.println("extCustId :"+extCustId);
	}
	@Test
	public void apnTest(){
	    String sss = "CUIOT;CMIOT;CMIOTBYDA.SN";
	    String apncode = "CUIOT";
        String[] apnValues;
        apnValues = StringUtils.splitPreserveAllTokens(sss,";");
        for(String apnValue : apnValues){
            System.out.println(apnValue);
            if(apncode.equals(apnValue)){
                System.out.println("1"); 
            }
         }
        
	}
	
	@Test
	public void postTest(){
	    String url = "http://10.6.186.30:8080/servermgt/orders";
	    Map<String, String> param = new HashMap<>();
	    param.put("start_time", "2015-06-29 08:00");
	    param.put("end_time", "2015-06-30 08:00");
	    param.put("state", "已提交");
	    param.put("type", "2");
	    String s = HttpClientUtil.sendPost(url, JSON.toJSONString(param));
	    System.out.println("s:"+s); 

	    
	    
	    
	}
	@Test
	public void priceTest(){
	    String parameters = "{\"AERA_ID\":\"HuaBei1\",\"CPU_MEM\":\"2C4G\",\"IO_OPTI\":\"YES\",\"OS\":\"win\",\"SERIAL_ID\":\"1\"}";
        ShoppingList shoppingList = new ShoppingList();
        PriceElementInfoZX request = new PriceElementInfoZX();
        List<ShoppingList> shopping_lists = new ArrayList<ShoppingList>();
        
        shoppingList.setList_id("1");
        shoppingList.setService_id("test1");
        shoppingList.setParameters(parameters);
       
        shopping_lists.add(shoppingList);
        request.setShopping_lists(shopping_lists);
	    String s = HttpClientUtil.sendPost(
	                    "http://10.1.130.84:10884/baas-bmc/pricemaking/queryPricemakingZX", JSON.toJSONString(request));

        System.out.println(s);
        Map<String, String> m  = (Map<String, String>) JSON.parse(s);
        System.out.println(m.get("data")); 
        PricemakingResponseZX sss = JSON.parseObject(m.get("data"), PricemakingResponseZX.class);
        System.out.println(sss.getDetail_costs().get(0).getList_id());
        
//        JSONObject rootObject = (JSONObject)JSONObject.parse(s);
//        System.out.println(rootObject.toString());
//        JSONArray dataArray = rootObject.getJSONArray("data");
//        JSONObject costObject = dataObject.getJSONObject("detail_costs");
//        System.out.println(costObject.toJSONString());
//        List<CostInfo> costList =  JsonUtil.jsonToBeanList(costObject.toString(), CostInfo.class);
	}
	
}
