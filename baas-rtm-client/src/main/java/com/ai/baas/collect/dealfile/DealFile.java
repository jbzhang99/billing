package com.ai.baas.collect.dealfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ai.baas.collect.basethread.LoopThread;
import com.ai.baas.collect.service.ICalProcessor;
import com.ai.baas.collect.util.FileUtil;
import com.ai.baas.collect.vo.ServiceParam;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: DealFile.java
 * @Description: 处理文件的线程类
 *
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月16日 下午5:16:50
 *
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2017年3月16日     hanzf           v1.0.0               创建
 */
public class DealFile extends LoopThread{
	private  Logger logger = LoggerFactory.getLogger(DealFile.class);

	
	ServiceParam serviceParam = null;
	//消息队列 用来指向 扫描进程的队列
	LinkedBlockingQueue<String> lFileQueue;
	
	ICalProcessor callProcessor;
	
	
	

	public ICalProcessor getCallProcessor() {
		return callProcessor;
	}



	public void setCallProcessor(ICalProcessor callProcessor) {
		this.callProcessor = callProcessor;
	}



	public LinkedBlockingQueue<String> getlFileQueue() {
		return lFileQueue;
	}



	public void setlFileQueue(LinkedBlockingQueue<String> lFileQueue) {
		this.lFileQueue = lFileQueue;
	}



	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		if(serviceParam == null)
			return false;
		if(this.lFileQueue == null)
			return false;
		if(this.callProcessor == null)
		{
			return false;
		}
		return true;
	}
	
	

	@Override
	public boolean unInit() {
		// TODO Auto-generated method stub
		
		return true;
	}

	/*运行程序*/
	public void run(){
		logger.error("开始启动处理线程：" + this.threadName);
		
		if(!init())
		{
			logger.error(serviceParam.getName()+"处理线程初始化失败！");
		}		
		
		//循环扫描文件
		while(true)
		{
			String filePath = this.lFileQueue.poll();
			
			
			//为空且需要退出了 就跳出循环
			if(filePath == null && this.exitFlag)
			{
				logger.error(this.threadName + "队列为空，且收到退出信号，退出！");
				break;
			}
			if(filePath == null)
			{
				try {
					Thread.sleep(this.serviceParam.getDealScanInter()*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			logger.error(this.threadName + "获取到一个任务：" + filePath);
			logger.error(serviceParam.getName()+ "剩余任务：" + this.lFileQueue.size());
			File file   = new File(filePath);
			
			
			//读取到文件开始读取文件进行处理
			FileReader fReader;
			BufferedReader  bufferReader;
			
			//错单文件
			boolean bErrFlag = false;
			File ferrorFile = new File(serviceParam.getErrorPath()+File.separator+"err_" + file.getName());
			FileWriter fWriter;
			BufferedWriter bWriter;
			try {
				fWriter = new FileWriter(ferrorFile,true);	
				bWriter = new BufferedWriter(fWriter);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				logger.error("打开写错单文件失败！");
				//直接跳出不处理了
				continue;
			}
			
			
			try {
				fReader = new FileReader(file);
				bufferReader = new BufferedReader(fReader);
				String szLine = "";
				logger.debug(this.threadName + "开始读取文件：" + file.getAbsolutePath());
				while( true)
				{					
					try {
						szLine = bufferReader.readLine();
						//垃圾行  跳过
						if(szLine != null && szLine.length() < 3)
							continue;
						//读完则结束
						if(szLine == null )
							break;
						//循环处理每一行
						logger.debug(this.threadName + "处理记录：" + szLine);
						int nResult = 0;
						try{
						 nResult = this.callProcessor.doProcessor(szLine, serviceParam);
						}catch(Exception e)
						{
							e.printStackTrace();
							logger.error(this.threadName + "处理错误：" + szLine);
							nResult = -1;
						}
						logger.debug(this.threadName + "处理结果：" + nResult );
						//报错需要写入错单文件
						if(nResult != 0)
						{
							//需要写入错单文件
							bWriter.write(nResult + "_" + szLine );
							bWriter.newLine();
							bErrFlag = true;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				bWriter.flush();
				bWriter.close();
				fWriter.close();
				
				bufferReader.close();
				fReader.close();
				
				//删除此文件
				file.delete();
				//没有错单，删除错单文件
				if(!bErrFlag)
				{
					ferrorFile.delete();
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		//执行完即退出
		unInit();
		logger.error(this.threadName + "收到退出信号，退出！");
		
		
	}

	public ServiceParam getServiceParam() {
		return serviceParam;
	}

	public void setServiceParam(ServiceParam serviceParam) {
		this.serviceParam = serviceParam;
	}
	
}
