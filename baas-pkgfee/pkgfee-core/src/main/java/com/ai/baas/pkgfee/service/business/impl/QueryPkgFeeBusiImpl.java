package com.ai.baas.pkgfee.service.business.impl;

import com.ai.baas.pkgfee.mds.output.MDSOutput;
import com.ai.baas.pkgfee.service.atom.impl.AccquireDshm;
import com.ai.baas.pkgfee.service.atom.interfaces.IBlSubsCommAtomSV;
import com.ai.baas.pkgfee.service.atom.interfaces.ICpPackageFeeAtomSV;
import com.ai.baas.pkgfee.service.atom.interfaces.ICpPackageTaskLogAtom;
import com.ai.baas.pkgfee.service.business.interfaces.IQueryPkgFeeBusi;
import com.ai.baas.pkgfee.util.FeeListUtil;
//import com.ai.baas.pkgfee.api.packageassemble.impl.atom.AccquireDshm;
import com.ai.baas.pkgfee.constants.*;
import com.ai.baas.pkgfee.constants.TableConstants.UserInfo;
import com.ai.baas.pkgfee.dao.mapper.bo.BlSubsComm;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageTaskLog;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mds.MessageClientException;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(noRollbackFor=com.ai.paas.ipaas.mds.MessageClientException.class) 
public class QueryPkgFeeBusiImpl implements IQueryPkgFeeBusi {
	private static final Logger logger = LogManager
			.getLogger(QueryPkgFeeBusiImpl.class);
	@Autowired
	private ICpPackageFeeAtomSV iCpPkgfeeAtom;
	@Autowired
	private IBlSubsCommAtomSV iBlSubsCommAtom;
	@Autowired
	private ICpPackageTaskLogAtom iCpPackageTaskLogAtom;
	
	private CpPackageFee queryCpPkgFee(String tenantID, String priceCode) {
		try
		{
			CpPackageFee pkgfeeList = iCpPkgfeeAtom.queryByPriceCode(tenantID, priceCode);
			return pkgfeeList;
		}catch (Exception e) {
			logger.error("PKG-B0001:【包年包月费用查询异常】" + e.getMessage());
			throw new BusinessException("PKG-B0001", e);
		}
	}
	
	/**
	 * 获取对应时间的fee，以及优惠活动的fee和subject
	 * @param cpPkgFee
	 * @return
	 */
	private Map<String, String[]> getPkgFeeByDate(CpPackageFee cpPkgFee, List<String> lstTime) {
		//获取购买单位
        String purchaseUnit = cpPkgFee.getPurchaseUnit();
        Map<String, String[]> mapResult = new HashMap<String, String[]>();
        
        //判断购买单位
        switch(purchaseUnit){
	        case CpPkgfeeConstants.PURCHASE_UNIT_HOUR:
	        	break;
	        case CpPkgfeeConstants.PURCHASE_UNIT_DAY:
	        	break;
	        case CpPkgfeeConstants.PURCHASE_UNIT_MON:
	        	FeeListUtil feeUtil = new FeeListUtil();
            	feeUtil.parser(cpPkgFee.getFeeList());
            	Map<String, String> mapFee = feeUtil.getFeeMap();
            	Map<String, String> mapdisFee = feeUtil.getPreferentialMap();
            	String subjectDis = feeUtil.getPreferentialSubjectCode();
            	for(String strTime : lstTime){
	            	String[] result = new String[3];
	            	if(mapFee.containsKey(strTime)){
	            		result[0] = mapFee.get(strTime);
	            	}
	            	if(mapdisFee.containsKey(strTime)){
	            		result[1] = mapdisFee.get(strTime);
	            	}
	            	result[2] = subjectDis;
	            	mapResult.put(strTime, result);
            	}
	        	break;
	        case CpPkgfeeConstants.PURCHASE_UNIT_YEAR:
	        	break;
        	default:
        		break;
        }
        return mapResult;
	}
		
	@Override
	public BaseResponse GetPkgfeeQueInfo(String tenantID){
		BaseResponse response = new BaseResponse();
		// 遍历bl_subs_comm表
		Calendar c = Calendar.getInstance();
        SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmss");
        String sysDate = sft.format(c.getTime());
       
        List<BlSubsComm> lstSubsComm = QuerySubsComms(tenantID, c.getTime());
        
        ArrayList<String> distincData = new ArrayList<String>();
        MDSOutput mds = new MDSOutput();
        
        for(BlSubsComm subsComm : lstSubsComm){
        	if(Collections.frequency(distincData, subsComm.getProductId()) >= 1) {
        		continue;
        	}
        	//根据产品编码price_code查询cp_package_fee表
        	CpPackageFee cpPkgFee = queryCpPkgFee(tenantID, subsComm.getProductId());
        	if(cpPkgFee == null){
        		logger.info("ProductID：" +  subsComm.getProductId() + "没有找到该用户订购的包费信息!");
        		continue;
        	}
        	//是否出账的判断
            List<String> lstSend = getSendMsgTime(subsComm, c.getTime());
            if(lstSend.size() == 0){
            	logger.info("ProductID：" +  subsComm.getProductId() + "未出账!");
            	continue;
            }
                    	
        	distincData.add(subsComm.getProductId());
            Map<String, String> blUserinfo = AccquireDshm.getBlUserinfo(subsComm.getSubsId(), tenantID);
            Map<String, String> priceDetail = AccquireDshm.getPriceDetail(subsComm.getProductId());
                      	
        	String acctID = blUserinfo.get(UserInfo.ACCT_ID);
        	String custID = blUserinfo.get(UserInfo.CUST_ID);

        	Map<String, String[]> mapArrData = getPkgFeeByDate(cpPkgFee, lstSend);
        	
        	for (String strTime : lstSend) {
        		logger.info("ProductID：" +  subsComm.getProductId() + "出账!账期：" + strTime);
        		String[] arrData = mapArrData.get(strTime);
	        	JSONArray fee_info = new JSONArray(); 
	            //fee_info
	        	if(!StringUtil.isBlank(arrData[0])){
	                JSONObject fee = new JSONObject();
		            fee.put("apportion_list", null); 
		            //由元转为厘
		            fee.put("fee", (new BigDecimal(arrData[0])).multiply(new BigDecimal("1000")).toString());
		            fee.put("subject", cpPkgFee.getSubjectCode() );
		            fee.put("priceCode", priceDetail.get("price_code"));
		            fee_info.add(fee); 
	
	        	}
	
	            if(!StringUtil.isBlank(arrData[1])){
		            JSONObject feeDis = new JSONObject();
		            feeDis.put("apportion_list", null); 
		           //由元转为厘
		            feeDis.put("fee", (new BigDecimal(arrData[1])).multiply(new BigDecimal("1000")).toString());
		            feeDis.put("subject", arrData[2]);
		            feeDis.put("priceCode", priceDetail.get("price_code"));
		            fee_info.add(feeDis); 
	
	            }
	            JSONObject output = new JSONObject();
	            
	            output.put("fee_info", fee_info);
	            output.put("account_period", strTime);
	            output.put("arrival_time", sysDate);
	            output.put("start_time", sysDate);
	            output.put("acct_id", acctID);
	            output.put("cust_id", custID);
	            output.put("subs_id", blUserinfo.get(UserInfo.SUBS_ID));
	            output.put("service_id", blUserinfo.get(UserInfo.SERVICE_ID));
	            
	            output.put("product_id",subsComm.getProductId());  
	            output.put("tenant_id",tenantID);
	            logger.info("【发送包费消息: "+output.toJSONString()+" 】");
	            
	            try {
	            	mds.send(output.toJSONString());
	            	
					if(!StringUtil.isBlank(arrData[0])){
						addPkgFeeLog(cpPkgFee, strTime, Double.valueOf(arrData[0]), custID, acctID, true);
					}
					
					if(!StringUtil.isBlank(arrData[1])){
						addDiscountPkgFeeLog(cpPkgFee, strTime, Double.valueOf(arrData[1]), arrData[2], custID, acctID, true);
					}
				} catch (MessageClientException e) {
					if(!StringUtil.isBlank(arrData[0])){
						addPkgFeeLog(cpPkgFee, strTime, Double.valueOf(arrData[0]), custID, acctID, false);
					}
					
					if(!StringUtil.isBlank(arrData[1])){
						addDiscountPkgFeeLog(cpPkgFee, strTime, Double.valueOf(arrData[1]), arrData[2], custID, acctID, false);
					}
					// TODO Auto-generated catch block
					throw new MessageClientException("PKG-B0001", "消息发送异常");
				} 
        	}
        }
        ResponseHeader header = new ResponseHeader();
        header.setIsSuccess(true);
        header.setResultCode(CpPkgfeeConstants.SUCCESS);
        header.setResultMessage("处理成功！");
        response.setResponseHeader(header);

		return response;
	}

	private List<String> getSendMsgTime(BlSubsComm subsComm, Date curDate){
		List<String> lstIsSendMsg = new ArrayList<String>();
		
		Date activeDate = new Date(subsComm.getActiveTime().getTime());
		Date inActiveDate = new Date(subsComm.getInactiveTime().getTime());
		//失效-生效<7天，不出账
		if(isUnsubscribeTimeAccept(activeDate, inActiveDate)){
			return lstIsSendMsg;
		}
		//当前时间-生效时间<7天，不出账
		if(isUnsubscribeTimeAccept(activeDate, curDate)){
			return lstIsSendMsg;
		}
		//看前一个月是否满足出账要求，再看前前一个月是否满足出账要求，按照周期的时间来确定
		int monDiff = getMonDiff(activeDate, curDate);
		if(monDiff == 0){
			return lstIsSendMsg;
		}else if(monDiff != 2 ){
			lstIsSendMsg.add(getSystemTimeMonBefore(1));
		}else if(monDiff == 2){
			String strTime1ago = getSystemTimeMonBefore(1);
			lstIsSendMsg.add(strTime1ago);
			
			String strTime2ago = getSystemTimeMonBefore(monDiff);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
			Date date1ago = null;
		    try {
				date1ago = sdf.parse(strTime1ago + "01000000");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				date1ago = curDate;
			} 
			if(isUnsubscribeTimeAccept(activeDate, date1ago)){
				lstIsSendMsg.add(strTime2ago);
			}
		}
		
		return lstIsSendMsg;
	}
    
	/**
	 * 后者时间减去前者时间是否在退订时间之内
	 * @param beforeDate 前一个时间
	 * @param afterDate  后一个时间
	 * @return true：在退订时间内； false：不在退订时间内
	 */
	private boolean isUnsubscribeTimeAccept(Date beforeDate, Date afterDate){
		boolean isUnsubscribeTimeAccept = false;
		
		long before = beforeDate.getTime(); 
    	long after = afterDate.getTime();
    	
    	long timeCurSencond = (long)((after - before) / 1000); 
    	long timeWait = CpPkgfeeConstants.PERIOD_WAIT * 24 * 60 * 60;
		
		if(timeCurSencond < timeWait){
			isUnsubscribeTimeAccept = true;
		}
		return isUnsubscribeTimeAccept;
	}
	
	/**
	 * 获取月份数字差 eg：4月29日与5月1日月份数字差为1，与6月30日数字差为2
	 * @param startMonth
	 * @param endMonth
	 * @return
	  */
	 public int getMonDiff(Date startMonth, Date endMonth){
		 int monthNum = 0;
		 int yearNumber = 0;
		 Calendar startCalendar = Calendar.getInstance(); 
		 Calendar endCalendar = Calendar.getInstance();
   
		 startCalendar.setTime(startMonth); 
		 endCalendar.setTime(endMonth);
   
		 yearNumber = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		 monthNum = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
   
		 return yearNumber*12 + monthNum;
	}
	
	/**
	 * 将信息插入到cp_package_task_log
	 * @param cpPkgFee
	 * @param period
	 * @param fee
	 * @param custID
	 * @param acctID
	 * @param result
	 */
	private void addPkgFeeLog(CpPackageFee cpPkgFee, String period, Double fee, 
			                  String custID, String acctID, boolean result){
		try{
			CpPackageTaskLog log = new CpPackageTaskLog();
			log.setTenantId(cpPkgFee.getTenantId());
			log.setPackageId(cpPkgFee.getPackageId());
			log.setAccountPeriod(period);
			log.setFee(fee);
			log.setSubjectCode(cpPkgFee.getSubjectCode());
			log.setAcctId(acctID);
			log.setCustId(custID);
			log.setResult(result ? "Y" : "N");
			log.setTaskTime(new Timestamp(System.currentTimeMillis()));
			
			iCpPackageTaskLogAtom.addCpPackageTaskLog(log);
		}catch(Exception ex){
			logger.error("PKG-B0001:【cp_package_task_log添加异常】" + ex);
		}
	}
	
	/**
	 * 将有优惠活动的信息插入到cp_package_task_log
	 * @param cpPkgFee
	 * @param period
	 * @param feeDiscount
	 * @param disSubject
	 * @param custID
	 * @param acctID
	 * @param result
	 */
	private void addDiscountPkgFeeLog(CpPackageFee cpPkgFee, String period,
			                    Double feeDiscount, String disSubject,
					            String custID, String acctID, boolean result){
		try{
			CpPackageTaskLog log = new CpPackageTaskLog();
			log.setTenantId(cpPkgFee.getTenantId());
			log.setPackageId(cpPkgFee.getPackageId());
			log.setAccountPeriod(period);
			log.setFee(feeDiscount);
			log.setSubjectCode(disSubject);
			log.setAcctId(acctID);
			log.setCustId(custID);
			log.setResult(result ? "Y" : "N");
			log.setTaskTime(new Timestamp(System.currentTimeMillis()));
			
			iCpPackageTaskLogAtom.addCpPackageTaskLog(log);
		}catch(Exception ex){
			logger.error("PKG-B0001:【cp_package_task_log优惠活动添加异常】" + ex);
		}
	}
	
	/**
	 * 获取当前月份的前一个月份
	 * @return
	 */
	private String getSystemTimeMonBefore(int nBefore){
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - nBefore); 
		String str=  new SimpleDateFormat("yyyyMM").format( cal.getTime()); 
		return str;
	}
	
	/**
	 * 根据tenantID获取BlSubsComm表的内容
	 * @param tenantID
	 * @return
	 */
	private List<BlSubsComm> QuerySubsComms(String tenantID, Date curDate) {
		String strTime1ago = getSystemTimeMonBefore(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
		Date date1ago = null;
	    try {
			date1ago = sdf.parse(strTime1ago + "01000000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			date1ago = curDate;
		} 
	    
		BlSubsComm blSubsComm = new BlSubsComm();
		blSubsComm.setTenantId(tenantID);
		List<BlSubsComm> dataList = iBlSubsCommAtom.getBlSubsComm(blSubsComm, date1ago, curDate);
        if (CollectionUtil.isEmpty(dataList)) {
        	logger.info("未获取到BlSubsComm表对应记录");
            return null;
        }
		return dataList;
	}
	
}
