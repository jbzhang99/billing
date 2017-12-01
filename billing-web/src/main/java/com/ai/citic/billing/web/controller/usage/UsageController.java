package com.ai.citic.billing.web.controller.usage;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.*;
import com.ai.baas.bmc.api.detailsquery.interfaces.IDetailsQuerySV;
import com.ai.baas.bmc.api.detailsquery.params.DetailVo;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryResponse;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.model.usage.UsageQueryVo;
import com.ai.citic.billing.web.model.usage.UsageRecord;
import com.ai.citic.billing.web.util.CacheUtil;
import com.ai.citic.billing.web.util.FeesStringUtil;
import com.ai.citic.billing.web.util.excel.CellField;
import com.ai.citic.billing.web.util.excel.ExcelUtil;
import com.ai.citic.billing.web.util.excel.SheetField;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.util.UUIDUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UsageController.class);

    private static final String BILL_MONTH_FORMAT_STR = "%s年%s月";

    @Autowired
    private CommonCaller commonCaller;

    /**
     * 运营管理员使用记录导出界面
     * @return
     */
    @RequestMapping("/toUsageRecordExport")
    public ModelAndView toUsageRecordExport(){
        ModelAndView view = new ModelAndView("jsp/usage/usageRecord");
        return view;
    }

    /**
     * 租户管理员使用记录导出界面
     * @return
     */
    @RequestMapping("/toTenantUsageRecordExport")
    public ModelAndView toTenantUsageRecordExport(){
        ModelAndView view = new ModelAndView("jsp/usage/usageRecordForTenant");
        return view;
    }

    /**
     * 用户使用记录导出页面
     * @return
     */
    @RequestMapping("/toUserUsageRecordExport")
    public ModelAndView toUserUsageRecordExport(){
        ModelAndView view = new ModelAndView("jsp/usage/usageRecordForUser");
        return view;
    }

    /**
     * 获取供应商下拉
     * @param request
     * @return
     */
    @RequestMapping("/getSuppliers")
    public ResponseData<List<BaseCode>> getSuppliers(HttpServletRequest request){
        ResponseData<List<BaseCode>> responseData = null;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IBaseInfoSV baseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
            QueryInfoParams queryInfo = new QueryInfoParams();
            queryInfo.setTenantId(user.getTenantId());
            queryInfo.setParamType("SUPPLIER");
            queryInfo.setTradeSeq(UUIDUtil.genId32());
            BaseCodeInfo baseInfo = baseInfoSV.getBaseInfo(queryInfo);
            if(baseInfo!=null){
                if(!CollectionUtil.isEmpty(baseInfo.getParamList())){
                    responseData = new ResponseData<List<BaseCode>>(ResponseData.AJAX_STATUS_SUCCESS,"查询供应商成功",baseInfo.getParamList());
                }
            }
        } catch (BusinessException|SystemException e) {
            LOGGER.error("查询供应商下拉失败",e);
            responseData = new ResponseData<List<BaseCode>>(ResponseData.AJAX_STATUS_FAILURE,"供应商初始化失败");
        }
        return responseData;
    }

    /**
     * 获取供应商提供的服务
     * @param request
     * @param parentCode
     * @return
     */
    @RequestMapping("/getServices")
    public ResponseData<List<BaseCode>> getServices(HttpServletRequest request,String parentCode){
        ResponseData<List<BaseCode>> responseData = null;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IBaseInfoSV baseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
            QueryChildCodeRequest queryInfo = new QueryChildCodeRequest();
            queryInfo.setTenantId(user.getTenantId());
            queryInfo.setParentCode(parentCode);
            queryInfo.setTradeSeq(UUIDUtil.genId32());
            ChildeCodeResponse childeCodeResponse = baseInfoSV.getChildCode(queryInfo);
            if(childeCodeResponse!=null){
                if(!CollectionUtil.isEmpty(childeCodeResponse.getParamList())){
                    responseData = new ResponseData<List<BaseCode>>(ResponseData.AJAX_STATUS_SUCCESS,"查询供应商服务成功",childeCodeResponse.getParamList());
                }
            }
        } catch (BusinessException|SystemException e) {
            LOGGER.error("查询供应商服务失败",e);
            responseData = new ResponseData<List<BaseCode>>(ResponseData.AJAX_STATUS_FAILURE,"供应商服务初始化失败");
        }
        return responseData;
    }
    /**
     * 运营管理员导出租户账单
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping("/searchRecords")
    public ResponseData<String> searchRecords(HttpServletRequest request, UsageQueryVo vo){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IDetailsQuerySV detailsQuerySV = DubboConsumerFactory.getService(IDetailsQuerySV.class);
            DetailsQueryRequest queryVo = new DetailsQueryRequest();
            queryVo.setTenantId(user.getTenantId());
            queryVo.setServiceType(vo.getServiceType());
            if(!StringUtil.isBlank(vo.getBeginDate())){
                queryVo.setStartDate(vo.getBeginDate().replaceAll("-","")+"000000");
            }
            if(!StringUtil.isBlank(vo.getBeginDate())){
                queryVo.setEndDate(vo.getEndDate().replace("-","")+"235959");
            }
            HashSet<String> accountIds = new HashSet<>();
            if(!StringUtil.isBlank(vo.getUserId())){
                List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), vo.getUserId());
                if(!CollectionUtil.isEmpty(acctAndCustInfos)){
                    for (BlAcctInfoInfo blAcctInfoInfo:acctAndCustInfos) {
                        CacheUtil.fetchOrgInfo(user.getTenantId(),vo.getUserId());
                        CacheUtil.cacheExtCustIdForAcct(user.getTenantId()+":"+blAcctInfoInfo.getAcctId(),vo.getUserId());
                        accountIds.add(blAcctInfoInfo.getAcctId());
                    }
                }
            }else if(!StringUtil.isBlank(vo.getUserName())){
                //调用中信api根据租户名称查询租户id
                List<BlAcctInfoInfo> blAcctInfoInfos = commonCaller.getAcctAndCustListByName(user.getTenantId(), vo.getUserName());
                if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
                    for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
                        accountIds.add(blAcctInfoInfo.getAcctId());
                    }
                }
            }
            if(accountIds.size()>0){
                List<String> ids = new ArrayList<>();
                for (String id:accountIds) {
                    ids.add(id);
                }
                queryVo.setAccountIds(ids);
                DetailsQueryResponse details = detailsQuerySV.queryDetails(queryVo);
                if(!CollectionUtil.isEmpty(details.getDetails())){
                    request.getSession().setAttribute("drDetails",details.getDetails());
                    responseData =  new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"租户详单信息查询成功");
                }else{
                    responseData =  new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"未查询到租户详单信息");
                }
            }else {
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"账户信息有误，无法导出");
            }
        } catch (Exception e) {
            responseData =  new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"查询到租户详单信息失败");
            LOGGER.error("租户详单信息查询失败",e);
        }

        return responseData;
    }

    /**
     * 租户管理员导出用户账单
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping("/searchRecordsForTenant")
    public ResponseData<String> searchRecordsForTenant(HttpServletRequest request,HttpServletResponse response,UsageQueryVo vo){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            if(!StringUtil.isBlank(user.getAcctId())){
                IDetailsQuerySV detailsQuerySV = DubboConsumerFactory.getService(IDetailsQuerySV.class);
                DetailsQueryRequest queryVo = new DetailsQueryRequest();
                queryVo.setTenantId(user.getTenantId());
                queryVo.setServiceType(vo.getServiceType());
                if(!StringUtil.isBlank(vo.getBeginDate())){
                    queryVo.setStartDate(vo.getBeginDate().replaceAll("-","")+"000000");
                }
                if(!StringUtil.isBlank(vo.getBeginDate())){
                    queryVo.setEndDate(vo.getEndDate().replace("-","")+"235959");
                }
                List<String> acctIds = new ArrayList<>();
                acctIds.add(user.getAcctId());
                queryVo.setAccountIds(acctIds);
                List<String> userIds = new ArrayList<>();
                if(!StringUtil.isBlank(vo.getUserId())){
                    userIds.add(vo.getUserId());
                }else if(!StringUtil.isBlank(vo.getUserName())){
                    ICiticRestReqWrapperSV citicRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
                    UserInfoQueryVo query = new UserInfoQueryVo();
                    query.setTenantId(user.getTenantId());
                    query.setSelectType("4");
                    query.setSelectId(user.getTenant());
                    UserQueryResponse userQueryResponse = citicRestReqWrapperSV.searchUser(query);
                    if(userQueryResponse.getResponseHeader().isSuccess()&&!CollectionUtil.isEmpty(userQueryResponse.getUsers())){
                        for (UserInfo info:userQueryResponse.getUsers()){
                            if(info.getName().equals(vo.getUserName())){
                                userIds.add(info.getUserId());
                            }
                        }
                    }
                }
                DetailsQueryResponse details = null;
                if(userIds.size()>0){
                    CacheUtil.fetchUserInfo(user.getTenantId(),user.getTenant());
                    CacheUtil.fetchOrgInfo(user.getTenantId(),user.getOrgId());
                    OrgInfo orgInfo = CacheUtil.getOrgInfoFromCache(user.getOrgId());
                    if(orgInfo!=null){
                        CacheUtil.cacheExtCustIdForAcct(user.getTenantId()+":"+user.getAcctId(),orgInfo.getOrgId());
                    }
                    queryVo.setUserIds(userIds);
	                details = detailsQuerySV.queryDetails(queryVo);
	                if(!CollectionUtil.isEmpty(details.getDetails())){
	                    request.getSession().setAttribute("drDetails",details.getDetails());
	                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"用户详单信息查询成功");
	                }else{
	                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"未查询到详单信息，无法导出");
	                }
                }else{
                	responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"未查询到用户信息，无法导出");
                }
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"账户信息有误，无法导出");
            }
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"用户详单信息查询失败");
            LOGGER.error("用户详单信息查询失败",e);
        }
        return responseData;
    }

    /**
     * 用户导出自己账单
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping("/searchRecordsForUser")
    public ResponseData<String> searchRecordsForUser(HttpServletRequest request,HttpServletResponse response,UsageQueryVo vo){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            if(!StringUtil.isBlank(user.getAcctId())){
                IDetailsQuerySV detailsQuerySV = DubboConsumerFactory.getService(IDetailsQuerySV.class);
                DetailsQueryRequest queryVo = new DetailsQueryRequest();
                queryVo.setTenantId(user.getTenantId());
                queryVo.setServiceType(vo.getServiceType());
                if(!StringUtil.isBlank(vo.getBeginDate())){
                    queryVo.setStartDate(vo.getBeginDate().replaceAll("-","")+"000000");
                }
                if(!StringUtil.isBlank(vo.getBeginDate())){
                    queryVo.setEndDate(vo.getEndDate().replace("-","")+"235959");
                }
                List<String> acctIds = new ArrayList<>();
                acctIds.add(user.getAcctId());
                queryVo.setAccountIds(acctIds);
                List<String> userIds = new ArrayList<>();
                userIds.add(user.getUserId());
                queryVo.setUserIds(userIds);
                CacheUtil.fetchOrgInfo(user.getTenantId(),user.getOrgId());
                CacheUtil.fetchUserInfo(user.getTenantId(),user.getTenant());
                OrgInfo orgInfo = CacheUtil.getOrgInfoFromCache(user.getOrgId());
                if(orgInfo!=null){
                    CacheUtil.cacheExtCustIdForAcct(user.getTenantId()+":"+user.getAcctId(),orgInfo.getOrgId());
                }
                DetailsQueryResponse details = detailsQuerySV.queryDetails(queryVo);
                if(!CollectionUtil.isEmpty(details.getDetails())){
                    request.getSession().setAttribute("drDetails",details.getDetails());
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"用户详单信息查询成功");
                }else {
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"未查询到详单信息，无法导出");
                }
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"账户信息有误，无法导出");
            }
        } catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"用户详单信息查询失败");
            LOGGER.error("用户详单信息查询失败",e);
        }
        return responseData;
    }

    /**
     * 导出到excel
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportRecords")
    public void exportToExcel(HttpServletRequest request,HttpServletResponse response, String sheetName){
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            List<DetailVo> details = (List<DetailVo>) request.getSession().getAttribute("drDetails");
            if(!CollectionUtil.isEmpty(details)){
                List<SheetField> sheets = new ArrayList<>();
                //使用记录
                SheetField usageSheet = new SheetField();
                usageSheet.setSheetName(sheetName);
                usageSheet.setSheetIndex(0);
                usageSheet.setCells(this.getUsageRecordCells());
                usageSheet.setClazz(UsageRecord.class);

                IQueryIdInfoSV queryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
                ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
                List<Object> usageInfos = new ArrayList<>();
                for(int i = 0;i<details.size();i++){
                    DetailVo vo = details.get(i);
                    UsageRecord record = new UsageRecord();
                    record.setIndex(String.valueOf(i+1));
                    String orgId = CacheUtil.getOrgIdFromCache(user.getTenantId()+":"+vo.getAcctId());
                    if(StringUtil.isBlank(orgId)){
                        AcctIdInfo acctIdInfo = new AcctIdInfo();
                        acctIdInfo.setTenantId(user.getTenantId());
                        acctIdInfo.setAcctId(vo.getAcctId());
                        BlCustinfoResponse blCustinfoResponse = queryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
                        if(blCustinfoResponse!=null&&blCustinfoResponse.getResponseHeader().isSuccess()){
                            orgId = blCustinfoResponse.getBlCustinfoInfos().get(0).getExtCustId();
                        }
                    }
                    if(!StringUtil.isBlank(orgId)){
                        record.setTenant(orgId);
                        OrgInfo orgInfo = CacheUtil.getOrgInfoFromCache(orgId);
                        if(orgInfo == null){
                            OrgQueryVo orgQueryVo = new OrgQueryVo();
                            orgQueryVo.setTenantId(user.getTenantId());
                            orgQueryVo.setSelectType("3");
                            orgQueryVo.setSelectId(orgId);
                            OrgQueryResponse orgQueryResponse = iCiticRestReqWrapperSV.searchOrg(orgQueryVo);
                            if(orgQueryResponse!=null&&orgQueryResponse.getResponseHeader().isSuccess()){
                                if(!CollectionUtil.isEmpty(orgQueryResponse.getOrgs())){
                                    orgInfo = orgQueryResponse.getOrgs().get(0);
                                    CacheUtil.cacheOrgInfo(orgInfo);
                                    record.setTenantName(orgInfo.getName());
                                }else{
                                    LOGGER.error("机构信息【"+orgId+"】封装失败，略过！");
                                }
                            }
                        }else {
                            record.setTenantName(orgInfo.getName());
                        }
                    }
                    record.setPayFee(FeesStringUtil.liToyuan(vo.getFee(), 2));
                    if(StringUtils.isNotBlank(vo.getAccountPeriod())){
                        String billMonth = String.format(BILL_MONTH_FORMAT_STR, vo.getAccountPeriod().substring(0, 4), vo.getAccountPeriod().substring(4, 6));
                        record.setBillMonth(billMonth);
                    }
                    record.setSerialNo(vo.getInstanceId());
                    record.setUserId(vo.getServiceId());
                    UserInfo userInfo = CacheUtil.getUserInfoFromCache(vo.getServiceId());
                    if(userInfo==null){
                        UserInfoQueryVo userQueryVo = new UserInfoQueryVo();
                        userQueryVo.setTenantId(user.getTenantId());
                        userQueryVo.setSelectType("7");
                        userQueryVo.setSelectId(vo.getServiceId());
                        UserQueryResponse userQueryResponse = iCiticRestReqWrapperSV.searchUser(userQueryVo);
                        if(userQueryResponse!=null&&userQueryResponse.getResponseHeader().isSuccess()){
                            if(!CollectionUtil.isEmpty(userQueryResponse.getUsers())){
                                userInfo = userQueryResponse.getUsers().get(0);
                                CacheUtil.cacheUserInfo(userInfo);
                                record.setUserName(userInfo.getName());
                            }else{
                                LOGGER.error("用户信息【"+vo.getServiceId()+"】封装失败，略过！");
                            }
                        }
                    }else{
                        record.setUserName(userInfo.getName());
                    }
                    record.setRemark(vo.getServiceType()+"消费");
                    String hour = vo.getStartTime().substring(8,10);
                    String begin = vo.getStartTime().substring(0,4)+"-"+vo.getStartTime().substring(4,6)+"-"+vo.getStartTime().substring(6,8)+" "+hour+":"+vo.getStartTime().substring(10,12);
                    String end = "";
                    if(Integer.parseInt(hour)+1<10){
                        end = vo.getStartTime().substring(0,4)+"-"+vo.getStartTime().substring(4,6)+"-"+vo.getStartTime().substring(6,8)+" 0"+(Integer.parseInt(hour)+1)+":"+vo.getStartTime().substring(10,12);
                    }else{
                        end = vo.getStartTime().substring(0,4)+"-"+vo.getStartTime().substring(4,6)+"-"+vo.getStartTime().substring(6,8)+" "+(Integer.parseInt(hour)+1)+":"+vo.getStartTime().substring(10,12);
                    }
                    record.setBillPeriod(begin+"至"+end);
                    usageInfos.add(record);
                }
                usageSheet.setValues(usageInfos);
                sheets.add(usageSheet);

                ServletOutputStream os = response.getOutputStream();
                response.reset();// 清空输出流
                response.setContentType("application/msexcel");// 定义输出类型
                response.setHeader("Content-disposition", "attachment; filename="+sheetName
                        +".xlsx");// 设定输出文件头
                ExcelUtil.writeExcel(os, sheets);
            }
        } catch (Exception e) {
            LOGGER.error("使用量导出失败",e);
        }

    }

    private List<CellField> getUsageRecordCells() {
        List<CellField> list = new ArrayList<CellField>() {{
            add( new CellField("序号", "index", true));
            add( new CellField("流水号", "serialNo", true));
            add( new CellField("租户ID", "tenant", true));
            add( new CellField("租户名称", "tenantName", true));
            add( new CellField("用户ID", "userId", true));
            add( new CellField("用户名称", "userName", true));
            add( new CellField("账期", "billMonth", true));
            add( new CellField("消费时间", "billPeriod", true));
            add( new CellField("应付金额（元）", "payFee", true));
            add( new CellField("备注", "remark", true));
        }};
        return list;
    }

}
