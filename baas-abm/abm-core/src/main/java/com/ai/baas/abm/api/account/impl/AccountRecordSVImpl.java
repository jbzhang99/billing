package com.ai.baas.abm.api.account.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ai.baas.abm.api.account.interfaces.IAccountRecordSV;
import com.ai.baas.abm.api.account.params.AmcResBookVo;
import com.ai.baas.abm.service.business.interfaces.IAccountRecordBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 
 * @author wangluyang
 *
 */
@Service
@Component
public class AccountRecordSVImpl implements IAccountRecordSV{
	
	private static final Logger LOG = LogManager.getLogger(AccountRecordSVImpl.class);
	
    public static final String[] PATH = { "classpath:context/core-context.xml" };
    public static ClassPathXmlApplicationContext context;

	@Autowired
	private IAccountRecordBusiSV AccountRecordBusiSV;
	
	@Override
	public BaseResponse saveAccountRecord(AmcResBookVo bookVo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		
		return AccountRecordBusiSV.saveAccountRecord(bookVo);
	}

	@Override
	public BaseResponse clearExpireAccountRecord() throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		
		return AccountRecordBusiSV.clearExpireAccountRecord();
	}
	
	public static void main(String[] args) {
		
		   // 启动spring容器
		   LOG.info("启动spring容器,配置文件路径为"+PATH[0]);
	       System.out.println("启动spring容器,配置文件路径为"+PATH[0]);
	       context = new ClassPathXmlApplicationContext(PATH);
	       context.registerShutdownHook();
	       context.start();
	       AccountRecordSVImpl recordSVImpl = context.getBean(AccountRecordSVImpl.class);
	       recordSVImpl.clearExpireAccountRecord();
	       System.out.println("finish");
		}
}
