//package com.ai.runner.center.ctp.rtm.core.processor;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.sql.Timestamp;
//
//import org.apache.commons.io.Charsets;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.runner.center.ctp.rtm.core.db.DaoFactory;
//import com.ai.runner.center.ctp.rtm.core.db.dao.RtmLogDao;
//import com.ai.runner.center.ctp.rtm.core.db.entity.RtmLog;
//import com.ai.runner.center.ctp.rtm.core.executor.DeliverHandler;
//import com.ai.runner.center.ctp.rtm.core.executor.LoopThread;
//import com.ai.runner.center.ctp.rtm.core.util.Filelist.FileReaderDescriptor;
//import com.ai.runner.center.ctp.rtm.core.util.RtmProtocol;
//import com.ai.runner.center.ctp.rtm.core.util.StringLine;
//import com.alibaba.fastjson.JSONObject;
//
//public class FileProcessor extends LoopThread {
//
//	private static Logger logger = LoggerFactory.getLogger(FileProcessor.class);
//	private String message;
//	private FileReaderDescriptor fileReaderDesc;
//	private String encoding = "UTF-8";
//	private String service_id;
//	private String delimiter;
//	private long readStartTime;
//	private long readEndTime;
//	private int total;
//	private String tenant_id;
//	private String protocol = RtmProtocol.FILE.getName();
//	
//	public String getDelimiter() {
//		return delimiter;
//	}
//
//	public void setDelimiter(String delimiter) {
//		this.delimiter = delimiter;
//	}
//
//	public String getTenant_id() {
//		return tenant_id;
//	}
//
//	public void setTenant_id(String tenant_id) {
//		this.tenant_id = tenant_id;
//	}
//
//	public FileProcessor(String message){
//		this.message = message;
//	}
//	
//	public FileProcessor() {
//		super();
//	}
//
//	@Override
//	public boolean init() {
//		//business_id = (String)PropertiesUtil.getValue("bmc.reader.business.id");
//		try{
//			parserMessage();
//			return true;
//		}catch(Exception e){
//			logger.error(e.getMessage());
//			return false;
//		}
//	}
//
//	@Override
//	public boolean unInit() {
//		return true;
//	}
//
//	@Override
//	public void work() {
//		try {
//			readLineToQueue();
//			writeRtmLog();
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//		exitFlag = true;
//	}
//	
//	private void parserMessage(){
//		fileReaderDesc = JSONObject.parseObject(message, FileReaderDescriptor.class);
//		service_id = fileReaderDesc.getService_id();
//		logger.info("service_id**************************="+service_id);
//	}
//	
//	private void readLineToQueue() throws Exception{
//		String pathName = fileReaderDesc.getLock_fileName();
//logger.info("pathName="+pathName);		
//		if(StringUtils.isBlank(pathName)){
//			throw new Exception("scan path is null!");
//		}
//		InputStream in = null;
//		BufferedReader reader = null;
//		try{
//			in = new FileInputStream(FileUtils.getFile(pathName));
//			reader = new BufferedReader(new InputStreamReader(in, Charsets.toCharset(encoding)));
//			readStartTime = System.currentTimeMillis();
//			String sLine = reader.readLine();
//logger.info("sLine="+sLine);			
//			StringLine stringLine = null;
//			int rowNum = 0;
//			while (sLine != null) {
//				rowNum++;
//				stringLine = new StringLine(sLine,delimiter,fileReaderDesc.getFile_name(),rowNum);
//				DeliverHandler.deliverQueue.put(service_id, stringLine);
//				sLine = reader.readLine();
//logger.info("sLine="+sLine);
//			}
//			total = rowNum;
//			readEndTime = System.currentTimeMillis();
//		}finally{
//			IOUtils.closeQuietly(in);
//		}
//	}
//	
//	private void writeRtmLog(){
//		RtmLog rtmLog = new RtmLog();		
//		rtmLog.setService_id(service_id);
//		rtmLog.setTenant_id(tenant_id);
//		rtmLog.setProtocol(protocol);
//		rtmLog.setSource(fileReaderDesc.getFile_name());
//		rtmLog.setPath(fileReaderDesc.getFile_path());
//		rtmLog.setSize(fileReaderDesc.getFile_size());
//		rtmLog.setCreate_date(new Timestamp(fileReaderDesc.getFile_createDate()));
//		rtmLog.setStart_time(new Timestamp(readStartTime));
//		rtmLog.setEnd_time(new Timestamp(readEndTime));
//		rtmLog.setTotal(total);
//		
//		logger.debug("RtmLog="+rtmLog.toString());
//		RtmLogDao rtmDao = (RtmLogDao)DaoFactory.getInstance(RtmLogDao.name);
//		rtmDao.insertRtmLog(rtmLog);
//	}
//
//}
