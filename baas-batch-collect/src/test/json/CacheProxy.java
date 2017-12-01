package json;

import java.util.Map;
import java.util.Properties;

import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class CacheProxy {
	public static final String CCS_APPNAME = "ccs.appname";
	public static final String CCS_ZK_ADDRESS = "ccs.zk_address";
	private ICacheClient cacheClient;
	private static CacheProxy instance;
	private static Properties param = new Properties();
	//private IDshmClient dshmClient;
	
	public static void loadResource(Map<String,String> config){
		if(instance == null){
			synchronized(CacheProxy.class){
				if(instance == null){
					instance = new CacheProxy();
					instance.loading(config);
				}
			}
		}
	}
	
	private void loading(Map<String,String> config){      
//	    param.setProperty(CCS_APPNAME, config.get(CCS_APPNAME));
//	    param.setProperty(CCS_ZK_ADDRESS, config.get(CCS_ZK_ADDRESS));
	    param.setProperty("paas.auth.url", config.get("paas.auth.url"));
	    param.setProperty("paas.auth.pid", config.get("paas.auth.pid"));
	    param.setProperty("paas.ccs.serviceid", config.get("paas.ccs.serviceid"));
	    param.setProperty("paas.ccs.servicepassword", config.get("paas.ccs.servicepassword"));
	    System.out.println("CacheClient.Properties==="+param.toString());
	    cacheClient = CacheFactoryUtil.getCacheClient(param,CacheBLMapper.CACHE_BL_CAL_PARAM);
	    //dshmClient = new DshmClient();
	}
	
	private void reload(){
		
	}
	
	public static CacheProxy getInstance(){
		return instance;
	}
	
//	public List<Map<String, String>> doQuery(String tableName, Map<String,String> params){
//		return dshmClient.list(tableName).where(params).executeQuery(cacheClient);
//	}
	
	public ICacheClient getCacheClient(){
		return cacheClient;
	}
	
	
}
