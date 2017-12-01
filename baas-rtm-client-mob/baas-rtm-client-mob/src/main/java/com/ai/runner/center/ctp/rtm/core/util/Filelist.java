package com.ai.runner.center.ctp.rtm.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.lingala.zip4j.exception.ZipException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ai.runner.center.ctp.rtm.core.db.DaoFactory;
import com.ai.runner.center.ctp.rtm.core.db.dao.SingleFileInfoDao;
import com.ai.runner.center.ctp.rtm.core.db.entity.SingleFileInfo;
import com.ai.runner.utils.util.DateUtil;

public class Filelist implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(Unzip.class);
	private String compressPanth;
	private String url;
	private List<String> list;
	private String backupath;
	private String dest;
	private Map<String, String> cache;
	private String system_id;
	private String readername;
	private String appId;
	private String httpClientPwd;
	private String appKey;
	private String sign;

	public Filelist(String compressPanth, List<String> list, String backupath,
			String dest, Map<String, String> cache, String url,
			String system_id, String readername, String appId,
			String httpClientPwd,String appKey, String sign) {
		this.compressPanth = compressPanth;
		this.list = list;
		this.backupath = backupath;
		this.dest = dest;
		this.cache = cache;
		this.url = url;
		this.system_id = system_id;
		this.readername = readername;
		this.appId = appId;
		this.httpClientPwd = httpClientPwd;
		this.appKey =appKey;
		this.sign = sign;
	}

	public void scance(String compressPanth, List<String> list,
			String backupath, String dest, Map<String, String> cache,
			String url, String system_id, String readername, String appId,
			String httpClientPwd,String appKey,String sign) {

		for (int i = 0; i < list.size(); i++) {
			String filename = list.get(i);
			File zfile = new File(compressPanth + "/" + filename);
			// 文件备份
			Bakupfile.copyFile(compressPanth + "/" + list.get(i), backupath
					+ "/" + DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS)
					+ list.get(i));

			String filetime = filename.toString().substring(7, 15);
			logger.info(zfile + "创建时间 " + filetime);
			String password = "";
			String isRequestOperator = "";
			String token ="";
			String transId ="";
			JSONObject req = null;
			String content = null;
			if (cache.containsKey(filetime)) {
				// 缓存中存在当前日期的密码
				password = cache.get(filetime).toString();
				isRequestOperator = "no";
			} else {
				// 缓存中不存在当前日期的密码
				// String str = "";

				int max = 9999999;
				Random random = new Random();
				int s = random.nextInt(max) + 10000000;

				transId = appId
						+ DateUtil.getDateString(DateUtil.getSysDate(),
								DateUtil.YYYYMMDDHHMMSS) + String.valueOf(s);
				token = getToken(appId,httpClientPwd,transId);
				
				Map<String, String> headerValue = new HashMap<String, String>();
				headerValue.put(appKey,sign);
				logger.info("headerValue="+headerValue);
				
				req = new JSONObject();
				req.put("transId", transId);
				req.put("fileTime", filetime);
				req.put("token", token);
				req.put("appId", appId);
				logger.info("请求 参数="+req);
				
//				try {
//					URL ul = new URL(url);
//					HttpURLConnection con = (HttpURLConnection) ul.openConnection();
//					con.setRequestMethod("POST");
//					con.setRequestProperty("AppKey", "cab8f3b59531b44c5fc84b7a94905ccc");
//					con.setRequestProperty("sign", "123456");
//					con.setRequestProperty("Content-Type", "application/json");
//					con.setUseCaches(false);
//						con.setDoOutput(true);
//							OutputStream os = con.getOutputStream();
//							os.write(req.toString().getBytes("UTF-8"));
//							os.close();
//					
//					
//					String encoding = con.getContentEncoding();
//					InputStream is = con.getInputStream();
//					int read = -1;
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();
//					while ((read = is.read()) != -1) {
//						baos.write(read);
//					}
//					byte[] data = baos.toByteArray();
//					baos.close();
//					if (encoding != null) {
//						content = new String(data, encoding);
//					} else {
//						content = new String(data, "UTF-8");
//					}
//					System.out.println("请求报文:"+req);
//					System.out.println("响应报文:"+content);
//					JSONObject jsonObject = JSONObject.fromObject(content);
//					JSONObject header=jsonObject.getJSONObject("responseHeader");
//					if(header!=null){
//						if(header.getLong("returnCode") == 000000){
//							JSONObject data1 = jsonObject.getJSONObject("data");
//							 password = data1.getString("password");						
//						}
//						else {// 失败
//							logger.info("get password failed！！");
//							return;
//							}
//					}
//				} catch (Exception e) {
//					throw new RuntimeException(e);
//				}

			cache.put(filetime, password);
			isRequestOperator = "yes";
			}
			logger.info("日期" + filetime + "的密码：" + password);
			File zipFile = new File(compressPanth + "/" + list.get(i));
			String isSuccessUnzip = "";
            String unzipFailException = "";
			try {
			    
				boolean unzip = Unzip.unzip(zipFile, dest, password,filename);
				
				if (unzip) {
					isSuccessUnzip = "yes";
				}else {
					isSuccessUnzip="no";
				}
				//扫描路径发送rest消息
				SendRest.scanFiles(dest, filename);
								
			} catch (ZipException e) {
				// TODO Auto-generated catch block
				logger.error("解压文件[{}]失败:",(compressPanth + "/" + list.get(i)),e);
				unzipFailException = e.getMessage();
			}
			//将文件信息记录入库
//			try {
//				SingleFileInfo info = new SingleFileInfo();
//				info.setSysId(system_id);
//				info.setTenantId("");
//				info.setCompressPath(compressPanth);
//				info.setBackupPath(backupath);
//				info.setDestPath(dest);
//				info.setFilename(filename);
//				info.setHavarequestoperator(isRequestOperator);
//				info.setUrl(url);
//				info.setFiletime(filetime);
//				info.getAppid();
//				info.setHttpclientpwd(httpClientPwd);
//				info.setTransid(transId);
//				info.setToken(token);
//				info.setAppkey(appKey);
//				info.setSign(sign);
//				info.setFileunzippwd(password);
//				if (req!=null) {
//					info.setRequestParam(req.toString());
//				}
//				info.setResponseResult(content);
//				info.setIsSuccessUnzip(isSuccessUnzip);
//				info.setUnzipFailException(unzipFailException);
//				writeSingleFileInfo(info);
//				logger.debug("文件[{}]解压过程记录到库成功!",filename);
//			} catch (Exception e) {
//				logger.error("将话单信息记录到库中异常,",e);
//			}

	}
	}
	
	private void writeSingleFileInfo(SingleFileInfo info){
		SingleFileInfoDao infoDao = (SingleFileInfoDao)DaoFactory.getInstance(SingleFileInfoDao.name);
		infoDao.insertSingleFileInfo(info);
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
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		scance(compressPanth, list, backupath, dest, cache, url, system_id,
				readername, appId, httpClientPwd,appKey,sign);
	}
}