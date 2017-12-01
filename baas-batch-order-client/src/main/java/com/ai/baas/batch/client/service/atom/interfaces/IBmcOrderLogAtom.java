package com.ai.baas.batch.client.service.atom.interfaces;

import com.ai.baas.batch.client.dao.mapper.bo.BmcOrderLogYyyymm;

public interface IBmcOrderLogAtom {

	public void insertOrderLog(String currentMonth,BmcOrderLogYyyymm bmcOrderLogYyyymm);
}
