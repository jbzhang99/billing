package com.ai.baas.collect.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.baas.collect.start.MainStart;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: FileUtil.java
 * @Description: 文件处理工具
 *
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月16日 上午10:45:06
 *
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2017年3月16日     hanzf           v1.0.0               创建
 */
public class FileUtil {
	
    private static final Log logger = LogFactory.getLog(FileUtil.class);


	/**
	 * 
	 * 
	 * @Description: 复制文件
	 *
	 * @param:参数描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年3月16日 上午10:45:56
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年3月16日       hanzf           v1.0.0               创建
	 */
	public static int copyFile(String source, String dest )
	{

		boolean isWindows = SystemUtil.isWindow();
		String cmd = "";
		
		Process  pro; 
		int nResult = 0;
			
		if(isWindows)
		{
			cmd = "cmd.exe /c copy " + source + " " + dest;
		}
		else
		{
			cmd = "cp " + source + " " + dest; 
		}
		logger.debug("执行复制命令：" + cmd);
		//执行系统命令
		try {
			pro = Runtime.getRuntime().exec(cmd);
			pro.waitFor();
			nResult = pro.exitValue();
			logger.debug("执行复制结果：" + nResult);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
		
		
		
		return 0;
	}
	
	/**
	 * 
	 * 
	 * @Description: 复制文件
	 *
	 * @param:参数描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年3月16日 上午10:45:56
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年3月16日       hanzf           v1.0.0               创建
	 */
	public static void moveFile(String source, String dest )
	{

		boolean isWindows = SystemUtil.isWindow();
		String cmd = "";
		if(isWindows)
		{
			cmd = "cmd.exe /c move " + source + " " + dest;
		}
		else
		{
			cmd = "mv " + source + " " + dest; 
		}
		logger.debug("执行复制命令：" + cmd);
		//执行系统命令
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return;
	}
	
	/**
	 * 
	 * 
	 * @Description: 拆分文件
	 *
	 * @param: 文件  每个文件行数  原前缀  新前缀
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年3月16日 下午1:58:16
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年3月16日       hanzf           v1.0.0               创建
	 */
	public static boolean splitFile(File file,int lineNumber,String sSub,String nSub)
	{
		boolean isWindows = SystemUtil.isWindow();
		String cmd = "";
		String newName = "";
		Process pr;
		int nRe = -1;
		if(isWindows)
		{
			return false;
		}
		else
		{
			//先切换目录，再分割
			newName = file.getName().replace(sSub, nSub);
			cmd =  "  split -l " + lineNumber + " " + file.getAbsolutePath() + " " + newName;
		}
		logger.debug("执行分割命令：" + cmd);
		//执行系统命令
		try {
			pr = Runtime.getRuntime().exec(cmd,null,new File(file.getParent()));
			pr.waitFor();
			nRe = pr.exitValue();
			logger.debug("分割结果：" + nRe);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("分割文件失败！" + file.getAbsolutePath());
			e.printStackTrace();
			return false;
		}
		if(nRe!= 0)
			return false;
		return true;
	}
	
	
	
}
