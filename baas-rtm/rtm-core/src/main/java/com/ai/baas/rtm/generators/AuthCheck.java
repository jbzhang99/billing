package com.ai.baas.rtm.generators;

import org.apache.log4j.Logger;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.initload.McsClient;

public class AuthCheck {
	private String tenant;
	private String user;
	private String passwd; 
	private int type;
	private String jsBsn;
	public AuthCheck(String tenant,String user,String passwd){
		this.tenant=tenant;
		this.user=user;
		this.passwd=passwd;
	}
	public  String check(){
		StringBuilder authKey=new StringBuilder();
		authKey.append(tenant.trim()).append(RtmConstants.KEY_JOINER).append(user.trim());
		String passWd=McsClient.client.get(authKey.toString());
		if(passWd==null)
			//return "鉴权失败......";
			return "0000002";
		else {
			if(passwd.equals(passWd)){
					return "0000000";
			}
			
			else {
				return "0000002";
			}
		}
		
	}

}
