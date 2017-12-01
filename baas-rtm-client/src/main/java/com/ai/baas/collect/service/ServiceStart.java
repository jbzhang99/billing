package com.ai.baas.collect.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.collect.atom.interfaces.IBlCollectFileAtomSV;
import com.ai.baas.collect.dealfile.DealFile;
import com.ai.baas.collect.dealfile.ScanFile;
import com.ai.baas.collect.getfile.GetFiles;
import com.ai.baas.collect.start.MainStart;
import com.ai.baas.collect.util.PropertiesUtil;
import com.ai.baas.collect.vo.ServiceParam;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: ServiceStart.java
 * @Description: 服务启动
 * 
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月15日 下午1:42:45
 * 
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2017年3月15日
 *        hanzf v1.0.0 创建
 */
@Component
public class ServiceStart {
	private static final Log logger = LogFactory.getLog(ServiceStart.class);

	@Autowired
	IBlCollectFileAtomSV blCollectFileAtomSVImpl;

	@Autowired
	@Qualifier("woyunprocessor")
	ICalProcessor callProcessor;

	String serviceList[];
	int serviceNum = 0;

	// 下载文件的线程队列
	List<GetFiles> lGetFiles;
	List<ScanFile> lScanFile;
	List<DealFile> lDealFile;

	public void startService() {
		// 启动下载文件线程
		logger.error("任务列表：" + PropertiesUtil.getValue("SERVICE_LIST"));
		serviceList = PropertiesUtil.getValue("SERVICE_LIST").split(";");
		serviceNum = serviceList.length;
		lGetFiles = new ArrayList<GetFiles>();
		lScanFile = new ArrayList<ScanFile>();
		lDealFile = new ArrayList<DealFile>();

		// 开始按服务启动线程
		for (int i = 0; i < serviceNum; i++) {
			// 开始启动线程
			logger.error("开始启动线程：" + serviceList[i]);

			// 获取本服务的参数
			ServiceParam serviceParam = PropertiesUtil
					.getServiceParam(serviceList[i]);
			logger.error("记载到参数：" + serviceParam.toString());

			// 开始启动采集线程
			GetFiles getFiles = new GetFiles();
			getFiles.setServiceParam(serviceParam);
			getFiles.setThreadName(serviceParam.getName() + "GetFiles");
			getFiles.start();
			lGetFiles.add(getFiles);
			logger.error("下载文件线程启动完成！");

			// 开始启动拆分文件并分发任务线程
			// 生成文件队列
			LinkedBlockingQueue<String> lFileQueue = new LinkedBlockingQueue<String>();
			ScanFile scanFile = new ScanFile(blCollectFileAtomSVImpl);
			scanFile.setlFileQueue(lFileQueue);
			scanFile.setServiceParam(serviceParam);
			scanFile.setThreadName(serviceParam.getName() + "ScanFile");
			logger.error("扫描文件线程启动完成！");
			scanFile.start();

			lScanFile.add(scanFile);

			// 启动处理文件的线程
			logger.error("启动处理线程数量：" + serviceParam.getDealThreadNum());
			DealFile dealFile;
			for (int n = 0; n < serviceParam.getDealThreadNum(); n++) {
				dealFile = new DealFile();
				dealFile.setThreadName(serviceParam.getName() + "DealFile" + n);
				dealFile.setServiceParam(serviceParam);
				dealFile.setlFileQueue(lFileQueue);
				dealFile.setCallProcessor(callProcessor);

				dealFile.start();

				lDealFile.add(dealFile);
			}

			logger.error("启动线程完成：" + serviceList[i]);

		}

	}

	public void startGetFileService() {

	}

	public void stopService() {
		logger.error("通知结束线程开始");
		
		synchronized (this) {

			for (int i = 0; i < this.lGetFiles.size(); i++) {
				this.lGetFiles.get(i).setExitFlag(true);
			}
			for (int i = 0; i < this.lScanFile.size(); i++) {
				this.lScanFile.get(i).setExitFlag(true);
			}
			for (int i = 0; i < this.lDealFile.size(); i++) {
				this.lDealFile.get(i).setExitFlag(true);
			}

			logger.error("通知线程结束");

			// 循环判断状态
			boolean bGetFiles = false;
			boolean bScanFiles = false;
			boolean bDealFiles = false;
			while (true) {
				bGetFiles = false;
				bScanFiles = false;
				bDealFiles = false;
				logger.error("开始判断线程状态：");
				for (int i = 0; i < this.lGetFiles.size(); i++) {
					logger.debug(this.lGetFiles.get(i).getThreadName() + ":"
							+ this.lGetFiles.get(i).getState());
					if (this.lGetFiles.get(i).getState() != Thread.State.TERMINATED)
						bGetFiles = true;
				}
				for (int i = 0; i < this.lScanFile.size(); i++) {
					logger.debug(this.lScanFile.get(i).getThreadName() + ":"
							+ this.lScanFile.get(i).getState());
					if (this.lScanFile.get(i).getState() != Thread.State.TERMINATED)
						bScanFiles = true;
				}
				for (int i = 0; i < this.lDealFile.size(); i++) {
					logger.debug(this.lDealFile.get(i).getThreadName() + ":"
							+ this.lDealFile.get(i).getState());

					if (this.lDealFile.get(i).getState() != Thread.State.TERMINATED)
						bDealFiles = true;
				}
				// 存在一个没退出则等待
				if (bGetFiles || bScanFiles || bDealFiles) {
					logger.error("存在未退出线程");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// 都退出了，则调出循环
					logger.error("所有线程均已退出！");
					break;
				}
			}//end while
		}
		return;
	}

	public String[] getServiceList() {
		return serviceList;
	}

	public void setServiceList(String[] serviceList) {
		this.serviceList = serviceList;
	}

	public int getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(int serviceNum) {
		this.serviceNum = serviceNum;
	}

	public List<GetFiles> getlGetFiles() {
		return lGetFiles;
	}

	public void setlGetFiles(List<GetFiles> lGetFiles) {
		this.lGetFiles = lGetFiles;
	}

	public List<ScanFile> getlScanFile() {
		return lScanFile;
	}

	public void setlScanFile(List<ScanFile> lScanFile) {
		this.lScanFile = lScanFile;
	}

	public List<DealFile> getlDealFile() {
		return lDealFile;
	}

	public void setlDealFile(List<DealFile> lDealFile) {
		this.lDealFile = lDealFile;
	}

}
