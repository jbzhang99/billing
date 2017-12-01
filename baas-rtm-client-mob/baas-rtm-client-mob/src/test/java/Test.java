//import java.security.MessageDigest;
//import java.util.Random;
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//
//import net.sf.json.JSONObject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.runner.center.ctp.rtm.core.util.HttpClientgetUtil;
//
//
//public class Test {
//	private static Logger logger = LoggerFactory.getLogger(Test.class);
//	
//	public static void main(String[] args) {
//		File file=new File("F/a/df/a/123.avl");
//		String fileName = file.getAbsolutePath();
//		String last = fileName.substring(fileName.lastIndexOf("."));
//		
//		CharSequence avl = null;
//		CharSequence zip = null;
//		last.replace(avl, zip);
//		
//		System.out.println(last);
//		System.out.println(fileName);
//		
//	
//	}
//
//}
////import com.ai.runner.utils.util.DateUtil;
////
////public class Test {
////private static Logger logger = LoggerFactory.getLogger(Test.class);
////
////public static void main(String[] args) {
//////	String appId = "BYD00001";
//////	  int max = 9999999;
//////		Random random = new Random();
//////		int s = random.nextInt(max) + 10000000;
//////	String transId = appId+DateUtil.getDateString(DateUtil.getSysDate(),DateUtil.YYYYMMDDHHMMSS)+String.valueOf(s);
////////              
//////System.out.println(transId);
////String appId="BYD0000";
////String httpClientPwd ="BYD2016";
////
////int max = 9999999;
////	Random random = new Random();
////	int s = random.nextInt(max) + 10000000;
////String transId = appId+DateUtil.getDateString(DateUtil.getSysDate(),DateUtil.YYYYMMDDHHMMSS)+String.valueOf(s);
////////        
////	String mingwen = appId + httpClientPwd + transId;
////	StringBuilder sb = new StringBuilder();
////	MessageDigest md5;
////	try {
////		md5 = MessageDigest.getInstance("SHA-256");
////		md5.update(mingwen.getBytes());
////		for (byte b : md5.digest()) {
////		    sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
////		}
////	}catch (Exception e) {	
////		 e.printStackTrace();
////	}
////	String token = sb.toString().toLowerCase();
////	System.out.println(token);
////}
////}
//package com.ai.runner.clc.util.centerinterface;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.ai.runner.base.exception.CallerException;
//import com.ai.runner.clc.constants.Constants;
//import com.ai.runner.clc.util.HttpClientUtil;
//import com.ai.runner.clc.util.UniConfig;
//import com.ai.runner.clc.vo.o2p.CheckChgConnectVO;
//import com.ai.runner.clc.vo.o2p.ChgPileStatueVO;
//import com.ai.runner.clc.vo.o2p.CutChgByControlVO;
//import com.ai.runner.clc.vo.o2p.RechargeOpenVo;
//import com.ai.runner.utils.constants.ExceptCodeConstants;
//import com.ai.runner.utils.util.StringUtil;
//import com.alibaba.fastjson.JSON;
//
//public final  class O2pUtil {
//    /**
//     * O2p通知 充电开通请求接口 Date: 2015年12月15日 <br>
//     * Copyright (c) 2015 asiainfo.com <br>
//     * 
//     * @author zhouyy5
//     */
//    private static final Logger LOG = LogManager.getLogger(O2pUtil.class);
//    private O2pUtil(){}
//
//    public static String rechargeOpen(RechargeOpenVo req, String url) {
//        if (null == req) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[请求参数为空]");
//        }
//        if (StringUtil.isBlank(req.getIccid())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[调O2P卡号，为空]");
//        }
//        if (StringUtil.isBlank(req.getPileId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[调O2P充电桩Id为空]");
//        }
//        if (StringUtil.isBlank(req.getStaId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[调O2P充电站Id为空]");
//        }
//        if (StringUtil.isBlank(req.getBalance())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[调O2P余额为空]");
//        }
//
//        if (StringUtil.isBlank(req.getSysId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[调O2P系统id为空]");
//        }
//
//        if (StringUtil.isBlank(req.getTenId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[调O2P租户为空]");
//        }
//        String data = JSON.toJSONString(req);
//        String keyValue = null;
//        String urlValue = null;
//        keyValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.APPKEYVALUE);
//        urlValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.BYDOPENURL);
//        Map<String, String> headerValue = new HashMap<String, String>();
//        if(null!=keyValue&&null!=urlValue){
//        headerValue.put(Constants.O2p.APPKEY, keyValue);
//        headerValue.put(Constants.O2p.SIGN, keyValue);
//        }else{
//            headerValue.put(Constants.O2p.APPKEY, Constants.O2p.APPKEYVALUES);
//            headerValue.put(Constants.O2p.SIGN, Constants.O2p.SIGNVALUE);
//        }
//        String result = null;
//        try {
//            if(null!=keyValue&&null!=urlValue){
//            result = HttpClientUtil.sendPostRequest(urlValue, data, headerValue);
//            }
//            else{
//                result = HttpClientUtil.sendPostRequest(url, data, headerValue);
//            }
//        } catch (Exception e) {
//           // e.printStackTrace();
//            LOG.info("调用O2P充电桩开通接口返回错误:" + e.getMessage(),e);
//        }
//
//        return result;
//
//    }
//
//    /**
//     * 充电连通校验服务
//     */
//    public static String checkChgConnect(CheckChgConnectVO req, String url) {
//
//        if (null == req) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[请求参数为空]");
//        }
//        if (StringUtil.isBlank(req.getPileId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电桩Id为空]");
//        }
//        if (StringUtil.isBlank(req.getStaId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电站Id为空]");
//        }
//        if (StringUtil.isBlank(req.getPileCode())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电桩编号为空]");
//        }
//        
//        String keyValue = null;
//        String urlValue = null;
//        keyValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.APPKEYVALUE);
//        urlValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.BYDPILESTATEQRYURL);
//
//        String data = JSON.toJSONString(req);
//        Map<String, String> headerValue = new HashMap<String, String>();
//        if(null!=keyValue){
//        headerValue.put(Constants.O2p.APPKEY, keyValue);
//        headerValue.put(Constants.O2p.SIGN, keyValue);
//        }else{
//            headerValue.put(Constants.O2p.APPKEY, Constants.O2p.APPKEYVALUES);
//            headerValue.put(Constants.O2p.SIGN, Constants.O2p.SIGNVALUE);
//        }
//        
//        String result = null;
//        try {
//            if(null!=keyValue&&null!=urlValue){
//            result = HttpClientUtil.sendPostRequest(urlValue, data, headerValue);
//            }else{
//                result = HttpClientUtil.sendPostRequest(url, data, headerValue);
//            }
//            
//            } catch (Exception e) {
//            LOG.error("调用O2P充电桩状态查询接口返回错误:" + e.getMessage(),e);
//        }
//        return result;
//    }
//
//    
//    /**
//     * 信控断
//     */
//    public static String cutChgByControl(CutChgByControlVO req, String url) {
//        if (null == req) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[请求参数为空]");
//        }
//        if (StringUtil.isBlank(req.getIccid())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[卡号，为空]");
//        }
//        if (StringUtil.isBlank(req.getPileId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电桩Id为空]");
//        }
//        if (StringUtil.isBlank(req.getStaId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电站Id为空]");
//        }
//        if (StringUtil.isBlank(req.getRecType())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[话单类型为空]");
//        }
//
//        if (StringUtil.isBlank(req.getSysId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[系统id为空]");
//        }
//        if (StringUtil.isBlank(req.getTenId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[租户为空]");
//        }
//        if (StringUtil.isBlank(req.getMsgId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[消息ID为空]");
//        }
//        if (null == (req.getReqTime())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[请求时间为空]");
//        }
//        String data = JSON.toJSONString(req);
//        String keyValue = null;
//        String urlValue = null;
//        keyValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.APPKEYVALUE);
//        urlValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.BYDCHARCLOSEURL);
//        Map<String, String> headerValue = new HashMap<String, String>();
//        if(null!=keyValue&&null!=urlValue){
//        headerValue.put(Constants.O2p.APPKEY, keyValue);
//        headerValue.put(Constants.O2p.SIGN, keyValue);
//        }else{
//            headerValue.put(Constants.O2p.APPKEY, Constants.O2p.APPKEYVALUES);
//            headerValue.put(Constants.O2p.SIGN, Constants.O2p.SIGNVALUE);
//        }
//        String result = null;
//        try {
//            if(null!=keyValue&&null!=urlValue){
//            result = HttpClientUtil.sendPostRequest(urlValue, data, headerValue);
//            }
//            else{
//                result = HttpClientUtil.sendPostRequest(url, data, headerValue);
//            }
//        } catch (Exception e) {
//          //  e.printStackTrace();
//            LOG.info("调用O2P信控充电断开接口请求返回错误:" + e.getMessage(),e);
//        }
//             
//        return result;
//    }
//    
//    public static String getChgPileStatus(ChgPileStatueVO req, String url) {
//
//        if (null == req) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[请求参数为空]");
//        }
//        if (StringUtil.isBlank(req.getPileId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电桩Id为空]");
//        }
//        if (StringUtil.isBlank(req.getStaId())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电站Id为空]");
//        }
//        if (StringUtil.isBlank(req.getPileCode())) {
//            throw new CallerException(ExceptCodeConstants.Special.PARAM_IS_NULL, "[充电桩编号为空]");
//        }
//        
//        String keyValue = null;
//        String urlValue = null;
//        keyValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.APPKEYVALUE);
//        urlValue = UniConfig.getValue(Constants.O2p.O2PURL, Constants.O2p.BYDPILESTATEQRYURL);
//
//        String data = JSON.toJSONString(req);
//        Map<String, String> headerValue = new HashMap<String, String>();
//        if(null!=keyValue){
//        headerValue.put(Constants.O2p.APPKEY, keyValue);
//        headerValue.put(Constants.O2p.SIGN, keyValue);
//        }else{
//            headerValue.put(Constants.O2p.APPKEY, Constants.O2p.APPKEYVALUES);
//            headerValue.put(Constants.O2p.SIGN, Constants.O2p.SIGNVALUE);
//        }
//        
//        String result = null;
//        try {
//            if(null!=keyValue&&null!=urlValue){
//            result = HttpClientUtil.sendPostRequest(urlValue, data, headerValue);
//            }else{
//                result = HttpClientUtil.sendPostRequest(url, data, headerValue);
//            }
//            
//            } catch (Exception e) {
//            LOG.error("调用O2P充电桩状态查询接口返回错误:" + e.getMessage(),e);
//        }
//        return result;
//    }
//}




import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Random;

import com.ai.runner.utils.util.DateUtil;

import net.sf.json.JSONObject;


public class Test {

	public static void main(String[] args) throws Exception {
		
		/*{
		     "appId":"BYD0000",
		     "transId":"1000012014101615303080000005",
		     "fileTime":"20160322",
			 "token":"5d5c3dbe4425c5704e7a9fedc3f9b75f051b6cb66c1fdf569a75f3443a577815"
		}*/
		
		
		String appId = "BYD0000";
		//transId:事务编码，由物联卡集团客户按照相应规则自主生成。
		//生成规则：APPID+YYYYMMDDHHMISS+8位数字序列(此序列由集团客户自主生成，比如从00000001开始递增等等)
		int max = 9999999;
		Random random = new Random();
		int s = random.nextInt(max) + 10000000;

		String transId = appId
				+ DateUtil.getDateString(DateUtil.getSysDate(),
						DateUtil.YYYYMMDDHHMMSS) + String.valueOf(s);

//		String transId =  "BYD00002016031814555200000001";//
		String httpClientPwd = "BYD2016";
		String fileTime = "20160408";
		String token = getToken(appId,httpClientPwd,transId);
		JSONObject req = new JSONObject();
		req.put("transId", transId);
		req.put("fileTime", fileTime);
		req.put("token", token);
		req.put("appId", appId);
		
		String serviceUrl = "http://120.25.132.169:8090/occ/http/srv_viv_byd_bill_file_pwd";
		try {
			URL url = new URL(serviceUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("AppKey", "cab8f3b59531b44c5fc84b7a94905ccc");
			con.setRequestProperty("sign", "123456");
			con.setRequestProperty("Content-Type", "application/json");
			con.setUseCaches(false);
				con.setDoOutput(true);
					OutputStream os = con.getOutputStream();
					os.write(req.toString().getBytes("UTF-8"));
					os.close();
			
			
			String encoding = con.getContentEncoding();
			InputStream is = con.getInputStream();
			int read = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((read = is.read()) != -1) {
				baos.write(read);
			}
			byte[] data = baos.toByteArray();
			baos.close();
			String content = null;
			if (encoding != null) {
				content = new String(data, encoding);
			} else {
				content = new String(data, "UTF-8");
			}
			System.out.println("请求报文:"+req);
			System.out.println("响应报文:"+content);
			
			JSONObject jsonObject = JSONObject.fromObject(content);
			JSONObject header=jsonObject.getJSONObject("responseHeader");
			if(header!=null){
				if(header.getLong("returnCode") == 000000){
					JSONObject data1 = jsonObject.getJSONObject("data");
					String password = data1.getString("password");	
					System.out.println("password="+password);
				}
				else {// 失败
					System.out.println("get password failed！！");
					return;
					}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String getToken(String appId,String httpClientPwd,String transId){
		String mingwen = appId + httpClientPwd + transId;
		StringBuilder sb = new StringBuilder();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("SHA-256");
			md5.update(mingwen.getBytes());
			for (byte b : md5.digest()) {
			    sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
			}
		}catch (Exception e) {	
			 e.printStackTrace();
		}
		String token = sb.toString().toLowerCase();
		return token;
	}
}