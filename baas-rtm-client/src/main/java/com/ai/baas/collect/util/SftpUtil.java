package com.ai.baas.collect.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {

	private final static Log logger = LogFactory.getLog(SftpUtil.class);

	/*
	 * 从SFTP服务器下载文件
	 * 
	 * @param ftpHost SFTP IP地址
	 * 
	 * @param ftpUserName SFTP 用户名
	 * 
	 * @param ftpPassword SFTP用户名密码
	 * 
	 * @param ftpPort SFTP端口
	 * 
	 * @param ftpPath SFTP服务器中文件所在路径 格式： ftptest/aa
	 * 
	 * @param localPath 下载到本地的位置 格式：H:/download
	 * 
	 * @param fileName 文件名称
	 */
	public static void downloadSftpFile(String ftpHost, String ftpUserName,
			String ftpPassword, int ftpPort, String ftpPath, String localPath,
			String fileName ,String bakPath) throws JSchException {
		Session session = null;
		Channel channel = null;

		JSch jsch = new JSch();
		session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
		session.setPassword(ftpPassword);
		session.setTimeout(100000);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp chSftp = (ChannelSftp) channel;
		
		
		//获取文件列表
		Vector  vFileList;
		try {
			chSftp.cd(ftpPath);
			vFileList = chSftp.ls("*" + fileName + "*");
			
		} catch (SftpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("获取文件列表失败！");
			return;
		}
		logger.error("获取文件列表成功！" + vFileList.size());
		for(int i=0;i<vFileList.size();i++)
		{
			LsEntry enti = (LsEntry)vFileList.get(i);
			logger.error("开始下载：" + enti.getFilename());
			String localFilePath = localPath + File.separatorChar + "dling_" + enti.getFilename();

			try {
				chSftp.get(ftpPath + "/" + enti.getFilename(), localFilePath);
				//删除文件
				chSftp.rm(enti.getFilename());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("download error.");
				//报错后执行下载后面一个
				continue;
			} finally {
				
			}
			
			logger.error("下载完成：" + enti.getFilename());
			//下载完成后， 统一进行备份			
			
			String bakFile = bakPath + File.separatorChar + enti.getFilename();
			//下载完成后进行备份
			File localFile = new File(localFilePath);
			int nCopy =  FileUtil.copyFile(localFilePath, bakFile);
			
			
			//下载完改名字
			String destFile =localPath + File.separatorChar + "dlne_" + enti.getFilename();			
			
			if(localFile.renameTo(new File(destFile)))
			{
				//FileUtil.copyFile(destFile, bakFile);
			}
			else
			{
				logger.error("改名失败！" + localFile.getAbsolutePath());
				//FileUtil.copyFile(localFilePath, bakFile);
			}
						
			
		} //end for
		
		//中断链接
		chSftp.quit();
		channel.disconnect();
		session.disconnect();
		

	}

}
