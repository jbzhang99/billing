package com.ai.baas.ccp.topoligy.core.manager.service.shm;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.SubsUserService;
import com.ai.baas.ccp.topoligy.core.pojo.User;
import com.ai.baas.ccp.topoligy.core.util.CacheClient;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * @ClassName: SubsUserServiceImpl
 * @Description: 获取用户资料信息
 * @author lvsj
 * @date 2015年11月24日 下午5:16:23
 * 
 */
@Component
public final class SubsUserServiceImplShm implements SubsUserService {
    private static Logger LOGGER = LoggerFactory.getLogger(SubsUserServiceImplShm.class);

    private static final CacheClient cacheClient = CacheClient.getInstance();

    // 用户信息表表名
    private static final String USER_TABLE = "bl_userinfo";

    // 用户信息扩展表表名
    private static final String USER_INFO_EXT = "bl_userinfo_ext";

    // 提醒号码在扩展表中名称
    private static final String REMIND_NUM = "remind_num";

    @Override
    public User selectById(String tenantid, String id) throws OmcException {
        try {
            Map<String, String> params = new TreeMap<String, String>();
            params.put("SUBS_ID", id);
            params.put("TENANT_ID", tenantid);

            List<Map<String, String>> result = cacheClient.doQuery(USER_TABLE, params);
            if (result == null || result.size() == 0) {
                throw new OmcException("OMC-SUBS0001B", "subs_user表没有找到用户信息!" + params.toString());
            }
            return getUsers(result).get(0);
        } catch (Exception e) {
            throw new OmcException("OMC-RULE0001B", e);
        }
    }

    /**
     * 根据账户id查询用户列表
     * 
     * @param tenantid
     * @param id
     * @return
     * @throws OmcException
     */
    @Override
    public List<User> selectByAcctId(String tenantid, String id) throws OmcException {

        Map<String, String> params = new TreeMap<String, String>();
        params.put("ACCT_ID", id);
        params.put("TENANT_ID", tenantid);

        List<Map<String, String>> result = cacheClient.doQuery(USER_TABLE, params);
        if (result == null || result.size() == 0) {
            throw new OmcException("OMC-SUBS0001B", "bl_userinfo表没有找到用户信息!" + params.toString());
        }
        return getUsers(result);
    }

    /**
     * 通过客户id查询用户列表
     * 
     * @param tenantid
     * @param id
     * @return
     * @throws OmcException
     */
    @Override
    public List<User> selectByCustId(String tenantid, String id) throws OmcException {
        try {
            Map<String, String> params = new TreeMap<String, String>();
            params.put("CUST_ID", id);
            params.put("TENANT_ID", tenantid);

            List<Map<String, String>> result = cacheClient.doQuery(USER_TABLE, params);
            if (result == null || result.size() == 0) {
                throw new OmcException("OMC-SUBS0001B", "subs_user表没有找到用户信息!" + params.toString());
            }

            return getUsers(result);
        } catch (Exception e) {
            throw new OmcException("OMC-RULE0001B", e);
        }
    }

    @Override
    public User selectByNbr(String tenantid, String nbr) throws OmcException {
        try {
            Map<String, String> params = new TreeMap<String, String>();
            params.put("SERVICE_ID", nbr);
            params.put("TENANT_ID", tenantid);

            List<Map<String, String>> result = cacheClient.doQuery(USER_TABLE, params);
            if (result == null || result.isEmpty()) {
                throw new OmcException("OMC-SUBS0001B", "subs_user表没有找到用户信息!" + params.toString());
            }

            return getUsers(result).get(0);
        } catch (Exception e) {
            throw new OmcException("OMC-RULE0001B", e);
        }
    }

    private List<User> getUsers(List<Map<String, String>> result) {
        List<User> users = new ArrayList<User>();
        for (Map<String, String> map : result) {
            User user = new User();
            user.setAccountid(map.get("acct_id"));
            user.setCitycode(map.get("city_code"));
            user.setCustomerid(map.get("cust_id"));
            user.setProvincecode(map.get("province_code"));
            user.setServiceId(map.get("service_id"));
            user.setSubsid(map.get("subs_id"));
            /*
             * 此属性已取消,为兼容原数据设计,将此项设置为常量 updateDate 2016-03-22
             */
            user.setSystemid("1");
            user.setTenantid(map.get("tenant_id"));
            /**
             * 此属性修改为从扩展表中获取 updateDate 2016-03-24
             */
            user.setFactorcode(getRemindNum(map.get("subs_id")));
            String s2 = StringUtils.isBlank(map.get("active_time")) ? "1900-01-01 00:00:00.000"
                    : map.get("active_time");
            user.setActivetime(Timestamp.valueOf(s2));
            String s3 = StringUtils.isBlank(map.get("inactive_time")) ? "1900-01-01 00:00:00.000"
                    : map.get("inactive_time");
            user.setInactivetime(Timestamp.valueOf(s3));
            user.setPolicy_id(map.get("policy_id"));
            users.add(user);
        }

        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User subsUser = iterator.next();
            Timestamp nowTime = DateUtils.currTimeStamp();
            // 如果当前时间在用户生效时间之前或失效时间之后,则用户无效
            if (nowTime.before(subsUser.getActivetime())
                    && nowTime.after(subsUser.getInactivetime())) {
                iterator.remove();
            }
        }

        if (users.isEmpty()) {
            users = Collections.emptyList();
        }
        return users;
    }

    /**
     * 从用户信息扩展表中获取提醒号码
     * 
     * @param subsId
     * @return
     * @addDate 2016-03-24
     */
    public String getRemindNum(String subsId) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("EXT_NAME", REMIND_NUM);
        params.put("SUBS_ID", subsId);
        String remindNum = null;
        List<Map<String, String>> result = cacheClient.doQuery(USER_INFO_EXT, params);
        if (result != null && result.size() > 0) {
            String extVals = result.get(0).get("ext_value");
            remindNum = extVals;
        }
        return remindNum;
    }

}
