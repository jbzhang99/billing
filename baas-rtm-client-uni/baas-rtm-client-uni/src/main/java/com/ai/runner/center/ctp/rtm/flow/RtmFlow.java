package com.ai.runner.center.ctp.rtm.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.reader.Reader;

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
	  reader.run();
	  logger.debug("[扫描任务以开启!]");
  }

}
