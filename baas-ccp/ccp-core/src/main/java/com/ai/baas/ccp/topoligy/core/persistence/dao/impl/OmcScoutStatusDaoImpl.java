package com.ai.baas.ccp.topoligy.core.persistence.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatusCriteria;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatusYyyymm;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutStatusMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutStatusYyyymmMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcScoutStatusDao;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * 信控状态操作
 */
@Component
public final class OmcScoutStatusDaoImpl implements OmcScoutStatusDao {

    @Autowired
    private transient OmcScoutStatusMapper omcScoutStatusMapper;

    @Autowired
    private transient OmcScoutStatusYyyymmMapper omcScoutStatusYyyymmMapper;
    
    @Override
    public int insert(Connection connection, OmcScoutStatus record) throws OmcException {
        omcScoutStatusMapper.insertSelective(record);
        return movetobackup(connection, record);
    }

    /**
     * 根据用户id,租户id,业务类型查询信控状态
     * 
     * @param connection
     * @param param
     * @return
     * @throws OmcException
     */
    @Override
    public OmcScoutStatus selectByparam(Connection connection, Map<String, String> param)
            throws OmcException {

        OmcScoutStatusCriteria omcScoutStatusCriteria = new OmcScoutStatusCriteria();
        omcScoutStatusCriteria.createCriteria().andSubsIdEqualTo(param.get("subs_id"))
                .andTenantIdEqualTo(param.get("tenant_id"))
                .andBusinessCodeEqualTo(param.get("business_code"));
        List<OmcScoutStatus> omcScoutStatus = omcScoutStatusMapper.selectByExample(omcScoutStatusCriteria);
        if (CollectionUtil.isEmpty(omcScoutStatus)) {
            return null;
        }

        return omcScoutStatus.get(0);

    }

    @Override
    public int update(Connection connection, OmcScoutStatus record) throws OmcException {
        OmcScoutStatusCriteria criteria = new OmcScoutStatusCriteria();
        criteria.createCriteria().andScoSeqEqualTo(record.getScoSeq());
        omcScoutStatusMapper.updateByExampleSelective(record, criteria);
                return movetobackup(connection, record);
    }

    private int movetobackup(Connection connection, OmcScoutStatus record) throws OmcException {
        String currmonth = DateUtils.getCurrMonth();
        OmcScoutStatusYyyymm omcScoutStatusYyyymm = new OmcScoutStatusYyyymm();
        BeanUtils.copyVO(omcScoutStatusYyyymm, record);
        omcScoutStatusYyyymm.setYyyymm(currmonth);
        return omcScoutStatusYyyymmMapper.insertSelective(omcScoutStatusYyyymm);
    }

}
