package com.ai.baas.dshm.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.baas.dshm.api.dshmprocess.params.FieldInfo;
import com.ai.baas.dshm.api.dshmprocess.params.FieldQueryRequest;
import com.ai.baas.dshm.api.dshmprocess.params.ShmTableRecordVo;
import com.ai.baas.dshm.dto.EbillingShmTableDb;
import com.ai.opt.base.exception.BusinessException;

public interface SaasMshmSv {
    
    /**
     * 获得ebilling_shm_table_db表的数据库的信息
     * 
     */
    List<EbillingShmTableDb> ListDbName();
	
	/**
	 * 根据数据库的连接信息获得数据库中表名列表
	 * 
	 */
	List<String> ListTableName(String params);
	/**
	 * 根据表名获得字段信息
	 *
	 */
	List<String> ListFieldName(String dbConnect,String tableName);

	/**
	 * 根据表名获得字段信息
	 *
	 */
	List<FieldInfo> ListFieldInfo(String dbConnect, String tableName);
	
	/**
	 * 将新增的表及字段插入到ebilling_shm_table_record表中
	 * 
	 */
	String add(String dbConnect,String tableName, List<ShmTableRecordVo> names,List<String> isIndexKeysList);

	/**
	 * 删除info表和record表中的某个表的信息，同时删除缓存中的信息
	 *
	 */
	String deleteTable(Map<String,String> tableInfos);
	/**
	 * 查询添加的表字段
	 */
	 List<String> getTableFieldRecord(FieldQueryRequest request) throws BusinessException; 
}
