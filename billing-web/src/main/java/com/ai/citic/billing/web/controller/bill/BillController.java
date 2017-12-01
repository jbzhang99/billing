package com.ai.citic.billing.web.controller.bill;

import com.ai.baas.amc.api.bill.interfaces.IBillSearchSV;
import com.ai.baas.amc.api.bill.params.BillChargeVo;
import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.api.bill.params.BillSearchResponse;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.model.bill.BillChargeShowVo;
import com.ai.citic.billing.web.model.invoice.InvoiceRecordShowVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private CommonCaller commonCaller;

    @RequestMapping("/toBillSearch")
    public ModelAndView toBillSearch(){
        ModelAndView view = new ModelAndView("jsp/bill/billSearch");

        return view;
    }
    
    @RequestMapping("/toTenantBillSearch")
    public ModelAndView toTenantBillSearch(){
        ModelAndView view = new ModelAndView("jsp/bill/tenantbillSearch");

        return view;
    }

    @RequestMapping("/billSearch")
    public ResponseData<PageInfo<BillChargeShowVo>> billSearch(HttpServletRequest request,BillSearchRequest searchRequest, String custId){
        ResponseData<PageInfo<BillChargeShowVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IBillSearchSV iBillSearchSV = DubboConsumerFactory.getService(IBillSearchSV.class);
            searchRequest.setTenantId(user.getTenantId());
            searchRequest.setUserType("1");
            if(searchRequest.getBillFeeMin()!=null){
                searchRequest.setBillFeeMin(new BigDecimal(searchRequest.getBillFeeMin().longValue()*1000));
            }
            if(searchRequest.getBillFeeMax()!=null){
                searchRequest.setBillFeeMax(new BigDecimal(searchRequest.getBillFeeMax().longValue()*1000));
            }
            HashSet<String> accountIds = new HashSet<>();
            boolean isQuery = true;
            if(!StringUtil.isBlank(custId)){
                List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), custId);
                if(!CollectionUtil.isEmpty(acctAndCustInfos)){
                    for (BlAcctInfoInfo blAcctInfoInfo:acctAndCustInfos) {
                        accountIds.add(blAcctInfoInfo.getAcctId());
                    }
                }
                isQuery = false;
            }else if(!StringUtil.isBlank(searchRequest.getCustName())){
                //调用中信api根据租户名称查询租户id
                List<BlAcctInfoInfo> blAcctInfoInfos = commonCaller.getAcctAndCustListByName(user.getTenantId(), searchRequest.getCustName());
                if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
                    for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
                    	accountIds.add(blAcctInfoInfo.getAcctId());
                    }
                }
                isQuery = false;
            }
            if(!CollectionUtil.isEmpty(accountIds)){
                List<String> ids = new ArrayList<>();
                for (String id:accountIds) {
                    ids.add(id);
                }
                searchRequest.setAcctIdList(ids); 
            }
            if(isQuery || accountIds.size()>0){
            	LOGGER.info("账单查询入参:"+JSONArray.fromObject(searchRequest).toString());
            	BillSearchResponse billSearchResponse = iBillSearchSV.queryBillList(searchRequest);
                if(billSearchResponse.getResponseHeader().isSuccess()){
                	PageInfo<BillChargeVo> result = billSearchResponse.getBillPageVo();
                	PageInfo<BillChargeShowVo> page = new PageInfo<BillChargeShowVo>();
                	List<BillChargeShowVo> list = new ArrayList<BillChargeShowVo>();
                	if(result!=null && result.getResult()!=null){
            			for(int i=0; i<result.getResult().size();i++){
                    		BillChargeShowVo billVo = new BillChargeShowVo();
                    		billVo.setBillChargeVo(result.getResult().get(i));
                    		billVo.setIndex(String.valueOf((searchRequest.getPageNo()-1)*searchRequest.getPageSize()+1+i));
                    		list.add(billVo);
                    	}
            			page.setPageCount(result.getPageCount());
            			page.setCount(result.getCount());
            			page.setPageNo(result.getPageNo());
            			page.setPageSize(result.getPageSize());
                	}else{
                		page.setPageCount(1);
                		page.setCount(0);
                		page.setPageNo(searchRequest.getPageNo());
                		page.setPageSize(searchRequest.getPageSize()); 
                	}
                	page.setResult(list);
                    responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_SUCCESS,"查询成功",page);
                }else{
                    responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_FAILURE,"账单查询失败");
                }
            }else{
            	responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_SUCCESS,"查询成功");
            }
            
        } catch (Exception e) { 
            responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_FAILURE,"账单查询失败");
            LOGGER.error("查询账单失败",e);
        }

        return responseData;
    }
    
    @RequestMapping("/tenantBillSearch")
    public ResponseData<PageInfo<BillChargeShowVo>> tenantBillSearch(HttpServletRequest request,BillSearchRequest searchRequest){
        ResponseData<PageInfo<BillChargeShowVo>> responseData;
        try {
            CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            IBillSearchSV iBillSearchSV = DubboConsumerFactory.getService(IBillSearchSV.class);
            searchRequest.setTenantId(user.getTenantId());
            searchRequest.setUserType("0");
            boolean isQuery = true;
            if(!StringUtil.isBlank(user.getTenant())){
                List<String> ids = new ArrayList<>();
                List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), user.getTenant());
                if(!CollectionUtil.isEmpty(acctAndCustInfos)){
                    for (BlAcctInfoInfo blAcctInfoInfo:acctAndCustInfos) {
                        ids.add(blAcctInfoInfo.getAcctId());
                    }
                }
                searchRequest.setAcctIdList(ids);
            }
            if(searchRequest.getAcctIdList()==null || searchRequest.getAcctIdList().size()==0){
            	isQuery = false;
            }
            if(isQuery){
            	 LOGGER.info("租户账单查询入参:"+JSONArray.fromObject(searchRequest).toString());
                 BillSearchResponse billSearchResponse = iBillSearchSV.queryBillList(searchRequest);
                 LOGGER.info("租户账单查询出参:"+JSONArray.fromObject(billSearchResponse).toString());
                 if(billSearchResponse.getResponseHeader().isSuccess()){
                	PageInfo<BillChargeVo> result = billSearchResponse.getBillPageVo();
                 	PageInfo<BillChargeShowVo> page = new PageInfo<BillChargeShowVo>();
                 	List<BillChargeShowVo> list = new ArrayList<BillChargeShowVo>();
                 	if(result!=null && result.getResult()!=null){
             			for(int i=0; i<result.getResult().size();i++){
                     		BillChargeShowVo billVo = new BillChargeShowVo();
                     		billVo.setBillChargeVo(result.getResult().get(i));
                     		billVo.setIndex(String.valueOf((searchRequest.getPageNo()-1)*searchRequest.getPageSize()+1+i));
                     		list.add(billVo);
                     	}
             			page.setPageCount(result.getPageCount());
             			page.setCount(result.getCount());
             			page.setPageNo(result.getPageNo());
             			page.setPageSize(result.getPageSize());
                 	}else{
                 		page.setPageCount(1);
                 		page.setCount(0);
                 		page.setPageNo(searchRequest.getPageNo());
                 		page.setPageSize(searchRequest.getPageSize()); 
                 	}
                 	page.setResult(list);
                     responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_SUCCESS,"查询成功",page);
                 }else{
                     responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_SUCCESS,"账单查询失败");
                 }
            }else{
            	PageInfo<BillChargeVo> info = new PageInfo<BillChargeVo>();
            	info.setPageCount(1);
    			info.setCount(0);
    			info.setPageNo(searchRequest.getPageNo());
    			info.setPageSize(searchRequest.getPageSize()); 
            	responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_SUCCESS,"客户id不能为空");
            }
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<BillChargeShowVo>>(ResponseData.AJAX_STATUS_FAILURE,"账单查询异常");
            LOGGER.error("查询账单失败",e);
        }

        return responseData;
    }
}
