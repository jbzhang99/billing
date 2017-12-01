package com.ai.baas.ccp.topoligy.db;

import java.util.Map;

import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

public final class DbManager {
    private static final Object DB_MYSQL = "mysql";
    private static final Object DB_HBASE = "hbase";
    private static String dbType = null;
    private DbManager() {
    }

    private static DbManager dbManager;
    private static AbstractDbProxy dbProxy;
    private static JdbcProxy jdbcProxy;
    private static HBaseProxy hBaseProxy;
    private static boolean isInit;
    /**
     * 加载数据源
     * 
     * @param config
     * @author mayt
     * @ApiDocMethod
     */
    public static void loadResource(Map<String, String> config) {
        if (!isInit) {
            synchronized (DbManager.class) {
                if (!isInit) {
                    loading(config);
                    isInit = true;
                }
            }
        }
    }

    private static void loading(Map<String, String> config) {
        if (null == config) {
            throw new SystemException("config is empty");
        }
        jdbcProxy = JdbcProxy.getInstance();
        jdbcProxy.loadResource(config);
        hBaseProxy = HBaseProxy.getInstance();
        hBaseProxy.loadResource(config);
    }

    public static AbstractDbProxy getDbProxy() {
        return dbProxy;

    }

    public static String getDbType() {
        return dbType;
    }

}
