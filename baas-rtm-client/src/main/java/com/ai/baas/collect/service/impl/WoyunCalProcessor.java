package com.ai.baas.collect.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.ai.baas.collect.util.DateUtil;
import com.ai.baas.collect.util.ExchangeIdUtil;
import com.ai.baas.collect.util.HttpClientUtil;
import com.ai.baas.collect.util.JsonUtil;
import com.ai.baas.collect.service.ICalProcessor;
import com.ai.baas.collect.service.ServiceStart;
import com.ai.baas.collect.vo.Constants;
import com.ai.baas.collect.vo.ErrorInfo;
import com.ai.baas.collect.vo.RtmResponse;
import com.ai.baas.collect.vo.ServiceParam;
import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.Logger;

@Component("woyunprocessor")
public class WoyunCalProcessor implements ICalProcessor {
	private final Log logger = LogFactory.getLog(WoyunCalProcessor.class);

	@Override
	public int doProcessor(String szContent, ServiceParam serviceParam) {
		// TODO Auto-generated method stub
		// 开始解析字符串进行发送
		logger.debug(szContent);

		String arContent[] = szContent.split(Constants.WO_SPLIT, -1);
		if (arContent.length != Constants.WO_FIELD_NUM) {
			// 错误行
			return ErrorInfo.ERROR_FIELD_NUM.getErrCode();
		}
		// 获取到数组

		// 进行id转换
		String serviceId = "";

		if ("Y".equals(serviceParam.getIsExchange())) {

			List<Map<String, String>> results = ExchangeIdUtil.getServiceId(
					serviceParam.getTenantId(), arContent[3]);
			if (results == null) {
				logger.error(serviceParam.getTenantId() + "," + arContent[3]
						+ "未能找到对应service_id");
				return ErrorInfo.NO_SERVICE_ID.getErrCode();
			} else {
				for (Map<String, String> data : results) {
					for (Entry<String, String> entry : data.entrySet()) {
						String key = entry.getKey();
						if ("service_id".equals(key)) {
							serviceId = entry.getValue();
							break;
						}

					}// end for
				}// end for
			}
			// serviceId = arContent[3];
		} else {
			serviceId = arContent[3];
		}
		logger.debug("获取到service_id:" + serviceId);
		// 拼接报文头
		StringBuilder strHeader = new StringBuilder();
		StringBuilder strBody = new StringBuilder();
		StringBuilder strSendContent = new StringBuilder();

		strHeader.append(serviceParam.getTenantId());
		strHeader.append(Constants.HEAD_SPLIT);
		strHeader.append(serviceParam.getSystemId());
		strHeader.append(Constants.HEAD_SPLIT);
		/*
		 * strHeader.append(serviceId); strHeader.append(arContent[2]); Date dt
		 * = new Date(); String time = String.valueOf(dt.getTime());
		 * strHeader.append(time);
		 */
		strHeader.append(serviceParam.getTenantId());
		strHeader.append(arContent[0]);

		strHeader.append(Constants.HEAD_SPLIT);
		strHeader.append(serviceParam.getRtmUser());
		strHeader.append(Constants.HEAD_SPLIT);
		strHeader.append(serviceParam.getRtmPassword());
		strHeader.append(Constants.HEAD_SPLIT);
		// 拼接报文体
		
		strBody.append(serviceParam.getSource());   //--1
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(serviceId);					//--2
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[3]); // 资源标志        //--3
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[0]);// 流水号		//--4
		strBody.append(Constants.MESSAGE_SPLIT);

		String value = "";

		//根据计费资源存入值
		switch (arContent[2]) {								
		case Constants.BILL_TIME:
			value = DateUtil.difHour(arContent[5], arContent[6]);
			break;
		case Constants.BILL_STREAM:
			value = String.valueOf(Long.parseLong(arContent[8])
					+ Long.parseLong(arContent[9]));
			break;
		case Constants.BILL_TIMES:
			if(Long.parseLong(arContent[10]) >= 10000){
				value = arContent[10];
			}else{
				value = "0";
			}
			break;
		case Constants.BILL_STORAGE:
			value = arContent[11];
			break;
		default:
			return ErrorInfo.ERR_BILL_TYPE.getErrCode();
		}
		//value = arContent[2];

		strBody.append(value); // 值			//--5
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[5]);// 开始时间		//--6
		strBody.append(Constants.MESSAGE_SPLIT);

		String type = "";
		switch (arContent[2]) {
		case Constants.BILL_TIME:
			type = Constants.TYPE_TIME;
			break;
		case Constants.BILL_STREAM:
			type = Constants.TYPE_STREAM;
			break;
		case Constants.BILL_STORAGE:
			type = Constants.TYPE_STORAGE;
			break;
		case Constants.BILL_TIMES:
			type = Constants.TYPE_TIMES;
			break;
		default:
			return ErrorInfo.ERR_BILL_TYPE.getErrCode();
		}
		strBody.append(type);// 类型					//--7
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[6]);// 结束时间		//--8
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[1]);// 原始资源类型		//--9
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[2]);// 原始计费资源项		//--10
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[4]);// 开始时间		//--11
		strBody.append(Constants.MESSAGE_SPLIT);
		strBody.append(arContent[7]);// 开始时间		//--12
		
		strSendContent.append(Constants.RTMHEAD).append(strHeader.toString())
				.append(strBody.toString()).append(Constants.RTMEND);

		logger.debug("发送rtm：" + strSendContent.toString());
		HttpResponse rs = null;
		try {
			rs = HttpClientUtil.send(serviceParam.getRtmUrl(),
					strSendContent.toString());
		} catch (Exception e) {
			e.printStackTrace();
			rs = null;
		}
		if (rs == null) {
			logger.error("发送rtm返回空值");
			return ErrorInfo.ERR_SEND_RTM.getErrCode();
		}
		// 发送完成 判断结果
		if (rs != null && rs.getStatusLine().getStatusCode() != 200) {
			logger.error("发送rtm：" + rs.getStatusLine().getStatusCode());
			return ErrorInfo.ERR_SEND_RTM.getErrCode();
		}
		// 判断rtm的返回结果
		try {
			String strRes = EntityUtils.toString(rs.getEntity());
			logger.debug("返回内容：" + strRes);
			// 转换内容
			RtmResponse rsObject = JsonUtil.jsonToBean(strRes,
					RtmResponse.class);
			logger.debug("返回值：" + rsObject.getResponseMessage());
			if (!Constants.RTM_SUCCESS_CODE.equals(rsObject
					.getResponseMessage())) {
				logger.error("RTM return ERRor："
						+ EntityUtils.toString(rs.getEntity()));
				return ErrorInfo.ERR_SEND_RTM.getErrCode();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ErrorInfo.SUCCESS.getErrCode();
	}

}
