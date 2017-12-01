package com.ai.baas.mt.web.controller.configure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.dshm.api.dshmprocess.interfaces.IdshmSV;
import com.ai.baas.dshm.api.dshmprocess.params.AddEbillingShmTableRecord;
import com.ai.baas.dshm.api.dshmprocess.params.FieldInfo;
import com.ai.baas.dshm.api.dshmprocess.params.FieldListResponse;
import com.ai.baas.dshm.api.dshmprocess.params.FieldQueryRequest;
import com.ai.baas.dshm.api.dshmprocess.params.PagingTableInfoRequest;
import com.ai.baas.dshm.api.dshmprocess.params.ReqParam;
import com.ai.baas.dshm.api.dshmprocess.params.ShmTableInfoVo;
import com.ai.baas.dshm.api.dshmprocess.params.ShmTableRecordVo;
import com.ai.baas.dshm.api.dshmprocess.params.TableQuery;
import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.baas.mt.web.model.AddTableInfo;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 配置管理-加载配置
 */
@RestController
@RequestMapping("/config/loadcfg")
public class ConfigureLoadController {

	private static final Logger LOG = Logger.getLogger(ConfigureLoadController.class);

	/**
	 * 列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView discountProduct(HttpServletRequest request) {
		return new ModelAndView("jsp/configure/configLoadList");
	}

	/**
	 * 配置加载查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getList")
	public ResponseData<PageInfo<ShmTableInfoVo>> getList(HttpServletRequest request) {

		ResponseData<PageInfo<ShmTableInfoVo>> responseData = null;

		try {
			IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);

			PagingTableInfoRequest queryInfo = new PagingTableInfoRequest();

			String strPageNo = (null == request.getParameter(BaaSMTConstants.PAGENO)) ? "1"
					: request.getParameter(BaaSMTConstants.PAGENO);
			String strPageSize = (null == request.getParameter(BaaSMTConstants.PAGESIZE)) ? "10"
					: request.getParameter(BaaSMTConstants.PAGESIZE);
			PageInfo<ShmTableInfoVo> searchpage = new PageInfo<ShmTableInfoVo>();
			searchpage.setPageNo(Integer.parseInt(strPageNo));
			searchpage.setPageSize(Integer.parseInt(strPageSize));
			queryInfo.setPageInfo(searchpage);

			queryInfo.setTableName(request.getParameter("tabName"));

			LOG.debug("配置加载查询入参:" + JSONObject.fromObject(queryInfo).toString());
			PageInfo<ShmTableInfoVo> resultInfo = idshmSV.pagingTableInfo(queryInfo);
			LOG.debug("配置加载查询出参:" + JSONArray.fromObject(resultInfo).toString());

			responseData = new ResponseData<PageInfo<ShmTableInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功",
					resultInfo);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<ShmTableInfoVo>>(ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.error("获取信息出错：", e);
		}
		return responseData;
	}

	/**
	 * 加载、批量加载
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshLoader")
	public ResponseData<Object> refreshLoader(HttpServletRequest request) {
		ResponseData<Object> responseData = null;

		String tabIdStr = request.getParameter("tabIds");
		String tabNameStr = request.getParameter("tabNames");
		try {
			if (StringUtils.isNoneBlank(tabIdStr, tabNameStr)) {
				String[] tabIds = getJsonToStringArray(tabIdStr);
				String[] tabNames = getJsonToStringArray(tabNameStr);

				IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
				ReqParam param = new ReqParam();
				param.setTableId(tabIds);
				param.setTableNames(tabNames);

				int flag = idshmSV.refreshLoader(param);
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "加载完成", flag);
			}
		} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "加载失败");
			LOG.error("加载缓存时出错：", e);
		}
		return responseData;
	}

	/**
	 * 释放、批量释放
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shmDelete")
	public ResponseData<Object> shmDelete(HttpServletRequest request) {
		ResponseData<Object> responseData = null;

		String tabIdStr = request.getParameter("tabIds");
		String tabNameStr = request.getParameter("tabNames");
		try {
			if (StringUtils.isNoneBlank(tabIdStr, tabNameStr)) {
				String[] tabIds = getJsonToStringArray(tabIdStr);
				String[] tabNames = getJsonToStringArray(tabNameStr);

				IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
				ReqParam param = new ReqParam();
				param.setTableId(tabIds);
				param.setTableNames(tabNames);

				int flag = idshmSV.shmDelete(param);
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "释放完成", flag);
			}
		} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "释放失败");
			LOG.error("释放缓存时出错：", e);
		}
		return responseData;
	}

	/**
	 * 批量删除配置
	 * 
	 * @param request
	 * @return
	 * @author gaogang
	 */
	@RequestMapping("/shmFieldsDelete")
	public ResponseData<Object> shmFieldsDelete(HttpServletRequest request) {
		ResponseData<Object> responseData = null;

		String tabIdStr = request.getParameter("tabIds");
		String tabNameStr = request.getParameter("tabNames");
		try {
			if (StringUtils.isNoneBlank(tabIdStr, tabNameStr)) {
				String[] tabIds = getJsonToStringArray(tabIdStr);
				String[] tabNames = getJsonToStringArray(tabNameStr);
				Map<String, String> map = new HashMap<String, String>();
				IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
				for (int i = 0; i < tabIds.length; i++) {
					map.put(tabIds[i], tabNames[i]);
				}
				String ret = idshmSV.deleteTable(map);
				/*
				 * ReqParam param = new ReqParam(); param.setTableId(tabIds);
				 * param.setTableNames(tabNames);
				 * 
				 * int flag = idshmSV.shmDelete(param);
				 */
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "释放完成", ret);
			}
		} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "释放失败");
			LOG.error("释放缓存时出错：", e);
		}
		return responseData;
	}

	/**
	 * 删除表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteTable")
	public ResponseData<Object> deleteTable(HttpServletRequest request) {
		ResponseData<Object> responseData = null;

		String tabId = request.getParameter("tabId");
		String tabName = request.getParameter("tabName");
		try {
			if (StringUtils.isNoneBlank(tabId, tabName)) {
				IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
				Map<String, String> param = new HashMap<String, String>();
				param.put(tabId,tabName);

				String flag = idshmSV.deleteTable(param);
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "删除完成", flag);
			}
		} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "删除失败");
			LOG.error("删除指定表时出错：", e);
		}
		return responseData;
	}

	/**
	 * 将json数组转化为String型
	 */
	private static String[] getJsonToStringArray(String str) {
		JSONArray jsonArray = JSONArray.fromObject(str);
		String[] arr = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			arr[i] = jsonArray.getString(i);
		}
		return arr;
	}

	@RequestMapping("/toAdd")
	public ModelAndView toAddTablePag() {

		return new ModelAndView("jsp/configure/addTable");
	}

	@RequestMapping(value = "/getDbNameList")
	public ResponseData<String[]> getDbNameList() {
		ResponseData<String[]> responseData;
		IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
		String[] dbNames = idshmSV.ListDBName();
		responseData = new ResponseData<String[]>(ResponseData.AJAX_STATUS_SUCCESS, "数据库列表查询成功", dbNames);
		return responseData;
	}

	@RequestMapping(value = "/getTableListByDb")
	public ResponseData<List<String>> getTableListByDb(String dbName) {
		ResponseData<List<String>> responseData = null;
		if (!StringUtil.isBlank(dbName)) {
			IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
			TableQuery tableQuery = new TableQuery();
			tableQuery.setDbName(dbName);
			List<String> tableList = idshmSV.ListTableName(tableQuery);
			responseData = new ResponseData<List<String>>(ResponseData.AJAX_STATUS_SUCCESS, "数据库列表查询成功", tableList);
		}
		return responseData;
	}

	@RequestMapping(value = "/getFieldsByDbAndTable")
	public ResponseData<List<FieldInfo>> getFieldsByDbAndTable(String dbName, String tableName) {
		ResponseData<List<FieldInfo>> responseData = null;
		if (!StringUtil.isBlank(dbName) && !StringUtil.isBlank(tableName)) {
			IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
			List<FieldInfo> fieldInfos = idshmSV.ListFiledName(dbName, tableName);
			responseData = new ResponseData<List<FieldInfo>>(ResponseData.AJAX_STATUS_SUCCESS, "数据库列表查询成功", fieldInfos);
		}
		return responseData;
	}

	@RequestMapping(value = "/addTable")
	public ResponseData<String> addTable(AddTableInfo tableInfo) {
		IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class);
		AddEbillingShmTableRecord tableinfos = new AddEbillingShmTableRecord();
		tableinfos.setDbConnect(tableInfo.getDbName());
		tableinfos.setTableName(tableInfo.getTableName());
		String[] indexkeys = tableInfo.getIndexKeys().split(",");
		tableinfos.setIsIndexKey(Arrays.asList(indexkeys));
		String[] fields = tableInfo.getCacheFields().split(",");

		List<String> primaryKeys = null;
		if (StringUtils.isNotBlank(tableInfo.getPrimaryKeys())) {
			String[] tmpStr=tableInfo.getPrimaryKeys().split(",");
			primaryKeys = Arrays.asList(tmpStr);
		}
		
		List<ShmTableRecordVo> recordList = new ArrayList<ShmTableRecordVo>();
		for (String filedName : fields) {
			ShmTableRecordVo vo = new ShmTableRecordVo();
			vo.setFieldName(filedName);
			vo.setFieldType(0);
			vo.setIsHashkey(0);
			if (primaryKeys != null && primaryKeys.contains(filedName)) {//修改0是主键，1表示不是主键
				vo.setIsPrimary(0);  
			} else {
				vo.setIsPrimary(1);
			}
			recordList.add(vo);
		}
		tableinfos.setShmTableRecordVoList(recordList);
		String s = idshmSV.addTable(tableinfos);
		return new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "数据库列表查询成功", s);
	}

	@RequestMapping(value = "/getFields")
	public ResponseData<FieldListResponse> getFields(HttpServletRequest request) {
		ResponseData<FieldListResponse> responseData = null;

		try {
			String tableId = request.getParameter("tabId");
			String tableName = request.getParameter("tabName");
			IdshmSV idshmSV = DubboConsumerFactory.getService(IdshmSV.class); 
			FieldQueryRequest rq=new FieldQueryRequest();
			rq.setTableId(Integer.parseInt(tableId));
			rq.setTableName(tableName);
			FieldListResponse fr=idshmSV.getTableFieldRecord(rq);
			responseData = new ResponseData<FieldListResponse>(ResponseData.AJAX_STATUS_SUCCESS,"数据库列表查询成功",fr);
		} catch (Exception e) {
			responseData = new ResponseData<FieldListResponse>(ResponseData.AJAX_STATUS_FAILURE,"数据库列表查询失败",null);
			LOG.error("数据库列表查询失败",e);
		}

		return responseData;
	}
}
