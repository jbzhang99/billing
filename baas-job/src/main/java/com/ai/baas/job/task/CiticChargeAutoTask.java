package com.ai.baas.job.task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.baas.job.constants.BaasJobCacheConstants;
import com.ai.baas.job.dao.mapper.bo.BmcBasedataCode;
import com.ai.baas.job.service.busi.interfaces.ICiticBankChargeBusiSV;
import com.ai.baas.job.util.BmcBaseDataCodeUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.extension.DubboRestResponse;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 获取中信银行交易明细，对账户充值<br>
 * Date: 2016年9月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author mayt
 */
public class CiticChargeAutoTask {
    private static final Logger LOGGER = LogManager.getLogger(CiticChargeAutoTask.class);

    private static final String PATH = "classpath:context/job-context.xml";

    public static void main(String[] args) {
        LOGGER.info("开始执行银企直联任务， 传入的参数：" + Arrays.toString(args));
        ApplicationContextUtil.loadApplicationContext(new ClassPathXmlApplicationContext(
                new String[] { PATH }));
        doCharge(args[0]);
        ApplicationContextUtil.closeAppContext();
    }

    @SuppressWarnings("unchecked")
    private static void doCharge(String dayStr) {
        // dayStr设定某一天，格式YYYYMMDD
        // 1.获取机构信息中是租户的部分
        // 获取接口url
        String url = getUrl();
        Map<String, String> param = new HashMap<String, String>();
        param.put("select_type", "2");
        param.put("select_id", "1");
        String rs = HttpClientUtil.sendGet(url, param);
        LOGGER.info("机构基本信息返回:" + rs);
        // rs =
        // "{\"resultCode\":\"000000\", \"data\":\"{  \\\"orgs\\\": [{    \\\"org_id\\\": \\\"6ba76566-0251-4e1a-a188-64e1b2d26be2\\\",    \\\"citic_org_id\\\": \\\"000025\\\",    \\\"name\\\": \\\"中信证券股份有限公司\\\",    \\\"abbreviation\\\": \\\"中信证券\\\",    \\\"superior\\\": \\\"52dbb3e5-ec31-4989-be82-705123c45eef\\\",    \\\"is_cost_center\\\": true,    \\\"is_tenant\\\": true,    \\\"is_supplier\\\": true,    \\\"vpc_id\\\": \\\"b1305db4-c5e3-4413-80fa-b0273536d0b5\\\",    \\\"uri\\\": \\\"http://csadaptor.citic.com/\\\",   \\\"bank_account\\\" : \\\"3110710008921019591\\\",\\\"enter_time\\\" : \\\"2016-06-30\\\"  }]}\"}";
        DubboRestResponse dubboRestResponse = JSON.parseObject(rs, DubboRestResponse.class);
        if (!"000000".equals(dubboRestResponse.getResultCode())) {
            throw new SystemException("查询机构基本信息失败" + rs);
        }
        String data = (String) dubboRestResponse.getData();
        // data =
        // "{  \"orgs\": [{    \"org_id\": \"6ba76566-0251-4e1a-a188-64e1b2d26be2\",    \"citic_org_id\": \"000025\",    \"name\": \"中信证券股份有限公司\",    \"abbreviation\": \"中信证券\",    \"superior\": \"52dbb3e5-ec31-4989-be82-705123c45eef\",    \"is_cost_center\": true,    \"is_tenant\": true,    \"is_supplier\": true,    \"vpc_id\": \"b1305db4-c5e3-4413-80fa-b0273536d0b5\",    \"uri\": \"http://csadaptor.citic.com/\",   \"bank_account\" : \"60193480640935824035008\",\"enter_time\" : \"2016-06-30\"  }]}";
        JSONObject jsonObject = JSON.parseObject(data);
        String orgsStr = jsonObject.getString("orgs");
        @SuppressWarnings("rawtypes")
        List<Map> list = JSON.parseArray(orgsStr, Map.class);
        for (@SuppressWarnings("rawtypes")
        Map map : list) {
            ICiticBankChargeBusiSV sv = ApplicationContextUtil
                    .getService(ICiticBankChargeBusiSV.class);
            try {
                sv.charge(map, dayStr);
            } catch (Exception e) {
                LOGGER.error("存款失败 " + map.toString(), e);
            }
        }
    }

    private static String getUrl() {
        BmcBasedataCode bmcBasedataCode = BmcBaseDataCodeUtil.getBmcBasedataCode("",
                BaasJobCacheConstants.ParamType.ADAPTER_PARAM,
                BaasJobCacheConstants.ParamCode.ORG_QUERY_PATH);
        if (null == bmcBasedataCode) {
            throw new BusinessException("获取[机构基本信息查询管理]url失败，请检查缓存");
        }
        String url = "";
        url = bmcBasedataCode.getDefaultValue();
        if (StringUtil.isBlank(url)) {
            throw new BusinessException("获取[机构基本信息查询管理]url为空，请检查缓存");
        }
        return url;
    }

}
