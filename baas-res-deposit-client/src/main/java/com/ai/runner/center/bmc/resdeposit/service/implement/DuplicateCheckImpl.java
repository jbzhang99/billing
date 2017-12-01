package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IDuplicateCheck;
import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
import com.ai.runner.center.bmc.resdeposit.util.MyHbaseUtil;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;

@Service
public class DuplicateCheckImpl implements IDuplicateCheck {
    private static final String SPLIT = ":";
    
    /**
     * 
     * @param tableName
     */
    private void createTableIfNeed(String tableName){
    	
		try {
			Admin admin = MyHbaseUtil.getConnection().getAdmin();
			
	        if (!admin.isTableAvailable(TableName
	                .valueOf(tableName))) {
	            HTableDescriptor tableDesc = new HTableDescriptor(
	                    TableName.valueOf(tableName));
	            tableDesc.addFamily(new HColumnDescriptor("subs_id"));
	            tableDesc.addFamily(new HColumnDescriptor("product_id"));
	            tableDesc.addFamily(new HColumnDescriptor("subs_product_id"));
	            tableDesc.addFamily(new HColumnDescriptor("systemtime"));
	            admin.createTable(tableDesc);
	            LoggerUtil.log.info("create hbase table : "+tableName);
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
			LoggerUtil.log.error("", e);
		}
    }
    
    @Override
    public synchronized boolean hasRecord(FunResBook comm,String date) throws IOException {
        //判重表表名
        String tableName = "res_dup_log_" + date;
        createTableIfNeed(tableName);
        Table table = MyHbaseUtil.getConnection().getTable(
                TableName.valueOf(tableName));
        String rowKey =comm.getTENANT_ID()+ SPLIT + comm.getSUBS_ID() + SPLIT + comm.getPRODUCT_ID() + SPLIT + comm.getSUBS_PRODUCT_ID();
        Get get = new Get(Bytes.toBytes(rowKey));
        boolean result = table.exists(get);
        if(!result){
            insertResDupTable(comm, date);
        }
        return result;
    }

    @Override
    public void insertResDupTable(FunResBook comm,String date) throws IOException {
        //判重表表名
        String tableName = "res_dup_log_" + date;
        
        Table table = MyHbaseUtil.getConnection().getTable(
                TableName.valueOf(tableName));
        String rowKey = comm.getSUBS_ID() + SPLIT + comm.getPRODUCT_ID() + SPLIT + comm.getSUBS_PRODUCT_ID();
        Put put = new Put(Bytes.toBytes(rowKey));
        addColumn(put, "subs_id", comm.getSUBS_ID());
        addColumn(put, "product_id", comm.getPRODUCT_ID());
        addColumn(put, "subs_product_id", comm.getSUBS_PRODUCT_ID());
        addColumn(put, "systemtime", DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS));
        table.put(put);
    }
    
    private static void addColumn(Put put, String a, String data){
        put.addColumn(Bytes.toBytes(a), Bytes.toBytes(a), Bytes.toBytes(data));
    }
}
