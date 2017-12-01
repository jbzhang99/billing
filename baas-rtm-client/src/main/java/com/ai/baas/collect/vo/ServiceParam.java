package com.ai.baas.collect.vo;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: ServiceParam.java
 * @Description: 每个服务的参数对象
 * 
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月14日 下午5:14:46
 * 
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2017年3月14日
 *        hanzf v1.0.0 创建
 */
public class ServiceParam {
	
	//name
	String name;
	
	// WO_HOST.SCAN_INTER=10
	int scanInter;
	// FTP OR SFTP WO_HOST.TRANSFER_PROTOCOL=FTP
	String transferProtocol;
	// WO_HOST.ADDR=192.168.1.1
	String addr;
	// WO_HOST.USER=AAA
	String user;

	// WO_HOST.PASSWORD=AAA
	String passWord;

	// WO_HOST.REMOTE_PATH=/AAA
	String remotePath;
	// WO_HOST.LOCAL_PATH=/AAA
	String localPath;
	// WO_HOST.BAK_PATH=/AAA
	String bakPath;
	// WO_HOST.FILE_NAME=AAA
	String fileName;
	// #0 no split
	// WO_HOST.SPLIT_NUM=100
	int splitNum;
	
	
	//#SCAN FILE INTER
	//WO_HOST.SPLIT_SCAN_INTER=5
	int splitScanInter;
	//#DEAL FILE THREAD NUM
	//WO_HOST.DEAL_THREAD_NUM=3
	int dealThreadNum;
	//WO_HOST.DEAL_SCAN_INTER=5
	int dealScanInter;
	
	//ERROR_PATH
	String errorPath;
	
	int port;
	
	//WO_HOST.IS_EXCHANGE=Y
	String isExchange;
	//WO_HOST.TENANT_ID=WOYUN
	String tenantId;
	//WO_HOST.RTM_USER=123
	String rtmUser;
	//WO_HOST.RTM_PASSWORD=444444
	String rtmPassword;
	//WO_HOST.SYSTEM_ID=WO
	String SystemId;
	//WO_HOST.SOURCE=WOS
	String  source;
	
	//RTM_URL
	String rtmUrl;
	
	public String getRtmUrl() {
		return rtmUrl;
	}
	public void setRtmUrl(String rtmUrl) {
		this.rtmUrl = rtmUrl;
	}
	public String getIsExchange() {
		return isExchange;
	}
	public void setIsExchange(String isExchange) {
		this.isExchange = isExchange;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getRtmUser() {
		return rtmUser;
	}
	public void setRtmUser(String rtmUser) {
		this.rtmUser = rtmUser;
	}
	public String getRtmPassword() {
		return rtmPassword;
	}
	public void setRtmPassword(String rtmPassword) {
		this.rtmPassword = rtmPassword;
	}
	public String getSystemId() {
		return SystemId;
	}
	public void setSystemId(String systemId) {
		SystemId = systemId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getErrorPath() {
		return errorPath;
	}
	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}
	public int getSplitScanInter() {
		return splitScanInter;
	}
	public void setSplitScanInter(int splitScanInter) {
		this.splitScanInter = splitScanInter;
	}
	public int getDealThreadNum() {
		return dealThreadNum;
	}
	public void setDealThreadNum(int dealThreadNum) {
		this.dealThreadNum = dealThreadNum;
	}
	public int getDealScanInter() {
		return dealScanInter;
	}
	public void setDealScanInter(int dealScanInter) {
		this.dealScanInter = dealScanInter;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScanInter() {
		return scanInter;
	}
	public void setScanInter(int scanInter) {
		this.scanInter = scanInter;
	}
	public String getTransferProtocol() {
		return transferProtocol;
	}
	public void setTransferProtocol(String transferProtocol) {
		this.transferProtocol = transferProtocol;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRemotePath() {
		return remotePath;
	}
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getBakPath() {
		return bakPath;
	}
	public void setBakPath(String bakPath) {
		this.bakPath = bakPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getSplitNum() {
		return splitNum;
	}
	public void setSplitNum(int splitNum) {
		this.splitNum = splitNum;
	}
	@Override
	public String toString() {
		return "ServiceParam [scanInter=" + scanInter + ", transferProtocol="
				+ transferProtocol + ", addr=" + addr + ", user=" + user
				+ ", passWord=" + passWord + ", remotePath=" + remotePath
				+ ", localPath=" + localPath + ", bakPath=" + bakPath
				+ ", fileName=" + fileName + ", splitNum=" + splitNum + "]";
	}
	
	

}
