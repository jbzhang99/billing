package com.ai.citic.billing.web.controller.inoutfund;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.amc.api.queryinoutfundserial.interfaces.IqueryFundSerialSV;
import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialResponse;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.inoutfund.InOutFundShowVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/inOutFund")
public class InOutFundQueryController {

    public static final Logger LOGGER = LoggerFactory.getLogger(InOutFundQueryController.class);

    @Autowired
    private CommonCaller commonCaller;

    @RequestMapping("/queryInOutFund")
    public ModelAndView queryInOutFun() {
        ModelAndView view = new ModelAndView("jsp/inoutfund/inOutFund");
        return view;
    }
    @RequestMapping("/queryInOutFundForTenant")
    public ModelAndView queryInOutFunForT() {
        ModelAndView view = new ModelAndView("jsp/inoutfund/inOutFundForTenant");
        return view;
    }

    @RequestMapping("/queryInOutFundForTenantList")
    public ResponseData<PageInfo<InOutFundShowVo>> queryInOutFundForTanentList(
            HttpServletRequest request) {
        ResponseData<PageInfo<InOutFundShowVo>> responseData = null;
        try {
            List<String> strings = new ArrayList<String>();
            CiticSSOClientUser user = getUser(request);
            strings.add(user.getAcctId());
            String startTime = (String) request.getParameter("startTime");
            String endTime = (String) request.getParameter("endTime");
            String optType = (String) request.getParameter("optType");

            PageInfo<InOutFundShowVo> inOutFundShowVoPage = null;

            IqueryFundSerialSV iqueryFundSerialSV = DubboConsumerFactory
                    .getService(IqueryFundSerialSV.class);
            QueryFundSerialRequest queryFundSerialRequest = new QueryFundSerialRequest();
            IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
            queryFundSerialRequest.setAcctIds(strings);
            if(strings.size()>0){
            	inOutFundShowVoPage = assembleQueryFundSerialRequest(queryFundSerialRequest, request,
                        iQueryIdInfoSV, user.getAcctId(), startTime, endTime, optType,
                        iqueryFundSerialSV);
                responseData = new ResponseData<PageInfo<InOutFundShowVo>>(
                        ResponseData.AJAX_STATUS_SUCCESS, "查询成功", inOutFundShowVoPage);
            }else{
            	 responseData = new ResponseData<PageInfo<InOutFundShowVo>>(
                         ResponseData.AJAX_STATUS_SUCCESS, "获取账户id失败");
            }
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<InOutFundShowVo>>(
                    ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
            LOGGER.error("获取信息出错：", e);
        }
        return responseData;
    }

    @RequestMapping("/queryInOutFundList")
    public ResponseData<PageInfo<InOutFundShowVo>> queryInOutFundList(HttpServletRequest request) {
        CiticSSOClientUser user = getUser(request);
        ResponseData<PageInfo<InOutFundShowVo>> responseData = null;
        try {
            String acctId = (String) request.getParameter("acctId");
            String acctName = (String) request.getParameter("acctName");
            String startTime = (String) request.getParameter("startTime");
            String endTime = (String) request.getParameter("endTime");
            String optType = (String) request.getParameter("optType");
            PageInfo<InOutFundShowVo> inOutFundShowVoPage = null;
            IqueryFundSerialSV iqueryFundSerialSV = DubboConsumerFactory
                    .getService(IqueryFundSerialSV.class);
            QueryFundSerialRequest queryFundSerialRequest = new QueryFundSerialRequest();
            IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
            List<String> acctIds = new ArrayList<String>();
            boolean isQuery = true;
            if (!StringUtil.isBlank(acctId)) {
                List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), acctId);
                if(!CollectionUtil.isEmpty(acctAndCustInfos)) {
                    for(BlAcctInfoInfo blAcctInfoInfo:acctAndCustInfos){
                        acctIds.add(blAcctInfoInfo.getAcctId());
                    }
                }
                isQuery = false;
            } else if (!StringUtil.isBlank(acctName)) {
                // 如果填写了名称则根据名称查找到id调用rest接口查询然后校验查到的id是否和填写的一致
                List<BlAcctInfoInfo> acctAndCustListByName = commonCaller.getAcctAndCustListByName(
                        user.getTenantId(), acctName);
                acctIds.addAll(getAcctIdList(acctAndCustListByName));
                isQuery = false;
            }

            if(isQuery||acctIds.size()>0){
                inOutFundShowVoPage = assembleQueryFundSerialRequest(queryFundSerialRequest,
                        request, iQueryIdInfoSV, acctId, startTime, endTime, optType,
                        iqueryFundSerialSV);
            }
            responseData = new ResponseData<PageInfo<InOutFundShowVo>>(
                    ResponseData.AJAX_STATUS_SUCCESS, "查询成功", inOutFundShowVoPage);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<InOutFundShowVo>>(
                    ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
            LOGGER.error("获取信息出错：", e);
        }
        return responseData;

    }

    private CiticSSOClientUser getUser(HttpServletRequest request) {
        return (CiticSSOClientUser) request.getSession().getAttribute(
                SSOClientConstants.USER_SESSION_KEY);
    }

    private PageInfo<InOutFundShowVo> assembleQueryFundSerialRequest(
            QueryFundSerialRequest queryFundSerialRequest, HttpServletRequest request,
            IQueryIdInfoSV iQueryIdInfoSV, String acctId,String startTime,  String endTime,
            String optType, IqueryFundSerialSV iqueryFundSerialSV) {
        CiticSSOClientUser user = getUser(request);
        PageInfo<InOutFundShowVo> inOutFundShowVoPage = new PageInfo<InOutFundShowVo>();
        List<InOutFundShowVo> inOutFundShowVos = new ArrayList<InOutFundShowVo>();
        queryFundSerialRequest.setTenantId(user.getTenantId());
        if (!StringUtil.isBlank(startTime)) {
            startTime = startTime + " 00:00:00";
            queryFundSerialRequest.setBeginTime(startTime);
        }
        if (!StringUtil.isBlank(endTime)) {
            endTime = endTime + " 23:59:59";
            queryFundSerialRequest.setEndTime(endTime);
        }
        if (!StringUtil.isBlank(optType)) {
            queryFundSerialRequest.setOptType(optType);
        }
        int strPageNo = (null == request.getParameter(CiticWebConstants.PAGENO)) ? 1 : Integer
                .parseInt(request.getParameter(CiticWebConstants.PAGENO));
        int strPageSize = (null == request.getParameter(CiticWebConstants.PAGESIZE)) ? 10 : Integer
                .parseInt(request.getParameter(CiticWebConstants.PAGESIZE));
        PageInfo<FundSerialInfo> pageInfo2 = new PageInfo<FundSerialInfo>();
        pageInfo2.setPageNo(strPageNo);
        pageInfo2.setPageSize(strPageSize);
        queryFundSerialRequest.setPageInfo(pageInfo2);
        LOGGER.info("收支明细记录查询入參"+JSONArray.fromObject(queryFundSerialRequest).toString());
        QueryFundSerialResponse queryFundSerialResponse = iqueryFundSerialSV
                .queryFundSerialList(queryFundSerialRequest);
        PageInfo<FundSerialInfo> pageInfo = queryFundSerialResponse.getPageInfo();

        if (pageInfo != null && pageInfo.getResult() != null) {
            int pageNo = pageInfo.getPageNo();
            int pageSize = pageInfo.getPageSize();
            for (int i = 0; i < pageInfo.getResult().size(); i++) {
                InOutFundShowVo vo = new InOutFundShowVo();

                if (StringUtil.isBlank(acctId)) {
                    FundSerialInfo fundSerialInfo = AssembleFundSerialInfo(iQueryIdInfoSV, pageInfo
                            .getResult().get(i));
                    if (fundSerialInfo != null) {
                        vo.setIndex(String.valueOf((pageNo - 1) * pageSize + 1 + i));
                    }
                    vo.setFundSerialInfo(fundSerialInfo);
                } else {
                    vo.setIndex(String.valueOf((pageNo - 1) * pageSize + 1 + i));
                    vo.setFundSerialInfo(pageInfo.getResult().get(i));
                }
                inOutFundShowVos.add(vo);
            }
            inOutFundShowVoPage.setPageCount(pageInfo.getPageCount());
            inOutFundShowVoPage.setCount(pageInfo.getCount());
            inOutFundShowVoPage.setPageNo(pageInfo.getPageNo());
            inOutFundShowVoPage.setPageSize(pageInfo.getPageSize());
        } else {
            inOutFundShowVoPage.setPageCount(1);
            inOutFundShowVoPage.setCount(0);
            inOutFundShowVoPage.setPageNo(strPageNo);
            inOutFundShowVoPage.setPageSize(strPageSize);
        }
        inOutFundShowVoPage.setResult(inOutFundShowVos);

        return inOutFundShowVoPage;
    }

    private List<String> getAcctIdList(List<BlAcctInfoInfo> acctAndCustListByName) {
        List<String> resList = new ArrayList<String>();
        for (int i = 0; i < acctAndCustListByName.size(); i++) {
            resList.add(acctAndCustListByName.get(i).getAcctId());
        }
        return resList;
    }

    private FundSerialInfo AssembleFundSerialInfo(IQueryIdInfoSV iQueryIdInfoSV,
            FundSerialInfo fundSerialInfo) {
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory
                .getService(ICiticRestReqWrapperSV.class);
        AcctIdInfo acctIdInfo = new AcctIdInfo();
        acctIdInfo.setTenantId(fundSerialInfo.getTenantId());
        acctIdInfo.setAcctId(fundSerialInfo.getAcctId1());
        BlCustinfoResponse blCustinfoResponse = iQueryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
        if (!CollectionUtil.isEmpty(blCustinfoResponse.getBlCustinfoInfos())) {
            fundSerialInfo.setExtCustId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                    .getExtCustId());
            OrgQueryVo queryVo = new OrgQueryVo();
            queryVo.setSelectId(fundSerialInfo.getExtCustId());
            queryVo.setSelectType("3");

            OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
            if (searchOrg != null) {
                fundSerialInfo.setTenantName(searchOrg.getOrgs().get(0).getName());
                fundSerialInfo.setBandSerialCode(searchOrg.getOrgs().get(0).getBankAccount());
            }
            // 根据外部客户id翻译银行流水和（调用rest接口）
        }
        return fundSerialInfo;
    }

}
