package com.ai.runner.center.ctp.rtm.core.processor;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.ConstructorUtils;
import com.ai.runner.center.ctp.rtm.core.executor.LoopThread;
import com.ai.runner.center.ctp.rtm.core.reader.SXServiceReader;

public class ProcessorFactory {

	private Map<String, String> processorMap = new HashMap<String, String>();
	
	public ProcessorFactory(){
		processorMap.put(SXServiceReader.readerName, "com.ai.runner.center.ctp.rtm.core.processor.ServiceProcessor");
	}
	
	public LoopThread getProcessorByName(String readerName, Object constructArg) throws Exception{
		String procClass = processorMap.get(readerName);
		if(StringUtils.isBlank(procClass)){
			return null;
		}
		return (LoopThread)ConstructorUtils.invokeConstructor(Class.forName(procClass), constructArg);
	}
	
}
