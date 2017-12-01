package com.ai.runner.center.ctp.rtm.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.reader.Reader;
import com.ai.runner.center.ctp.rtm.core.util.DataSourceUtil;
import com.ai.runner.center.ctp.rtm.core.util.GenerateAPNUtil;
import com.ai.runner.center.ctp.rtm.core.util.PropertiesUtil;

public class RtmFlow {
  private static Logger logger = LoggerFactory.getLogger(RtmFlow.class);
  private Reader reader;
 // private ProcessHandler process=new ProcessHandler();
  //private DeliverHandler deliver;
 // private WrapperExecutor wrapper;
 // private ProducerProxy producerProxy;
  
 // public WrapperExecutor getWrapper() {
	//return wrapper;
//}
//
//public void setWrapper(WrapperExecutor wrapper) {
//	this.wrapper = wrapper;
//}
//
//
//
//public ProducerProxy getProducerProxy() {
//	return producerProxy;
//}
//
//public void setProducerProxy(ProducerProxy producerProxy) {
//	this.producerProxy = producerProxy;
//}
//
//public DeliverHandler getDeliver() {
//	return deliver;
//}
//
//public void setDeliver(DeliverHandler deliver) {
//	this.deliver = deliver;
//}

public Reader getReader() {
	return reader;
}

public void setReader(Reader reader) {
	this.reader = reader;
}

//public ProcessHandler getProcess() {
//	return process;
//}
//
//public void setProcess(ProcessHandler process) {
//	this.process = process;
//}

@SuppressWarnings("static-access")
public void startRun() throws Exception{
//	if(wrapper!=null){
//	  wrapper.create();
//	  logger.debug("[线程池加载成功!]");
//	}
//	  process.start();
//	  logger.debug("[任务处理器启动成功!]");
//	  deliver.start();
//	  logger.debug("[消息分发器启动成功!]");
//	  producerProxy.getInstance();
//	  logger.debug("[消息队列启动成功!]");
	 PropertiesUtil.load("ctp-rtm.properties");
	 GenerateAPNUtil.load("apn-source.properties");
	 logger.debug("[读取配置文件成功!]");
	 //System.out.println("[读取配置文件成功!]");
	 //BpsRecordManager.getInstance();
	 //logger.debug("[从数据库加载业务映射字段成功!]");
	 DataSourceUtil.load();//创建数据库连接池
	 logger.debug("[加载数据源完成!]");
	
	  reader.run();
	  logger.debug("[扫描任务以开启!]");
  }

}
