package com.ai.baas.ccp.topoligy.core.persistence.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcCreditfee;
import com.ai.baas.ccp.dao.mapper.bo.OmcCreditfeeCriteria;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcCreditfeeMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.persistence.dao.OmcCreditDao;
import com.ai.baas.ccp.topoligy.core.pojo.OmcCredit;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * Created by jackieliu on 16/3/22.
 */
@Component
public class OmcCreditDaoImpl implements OmcCreditDao {
    private Logger logger = LoggerFactory.getLogger(OmcCreditDaoImpl.class);

    @Autowired
    private transient OmcCreditfeeMapper omcCreditfeeMapper;

    String tableName = "omc_creditfee";

    @Override
    public List<OmcCredit> selectCredit(Connection connection, String tenantId, String ownerType,
            String ownerId, String resourceCode) throws OmcException {
        List<OmcCredit> omcCredits = new ArrayList<OmcCredit>();
            OmcCreditfeeCriteria creditfeeCriteria = new OmcCreditfeeCriteria();
            creditfeeCriteria.createCriteria().andTenantIdEqualTo(tenantId)
                    .andOwnerTypeEqualTo(ownerType).andOwnerIdEqualTo(ownerId)
                    .andResourceCodeEqualTo(resourceCode);
            List<OmcCreditfee> creditfees = omcCreditfeeMapper.selectByExample(creditfeeCriteria);
            if (CollectionUtil.isEmpty(creditfees)) {
                logger.info("信用度没取到数据【omc_creditfee】");
                return Collections.emptyList();
            }

            for (OmcCreditfee map : creditfees) {
                OmcCredit omcCredit = new OmcCredit();
                omcCredit.setOwnerid(ownerId);
                omcCredit.setOwnertype(ownerType);
                omcCredit.setTenantid(tenantId);
                String creditline = (map.getCreditLine() == null) ? "0.0" : String.valueOf(map.getCreditLine());
                omcCredit.setCreditline(Double.parseDouble(creditline));
                omcCredit.setCredittype(map.getCreditType());
                omcCredit.setEffDate(map.getEffTime());
                omcCredit.setExpDate(map.getExpTime());
                omcCredits.add(omcCredit);
            }
        return omcCredits;
    }
}
