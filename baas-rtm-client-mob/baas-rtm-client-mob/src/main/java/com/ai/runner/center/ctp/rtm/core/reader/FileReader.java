////package com.ai.runner.center.ctp.rtm.core.reader;
////
////import java.io.File;
////import java.io.FileInputStream;
////import java.io.FileOutputStream;
////import java.io.InputStream;
////import java.text.SimpleDateFormat;
////import java.util.Calendar;
////import java.util.HashMap;
////import java.util.Map;
////
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////import com.ai.runner.center.ctp.rtm.core.util.Unzip;
////import com.ai.runner.utils.util.DateUtil;
////
////public class Freader implements Reader {
////	private static Logger logger = LoggerFactory.getLogger(Freader.class);
////
////	private String compressPanth;// 压缩文件路径
////	private String backupath;// 备份目录
////	private String url;
////	private String dest;// 解压目录
////	private Long intervals ;
////	private Long interval ;
////
////
////
////	public Long getInterval() {
////		return interval;
////	}
////
////	public void setInterval(Long interval) {
////		this.interval = interval;
////	}
////
////	public String getBackupath() {
////		return backupath;
////	}
////
////	public void setBackupath(String backupath) {
////		this.backupath = backupath;
////	}
////
////	public Long getIntervals() {
////		return intervals;
////	}
////
////	public void setIntervals(Long intervals) {
////		this.intervals = intervals;
////	}
////
////	public String getCompressPanth() {
////		return compressPanth;
////	}
////
////	public void setCompressPanth(String compressPanth) {
////		this.compressPanth = compressPanth;
////	}
////
////
////
////	public String getUrl() {
////		return url;
////	}
////
////	public void setUrl(String url) {
////		this.url = url;
////	}
////
////	public String getDest() {
////		return dest;
////	}
////
////	public void setDest(String dest) {
////		this.dest = dest;
////	}
////	
////	
////	
////
////	@Override
////	public void run() {
////
////		Map<String, String> Cache = new HashMap<String, String>();
////
////		while (true) {
////			File file = new File(compressPanth);
////
////			String filelist[] = file.list();
////			try {
////				for (int i = 0; i < filelist.length; i++) {
////				
////					File zfile = new File(compressPanth + "/" + filelist[i]);
////					String fileName=zfile.getName();
////				    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
////
////					if(prefix!="file."){
////					copyFile(
////							compressPanth + "/" + filelist[i],
////							backupath
////									+ "/"+ DateUtil
////									.getDateString(DateUtil.YYYYMMDDHHMMSS)
////									+ filelist[i]
////									);
////					
//////					File zfile = new File(compressPanth + "/" + filelist[i]);
////					// 获取文件创建时间
////					
////					Calendar cal = Calendar.getInstance();
////					long time = zfile.lastModified();
////					SimpleDateFormat formatter = new SimpleDateFormat(
////							"yyyyMMdd");
////					cal.setTimeInMillis(time);
////					// System.out.println("111111111111111111111111111111");
////					String filetime = formatter.format(cal.getTime());
////					logger.debug(zfile + "创建时间 " + filetime);
////					String password = "";
////					password = "123aa";
////					 if (Cache.containsKey(filetime)) {
////					 //缓存中存在当前日期的密码
////					 password = Cache.get(filetime).toString();
////					 } else {
////					 //缓存中不存在当前日期的密码
//////					 JSONObject jsonObject = new JSONObject(
//////					 HttpClientgetUtil.executeGet(url + "?fileTime="
//////					 + filetime));
//////					 password = jsonObject.getString("filetime");
////					
////					 Cache.put(filetime, password);
////					 }
////					logger.info("日期" + filetime + "的密码：" + password);
//////					ZipFile zFile = new ZipFile(compressPanth + "/" + filelist[i]);
//////				    if (zFile.isValidZipFile()) {
//////				    	try {
//////							
//////							zFile.setFileNameCharset("GBK");
//////							File destDir = new File(dest);
//////							if (destDir.isDirectory() && !destDir.exists()) {
//////								destDir.mkdir();
//////							logger.debug("解压目录不存在创建解压目录"+destDir);
//////							}
//////							if (zFile.isEncrypted()) {
//////								zFile.setPassword(password.toCharArray());
//////							}
//////							zFile.extractAll(dest);
//////						    //
//////						} catch (Exception e) {
//////							logger.error("解压失败检查密码是否正确");
//////						}
//////				    	//文件读取
//////				    }else{
//////				    	logger.error("压缩文件不合法");
//////				    }
////					logger.info("日期" + filetime + "的密码：" + password);
////					File zipFile = new File(compressPanth + "/" + filelist[i]);
////					File unzip= Unzip.unzip(zipFile, dest, password);
////					
////					zfile.delete();
////						
////					
////				}else{
////					Thread.sleep(interval);
////				}
////				}
////				Thread.sleep(intervals);
////			} catch (Exception e) {
////
////			}
////
////		}
////	}
////
////	/**
////	 * 文件备份
////	 * 
////	 * @param path 文件地址
////	 * @param Backupath 备份目录
////	 */
////
////	public void copyFile(String path, String Backupath) {
////		try {
////			int bytesum = 0;
////			int byteread = 0;
////			File oldfile = new File(path);
////			if (oldfile.exists()) { // 文件存在时
////				InputStream is = new FileInputStream(path); // 读入原文件
////
////				FileOutputStream fs = new FileOutputStream(Backupath);
////				byte[] buffer = new byte[1024];
////
////				while ((byteread = is.read(buffer)) != -1) {
////					bytesum += byteread; // 字节数 文件大小
////					// System.out.println(bytesum);
////					fs.write(buffer, 0, byteread);
////				}
////				is.close();
////			}
////		} catch (Exception e) {
////			System.out.println("复制单个文件操作出错");
////			e.printStackTrace();
////
////		}
////	}
////
////	
////}
//
//
//package com.ai.runner.center.ctp.rtm.core.reader;
//
//import java.io.File;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.runner.center.ctp.rtm.core.executor.LoopThread;
//import com.ai.runner.center.ctp.rtm.core.executor.ProcessHandler;
//import com.ai.runner.center.ctp.rtm.core.util.ScanPathContainer;
//import com.alibaba.fastjson.JSON;
//
//public class FileReader implements Reader{
//
//	private static Logger logger = LoggerFactory.getLogger(FileReader.class);
//	public static final String readerName= "file-reader";
//	private Long interval = 2l;// 默认2秒
//	private Boolean isMultiThreadScan = true;//是否多线程扫描
//	private String scanPath;
//	private String extension;
//	private String[] extensions = null;
//	private String system_id = "";
//	private String strPathThread;
//	
//	public String getExtension() {
//		return extension;
//	}
//
//	public void setExtension(String extension) {
//		this.extension = extension;
//	}
//
//	public Long getInterval() {
//		return interval;
//	}
//
//	public void setInterval(Long interval) {
//		this.interval = interval;
//	}
//	public String getStrPathThread() {
//		return strPathThread;
//	}
//
//	public void setStrPathThread(String strPathThread) {
//		this.strPathThread = strPathThread;
//	}
//
//	public String getScanPath() {
//		return scanPath;
//	}
//
//	public void setScanPath(String scanPath) {
//		this.scanPath = scanPath;
//	}
//
//	public String[] getExtensions() {
//		return extensions;
//	}
//
//	public void setExtensions(String[] extensions) {
//		this.extensions = extensions;
//	}
//
//	public String getSystem_id() {
//		return system_id;
//	}
//
//	public void setSystem_id(String system_id) {
//		this.system_id = system_id;
//	}
//
//	public void run() {
//		
//		ScanPathContainer.loadPath(scanPath);
//		Map<String,String> scanPathMap = ScanPathContainer.getScanPathContainer();
//		//单线程扫描
//		if(StringUtils.isNotBlank(strPathThread) && "Y".equals(strPathThread.toUpperCase())){
//			isMultiThreadScan = true;
//		}
//		if(!isMultiThreadScan){
//			ScanTask scanTasks = new ScanTask(scanPathMap.keySet());
//			scanTasks.start();
//			return;
//		}
//		//多线程扫描
//		ScanTask scanTask = null;
//		Set<String> keySet = null;
//		for(String key:scanPathMap.keySet()){
//			keySet = new HashSet<String>();
//			keySet.add(key);
//			scanTask = new ScanTask(keySet);
//			scanTask.start();
//		}
//	}
//	
//	
//	
//	/**
//	 * 扫描线程
//	 * @author mj
//	 */
//	public  class ScanTask extends LoopThread{
//		private Set<String> keys;
//		private Map<String,String> scanPathMap;
//		
//		public ScanTask(Set<String> keys){
//			this.keys = keys;
//		}
//
//		@Override
//		public boolean init() {
//			scanPathMap = ScanPathContainer.getScanPathContainer();
//			return true;
//		}
//
//		@Override
//		public boolean unInit() {
//			return true;
//		}
//
//		@Override
//		public void work() {
//			try {
//				for(String key:keys){
//					doScan(key);
//				}
//				TimeUnit.SECONDS.sleep(interval);
//			} catch (Exception e) {
//				logger.error("context", e);
//				exitFlag = true;
//			}
//			
//		}
//		
//		private boolean doScan(String service_id){
//			boolean isSucc = false;
//			String strAbsolutePath = "",strLockFileName = "";
//			try{
//				File scanPath = FileUtils.getFile(scanPathMap.get(service_id));
//				if(StringUtils.isNotBlank(extension)){
//					extensions = StringUtils.splitPreserveAllTokens(extension);
//				}
//				Collection<File> files = FileUtils.listFiles(scanPath, extensions, true);
//				for(File f:files){
//					strAbsolutePath = f.getAbsolutePath();
//					strLockFileName = strAbsolutePath+".lock";
//					
//					FileReaderDescriptor fileReaderDesc = new FileReaderDescriptor();
//					fileReaderDesc.setSystem_id(system_id);
//					//fileReaderDesc.setBusiness_type(business_type);
//					fileReaderDesc.setService_id(service_id);
//					fileReaderDesc.setFile_name(f.getName());
//					fileReaderDesc.setFile_path(f.getParent());
//					fileReaderDesc.setFile_size(f.length());
//					fileReaderDesc.setFile_createDate(f.lastModified());
//					fileReaderDesc.setLock_fileName(strLockFileName);
//					
//					StringBuilder message = new StringBuilder();
//					message.append(readerName);
//					message.append("|");
//					message.append(JSON.toJSONString(fileReaderDesc));
//
//					logger.debug(message.toString());
//					File lockFile = FileUtils.getFile(strLockFileName);
//					FileUtils.deleteQuietly(lockFile);
//					FileUtils.moveFile(FileUtils.getFile(strAbsolutePath), lockFile);
//					
//					ProcessHandler.taksQueue.put(message.toString());
//				}
//			}catch(Exception e){
//				logger.error("context", e);
//			}
//			
//			return isSucc;
//		}
//		
//	}
//	
//	public static class FileReaderDescriptor{
//		private String system_id;
//		private String service_id;
//		private String file_name;
//		private String file_path;
//		private long file_size;
//		private long file_createDate;
//		private String lock_fileName;
//
//		public String getSystem_id() {
//			return system_id;
//		}
//
//		public void setSystem_id(String system_id) {
//			this.system_id = system_id;
//		}
//
//		public String getService_id() {
//			return service_id;
//		}
//
//		public void setService_id(String service_id) {
//			this.service_id = service_id;
//		}
//
//		public String getFile_name() {
//			return file_name;
//		}
//
//		public void setFile_name(String file_name) {
//			this.file_name = file_name;
//		}
//
//		public String getFile_path() {
//			return file_path;
//		}
//
//		public void setFile_path(String file_path) {
//			this.file_path = file_path;
//		}
//
//		public long getFile_size() {
//			return file_size;
//		}
//
//		public void setFile_size(long file_size) {
//			this.file_size = file_size;
//		}
//
//		public long getFile_createDate() {
//			return file_createDate;
//		}
//
//		public void setFile_createDate(long file_createDate) {
//			this.file_createDate = file_createDate;
//		}
//
//		public String getLock_fileName() {
//			return lock_fileName;
//		}
//
//		public void setLock_fileName(String lock_fileName) {
//			this.lock_fileName = lock_fileName;
//		}
//
//		
//	}
//	
//}
//
