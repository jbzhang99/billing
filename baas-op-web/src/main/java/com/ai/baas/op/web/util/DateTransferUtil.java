package com.ai.baas.op.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间转换工具类
 *
 * Date: 2016年5月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public final class DateTransferUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateTransferUtil.class);
	
    /**
     * 将秒转换为时分秒
     * @return
     * @author gaogang
     * @ApiDocMethod
     * @ApiCode
     */
    public static String tranferSec(Long sec){
    	
    	
    	Long second=sec%60;
    	
    	Long totalMinute=sec/60;
    	
    	Long minutes=totalMinute%60;
    	
    	
    	Long hour=totalMinute/60;
    	
    	String time=hour+"小时"+minutes+"分钟"+second+"秒";
    	
    	return time;
    }
    
    public static void main(String[] args) {
    	LOGGER.info(tranferSec(63L));
	}
}
