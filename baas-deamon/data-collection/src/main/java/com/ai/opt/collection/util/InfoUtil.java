package com.ai.opt.collection.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ai.opt.collection.model.TRUserInfo;

public class InfoUtil {
	private static final Logger LOG = LogManager.getLogger(InfoUtil.class);

	// kafka配置信息路径
	private static final String PATH = "context/trinfo.properties";

	// 默认配置信息
	private static final Properties propModel;

	// 实际配置信息
	private Properties prop;

	// 加载静态配置信息
	static {
		propModel = new Properties();
		try {
			propModel.load(InfoUtil.class.getClassLoader().getResourceAsStream(PATH));
		} catch (IOException e) {
			LOG.error("加载失败", e);
		}
	}

	public static void main(String[] args) {

		System.out.println(propModel.getProperty("tr.password"));
	}
	public static TRUserInfo getTR(){
		TRUserInfo userInfo=new TRUserInfo();
		userInfo.setPassword(propModel.getProperty("tr.password"));
		userInfo.setResource(propModel.getProperty("tr.resource"));
		userInfo.setServiceType(propModel.getProperty("tr.service"));
		userInfo.setTenantId(propModel.getProperty("tr.tenantId"));
		userInfo.setUserName(propModel.getProperty("tr.username"));
		return userInfo;
	}

}
