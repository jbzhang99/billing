package com.ai.baas.job.service.busi.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.deposit.interfaces.IDepositSV;
import com.ai.baas.amc.api.deposit.param.DepositRequest;
import com.ai.baas.amc.api.deposit.param.DepositResponse;
import com.ai.baas.amc.api.deposit.param.TransSummary;
import com.ai.baas.amc.api.oweinfo.interfaces.IOweInfoSV;
import com.ai.baas.amc.api.oweinfo.params.OweInfoCreateRequest;
import com.ai.baas.job.constants.BaasJobCacheConstants;
import com.ai.baas.job.constants.BaasJobCacheConstants.ParamCode;
import com.ai.baas.job.constants.BaasJobCacheConstants.ParamType;
import com.ai.baas.job.constants.BaasJobConstants;
import com.ai.baas.job.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.job.dao.mapper.bo.BlAcctInfoCriteria;
import com.ai.baas.job.dao.mapper.bo.BlCustinfo;
import com.ai.baas.job.dao.mapper.factory.MapperFactory;
import com.ai.baas.job.service.atom.interfaces.IBlAcctInfoAtomSV;
import com.ai.baas.job.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.job.service.busi.interfaces.ICiticBankChargeBusiSV;
import com.ai.baas.job.util.BmcBaseDataCodeUtil;
import com.ai.baas.job.util.BmcSeqUtil;
import com.ai.baas.job.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.extension.DubboRestResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;

@Component
@Transactional
public class CiticBankChargeBusiSVImpl implements ICiticBankChargeBusiSV {
    private static final Logger LOGGER = LogManager.getLogger(CiticBankChargeBusiSVImpl.class);

    @Override
    public void charge(Map<String, String> map, String dayStr) {
        // 2.
        // 获取机构信息中的“租户充值银行账号”作为入参，调用中信银行【附属账户交易明细查询】接口，获取该账号当前时间前一天的交易明细列表；入参中的userName是银企直联的用户名，需可以配置；
        String bankAccount = map.get("bank_account");
        if (StringUtil.isBlank(bankAccount)) {
            return;
        }
        String extCustId = map.get("org_id");
        String tenantId = "ECITIC";
        String custId = null;
        String acctId = null;

        String url = getEciticBankUrl(tenantId);
        String data = getBankChargeDetail(bankAccount, url, tenantId, dayStr);
//        data = "<?xml version=\"1.0\" encoding=\"GBK\"?><stream><status></status><statusText></statusText><list name=\"userDataList\"> <row><subAccNo></subAccNo><MNGNODE></MNGNODE> <TRANAMT>12.34</TRANAMT> <ACCBAL></ACCBAL>  <OTHERACCNO></OTHERACCNO><OTHERACCNAME></OTHERACCNAME><OPENACCNAME></OPENACCNAME>  <TRANTYPE></TRANTYPE>     <TRANDATE></TRANDATE> <MEMO></MEMO> <TRANNO>12345</TRANNO><MIXNO></MIXNO> <CDFG></CDFG> <CRYTYPE></CRYTYPE> <VCTP></VCTP> <VCNM></VCNM></row></list></stream>";
        Document docResult;
        try {
            docResult = DocumentHelper.parseText(data);
        } catch (DocumentException e) {
            throw new SystemException("解析银行交易明细失败", e);
        }
        Element rootElement = docResult.getRootElement();
        Element listElement = rootElement.element("list");
        Iterator<Element> iterator = listElement.elementIterator("row");
        while (iterator.hasNext()) {
            Element rowElement = iterator.next();
            String cdfg = rowElement.elementTextTrim("CDFG");// 借贷标识
            String tranamt = rowElement.elementTextTrim("TRANAMT");// 金额 decimal(15,2)
            String tranno = rowElement.elementTextTrim("TRANNO");// 柜员交易号
            String trandate = rowElement.elementTextTrim("TRANDATE");// 交易日期
            String mixno = rowElement.elementTextTrim("MIXNO");// 调账序号
            String busiSerialNo = new StringBuilder().append(tranno).append(",").append(trandate).append(",").append(mixno).toString();
//           付款 D； 收款 C
            if (!"C".equals(cdfg)) {
                continue;
            }
            Double amount = Double.parseDouble(tranamt) * 1000;

            if (StringUtil.isBlank(custId) || StringUtil.isBlank(acctId)) {
                Map<String, String> mapTmp = getCustAcctId(extCustId, tenantId, custId, acctId);
                custId = mapTmp.get("custId");
                acctId = mapTmp.get("acctId");
            }

            // 调用存款接口
            TransSummary summary = new TransSummary();
            summary.setSubjectId(100000);
            summary.setAmount(amount.longValue());

            List<TransSummary> list = new ArrayList<TransSummary>();
            list.add(summary);

            DepositRequest depositRequest = new DepositRequest();
            depositRequest.setSystemId("ZXYH-YQZL");
            depositRequest.setAccountId(acctId);
            depositRequest.setCustId(custId);
            depositRequest.setBusiSerialNo(busiSerialNo);
            depositRequest.setBusiDesc("中信银行-银企直联存款");
            depositRequest.setTenantId(tenantId);
            depositRequest.setTransSummary(list);

            DepositResponse depositResponse = DubboConsumerFactory.getService(IDepositSV.class)
                    .depositFund(depositRequest);
            if (null == depositResponse) {
                throw new SystemException("存款失败, 返回为空");
            }
            if (null == depositResponse.getResponseHeader()) {
                throw new SystemException("存款失败, 返回报文头为空");
            }
            if (!depositResponse.getResponseHeader().getIsSuccess()) {
                throw new SystemException("存款失败", depositResponse.getResponseHeader()
                        .getResultMessage());
            }
            LOGGER.info("账户{}充值成功，来源：附属账号{},金额{}元:", acctId, bankAccount, tranamt);
        }
    }

    private String getEciticBankUrl(String tenantId) {
        String url = BmcBaseDataCodeUtil.getBmcBasedataCodeDefaultValue(tenantId,
                BaasJobCacheConstants.ParamType.ECITIC_BANK, BaasJobCacheConstants.ParamCode.URL);
        if (StringUtil.isBlank(url)) {
            throw new SystemException(
                    "获取中信银行url失败，请检查bmc_basedata_code表缓存，param_type = ECITIC_BANK, param_code = URL");
        }
        return url;
    }

    private String getBankChargeDetail(String bankAccount, String url, String tenantId, String dayStr) {
        String userName = BmcBaseDataCodeUtil.getBmcBasedataCodeDefaultValue(tenantId,
                ParamType.ECITIC_BANK, ParamCode.USER_NAME);
        if (StringUtil.isBlank(userName)) {
            throw new BusinessException(
                    "未获取到中信银行用户名，请检查bmc_basedata_code表缓存，ParamType.ECITIC_BANK, ParamCode.USER_NAME");
        }
        if (StringUtil.isBlank(dayStr)) {
            Timestamp sysDate = DateUtil.getSysDate();
            dayStr = DateUtil.getDateString(DateUtil.getOffsetDaysTime(sysDate, -1),
                    DateUtil.YYYYMMDD);
        }
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("GBK");
        Element streamElement = document.addElement("stream");
        Element actionElement = streamElement.addElement("action");
        actionElement.addText("DLSUBDTL");
        Element userNameElement = streamElement.addElement("userName");
        userNameElement.addText(userName);
        Element subAccNoElement = streamElement.addElement("subAccNo");
        subAccNoElement.addText(bankAccount);
        Element startDateElement = streamElement.addElement("startDate");
        startDateElement.addText(dayStr);
        Element endDateElement = streamElement.addElement("endDate");
        endDateElement.addText(dayStr);
        streamElement.addElement("tranType");
        streamElement.addElement("minAmt");
        streamElement.addElement("maxAmt");

        String body = document.asXML();
        LOGGER.info(body);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "text/xml");
        header.put("charset", "GBK");

        String rs;
        try {
            rs = HttpClientUtil.sendPost(url, body, header);
        } catch (IOException | URISyntaxException e1) {
            throw new SystemException("查询银行交易明细失败", e1);
        }

        LOGGER.info(rs);
        DubboRestResponse dubboRestResponse = JSON.parseObject(rs, DubboRestResponse.class);
        return (String) dubboRestResponse.getData();
    }

    private Map<String, String> getCustAcctId(String extCustId, String tenantId, String custId,
            String acctId) {
        String custName = "";
        // 查询客户信息
        Map<String, String> params = new TreeMap<String, String>();
        params.put("EXT_CUST_ID", extCustId);
        params.put("TENANT_ID", tenantId);
        List<Map<String, String>> dshmResult = DshmUtil.getClient()
                .list(BaasJobCacheConstants.Dshm.TableName.BL_CUSTINFO).where(params)
                .executeQuery(DshmUtil.getCacheClient());

        Timestamp sysdate = DateUtil.getSysDate();
        if (CollectionUtil.isEmpty(dshmResult)) {
            custId = BmcSeqUtil.getCustId();
            custName = custId;
            // 客户不存在，新建
            BlCustinfo blCustinfo = new BlCustinfo();
            blCustinfo.setCustId(custId);
            blCustinfo.setTenantId(tenantId);
            blCustinfo.setExtCustId(extCustId);
            blCustinfo.setCustName(custId);
            blCustinfo.setState(BaasJobConstants.BlCustinfo.State.NORMAL);
            blCustinfo.setStateChgTime(sysdate);
            MapperFactory.getBlCustinfoMapper().insertSelective(blCustinfo);
            // 写入缓存
            ApplicationContextUtil.getService(IBlCustinfoAtomSV.class).addDshmData(blCustinfo);
        } else {
            custId = dshmResult.get(0).get(BaasJobCacheConstants.Dshm.FieldName.CUST_ID);
            custName = dshmResult.get(0).get(BaasJobCacheConstants.Dshm.FieldName.CUST_NAME);
        }
        // 查询账户信息
        BlAcctInfoCriteria blAcctInfoCriteria = new BlAcctInfoCriteria();
        blAcctInfoCriteria.createCriteria().andTenantIdEqualTo(tenantId)
                .andOwnerTypeEqualTo(BaasJobConstants.BlAcctInfo.OwnerType.CUST)
                .andOwnerIdEqualTo(custId);
        List<BlAcctInfo> blAcctInfos = MapperFactory.getBlAcctInfoMapper().selectByExample(
                blAcctInfoCriteria);
        boolean needCreateOweInfo = false;
        if (CollectionUtil.isEmpty(blAcctInfos)) {
            needCreateOweInfo = true;
            // 账户不存在，创建账户
            acctId = BmcSeqUtil.getAcctId();
            BlAcctInfo blAcctInfo = new BlAcctInfo();
            blAcctInfo.setTenantId(tenantId);
            blAcctInfo.setAcctId(acctId);
            blAcctInfo.setOwnerType(BaasJobConstants.BlAcctInfo.OwnerType.CUST);
            blAcctInfo.setOwnerId(custId);
            blAcctInfo.setCreateTime(sysdate);
            MapperFactory.getBlAcctInfoMapper().insertSelective(blAcctInfo);
            // 写入缓存
            ApplicationContextUtil.getService(IBlAcctInfoAtomSV.class).addDshmData(blAcctInfo);
        } else {
            acctId = blAcctInfos.get(0).getAcctId();
        }

        if (needCreateOweInfo) {
            OweInfoCreateRequest oweInfoCreateRequest = new OweInfoCreateRequest();
            oweInfoCreateRequest.setTenantId(tenantId);
            oweInfoCreateRequest.setAcctId(acctId);
            oweInfoCreateRequest.setCustId(custId);
            oweInfoCreateRequest.setCustName(custName);
            oweInfoCreateRequest.setMonth(DateUtil.getDateString(
                    DateUtil.getOffsetMonthsTime(sysdate, -1), DateUtil.YYYYMM));
            DubboConsumerFactory.getService(IOweInfoSV.class).createOweInfo(oweInfoCreateRequest);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("custId", custId);
        map.put("acctId", acctId);
        return map;

    }

}
