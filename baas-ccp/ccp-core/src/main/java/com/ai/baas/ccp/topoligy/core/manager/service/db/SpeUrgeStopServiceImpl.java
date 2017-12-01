package com.ai.baas.ccp.topoligy.core.manager.service.db;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcAvoidscout;
import com.ai.baas.ccp.dao.mapper.bo.OmcAvoidscoutCriteria;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcAvoidscoutMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.SpeUrgeStopService;
import com.ai.baas.ccp.topoligy.core.pojo.SpeUrgeStop;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;

@Component
public class SpeUrgeStopServiceImpl implements SpeUrgeStopService {

    private static final Logger logger = LoggerFactory.getLogger(SpeUrgeStopServiceImpl.class);

    @Autowired
    private transient OmcAvoidscoutMapper omcAvoidscoutMapper;

    @Override
    public SpeUrgeStop selectById(String tenantid, String ownertype, String ownerid)
            throws OmcException {
        SpeUrgeStop speUrgeStop = null;
        String ownerType1 = ownertype;

        if (StringUtils.startsWith(ownertype, "/")) {
            ownerType1 = StringUtils.substring(ownertype, 1);
        }

        Timestamp sysdate = DateUtil.getSysDate();
        OmcAvoidscoutCriteria omcAvoidscoutCriteria = new OmcAvoidscoutCriteria();
        omcAvoidscoutCriteria.createCriteria().andOwnerTypeEqualTo(ownerType1)
                .andOwnerIdEqualTo(ownerid).andTenantIdEqualTo(tenantid)
                .andEffDateLessThanOrEqualTo(sysdate).andExpDateGreaterThanOrEqualTo(sysdate)
                .andSpeTypeNotEqualTo("REDLIST");
        List<OmcAvoidscout> omcAvoidscouts = omcAvoidscoutMapper
                .selectByExample(omcAvoidscoutCriteria);
        if (CollectionUtil.isEmpty(omcAvoidscouts)) {
            logger.info("免催停没获得数据【omc_avoidscout】");
            return speUrgeStop;
        }
        OmcAvoidscout map = omcAvoidscouts.get(0);
        speUrgeStop = new SpeUrgeStop();
        speUrgeStop.setOwnerid(ownerid);
        speUrgeStop.setOwnertype(ownertype);
        speUrgeStop.setTenantid(tenantid);
        speUrgeStop.setSpeType(map.getSpeType());
        speUrgeStop.setEffDate(map.getEffDate());
        speUrgeStop.setExpDate(map.getExpDate());
        return speUrgeStop;
    }
}
