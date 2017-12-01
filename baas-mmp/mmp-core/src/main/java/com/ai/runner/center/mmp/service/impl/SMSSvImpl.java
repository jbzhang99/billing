package com.ai.runner.center.mmp.service.impl;

import java.sql.Timestamp;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.runner.center.mmp.dao.interfaces.SgipSrcGsmMapper;
import com.ai.runner.center.mmp.dao.mapper.bo.SgipSrcGsm;
import com.ai.runner.center.mmp.service.SMSSv;
import com.ai.runner.center.mmp.vo.SMSInputVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Service
@Transactional
public class SMSSvImpl implements SMSSv {

	private static final Logger LOGGER = Logger.getLogger(SMSSvImpl.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public String dataInput(String param) {
		// 保留 -----System.out.println("进入dataInput方法,打印入参json:"+param)-----
		LOGGER.info("进入dataInput方法,打印入参json:" + param);
		try {
			Gson gson = new Gson();
			SMSInputVo sMSInputVo = gson.fromJson(param, SMSInputVo.class);
			boolean flag = false;
			if (sMSInputVo.getTenementid() != null && sMSInputVo.getSystemid() != null
					&& sMSInputVo.getVerifyid() != null && sMSInputVo.getTemplateid() != null
					&& sMSInputVo.getPhone() != null && sMSInputVo.getGsmcontent() != null
					&& sMSInputVo.getPriority() != null && sMSInputVo.getServicetype() != null) {
				if ((sMSInputVo.getServicetype() + "").matches("[0-2]") && sMSInputVo.getPriority().matches("[0-9]")) {
					flag = true;
				} else {
					LOGGER.info("serviceType只能取0,1,2.并且priority只能取0-9");
				}
			} else {
				LOGGER.info("所有字段都不能为空!");
			}

			if (!flag) {
				return "422";
			} else {

				// 保存到数据库中
				dataInput(sMSInputVo);

				return "200";
			}
		} catch (JsonSyntaxException e){
			LOGGER.error("JsonSyntaxException:" + e.getMessage());
			return "401";
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException:" + e.getMessage());
			return "401";
		}
	}

	@Override
	public String dataInput(SMSInputVo sMSInputVo) {
		LOGGER.info("dataInput(): Phone = [" + sMSInputVo.getPhone() + "]");
		SgipSrcGsm ssg = new SgipSrcGsm();

		ssg.setSrcName(sMSInputVo.getTenementid() + sMSInputVo.getSystemid());
		ssg.setTemplateId(Long.parseLong(sMSInputVo.getTemplateid()));
		ssg.setServicetype(sMSInputVo.getServicetype() + "");
		ssg.setVerifyid(Long.parseLong(sMSInputVo.getVerifyid()));
		ssg.setPhone(sMSInputVo.getPhone());
		ssg.setGsmcontent(sMSInputVo.getGsmcontent());
		ssg.setFlag(0);
		ssg.setPriority(Integer.parseInt(sMSInputVo.getPriority()));
		ssg.setCreateTime(new Timestamp(System.currentTimeMillis()));

		SgipSrcGsmMapper ssgMapper = sqlSessionTemplate.getMapper(SgipSrcGsmMapper.class);

		ssgMapper.insert(ssg);

		LOGGER.info("dataInput(): after insert()");

		return "OK";
	}
}
