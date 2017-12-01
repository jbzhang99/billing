package com.ai.runner.center.ctp.rtm.core.cache;

import java.util.Map;
import java.util.Set;

public interface ICacheClient {

	String set(String key, String value);

	String get(String key);
	
	Long del(String key);

	Long del(String... keys);

	Long incr(String key);
//	
//	Long incrBy(String paramString, long paramLong);
	
	Long sadd(String key, String... members);
	
	Set<String> smembers(String key);
	
	Long scard(String key);
	
	boolean exists(String key);
	
	String rename(String oldkey,String newkey);
	
	Long hincrBy(String key,String field, long value);
	
	Map<String,String> hgetAll(String key);
	
}
