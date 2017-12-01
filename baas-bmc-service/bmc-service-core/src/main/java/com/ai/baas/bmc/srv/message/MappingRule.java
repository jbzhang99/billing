package com.ai.baas.bmc.srv.message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.message.RecordFmt.RecordFmtKey;

@Component
public class MappingRule {
	private static Logger logger = LoggerFactory.getLogger(MappingRule.class);
    private static Map<RecordFmtKey, Map<String, Integer>> recordFmtMap = new HashMap<RecordFmtKey, Map<String, Integer>>();
	
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    @PostConstruct
    public void initMappingRule() {
    	StringBuilder recordFmtSql = new StringBuilder();
        recordFmtSql.append("select r.tenant_id tenantId,r.service_type serviceType,r.source source,");
        recordFmtSql.append("       r.format_type formatType,r.field_serial fieldSerial,r.field_name fieldName,");
        recordFmtSql.append("       r.field_code fieldCode ");
        recordFmtSql.append("from bmc_record_fmt r ");
        recordFmtSql.append("where r.format_type<=2");
        recordFmtSql.append(" order by r.field_serial ");
        List<RecordFmt> recordFmtList = jdbcTemplate.query(recordFmtSql.toString(), new RowMapper<RecordFmt>(){
			@Override
			public RecordFmt mapRow(ResultSet rs, int rowNum) throws SQLException {
				RecordFmt recordFmt = new RecordFmt();
				recordFmt.setTenantId(rs.getString("tenantId"));
				recordFmt.setServiceType(rs.getString("serviceType"));
				recordFmt.setSource(rs.getString("source"));
				recordFmt.setFormatType(rs.getString("formatType"));
				recordFmt.setFieldSerial(rs.getInt("fieldSerial"));
				recordFmt.setFieldName(rs.getString("fieldName"));
				recordFmt.setFieldCode(rs.getString("fieldCode"));
				return recordFmt;
			}
        });
        setData(recordFmtList);
        logger.debug("MappingRule资源加载完成...");
    }
    
    private void setData(List<RecordFmt> recordFmtList) {
    	if (recordFmtList == null) {
            return;
        }
    	for (RecordFmt recordFmt : recordFmtList) {
            Map<String, Integer> indexMap = recordFmtMap.get(recordFmt.getRecordFmtKey());
            if (indexMap == null) {
                indexMap = new HashMap<String, Integer>();
                recordFmtMap.put(recordFmt.getRecordFmtKey(), indexMap);
            }
            indexMap.put(recordFmt.getFieldCode(), recordFmt.getFieldSerial());
        }
        logger.debug("there is " + recordFmtMap.size() + " mapping loaded!");
    }
	
    public static Map<String, Integer> getIndexes(RecordFmtKey recordFmtKey) {
        return recordFmtMap.get(recordFmtKey);
    }
	
}
