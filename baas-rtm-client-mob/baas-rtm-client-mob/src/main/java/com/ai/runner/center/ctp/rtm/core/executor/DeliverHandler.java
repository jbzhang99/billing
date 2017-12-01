package com.ai.runner.center.ctp.rtm.core.executor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.cache.CacheProxy;
import com.ai.runner.center.ctp.rtm.core.deliver.MessageDeliver;
import com.ai.runner.center.ctp.rtm.core.generator.PsnContainer;
import com.ai.runner.center.ctp.rtm.core.generator.SnContainer;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;
import com.ai.runner.center.ctp.rtm.core.util.SyncMultiMap;

public class DeliverHandler extends LoopThread {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DeliverHandler.class);
	public static SyncMultiMap deliverQueue = new SyncMultiMap();
	//public static PsnContainer psnGeneratorContainer = new PsnContainer();
	private Integer deliverInterval = 5;
	public  static Integer packetNum = 1;//每个包中报文条数
	private CacheProxy cacheproxy;
	private PsnContainer psnContainer;
	private SnContainer snContainer;
	
	public PsnContainer getPsnContainer() {
		return psnContainer;
	}

	public void setPsnContainer(PsnContainer psnContainer) {
		this.psnContainer = psnContainer;
	}

	public SnContainer getSnContainer() {
		return snContainer;
	}

	public void setSnContainer(SnContainer snContainer) {
		this.snContainer = snContainer;
	}

	public CacheProxy getCacheproxy() {
		return cacheproxy;
	}

	public void setCacheproxy(CacheProxy cacheproxy) {
		this.cacheproxy = cacheproxy;
	}

	public Integer getDeliverInterval() {
		return deliverInterval;
	}

	public void setDeliverInterval(Integer deliverInterval) {
		this.deliverInterval = deliverInterval;
	}

	public  Integer getPacketNum() {
		return packetNum;
	}

	@SuppressWarnings("static-access")
	public  void setPacketNum(Integer packetNum) {
		this.packetNum = packetNum;
	}

	@Override
	public boolean init() {
		psnContainer.initContainer();
		snContainer.initContainer();
		cacheproxy.getCache();
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		if(checkedQueueNull()){
			doThreadSleep();
			return;
		}
		long startTime = System.currentTimeMillis()/1000;
		long lastTime = 0L;
		List<String> keys = null;
		for(;;){
			//需要判断deliverQueue是否为空
			if(checkedQueueNull()){
				break;
			}
			lastTime = System.currentTimeMillis()/1000;
			//超时处理一次
			if(lastTime-startTime>deliverInterval){
//				logger.debug("系统已经超时啦!");
//				logger.debug("key size="+deliverQueue.keySize()+"key="+deliverQueue.keys().toString());
//				
//				logger.debug("original map="+deliverQueue.totalSize());
				SyncMultiMap syncMultiMap = deliverQueue.cloneAndClear();
//				logger.debug("key size="+syncMultiMap.keySize());
//				logger.debug("clone map="+syncMultiMap.totalSize());
//				logger.debug("original map="+deliverQueue.totalSize());
				
				//需要判断key为0的情况????
				if(syncMultiMap.keySize()!=0){
					deliverAll(syncMultiMap);
				}
				break;
			}else{
				keys = deliverQueue.getBeyondMaxLimitKeys(packetNum);
				deliver(keys);
			}
		}
		
	}
	
	private boolean checkedQueueNull(){
		int deliverQueueKeySize = deliverQueue.keySize();
		//print test
		//deliverQueue.printMultiMap();
		if(deliverQueueKeySize == 0){
			return true;
		}else{
			return false;
		}
	}
	//@Autowired
	private MessageDeliver msgDeliver;
	
	public MessageDeliver getMsgDeliver() {
		return msgDeliver;
	}

	public void setMsgDeliver(MessageDeliver msgDeliver) {
		this.msgDeliver = msgDeliver;
	}

	private void deliverAll(SyncMultiMap syncMultiMap){
		//MessageDeliver msgDeliver = new MessageDeliver(syncMultiMap);
		msgDeliver.setSyncMultiMap(syncMultiMap);
		msgDeliver.setDeliverAll(true);
		msgDeliver.start();
	}
	
	
	private void deliver(List<String> keys){
		List<StringLine> element;
		//MessageDeliver msgDeliver;
		for(String key:keys){
			//element = deliverQueue.remove(key);
			element = deliverQueue.getAdequateElement(key, packetNum);
			//按照key的多少起相应的线程
			//msgDeliver = new MessageDeliver(key, element);
			msgDeliver.setService_id(key);
			msgDeliver.setMsgQueue(element);
			msgDeliver.start();
		}
	}
	
	
	private void doThreadSleep(){
		try {
			//TimeUnit.SECONDS.sleep(deliverInterval);
			//TimeUnit.SECONDS.sleep(1);
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
