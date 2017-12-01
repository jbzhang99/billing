package com.ai.baas.bmc.srv.persistence.dao;

import java.util.List;

import com.ai.baas.bmc.srv.entity.ResBookUpdater;
import com.ai.baas.bmc.srv.persistence.entity.ResBook;

public interface ResBookDao {

	List<ResBook> queryResBookBalance(String subs_id,String price_code,String start_time);
	
	int[] updateResBookAndLog(List<ResBookUpdater> resbookUpdaters,String account_period,String drTable,String drKey);
	
}
