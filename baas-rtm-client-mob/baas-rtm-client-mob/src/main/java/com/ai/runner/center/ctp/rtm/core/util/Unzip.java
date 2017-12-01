package com.ai.runner.center.ctp.rtm.core.util;

import java.io.File;

import javax.swing.text.StyledEditorKit.BoldAction;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.base.exception.BusinessException;
import com.ai.runner.utils.util.DateUtil;

public class Unzip {
//	private static final File destDir = null;
	private static Logger logger = LoggerFactory.getLogger(Unzip.class);

	/**
	 * 使用给定密码解压指定的ZIP压缩文件到指定目录 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
	 * 
	 * @param zip
	 *            指定的ZIP压缩文件
	 * @param dest
	 *            解压目录
	 * @param passwd
	 *            ZIP文件的密码
	 * @throws ZipException
	 *             压缩文件有损坏或者解压缩失败抛出
	 */
	public static boolean unzip(File zipFile, String dest, String passwd,String filename) throws ZipException{
		ZipFile zFile = new ZipFile(zipFile);
		zFile.setFileNameCharset("GBK");
		File destDir = new File(dest);
		try {
			if (zFile.isValidZipFile()) {
//				File destDir = new File(dest);

				if (destDir.isDirectory() && !destDir.exists()) {
					destDir.mkdir();
				}
				if (zFile.isEncrypted()) {
					zFile.setPassword(passwd.toCharArray());
				}
				zFile.extractAll(dest+ "/" + filename);
				// 删除源文件
				zipFile.delete();
				
			} else {
				logger.debug("压缩文件"+zipFile.getName()+"不合法,可能被损坏");
				
			}
		} catch (Exception e) {
			zipFile.delete();
			logger.info("文件"+zipFile.getName()+"解压失败检查密码");
		}
		logger.info("文件解压位置"+destDir);
		return true;		

	}
}