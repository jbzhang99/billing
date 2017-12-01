package com.ai.baas.cust.service.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.baas.cust.constants.BmcConstants;
import com.ai.baas.cust.service.business.interfaces.IGetReProcessClassSV;
import com.ai.baas.cust.util.LoggerUtil;
import com.ai.baas.cust.util.MyHbaseUtil;
import com.google.common.collect.Lists;



public class GetDataDetail {	
    private static final Logger LOGGER = LogManager.getLogger(GetDataDetail.class);
//	@Autowired
	private IGetReProcessClassSV reBillingProcess;
	private String tenantId;
	private String serviceType;
//	private String Acct;
	private StringBuilder tableName=new StringBuilder();
	private StringBuilder delTableName= new StringBuilder();
	private byte[] detailBillFamilyName = "detail_bill".getBytes();

	IDeductProcessor deductProcessor;
	public GetDataDetail(String tenantId,String serviceType,String Acct, IGetReProcessClassSV reBillingProcess){
		this.tenantId=tenantId;
		this.serviceType=serviceType;
//		this.Acct=Acct;
		this.reBillingProcess=reBillingProcess;
		tableName.append(tenantId).append(BmcConstants.SEQ.BMC_BAK_HTABLE_SPLIT).append(serviceType)
		.append(BmcConstants.SEQ.BMC_BAK_HTABLE_SPLIT).append("RBK").append(BmcConstants.SEQ.BMC_BAK_HTABLE_SPLIT).append(Acct);
		delTableName.append(tenantId).append(BmcConstants.SEQ.BMC_BAK_HTABLE_SPLIT).append(serviceType)
		.append(BmcConstants.SEQ.BMC_BAK_HTABLE_SPLIT).append("DR").append(BmcConstants.SEQ.BMC_BAK_HTABLE_SPLIT).append(Acct);
		createHtable(tableName.toString());
	}
	public Map<String, Object> resultToMap(ResultScanner resultScan){
		Map<String, Object> drMap=new HashMap<String, Object>();
		for(Result result:resultScan){
			String rowKey=result.getRow().toString();
			LOGGER.info("the real rowkey is "+rowKey);
			Map<String, String> dataMap=new HashMap<String,String>();
			for(Cell r:result.rawCells()){
				dataMap.put(CellUtil.cloneQualifier(r).toString(),CellUtil.cloneValue(r).toString());
			}
			//将map转换成json
			Object data=JSONObject.fromObject(dataMap);
			drMap.put(rowKey, data);
		}
		return drMap;
	}
	private void createHtable(String hbaseName){
		if(!"".equals(hbaseName)&&hbaseName!=null){
			try{
				Admin admin = MyHbaseUtil.getConnection().getAdmin();
				if (!admin.isTableAvailable(TableName.valueOf(hbaseName))) {
					HTableDescriptor tableDesc = new HTableDescriptor(
							TableName.valueOf(hbaseName));
					tableDesc.addFamily(new HColumnDescriptor(detailBillFamilyName));
					admin.createTable(tableDesc);
					LoggerUtil.log.debug("Create table ["+hbaseName+"] ok!");
				}
			}catch(IOException e){
				LOGGER.error(e);
			}
		}else{
			LoggerUtil.log.error("table name ["+hbaseName+"] is empty!");
		}
		
	}
	
	public Boolean sendToHbase(Map<String, Object> hbaseData) throws Exception{
		List<Put> batchPut=Lists.newArrayList();
		int count=0;
		Map<String,String> dataMap= null;
		List list = new ArrayList(); 
		//getProcessorByRoute processRoute=new getProcessorByRoute(jdbcTemplate);
		//GetReProcessClassImpl reBillingProcess=new GetReProcessClassImpl();
		deductProcessor=reBillingProcess.getProcessor(tenantId, serviceType);
		try{
			for(Entry<String, Object> entry:hbaseData.entrySet()){
				count++;
				String rowKey=entry.getKey();
				dataMap=JSONObject.fromObject(entry.getValue());
				deductProcessor.sendToMds(dataMap);
				Put put=new Put(rowKey.getBytes());  
		        Delete del = new Delete(rowKey.getBytes());    
		        list.add(del);  
				for(Entry<String,String> putEntry:dataMap.entrySet()){
					byte[] column=Bytes.toBytes(putEntry.getKey());
					String value=putEntry.getValue();
					put.addColumn(detailBillFamilyName, column, Bytes.toBytes(value==null ? "":value));
				}
				batchPut.add(put);
				if(count%500==0){
					doTable(tableName.toString(), batchPut, delTableName.toString(), list);
					batchPut.clear();
					list.clear();
				}
			}
			doTable(tableName.toString(), batchPut, delTableName.toString(), list);
			batchPut.clear();
			list.clear();
			return true;
		}catch(IOException e){
			LOGGER.error(e);
			return false;
		}
	}
	
	private void doTable(String insertTableName,List<Put> batchPut,String delTableName,List list) throws IOException{
		Table insertTable=MyHbaseUtil.getTable(insertTableName.toString());
		insertTable.put(batchPut);
		//进行批量的删除；
		Table delTable=MyHbaseUtil.getTable(delTableName.toString());
		delTable.delete(list);
		insertTable.close();
	}

}
