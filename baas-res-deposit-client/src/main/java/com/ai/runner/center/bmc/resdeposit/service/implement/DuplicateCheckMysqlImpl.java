package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.runner.center.bmc.resdeposit.dao.interfaces.FailMsgLogMapper;
import com.ai.runner.center.bmc.resdeposit.dao.interfaces.ResDupLogMapper;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.ResDupLog;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.ResDupLogCriteria;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IDuplicateCheckMysql;
import com.ai.runner.center.bmc.resdeposit.util.BmcSeqUtil;
import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
import com.ai.runner.center.bmc.resdeposit.util.MyHbaseUtil;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;

@Service
@Transactional
public class DuplicateCheckMysqlImpl implements IDuplicateCheckMysql {
    
    @Autowired
	private SqlSessionTemplate sqlSessionTemplate;
    
    @Override
    public synchronized boolean hasRecord(FunResBook comm,String date) throws IOException {
        //判重表表名
        String tableName = "res_dup_log_" + date;
        ResDupLogMapper dupLogMapper = sqlSessionTemplate.getMapper(ResDupLogMapper.class);
        boolean result = false;
        if(dupLogMapper.getTableNum(tableName)==0){
        	//创建表
        	dupLogMapper.createResDupLogTable(tableName);
        	 ResDupLog record = new ResDupLog();
             record.setId(Integer.valueOf(BmcSeqUtil.getResDupLogId()));
             record.setSubsId(comm.getSUBS_ID());
             record.setProductId(comm.getPRODUCT_ID());
             record.setSubsProductId(comm.getSUBS_PRODUCT_ID());
             record.setSystemtime(DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS));
             dupLogMapper.insertResDupLog(record, tableName);
        }else{
        	ResDupLogCriteria sql = new ResDupLogCriteria();
        	ResDupLogCriteria.Criteria criteria = sql.createCriteria();
        	criteria.andSubsIdEqualTo(comm.getSUBS_ID());
        	criteria.andProductIdEqualTo(comm.getPRODUCT_ID());
        	criteria.andSubsProductIdEqualTo(comm.getSUBS_PRODUCT_ID());
        	List<ResDupLog> dupLogs = dupLogMapper.selectResDupLogByExample(sql, tableName);
        	if(dupLogs==null || dupLogs.size()==0){
        		 ResDupLog record = new ResDupLog();
                 record.setId(Integer.valueOf(BmcSeqUtil.getResDupLogId()));
                 record.setSubsId(comm.getSUBS_ID());
                 record.setProductId(comm.getPRODUCT_ID());
                 record.setSubsProductId(comm.getSUBS_PRODUCT_ID());
                 record.setSystemtime(DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS));
                 dupLogMapper.insertResDupLog(record, tableName);
        	}else{
        		result = true;
        	}
        }
        return result;
    }

    @Override
    public void insertResDupTable(FunResBook comm,String date) throws IOException {
        //判重表表名
        String tableName = "res_dup_log_" + date;
        ResDupLogMapper dupLogMapper = sqlSessionTemplate.getMapper(ResDupLogMapper.class);
        
        if(dupLogMapper.getTableNum(tableName)==0){
        	//创建表
        	dupLogMapper.createResDupLogTable(tableName);
        }
        ResDupLog record = new ResDupLog();
        record.setId(Integer.valueOf(BmcSeqUtil.getResDupLogId()));
        record.setSubsId(comm.getSUBS_ID());
        record.setProductId(comm.getPRODUCT_ID());
        record.setSubsProductId(comm.getSUBS_PRODUCT_ID());
        record.setSystemtime(DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS));
        dupLogMapper.insertResDupLog(record, tableName);
    }
}
