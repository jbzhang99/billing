package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.CpPackageInfo;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.ICpPackageInfoAtom;
@Component("cpPackageInfoAtom")
public class CpPackageInfoAtomImpl implements ICpPackageInfoAtom{

	@Override
	public void insert(CpPackageInfo cpPackageInfo) {
		MapperFactory.getCpPackageInfoMapper().insert(cpPackageInfo);
	}

}
