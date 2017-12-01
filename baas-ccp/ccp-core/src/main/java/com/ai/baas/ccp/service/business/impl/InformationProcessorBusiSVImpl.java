package com.ai.baas.ccp.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.service.business.interfaces.IInformationProcessorBusiSV;
import com.ai.baas.ccp.topoligy.core.constant.AccOwnerType;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.OwnerType;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.AccountService;
import com.ai.baas.ccp.topoligy.core.manager.service.CustomerService;
import com.ai.baas.ccp.topoligy.core.manager.service.SubsUserService;
import com.ai.baas.ccp.topoligy.core.pojo.Account;
import com.ai.baas.ccp.topoligy.core.pojo.Customer;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.User;
import com.ai.baas.ccp.util.ResourceCheck;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.google.gson.JsonObject;

/**
 * 三户信息
 */
@Component
public final class InformationProcessorBusiSVImpl implements IInformationProcessorBusiSV{
    private static final Logger logger = LoggerFactory
            .getLogger(InformationProcessorBusiSVImpl.class);

    @Autowired
    private SubsUserService subsUserService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    public InformationProcessorObject process(ConfigContainerObject confContainer, OmcObj omcObj,
            JsonObject jsonObject) throws OmcException {
        InformationProcessorObject informationProcessorObject = new InformationProcessorObject();
        informationProcessorObject.setDefault(confContainer, omcObj, jsonObject);

        String ownertype = omcObj.getOwertype();
        String ownerid = omcObj.getOwerid();
        String tenantid = omcObj.getTenantid();
        String policyId = jsonObject.get(OmcCalKey.OMC_POLICY_ID).getAsString();

        check(tenantid, ownertype, policyId, confContainer);
        // 账户
        if (OwnerType.ACCT.equals(ownertype)) {
            infoBuilerByAcct(tenantid, ownertype, ownerid, informationProcessorObject);
        }
        // 客户
        if (OwnerType.CUST.equals(ownertype)) {
            infoBuilerByCust(tenantid, ownertype, ownerid, informationProcessorObject);
        }
        // 用户
        if (OwnerType.SERV.equals(ownertype)) {
            infoBuilerByUser(tenantid, ownertype, ownerid, informationProcessorObject);
        }
        return informationProcessorObject;

    }

    private void check(String tenantid, String ownertype, String policyId,
            ConfigContainerObject confContainer) throws OmcException {
        if (StringUtils.isBlank(ownertype)) {
            throw new OmcException("Information", "信控对象类型不能为空");
        }

        if (!(OwnerType.ACCT.equals(ownertype)) && !(OwnerType.CUST.equals(ownertype))
                && !(OwnerType.SERV.equals(ownertype))) {
            throw new OmcException("Information", "不支持的信控对象类型:" + ownertype);
        }
        // 根据余额模式检测 owner_type的正确性
        if (!ResourceCheck.checkOwnerType(tenantid, ownertype, policyId, confContainer)) {
            throw new OmcException("Information", "信控对象类型与信控参数配置不符：OWERTYPE[" + ownertype + "]");
        }

    }

    /**
     * 通过客户查询三户信息
     * 
     * @param tenantid
     * @param ownertype
     * @param ownerid
     * @param informationProcessorObject
     * @return
     */
    private boolean infoBuilerByCust(String tenantid, String ownertype, String ownerid,
            InformationProcessorObject informationProcessorObject) throws OmcException {
        logger.error("待扩展");
        return false;
    }

    /**
     * 通过账户查找三户资料
     * 
     * @param informationProcessorObject
     * 
     * @Title: infoBuilerByAcct
     * @Description:
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws OmcException
     */
    private boolean infoBuilerByAcct(String tenantid, String ownertype, String ownerid,
            InformationProcessorObject informationProcessorObject) throws OmcException {
        // 获取账户信息
        Account account = accountService.selectById(tenantid, ownerid);
        if (null == account) {
            throw new OmcException("Information", "没有取到账户信息infoOwner:"
                    + paramsString(tenantid, ownertype, ownerid));
        }

        // 默认设想为客户id
        String custId = account.getOwnerId();
        // 判断用户类型,如果为用户类型
        if (AccOwnerType.USER_TYPE.equals(account.getOwnerType())) {
            User user = subsUserService.selectById(tenantid, account.getOwnerId());
            if (user != null) {
                custId = user.getCustomerid();
            } else {
                throw new OmcException("Information", "账户属主为用户,但无法查询到用户信息,账户信息:"
                        + account.toString());
            }
            // 不是用户类型,也不是客户类型,则抛出异常
        } else if (!AccOwnerType.CUST_TYPE.equals(account.getOwnerType())) {
            throw new OmcException("Information", "账户属主类型无法识别,账户信息:" + account.toString());
        }

        // 获取客户信息
        Customer cust = customerService.getCustomer(tenantid, custId);
        if (null == cust) {
            throw new OmcException("Information", "没有取到客户信息infoOwner"
                    + paramsString(tenantid, ownertype, ownerid));
        }

        // 获取用户信息
        List<User> usrs = subsUserService.selectByAcctId(tenantid, ownerid);
        if ((null == usrs) || (usrs.isEmpty())) {
            throw new OmcException("Information", "没有取到客户信息infoOwner"
                    + paramsString(tenantid, ownertype, ownerid));
        }
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account);
        informationProcessorObject.setAccounts(accounts);
        informationProcessorObject.setCustomer(cust);
        informationProcessorObject.setUsers(usrs);
        return true;
    }

    /**
     * 通过用户查询三户信息
     * 
     * @param informationProcessorObject
     * 
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws OmcException
     */
    private boolean infoBuilerByUser(String tenantid, String ownertype, String ownerid,
            InformationProcessorObject informationProcessorObject) throws OmcException {
        // 获取用户信息
        User user = subsUserService.selectById(tenantid, ownerid);
        if (null == user) {
            throw new OmcException("Information", "没有取到用户信息infoOwner:"
                    + paramsString(tenantid, ownertype, ownerid));
        }

        // 获取账户信息
        Account account = accountService.selectById(tenantid, user.getAccountid());
        if (null == account) {
            throw new OmcException("Information", "没有取到账户信息infoOwner:"
                    + paramsString(tenantid, ownertype, ownerid));
        }

        // 获取客户信息
        Customer cust = customerService.getCustomer(tenantid, user.getCustomerid());
        if (null == cust) {
            throw new OmcException("Information", "没有取到客户信息infoOwner:"
                    + paramsString(tenantid, ownertype, ownerid));
        }

        List<User> usrs = new ArrayList<User>();
        usrs.add(user);
        List<Account> accounts = new ArrayList<Account>();
        accounts.add(account);
        informationProcessorObject.setAccounts(accounts);
        informationProcessorObject.setCustomer(cust);
        informationProcessorObject.setUsers(usrs);
        return true;
    }

    public String getRemindNbr(String remintype, String tenantid, String ownertype, String ownerid)
            throws OmcException {
        User user = subsUserService.selectById(tenantid, ownerid);
        if (user == null) {
            throw new OmcException("Information", "获取提醒号码异常:userid：" + ownerid);
        }
        if (StringUtils.isBlank(user.getFactorcode())) {
            throw new OmcException("Information", "资料异常没有配置提醒号码:userid：" + ownerid);
        }
        return user.getFactorcode();
    }

    private String paramsString(String tenantid, String ownertype, String ownerid) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(OmcCalKey.OMC_OWNER_ID, ownerid);
        jsonObject.addProperty(OmcCalKey.OMC_OWNER_TYPE, ownertype);
        jsonObject.addProperty(OmcCalKey.OMC_TENANT_ID, tenantid);
        return jsonObject.toString();
    }

}
