package com.ai.baas.collect.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.collect.dao.interfaces.BlCollectFilesMapper;

public class FtpUtil {

	private final static Log logger = LogFactory.getLog(FtpUtil.class);
	
	

	/**
	 * 获取FTPClient对象
	 * 
	 * @param ftpHost
	 *            FTP主机服务器
	 * @param ftpPassword
	 *            FTP 登录密码
	 * @param ftpUserName
	 *            FTP登录用户名
	 * @param ftpPort
	 *            FTP端口 默认为21
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
			String ftpPassword, int ftpPort) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				logger.info("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
			} else {
				logger.info("FTP连接成功。");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			logger.info("FTP的IP地址可能错误，请正确配置。");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("FTP的端口错误,请正确配置。");
		}
		return ftpClient;
	}

	/*
	 * 从FTP服务器下载文件
	 * 
	 * @param ftpHost FTP IP地址
	 * 
	 * @param ftpUserName FTP 用户名
	 * 
	 * @param ftpPassword FTP用户名密码
	 * 
	 * @param ftpPort FTP端口
	 * 
	 * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
	 * 
	 * @param localPath 下载到本地的位置 格式：H:/download
	 * 
	 * @param fileName 文件名称  关键字
	 */
	public static void downloadFtpFile(String ftpHost, String ftpUserName,
			String ftpPassword, int ftpPort, String ftpPath, String localPath,
			String fileName,String bakPath) {

		FTPClient ftpClient = null;

		try {
			ftpClient = getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			//获取远程文件列表
			List<String> fileList;
			try {
				 fileList = listRemoteAllFiles(ftpClient,ftpPath,fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("获取列表失败！");
				e.printStackTrace();
				return ;
			}
			logger.error("获取文件列表成功！" + fileList.size());
			ftpClient.changeWorkingDirectory(ftpPath);
			for(int i=0;i<fileList.size();i++)
			{				
				
				logger.error("开始下载：" + fileList.get(i));
				File localFile = new File(localPath  + File.separatorChar + "dling_" + fileList.get(i));
				OutputStream os = new FileOutputStream(localFile);
				ftpClient.retrieveFile(fileList.get(i), os);		
				os.flush();
				os.close();
				logger.error("下载完成：" + fileList.get(i));
				//下载完成改名
				String destFile = localPath + File.separatorChar + "dlne_" + fileList.get(i);
				//localFile.renameTo(new File(destFile));
				//删除远程端的文件
				ftpClient.deleteFile(fileList.get(i));
				
				//备份本地文件
				String bakFile = bakPath + File.separatorChar + fileList.get(i);
				//先备份：
				FileUtil.copyFile(localFile.getAbsolutePath(), bakFile);
				
				if(localFile.renameTo(new File(destFile)))
				{
					//FileUtil.copyFile(destFile, bakFile);
				}
				else
				{
					//FileUtil.copyFile(localFile.getAbsolutePath(), bakFile);
					logger.error("改名失败！" + localFile.getAbsolutePath());
				}
				
				
			}
			
			ftpClient.logout();


		} catch (FileNotFoundException e) {
			logger.error("没有找到" + ftpPath + "文件");
			e.printStackTrace();
		} catch (SocketException e) {
			logger.error("连接FTP失败.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("文件读取错误。");
			e.printStackTrace();
		}

	}
	
	//列出所有文件内容
		public static List<String> listRemoteAllFiles(FTPClient ftpClient,String path,final String fileName) throws Exception {	
			ftpClient.enterLocalPassiveMode(); 
			FTPFile[] files = ftpClient.listFiles(path, new FTPFileFilter() { 
				@Override
				public boolean accept(FTPFile file) { 
					if (file.isFile() && ( file.getName().contains(fileName) || fileName == null || fileName.equals(""))) 
							return true ;
					return false ;
				}}) ;
			
			List<String> list = new ArrayList() ;
			for (FTPFile file : files) {
				list.add(file.getName()) ;
			}
			return list ;
		}

}
