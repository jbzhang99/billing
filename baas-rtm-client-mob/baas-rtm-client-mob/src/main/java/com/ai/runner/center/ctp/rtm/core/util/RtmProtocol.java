package com.ai.runner.center.ctp.rtm.core.util;

public enum RtmProtocol {

	FILE("file"),WEBSERVICE("ws"),FTP("ftp");
	
	private String name;
	
	private RtmProtocol(String name){
		this.name = name;
	}
	
	public String getName() {
	    return name;
	}
	
}
