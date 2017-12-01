package com.ai.baas.bmc.srv.persistence.dao;

import org.springframework.stereotype.Repository;

import com.ai.baas.bmc.srv.entity.FailureBill;

@Repository("failureBillFromHBase")
public class FailureBillFromHBaseDaoImpl implements FailureBillDao {

	
	@Override
	public void insertFailBillData(FailureBill failureBill) {
		//System.out.println("将错单数据插入hbase!");

	}

}
