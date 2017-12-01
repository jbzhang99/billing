package com.ai.runner.center.ctp.rtm.core.cache;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheClient implements ICacheClient {

	private static Logger logger = LoggerFactory.getLogger(CacheClient.class);
	private JedisPool pool;
	private GenericObjectPoolConfig config;
	private String address="10.1.234.162:6379";
	
	public CacheClient(){
		setPoolConfig();
		createPool();
	}
	
	private void setPoolConfig(){
		address = (String)PropertiesUtil.getValue("ctp.rtm.cache.address");
		config = new GenericObjectPoolConfig();
		String maxActive = (String)PropertiesUtil.getValue("ctp.rtm.cache.maxActive");
		if(StringUtils.isNotBlank(maxActive)){
			config.setMaxTotal(Integer.parseInt(maxActive));
		}
		String maxIdle = (String)PropertiesUtil.getValue("ctp.rtm.cache.maxIdle");
		if(StringUtils.isNotBlank(maxIdle)){
			config.setMaxIdle(Integer.parseInt(maxIdle));
		}
		String maxWait = (String)PropertiesUtil.getValue("ctp.rtm.cache.maxWait");
		if(StringUtils.isNotBlank(maxWait)){
			config.setMaxWaitMillis(Integer.parseInt(maxWait));
		}
		String testOnBorrow = (String)PropertiesUtil.getValue("ctp.rtm.cache.testOnBorrow");
		if(StringUtils.isNotBlank(testOnBorrow)){
			config.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		}
		String testOnReturn = (String)PropertiesUtil.getValue("ctp.rtm.cache.testOnReturn");
		if(StringUtils.isNotBlank(testOnReturn)){
			config.setTestOnReturn(Boolean.parseBoolean(testOnReturn));
		}
	}
	
	private void createPool() {
		if (!canConnection()) {
			logger.debug("Create JedisPool Begin ...");
			try {
				String[] ipAndPort = address.split(":");
				pool = new JedisPool(this.config, ipAndPort[0], Integer.parseInt(ipAndPort[1]), 5000);
				if (canConnection()) {
					logger.debug("Redis Server Info:" + ipAndPort[0]);
				}
				logger.debug("Create JedisPool Done ...");
			} catch (Exception e) {
				logger.error("context",e);
			}
		}
	}
	
	private boolean canConnection() {
		if (this.pool == null) {
			return false;
		}
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.connect();
			jedis.get("ok");
		} catch (Exception e) {
			logger.error("context",e);
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
		return true;
	}
	
	private Jedis getJedis() {
		return this.pool.getResource();
	}
	
	private void returnResource(Jedis jedis) {
		this.pool.returnResourceObject(jedis);
	}
	
	public void destroyPool() {
		if (this.pool != null) {
			this.pool.destroy();
		}
	}
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.set(key, value);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}
	
	@Override
	public Long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.incr(key);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}
	
	@Override
	public Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.del(key);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public Long del(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.del(keys);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public Long sadd(String key, String... members) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.sadd(key, members);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.smembers(key);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public Long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.scard(key);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}
	
	@Override
	public boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.exists(key).booleanValue();
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public String rename(String oldkey,String newkey) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.rename(oldkey, newkey);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				returnResource(jedis);
			}
		}
	}
	
	

}
