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
<script type="text/javascript" src="${_base}/resources/baaspt/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${_base}/resources/baaspt/js/frame.js"></script>
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
                  <li class="bt"><A id="feature_list" href="${_base }/guide/list" ><span></span>接口介绍</A></li>
				<li><A id="sv_custinfo" href="${_base }/guide/custinfo" >客户信息同步接口</A></li>
				<li><A id="sv_custordinfo" href="${_base }/guide/custordinfo">订购信息同步接口</A></li>
				<li><A id="sv_billinfo" href="${_base }/guide/billinfo">账单查询接口</A></li>
				<li><A id="sv_useageinfo" href="${_base }/guide/useageinfo">使用量查询接口</A></li>
                <li><A id="sv_feeowed" href="${_base }/guide/feeowed" class="hover-bj">欠费查询接口</A></li>
             </ul>
         </div>
         <!--右侧-->
         <div class="help-introduce-right">
           
             <div class="zhina-bj"> <!--悬浮-->
             <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li>接口介绍  >>  欠费查询接口</li>
                 </ul>
                 <ul class="right">
                 	<c:choose>
                		<c:when test="${empty sessionScope.user_session_key.accountId || 0==sessionScope.user_session_key.accountId}">
                 	   		<li><A href="${_base }/guide/download/chk/feeowed"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
                 		</c:when>
	                    <c:otherwise>
	                    	<li><A href="${_base }/guide/download/feeowed"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
	                    </c:otherwise>
	                </c:choose>
                 </ul>
             </div>
            <div class="help-introduce-post">
                <ul>
                <li><a href="#y1"><i class="icon-circle"></i>应用场景</a></li>
                <p></p>
                <li><a href="#y2"><i class="icon-circle"></i>请求说明</a></li>
                <p></p>
                <li><a href="#y3"><i class="icon-circle"></i>输入参数</a></li>
                <p></p>
                <li><a href="#y4"><i class="icon-circle"></i>返回结果</a></li>
                <p></p>
                <li><a href="#y5"><i class="icon-circle"></i>示例</a></li>
                </ul>
            </div>
             </div><!--悬浮结束-->
             
          <div class="as">
          <div class="help-introduce-right-tit" >
                 <ul class="left">
                     <li class="bt" id="y1" >应用场景</li>
                     <li class="word">租户系统发起调用<br>实现租户系统内欠费信息查询服务。</li>
                 </ul>
             </div>     

        <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="bt"  id="y2">请求说明</li>
                 </ul>
         </div>        
        <div class="help-introduce-right-table">
             <table width="100%" border="0">
                  <tr>
                    <td class="bj">使用方</td>
                    <td>租户系统</td>
                  </tr>
                  <tr>
                    <td class="bj">API名称</td>
                    <td>srv_baas_feeowed_qry </td>
                  </tr>
                  <tr>
                    <td class="bj">调用入口</td>
                    <td>http://{api.rop.asiainfo.com}/occ/http/srv_baas_feeowed_qry</td>
                  </tr>
                  <tr>
                    <td class="bj">HTTP请求方式</td>
                    <td>HTTP-POST</td>
                  </tr>
                  <tr>
                    <td class="bj">格式</td>
                    <td>JSON</td>
                  </tr>
                  <tr>
                    <td class="bj">API授权类型</td>
                    <td>需要授权</td>
                  </tr>
                  <tr>
                    <td class="bj">类型</td>
                    <td>基础服务</td>
                  </tr>
                   <tr>
                    <td class="bj">版本</td>
                    <td>v1</td>
                  </tr> 
  			  </table>
        </div>  
        <div class="dashed"></div>
       <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="bt"  id="y3">输入参数--<span>应用级输入参数</span></li>
                 </ul>
       </div> 
       
      <div class="help-introduce-right-table">
             <table width="100%" border="0">
                  <tr class="bt">
                    <td>节点名称</td>
                    <td>父节点名称</td>
                    <td>节点编码</td>
                    <td>父节点编码</td>
                    <td>必选</td>
                    <td>类型</td>
                    <td>说明</td>
                  </tr>
                  <tr>
                    <td>消息流水</td>
                    <td>欠费查询请求 </td>
                    <td>tradeSeq </td>
                    <td>oweFeeQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>组成：</br>租户ID + YYMMDDHH24MISS</br> + SSS(毫秒) + 9位序列号</td>
                  </tr>                   
                  <tr>
                    <td>租户ID</td>
                    <td>欠费查询请求 </td>
                    <td>tenantId </td>
                    <td>oweFeeQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                   <tr>
                    <td>外部客户ID</td>
                    <td>欠费查询请求 </td>
                    <td>extCustId </td>
                    <td>oweFeeQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                   <tr>
                    <td>服务号码</td>
                    <td>欠费查询请求</td>
                    <td>serviceId </td>
                    <td>oweFeeQuery</td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                  <tr>
                    <td>开始时间</td>
                    <td>欠费查询请求 </td>
                    <td>beginDate </td>
                    <td>oweFeeQuery </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>缺省是系统时间，</br>格式：YYYYMMDDHH24MISS</td>
                  </tr>   
                  <tr>
                    <td>结束时间</td>
                    <td>欠费查询请求 </td>
                    <td>endDate </td>
                    <td>oweFeeQuery </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>缺省是系统时间，</br>格式：YYYYMMDDHH24MISS</td>
                  </tr>   
                 
  			  </table>
             </div>    
       <div class="dashed"></div>
       <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="bt"  id="y4">返回结果</li>
                 </ul>
       </div>   
        <div class="help-introduce-right-table">
             <table width="100%" border="0">
                  <tr class="bt">
                    <td>节点名称</td>
                    <td>父节点名称</td>
                    <td>节点编码</td>
                    <td>父节点编码</td>
                    <td>必选</td>
                    <td>类型</td>
                    <td>说明</td>
                  </tr>
                  <tr>
                    <td>返回码 </td>
                    <td>响应报文</td>
                    <td>returnCode </td>
                    <td>responseMessage</td>
                    <td>Y</td>
                    <td>VARCHAR(14)</td>
                    <td>BaaS-000000成功；其他失败</td>
                  </tr>                   
                  <tr>
                    <td>消息流水 </td>
                    <td>响应报文</td>
                    <td>tradeSeq</td>
                    <td>responseMessage</td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>     
                   <tr>
                    <td>外部客户ID</td>
                    <td>响应报文</td>
                    <td>extCustId</td>
                    <td>responseMessage</td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>外部客户ID</td>
                  </tr>                   
                  <tr>
                    <td>服务标识 </td>
                    <td>响应报文</td>
                    <td>ServiceId</td>
                    <td>responseMessage</td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td>服务号码</td>
                  </tr> 
                   <tr>
                    <td>欠费费用 </td>
                    <td>响应报文</td>
                    <td>feeOwed</td>
                    <td>responseMessage</td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td>单位：厘，整数。</br>计费中心在接口中转换时不足1厘</br>的小数部分四舍五入</td>
                  </tr>  
                  <tr>
                    <td>实时费用 </td>
                    <td>响应报文</td>
                    <td>realTimeFee</td>
                    <td>responseMessage</td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td>单位：厘，整数。</br>计费中心在接口中转换时不足1厘</br>的小数部分四舍五入</td>
                  </tr>
                    <tr>
                    <td>开始时间 </td>
                    <td>响应报文</td>
                    <td>beginDate</td>
                    <td>responseMessage</td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>格式：</br>YYYYMMDDHH24MISS</td>
                  </tr>  
                  <tr>
                    <td>结束时间 </td>
                    <td>响应报文</td>
                    <td>endDate</td>
                    <td>responseMessage</td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>格式：</br>YYYYMMDDHH24MISS</td>
                  </tr>          
  			  </table>
             </div>    
       <div class="dashed"></div>
       <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="bt"  id="y5">示例</span></li>
                     <li class="word">请求示列:</li>
                 </ul>
       </div> 
       <div class="shilie">
       
       
	<p>		POST http://{api.rop.asiainfo.com}/occ/http/srv_baas_feeowed_qry?[TOKEN] HTTP/1.1      </p>
	<p>		Accept-Encoding: gzip,deflate                                                            </p>
	<p>		Content-Type: application/json                                                           </p>
	<p>		sign: xxxdesdfasdfasdfasdf6xxxx7bxxx60                                                   </p>
	<p>		appkey: xxxde52a0axxxaefa93c6xxxx7bxxx60                                                 </p>
	<p>		Content-Length: #JSON消息长度                                                            </p>
	<p>		{                                                                                     </p>
	<p class="text-ind">		    "tradeSeq": "MVNE20160401121212000100000005",                                        </p>
	<p class="text-ind">		    "tenantId": "MVNE",                                                                  </p>
	<p class="text-ind">		    "extCustId": "10010111",                                                             </p>
	<p class="text-ind" >		    "serviceId": "1001",                                                                  </p>
	<p class="text-ind">		    "beginDate": "20160101000000",                                                                     </p>
	<p class="text-ind">		    "endDate": "20160501000000"                                                                    </p>
	                                                                                 
	<p>		}                                                                                        </p>
       
       
       </div>
        <div class="dashed"></div>
       <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="word">返回示列:</li>
                 </ul>
       </div> 
       <div class="shilie">
       
       
       <p>{</p>
       <p class="text-ind">"returnCode": "BaaS-000000",</p>
       <p class="text-ind">"tradeSeq": " MVNE20160401121212000100000005",</p>
       <p class="text-ind">"extCustId": "10010111",</p>
       <p class="text-ind"> "serviceId": "1001",</p>
       <p class="text-ind">"feeOwed": "99000",</p>
       <p class="text-ind">"realTimeFee": "19000",</p>
       <p class="text-ind">"beginDate": "20160101000000",</p>
       <p class="text-ind">"endDate": "20160501000000"</p>
       <p>}</p>
       
       
       
       
       </div>
       
       </div>  
             
         </div>
 
     </div>
 </div>






<!--footer-->
<%@ include file="/inc/foot.jsp"%>

</body>
</html>
