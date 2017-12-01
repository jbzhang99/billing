package com.ai.baas.omc.topoligy.db;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.storm.util.BaseConstants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HBaseProxy extends AbstractDbProxy {
	private static Logger LOGGER = LoggerFactory.getLogger(HBaseProxy.class);
	private static Connection connection;
	private static HBaseProxy instance;
	
	public void loadResource(Map<String,String> config){
		if(connection == null){
			synchronized(HBaseProxy.class){
				if(connection == null){
					loading(config);
				}
			}
		}
	}
	
	private void loading(Map<String,String> config){
		Configuration configuration = HBaseConfiguration.create();
		String hbaseSite = config.get(BaseConstants.HBASE_PARAM);
		try {
			if(StringUtils.isBlank(hbaseSite)){
			    LOGGER.info("未配置hbase连接信息，跳过加载hbase数据源");
				return;
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject)jsonParser.parse(hbaseSite);
			for(Entry<String, JsonElement> entry:jsonObject.entrySet()){
				configuration.set(entry.getKey(), entry.getValue().getAsString());
			}
			connection = ConnectionFactory.createConnection(configuration);
		} catch (Exception e) {
			LOGGER.error("error", e);
		}
	}
	
	public Object getConnection(){
		return connection;
	}

    public static HBaseProxy getInstance() {
        if(instance == null){
            synchronized(HBaseProxy.class){
                if(instance == null){
                    instance = new HBaseProxy();
                }
            }
        }
        return instance;
    }
	
	
	
}
