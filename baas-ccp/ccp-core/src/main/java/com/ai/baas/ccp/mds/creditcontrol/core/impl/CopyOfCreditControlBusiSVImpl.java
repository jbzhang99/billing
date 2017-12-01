package com.ai.baas.ccp.mds.creditcontrol.core.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.ccp.mds.creditcontrol.core.interfaces.ICreditControlBusiSV;
import com.ai.baas.ccp.service.business.impl.ConfigContainerBusiSVImpl;
import com.ai.baas.ccp.service.business.impl.EventProcessorBusiSVImpl;
import com.ai.baas.ccp.service.business.impl.InformationProcessorBusiSVImpl;
import com.ai.baas.ccp.service.business.impl.NoticeProcessorBusiSVImpl;
import com.ai.baas.ccp.service.business.impl.OmcCalProcessorBusiSVImpl;
import com.ai.baas.ccp.service.factory.ServiceFactory;
import com.ai.baas.ccp.topoligy.core.business.EventProcessor;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.dto.Dto4CreditCal;
import com.ai.baas.ccp.topoligy.core.dto.Dto4CreditNotice;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.ScoutLog;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.util.OmcUtils;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.EventProcessorObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.ai.baas.ccp.vo.NoticeProcessorObject;
import com.ai.baas.ccp.vo.OmcCalProcessorObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 重构后的业务处理，基本上实现了数据与操作分离，适用于spring框架下的实例管理
 * @author mayt
 *
 */
@Component
@Transactional
public class CopyOfCreditControlBusiSVImpl implements ICreditControlBusiSV {
    private static final Logger LOGGER = LoggerFactory.getLogger(CopyOfCreditControlBusiSVImpl.class);

    @Autowired
    private transient ConfigContainerBusiSVImpl confContainer;

    @Autowired
    private transient EventProcessorBusiSVImpl eventProcessor;

    @Autowired
    private transient OmcCalProcessorBusiSVImpl omcCalProcessor;

    @Autowired
    private transient NoticeProcessorBusiSVImpl noticeProcessor;

    @Autowired
    private InformationProcessorBusiSVImpl info;
    
    @Override
    public void excute(String data) {
        try {
            ConfigContainerObject configContainerObject = getPolocyConfig();
            LOGGER.info("adata = [" + data + "]");

            Gson gson = new Gson();
            // 对获取数据进行解析
            JsonObject input = gson.fromJson(data, JsonObject.class);
            String amount = input.get("amount").getAsString();
            String owner_type = input.get("owner_type").getAsString();
            String amount_type = input.get("amount_type").getAsString();
            String event_type = input.get("event_type").getAsString();
            String amount_mark = input.get("amount_mark").getAsString();
            String owner_id = input.get("owner_id").getAsString();
            // String source_type = input.get("source_type").getAsString();
            String tenant_id = input.get("tenant_id").getAsString();
            String system_id = input.get("system_id").getAsString();
            String event_id = input.get("event_id").getAsString();
            // String expanded_info = input.get("expanded_info").toString();

            if (!owner_type.startsWith("/")) {
                owner_type = "/" + owner_type;
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(OmcCalKey.OMC_SYSTEM_ID, system_id);
            jsonObject.addProperty(OmcCalKey.OMC_CHARGING_STATION, "");
            jsonObject.addProperty(OmcCalKey.OMC_CHARGING_PILE, "");

            LOGGER.debug("策略、规则、参数信息获取......");
            // 信控对象
            OmcObj omcObj = new OmcObj(tenant_id, owner_type, owner_id, event_type);

            // 获取策略、规则
            EventProcessorObject eventProcessorObject = new EventProcessorObject();
            eventProcessorObject.setConfig(configContainerObject);
            eventProcessorObject.setInput(jsonObject);
            eventProcessorObject.setOmcobj(omcObj);
            eventProcessor.process(eventProcessorObject);
            JsonObject evendata = eventProcessorObject.getOutput();

            // 添加策略信息
            jsonObject.add(EventProcessor.DF_POLICY, evendata.get(EventProcessor.DF_POLICY));
            // 添加策略对应所有规则信息
            jsonObject.add(EventProcessor.DF_RULES, evendata.get(EventProcessor.DF_RULES));

            Dto4CreditCal dto4CreditCal = new Dto4CreditCal();
            dto4CreditCal.setOwner(omcObj);
            dto4CreditCal.setAmount(amount);
            dto4CreditCal.setAmount_mark(amount_mark);
            dto4CreditCal.setAmount_type(amount_type);
            dto4CreditCal.setEventid(event_id);
            dto4CreditCal.setEventtype(event_type);
            dto4CreditCal.setExpanded_info(jsonObject.toString());

            gson = new Gson();
            // 获取策略和规则信息
            jsonObject = gson.fromJson(dto4CreditCal.getExpanded_info(), JsonObject.class);
            // 补充Owner数据
            String extAmount = dto4CreditCal.getAmount();
            String chargingStation = jsonObject.get(OmcCalKey.OMC_CHARGING_STATION).getAsString();
            String chargingPile = jsonObject.get(OmcCalKey.OMC_CHARGING_PILE).getAsString();
            String policyId = jsonObject.get(OmcCalKey.OMC_POLICY_ID).getAsString();

            jsonObject.addProperty(OmcCalKey.OMC_EXT_AMOUNT, extAmount);
         // 准备资料 获取三户信息
            InformationProcessorObject informationProcessorObject = info.process(configContainerObject, omcObj,
                    jsonObject);
            // 调用信控计算处理
            OmcCalProcessorObject calProcessorObject = omcCalProcessor.process(configContainerObject, omcObj, jsonObject);
            // 获取信控计算结果
            JsonObject caldata = calProcessorObject.getOutput();
            // 获取信控计算后的余额信息
            RealTimeBalance realTimeBalance = calProcessorObject.getRealTimeBalance();

            // 根据匹配后的规则列表
            String rules = caldata.get(OmcCalKey.OMC_RULE_ID_LIST).toString();
            // caldata.get(OmcCalKey.OMC_RULE_ID_LIST);
            List<SectionRule> sectionRules = OmcUtils.toSectionRules(configContainerObject, rules);
            if ((null == sectionRules) || sectionRules.isEmpty()) {
                LOGGER.debug("没取到对应的规则，无法信控");
                return;
            }

            LOGGER.debug("--CreditCalProcesser计算完毕");

            // 准备信控计算后输出
            JsonObject outdata = new JsonObject();
            outdata.addProperty(OmcCalKey.OMC_CHARGING_STATION, chargingStation);
            outdata.addProperty(OmcCalKey.OMC_CHARGING_PILE, chargingPile);
            outdata.addProperty(OmcCalKey.OMC_POLICY_ID, policyId);
            outdata.add(OmcCalKey.OMC_RULE_ID_LIST, caldata.get(OmcCalKey.OMC_RULE_ID_LIST));

            // 准备数据
            Dto4CreditNotice dto4CreditNotice = new Dto4CreditNotice();
            dto4CreditNotice.setOwner(omcObj);
            dto4CreditNotice.setRealTimeBalance(realTimeBalance);
            dto4CreditNotice.setExtInfo(outdata.toString());

            ScoutLog scoutLog = new ScoutLog();
            scoutLog.setOwner(dto4CreditNotice.getOwner());
            scoutLog.setRealTimeBalance(dto4CreditNotice.getRealTimeBalance());

            gson = new Gson();
            jsonObject = gson.fromJson(dto4CreditNotice.getExtInfo(), JsonObject.class);

            // 信控通知处理
            NoticeProcessorObject noticeProcessorObject = new NoticeProcessorObject();
            noticeProcessorObject.setConfig(configContainerObject);
            noticeProcessorObject.setInfo(informationProcessorObject);
            noticeProcessorObject.setInput(jsonObject);
            noticeProcessorObject.setOmcobj(omcObj);
            noticeProcessorObject.setRealBalance(realTimeBalance);
            noticeProcessor.process(noticeProcessorObject);
        } catch (Exception e) {
            LOGGER.error("信控处理失败", e);
        }

    }

    private ConfigContainerObject getPolocyConfig() throws OmcException {
        return ServiceFactory.getIConfigContainerBusiSV().configObtain();
    }

}
