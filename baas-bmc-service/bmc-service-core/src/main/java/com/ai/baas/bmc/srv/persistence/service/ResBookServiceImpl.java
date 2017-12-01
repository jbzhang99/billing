package com.ai.baas.bmc.srv.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.srv.entity.ResBookUpdater;
import com.ai.baas.bmc.srv.persistence.dao.ResBookDao;
import com.ai.baas.bmc.srv.persistence.entity.ResBook;

@Service("resBookService")
public class ResBookServiceImpl implements ResBookService {

	@Autowired
	private ResBookDao resBookDao;
	
	@Override
	public List<ResBook> queryResBookBalance(String subs_id, String price_code, String start_time) {
		// TODO Auto-generated method stub
		return resBookDao.queryResBookBalance(subs_id, price_code, start_time);
	}

	
	@Override
	@Transactional("transactionManagerAmc")
	public int[] updateResBookAndLog(List<ResBookUpdater> resbookUpdaters,String account_period,String drTable,String drKey) {
		return resBookDao.updateResBookAndLog(resbookUpdaters,account_period,drTable,drKey);
	}



	public void setResBookDao(ResBookDao resBookDao) {
		this.resBookDao = resBookDao;
	}
	
	

}
