package com.ai.runner.center.ctp.rtm.core.cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class CacheClusterClient implements ICacheClient {

	private static Logger logger = LoggerFactory.getLogger(CacheClusterClient.class);
	private GenericObjectPoolConfig config;
	private JedisCluster jc;
	private String[] hosts=new String[]{};
	
	public CacheClusterClient(){
		getCluster();
	}
	
	private void getCluster() {
		logger.debug("-----------------------创建JedisPool------------------------begin---");
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		try {
			for (String address : this.hosts) {
				String[] ipAndPort = address.split(":");
				jedisClusterNodes.add(new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1])));
				logger.debug(address);
			}
			this.jc = new JedisCluster(jedisClusterNodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("-----------------------创建JedisPool------------------------end---");
	}
	
	@Override
	public String set(String key, String value) {
		String str = "";
		try {
			str = this.jc.set(key, value);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}

	@Override
	public String get(String key) {
		String str = "";
		try {
			str = this.jc.get(key);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}

	@Override
	public Long incr(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long del(String... paramVarArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sadd(String key, String... paramVarArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> smembers(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long scard(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String rename(String oldkey, String newkey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
