package com.ai.citic.billing.web.controller.chargeAdjust;

import com.ai.baas.amc.api.chargeadjust.interfaces.IChargeAdjustSV;
import com.ai.baas.amc.api.chargeadjust.interfaces.ILedgerSearchSV;
import com.ai.baas.amc.api.chargeadjust.params.*;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chargeAdjust")
public class ChargeAdjustController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ChargeAdjustController.class);

    @Autowired
    private CommonCaller commonCaller;

    @RequestMapping("/toSearchLedger")
    public ModelAndView toBillSearch(){
        ModelAndView view = new ModelAndView("jsp/chargeAdjust/ledgerSearch");

        return view;
    }

    @RequestMapping("/toAdjustCharge")
    public ModelAndView toBillSearch(String acctId,String billMonth){
        ModelAndView view = new ModelAndView("jsp/chargeAdjust/chargeAdjust");
        view.addObject("acctId",acctId);
        view.addObject("billMonth",billMonth);
        return view;
    }

    @RequestMapping("/ledgerSearch")
    public ResponseData<PageInfoResponse<LedgerVo>> ledgerSearch(HttpServletRequest request,LedgerRequest req,String tenantName){
        ResponseData<PageInfoResponse<LedgerVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            List<String> accountIds = null;
            if(!StringUtil.isBlank(tenantName)){
                //调用中信api根据租户名称查询租户id
                List<BlAcctInfoInfo> blAcctInfoInfos = commonCaller.getAcctAndCustListByName(user.getTenantId(), tenantName);
                if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
                    accountIds = new ArrayList<>();
                    for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
                        accountIds.add(blAcctInfoInfo.getAcctId());
                    }
                }
                req.setAcctIds(accountIds);
            }
            if(!StringUtil.isBlank(tenantName)&&CollectionUtil.isEmpty(accountIds)){
                responseData = new ResponseData<PageInfoResponse<LedgerVo>>(ResponseData.AJAX_STATUS_SUCCESS,"调账查询成功");
            }else {
                ILedgerSearchSV ledgerSearchSV = DubboConsumerFactory.getService(ILedgerSearchSV.class);
                req.setTenantId(user.getTenantId());
                PageInfoResponse<LedgerVo> response = ledgerSearchSV.searchLedger(req);
                responseData = new ResponseData<PageInfoResponse<LedgerVo>>(ResponseData.AJAX_STATUS_SUCCESS,"调账查询成功",response);
            }
        } catch (Exception e) {
            LOGGER.error("调账查询失败",e);
            responseData = new ResponseData<PageInfoResponse<LedgerVo>>(ResponseData.AJAX_STATUS_FAILURE,"调账查询失败");
        }
        return responseData;
    }

    @RequestMapping("/queryChargeList")
    public ResponseData<List<ChargeVo>> queryChargeList(HttpServletRequest request, SearchRequest req){
        ResponseData<List<ChargeVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            ILedgerSearchSV ledgerSearchSV = DubboConsumerFactory.getService(ILedgerSearchSV.class);
            req.setTenantId(user.getTenantId());
            DateFormat fmt = new SimpleDateFormat("yyyyMM");
            DateFormat fmt1 = new SimpleDateFormat("yyyy年MM月");
            Date date = fmt1.parse(req.getBillMonth());
            req.setBillMonth(fmt.format(date));
            ChargeListResponse chargeListResponse = ledgerSearchSV.searchChargeList(req);
            if(chargeListResponse!=null&&!CollectionUtil.isEmpty(chargeListResponse.getChargeVos())){
                responseData = new ResponseData<List<ChargeVo>>(ResponseData.AJAX_STATUS_SUCCESS,"费用类型查询成功",chargeListResponse.getChargeVos());
            }else{
                responseData = new ResponseData<List<ChargeVo>>(ResponseData.AJAX_STATUS_FAILURE,"未查询到符合条件的费用类型");
            }
        } catch (Exception e) {
            LOGGER.error("费用类型查询失败",e);
            responseData = new ResponseData<List<ChargeVo>>(ResponseData.AJAX_STATUS_FAILURE,"费用类型查询失败");
        }
        return responseData;
    }

    @RequestMapping("/adjustCharge")
    public ResponseData<String> adjustCharge(HttpServletRequest request, AdjustChargeRequest req,String adjustFlag,BigDecimal amount){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IChargeAdjustSV iChargeAdjustSV = DubboConsumerFactory.getService(IChargeAdjustSV.class);
            req.setTenantId(user.getTenantId());
            req.setOperator(user.getUserId());
            DateFormat fmt = new SimpleDateFormat("yyyyMM");
            DateFormat fmt1 = new SimpleDateFormat("yyyy年MM月");
            Date date = fmt1.parse(req.getBillMonth());
            req.setBillMonth(fmt.format(date));
            if("1".equals(adjustFlag)){
                req.setAdjustAmount(-amount.multiply(new BigDecimal(1000)).longValue());
            }else{
                req.setAdjustAmount(amount.multiply(new BigDecimal(1000)).longValue());
            }
            BaseResponse response = iChargeAdjustSV.chargeAdjust(req);
            if(response!=null&&response.getResponseHeader().isSuccess()){
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"调账成功");
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"调账失败");
            }
        } catch (Exception e) {
            LOGGER.error("调账失败",e);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"调账失败");
        }

        return responseData;
    }

    @RequestMapping("/judgeAmount")
    public ResponseData<String> judgeAmount(HttpServletRequest request, ChargeSearchRequest req,BigDecimal amount,String adjustFlag){
        ResponseData<String> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            ILedgerSearchSV ledgerSearchSV = DubboConsumerFactory.getService(ILedgerSearchSV.class);
            req.setTenantId(user.getTenantId());
            DateFormat fmt = new SimpleDateFormat("yyyyMM");
            DateFormat fmt1 = new SimpleDateFormat("yyyy年MM月");
            Date date = fmt1.parse(req.getBillMonth());
            req.setBillMonth(fmt.format(date));
            ChargeSearchResponse response = ledgerSearchSV.searchCharge(req);
            if(response!=null&&response.getResponseHeader().isSuccess()){
                ChargeVo chargeVo = response.getChargeVo();
                if(chargeVo!=null){
                    if("1".equals(adjustFlag)&&amount!=null&&amount.multiply(new BigDecimal(1000)).longValue()>chargeVo.getBalance()){
                        responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"调减金额超过可调整最大值["+new BigDecimal(chargeVo.getBalance()).divide(new BigDecimal(1000))+"]","1");
                    }else{
                        responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"调账金额校验通过","0");
                    }
                }else{
                    responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"调账金额校验查询失败");
                }
            }else{
                responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"调账金额校验失败");
            }
        } catch (Exception e) {
            LOGGER.error("调账金额校验失败",e);
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"调账金额校验失败");
        }

        return responseData;
    }
}
