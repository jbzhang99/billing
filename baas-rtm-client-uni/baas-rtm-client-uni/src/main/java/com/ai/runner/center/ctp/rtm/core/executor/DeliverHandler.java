package com.ai.runner.center.ctp.rtm.core.executor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.api.datacollect.params.DataVO;
import com.ai.runner.center.ctp.rtm.core.service.model.GetTerminalUsageDataDetail;
import com.ai.runner.center.ctp.rtm.core.util.HttpClientUtil;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.center.ctp.rtm.core.util.SendRest;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.utils.util.StringUtil;
import com.alibaba.fastjson.JSON;

public class DeliverHandler extends LoopThread {

	private static Logger logger = LoggerFactory.getLogger(DeliverHandler.class);
	public static Vector<GetTerminalUsageDataDetail> lineData = new Vector<GetTerminalUsageDataDetail>();
	private int deliverInterval = 5;
	private FileOutputStream out =  null;
	private String dest = "";
	private String destsuffix = "";
	private String desttmpsuffix = "";
	private String fieldsplit = "";
	private String recordsplit = "";
	
    private static String address; // "http://10.1.130.84:10771/baasrtm/dataService/transResource";
    private static String tenant;
    private static String system;
    private  static String flow;
    private  static String user;
    private static  String passwd;
    private static String infoId;
    private static int num;
	
	@Override
	public boolean init() {
        address=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.address"); // "http://10.1.130.84:10771/baasrtm/dataService/transResource";
        tenant=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.tenant");
        system=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.system");
        flow=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.flow");
        user=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.user");
        passwd=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.passwd");
        infoId=(String)PropertiesUtil.getValue("ctp.rtm.deliver.rest.infoId");
        num=Integer.parseInt(PropertiesUtil.getValue("ctp.rtm.deliver.rest.num"));
	    
		dest = (String)PropertiesUtil.getValue("ctp.rtm.deliver.filewrite.dest");
		String strDeliverInterval = (String)PropertiesUtil.getValue("ctp.rtm.deliver.filewrite.interval.second");
		destsuffix  = (String)PropertiesUtil.getValue("ctp.rtm.deliver.filewrite.destsuffix");
		desttmpsuffix  = (String)PropertiesUtil.getValue("ctp.rtm.deliver.filewrite.desttmpsuffix");
		fieldsplit  = (String)PropertiesUtil.getValue("ctp.rtm.deliver.filewrite.fieldsplit");
		recordsplit  = (String)PropertiesUtil.getValue("ctp.rtm.deliver.filewrite.fieldsplit");
		if(StringUtils.isNotBlank(strDeliverInterval)){
			deliverInterval = Integer.parseInt(strDeliverInterval);
		}
		if (StringUtil.isBlank(dest)) {
			logger.error("目标目录信息为空，请配置");
			return false;
		}
		if (StringUtil.isBlank(destsuffix)) {
			destsuffix = RtmConstants.DEST_SUFFIX;
		}
		if (StringUtil.isBlank(desttmpsuffix)) {
			desttmpsuffix = RtmConstants.DEST_TMP_SUFFIX;
		}
		if (StringUtil.isBlank(fieldsplit)) {
			fieldsplit = RtmConstants.RECORD_FIELD_SPLIT;
		}
		if (StringUtil.isBlank(recordsplit)) {
			recordsplit = RtmConstants.RECORD_BR_STRING;
		}
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		if(checkedQueueNull()){
			logger.debug("当前无话单要写文件，写文件线程进行休眠【{}】分钟再进行写文件",deliverInterval);
			doThreadSleep();
			return;
		}
		//写文件
		Vector<GetTerminalUsageDataDetail> doWriteData = this.cloneAndClear();
		int size = doWriteData.size();
		if (size>0) {
//			String fileFullPath = dest+"CU"+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+"."+destsuffix;
//			String fileFullPathTmp = fileFullPath+"."+desttmpsuffix;
//			logger.debug("【{}】开始写文件【{}】,需要写入【{}】行数据",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS),fileFullPath,size);
			try {
//				File tmpFile = FileUtils.getFile(fileFullPathTmp);
//				out = new FileOutputStream(tmpFile, true);
			    StringBuilder sb = new StringBuilder();
			    
				for (int i = 0; i < size; i++) {
					GetTerminalUsageDataDetail detail = doWriteData.get(i);
					
					sb.append(infoId).append(RtmConstants.FIELD_SPLIT);
					//号码					
					sb.append(detail.getIccid());
					sb.append(RtmConstants.FIELD_SPLIT);
					//标识
//					sb.append(detail.getServiceType());
					sb.append("APN2"); 
					sb.append(RtmConstants.FIELD_SPLIT);
					//年月日
					sb.append(this.dealDate(detail.getSessionStartTime()));
					sb.append(RtmConstants.FIELD_SPLIT);
					//时分秒
					sb.append(this.dealTime(detail.getSessionStartTime()));
					sb.append(RtmConstants.FIELD_SPLIT);
					//上网时长
					sb.append(detail.getDuration());
					sb.append(RtmConstants.FIELD_SPLIT);
					//上行流量
					sb.append(this.calculationKbTobyte(detail.getDataVolume()));
					sb.append(RtmConstants.FIELD_SPLIT);
					//下行流量
					sb.append("0");
					sb.append(RtmConstants.FIELD_SPLIT);
					sb.append("CUIOT");
					sb.append(RtmConstants.RECORD_SPLIT);
					
					if(i==num||i==(size-1)){ 
        			    String message = assembleMessage(sb);     			    
        			  logger.error("message :" + message);
        	            //send
        	            DataVO dataVO = new DataVO();
        	            dataVO.setTransData(message);
        	            HttpResponse httpResponse = HttpClientUtil.send(address,JSON.toJSONString(dataVO));
        	            logger.error("httpResponse.getStatusLine() = " + httpResponse.getStatusLine());
        			    sb.delete(0, sb.length());					    
					}
					//out.write(sb.toString().getBytes());
				}
				
//				if (out!=null) {
//					try {
//						out.close();
//						logger.debug("【{}】结束写文件【{}】,共写入【{}】行",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS),fileFullPath,size);
//					} catch (IOException e) {
//						lineData.addAll(doWriteData);
//						logger.error("关闭文件流异常",e);
//					}
//				}
				
//				File file = FileUtils.getFile(fileFullPath);
//				FileUtils.deleteQuietly(file);
//				FileUtils.moveFile(tmpFile, file);
				
				//扫描路径发送rest消息
//             SendRest.scanFiles(fileFullPath, "");
                
//			} catch (FileNotFoundException e) {
//				lineData.addAll(doWriteData);
//				logger.error("创建文件失败",e);
			}catch (Exception e) {
				lineData.addAll(doWriteData);
				logger.error("写文件失败",e);
			}
//			finally{
//				if (out!=null) {
//					try {
//						out.close();
//						logger.debug("【{}】结束写文件【{}】,共写入【{}】行",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS),fileFullPath,size);
//					} catch (IOException e) {
//						lineData.addAll(doWriteData);
//						logger.error("关闭文件流异常",e);
//					}
//				}
//			}
		}
		logger.debug("write end 【{}】",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS));
	}
	
	private String assembleMessage(StringBuilder sb) {
	    
	    StringBuilder busData = new StringBuilder();
        //head:tenant:system:flow:user:passwd
        busData.append(tenant).append(RtmConstants.HEAD_SPLIT);
        busData.append(system).append(RtmConstants.HEAD_SPLIT);
        busData.append(flow).append(RtmConstants.HEAD_SPLIT);
        busData.append(user).append(RtmConstants.HEAD_SPLIT);
        busData.append(passwd).append(RtmConstants.HEAD_SPLIT);
        
        busData.append(sb);
//        for(int i=0;i<lines.size();i++){
//            StringBuilder record = new StringBuilder();
//            oneLine = StringUtils.splitPreserveAllTokens(lines.get(i), "|");
//            record.append(infoId).append(RtmConstants.FIELD_SPLIT);
//
//            busData.append(record.substring(0, record.length()-1)).append(RtmConstants.RECORD_SPLIT);
//        }
        return busData.substring(0, busData.length()-1).toString();	    
    }

    /**
	 * 截取年月日  20160427
	 * @param time 2016-04-27T01:06:57.000Z
	 * @return
	 */
	private String dealDate(String time){
		String temp = time.substring(0, 10);
		return temp.replaceAll("-", "");
	}
	
	/**
	 * 截取时分秒 010657
	 * @param time 2016-04-27T01:06:57.000Z
	 * @return
	 */
	private String dealTime(String time){
		String temp = time.substring(11, 19);
		return temp.replaceAll(":", "");
	}
	
	/**
	 * 截取时分秒 010657
	 * @param time 2016-04-27T01:06:57.000Z
	 * @return
	 */
	private String calculationKbTobyte(String kbNum){
		BigDecimal bd = new BigDecimal(kbNum);
		return bd.multiply(new BigDecimal(1024)).toString();
	}
	
	private boolean checkedQueueNull(){
		int deliverQueueKeySize = lineData.size();
		if(deliverQueueKeySize <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	private void doThreadSleep(){
		try {
			TimeUnit.MINUTES.sleep(deliverInterval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private synchronized Vector<GetTerminalUsageDataDetail> cloneAndClear(){
		Vector<GetTerminalUsageDataDetail> doWriteData = (Vector<GetTerminalUsageDataDetail>) lineData.clone();
		lineData.removeAllElements();
		return doWriteData;
	}
	
}
