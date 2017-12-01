package com.ai.runner.center.mmp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.runner.center.mmp.dao.interfaces.UmsMsgServiceMapper;
import com.ai.runner.center.mmp.dao.interfaces.UmsMsgTemplateMapper;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgService;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgServiceCriteria;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgTemplate;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgTemplateCriteria;
import com.ai.runner.center.mmp.service.MmpSv;
import com.ai.runner.center.mmp.vo.PageEntity;
import com.ai.runner.center.mmp.vo.PageResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

@Service
@Transactional
public class MmpSvImpl implements MmpSv {
	public static final String S_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final Logger LOGGER = Logger.getLogger(MmpSvImpl.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public String saveUmsMsgService(String param) {
		try {
			Gson gson = new Gson();
			UmsMsgService umsMsgService = gson.fromJson(param, UmsMsgService.class);
			LOGGER.info(umsMsgService);

			UmsMsgServiceMapper umsMapper = sqlSessionTemplate.getMapper(UmsMsgServiceMapper.class);

			// 保留 -----umsMsgServiceBeforeUpdate=umsMapper.selectByPrimaryKey(umsMsgService.getServiceId())-----
			// 如果为umsMsgServiceBeforeUpdate不存在,执行insert操作,否则执行update操作,即先按主键删除,再插入数据
			if (umsMsgService.getServiceId() == null) {
				LOGGER.info("执行insert操作 saveUmsMsgService");
				umsMapper.insert(umsMsgService);
				
			} else {
				LOGGER.info("执行update操作 saveUmsMsgService");
				umsMapper.updateByPrimaryKey(umsMsgService);

				// 保留 -----umsMapper.deleteByPrimaryKey(umsMsgServiceBeforeUpdate.getServiceId())-----
				// 保留 -----umsMapper.insert(umsMsgService)-----
			}
		} catch (JsonSyntaxException e) {
			LOGGER.error("JsonSyntaxException saveUmsMsgService:" ,e);
			throw new BusinessException("101", "saveUmsMsgService JsonSyntaxException failed!"+ e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException saveUmsMsgService:" ,e);
			throw new BusinessException("102", "saveUmsMsgService PersistenceException failed!"+ e.getMessage());
		}
		
		return "saveUmsMsgService success!";
	}

	@Override
	public String delUmsMsgService(String param) {
		try {
			Gson gson = new Gson();
			UmsMsgService umsMsgService = gson.fromJson(param, UmsMsgService.class);
			LOGGER.info(umsMsgService);
			// 保留  --UmsMsgServiceCriteria ------umsCriteria=new
			// 保留 --UmsMsgServiceCriteria() ----

			// 保留 ---umsCriteria.createCriteria().-----andServiceIdEqualTo(umsMsgService.getServiceId())
			UmsMsgServiceMapper umsMapper = sqlSessionTemplate.getMapper(UmsMsgServiceMapper.class);
			// 保留 ---umsMapper.deleteByExample(umsCriteria)----
			umsMapper.deleteByPrimaryKey(umsMsgService.getServiceId());
		} catch (JsonSyntaxException e) {
			LOGGER.error("JsonSyntaxException delUmsMsgService:" + e.getMessage());
			throw new BusinessException("101", "delUmsMsgService JsonSyntaxException failed!"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException delUmsMsgService:" + e.getMessage());
			throw new BusinessException("102", "delUmsMsgService PersistenceException failed!"+e.getMessage());
		}

		return "delUmsMsgService success!";
	}

	@Override
	public String getUmsMsgService(String param) {
		String json = "";
		try {
			Gson gson = new GsonBuilder().setDateFormat(S_DATEFORMAT).create();
			// 保留  -----Map map=new HashMap()
			// 保留 -----System.out.println(param)-----
			PageEntity pe = gson.fromJson(param, PageEntity.class);
			String serviceName = pe.getName();
			int currentPage = pe.getCurrentPage();
			int pageSize = pe.getPageSize();

			UmsMsgServiceCriteria umsCriteria = new UmsMsgServiceCriteria();
			umsCriteria.createCriteria().andServicenameLike("%" + serviceName + "%");
			UmsMsgServiceMapper umsMapper = sqlSessionTemplate.getMapper(UmsMsgServiceMapper.class);
			int totalCount = umsMapper.countByExample(umsCriteria);

			int startIndex = this.getStartIndex(currentPage, pageSize);
			umsCriteria.setLimitStart(startIndex);
			umsCriteria.setLimitEnd(pageSize);
			List<UmsMsgService> umsList = umsMapper.selectByExample(umsCriteria);

			// 保留 -----------for(int i=0;i<umsList.size();i++){------------
			// 保留 --------System.out.println(umsList.get(i).getServiceId()+":"+umsList.get(i).getServicename())
			// 保留 ----}---
			PageResult<UmsMsgService> pr = new PageResult<>();
			pr.setCurrentPage(currentPage);
			pr.setTotalCount(totalCount);
			pr.setTotalPages(this.getTotalPages(totalCount, pageSize));
			pr.setResultList(umsList);
			json = gson.toJson(pr);
		} catch (JsonSyntaxException e) {
			LOGGER.error("JsonSyntaxException getUmsMsgService:" + e.getMessage());
			throw new BusinessException("101", "getUmsMsgService JsonSyntaxException context"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException getUmsMsgService:" + e.getMessage());
			throw new BusinessException("102", "getUmsMsgService PersistenceException context"+e.getMessage());
		}

		return json;
	}

	@Override
	public String saveUmsMsgTemplate(String param) {
		try {
			Gson gson = new Gson();
			UmsMsgTemplate umsMsgTemplate = gson.fromJson(param, UmsMsgTemplate.class);
			LOGGER.info(umsMsgTemplate);

			UmsMsgTemplateMapper umtMapper = sqlSessionTemplate.getMapper(UmsMsgTemplateMapper.class);

			// 如果为umsMsgTemplateBeforeUpdate不存在,执行insert操作,否则执行update操作,即先按主键删除,再插入数据
			if (umsMsgTemplate.getSequenceId() == null) {
				LOGGER.info("执行insert操作 saveUmsMsgTemplate String");
				umtMapper.insert(umsMsgTemplate);
			} else {
				LOGGER.info("执行update操作 saveUmsMsgTemplate String");
				umtMapper.updateByPrimaryKey(umsMsgTemplate);
			}
		} catch (JsonSyntaxException e) {
			LOGGER.error("JsonSyntaxException saveUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("101", "saveUmsMsgTemplate gson.fromJson failed!"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException saveUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("102", "saveUmsMsgTemplate PersistenceException failed!"+e.getMessage());
		}
		
		return "saveUmsMsgService success!";
	}

	@Override
	public String delUmsMsgTemplate(String param) {
		try {
			Gson gson = new Gson();
			UmsMsgTemplate umsMsgTemplate = gson.fromJson(param, UmsMsgTemplate.class);
			LOGGER.info(umsMsgTemplate);
			// 保留 -----UmsMsgServiceCriteria umsCriteria=new UmsMsgServiceCriteria()-----
			// 保留 -----umsCriteria.createCriteria().andServiceIdEqualTo(umsMsgService.getServiceId())-----
			UmsMsgTemplateMapper umtMapper = sqlSessionTemplate.getMapper(UmsMsgTemplateMapper.class);
			// 保留 -----umsMapper.deleteByExample(umsCriteria)-----
			umtMapper.deleteByPrimaryKey(umsMsgTemplate.getSequenceId());
		} catch (JsonSyntaxException e){
			LOGGER.error("JsonSyntaxException delUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("101", "delUmsMsgTemplate gson failed!"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException delUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("101", "delUmsMsgTemplate PersistenceException failed!"+e.getMessage());
		}

		return "delUmsMsgTemplate success!";
	}

	@Override
	public String getUmsMsgTemplate(String param) {
		String json = "";
		try {
			Gson gson = new GsonBuilder().setDateFormat(S_DATEFORMAT).create();
			PageEntity pe = gson.fromJson(param, PageEntity.class);
			String templateName = pe.getName();
			Map<String, String> map = pe.getParams();
			String serviceIdStr = map.get("serviceId");
			Long serviceId = Long.parseLong(serviceIdStr);
			int currentPage = pe.getCurrentPage();
			int pageSize = pe.getPageSize();

			// -----System.out.println("templateName="+templateName+"serviceId="+serviceId)-----
			LOGGER.info("templateName=" + templateName + "serviceId=" + serviceId);

			UmsMsgTemplateCriteria umtCriteria = new UmsMsgTemplateCriteria();
			umtCriteria.createCriteria().andServiceIdEqualTo(serviceId).andTemplateNameLike("%" + templateName + "%");
			UmsMsgTemplateMapper umtMapper = sqlSessionTemplate.getMapper(UmsMsgTemplateMapper.class);
			int totalCount = umtMapper.countByExample(umtCriteria);
			int startIndex = this.getStartIndex(currentPage, pageSize);
			umtCriteria.setLimitStart(startIndex);
			umtCriteria.setLimitEnd(pageSize);
			List<UmsMsgTemplate> umtList = umtMapper.selectByExample(umtCriteria);

			PageResult<UmsMsgTemplate> pr = new PageResult<>();
			pr.setCurrentPage(currentPage);
			pr.setTotalCount(totalCount);
			pr.setTotalPages(this.getTotalPages(totalCount, pageSize));
			pr.setResultList(umtList);
			json = gson.toJson(pr);
			// 保留  -----System.out.println(json)-----
			LOGGER.info(json);
		} catch (NumberFormatException e) {
			LOGGER.error("NumberFormatException getUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("103","getUmsMsgTemplate NumberFormatException context:"+e.getMessage());
		} catch (JsonSyntaxException e){
			LOGGER.error("JsonSyntaxException getUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("101","getUmsMsgTemplate JsonSyntaxException context:"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException getUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("102","getUmsMsgTemplate PersistenceException context:"+e.getMessage());
		}

		return json;
	}

	public String getOneServiceByServiceId(String serviceIdJson) {
		String json = "";
		try {
			LOGGER.info(serviceIdJson);
			Gson gson = new GsonBuilder().setDateFormat(S_DATEFORMAT).create();
			UmsMsgService ums = gson.fromJson(serviceIdJson, UmsMsgService.class);
			Long serviceId = ums.getServiceId();
			UmsMsgServiceCriteria umsCriteria = new UmsMsgServiceCriteria();
			umsCriteria.createCriteria().andServiceIdEqualTo(serviceId);
			UmsMsgServiceMapper umsMapper = sqlSessionTemplate.getMapper(UmsMsgServiceMapper.class);
			List<UmsMsgService> umsList = umsMapper.selectByExample(umsCriteria);
			if (umsList != null && umsList.size() != 0) {
				json = gson.toJson(umsList.get(0));
				LOGGER.info(json);
			}
		} catch (JsonSyntaxException e){
			LOGGER.error("JsonSyntaxException getOneServiceByServiceId:" + e.getMessage());
			throw new BusinessException("101","getOneServiceByServiceId JsonSyntaxException context:"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException getOneServiceByServiceId:" + e.getMessage());
			throw new BusinessException("102", "getOneServiceByServiceId PersistenceExceptioncontext:"+e.getMessage());
		}
		return json;
	}

	public String getOneTemplateBySequenceId(String sequenceIdJson) {
		String json = "";
		try {
			LOGGER.info(sequenceIdJson);
			Gson gson = new GsonBuilder().setDateFormat(S_DATEFORMAT).create();
			UmsMsgTemplate umt = gson.fromJson(sequenceIdJson, UmsMsgTemplate.class);
			Long sequenceId = umt.getSequenceId();
			UmsMsgTemplateCriteria umtCriteria = new UmsMsgTemplateCriteria();
			umtCriteria.createCriteria().andSequenceIdEqualTo(sequenceId);
			UmsMsgTemplateMapper umtMapper = sqlSessionTemplate.getMapper(UmsMsgTemplateMapper.class);
			List<UmsMsgTemplate> umtList = umtMapper.selectByExample(umtCriteria);
			if (umtList != null && umtList.size() != 0) {
				json = gson.toJson(umtList.get(0));
				LOGGER.info(json);
			}
		} catch (JsonSyntaxException e){
			LOGGER.error("JsonSyntaxException getOneTemplateBySequenceId:" + e.getMessage());
			throw new BusinessException("101", "getOneTemplateBySequenceId JsonSyntaxException context:"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException getOneTemplateBySequenceId:" + e.getMessage());
			throw new BusinessException("102", "getOneTemplateBySequenceId PersistenceException context:"+e.getMessage());
		}
		return json;
	}

	public boolean existTemplateByServiceId(String serviceIdJson) {
// 原代码			-----		boolean flag = false----
		try {
			LOGGER.info(serviceIdJson);
			Gson gson = new GsonBuilder().setDateFormat(S_DATEFORMAT).create();
			UmsMsgService ums = gson.fromJson(serviceIdJson, UmsMsgService.class);
			Long serviceId = ums.getServiceId();
			UmsMsgTemplateCriteria umtCriteria = new UmsMsgTemplateCriteria();
			umtCriteria.createCriteria().andServiceIdEqualTo(serviceId);
			UmsMsgTemplateMapper umtMapper = sqlSessionTemplate.getMapper(UmsMsgTemplateMapper.class);
			List<UmsMsgTemplate> umtList = umtMapper.selectByExample(umtCriteria);
// 原代码			-----if (umtList == null || umtList.size() == 0) {-----
// 原代码			-----	return false-----
// 原代码			-----} else {-----
// 原代码			-----	return true-----
// 原代码			-----}-----
			//下面是规定写法 及其丑陋
			return ! (umtList == null || umtList.size() == 0) ;
		} catch (JsonSyntaxException e){
			LOGGER.error("JsonSyntaxException existTemplateByServiceId:" + e.getMessage());
			throw new BusinessException("101", "existTemplateByServiceId JsonSyntaxException context:"+e.getMessage());
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException existTemplateByServiceId:" + e.getMessage());
			throw new BusinessException("102", "existTemplateByServiceId PersistenceException context:"+e.getMessage());
		}
	}

	private int getStartIndex(int currentPage, int pageSize) {
		int startIndex = (currentPage - 1) * pageSize;
		if (startIndex < 0) {
			startIndex = 0;
		}
		return startIndex;
	}

	private int getTotalPages(int totalCount, int pageSize) {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize + 1;
		}
	}

	/**
	 * 使用对象保存模板信息
	 */
	@Override
	public String saveUmsMsgTemplate(UmsMsgTemplate umsMsgTemplate) {
		try {
			UmsMsgTemplateMapper umtMapper = sqlSessionTemplate.getMapper(UmsMsgTemplateMapper.class);

			// 如果为umsMsgTemplateBeforeUpdate不存在,执行insert操作,否则执行update操作,即先按主键删除,再插入数据
			if (umsMsgTemplate.getSequenceId() == null) {
				LOGGER.info("执行insert操作 saveUmsMsgTemplate");
				umtMapper.insert(umsMsgTemplate);
			} else {
				LOGGER.info("执行update操作 saveUmsMsgTemplate");
				umtMapper.updateByPrimaryKey(umsMsgTemplate);
			}
		} catch (PersistenceException e) {
			LOGGER.error("PersistenceException saveUmsMsgTemplate:" + e.getMessage());
			throw new BusinessException("101", "saveUmsMsgTemplate PersistenceException failed!"+e.getMessage());
			//----- "FAIL"-----
		}

		return "OK";
	}
}
