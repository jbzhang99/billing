package com.ai.baas.ccp.topoligy.dao.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

public final class DaoInstanceFactory {
    
    private static final Map<String, String> servMap = new HashMap<String, String>();
    private static final Map<String, String> tableMap = new HashMap<String, String>();
    private DaoInstanceFactory() {}
    public static Object getInstance(Class<?> clazz, String tableName) {
        String dbType = getDbType(tableName);
        
        String key = new StringBuilder().append(clazz.getSimpleName()).append(".").append(dbType).toString();
        if (!servMap.containsKey(key)) {
            loadConfig();
        }
        if (!servMap.containsKey(key)) {
            throw new SystemException("生成实例失败，请检查instance-config配置，key = " + key);
        }
        try {
            return Class.forName(servMap.get(key)).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new SystemException("生成实例失败", e);
        }
    }
    
    private static String getDbType(String tableName) {
        if (!tableMap.containsKey(tableName)) {
            readTableDbConf();
        }
        if (!tableMap.containsKey(tableName)) {
            return "mysql";
        }
        return tableMap.get(tableName);
    }
    
    private static void readTableDbConf() {
        InputStream in = DaoInstanceFactory.class.getClassLoader().getResourceAsStream(
                "tabledb.config/table-db-config.properties");
        if (in == null) {
            throw new SystemException("读取表所在数据库类型配置文件失败");
        }
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        Iterator<Entry<Object, Object>> it = p.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();
            String key = StringUtil.toString(entry.getKey());
            String value = StringUtil.toString(entry.getValue());
            tableMap.put(key, value);
        }
    }
    private static void loadConfig() {
        InputStream in = DaoInstanceFactory.class.getClassLoader().getResourceAsStream(
                "instance.config/instance-config.properties");
        if (in == null) {
            throw new SystemException("读取服务实现配置文件失败");
        }
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        Iterator<Entry<Object, Object>> it = p.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();
            String key = StringUtil.toString(entry.getKey());
            String className = StringUtil.toString(entry.getValue());
            servMap.put(key, className);
        }
    }

}
