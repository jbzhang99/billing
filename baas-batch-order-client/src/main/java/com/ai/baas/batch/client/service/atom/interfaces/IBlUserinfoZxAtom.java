package com.ai.baas.batch.client.service.atom.interfaces;

import com.ai.baas.batch.client.dao.mapper.bo.BlUserinfoZx;

public interface IBlUserinfoZxAtom {

	public void insert(BlUserinfoZx blUserinfoZx);
	
	public int getCountQuery(String instance_id);
}
