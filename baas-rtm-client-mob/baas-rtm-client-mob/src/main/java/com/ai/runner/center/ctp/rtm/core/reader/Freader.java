//package com.ai.runner.center.ctp.rtm.core.reader;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.runner.center.ctp.rtm.core.util.Unzip;
//import com.ai.runner.utils.util.DateUtil;
//
//public class Freader implements Reader {
//	private static Logger logger = LoggerFactory.getLogger(Freader.class);
//
//	private String compressPanth;// 压缩文件路径
//	private String backupath;// 备份目录
//	private String url;
//	private String dest;// 解压目录
//	private Long intervals ;
//	private Long interval ;
//
//
//
//	public Long getInterval() {
//		return interval;
//	}
//
//	public void setInterval(Long interval) {
//		this.interval = interval;
//	}
//
//	public String getBackupath() {
//		return backupath;
//	}
//
//	public void setBackupath(String backupath) {
//		this.backupath = backupath;
//	}
//
//	public Long getIntervals() {
//		return intervals;
//	}
//
//	public void setIntervals(Long intervals) {
//		this.intervals = intervals;
//	}
//
//	public String getCompressPanth() {
//		return compressPanth;
//	}
//
//	public void setCompressPanth(String compressPanth) {
//		this.compressPanth = compressPanth;
//	}
//
//
//
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getDest() {
//		return dest;
//	}
//
//	public void setDest(String dest) {
//		this.dest = dest;
//	}
//	
//	
//	
//
//	@Override
//	public void run() {
//
//		Map<String, String> Cache = new HashMap<String, String>();
//
//		while (true) {
//			File file = new File(compressPanth);
//
//			String filelist[] = file.list();
//			try {
//				for (int i = 0; i < filelist.length; i++) {
//				
//					File zfile = new File(compressPanth + "/" + filelist[i]);
//					String fileName=zfile.getName();
//				    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
//
//					if(prefix!="file."){
//					copyFile(
//							compressPanth + "/" + filelist[i],
//							backupath
//									+ "/"+ DateUtil
//									.getDateString(DateUtil.YYYYMMDDHHMMSS)
//									+ filelist[i]
//									);
//					
////					File zfile = new File(compressPanth + "/" + filelist[i]);
//					// 获取文件创建时间
//					
//					Calendar cal = Calendar.getInstance();
//					long time = zfile.lastModified();
//					SimpleDateFormat formatter = new SimpleDateFormat(
//							"yyyyMMdd");
//					cal.setTimeInMillis(time);
//					// System.out.println("111111111111111111111111111111");
//					String filetime = formatter.format(cal.getTime());
//					logger.debug(zfile + "创建时间 " + filetime);
//					String password = "";
//					password = "123aa";
//					 if (Cache.containsKey(filetime)) {
//					 //缓存中存在当前日期的密码
//					 password = Cache.get(filetime).toString();
//					 } else {
//					 //缓存中不存在当前日期的密码
////					 JSONObject jsonObject = new JSONObject(
////					 HttpClientgetUtil.executeGet(url + "?fileTime="
////					 + filetime));
////					 password = jsonObject.getString("filetime");
//					
//					 Cache.put(filetime, password);
//					 }
//					logger.info("日期" + filetime + "的密码：" + password);
////					ZipFile zFile = new ZipFile(compressPanth + "/" + filelist[i]);
////				    if (zFile.isValidZipFile()) {
////				    	try {
////							
////							zFile.setFileNameCharset("GBK");
////							File destDir = new File(dest);
////							if (destDir.isDirectory() && !destDir.exists()) {
////								destDir.mkdir();
////							logger.debug("解压目录不存在创建解压目录"+destDir);
////							}
////							if (zFile.isEncrypted()) {
////								zFile.setPassword(password.toCharArray());
////							}
////							zFile.extractAll(dest);
////						    //
////						} catch (Exception e) {
////							logger.error("解压失败检查密码是否正确");
////						}
////				    	//文件读取
////				    }else{
////				    	logger.error("压缩文件不合法");
////				    }
//					logger.info("日期" + filetime + "的密码：" + password);
//					File zipFile = new File(compressPanth + "/" + filelist[i]);
//					File unzip= Unzip.unzip(zipFile, dest, password);
//					
//					zfile.delete();
//						
//					
//				}else{
//					Thread.sleep(interval);
//				}
//				}
//				Thread.sleep(intervals);
//			} catch (Exception e) {
//
//			}
//
//		}
//	}
//
//	/**
//	 * 文件备份
//	 * 
//	 * @param path 文件地址
//	 * @param Backupath 备份目录
//	 */
//
//	public void copyFile(String path, String Backupath) {
//		try {
//			int bytesum = 0;
//			int byteread = 0;
//			File oldfile = new File(path);
//			if (oldfile.exists()) { // 文件存在时
//				InputStream is = new FileInputStream(path); // 读入原文件
//
//				FileOutputStream fs = new FileOutputStream(Backupath);
//				byte[] buffer = new byte[1024];
//
//				while ((byteread = is.read(buffer)) != -1) {
//					bytesum += byteread; // 字节数 文件大小
//					// System.out.println(bytesum);
//					fs.write(buffer, 0, byteread);
//				}
//				is.close();
//			}
//		} catch (Exception e) {
//			System.out.println("复制单个文件操作出错");
//			e.printStackTrace();
//
//		}
//	}
//
//	
//}


