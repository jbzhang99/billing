package com.ai.baas.mt.web.model;

import java.util.List;

/**
 * Created by wangyongxin on 2016/4/1.
 */
public class AddTableInfo {
    private String dbName;
    private String tableName;
    private String indexKeys;
    private String cacheFields;
    private String primaryKeys;

    public String getIndexKeys() {
        return indexKeys;
    }

    public void setIndexKeys(String indexKeys) {
        this.indexKeys = indexKeys;
    }

    public String getCacheFields() {
        return cacheFields;
    }

    public void setCacheFields(String cacheFields) {
        this.cacheFields = cacheFields;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

	public String getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(String primaryKeys) {
		this.primaryKeys = primaryKeys;
	}
}
