package com.ai.baas.collect.dealfile;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.collect.atom.interfaces.IBlCollectFileAtomSV;
import com.ai.baas.collect.basethread.LoopThread;
import com.ai.baas.collect.dao.bo.BlCollectFiles;
import com.ai.baas.collect.dao.interfaces.BlCollectFilesMapper;
import com.ai.baas.collect.util.FileUtil;
import com.ai.baas.collect.vo.ServiceParam;

public class ScanFile extends LoopThread {
	private Logger logger = LoggerFactory.getLogger(ScanFile.class);

	ServiceParam serviceParam = null;
	// 本线程的消息队列  存放绝对路径
	LinkedBlockingQueue<String> lFileQueue;

	IBlCollectFileAtomSV BlCollectFileAtomSVImpl;

	public ScanFile(IBlCollectFileAtomSV BlCollectFileAtomSVImpl) {
		// lFileQueue = new LinkedBlockingQueue<File>();
		this.BlCollectFileAtomSVImpl = BlCollectFileAtomSVImpl;
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
		if (serviceParam == null)
			return false;

		return true;
	}

	@Override
	public boolean unInit() {
		// TODO Auto-generated method stub

		return true;
	}

	/* 运行程序 */
	public void run() {

		if (!init()) {
			logger.error(this.threadName + "处理线程初始化失败！");
		}
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				if (file.isFile()
						&& file.getName().contains("dlne_")
						&& (file.getName().contains(serviceParam.getFileName())
								|| serviceParam.getFileName() == null || serviceParam
								.getFileName().equals("")))
					return true;
				return false;
			}
		};

		// 循环扫描文件
		while (!exitFlag) {
			// 获取本地目录
			File localFile = new File(serviceParam.getLocalPath());
			// 获取下载完成的文件列表 dlne_
			File files[] = localFile.listFiles(fileFilter);
			logger.error(this.threadName + serviceParam.getLocalPath()
					+ "获取到文件数量：" + files.length);
			if (files.length < 1) {
				// 执行一次后， sleep一段时间
				try {
					Thread.sleep(serviceParam.getSplitScanInter() * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String newName = "";
			// 循环处理文件
			for (File file : files) {
				logger.debug(this.threadName + "开始处理文件信息："
						+ file.getAbsolutePath());
				
				File fnewFile = null;
				// 判断文件是否已经处理过
				int nCount = 0;
				try {
					nCount = BlCollectFileAtomSVImpl.selectCountByName(
							serviceParam.getTenantId(), file.getName());

				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.debug(this.threadName + "文件是否被处理过：" + nCount);
				if (nCount > 0) {
					// 已经处理过则直接删除
					logger.debug("删除重复文件并跳过处理流程");
					file.delete();
					continue;
				} else {
					// 写入文件
					BlCollectFiles record = new BlCollectFiles();
					record.setTenantid(serviceParam.getTenantId());
					record.setFileName(file.getName());
					record.setDownloadTime(new Date());
					record.setState(0);

					BlCollectFileAtomSVImpl.saveCollectFile(record);
					logger.debug("保存处理文件成功！");
				}

				logger.debug(this.threadName + "判断文件是否已处理完成！");

				newName = file.getName().replace("dlne_", "dealing_");
				final String oriName = file.getName().replace("dlne_", "");
				try {
					String newFile = file.getParent()
							+ File.separatorChar + newName;
					fnewFile = new File(newFile);
					boolean bRename = file.renameTo(fnewFile);
					if (!bRename) {
						// 更名错误 则跳过
						logger.error(this.threadName + file.getAbsolutePath()
								+ "更名出现错误！");
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				logger.debug(this.threadName + file.getAbsolutePath()
						+ "更新文件名称完成");
				// 判断是否进行分割
				boolean bSplited = false;
				if (this.serviceParam.getSplitNum() != 0) {
					logger.debug(this.threadName + "需要进行分割文件！"
							+ file.getAbsolutePath());
					bSplited = FileUtil.splitFile(fnewFile,
							this.serviceParam.getSplitNum(), "dealing_",
							"splited_");
				}
				// 如果进行了分割
				if (bSplited) {
					// 删除分割前文件
					fnewFile.delete();
					// 重新获取分割后的文件列表
					FileFilter fileFilter2 = new FileFilter() {
						@Override
						public boolean accept(File file) {
							// TODO Auto-generated method stub
							if (file.isFile()
									&& file.getName().contains(
											"splited_" + oriName))
								return true;
							return false;
						}
					};
					File filesSplit[] = localFile.listFiles(fileFilter2);
					for (File fileSplit : filesSplit) {
						// 放入消息队列
						try {
							this.lFileQueue.put(fileSplit.getAbsolutePath());
							logger.error(this.threadName + "放入队列："
									+ fileSplit.getAbsolutePath());

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

							// 挪如错单文件
							FileUtil.moveFile(
									fileSplit.getAbsolutePath(),
									this.serviceParam.getErrorPath()
											+ File.separatorChar
											+ fileSplit.getName() + ".err");
						}
					}
				} // end if splited
				else {
					// 将新文件名称放入队列
					try {
						this.lFileQueue.put(fnewFile.getAbsolutePath());
						logger.error(this.threadName + "放入队列："
								+ file.getAbsolutePath());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						// 挪如错单文件
						FileUtil.moveFile(
								file.getAbsolutePath(),
								this.serviceParam.getErrorPath()
										+ File.separatorChar + "err_"
										+ file.getName());
					}
				}

			}// end for file

		}
		// 执行完即退出
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
