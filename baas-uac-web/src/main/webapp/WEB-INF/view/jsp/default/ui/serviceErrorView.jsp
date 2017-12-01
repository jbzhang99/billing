<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-cn">
<head>
<%@ include file="/inc/inc.jsp"%>
<title>中信云平台单点登录</title>
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
</head>
<body>
<%@include file="/inc/head.jsp" %>

<div class="loginCon">
  <img class="loginadv" src="${_baasBase}/images/loginadv1.png" alt="">
  <div id="msg" class="errors" style="width: 40%;height: 500px;float: left;padding-top: 100px;">
    <h2><spring:message code="screen.service.error.header" /></h2>
    <p style="margin-top: 20px;"><spring:message code="${rootCauseException.code}" /></p>
  </div>
</div>
<%@include file="/inc/foot.jsp" %>
</body>
</html>
