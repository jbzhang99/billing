package com.ai.runner.center.ctp.rtm.core.db.entity;

import java.sql.Timestamp;

public class SingleFileInfo {
    private Long serialId;

    private String sysId;

    private String tenantId;

    private String compressPath;

    private String backupPath;

    private String destPath;

    private String filename;

    private String havarequestoperator;

    private String url;

    private String filetime;

    private String appid;

    private String httpclientpwd;

    private String transid;

    private String token;

    private String appkey;

    private String sign;

    private String fileunzippwd;

    private String requestParam;

    private String responseResult;

    private Timestamp createTime;
    
    private String isSuccessUnzip;

    private String unzipFailException;

    public Long getSerialId() {
        return serialId;
    }

    public void setSerialId(Long serialId) {
        this.serialId = serialId;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath == null ? null : compressPath.trim();
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath == null ? null : backupPath.trim();
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath == null ? null : destPath.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getHavarequestoperator() {
        return havarequestoperator;
    }

    public void setHavarequestoperator(String havarequestoperator) {
        this.havarequestoperator = havarequestoperator == null ? null : havarequestoperator.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getFiletime() {
        return filetime;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime == null ? null : filetime.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getHttpclientpwd() {
        return httpclientpwd;
    }

    public void setHttpclientpwd(String httpclientpwd) {
        this.httpclientpwd = httpclientpwd == null ? null : httpclientpwd.trim();
    }

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid == null ? null : transid.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getFileunzippwd() {
        return fileunzippwd;
    }

    public void setFileunzippwd(String fileunzippwd) {
        this.fileunzippwd = fileunzippwd == null ? null : fileunzippwd.trim();
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult == null ? null : responseResult.trim();
    }

    public Timestamp getCreateTime() {
    	return createTime == null ? null : new Timestamp(createTime.getTime());
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = (createTime == null ? null : new Timestamp(
        		createTime.getTime()));
    }

	public String getIsSuccessUnzip() {
		return isSuccessUnzip;
	}

	public void setIsSuccessUnzip(String isSuccessUnzip) {
		this.isSuccessUnzip = isSuccessUnzip;
	}

	public String getUnzipFailException() {
		return unzipFailException;
	}

	public void setUnzipFailException(String unzipFailException) {
		this.unzipFailException = unzipFailException;
	}
    
    
}