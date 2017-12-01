package com.ai.baas.ccp.topoligy.core.persistence.dao.impl;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatusCriteria;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatusYyyymm;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcUrgeStatusMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcUrgeStatusYyyymmMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcUrgeStatusDao;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
import com.ai.opt.sdk.util.BeanUtils;

/**
 * 催缴状态操作
 */
@Component
public final class OmcUrgeStatusDaoImpl implements OmcUrgeStatusDao {
    @Autowired
    private OmcUrgeStatusMapper omcUrgeStatusMapper;

    @Autowired
    private OmcUrgeStatusYyyymmMapper omcUrgeStatusYyyymmMapper;

    @Override
    public int insert(Connection connection, OmcUrgeStatus record) throws OmcException {
        omcUrgeStatusMapper.insertSelective(record);
        return movetobackup(connection, record);
    }

    @Override
    public OmcUrgeStatus selectByparam(Connection connection, Map<String, String> param)
            throws OmcException {
        StringBuilder sql = new StringBuilder();
        String tablename = "omc_urge_status";
        sql.append("select  ");
        sql.append(" urge_serial, owner_type, owner_id, urge_type, business_code, status, last_status, status_time, notify_time,");
        sql.append("notify_times, notify_status, notify_type, scout_info, system_id, tenant_id ");
        sql.append(" from ");
        sql.append(tablename);
        sql.append(" where tenant_id = ? and owner_type = ? and owner_id = ? and business_code = ? and urge_type = ?");
        Object[] params = new Object[5];

        params[0] = param.get("tenant_id");
        params[1] = param.get("owner_type");
        params[2] = param.get("owner_id");
        params[3] = param.get("business_code");
        params[4] = param.get("urge_type");
        try {
            List<Map<String, Object>> retMap = null;
            // List<Map<String, Object>> retMap = JdbcTemplate.query(connection,sql.toString(), new
            // MapListHandler(),params);
            if ((retMap == null) || (retMap.isEmpty())) {
                return null;
            }

            OmcUrgeStatus omcUrgeStatus = new OmcUrgeStatus();
            for (Iterator<Map<String, Object>> iterator = retMap.iterator(); iterator.hasNext();) {
                Map<String, Object> map = (Map<String, Object>) iterator.next();

                omcUrgeStatus.setBusinessCode(map.get("business_code").toString());
                omcUrgeStatus.setLastStatus(map.get("last_status").toString());
                omcUrgeStatus.setNotifyStatus(map.get("notify_status").toString());
                omcUrgeStatus.setNotifyType(map.get("notify_type").toString());
                omcUrgeStatus.setOwnerId(map.get("owner_id").toString());
                omcUrgeStatus.setOwnerType(map.get("owner_type").toString());
                omcUrgeStatus.setScoutInfo(map.get("scout_info").toString());
                omcUrgeStatus.setStatus(map.get("status").toString());
                omcUrgeStatus.setSystemId(map.get("system_id").toString());
                omcUrgeStatus.setTenantId(map.get("tenant_id").toString());
                omcUrgeStatus.setUrgeType(map.get("urge_type").toString());
                omcUrgeStatus.setNotifyTime(DateUtils.getTimestamp(map.get("notify_time")
                        .toString(), "yyyy-MM-dd HH:mm:ss"));
                omcUrgeStatus.setStatusTime(DateUtils.getTimestamp(map.get("status_time")
                        .toString(), "yyyy-MM-dd HH:mm:ss"));

                String notifytimes = (map.get("notify_times") == null) ? "0" : map.get(
                        "notify_times").toString();
                String ScoSeq = (map.get("urge_serial") == null) ? "0" : map.get("urge_serial")
                        .toString();

                omcUrgeStatus.setUrgeSerial(Long.parseLong(ScoSeq));
                omcUrgeStatus.setNotifyTimes(Integer.parseInt(notifytimes));
                break;
            }

            return omcUrgeStatus;

        } catch (Exception e) {
            throw new OmcException("获取预警状态异常", sql.toString() + Arrays.toString(params), e);
        }
    }

    @Override
    public int update(Connection connection, OmcUrgeStatus record) throws OmcException {
        OmcUrgeStatusCriteria criteria = new OmcUrgeStatusCriteria();
        criteria.createCriteria().andUrgeSerialEqualTo(record.getUrgeSerial());
        omcUrgeStatusMapper.updateByExampleSelective(record, criteria);

        return movetobackup(connection, record);
    }

    private int movetobackup(Connection connection, OmcUrgeStatus record) throws OmcException {
        String currmonth = DateUtils.getCurrMonth();
        OmcUrgeStatusYyyymm omcUrgeStatusYyyymm = new OmcUrgeStatusYyyymm();
        BeanUtils.copyVO(omcUrgeStatusYyyymm, record);
        omcUrgeStatusYyyymm.setYyyymm(currmonth);
        return omcUrgeStatusYyyymmMapper.insertSelective(omcUrgeStatusYyyymm);

    }
}
