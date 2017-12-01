package com.ai.runner.center.ctp.rtm.core.processor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.soap.SOAPException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ai.runner.base.exception.SystemException;
import com.ai.runner.center.ctp.rtm.core.db.dao.HbaseWordOrderToWeightDao;
import com.ai.runner.center.ctp.rtm.core.db.entity.BlUserinfo;
import com.ai.runner.center.ctp.rtm.core.executor.DeliverHandler;
import com.ai.runner.center.ctp.rtm.core.executor.LoopThread;
import com.ai.runner.center.ctp.rtm.core.service.GetTerminalUsageDataDetailsClient;
import com.ai.runner.center.ctp.rtm.core.service.model.GetTerminalUsageDataDetail;
import com.ai.runner.center.ctp.rtm.core.service.model.GetTerminalUsageDataDetailsResponse;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;
import com.ai.runner.utils.util.CollectionUtil;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.utils.util.StringUtil;
import com.sun.xml.wss.XWSSecurityException;

public class ServiceProcessor extends LoopThread {

	private static Logger logger = LoggerFactory.getLogger(ServiceProcessor.class);
	private List<BlUserinfo> message;
//	private String protocol = RtmProtocol.WEBSERVICE.getName();
	
	private String url = "";
	private String licenseKey = "";
	private String messageId = "";
	private String version = "";
	private String username = "";
	private String password = "";
	private int monthNum = -1;
	private int switchTime;
	private String timestr = "";
	
	public ServiceProcessor(List<BlUserinfo> message){
		this.message = message;
	}
	
	@Override
	public boolean init() {
		this.url = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.requesturl");
		this.licenseKey = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.licensekey");
		this.messageId = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.messageid");
		this.version = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.version");
		this.username = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.username");
		this.password = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.password");
		String switchTimeStr = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.switchtime");
		if (!StringUtil.isBlank(switchTimeStr)) {
			switchTime = Integer.parseInt(switchTimeStr);
		}else {
			throw new SystemException("请在ctp-rtm.properties中配置switchTimeStr常量值");
		}
		timestr = (String)PropertiesUtil.getValue("ctp.rtm.requestm2m.getusagedatadetail.timestr");
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		try {
			doRequestM2mAndDeal();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		exitFlag = true;
	}
	
	private void doRequestM2mAndDeal(){
		if (CollectionUtil.isEmpty(message)) {
			return;
		}
		HbaseWordOrderToWeightDao hbaseDao = new HbaseWordOrderToWeightDao();
		GetTerminalUsageDataDetailsClient client = null;
		try {
			client = new GetTerminalUsageDataDetailsClient(url, licenseKey,messageId,version);
		} catch (Exception e) {
			logger.error("创建请求联通服务对象失败，",e);
		} 
		if (client==null) {
			return ;
		}
//		ICacheClient cacheClient = CacheProxy.getCache();
		String dateString = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.yyyyMMddHHmmssSSS);
		logger.info("【{}】开始向联通请求话单时间【{}】",this.getName(),dateString);
		for (int i = 0; i < message.size(); i++) {
			BlUserinfo info = message.get(i);
			logger.debug("用户信息:",JSONObject.fromObject(info).toString());
			if (info==null) {
				continue;
			}
//			String reqTimeStr = DateUtil.getDateString("yyyy-MM-dd+HH:mm");
			String reqTime = calculationCycleStartDate();
			//请求M2m获取用户对应的账单
			client.setIccid(info.getServiceNum());
			client.setCycleStartDate(reqTime);
			client.setPageNumber("1");
			logger.debug("调用联通请求话单入参:"+client.toString());
			try {
 				GetTerminalUsageDataDetailsResponse response = client.callWebService(username, password);
				logger.debug("调用联通请求话单返回:"+JSONObject.fromObject(response).toString());
				if (response==null||CollectionUtil.isEmpty(response.getGetTerminalUsageDataDetails())) {
					continue;
				}
				this.checkRepeat(response, hbaseDao);
				if (response.getTotalPages()>1) {
					for (int j = 1; j <= response.getTotalPages(); j++) {
						client.setPageNumber(j+"");
						logger.debug("调用联通请求话单入参:"+client.toString());
						response = client.callWebService(username, password);
						logger.debug("调用联通请求话单返回:"+JSONObject.fromObject(response).toString());
						this.checkRepeat(response, hbaseDao);
					}
				}
				//该用户已经请求完了
//				timeDao.writeIccidLastRequestTime(info.getImsi1(), reqTime);
				logger.debug("{}用户【{}】从【{}】开始的话单请求处理完毕。",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS),JSONObject.fromObject(info).toString(),reqTime);
//				cacheClient.set(RtmConstants.REQUESTM2M_KEY_PROFIX_STRING+info.getServiceNum(), reqTimeStr);
			} catch (SOAPException e) {
				logger.error("调用话单请求SOAPException：",e);
			} catch (IOException e) {
				logger.error("调用话单请求IOException：",e);
			} catch (XWSSecurityException e) {
				logger.error("调用话单请求XWSSecurityException：",e);
			} catch (Exception e) {
				logger.error("调用话单请求Exception：",e);
			}
		}
		dateString = DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.yyyyMMddHHmmssSSS);
		logger.info("【{}】结束向联通请求话单时间【{}】",this.getName(),dateString);
	}
	
	private String calculationCycleStartDate(){
		int theDay = DateUtil.getDates();
		Calendar cal = new GregorianCalendar();
		if (theDay<switchTime) {
			cal.add(Calendar.MONTH, monthNum);
		}
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-");
		String year = timeformat.format(cal.getTime()).trim();
		year = year + timestr;
		return year;
}
	
	public static void main(String[] args) {
		int theDay = DateUtil.getDates();
		Calendar cal = new GregorianCalendar();
		if (theDay<28) {
			cal.add(Calendar.MONTH, -1);
		}
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(timeformat.format(cal.getTime()).trim());
		
		System.out.println(theDay);
	}
	
	private void checkRepeat(GetTerminalUsageDataDetailsResponse response,HbaseWordOrderToWeightDao hbaseDao){
		if (response==null) {
			return;
		}
		List<GetTerminalUsageDataDetail> any = response.getGetTerminalUsageDataDetails();
		if (CollectionUtil.isEmpty(any)) {
			return;
		}
		for (int j = 0; j < any.size(); j++) {
			GetTerminalUsageDataDetail detail = any.get(j);
			if (detail==null) {
				continue;
			}
			try {
				String dateString = DateUtil.getDateString(DateUtil.YYYYMM);
				if (!StringUtil.isBlank(detail.getSessionStartTime())) {
					dateString = detail.getSessionStartTime().substring(0, 4)+detail.getSessionStartTime().substring(5, 7);
				}
				/*20160728注释掉*/
				if (hbaseDao.isRepeat(hbaseDao.createRowkey(detail),dateString)) {
					continue;
				}else {
					logger.debug("有效话单【{}】",JSONObject.fromObject(detail).toString());
					DeliverHandler.lineData.add(detail);
				    hbaseDao.writeWordOrderToWeight(detail);
				}
			} catch (Exception e) {
				logger.error("查重过程失败",e);
			}
		}
	}
	
	
//	private void doRequestM2mAndDeal(){
//		if (CollectionUtil.isEmpty(message)) {
//			return;
//		}
//		HbaseWordOrderToWeightDao hbaseDao = new HbaseWordOrderToWeightDao();
//		BillingService billingService = new BillingService();
//		BillingPortType bill = billingService.getBillingPort();
//		((BindingProvider)bill).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://api.jasperwireless.com/ws/service/billing/GetTerminalUsageDataDetails");
//		((BindingProvider)bill).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "yangly");
//		//设置ssl访问登录密码
//		((BindingProvider)bill).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,"nihao@567634");
//		XMLGregorianCalendar value = this.convertToXMLGregorianCalendar();
//		System.setProperty("javax.xml.bind.JAXBContext", "com.sun.xml.internal.bind.v2.ContextFactory");  
//		for (int i = 0; i < message.size(); i++) {
//			BlUserinfo info = message.get(i);
//			logger.debug("用户信息:",JSONObject.fromObject(info).toString());
//			if (info==null) {
//				continue;
//			}
//			//请求M2m获取用户对应的账单
//			GetTerminalUsageDataDetailsRequest req = new GetTerminalUsageDataDetailsRequest();
////			req.setIccid(info.getImsi1());
//			req.setCycleStartDate(value);
//			req.setLicenseKey("8a83105a-c980-4add-a2fd-735d90e18361");
//			req.setMessageId("11223344");
//			req.setVersion("0.1");
//			req.setIccid("89860616090000080786");
//			req.setPageNumber(1);
//			logger.debug("调用联通请求话单入参:"+JSONObject.fromObject(req).toString());
//			GetTerminalUsageDataDetailsResponse response = bill.getTerminalUsageDataDetails(req);
//			logger.debug("调用联通请求话单返回:"+JSONObject.fromObject(response).toString());
//			this.checkRepeat(response, hbaseDao);
//			if (response.getTotalPages()>1) {
//				for (int j = 1; j <= response.getTotalPages(); j++) {
//					req.setPageNumber(j);
//					response = bill.getTerminalUsageDataDetails(req);
//					this.checkRepeat(response, hbaseDao);
//					}
//			}
//		}
//	}
	
//	private XMLGregorianCalendar convertToXMLGregorianCalendar() {
//		Long last = SXServiceReader.secondNum;
//		Calendar calender = Calendar.getInstance();
//		calender.setTime(DateUtil.getSysDate());
//		calender.add(13, (int) (0-last));
//		String dateStr = DateUtil.getDateString(new Timestamp(calender.getTimeInMillis()), DateUtil.DATE_FORMAT);
//		Date date = DateUtil.to_date(dateStr, DateUtil.DATE_FORMAT);
//        GregorianCalendar cal = new GregorianCalendar();
//        cal.setTime(date);
//        XMLGregorianCalendar gc = null;
//        try {
//            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
//        } catch (Exception e) {
//
//             e.printStackTrace();
//        }
//        return gc;
//    }
	
}
