package com.ai.runner.center.ctp.rtm.core.reader;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.base.exception.BusinessException;
import com.ai.runner.center.ctp.rtm.core.util.Bakupfile;
import com.ai.runner.center.ctp.rtm.core.util.Filelist;
import com.ai.runner.utils.util.DateUtil;

public class SXFileReader implements Reader {

	private static Logger logger = LoggerFactory.getLogger(SXFileReader.class);
	public static final String readerName = "file-reader";
	private Long interval = 2l;// 默认2秒

	private String compressPanth;// 压缩文件路径
	private String backupath;// 备份目录
	private String url;
	private String dest;// 解压目录
	private Long intervals;
	private String system_id;
	// private Long interval ;
	private int num;
	private String appId;
	private String httpClientPwd;
	private String appKey;
	
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	private String sign;
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getHttpClientPwd() {
		return httpClientPwd;
	}

	public void setHttpClientPwd(String httpClientPwd) {
		this.httpClientPwd = httpClientPwd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	// public Long getInterval() {
	// return interval;
	// }
	//
	// public void setInterval(Long interval) {
	// this.interval = interval;
	// }

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getBackupath() {
		return backupath;
	}

	public void setBackupath(String backupath) {
		this.backupath = backupath;
	}

	public Long getIntervals() {
		return intervals;
	}

	public void setIntervals(Long intervals) {
		this.intervals = intervals;
	}

	public String getCompressPanth() {
		return compressPanth;
	}

	public void setCompressPanth(String compressPanth) {
		this.compressPanth = compressPanth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	

	public void run() {

		Map<String, String> Cache = new HashMap<String, String>();
		while (true) {
			List<String> needUploadFileList = getFileUpload(compressPanth);

			if (!needUploadFileList.isEmpty()) {
				int size = needUploadFileList.size();
				if (size > num) {
					int yushu = size % num;
					int mol = size / num;
					if (yushu != 0) {
						num++;
					}
					try {
						for (int i = 0; i < num; i++) {
							int start = i * mol;
							int end = start + mol;
							if (end > size) {
								end = size;
							}
							List<String> up = needUploadFileList.subList(start,
									end);
							Filelist fl = new Filelist(compressPanth, up,
									backupath, dest, Cache, url, system_id,
									readerName,appId,httpClientPwd,appKey,sign);

							Thread t = new Thread(fl);
							t.start();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					Filelist fl = new Filelist(compressPanth, needUploadFileList,
							backupath, dest, Cache, url, system_id,
							readerName,appId,httpClientPwd,appKey,sign);

					Thread t = new Thread(fl);
					t.start();
				}
			}
			try {
				TimeUnit.SECONDS.sleep(intervals);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private List<String> getFileUpload(String path) {

		List<String> needUploadFileList = new ArrayList<String>();
		File storefile = new File(path);
		// 1.get the .ok files to upload
		if (storefile.isDirectory()) {
			File[] files = storefile.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String name = pathname.getName();
					if(name.endsWith(".avl")){
						return true;
					}else{
						return false; 
					}
				}
			});
			for (File file : files) {
				System.out.println(file.getParent());
				System.out.println(file.getName().substring(0,file.getName().lastIndexOf("."))+".zip");
				File fileZip = new File(file.getParent(),file.getName().substring(0,file.getName().lastIndexOf("."))+".zip");
				
				if (!fileZip.exists()){
					File bakfile = 
							Bakupfile.copyFile( file.getAbsolutePath(), backupath+ "/" + file.getName());
							file.delete();
							logger.info(fileZip.getName()+"文件不存在"+backupath
									+ "/"+ file.getName()+"请处理");
							return needUploadFileList;
				}
				String fileName = fileZip.getAbsolutePath();
					File f = new File(fileName);
						needUploadFileList.add(f.getName());
				
			}
		}
		return needUploadFileList;

	}

}
