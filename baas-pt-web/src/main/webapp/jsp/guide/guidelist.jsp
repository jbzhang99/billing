<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
<%@ include file="/inc/inc.jsp"%>
<title>接入指南</title>
<meta name="viewport" content="width=device-width; initial-scale=1; user-scalable=1;" />
<link href="${_base}/resources/baaspt/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/frame.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/global.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/modular.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="topnav"><!--头部-->
    <div class="topnav-main">
        <%@ include file="/inc/topnav-main-ul.jsp"%>
    </div>
</div>

<div class="nav-bg">
	 <div class="er-subnav"><!--导航-->
	            <%@ include file="/inc/er-subnav-ul.jsp"%>
	 </div><!-- 导航结束-->
 </div>
<!--接入指南-->
 <div class="help-introduce">
     <div class="help-introduce-cnt">
         <div class="help-introduce-left"><!--左边-->
             <ul>
                 <li class="bt"><A id="feature_list" href="${_base }/guide/list" class="hover-bj"><span></span>接口介绍</A></li>
				<li><A id="sv_custinfo" href="${_base }/guide/custinfo" >客户信息同步接口</A></li>
				<li><A id="sv_custordinfo" href="${_base }/guide/custordinfo">订购信息同步接口</A></li>
				<li><A id="sv_billinfo" href="${_base }/guide/billinfo">账单查询接口</A></li>
				<li><A id="sv_useageinfo" href="${_base }/guide/useageinfo">使用量查询接口</A></li>
				<li><A id="sv_feeowed" href="${_base }/guide/feeowed">欠费查询接口</A></li>
             </ul>
         </div>
         <!--右侧-->
         <div class="help-introduce-right">
             <div class="help-introduce-right-tit">
                 <ul class="left">
                     <li class="bt">接口介绍</li>
                     <li class="word">实时接口是一种以实时数据处理进行交互的接入方式，在亚信云计费中包含同步、异步两种方式。</li>
                 </ul>
                 <ul class="right">
                 		<c:choose>
                		<c:when test="${empty sessionScope.user_session_key.accountId || 0==sessionScope.user_session_key.accountId}">
                 	   		<li><A href="${_base }/guide/download/chk/list"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
                 		</c:when>
	                    <c:otherwise>
	                    	<li><A href="${_base }/guide/download/opt-billing"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
	                    </c:otherwise>
	                </c:choose>
                 </ul>
             </div>
             
              <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="bt">API概览</li>
                 </ul>
             </div>
             <div class="help-introduce-right-table">
             <table width="100%" border="0">
                  <tr class="bt">
                    <td>API接口</td>
                    <td>说明</td>
                    <td>起步</td>
                  </tr>
                  <tr>
                    <td>客户信息同步接口</td>
                    <td>同步企业客户系统内需要计费的客户资料信息 </td>
                    <td><a href="${_base }/guide/custinfo"><i class="icon-search"></i>查看详情</a></td>
                  </tr>
                  <tr>
                    <td>订购信息同步接口</td>
                    <td>实现企业客户系统内客户订购关系信息同步</td>
                    <td><a href="${_base }/guide/custordinfo"><i class="icon-search"></i>查看详情</a></td>
                  </tr>
                  <tr>
                    <td>账单查询接口</td>
                    <td>实现企业客户系统内客户账单查询服务</td>
                    <td><a href="${_base }/guide/billinfo"><i class="icon-search"></i>查看详情</a></td>
                  </tr>
                  <tr>
                    <td>使用量查询接口</td>
                    <td>实现企业系统内客户使用量信息查询服务</td>
                    <td><a href="${_base }/guide/useageinfo"><i class="icon-search"></i>查看详情</a></td>
                  </tr>
                  <tr>
                    <td>欠费查询接口</td>
                    <td>实现租户系统内欠费信息查询服务</td>
                    <td><a href="${_base }/guide/feeowed"><i class="icon-search"></i>查看详情</a></td>
                  </tr>
  			  </table>

             </div>
         </div>
 
     </div>
 </div>






<!--footer-->
<%@ include file="/inc/foot.jsp"%>

</body>
</html>
