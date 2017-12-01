package com.ai.opt.sys.api.citicrestcommon.impl;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.extension.DubboRestResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.util.UUIDUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.*;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Component
public class CiticRestReqWrapperSVImpl implements ICiticRestReqWrapperSV{

    private static final Logger LOGGER = Logger.getLogger(CiticRestReqWrapperSVImpl.class);

    @Override
    public UserQueryResponse searchUser(UserInfoQueryVo queryVo) throws BusinessException, SystemException {
        UserQueryResponse queryResponse = new UserQueryResponse();
        ResponseHeader header = new ResponseHeader();
        try {
            String url = getReqUrl("CITIC_USER_URL");
            //String url = "http://10.6.186.31:8080/usrmgt/users.do";
            if(!StringUtil.isBlank(url)){
                Map<String,String> params = new HashMap<>();
                if(!StringUtil.isBlank(queryVo.getSelectType())){
                    params.put("select_type",queryVo.getSelectType());
                }
                if(!StringUtil.isBlank(queryVo.getSelectId())){
                    params.put("select_id",queryVo.getSelectId());
                }
                String s = HttpClientUtil.sendGet(url, params);
                if(!StringUtil.isBlank(s)){
                    DubboRestResponse restResponse = JSON.parseObject(s, DubboRestResponse.class);
                    if("000000".equals(restResponse.getResultCode())){
                        LOGGER.error("调用中信用户信息接口["+url+"]返回的信息为:"+ProcessJson(restResponse.getData().toString()));
                        queryResponse = JSON.parseObject(ProcessJson(restResponse.getData().toString()), UserQueryResponse.class);
                    }
                }
                header.setResultCode("000000");
                header.setIsSuccess(true);
                header.setResultMessage("查询用户管理接口成功");
            }else{
                header.setResultCode("000001");
                header.setIsSuccess(false);
                header.setResultMessage("获取配置url失败");
            }
        } catch (Exception e) {
            header.setResultCode("000001");
            header.setIsSuccess(false);
            header.setResultMessage("查询用户管理接口成功失败:"+e.getMessage());
            throw new SystemException("查询用户管理接口失败",e);
        }
        queryResponse.setResponseHeader(header);
        return queryResponse;
    }

    private String ProcessJson(String s) {
        s = s.toLowerCase();
        String reg = "_[a-z]";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(s);
        List<String> list = new ArrayList<>();
        while (matcher.find()){
            list.add(matcher.group());
            s = s.replace(matcher.group(),matcher.group().replace("_","").toUpperCase());
        }
        return s;
    }

    @Override
    public OrgQueryResponse searchOrg(OrgQueryVo queryVo) throws BusinessException, SystemException {
        OrgQueryResponse queryResponse = new OrgQueryResponse();
        ResponseHeader header = new ResponseHeader();
        try {
            String url = getReqUrl("CITIC_ORG_URL");
            //String url = "http://10.6.186.31:8080/usrmgt/service_org.do";
            if(!StringUtil.isBlank(url)){
                Map<String,String> params = new HashMap<>();
                if(!StringUtil.isBlank(queryVo.getSelectType())){
                    params.put("select_type",queryVo.getSelectType());
                }
                if(!StringUtil.isBlank(queryVo.getSelectId())){
                    params.put("select_id",queryVo.getSelectId());
                }
                String s = HttpClientUtil.sendGet(url, params);
                if(!StringUtil.isBlank(s)){
                    DubboRestResponse restResponse = JSON.parseObject(s, DubboRestResponse.class);
                    if("000000".equals(restResponse.getResultCode())){
                        LOGGER.error("调用中信机构信息接口["+url+"]返回的信息为:"+ProcessJson(restResponse.getData().toString()));
                        queryResponse = JSON.parseObject(ProcessJson(restResponse.getData().toString()), OrgQueryResponse.class);
                    }
                }
                header.setResultCode("000000");
                header.setIsSuccess(true);
                header.setResultMessage("查询机构管理接口成功");
            }else{
                header.setResultCode("000001");
                header.setIsSuccess(false);
                header.setResultMessage("获取配置url失败");
            }
        } catch (Exception e) {
            header.setResultCode("000001");
            header.setIsSuccess(false);
            header.setResultMessage("查询机构管理接口成功失败:"+e.getMessage());
            throw new SystemException("查询机构管理接口失败",e);
        }
        queryResponse.setResponseHeader(header);
        return queryResponse;
    }

    private String getReqUrl(String paramType) {
        IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
        QueryInfoParams req = new QueryInfoParams();
        req.setTenantId("BAAS");
        req.setParamType(paramType);
        req.setTradeSeq(UUIDUtil.genId32());
        BaseCodeInfo baseInfo = iBaseInfoSV.getBaseInfo(req);
        if(baseInfo!=null){
            BaseCode baseCode = baseInfo.getParamList().get(0);
            return baseCode.getParamCode();
        }
        return null;
    }
}
