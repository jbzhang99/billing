package com.ai.baas.batch.client.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.batch.client.dao.mapper.bo.BlUserinfoZxCriteria;
import com.ai.baas.batch.client.dao.mapper.factory.MapperFactory;
import com.ai.baas.batch.client.service.atom.interfaces.IBlUserinfoZxAtom;
import com.alibaba.fastjson.JSON;
@Component("blUserinfoZxAtom")
public class BluserinfoZxAtomImpl implements IBlUserinfoZxAtom{

	@Override
	public void insert(BlUserinfoZx blUserinfoZx) {
		MapperFactory.getBlUserinfoZxMapper().insert(blUserinfoZx);
	}
	
	@Override
	public int getCountQuery(String instance_id){
		BlUserinfoZxCriteria blUserinfoZxCriteria = new BlUserinfoZxCriteria();
		BlUserinfoZxCriteria.Criteria criteria = blUserinfoZxCriteria.createCriteria();
		criteria.andInstanceIdEqualTo(instance_id);
		System.out.println(JSON.toJSONString(MapperFactory.getBlUserinfoZxMapper().selectByExample(blUserinfoZxCriteria)));
		System.out.println("size:::::"+JSON.toJSONString(MapperFactory.getBlUserinfoZxMapper().selectByExample(blUserinfoZxCriteria).size()));
		return MapperFactory.getBlUserinfoZxMapper().selectByExample(blUserinfoZxCriteria).size();
	}

}
