package com.ai.runner.center.ctp.rtm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bakupfile {
	private static Logger logger = LoggerFactory.getLogger(Unzip.class);
	public static File copyFile(String path, String Backupath) {
		File bakup = new File(Backupath);
		
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(path);
				if (bakup.isDirectory() && !bakup.exists()) {
					bakup.mkdir();
				}
				if (oldfile.exists()) { // 文件存在时
					InputStream is = new FileInputStream(path); // 读入原文件
					
					FileOutputStream fs = new FileOutputStream(Backupath);
					byte[] buffer = new byte[1024];
					
					while ((byteread = is.read(buffer)) != -1) {
						bytesum += byteread; // 字节数 文件大小
						// System.out.println(bytesum);
						fs.write(buffer, 0, byteread);
					}
					is.close();
				}
			} catch (Exception e) {
				
				logger.error("复制文件"+path+"操作出错");

			}
			return bakup;
		}
		
	}

