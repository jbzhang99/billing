package com.ai.baas.amc.api.deposit.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.deposit.interfaces.IDepositSV;
import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.baas.amc.api.deposit.param.DepositResponse;
import com.ai.baas.amc.api.deposit.param.TransSummary;
import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IDepositBusiSV;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.mail.EmailFactory;
import com.ai.opt.sdk.components.mail.EmailTemplateUtil;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.util.UUIDUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
@Component
public class DepositSVImpl implements IDepositSV {

    private static final Logger LOG = LogManager.getLogger(DepositSVImpl.class);
    @Autowired
    private IDepositBusiSV depositSV;

    
    @Override
    public DepositResponse depositFund(DepositRequest request) throws BusinessException,
            SystemException {
        LOG.info("开始存款服务");
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
        }
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }        
        if (StringUtil.isBlank(request.getSystemId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "系统ID不能为空");
        }
        if (StringUtil.isBlank(request.getAccountId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "存款账户号不能为空");
        }
        if (StringUtil.isBlank(request.getBusiSerialNo())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "业务流水号不能为空");
        }
        if (CollectionUtil.isEmpty(request.getTransSummary())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "交易摘要不能为空");
        }
        for (TransSummary summary : request.getTransSummary()) {
            if (summary.getSubjectId() == 0l) {
                throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "科目ID不能为空["
                        + JSON.toJSONString(summary) + "]");
            }
            if (!StringUtil.isBlank(summary.getFundeffDate())
                    && !DateUtil.isValidDate(summary.getFundeffDate(), DateUtil.DATETIME_FORMAT)) {
                throw new BusinessException(ExceptCodeConstants.PARAM_TYPE_NOT_RIGHT, "生效时间格式不正确["
                        + JSON.toJSONString(summary) + "]");
            }
            if (!StringUtil.isBlank(summary.getFundexpDate())
                    && !DateUtil.isValidDate(summary.getFundexpDate(), DateUtil.DATETIME_FORMAT)) {
                throw new BusinessException(ExceptCodeConstants.PARAM_TYPE_NOT_RIGHT, "失效时间格式不正确["
                        + JSON.toJSONString(summary) + "]");
            }
        }
        
        String paySerialCode = depositSV.depositFund(request);
        DepositResponse response = new DepositResponse();
        ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
        response.setPaySerialCode(paySerialCode);
        response.setResponseHeader(responseHeader);
        LOG.info("存款结束");
        IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams queryInfo = new QueryInfoParams();
        queryInfo.setTenantId(request.getTenantId());        
        queryInfo.setParamType(AmcConstants.EmailSeng.SEND_MAIL);
        queryInfo.setTradeSeq(UUIDUtil.genId32());
        BaseCodeInfo baseCodeInfo=iBaseInfoSV.getBaseInfo(queryInfo);
        List<BaseCode> baseCodeList=baseCodeInfo.getParamList();
        for(BaseCode baseCode : baseCodeList){
        	baseCode.getParamCode();
            if(AmcConstants.EmailSeng.YES.equals(baseCode.getParamCode())){
            	IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
            	AcctIdInfo acctIdInfo=new AcctIdInfo();
            	acctIdInfo.setAcctId(request.getAccountId());
            	acctIdInfo.setTenantId(request.getTenantId());
            	BlCustinfoResponse blCustinfoResponse=iQueryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
            	
            	ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
                UserInfoQueryVo userInfoQueryVo=new UserInfoQueryVo();
                userInfoQueryVo.setSelectType("3");
                userInfoQueryVo.setSelectId(blCustinfoResponse.getBlCustinfoInfos().get(0).getExtCustId());
                userInfoQueryVo.setTenantId(request.getTenantId());
                UserQueryResponse userQueryResponse=iCiticRestReqWrapperSV.searchUser(userInfoQueryVo);
                
                
            	String[] tomails = new String[] { userQueryResponse.getUsers().get(0).getMail() };
        		String subject = "亚信中信云邮件";
        		String[] data = new String[] {request.getBusiDesc(), null,null};
        		String htmlcontext = EmailTemplateUtil.buildHtmlTextFromTemplate(AmcConstants.EmailSeng.BIND_EMAIL, data);
        		
        		try {
        			EmailFactory.SendEmail(tomails, null, subject, htmlcontext);
        		} catch (Exception e) {
        			throw new BusinessException("发送邮件失败", e);
        		}
        		LOG.info("邮件已发送");
            }
        }
        
        JSONObject json = new JSONObject();
        json.put(AmcConstants.APMessage.ACCT_ID,request.getAccountId());
        json.put(AmcConstants.APMessage.TENANT_ID, request.getTenantId());
        
        IMessageSender msgSender = MDSClientFactory
                .getSenderClient(AmcConstants.MDSTopic.WO_TOPIC);
        msgSender.send(json.toString(), Long.valueOf(new SecureRandom().nextInt(1000)));
        LOG.info("销账消息发送成功");

        
        return response;
    }

}
