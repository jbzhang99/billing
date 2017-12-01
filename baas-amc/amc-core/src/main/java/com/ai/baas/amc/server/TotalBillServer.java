package com.ai.baas.amc.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ai.baas.amc.service.business.interfaces.ITotalBillBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 总账计算入口
 * Date: 2016年7月12日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public class TotalBillServer {
	private TotalBillServer(){}
	private static final Log LOG = LogFactory.getLog(TotalBillServer.class);
	public static void main(String[] args){
	    GenericXmlApplicationContext context = new GenericXmlApplicationContext();  
	    try {
	        String tenantId = args[0];
	        if(StringUtil.isBlank(tenantId)){
	            throw new BusinessException("传入的租户参数错误");
	        }
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MONTH, -1);
	        
	        String lastMonth = new SimpleDateFormat("yyyyMM").format(calendar.getTime());
	        LOG.info("处理总公司账单：tenantId["+tenantId+"],billMonth["+lastMonth+"]");
            context.setValidating(false);  
            context.load("/context/core-context.xml");  
            context.refresh();  
            ITotalBillBusiSV totalBillBusiSV = context.getBean(ITotalBillBusiSV.class);
            totalBillBusiSV.totalBill(tenantId, lastMonth);
        } catch (BeansException e) {
            LOG.error("总公司账单计算错误："+e.getMessage(),e);
        } catch (Exception e) {
            LOG.error("总公司账单计算错误："+e.getMessage(),e);
        }finally{
            context.close();
        }
	    
	}
}
