package com.ai.baas.ccp.service.business.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.service.business.interfaces.IBalanceCalProcessorBusiSV;
import com.ai.baas.ccp.topoligy.core.constant.BalancecalModel;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.OwnerType;
import com.ai.baas.ccp.topoligy.core.dubbo.service.RealtimeBalanceService;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.vo.BalanceCalProcessorObject;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.google.gson.JsonObject;

/**
 * 余额计算模式
 * 
 * @author jackieliu
 * 
 */
@Component
public final class BalanceCalProcessorBusiSVImpl implements IBalanceCalProcessorBusiSV{
    public BalanceCalProcessorBusiSVImpl() {
    }

    @Autowired
    private transient RealtimeBalanceService balanceService;

    public BalanceCalProcessorObject process(ConfigContainerObject confContainer,
            InformationProcessorObject informationProcessorObject, JsonObject data)
            throws OmcException {
        BalanceCalProcessorObject balanceCalProcessorObject = new BalanceCalProcessorObject(
                confContainer, informationProcessorObject, data);

        OmcObj omcObj = informationProcessorObject.getOmcobj();
        String ownertype = omcObj.getOwertype();
        String ownerid = omcObj.getOwerid();
        String tenantid = omcObj.getTenantid();
        String busicode = omcObj.getBusinesscode();

        String policyid = data.get(OmcCalKey.OMC_POLICY_ID).getAsString();
        String extAmount = data.get(OmcCalKey.OMC_EXT_AMOUNT).getAsString();

        JsonObject messageinfo = new JsonObject();
        messageinfo.addProperty(OmcCalKey.OMC_POLICY_ID, policyid);
        messageinfo.addProperty(OmcCalKey.OMC_TENANT_ID, tenantid);
        messageinfo.addProperty(OmcCalKey.OMC_OWNER_TYPE, ownertype);
        messageinfo.addProperty(OmcCalKey.OMC_OWNER_ID, ownerid);
        messageinfo.addProperty(OmcCalKey.OMC_BUSINESS_CODE, busicode);

        String balancemodel = confContainer.getCfgPara(OmcCalKey.OMC_CFG_BALANCECALMODEL, tenantid,
                policyid, "");

        if (StringUtils.isBlank(balancemodel)) {
            throw new OmcException("BalanceCal", "获取余额计算参数【" + OmcCalKey.OMC_CFG_BALANCECALMODEL
                    + "】失败，请检查配置或者设置缺省值:" + messageinfo.toString());
        }
        // 用户余额模式
        if ((BalancecalModel.SUBSMODEL.equals(balancemodel)) && (OwnerType.SERV.equals(ownertype))) {
            builderResBalanceServ(omcObj, extAmount, balanceCalProcessorObject);
            // 账户余额模式
        } else if ((BalancecalModel.ACCTMODEL.equals(balancemodel))
                && (OwnerType.ACCT.equals(ownertype))) {
            builderBalanceAcct(omcObj, extAmount, balanceCalProcessorObject);
            // 不支持模式
        } else {
            messageinfo.addProperty(OmcCalKey.OMC_CFG_BALANCECALMODEL, balancemodel);
            throw new OmcException("BalanceCal", "获取余额信息:余额模式与OwnerType不一致，请修改配置。" + balancemodel
                    + messageinfo.toString());
        }
        return balanceCalProcessorObject;
    }


    /**
     * 用户余额模式
     * 
     * @param owner
     * @param extinfo
     * @throws OmcException
     */
    private void builderResBalanceServ(OmcObj owner, String extinfo, BalanceCalProcessorObject balanceCalProcessorObject) throws OmcException {
        String appname = null;// this.getConfig().getSysconfig().get(OmcCalKey.OMC_CFG_ENVIRONMENT_APP);
        // RealTimeBalance realTimeBalance = urlClient.doQuery(appname, owner);
        // RealtimeBalanceService balanceService = DubboxUtils.getBalanceService();
        RealTimeBalance realBalance = balanceService.queryBalance(appname, owner);
        balanceCalProcessorObject.setRealBalance(realBalance);
        // RealTimeBalance realTimeBalance = new RealTimeBalance();
        // realTimeBalance.setOwner(owner);
        // realTimeBalance.setAcctMonth("201603");
        // realTimeBalance.setFstUnSettleMon("201603");
        // realTimeBalance.setBalance(new BigDecimal("0"));
        // realTimeBalance.setCreditline(new BigDecimal("0"));
        // realTimeBalance.setExtInfo("{}");
        // realTimeBalance.setRealBalance(new BigDecimal("-1000"));
        // realTimeBalance.setRealBill(new BigDecimal("0"));
        // realTimeBalance.setUnIntoBill(new BigDecimal("0"));
        // realTimeBalance.setUnSettleBill(new BigDecimal("0"));
        // realTimeBalance.setUnsettleMons(0);
        // realBalance = realTimeBalance;
    }

    /**
     * 账户余额模式
     * 
     * @param owner
     * @param extinfo
     * @throws OmcException
     */
    private void builderBalanceAcct(OmcObj owner, String extinfo, BalanceCalProcessorObject balanceCalProcessorObject) throws OmcException {
        // Map<String,String> syscfg = this.getConfig().getSysconfig();
        // String appname = syscfg.get(OmcCalKey.OMC_CFG_ENVIRONMENT_APP);
        // RealtimeBalanceService balanceService = DubboxUtils.getBalanceService();
        // realBalance = balanceService.queryBalance(appname,owner);
        RealTimeBalance realBalance = balanceService.queryBalance("", owner);
        balanceCalProcessorObject.setRealBalance(realBalance);
        // realBalance = urlClient.doQuery(appname, owner);
    }

}
