package com.ai.runner.center.ctp.rtm.core.cache;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CacheClient implements ICacheClient {

	private static Logger logger = LoggerFactory.getLogger(CacheClient.class);
	private JedisPool pool;
	private GenericObjectPoolConfig config;
	private String address="10.1.234.162:6379";
	private String maxActive;
	private String maxIdle;
	private String maxWait;
	private String testOnBorrow;
	private String testOnReturn;
	public CacheClient(){
		setPoolConfig();
		createPool();
	}
	
	private void setPoolConfig(){
		
		config = new GenericObjectPoolConfig();
		
		if(StringUtils.isNotBlank(maxActive)){
			config.setMaxTotal(Integer.parseInt(maxActive));
		}
		if(StringUtils.isNotBlank(maxIdle)){
			config.setMaxIdle(Integer.parseInt(maxIdle));
		}
		if(StringUtils.isNotBlank(maxWait)){
			config.setMaxWaitMillis(Integer.parseInt(maxWait));
		}
		if(StringUtils.isNotBlank(testOnBorrow)){
			config.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
		}
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
	


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(String maxActive) {
		this.maxActive = maxActive;
	}

	public String getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(String maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(String maxWait) {
		this.maxWait = maxWait;
	}

	public String getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(String testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public String getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(String testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	

}
