package com.ai.baas.abm.service.business.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.abm.api.account.params.AmcResBookVo;
import com.ai.baas.abm.constants.ExceptCodeConstants;
import com.ai.baas.abm.dao.mapper.bo.AmcResBook;
import com.ai.baas.abm.dao.mapper.bo.AmcResBookBak;
import com.ai.baas.abm.dao.mapper.bo.AmcResBookCriteria;
import com.ai.baas.abm.dao.mapper.bo.AmcResBookLog;
import com.ai.baas.abm.dao.mapper.factory.MapperFactory;
import com.ai.baas.abm.service.business.interfaces.IAccountRecordBusiSV;
import com.ai.baas.abm.util.AmcSeqUtil;
import com.ai.baas.abm.util.DateUtils;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;

import net.sf.json.JSONArray;

@Service
@Transactional
public class AccountRecordBusiSVImpl implements IAccountRecordBusiSV {

	public static final Logger LOGGER = LoggerFactory.getLogger(AccountRecordBusiSVImpl.class);
	
	@Override
	public BaseResponse saveAccountRecord(AmcResBookVo bookVo) throws BusinessException {
		// TODO Auto-generated method stub
		
		BaseResponse result = new BaseResponse();
		AmcResBook record = new AmcResBook();
		record.setBookId(AmcSeqUtil.createResBookId());
		record.setTenantId(bookVo.getTenantId());
		record.setOwnerId(bookVo.getOwnerId());
		record.setOwnerType(bookVo.getOwnerType());
		record.setSubjectId("000000");
		record.setGoodsType(bookVo.getProductType());
		record.setResourceType(bookVo.getResourceType());
		record.setCreateTime(DateUtils.currTimeStamp());
		record.setEffectTime(DateUtils.getTimestamp(bookVo.getEffectDate(), DateUtils.DATETIME_FORMAT));
		record.setExpireTime(DateUtils.getTimestamp(bookVo.getExpireDate(), DateUtils.DATETIME_FORMAT));
		record.setTotalAmount(new BigDecimal(bookVo.getTotalAmount()));
		record.setDeductAmount(new BigDecimal(0));
		record.setPresentAmount(new BigDecimal(0));
		record.setExchangeAmount(new BigDecimal(0));
		record.setBookStatus("1");
		record.setAllowPresent(bookVo.getAllowPresent());
		record.setAllowConvert(bookVo.getAllowConvert());
		record.setAllowClear(bookVo.getAllowClear());
		record.setSourceType(bookVo.getSourceType());
		record.setSourceId(String.valueOf(bookVo.getSourceId()));
		record.setBalance(new BigDecimal(bookVo.getTotalAmount()));
		record.setAcctMonth(bookVo.getAccountPeriod());
		record.setUseVersion(Long.valueOf(bookVo.getUseFlag()));
		record.setLastUpdateTime(DateUtils.currTimeStamp());
		if(MapperFactory.getAmcResBookMapper().insert(record)<1){
			throw new BusinessException("BaaS-000001", "插入账单表失败");
		}else{
			
			AmcResBookLog bookLog = new AmcResBookLog();
				bookLog.setBookId(record.getBookId());
				bookLog.setTenantId(record.getTenantId());
				bookLog.setOwnerId(String.valueOf(record.getOwnerId()));
				bookLog.setOwnerType(record.getOwnerType());
				bookLog.setSubjectId(record.getSubjectId());
				bookLog.setGoodsType(record.getGoodsType());
				bookLog.setResourceType(record.getResourceType());
				bookLog.setCreateTime(record.getCreateTime());
				bookLog.setEffectTime(record.getEffectTime());
				bookLog.setExpireTime(record.getExpireTime());
				bookLog.setTotalAmount(record.getTotalAmount());
				bookLog.setTransferAmount(new BigDecimal(0));
//				bookLog.setDeductType(deductType);   null
//				bookLog.setDeductSource(deductSource);    null
				bookLog.setDeductAmount(record.getDeductAmount());
				bookLog.setBookStatus(record.getBookStatus());
				bookLog.setAllowPresent(record.getAllowPresent());
				bookLog.setAllowConvert(record.getAllowConvert());
				bookLog.setAllowClear(record.getAllowClear());
				bookLog.setSourceType(record.getSourceType());
				bookLog.setSourceId(record.getSourceId());
				bookLog.setAcctMonth(record.getAcctMonth());
				bookLog.setChangeAmount(record.getTotalAmount());
				bookLog.setNewExpireDate(record.getExpireTime());
				bookLog.setNewBookStatus(record.getBookStatus());
				bookLog.setOptTime(DateUtils.currTimeStamp());
				bookLog.setOptType(0);
				
				String tabName = "amc_res_book_log_"+bookVo.getAccountPeriod();
				if(MapperFactory.getAmcResBookLogMapper().getTableNum(tabName) == 0){
					MapperFactory.getAmcResBookLogMapper().createResBookLogTable(tabName);
				}
				if(MapperFactory.getAmcResBookLogMapper().insert(bookLog, bookVo.getAccountPeriod())<1){
					throw new BusinessException("BaaS-000001", "插入日志表失败");
				}
		}
		result.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return result;
	}

	@Override
	public BaseResponse clearExpireAccountRecord() throws BusinessException {
		// TODO Auto-generated method stub
		
		BaseResponse result = new BaseResponse();
		String tableNamePrefix = "amc_res_books_bak";
		List<String> exitTables = new ArrayList<>();
//		Connection conn = sqlSessionTemplate.getConnection();
		int i = 0;
		while(true){
			//查询失效数据数据
			AmcResBookCriteria example = new AmcResBookCriteria();
			example.setLimitStart(i);
			example.setLimitEnd(50);
			example.setOrderByClause("LAST_UPDATE_TIME desc");
			AmcResBookCriteria.Criteria criteria = example.createCriteria();
			criteria.andExpireTimeLessThanOrEqualTo(DateUtils.getTimeByMouth(3));
			criteria.andBookStatusEqualTo("0");
			List<AmcResBook> amcResBooks = MapperFactory.getAmcResBookMapper().selectByExample(example);
			
			//创建表并插入数据
			if(amcResBooks!=null && amcResBooks.size()>0){
				for(AmcResBook amcResBook:amcResBooks){
					String tableName = tableNamePrefix + "_" + new SimpleDateFormat("yyyyMM").format(amcResBook.getExpireTime());
					boolean doInsert = false;
					if(exitTables.contains(tableName)){
						doInsert = true;
					}else if(MapperFactory.getAmcResBookMapper().getTableNum(tableName)>0){
						exitTables.add(tableName);
						doInsert = true;
					}else{ 
						try {
							//创建表
							if(MapperFactory.getAmcResBookMapper().createResBookBakTable(tableName)==0){  
								exitTables.add(tableName);
								doInsert = true;
							}
						} catch (Exception e) {
							LOGGER.error("创建表失败：", e);
						}
					}
					
					//插入数据
					if(doInsert){
						AmcResBookBak resBookBak = new AmcResBookBak();
						BeanUtils.copyProperties(resBookBak, amcResBook);
						resBookBak.setOpTime(DateUtils.currTimeStamp());
						if(MapperFactory.getAmcResBookMapper().insertResBookBak(resBookBak, tableName)<1){
							LOGGER.error("插入失败数据：", JSONArray.fromObject(resBookBak).toString());
						}else{
							MapperFactory.getAmcResBookMapper().deleteByPrimaryKey(amcResBook.getBookId());
						}
					}
				}
			}
			
		   //判断是否读取完整张表 break
           if(amcResBooks.size() < 49){
               int num=i+amcResBooks.size()+1;
               LOGGER.info("总共传入"+num+"条用户信息");
               break;
           }
           i = i + 50;
		}
		result.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return result;
	}

}
