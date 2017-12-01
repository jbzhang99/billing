package com.ai.baas.bmc.service.business.productInfo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.dao.interfaces.CpFactorInfoMapper;
import com.ai.baas.bmc.dao.mapper.bo.CpFactorInfo;
import com.ai.baas.bmc.dao.mapper.bo.CpFactorInfoCriteria;
import com.ai.baas.bmc.service.business.productInfo.interfaces.ICpFactorInfoSvc;

@Service
@Transactional
public class CpFactorInfoSvcImpl implements ICpFactorInfoSvc {
	private static final Log log = LogFactory.getLog(CpFactorInfoSvcImpl.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String InsertCpFactorInfo(CpFactorInfo record) {
	    log.info("进入CpFactorInfoSvc.triggerCpFactorInfo方法,打印入参json = ["
        + record + "]");
	    
	    CpFactorInfoMapper aCpFactorInfoMapper = sqlSessionTemplate
                .getMapper(CpFactorInfoMapper.class);
        aCpFactorInfoMapper.insert(record);
        return "CTP-000000";
	}

	@Override
	public String triggerCpFactorInfo(CpFactorInfo record) {
		log.info("进入CpFactorInfoSvc.triggerCpFactorInfo方法,打印入参json = ["
				+ record + "]");

		try {
			CpFactorInfoMapper aCpFactorInfoMapper = sqlSessionTemplate
					.getMapper(CpFactorInfoMapper.class);

			CpFactorInfoCriteria cpFactorInfoCriteria = new CpFactorInfoCriteria();

			cpFactorInfoCriteria.createCriteria()
					.andFactorCodeEqualTo(record.getFactorCode())
					.andFactorNameEqualTo(record.getFactorName())
					//.andFactorOwnerEqualTo(record.getFactorOwner())
					.andFactorValueEqualTo(record.getFactorValue())
					.andTenantIdEqualTo(record.getTenantId());
			// 先删除再插入
			aCpFactorInfoMapper.deleteByExample(cpFactorInfoCriteria);
			aCpFactorInfoMapper.insert(record);

		} catch (Exception e) {
			log.error("出现异常:", e);
			return "401";
		}

		return "CTP-000000";

	}

	@Override
	public String factorInfoDEL(CpFactorInfo record) {
		CpFactorInfoMapper aCpFactorInfoMapper = sqlSessionTemplate
				.getMapper(CpFactorInfoMapper.class);

		CpFactorInfoCriteria cpFactorInfoCriteria = new CpFactorInfoCriteria();

		cpFactorInfoCriteria.createCriteria()
				.andFactorCodeEqualTo(record.getFactorCode())
				.andFactorNameEqualTo(record.getFactorName())
				//.andFactorOwnerEqualTo(record.getFactorOwner())
				.andFactorValueEqualTo(record.getFactorValue())
				.andSystemIdEqualTo(record.getSystemId())
				.andTenantIdEqualTo(record.getTenantId());

		aCpFactorInfoMapper.deleteByExample(cpFactorInfoCriteria);

		return "CTP-000000";
	}

}
