package com.ai.citic.billing.web.controller.account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.amc.api.bill.interfaces.IBillSearchSV;
import com.ai.baas.amc.api.bill.params.BillTotalRequest;
import com.ai.baas.amc.api.bill.params.BillTotalResponse;
import com.ai.baas.amc.api.fundquery.interfaces.IFundQuerySV;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryRequest;
import com.ai.baas.amc.api.fundquery.param.FundBookQueryResponse;
import com.ai.baas.bmc.api.acctinfo.interfaces.IAcctInfoSV;
import com.ai.baas.bmc.api.acctinfo.params.AcctInfoParams;
import com.ai.baas.bmc.api.acctinfo.params.AcctQueryRequest;
import com.ai.baas.bmc.api.acctinfo.params.ResponseMessage;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.baas.bmc.api.queryidinfo.params.CustIdInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.account.AcctInfoVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.util.AdapterUtil;
import com.ai.citic.billing.web.util.AmountUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;

/**
 * 账户总览 Date: 2016年7月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    public static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CommonCaller commonCaller;

    /**
     * 租户管理员入口
     * 
     * @param request
     * @return
     * @author LiangMeng
     */
    @RequestMapping("/accountViewForTenantManage")
    public ModelAndView accountViewForTenantManage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("jsp/account/accountViewForTenantManage");
//        CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(
//                SSOClientConstants.USER_SESSION_KEY);
//        String acctId = user.getAcctId();
//        String tenantId = user.getTenantId();
//        view.addObject("data", this.queryBillInfo(acctId, tenantId));
        return view;
    }

    /**
     * 运营管理员入口
     * 
     * @return
     * @author LiangMeng
     */
    @RequestMapping("/accountViewForSystemManage")
    public ModelAndView accountViewForSystemManage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("jsp/account/accountViewForSystemManage");
        CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(
                SSOClientConstants.USER_SESSION_KEY);
        String tenantId = user.getTenantId();
        view.addObject("data", this.queryAliBillInfo(tenantId));
        return view;
    }

    @RequestMapping("/accountTenantListQuery")
    public ResponseData<Map<String, Object>> accountTenantListQuery(HttpServletRequest request) {
        ResponseData<Map<String, Object>> responseData = null;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(
                    SSOClientConstants.USER_SESSION_KEY);
            String acctId = user.getAcctId();
            String tenantId = user.getTenantId();
            Map<String, Object> map = new HashMap<>();
            map = this.queryBillInfo(acctId, tenantId);
            responseData = new ResponseData<Map<String, Object>>(ResponseData.AJAX_STATUS_SUCCESS,
                    "查询账户列表成功", map);
        } catch (Exception e) {
            responseData = new ResponseData<Map<String, Object>>(ResponseData.AJAX_STATUS_FAILURE,
                    "账户信息查询失败");
            LOG.error("账户信息查询失败", e);
        }
        return responseData;
    }
    
    /**
     * 账户列表查询
     * 
     * @return
     * @author LiangMeng
     */
    @RequestMapping("/accountListQuery")
    public ResponseData<PageInfo<AcctInfoVo>> accountListQuery(HttpServletRequest request) {
        ResponseData<PageInfo<AcctInfoVo>> responseData = null;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(
                    SSOClientConstants.USER_SESSION_KEY);
            String tenantId = user.getTenantId();

            String orgName = (String) request.getParameter("orgName");

            String acctId = (String) request.getParameter("acctId");
            
            AcctQueryRequest acctQueryRequest = new AcctQueryRequest();
            IAcctInfoSV iAcctInfoSV = DubboConsumerFactory.getService("IAcctInfoSV");
            acctQueryRequest.setTenantId(tenantId);

            List<String> acctIds = new ArrayList<String>();
            if (!StringUtil.isBlank(acctId)) {
                acctIds.add(acctId);
            }
            if (!StringUtil.isBlank(orgName)) {
                List<BlAcctInfoInfo> acctAndCustListByName = commonCaller.getAcctAndCustListByName(
                        user.getTenantId(), orgName);
                if (!CollectionUtil.isEmpty(acctAndCustListByName)) {
                    acctIds.addAll(getAcctIdList(acctAndCustListByName));
                }
            }
            if (!CollectionUtil.isEmpty(acctIds)) {
                acctQueryRequest.setAcctIDs(acctIds);
            }
            String strPageNo = (null == request.getParameter(CiticWebConstants.PAGENO)) ? "1"
                    : request.getParameter(CiticWebConstants.PAGENO);
            String strPageSize = (null == request.getParameter(CiticWebConstants.PAGESIZE)) ? "10"
                    : request.getParameter(CiticWebConstants.PAGESIZE);
            acctQueryRequest.setPageNo(Integer.valueOf(strPageNo));
            acctQueryRequest.setPageSize(Integer.valueOf(strPageSize));
            ResponseMessage responseMessage = iAcctInfoSV.getAcctInfo(acctQueryRequest);
            PageInfo<AcctInfoParams> acctInfoParams = responseMessage.getAcctInfoParamsList();
            /* 转换，新增余额、租户、预警等信息 */
            PageInfo<AcctInfoVo> acctInfoVo = null;
            if ("000000".equals(responseMessage.getResponseHeader().getResultCode())) {
                acctInfoVo = this.converData(acctInfoParams, tenantId);
            }
            LOG.info("账户信息查询:" + responseMessage);
            responseData = new ResponseData<PageInfo<AcctInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS,
                    "查询账户列表成功", acctInfoVo);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<AcctInfoVo>>(ResponseData.AJAX_STATUS_FAILURE,
                    "账户信息查询失败");
            LOG.error("账户信息查询失败", e);
        }
        return responseData;
    }

    private List<String> getAcctIdList(List<BlAcctInfoInfo> acctAndCustListByName) {
        List<String> acctList = new ArrayList<String>();
        for (int i = 0; i < acctAndCustListByName.size(); i++) {
            acctList.add(acctAndCustListByName.get(i).getAcctId());
        }
        return acctList;
    }

    /**
     * 转换，新增余额、租户、预警等信息
     * 
     * @param param
     * @param tenantId
     * @return
     * @author LiangMeng
     */
    private PageInfo<AcctInfoVo> converData(PageInfo<AcctInfoParams> param, String tenantId) {
        PageInfo<AcctInfoVo> result = new PageInfo<AcctInfoVo>();
        if (param == null) {
            return result;
        }
        result.setCount(param.getCount());
        result.setPageCount(param.getPageCount());
        result.setPageNo(param.getPageNo());
        result.setPageSize(param.getPageSize());
        List<AcctInfoVo> resultList = new ArrayList<AcctInfoVo>();
        String warning = this.queryWarningTenant(tenantId);
        for (int i=0; i<param.getResult().size(); i++){
        	AcctInfoParams paramSub = param.getResult().get(i);
            AcctInfoVo vo = new AcctInfoVo();
            BeanUtils.copyProperties(vo, paramSub);
            Map<String, Object> orgMap = this.queryOrgInfo(vo.getAcctID(), tenantId);
            vo.setBalance(this.queryBalance(vo.getAcctID(), tenantId));
            vo.setWarning(warning);
            vo.setOrgId((String) orgMap.get("orgId"));
            vo.setOrgName((String) orgMap.get("orgName"));
            vo.setIndex(String.valueOf((param.getPageNo()-1)*param.getPageSize()+1+i));
            resultList.add(vo);
        }
        result.setResult(resultList);
        return result;
    }

    /**
     * 翻译租户相关信息
     * 
     * @return
     * @author LiangMeng
     */
    private Map<String, Object> queryOrgInfo(String acctId, String tenantId) {

        Map<String, Object> result = new HashMap<String, Object>();
        IQueryIdInfoSV queryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
        AcctIdInfo acctIdInfo = new AcctIdInfo();
        acctIdInfo.setTenantId(tenantId);
        acctIdInfo.setAcctId(acctId);
        BlCustinfoResponse res = queryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
        List<BlCustinfoInfo> custInfos = res.getBlCustinfoInfos();
        String extCustId = "";
        if (CollectionUtil.isEmpty(custInfos)) {
            return result;
        } else {
            extCustId = custInfos.get(0).getExtCustId();
        }
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory
                .getService(ICiticRestReqWrapperSV.class);
        OrgQueryVo queryVo = new OrgQueryVo();
        queryVo.setSelectId(extCustId);
        queryVo.setSelectType("3");
        OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
        if (searchOrg != null&&!CollectionUtil.isEmpty(searchOrg.getOrgs())) {
            result.put("orgId", extCustId);
            result.put("orgName", searchOrg.getOrgs().get(0).getName());
        } else {
            result.put("orgId", "");
            result.put("orgName", "");
        }
        return result;
    }

    /**
     * 查询账户余额
     * 
     * @param acctId
     * @param tenantId
     * @return
     * @author LiangMeng
     */
    private String queryBalance(String acctId, String tenantId) {
        String balance = "0";
        IFundQuerySV iFundQuerySV = DubboConsumerFactory.getService(IFundQuerySV.class);
        FundBookQueryRequest param = new FundBookQueryRequest();
        param.setAccountId(acctId + "");
        param.setTenantId(tenantId);
        FundBookQueryResponse res = iFundQuerySV.queryFund(param);
        if (res != null && "000000".equals(res.getResponseHeader().getResultCode())) {
            balance = String.valueOf(res.getBalance());
        }
        return balance;
    }

    /**
     * 查询预警阀值
     * 
     * @param tenantId
     * @return
     * @author LiangMeng
     */
    private String queryWarningTenant(String tenantId) {
        String warning = "0";
        IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams warParam = new QueryInfoParams();
        warParam.setTenantId(tenantId);
        warParam.setParamType("WARNING_TENANT");
        warParam.setTradeSeq(System.currentTimeMillis() + "");
        BaseCodeInfo info = iBaseInfoSV.getBaseInfo(warParam);
        if (info != null && info.getParamList() != null && info.getParamList().size() > 0) {
            warning = info.getParamList().get(0).getDefaultValue();
        }
        return warning;
    }

    private String queryWarningSystem(String tenantId) {
        String warning = "0";
        IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams warParam = new QueryInfoParams();
        warParam.setTenantId(tenantId);
        warParam.setParamType("WARNING_SYSTEM");
        warParam.setTradeSeq(System.currentTimeMillis() + "");
        BaseCodeInfo info = iBaseInfoSV.getBaseInfo(warParam);
        if (info != null && info.getParamList() != null && info.getParamList().size() > 0) {
            warning = info.getParamList().get(0).getDefaultValue();
        }
        return warning;
    }

    /**
     * 获取阿里的值
     * 
     * @return
     * @author LiangMeng
     */
    private Map<String, Object> queryBillInfo(String acctId, String tenantId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            /* 1.获取现金余额 */
            String balance = this.queryBalance(acctId, tenantId);
            /* 2.获取预警阀值 */
            String warning = this.queryWarningTenant(tenantId);

            /* 3.获取可打发票金额 */
            String invoice = "0";
            IBillSearchSV iBillSerchSV = DubboConsumerFactory.getService(IBillSearchSV.class);
            BillTotalRequest totalParam = new BillTotalRequest();
            totalParam.setAcctId(acctId);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);// 月份减1
            Date resultDate = cal.getTime(); // 结果
            String preMonth = new SimpleDateFormat("yyyyMM").format(resultDate);
            totalParam.setBillMonth(preMonth);
            totalParam.setTenantId(tenantId);
            BillTotalResponse resTotal = iBillSerchSV.queryBillTotal(totalParam);
            if (resTotal != null && "000000".equals(resTotal.getResponseHeader().getResultCode())
                    && resTotal.getTotalAmount() != null) {
                invoice = String.valueOf(resTotal.getTotalAmount());
            }
            result.put("balance", balance);
            result.put("warning", warning);
            result.put("invoice", invoice);
        } catch (BusinessException e) {
            LOG.error("获取账户信息异常：" + e.getMessage(), e);
        } catch (SystemException e) {
            LOG.error("获取账户信息异常：" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取阿里的值
     * 
     * @return
     * @author LiangMeng
     */
    private Map<String, Object> queryAliBillInfo(String tenantId) {
        Map<String, Object> result = new HashMap<String, Object>();
        String balance = "0";
        String warning = "0";
        String invoice = "0";
        try {
            balance = AdapterUtil.queryAliBalance();//
            warning = this.queryWarningSystem(tenantId);
            invoice = AdapterUtil.queryAliTotalBill();
        } catch (Exception e) {
           LOG.error("获取账务总览信息异常"+e.getMessage(),e);
        }
        result.put("balance", balance);
        result.put("warning", warning);
        result.put("invoice", invoice);
        return result;
    }

}
