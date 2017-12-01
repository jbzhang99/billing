<!DOCTYPE html>
<%@page import="com.ai.opt.uac.web.constants.Constants"%>
<%@page import="com.ai.opt.sdk.components.mcs.MCSClientFactory"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Date"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-cn">
<head>
<%@ include file="/inc/inc.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${_baasBase }/images/citic.ico">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>登录</title>
	<script type="text/javascript" src="${_baasBase }/js/datacheck.js" ></script>
	<script language="javascript" src="${pageContext.request.contextPath}/resources/spm_modules/app/login/casLoginView.js"></script>  
	
</head>

<body>
<%@include file="/inc/head.jsp" %>
	
<div class="loginCon">
<form:form method="post" id="fm1" name="fm1" commandName="${commandName}" htmlEscape="true">

<img class="loginadv" src="${_baasBase }/images/loginadv1.png" alt="">
	<div class="loginForm">
			<span class="loginTitle">用户登录</span>
			<hr size="2px" style="margin-top:36px">
			
			<ul>
				<li>
					<form:input  style="width: 300px; height: 36px;" cssClass="input-text" cssErrorClass="error" id="username" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" placeholder="登录名/公司邮箱/手机号"/>
				<li>
					<form:password  style="width: 300px; height: 36px;" cssClass="input-text" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off"  placeholder="密码" onkeydown="encryptPwd(event)"/>
				<li >
					<input type="text" class="input-text" id="captchaCode" 	style="height: 36px; width: 62%;"
								tabindex="3" name="captchaCode" path="captchaCode" onkeydown="encryptCaptcha(event)"
								placeholder="验证码"> 
					<img title="点击重新获取验证码" style="vertical-align: middle;"	src="${_base}/captcha/getImageVerifyCode" id="pictureVitenfy" onclick="reloadImage('${_base}/captcha/getImageVerifyCode');">
				
				</li>
				<li style="display:none;">
					<input id="rememberMe" name="rememberMe" type="checkbox" >
				</li>
			</ul>
			<div class="loginErr" id="loginErr"><span id="errorMsg"><form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" /></span><a href="">点击找回密码</a><a id="errclose"> &nbsp;</a></div>
			<div id="loginBtn" class="loginBtn">
				<input type="button" value="登录" accesskey="l" tabindex="4" onclick="javascript:dologin();">
            </div>
	</div>
	
	
	
	 <input type="hidden" name="lt" value="${loginTicket}" />
   	 <input type="hidden" name="execution" value="${flowExecutionKey}" />
   	 <input type="hidden" name="_eventId" value="submit" />
   	 <input type="hidden" name="sessionId" value="<%=request.getSession().getId()%>"/>
	</form:form>
</div>
	

   <%@include file="/inc/foot.jsp" %>
</body>
</html>
