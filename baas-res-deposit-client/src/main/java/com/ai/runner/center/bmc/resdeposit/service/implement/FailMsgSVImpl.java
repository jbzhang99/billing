package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.runner.center.bmc.resdeposit.dao.interfaces.FailMsgLogMapper;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLog;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLogCriteria;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLogCriteria.Criteria;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFailMsgSV;
import com.ai.runner.center.bmc.resdeposit.vo.PageInfo;

/**
 * Date: 2016年5月4日 <br>
 * 
 * @author zhoushanbin Copyright (c) 2016 asiainfo.com <br>
 */
@Service
@Transactional
public class FailMsgSVImpl implements IFailMsgSV {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public PageInfo<FailMsgLog> query(String systemId, String tenantId,
			String monthDate, String status,PageInfo<FailMsgLog> page) {
		
		page.setTotalCount(countByFilter(systemId, tenantId, monthDate,status));
		
		FailMsgLogCriteria example = new FailMsgLogCriteria();
		
		if(page.getSize() != 0){
			example.setLimitStart(page.getCurrPosInDb());
			example.setLimitEnd(page.getSize());
		}
		Criteria cr = example.createCriteria();
		
		cr.andDateEqualTo(monthDate);
		cr.andSystemIdEqualTo(systemId);
		cr.andTenantIdEqualTo(tenantId);
		cr.andStatusEqualTo(status);
		
		List<FailMsgLog> list= sqlSessionTemplate.getMapper(FailMsgLogMapper.class).selectByExample(
				example);
		
		page.setList(list);
		
		return page;

	}

	@Override
	public int countByFilter(String systemId, String tenantId, String monthDate,String status) {

		FailMsgLogCriteria example = new FailMsgLogCriteria();
		
		Criteria cr = example.createCriteria();
		cr.andDateEqualTo(monthDate);
		cr.andSystemIdEqualTo(systemId);
		cr.andTenantIdEqualTo(tenantId);
		cr.andStatusEqualTo(status);
		
		return sqlSessionTemplate.getMapper(FailMsgLogMapper.class).countByExample(
				example);

	}

	@Override
	public void insert(FailMsgLog log) {
		sqlSessionTemplate.getMapper(FailMsgLogMapper.class).insert(log);
	}

	@Override
	public void updateByPrimaryKey(FailMsgLog log) {
		sqlSessionTemplate.getMapper(FailMsgLogMapper.class).updateByPrimaryKey(log);
	}


}
