package com.ai.baas.pt.web.constants;

public final class HomePageConstants {
	private HomePageConstants(){}
	
	public static final class SendEmail{
		private SendEmail(){}
		
		/**
		 * 缓存命名空间 
		 */
		public static String CACHE_NAMESPACE = "com.ai.baas.pt.web.home.sendEmail";
		
		/**
		 * 发送次数缓存key
		 */
		public static String SEND_EMAIL_NO = "homepage-consult-sendemail-num";
		
		/**
		 * 一个ip发送询问的记录频率时间
		 */
		public static String IP_SEND_OVERTIME_KEY = "/ip-home-send-consult-overtime";
		
		/**
		 * 一个ip规定时间内的发送询问的最大频率
		 */
		public static String IP_SEND_MAX_NO_KEY = "/ip-home-send-consult-maxno";
		
		/**
		 * 客服邮件地址
		 */
		public static String RECEIVE_EMAIL_KEY = "/send-consult-receive-email";
		
		/**
		 * 邮件模型地址
		 */
		public static String TEMPLATE_EMAIL_URL = "email/template/consult-mail.xml";
		
	}
}
