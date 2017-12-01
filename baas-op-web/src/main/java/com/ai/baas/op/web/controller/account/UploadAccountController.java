package com.ai.baas.op.web.controller.account;

import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.constants.VerifyConstants.ResultCodeConstants;
import com.ai.baas.op.web.model.account.FileData;
import com.ai.baas.op.web.model.account.ImportLogShowVo;
import com.ai.baas.op.web.model.account.UploadAccountData;
import com.ai.baas.op.web.util.SftpUtil;
import com.ai.baas.smc.api.billdetail.interfaces.IBillDetailSV;
import com.ai.baas.smc.api.billdetail.param.BillDetailDataImportRequest;
import com.ai.baas.smc.api.queryimportlog.interfaces.IQueryImportLogSV;
import com.ai.baas.smc.api.queryimportlog.param.ImportLogVo;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogRequest;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogResponse;
import com.ai.baas.smc.api.streamfilemanage.interfaces.IStreamFileInputSV;
import com.ai.baas.smc.api.streamfilemanage.param.StreamFileParam;
import com.ai.baas.smc.api.sysparamcache.interfaces.ISmcSysParamCache;
import com.ai.baas.smc.api.sysparamcache.param.GetSysParamListRequest;
import com.ai.baas.smc.api.sysparamcache.param.SmcSysParam;
import com.ai.net.xss.util.StringUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.paas.ipaas.util.JSonUtil;
import com.jcraft.jsch.ChannelSftp;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class UploadAccountController {
	private static final Logger LOGGER = Logger.getLogger(UploadAccountController.class);

	
	@RequestMapping("/list")
    public ModelAndView toQueryResult(HttpServletRequest request) {
		return new ModelAndView("jsp/account/queryResult");
    }
	
	@RequestMapping("/searchImportLogList")
	@ResponseBody
	public ResponseData<PageInfo<ImportLogShowVo>> searchImportLogs(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = user.getTenantId();
		QueryImportLogRequest req = new QueryImportLogRequest();
		IQueryImportLogSV iQueryImportLogSV = DubboConsumerFactory.getService("iQueryImportLogSV");
		ResponseData<PageInfo<ImportLogShowVo>> responseData = null;
		req.setTenantId(tenantId);
		PageInfo<ImportLogVo> pageInfo = new PageInfo<ImportLogVo>();
		String strPageNo = (null == request.getParameter(BaaSOPConstants.PAGENO)) ? "1" : request.getParameter(BaaSOPConstants.PAGENO);
		String strPageSize = (null == request.getParameter(BaaSOPConstants.PAGESIZE)) ? "5" : request.getParameter(BaaSOPConstants.PAGESIZE);
		pageInfo.setPageNo(Integer.parseInt(strPageNo));
		pageInfo.setPageSize(Integer.parseInt(strPageSize));
		try {
			req.setPageInfo(pageInfo);
			QueryImportLogResponse resultInfo = iQueryImportLogSV.queryImportLog(req);
			PageInfo<ImportLogVo> result = resultInfo.getPageInfo();
			PageInfo<ImportLogShowVo> importLogPageInfo = null;
			// 翻译状态
			if (result != null) {
				importLogPageInfo = new PageInfo<ImportLogShowVo>();
				importLogPageInfo.setCount(result.getCount());
				Integer pageNo = result.getPageNo();
				importLogPageInfo.setPageNo(pageNo);
				Integer pageSize = result.getPageSize();
				importLogPageInfo.setPageSize(pageSize);
				List<ImportLogVo> logList = result.getResult();
				if (!CollectionUtil.isEmpty(logList)) {
					List<ImportLogShowVo> logShowList = new LinkedList<ImportLogShowVo>();
					int count = 1;
					for (ImportLogVo log : logList) {
						ImportLogShowVo importLog = new ImportLogShowVo();
						BeanUtils.copyProperties(importLog, log);
						//设置序号
						Integer index= (pageNo-1)*pageSize + count++;
						importLog.setIndex(index);
						String state = log.getState();
						// 设置文件异常原因
						if ("9".equals(state)) {
							importLog.setExceptMsg(log.getStateDesc());
						}
						// 翻译状态
						String stateName = ImportLogUtil.getImportStateName(state);
						importLog.setStateName(stateName);
						// 翻译文件类型
						String dataTypeName = ImportLogUtil.getDataTypeName(log.getDataType());
						importLog.setDataTypeName(dataTypeName);
						// 翻译流水类型
						String objectIdName = ImportLogUtil.getFileTypeName(log.getObjectId(),tenantId);
						importLog.setObjectIdName(objectIdName);
						// 转换时间
						String dateStr = ImportLogUtil.getYearMonthDate(log.getBillTimeSn());
						importLog.setBillTimeMsg(dateStr);
						logShowList.add(importLog);
					}
					importLogPageInfo.setResult(logShowList);
				}

			}
			responseData = new ResponseData<PageInfo<ImportLogShowVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", importLogPageInfo);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<ImportLogShowVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
			LOGGER.error("获取信息出错：", e);
		}
		return responseData;
	}
	
	@RequestMapping("/upload")
	public ModelAndView toUploadPage(HttpServletRequest request) {
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		ISmcSysParamCache iSmcSysParamCache = DubboConsumerFactory.getService("iSmcSysParamCache");
		GetSysParamListRequest paramListRequest = new GetSysParamListRequest();
		paramListRequest.setTenantId(tenantId);
		paramListRequest.setTypeCode("STL_ELEMENT");
		paramListRequest.setParamCode("OBJECT_ID");
		List<SmcSysParam> sysParams = iSmcSysParamCache.getSysParams(paramListRequest);
		Map<String,String> model = new HashMap<String,String>();
		if(sysParams!=null && sysParams.size()>0){
			String serviceTypeJSone = JSonUtil.toJSon(sysParams);
			model.put("serviceTypeList", serviceTypeJSone);
		}else{
			model.put("serviceTypeList", "[]");
		}
		return new ModelAndView("jsp/account/uploadAccount",model);
	}

	/**
	 * 上传文件至服务器
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadToServer")
	public ResponseData<FileData> uploadToServer(HttpServletRequest request) {
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		ResponseData<FileData> responseData = null;
		ChannelSftp sftp = null;
		try {
			MultipartRequest multipartRequest = (MultipartRequest) request;
			MultipartFile uploadFile = multipartRequest.getFile("uploadFile");
			ISmcSysParamCache iSmcSysParamCache = DubboConsumerFactory.getService("iSmcSysParamCache");
			String tenantId = userClient.getTenantId();
			String userName = null;
			String userPWD = null;
			String uploadUrl = null;
			GetSysParamListRequest paramListRequest1 = new GetSysParamListRequest();
			paramListRequest1.setTenantId(tenantId);
			paramListRequest1.setTypeCode("SFTP_CONF");
			paramListRequest1.setParamCode("USER_NAME");
			List<SmcSysParam> sysParamsName = iSmcSysParamCache.getSysParams(paramListRequest1);
			if (sysParamsName != null && sysParamsName.size() > 0) {
				userName = sysParamsName.get(0).getColumnValue();
			}
			GetSysParamListRequest paramListRequest2 = new GetSysParamListRequest();
			paramListRequest2.setTenantId(tenantId);
			paramListRequest2.setTypeCode("SFTP_CONF");
			paramListRequest2.setParamCode("PWD");
			List<SmcSysParam> sysParamsPWD = iSmcSysParamCache.getSysParams(paramListRequest2);
			if (sysParamsPWD != null && sysParamsPWD.size() > 0) {
				userPWD = sysParamsPWD.get(0).getColumnValue();
			}
			GetSysParamListRequest paramListRequest3 = new GetSysParamListRequest();
			paramListRequest3.setTenantId(tenantId);
			paramListRequest3.setTypeCode("SFTP_CONF");
			paramListRequest3.setParamCode("upload_url_diff_file");
			List<SmcSysParam> sysParamsURL = iSmcSysParamCache.getSysParams(paramListRequest3);
			if (sysParamsURL != null && sysParamsURL.size() > 0) {
				uploadUrl = sysParamsURL.get(0).getColumnValue();
			}
			if (userName == null || userPWD == null || uploadUrl == null) {
				responseData = new ResponseData<FileData>(ResponseData.AJAX_STATUS_FAILURE, "上传失败", null);
				LOGGER.error("上传失败，ftp账号相关数据不存在");
			}
			sftp = SftpUtil.connect(uploadUrl.split(":")[0], 22, userName, userPWD);
			String fileName = SftpUtil.upload(uploadUrl.split(":")[1], uploadFile, sftp);
			FileData fileData = new FileData();
			fileData.setName(fileName);
			fileData.setPosition(uploadUrl);
			responseData = new ResponseData<FileData>(ResponseData.AJAX_STATUS_SUCCESS, "上传成功", fileData);
			LOGGER.info("上传成功");
		} catch (Exception e) {
			responseData = new ResponseData<FileData>(ResponseData.AJAX_STATUS_FAILURE, "上传失败", null);
			LOGGER.error("上传失败", e);
		} finally {
			SftpUtil.disconnect(sftp);
		}
		return responseData;
	}

	@RequestMapping("/uploadFile")
	@ResponseBody
	public ResponseData<String> uploadFile(HttpServletRequest request, UploadAccountData uploadAccountData) {
		String dataType = uploadAccountData.getDataType();// 文件类型
		if ("order".equals(dataType)) {
			return uploadFileData(request, uploadAccountData);
		} else if ("bill".equals(dataType)) {
			return uploadBillData(request, uploadAccountData);
		} else {
			ResponseData<String> responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "上传文件类型错误");
			ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
			responseData.setResponseHeader(responseHeader);
			return responseData;
		}
	}

	/**
	 * 上传第三方账单文件
	 * 
	 * @param request
	 * @param uploadAccountData
	 * @return
	 */
	private ResponseData<String> uploadBillData(HttpServletRequest request, UploadAccountData uploadAccountData) {
		String dataObj = uploadAccountData.getDataObj();// 流水类型
		String fileName = uploadAccountData.getFileName();// 文件名称
		String filePosition = uploadAccountData.getFilePosition();// 文件路径
		String accountPeriod = uploadAccountData.getAccountPeriod();// 账期
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String operId = Long.toString(userClient.getAccountId());// 操作员id
		String tenantId = userClient.getTenantId();
		IBillDetailSV iBillDetailSV = DubboConsumerFactory.getService("iBillDetailSV");
		BillDetailDataImportRequest importRequest = new BillDetailDataImportRequest();
		importRequest.setObjectId(dataObj);
		importRequest.setImpFileName(fileName);
		importRequest.setImpFileUrl(filePosition);
		importRequest.setBillTimeSn(accountPeriod);
		importRequest.setOptOperId(operId);
		importRequest.setTenantId(tenantId);
		importRequest.setOptDeptId("system");
		ResponseData<String> responseData = null;
		try {
			BaseResponse importBillDetailData = iBillDetailSV.importBillDetailData(importRequest);
			if (importBillDetailData != null) {
				ResponseHeader resultResponseHeader = importBillDetailData.getResponseHeader();
				if (resultResponseHeader.isSuccess()) {
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, resultResponseHeader.getResultMessage());
					ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "上传成功");
					responseData.setResponseHeader(responseHeader);
				} else {
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, resultResponseHeader.getResultMessage());
					ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
					responseData.setResponseHeader(responseHeader);
					LOGGER.error("上传文件失败："+resultResponseHeader.getResultMessage());
				}
			} else {
				responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "上传失败");
				ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
				responseData.setResponseHeader(responseHeader);
				LOGGER.error("上传文件失败：dubbo服务返回为空");
			}
		} catch (Exception e) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "上传失败");
			ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
			responseData.setResponseHeader(responseHeader);
			LOGGER.error("上传文件失败:",e);
		}
		return responseData;
	}

	/**
	 * 上传业务流水文件
	 * 
	 * @param request
	 * @param uploadAccountData
	 * @return
	 */
	private ResponseData<String> uploadFileData(HttpServletRequest request, UploadAccountData uploadAccountData) {
		String dataObj = uploadAccountData.getDataObj();// 流水类型
		String fileName = uploadAccountData.getFileName();// 文件名称
		String filePosition = uploadAccountData.getFilePosition();// 文件路径
		String accountPeriod = uploadAccountData.getAccountPeriod();// 账期
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String operId = Long.toString(userClient.getAccountId());// 操作员id
		String tenantId = userClient.getTenantId();
		IStreamFileInputSV iStreamFileInputSV = DubboConsumerFactory.getService("iStreamFileInputSV");
		StreamFileParam fileParam = new StreamFileParam();
		fileParam.setDataObj(dataObj);
		fileParam.setFileName(fileName);
		fileParam.setFilePosition(filePosition);
		fileParam.setAccountPeriod(accountPeriod);
		fileParam.setOperId(operId);
		fileParam.setTenantId(tenantId);
		ResponseData<String> responseData = null;
		try {
			BaseResponse fileInport = iStreamFileInputSV.fileInport(fileParam);
			if (fileInport != null) {
				ResponseHeader resultResponseHeader = fileInport.getResponseHeader();
				if (resultResponseHeader.isSuccess()) {
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, resultResponseHeader.getResultMessage());
					ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "上传成功");
					responseData.setResponseHeader(responseHeader);
				} else {
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, resultResponseHeader.getResultMessage());
					ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
					responseData.setResponseHeader(responseHeader);
					LOGGER.error("上传文件失败："+resultResponseHeader.getResultMessage());
				}
			} else {
				responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "上传失败");
				ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
				responseData.setResponseHeader(responseHeader);
				LOGGER.error("上传文件失败：dubbo服务返回为空");
			}
		} catch (Exception e) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "上传失败");
			ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.ERROR_CODE, "上传失败");
			responseData.setResponseHeader(responseHeader);
			LOGGER.error("上传文件失败：",e);
		}
		return responseData;
	}

	@RequestMapping("/download")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		ChannelSftp sftp = null;
		try {
			String rstFileName = request.getParameter("rstFileName");
			String rstFileUrl = request.getParameter("rstFileUrl");
			if(StringUtil.isBlank(rstFileName)||StringUtil.isBlank(rstFileUrl)){
				return;
			}
			ISmcSysParamCache iSmcSysParamCache = DubboConsumerFactory.getService("iSmcSysParamCache");
			String userName = null;
			String userPWD = null;
			String uploadUrl = null;
			String tenantId = userClient.getTenantId();
			GetSysParamListRequest paramListRequest1 = new GetSysParamListRequest();
			paramListRequest1.setTenantId(tenantId);
			paramListRequest1.setTypeCode("SFTP_CONF");
			paramListRequest1.setParamCode("USER_NAME");
			List<SmcSysParam> sysParamsName = iSmcSysParamCache.getSysParams(paramListRequest1);
			if (sysParamsName != null && sysParamsName.size() > 0) {
				userName = sysParamsName.get(0).getColumnValue();
			}
			GetSysParamListRequest paramListRequest2 = new GetSysParamListRequest();
			paramListRequest2.setTenantId(tenantId);
			paramListRequest2.setTypeCode("SFTP_CONF");
			paramListRequest2.setParamCode("PWD");
			List<SmcSysParam> sysParamsPWD = iSmcSysParamCache.getSysParams(paramListRequest2);
			if (sysParamsPWD != null && sysParamsPWD.size() > 0) {
				userPWD = sysParamsPWD.get(0).getColumnValue();
			}
			GetSysParamListRequest paramListRequest3 = new GetSysParamListRequest();
			paramListRequest3.setTenantId(tenantId);
			paramListRequest3.setTypeCode("SFTP_CONF");
			paramListRequest3.setParamCode("upload_url_diff_file");
			List<SmcSysParam> sysParamsURL = iSmcSysParamCache.getSysParams(paramListRequest3);
			if (sysParamsURL != null && sysParamsURL.size() > 0) {
				uploadUrl = sysParamsURL.get(0).getColumnValue();
			}
			if (userName == null || userPWD == null || uploadUrl == null) {
				return;
			}
			sftp = SftpUtil.connect(uploadUrl.split(":")[0], 22, userName, userPWD);
			int index = rstFileUrl.indexOf(':');
			if(index>0){
				rstFileUrl = rstFileUrl.substring(index+1);
			}
			SftpUtil.download(rstFileUrl, rstFileName, response, sftp);
		} catch (Exception e) {
			LOGGER.error("下载文件出错：", e);
			SftpUtil.disconnect(sftp);
		}

	}
}
