package com.ai.runner.center.bmc.resdeposit.application;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import com.ai.baas.abm.api.account.interfaces.IAccountRecordSV;
import com.ai.baas.abm.api.account.params.AmcResBookVo;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.ai.runner.center.bmc.resdeposit.constants.Constants;
import com.ai.runner.center.bmc.resdeposit.constants.TypeEnum;
import com.ai.runner.center.bmc.resdeposit.service.ReEnterHandle;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IDuplicateCheck;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IDuplicateCheckMysql;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFunResBookSv;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IPackResult;
import com.ai.runner.center.bmc.resdeposit.util.MyJsonUtil;
import com.ai.runner.center.bmc.resdeposit.vo.CommMsg;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import com.ai.runner.viv.api.balance.interfaces.IResBalanceSV;
import com.ai.runner.viv.api.balance.param.ResourceDeposit;
import com.google.gson.Gson;

@Component
public class EnterAccount {
    @Autowired
    private IFunResBookSv aIFunResBookSv;

    @Autowired
    private IDuplicateCheck aIDuplicateCheck;
    
    @Autowired
    private IDuplicateCheckMysql aIDuplicateCheckMysql;

    @Autowired
    private IPackResult aIPackResult;

    private static String bookCenter;//使用入账接口开关

    private static String database;//使用查重表database开关

    public static Random random = new Random();
    
    public static Logger log = LogManager.getLogger(EnterAccount.class);

    static {
        try {
            Properties properties = new Properties();
            ClassLoader loader = EnterAccount.class.getClassLoader();
            properties.load(loader.getResourceAsStream("bookSwitch.properties"));
            bookCenter = properties.getProperty("bookCenter");
            database = properties.getProperty("database");
            log.error("init switch config success,bookCenter is {},database type is {}" , bookCenter,database);
        } catch (IOException e) {
            log.error("init switch config success",e);
        }
    }
    /**
     * 用户表触发的入账
     */
    public void userStrike(UserMsg msg) {
        log.debug("进入用户表触发入账的方法!");
        // 当service_status为2时表示停机，直接返回
//        if ("2".equals(msg.getSERVICE_STATUS())) {
//            log.debug("service_status为2，认定为停机用户");
//            return;
//        }
        for (FunResBook f : aIFunResBookSv.getFunResBook(msg)) {
            log.debug("封装后的FunResBook:" + MyJsonUtil.toJson(f));
            enterAccount(f, msg,true);
        }
    }

    /**
     * 资源表触发的入账
     */
    public void commStrike(CommMsg msg) {
        log.debug("进入资源表触发入账方法!");
        for (FunResBook c : aIFunResBookSv.getFunResBook(msg)) {
            log.debug("封装后的FunResBook:" + MyJsonUtil.toJson(c));
            enterAccount(c,msg ,true);
        }
    }

    /**
     * 批量入账触发入账
     */
    public void batchStrike(UserMsg msg) {
        log.debug("进入批量入账触发入账的方法!");
        try{
            for (FunResBook f : aIFunResBookSv.getFunResBook(msg)) {
                log.debug("封装后的FunResBook:" + MyJsonUtil.toJson(f));
                enterAccount(f,msg, false);
            }
        }
        catch(Exception e){
            log.error("get funresbook error!", e);
            ReEnterHandle.pushToStatic(msg, "get funresbook error!"+e.getMessage(),"BATCH");
        }
    }

    // 用户表和资源表统一的入账流程
    private void enterAccount(FunResBook book,Object msg, boolean isThisMonth) {
        log.error("【】开始入账!",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS));
        log.error("进入enterAccount方法，入参是【FunResBook:{},msg:{},isThisMonth:{}】",JSONObject.fromObject(book).toString(),msg,isThisMonth);
        if (book == null) {
            return;
        }
        // 当失效时间小于当前时间跳出
        try {
            if (Timestamp.valueOf(book.getINACTIVE_TIME()).getTime() < System.currentTimeMillis()) {
                log.debug("失效时间小于当前时间");
                return;
            }
        } catch (Exception e) {
            log.debug("时间格式错误");
            ReEnterHandle.pushToStatic(msg, "时间格式错误;"+e.getMessage(),"");
            return;
            // }
        }
        Timestamp startTime = DateUtil.getSysDate();
        Timestamp endTime = DateUtil.getTimeThisMonthLastSec(startTime);
        String basicOrgId = book.getBasicOrgId();
        String date = DateUtil.getDateString(DateUtil.YYYYMM);
        
        //移动联通的入账时间不同
        if (!StringUtil.isBlank(basicOrgId)) {
			if (basicOrgId.startsWith(Constants.CU_BASIC_START)) {
				startTime = getBillActiveTime(Constants.CU_BILL_DAY);
				endTime = getBillInactiveTime(Constants.CU_BILL_DAY);
				date = getBillMonth(Constants.CU_BILL_DAY);
			}else if (basicOrgId.startsWith(Constants.CM_BASIC_START)) {
				startTime = getBillActiveTime(Constants.CM_BILL_DAY);
				endTime = getBillInactiveTime(Constants.CM_BILL_DAY);
//				date = getBillMonth(Constants.CM_BILL_DAY);
			}
		}
        log.error("对应的生效时间【{}】，失效时间【{}】,所属账期【{}】",DateUtil.getDateString(startTime, DateUtil.YYYYMMDDHHMMSS),DateUtil.getDateString(endTime, DateUtil.YYYYMMDDHHMMSS),date);
        // 对清零标识进行判断，并修改生效时间和失效时间
        if (isThisMonth) {
            // 如果清零，失效时间为当月最后一秒
            if ("Y".equals(book.getRES_CLEAR_FLAG())) {
                // 生效时间大于当月最后一秒不入账
                try {
                    if (Timestamp.valueOf(book.getACTIVE_TIME()).getTime() > endTime.getTime()) {
                        log.debug("实时入账：生效时间大于当前账期最后一秒不入账");
                        return;
                    }
                } catch (Exception e) {
                    log.debug("时间格式错误");
                    ReEnterHandle.pushToStatic(msg, "处理startTime；endTime有误 ！"+e.getMessage(),"");
                    return;
                }
                log.debug("进行清零设置");
                book.setINACTIVE_TIME(endTime.toString());
            }
        }else {
            // 如果清零，生效日期为下月第一秒，失效日期为下月最后一秒
            Timestamp time = DateUtil.getTimeThisMonthFirstSec(new Timestamp(System.currentTimeMillis()));
            date = DateUtil.getDateString(time, DateUtil.YYYYMM);
            if (Timestamp.valueOf(book.getINACTIVE_TIME()).getTime() < time.getTime()) {
                log.debug("批量入账：失效小于下月第一秒，不入账");
                return;
            }
            if ("Y".equals(book.getRES_CLEAR_FLAG())) {
                try {
                    if (Timestamp.valueOf(book.getACTIVE_TIME()).getTime() > DateUtil
                            .getTimeThisMonthLastSec(time).getTime()) {
                        log.debug("批量入账：生效时间大于下月最后一秒不入账");
                        return; 
                    }
                } catch (Exception e) {
                    
                    log.debug("时间格式错误");
                    ReEnterHandle.pushToStatic(msg, "处理startTime；endTime有误 ！"+e.getMessage(),"BATCH");
                    return;
                }
                log.debug("进行清零设置");
                book.setACTIVE_TIME(time.toString());
                time = DateUtil.getTimeThisMonthLastSec(time);
                book.setINACTIVE_TIME(time.toString());
            }
        }

        // 当判重表res_dup_log_yyyymm中存在 跳出
        try {
            log.error("订购【{}】查重，账期【{}】",JSONObject.fromObject(book).toString(),date);
            if(Constants.MYSQL.equals(database)){//使用mysql查重
            	if(aIDuplicateCheckMysql.hasRecord(book, date)){
            		 Gson gson = new Gson();
                     log.debug(gson.toJson(book).toString()+"已存在判重表中");
                     return;
            	}
            }else{//使用hbase查重
                if (aIDuplicateCheck.hasRecord(book, date)) {
                    Gson gson = new Gson();
                    log.debug(gson.toJson(book).toString()+"已存在判重表中");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("判重发生错误，入参：" + MyJsonUtil.toJson(book), e);
            ReEnterHandle.pushToStatic(msg, "判重发生错误，入参：" + MyJsonUtil.toJson(book)+e.getMessage(),"BATCH");
            return;
        }
        
        int count = 0;
        StringBuilder msgBuild = new StringBuilder();
        for (TypeEnum t : TypeEnum.values()) {
            log.debug("获取amount，入参为【{}，{}】",book.getPRODUCT_ID(),t.type);
            String amount = aIFunResBookSv.getAmount(book.getPRODUCT_ID(), t.type, book.getTENANT_ID());
            if (StringUtil.isBlank(amount)) {
            	Gson gson = new Gson();
                log.debug(gson.toJson(book).toString()+"根据 product_id :"+book.getPRODUCT_ID() + " type = "+t.type + "查询入账流量为空");
                msgBuild.append(gson.toJson(book).toString()+"根据 product_id :"+book.getPRODUCT_ID() + " type = "+t.type + "查询入账流量为空").append(System.getProperty("line.separator"));
                count++;
                continue;
            }
            book.setResourceType(t.type);
            book.setTotalAmount(amount);
            log.debug(MyJsonUtil.toJson(book));
            ResourceDeposit req = aIPackResult.chResourceDeposit(book,date);
            try {
                log.error(
                        "调用dubbo，入参：{}",JSONObject.fromObject(req).toString());
                if(Constants.VIV.equals(bookCenter)){//viv入账
                    IResBalanceSV resDepositSv = DubboConsumerFactory.getService(IResBalanceSV.class);
                    resDepositSv.depositResource(req);
                }else if(Constants.CITIC.equals(bookCenter)){//中信入账
                    IAccountRecordSV iAccountRecordSV = DubboConsumerFactory.getService(IAccountRecordSV.class);
                    AmcResBookVo amcResBookVo = new AmcResBookVo();
                    BeanUtils.copyProperties(amcResBookVo,req);
                    iAccountRecordSV.saveAccountRecord(amcResBookVo);
                }else {
                    log.error("暂不支持["+bookCenter+"]入账");
                    throw new BusinessException("暂不支持["+bookCenter+"]入账");
                }
                log.error("调用入账结束！");
            } catch (Exception e) {
                log.error("dubboError:调用dubbo发生错误，入参：{}",JSONObject.fromObject(req).toString());
                log.error("dubboError:调用dubbo发生错误，",e);
                ReEnterHandle.pushToStatic(msg, "dubboError:调用dubbo发生错误，入参："+ MyJsonUtil.toJson(req)+e.getMessage(),"BATCH");
                continue;
            }

            //发信控
            sendMsg(MyJsonUtil.toJson(aIPackResult.chMsgControl(book)));
            
            log.debug("信控:" + MyJsonUtil.toJson(aIPackResult.chMsgControl(book)));
        }
        
        if(count == TypeEnum.values().length){
        	
        	StringBuffer buf = new StringBuffer();
        	buf.append("/********************************************/").append(System.getProperty("line.separator"));
        	buf.append(msgBuild.toString()).append(System.getProperty("line.separator"));;
        	buf.append("用户数据未入账").append(System.getProperty("line.separator"));;
        	buf.append("/*******************************************/").append(System.getProperty("line.separator"));
        	System.out.println(buf.toString());
        	log.debug(buf.toString());
        	
        }
        log.debug("【】入账成功!",DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS));
    }
    
    private void sendMsg(String json) {
        //信控
        String mdsns = "BaaS_OMC_MDS";//
        IMessageSender msgSender = MDSClientFactory.getSenderClient(mdsns);
        msgSender.send(json, random.nextInt(2) % 2);
//     msgSender.send("[opt-sdk-msg:"+i+"]This is a test message……", part);//第二个参数为分区键，如果不分区，传入0   
    
    }

    private Timestamp getBillActiveTime(int billDay){
    	int theDay = DateUtil.getDates();
    	Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, billDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
    	if (theDay<billDay) {
    		//小于月结日则为上月
			calendar.add(Calendar.MONTH, -1);
		}
    	return new Timestamp(calendar.getTimeInMillis());
    }
    
    private Timestamp getBillInactiveTime(int billDay){
    	int theDay = DateUtil.getDates();
    	Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, billDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
    	if (theDay>=billDay) {
    		//大于等于月结日则为下月
			calendar.add(Calendar.MONTH, 1);
		}
    	//小于月结日则为当月,则无需处理
    	return new Timestamp(calendar.getTimeInMillis());
    }
    
    private String getBillMonth(int billDay){
    	int theDay = DateUtil.getDates();
    	Calendar calendar = Calendar.getInstance();
    	if (theDay>=billDay) {
    		//大于等于月结日则为下月
			calendar.add(Calendar.MONTH, 1);
		}
    	//小于月结日则为当月,则无需处理
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
    	return df.format(calendar.getTime());
    }
    
}
