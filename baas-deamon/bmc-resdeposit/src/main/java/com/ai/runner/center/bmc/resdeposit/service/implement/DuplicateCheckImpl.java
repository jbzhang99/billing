package com.ai.runner.center.bmc.resdeposit.service.implement;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import com.ai.runner.center.bmc.resdeposit.service.interfaces.IDuplicateCheck;
import com.ai.runner.center.bmc.resdeposit.util.MyHbaseUtil;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.utils.util.DateUtil;

@Service
public class DuplicateCheckImpl implements IDuplicateCheck {
    private static final String SPLIT = ":";
    
    @Override
    public synchronized boolean hasRecord(FunResBook comm,String date) throws IOException {
        //判重表表名
        String tableName = "res_dup_log_" + date;
        
        Table table = MyHbaseUtil.getConnection().getTable(
                TableName.valueOf(tableName));
        String rowKey = comm.getSUBS_ID() + SPLIT + comm.getPRODUCT_ID() + SPLIT + comm.getSUBS_PRODUCT_ID();
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
