package com.ai.baas.bmc.srv.persistence.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ai.baas.bmc.srv.entity.ResBookUpdater;
import com.ai.baas.bmc.srv.persistence.entity.ResBook;
import com.ai.opt.sdk.util.DateUtil;
import com.google.common.collect.Lists;

@Repository
public class ResBookDaoImpl implements ResBookDao {

	@Autowired
	private JdbcTemplate jdbcTemplateAmc;
	
	@Override
	public List<ResBook> queryResBookBalance(String subs_id, String price_code, String start_time) {
		StringBuilder sql = new StringBuilder();
		sql.append("select r.book_id bookId,r.tenant_id tenantId,r.owner_id ownerId,r.owner_type ownerType,");
		sql.append(" r.subject_id subjectId,r.goods_type goodsType,r.resource_type resourceType, ");
		sql.append(" r.effect_time effectTime,r.expire_time expireTime,r.total_amount totalAmount, ");
		sql.append(" r.book_status bookStatus,r.allow_present allowPresent,r.allow_convert allowConvert, ");
		sql.append(" r.allow_clear allowClear,r.source_type sourceType,r.source_id sourceId, ");
		sql.append(" r.balance balance,r.acct_month acctMonth ");
		sql.append("from amc_res_book r ");
		sql.append("where r.owner_type = '0' and r.owner_id = ? ");
		sql.append(" and r.source_type = '0' and r.source_id = ?  ");
		sql.append(" and r.effect_time <= ? and r.expire_time >= ? ");
		sql.append(" order by r.effect_time ");
		
		Object[] params = new Object[4];
		params[0] = subs_id;
		params[1] = price_code;
		Date date = DateUtil.to_date(start_time, DateUtil.YYYYMMDDHHMMSS);
		params[2] = date;
		params[3] = date;
		
//		List<ResBook> resBooks = jdbcTemplateAmc.query(sql.toString(), params, new BeanPropertyRowMapper<ResBook>(ResBook.class));
//		if (resBooks != null && resBooks.size() > 0) {
//			resBook = resBooks.get(0);
//		}

		return jdbcTemplateAmc.query(sql.toString(), params, new BeanPropertyRowMapper<ResBook>(ResBook.class));
	}

	
	
	
	@Override
	public int[] updateResBookAndLog(List<ResBookUpdater> resbookUpdaters,String account_period,String drTable,String drKey) {
		List<String> sqls = Lists.newArrayList();
		String updateDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		for(ResBookUpdater resBookUpdater:resbookUpdaters){
			sqls.add(assembleResBookSql(resBookUpdater,updateDate));
			sqls.add(assembleResBookLogSql(resBookUpdater,updateDate,account_period,drTable,drKey));
		}
		return jdbcTemplateAmc.batchUpdate(sqls.toArray(new String[sqls.size()]));
	}

	
	private String assembleResBookSql(ResBookUpdater resBookUpdater,String updateDate){
		ResBook resBook = resBookUpdater.getResBook();
		StringBuilder strSql = new StringBuilder();
		strSql.append("update amc_res_book r ");
		strSql.append("set r.use_version = r.use_version + 1, ");
		strSql.append(" r.balance = ").append(resBookUpdater.getBalance().toPlainString()).append(", ");
		strSql.append(" r.last_update_time = ").append(updateDate);
		strSql.append(" where r.book_id = ").append(resBook.getBookId());
		return strSql.toString();
	}
	
	private String assembleResBookLogSql(ResBookUpdater resBookUpdater,String updateDate,String account_period,String drTable,String drKey){
		ResBook resBook = resBookUpdater.getResBook();
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into amc_res_book_log_").append(account_period).append(" (");
		strSql.append("`BOOK_ID`,`TENANT_ID`,`OWNER_ID`,`OWNER_TYPE`,`SUBJECT_ID`,`GOODS_TYPE`,`RESOURCE_TYPE`,`CREATE_TIME`,");
		strSql.append("`EFFECT_TIME`,`EXPIRE_TIME`,`TOTAL_AMOUNT`,`TRANSFER_AMOUNT`,`DEDUCT_AMOUNT`,`OCCUPY_AMOUNT`,");
		strSql.append("`BOOK_STATUS`,`ALLOW_PRESENT`,`ALLOW_CONVERT`,`ALLOW_CLEAR`,`SOURCE_TYPE`,`SOURCE_ID`,`CHANGE_BEFORE`,`CHANGE_AMOUNT`,");
		strSql.append("`DEDUCT_TYPE`,`DEDUCT_SOURCE`,`ACCT_MONTH`,`NEW_EXPIRE_DATE`,`NEW_BOOK_STATUS`,`OPT_TYPE`,`OPT_TIME`");
		strSql.append(") VALUES (");
		strSql.append(resBook.getBookId()).append(",");
		strSql.append("'").append(resBook.getTenantId()).append("',");
		strSql.append("'").append(resBook.getOwnerId()).append("',");
		strSql.append(resBook.getOwnerType()).append(",");
		strSql.append("'").append(resBook.getSubjectId()).append("',");
		strSql.append("'").append(resBook.getGoodsType()).append("',");
		strSql.append("'").append(resBook.getResourceType()).append("',");
		strSql.append("'").append(updateDate).append("',");
		strSql.append("'").append(resBook.getEffectTime()).append("',");
		strSql.append("'").append(resBook.getExpireTime()).append("',");
		strSql.append("'").append(resBook.getTotalAmount()).append("',");
		strSql.append("0,0,0,");
		strSql.append("'").append(resBook.getBookStatus()).append("',");
		strSql.append("'").append(resBook.getAllowPresent()).append("',");
		strSql.append("'").append(resBook.getAllowConvert()).append("',");
		strSql.append("'").append(resBook.getAllowClear()).append("',");
		strSql.append("'").append(resBook.getSourceType()).append("',");
		strSql.append("'").append(resBook.getSourceId()).append("',");
		strSql.append(resBookUpdater.getChangeBefore().toPlainString()).append(",");
		strSql.append(resBookUpdater.getChangeAmount().toPlainString()).append(",");
		strSql.append("'dr',");
		strSql.append("'").append(drTable).append(":").append(drKey).append("',");
		strSql.append("'").append(resBook.getAcctMonth()).append("',");
		strSql.append("NULL,NULL,1,");
		strSql.append("'").append(updateDate).append("') ");
		return strSql.toString();
	}



	public void setJdbcTemplateAmc(JdbcTemplate jdbcTemplateAmc) {
		this.jdbcTemplateAmc = jdbcTemplateAmc;
	}
	
	
}
