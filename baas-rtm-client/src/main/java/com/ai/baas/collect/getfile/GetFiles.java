package com.ai.baas.collect.getfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.collect.basethread.LoopThread;
import com.ai.baas.collect.util.FtpUtil;
import com.ai.baas.collect.util.SftpUtil;
import com.ai.baas.collect.vo.ServiceParam;
import com.jcraft.jsch.JSchException;

public class GetFiles extends LoopThread{
	
	private  Logger logger = LoggerFactory.getLogger(GetFiles.class);
	
	ServiceParam serviceParam = null;

	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		if(serviceParam == null)
			return false;
		return true;
	}

	@Override
	public boolean unInit() {
		// TODO Auto-generated method stub
		
		return true;
	}

	/*运行程序*/
	public void run(){
		
		if(!init())
		{
			logger.error(this.threadName+"下载文件线程初始化失败！");
		}
		//循环下载文件
		while(!exitFlag)
		{
			logger.error(this.threadName + "开始一次获取文件");
			if(serviceParam.getTransferProtocol().equals("FTP"))
			{
				FtpUtil.downloadFtpFile(serviceParam.getAddr(), serviceParam.getUser(), 
						serviceParam.getPassWord(), serviceParam.getPort(), serviceParam.getRemotePath(), 
						serviceParam.getLocalPath(), serviceParam.getFileName(),serviceParam.getBakPath());
			}
			else if(serviceParam.getTransferProtocol().equals("SFTP"))
			{
				try {
					SftpUtil.downloadSftpFile(serviceParam.getAddr(), serviceParam.getUser(), 
							serviceParam.getPassWord(), serviceParam.getPort(), serviceParam.getRemotePath(), 
							serviceParam.getLocalPath(), serviceParam.getFileName(),serviceParam.getBakPath());
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
			else
			{
				logger.error(serviceParam.getTransferProtocol()+"不支持的协议！");
				break;
			}
			logger.error(this.threadName + "完成一次获取文件");
			
			//执行一次后， sleep一段时间
			try {
				this.sleep(serviceParam.getScanInter()*1000);
			} catch (InterruptedException e) {
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
