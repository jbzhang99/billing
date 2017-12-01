package com.ai.runner.center.ctp.rtm.core.deliver;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.db.dao.HbaseOutputDetailDao;
import com.ai.runner.center.ctp.rtm.core.executor.DeliverHandler;
import com.ai.runner.center.ctp.rtm.core.executor.LoopThread;
import com.ai.runner.center.ctp.rtm.core.generator.PsnTimestampGenerator;
import com.ai.runner.center.ctp.rtm.core.packet.IPacket;
import com.ai.runner.center.ctp.rtm.core.packet.PacketFactory;
import com.ai.runner.center.ctp.rtm.core.producer.ProducerProxy;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;
import com.ai.runner.center.ctp.rtm.core.util.SyncMultiMap;
public class MessageDeliver extends LoopThread {

	private static Logger logger = LoggerFactory.getLogger(MessageDeliver.class);
	private String service_id;
	private List<StringLine> msgQueue;
	private SyncMultiMap syncMultiMap;
	private boolean isAll = false;
	//private Packets packets = new Packets();
	private int packetNum = DeliverHandler.packetNum;
	//private PsnContainer psnGeneratorContainer = DeliverHandler.psnGeneratorContainer;
	private HbaseOutputDetailDao hbaseOutputDetailDao = new HbaseOutputDetailDao();
	private PacketFactory packetFactory =new PacketFactory();
	//private ProducerProxy producerProxy=new ProducerProxy();
	
	public MessageDeliver(){
		
	}
	
	public SyncMultiMap getSyncMultiMap() {
		return syncMultiMap;
	}

	public void setSyncMultiMap(SyncMultiMap syncMultiMap) {
		this.syncMultiMap = syncMultiMap;
	}
	
	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public List<StringLine> getMsgQueue() {
		return msgQueue;
	}

	public void setMsgQueue(List<StringLine> msgQueue) {
		this.msgQueue = msgQueue;
	}

	public MessageDeliver(String service_id, List<StringLine> msgQueue){
		this.service_id = service_id;
		this.msgQueue = msgQueue;
	}
	
	public void setDeliverAll(boolean isAll){
		this.isAll = isAll;
	}
	
	@Override
	public boolean init() {
		
		return true;
	}

	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		try{
			if(isAll){
				deliverAll();
			}else{
				deliver();
			}
		}finally{
			exitFlag = true;
		}
	}
	
	/**
	 * 指定队列发送
	 * 1.从队列中得到行数据
	 * 2.转化成标准格式
	 * 3.打包后报文发送到消息队列
	 */
	@SuppressWarnings("unused")
	private void deliver(){
		List<StringLine> lines = null;
		String message = "";
		PsnTimestampGenerator psnGenerator;
		//String psn="";
		IPacket packet = packetFactory.createPacketObj(service_id);
		for(int i=0;i<msgQueue.size();i=i+packetNum){
			lines = msgQueue.subList(i, i+packetNum);
			//psnGenerator = psnGeneratorContainer.getGenerator(service_id);
			//psn = psnGenerator.getValue(lines.size());
			//message = packets.assembleLinePacket(service_id, psn, lines);
			message = packet.assemblePacketByLine(service_id, lines);
			try {
				hbaseOutputDetailDao.batchInstOutputDetail(lines);
			}catch(IOException e) {
				logger.error("", e);
			}
			//logger.debug("deliverAll send message={}",message);
			//System.out.println("deliver send message="+message);
			ProducerProxy.getInstance().sendMessage(message);
		}
	}

	/**
	 * 将队列中数据全部发送
	 * 1.从队列中得到行数据
	 * 2.转化成标准格式
	 * 3.打包后报文发送到消息队列
	 */
	@SuppressWarnings("unused")
	private void deliverAll(){
		//SyncMultiMap deliverQueue = DeliverDistributor.deliverQueue;
		Set<String> ketSets = new HashSet<String>(syncMultiMap.keys());
		List<StringLine> lines;
		String message = "";
		PsnTimestampGenerator psnGenerator;
		//String psn;
		IPacket packet;
		for(String key:ketSets){
			lines = syncMultiMap.remove(key);
			packet = packetFactory.createPacketObj(key);
			//message = packets.assemblePacket(key, lines);
			//psnGenerator = psnGeneratorContainer.getGenerator(key);
			//psn = psnGenerator.getValue(lines.size());
			//message = packets.assembleLinePacket(key, psn, lines);
			message = packet.assemblePacketByLine(key, lines);
			try{
				hbaseOutputDetailDao.batchInstOutputDetail(lines);
			}catch(IOException e){
				logger.error("", e);
			}
			//logger.debug("deliverAll send message={}",message);
			//System.out.println("deliverAll send message="+message);
			ProducerProxy.getInstance().sendMessage(message);
		}
	}
	
}
