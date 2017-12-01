package com.ai.citic.billing.web.controller.deposit;

import com.ai.baas.amc.api.deposit.interfaces.IDepositSV;
import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.baas.amc.api.deposit.param.DepositResponse;
import com.ai.baas.amc.api.deposit.param.TransSummary;
import com.ai.baas.amc.api.queryinoutfundserial.interfaces.IqueryFundSerialSV;
import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialResponse;
import com.ai.baas.bmc.api.acctinfo.interfaces.IAcctInfoSV;
import com.ai.baas.bmc.api.acctinfo.params.AcctInfoParams;
import com.ai.baas.bmc.api.acctinfo.params.AcctQueryRequest;
import com.ai.baas.bmc.api.acctinfo.params.ResponseMessage;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.baas.bmc.api.queryidinfo.params.CustIdInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.account.AcctInfoVo;
import com.ai.citic.billing.web.model.deposit.FundSerialInfoVo;
import com.ai.citic.billing.web.model.deposit.FundSerialShowInfo;
import com.ai.citic.billing.web.model.invoice.InvoiceRecordShowVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgInfo;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;
import com.esotericsoftware.minlog.Log;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {
	public static final Logger logger = LoggerFactory.getLogger(DepositController.class);
	private static final String SYSTEM_ID="citic-billing-web";
	private static final String OPTTYPE="1";
	private static final String SUBJECT_ID="SUBJECT_ID";
    @Autowired
    private CommonCaller commonCaller;
	
	@RequestMapping("/toDepositManage")
	public ModelAndView toDepositManage(){
		ModelAndView view = new ModelAndView("jsp/deposit/depositmanage");
		return view;
	}
	/**
	 * 充值
	 * @param request
	 * @return
	 * @author luoxuan
	 */
	@RequestMapping("/toDeposit")
	@ResponseBody
	public Map<String,Object> toDeposit(HttpServletRequest request) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		CiticSSOClientUser user = getUser(request);
		
		UUID uuid = UUID.randomUUID();//随机生成序列号
		String accountId=request.getParameter("accountId");
		String amount=request.getParameter("amount");
		String depositTime=request.getParameter("depositTime");
		
		String subjectId = "100001";
		
		IDepositSV iDepositSV=DubboConsumerFactory.getService(IDepositSV.class);
		DepositRequest repositRequest=new DepositRequest();
		repositRequest.setTenantId(user.getTenantId());
		repositRequest.setSystemId(SYSTEM_ID);
		repositRequest.setCustId(user.getOwnerId());
		repositRequest.setBusiSerialNo(uuid.toString());
		repositRequest.setDepositTime(depositTime);

		repositRequest.setAccountId(accountId);
		List<TransSummary> transSummaryList = new ArrayList<TransSummary>();
        TransSummary summary = new TransSummary();
        summary.setSubjectId(Long.valueOf(subjectId));
        summary.setAmount((long) (Double.parseDouble(amount)*1000));
        transSummaryList.add(summary);
        repositRequest.setTransSummary(transSummaryList);
		repositRequest.setBusiDesc("充值"+amount+"元");
		DepositResponse depositresponse=iDepositSV.depositFund(repositRequest);
		
		map.put("payserialcode", depositresponse.getResponseHeader().getResultCode());
		return map;
	}
	/**
	 * 显示用户
	 * @param request
	 * @return
	 * @author luoxuan
	 */
	@RequestMapping(value="/toSearch",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseData<PageInfo<AcctInfoVo>> toSearch(HttpServletRequest request) throws Exception{
		ResponseData<PageInfo<AcctInfoVo>> responseData;
		try {
			CiticSSOClientUser user = getUser(request);
			
			String bankAccount=request.getParameter("bankAccount");
			String orgName = (String) request.getParameter("orgName");
			
			IAcctInfoSV iAcctInfoSV=DubboConsumerFactory.getService("IAcctInfoSV");
			AcctQueryRequest acctQueryRequest=new AcctQueryRequest();
			List<String> accdIds = new ArrayList<String>();
			
			acctQueryRequest.setTenantId(user.getTenantId());
			
			boolean isQuery = true;
			 if(!StringUtil.isBlank(bankAccount)){
				 List<BlAcctInfoInfo> blAcctInfoInfos = getBlAcctInfoInfo(user.getTenantId(), bankAccount);
				 if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
					 for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
						 accdIds.add(blAcctInfoInfo.getAcctId());
					 }
				 }
				 isQuery = false;
			 }else if (!StringUtil.isBlank(orgName)){
                 List<BlAcctInfoInfo> acctAndCustListByName = commonCaller.getAcctAndCustListByName(
                        user.getTenantId(), orgName);
                 if (!CollectionUtil.isEmpty(acctAndCustListByName)){
                	 accdIds.addAll(getAcctIdList(acctAndCustListByName));
                 }
                 isQuery = false;
			 }
			 if(!CollectionUtil.isEmpty(accdIds)){
				 acctQueryRequest.setAcctIDs(accdIds);
	            }
	    	String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
		    String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
		    acctQueryRequest.setPageNo(Integer.valueOf(strPageNo));
		    acctQueryRequest.setPageSize(Integer.valueOf(strPageSize));
		    if(isQuery || accdIds.size()>0){
		    	logger.info("充值记录查询入參"+JSONArray.fromObject(acctQueryRequest).toString());
		    	ResponseMessage responseMessage=iAcctInfoSV.getAcctInfo(acctQueryRequest);
				PageInfo<AcctInfoParams> acctInfoParams = responseMessage.getAcctInfoParamsList();
				/* 转换，新增租户、银行账号等信息 */
				PageInfo<AcctInfoVo> acctInfoVo = this.converData(acctInfoParams,user.getTenantId());
				responseData = new ResponseData<PageInfo<AcctInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询账户列表成功",acctInfoVo);
		    }else{
		    	responseData = new ResponseData<PageInfo<AcctInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询账户列表成功");
		    }
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<AcctInfoVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询账户列表失败");
			Log.error("查询账户列表失败",e);
		}
		return responseData;
	}
	
	private CiticSSOClientUser getUser(HttpServletRequest request) {
		return (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
	}
	/**
	 * 充值明细查询
	 * @param request
	 * @return
	 * @author luoxuan
	 */
	@RequestMapping("/toSearchRecord")
	public ResponseData toSearchRecord(HttpServletRequest request) throws Exception{
		ResponseData<PageInfo<FundSerialShowInfo>> responseData ;
		CiticSSOClientUser user = getUser(request);
		try {
            String beginTime = (String) request.getParameter("beginTime");
            String endTime = (String) request.getParameter("endTime");
			String bankAccount=request.getParameter("bankAccount");
			String orgName = (String) request.getParameter("orgName");
			String strPageNo = (null == request.getParameter(CiticWebConstants.PAGENO)) ? "1"
                    : request.getParameter(CiticWebConstants.PAGENO);
            String strPageSize = (null == request.getParameter(CiticWebConstants.PAGESIZE)) ? "10"
                    : request.getParameter(CiticWebConstants.PAGESIZE);
			
			IqueryFundSerialSV iqueryFundSerialSV=DubboConsumerFactory.getService(IqueryFundSerialSV.class);
			QueryFundSerialRequest queryFundSerialRequest=new QueryFundSerialRequest();
			PageInfo<FundSerialInfo> pageInfo = new PageInfo<FundSerialInfo>();
			pageInfo.setPageNo(Integer.valueOf(strPageNo));
			pageInfo.setPageSize(Integer.valueOf(strPageSize));
			queryFundSerialRequest.setPageInfo(pageInfo);
			List<String> acctIds = new ArrayList<String>();
			boolean isQuery = true;
            if(!StringUtil.isBlank(bankAccount)){
				List<BlAcctInfoInfo> blAcctInfoInfos = getBlAcctInfoInfo(user.getTenantId(), bankAccount);
				if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
					for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
						acctIds.add(blAcctInfoInfo.getAcctId());
					}
				}
				isQuery = false;
            }else if(!StringUtil.isBlank(orgName)) {
                // 根据输入租户名称查询系统内所有账户客户信息
                List<BlAcctInfoInfo> acctAndCustListByName = commonCaller.getAcctAndCustListByName(
                        user.getTenantId(), orgName);
                if (!CollectionUtil.isEmpty(acctAndCustListByName)) {
                	acctIds.addAll(getAcctIdList(acctAndCustListByName));
                }
                isQuery = false;
            }
            if(!CollectionUtil.isEmpty(acctIds)){
            	queryFundSerialRequest.setAcctIds(acctIds);
            }
			queryFundSerialRequest.setOptType(OPTTYPE);
			if (!StringUtil.isBlank(beginTime)) {
				beginTime = beginTime + " 00:00:00";
                queryFundSerialRequest.setBeginTime(beginTime);
            }
            if (!StringUtil.isBlank(endTime)) {
                endTime = endTime + " 23:59:59";
                queryFundSerialRequest.setEndTime(endTime);
            }
            queryFundSerialRequest.setTenantId(user.getTenantId());

			if(isQuery||acctIds.size()>0){
				logger.info("充值明细查询入參"+JSONArray.fromObject(queryFundSerialRequest).toString());
				QueryFundSerialResponse queryFundSerialResponse=iqueryFundSerialSV.queryFundSerialList(queryFundSerialRequest);
				
				PageInfo<FundSerialInfo> fundSerialInfo=queryFundSerialResponse.getPageInfo();
				PageInfo<FundSerialShowInfo> page = new PageInfo<FundSerialShowInfo>();
				List<FundSerialShowInfo> list = new ArrayList<>();
				if(fundSerialInfo!=null && fundSerialInfo.getResult()!=null){
					for(int i=0; i<fundSerialInfo.getResult().size(); i++){
						FundSerialShowInfo vo = new FundSerialShowInfo();
	    				vo.setIndex(String.valueOf((Integer.valueOf(strPageNo)-1)*Integer.valueOf(strPageSize)+1+i));
	    				vo.setRecord(fundSerialInfo.getResult().get(i));
	    				list.add(vo);
					}
					page.setPageCount(fundSerialInfo.getPageCount());
					page.setCount(fundSerialInfo.getCount());
					page.setPageNo(fundSerialInfo.getPageNo());
					page.setPageSize(fundSerialInfo.getPageSize()); 
				}else{
					page.setPageCount(1);
					page.setCount(0);
					page.setPageNo(Integer.parseInt(strPageNo));
					page.setPageSize(Integer.parseInt(strPageSize)); 
				}
				page.setResult(list);
				responseData = new ResponseData<PageInfo<FundSerialShowInfo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功",page);
			}else{
				responseData = new ResponseData<PageInfo<FundSerialShowInfo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功");
			}
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<FundSerialShowInfo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
			Log.error("查询失败",e);
		}
		return responseData;
		
	}
	
    /**
     * 从账户客户信息中取出客户ID
     * @param acctAndCustListByName
     * @return
     */
    private List<String> getAcctIdList(List<BlAcctInfoInfo> acctAndCustListByName) {
        List<String> acctList = new ArrayList<String>();
        for (int i = 0; i < acctAndCustListByName.size(); i++) {
			acctList.add(acctAndCustListByName.get(i).getAcctId());
        }
        return acctList;
    }
    /**
     * 按银行账号查询
     * 
     * @return
     * @author luoxuan
     */
    private List<BlAcctInfoInfo> getBlAcctInfoInfo(String tenantId, String bankID) {
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
        OrgQueryVo orgQueryVo = new OrgQueryVo();
        orgQueryVo.setTenantId(tenantId);
        orgQueryVo.setSelectType("6");
        orgQueryVo.setSelectId(bankID);
        OrgQueryResponse queryResponse = iCiticRestReqWrapperSV.searchOrg(orgQueryVo);
        if(queryResponse.getResponseHeader().isSuccess()){
            if(!CollectionUtil.isEmpty(queryResponse.getOrgs())){
                OrgInfo orgInfo = queryResponse.getOrgs().get(0);
                return commonCaller.getAcctAndCustInfo(tenantId, orgInfo.getOrgId());
            }
        }
        return null;
    }
    
    /**
     * (充值管理)转换，新增租户,银行卡号等信息
     * 
     * @param param
     * @param tenantId
     * @return
     * @author luoxuan
     */
    private PageInfo<AcctInfoVo> converData(PageInfo<AcctInfoParams> param,String tenantId){
    	PageInfo<AcctInfoVo> result = new PageInfo<AcctInfoVo>();
    	 if (param == null) {
             return result;
         }
        result.setCount(param.getCount());
        result.setPageCount(param.getPageCount());
        result.setPageNo(param.getPageNo());
        result.setPageSize(param.getPageSize());
        List<AcctInfoVo> resultList = new ArrayList<AcctInfoVo>();
        for (int i=0; i<param.getResult().size(); i++){
        	 AcctInfoParams paramSub = param.getResult().get(i);
        	 AcctInfoVo vo = new AcctInfoVo();
             BeanUtils.copyProperties(vo, paramSub);
             Map<String, Object> orgMap = this.queryOrgInfo(vo.getCustID(), tenantId);
             vo.setOrgId((String) orgMap.get("orgId"));
             vo.setOrgName((String) orgMap.get("orgName"));
             vo.setBankAccount((String) orgMap.get("bankAccount"));
             vo.setIndex(String.valueOf((param.getPageNo()-1)*param.getPageSize()+1+i));
             resultList.add(vo);
        }
        result.setResult(resultList);
    	return result;
    }
    
    /**
     * (充值查询)转换，新增租户,银行卡号等信息
     * 
     * @param param
     * @param tenantId
     * @return
     * @author luoxuan
     */
    private PageInfo<FundSerialInfoVo> converFundSerialInfoVo(PageInfo<FundSerialInfo> param,String tenantId){
    	PageInfo<FundSerialInfoVo> result = new PageInfo<FundSerialInfoVo>();
    	if (param == null) {
            return result;
        }
    	result.setCount(param.getCount());
    	result.setPageCount(param.getPageCount());
    	result.setPageNo(param.getPageNo());
    	result.setPageSize(param.getPageSize());
    	List<FundSerialInfoVo> resultList = new ArrayList<FundSerialInfoVo>();
    	for(FundSerialInfo paramSub: param.getResult()){
    		FundSerialInfoVo vo=new FundSerialInfoVo();
    		BeanUtils.copyProperties(vo, paramSub);
            Map<String, Object> orgMap = this.queryOrgInfo(vo.getAcctId1(), tenantId);
            vo.setOrgName((String) orgMap.get("orgName"));
            vo.setBankAccount((String) orgMap.get("bankAccount"));
            resultList.add(vo);
    	}
    	return result;
    }
    
    /**
     * 翻译租户相关信息
     * 
     * @return
     * @author luoxuan
     */
    private Map<String, Object> queryOrgInfo(String custId, String tenantId){
    	Map<String, Object> result = new HashMap<String, Object>();
    	IQueryIdInfoSV queryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
    	CustIdInfo custIdInfo = new CustIdInfo();
        custIdInfo.setTenantId(tenantId);
        custIdInfo.setCustId(custId);
        BlCustinfoResponse res = queryIdInfoSV.queryBlCustinfoByCustId(custIdInfo);
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
            result.put("bankAccount", searchOrg.getOrgs().get(0).getBankAccount());
        }else{
            result.put("orgId", "");
            result.put("orgName", "");
            result.put("bankAccount", "");
        }
    	return result;
    }

	

}
