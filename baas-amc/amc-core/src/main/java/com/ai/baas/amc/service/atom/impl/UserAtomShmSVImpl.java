package com.ai.baas.amc.service.atom.impl;

import com.ai.baas.amc.service.atom.interfaces.IUserAtomSV;
import com.ai.baas.amc.util.CacheClientUtil;
import com.ai.baas.amc.util.DateUtils;
import com.ai.baas.amc.vo.UserVo;
import com.ai.opt.base.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * 从共享内存中获取用户信息
 */
@Component
public class UserAtomShmSVImpl implements IUserAtomSV {
    private static Logger LOGGER = LoggerFactory.getLogger(UserAtomShmSVImpl.class);
    private static final CacheClientUtil cacheClient = CacheClientUtil.getInstance();
    //用户信息表表名
    private static final String USER_TABLE = "bl_userinfo";
    //用户信息扩展表表名
    private static final String USER_INFO_EXT = "bl_userinfo_ext";
    //提醒号码在扩展表中名称
    private static final String REMIND_NUM = "remind_num";
    /**
     * 根据租户id和用户id获取用户信息
     * @param tenantid
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public UserVo selectById(String tenantid, String id) throws BusinessException {
        try{
            Map<String, String> params = new TreeMap<String, String>();
            params.put("SUBS_ID",id);
            params.put("TENANT_ID",tenantid);

            List<Map<String, String>> result = cacheClient.doQuery(USER_TABLE, params);
            if(result == null || result.size()==0){
                throw new BusinessException("AMC-SUBS0001B",
                        "subs_user表没有找到用户信息!" + params.toString());
            }
            List<UserVo> userList = getUsers(result);
            if (userList==null || userList.isEmpty()){
            	throw new BusinessException("AMC-SUBS0003B",
                        "subs_user表没有找到有效的用户信息!" + params.toString());
            }
            return  userList.get(0);
        }catch (Exception e){
            LOGGER.error("",e);
            throw new BusinessException("OMC-RULE0001B",e.getMessage());
        }
    }

    private List<UserVo> getUsers(List<Map<String, String>> result){
        String[] subsid =	StringUtils.split(result.get(0).get("subs_id"),"#");
        String[] custid =	StringUtils.split(result.get(0).get("cust_id"),"#");
        String[] acctid =	StringUtils.split(result.get(0).get("acct_id"),"#");
        String[] tenantid =	StringUtils.split(result.get(0).get("tenant_id"),"#");
        //service_id由service_num变化来
        String[] serviceIds =	StringUtils.split(result.get(0).get("service_id"),"#");
        String[] provincecode =	StringUtils.split(result.get(0).get("province_code"),"#");
        String[] citycode =	StringUtils.split(result.get(0).get("city_code"),"#");
        String[] activetime =	StringUtils.split(result.get(0).get("active_time"),"#");
        String[] inactivetime =	StringUtils.split(result.get(0).get("inactive_time"),"#");

        List<UserVo> users = new ArrayList<UserVo>();
        for (int i = 0; i < subsid.length; i++) {
            UserVo user = new UserVo();
            user.setAccountid(acctid[i]);
            user.setCitycode(citycode[i]);
            user.setCustomerid(custid[i]);
            user.setProvincecode(provincecode[i]);
            user.setServiceId(serviceIds[i]);
            user.setSubsid(subsid[i]);
            user.setSystemid("1");
            user.setTenantid(tenantid[i]);
            user.setFactorcode(getRemindNum(subsid[i]));
            String s2 = StringUtils.isBlank(activetime[i])?"1900-01-01 00:00:00.000":activetime[i];
            user.setActivetime(Timestamp.valueOf(s2));
            String s3 = StringUtils.isBlank(inactivetime[i])?"1900-01-01 00:00:00.000":inactivetime[i];
            user.setInactivetime(Timestamp.valueOf(s3));
            users.add(user);
        }

        for (Iterator<UserVo> iterator = users.iterator(); iterator.hasNext();) {
            UserVo subsUser = iterator.next();
            Timestamp nowTime = DateUtils.currTimeStamp();
            //如果当前时间在用户生效时间之前或失效时间之后,则用户无效
            if (nowTime.before(subsUser.getActivetime())
                    && nowTime.after(subsUser.getInactivetime())){
                iterator.remove();
            }
        }

        if (users.isEmpty()){
            users = Collections.emptyList();
        }
        return users;
    }

    /**
     * 从用户信息扩展表中获取提醒号码
     * @param subsId
     * @return
     * @addDate 2016-03-24
     */
    public String getRemindNum(String subsId) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("SUBS_ID", subsId);
        params.put("EXT_NAME", REMIND_NUM);
        List<Map<String, String>> result = cacheClient.doQuery(USER_INFO_EXT, params);
        if (result==null || result.isEmpty()){
            throw new BusinessException("AMC-SUBS0002B","未找到用户的"+REMIND_NUM);
        }
        String[] extVals = StringUtils.split(result.get(0).get("ext_value"), "#");
        return extVals[0];
    }
}
