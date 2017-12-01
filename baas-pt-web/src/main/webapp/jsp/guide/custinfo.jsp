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
				<li><A id="sv_custinfo" href="${_base }/guide/custinfo" class="hover-bj">客户信息同步接口</A></li>
				<li><A id="sv_custordinfo" href="${_base }/guide/custordinfo">订购信息同步接口</A></li>
				<li><A id="sv_billinfo" href="${_base }/guide/billinfo">账单查询接口</A></li>
				<li><A id="sv_useageinfo" href="${_base }/guide/useageinfo">使用量查询接口</A></li>
             <li><A id="sv_feeowed" href="${_base }/guide/feeowed">欠费查询接口</A></li>
             </ul>
         </div>
         <!--右侧-->
         <div class="help-introduce-right">
           
             <div class="zhina-bj"> <!--悬浮-->
             <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li>接口介绍  >>  客户信息同步接口</li>
                 </ul>
                 <ul class="right">
                 	<c:choose>
                		<c:when test="${empty sessionScope.user_session_key.accountId || 0==sessionScope.user_session_key.accountId}">
                 	   		<li><A href="${_base }/guide/download/chk/custinfo"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
                 		</c:when>
	                    <c:otherwise>
	                    	<li><A href="${_base }/guide/download/custinfo"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
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
                     <li class="word">租户发起调用<br>同步租户系统内需要计费的客户资料信息。</li>
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
                    <td>srv_baas_custinfo_sync </td>
                  </tr>
                  <tr>
                    <td class="bj">调用入口</td>
                    <td>http://{api.rop.asiainfo.com}/occ/http/srv_baas_custinfo_sync </td>
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
                    <td>交易流水</td>
                    <td>客户信息通知请求 </td>
                    <td>tradeSeq </td>
                    <td>CustInfoNotify </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>组成：</br>租户ID + YYMMDDHH24MISS </br>+ SSS(毫秒) + 9位序列号</td>
                  </tr>                   
                  <tr>
                    <td>租户ID</td>
                    <td>客户信息通知请求 </td>
                    <td>tenantId </td>
                    <td>CustInfoNotify </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>见总册附录</td>
                  </tr>   
                   <tr>
                    <td>外部客户ID</td>
                    <td>客户信息通知请求 </td>
                    <td>extCustId </td>
                    <td>CustInfoNotify </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>外部值</td>
                  </tr>   
                   <tr>
                    <td>客户姓名</td>
                    <td>客户信息通知请求 </td>
                    <td>custName </td>
                    <td>CustInfoNotify </td>
                    <td>Y</td>
                    <td>VARCHAR(128)</td>
                    <td></td>
                  </tr>   
                   <tr>
                    <td>客户类型</td>
                    <td>客户信息通知请求 </td>
                    <td>custType </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(1)</td>
                    <td>取值范围：
					P：个人客户，
					G：集团客户
					</td>
                  </tr>   
                  
                  <tr>
                    <td>客户等级</td>
                    <td>客户信息通知请求 </td>
                    <td>custGrade </td>
                    <td>CustInfoNotify</td>
                    <td>N</td>
                    <td>VARCHAR(1)</td>
                    <td>取值范围：
							A,B,C,D,E
					</td>
                  </tr>                    
                   <tr>
                    <td>归属省</td>
                    <td>客户信息通知请求 </td>
                    <td>provinceCode </td>
                    <td>CustInfoNotify </td>
                    <td>Y</td>
                    <td>VARCHAR(6)</td>
                    <td>参考总册省份定义表</td>
                  </tr>   
                  <tr>
                    <td>归属地市</td>
                    <td>客户信息通知请求 </td>
                    <td>cityCode </td>
                    <td>CustInfoNotify </td>
                    <td>Y</td>
                    <td>VARCHAR(6)</td>
                    <td>以0开头的区号</td>
                  </tr>   
                  <tr>
                    <td>状态</td>
                    <td>客户信息通知请求 </td>
                    <td>state </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(4)</td>
                    <td></td>
                  </tr>   
                  <tr>
                    <td>状态变更时间</td>
                    <td>客户信息通知请求 </td>
                    <td>stateChgTime </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>YYYYMMDDHH24MISS</td>
                  </tr>   
                   <tr>
                    <td>扩展信息列表</td>
                    <td>客户信息通知请求 </td>
                    <td>extInfoList </td>
                    <td>CustInfoNotify </td>
                    <td>*</td>
                    <td>list</td>
                    <td>当这个字段有值，需要调用扩展因素</br>接口传递扩展因素</td>
                  </tr>   
                   <tr>
                    <td>名称</td>
                    <td>扩展信息列表 </td>
                    <td>extName </td>
                    <td>ExtInfoList </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>  </td>
                  </tr>   
                  <tr>
                    <td>值</td>
                    <td>扩展信息列表</td>
                    <td>extValue</td>
                    <td>ExtInfoList </td>
                    <td>Y</td>
                    <td>VARCHAR(64)</td>
                    <td>  </td>
                  </tr>  
                   <tr>
                    <td>更新标识</td>
                    <td>扩展信息列表 </td>
                    <td>updateFlag </td>
                    <td>ExtInfoList </td>
                    <td>Y</td>
                    <td>VARCHAR(1)</td>
                    <td>取值范围：D：删除，U：更新，N：新增
</td>
                  </tr>  
                  <tr>
                    <td>备注</td>
                    <td>客户信息通知请求 </td>
                    <td>remark </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(1024)</td>
                    <td></td>
                  </tr>   
                  <tr>
                    <td>联系电话</td>
                    <td>客户信息通知请求 </td>
                    <td>contactNo </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                  <tr>
                    <td>邮箱</td>
                    <td>客户信息通知请求 </td>
                    <td>email </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                  <tr>
                    <td>地址</td>
                    <td>客户信息通知请求 </td>
                    <td>custAddress </td>
                    <td>CustInfoNotify </td>
                    <td>N</td>
                    <td>VARCHAR(128)</td>
                    <td></td>
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
                    <td>响应报文头</td>
                    <td>returnCode </td>
                    <td>responseHeader </td>
                    <td>Y</td>
                    <td>VARCHAR(14)</td>
                    <td>BaaS-000000成功；其他失败</td>
                  </tr>                   
                  <tr>
                    <td>返回描述 </td>
                    <td>响应报文头</td>
                    <td>returnDesc </td>
                    <td>responseHeader </td>
                    <td>Y</td>
                    <td>VARCHAR(14)</td>
                    <td>BaaS-000000成功；其他失败</td>
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
       
       
	<p>		POST http://{api.rop.asiainfo.com}/occ/http/srv_baas_custinfo_sync?[TOKEN] HTTP/1.1      </p>
	<p>		Accept-Encoding: gzip,deflate                                                            </p>
	<p>		Content-Type: application/json                                                           </p>
	<p>		sign: xxcde52a0aacvaefa93c6xxxx7bxxddc                                                   </p>
	<p>		appkey: xxxde52a0axxxaefa93c6xxxx7bxxx60                                                 </p>
	<p>		Content-Length: #JSON消息长度                                                            </p>
	<p>		{                                                                                     </p>
	<p class="text-ind">		    "tradeSeq": "MVNE20160401121212000100000002",                                        </p>
	<p class="text-ind">		    "tenantId": "MVNE",                                                                  </p>
	<p class="text-ind">		    "extCustId": "10101111",                                                             </p>
	<p class="text-ind" >		    "custName": "张三",                                                                  </p>
	<p class="text-ind">		    "custType": "P",                                                                     </p>
	<p class="text-ind">		    "custGrade": "C",                                                                    </p>
	<p class="text-ind">		    "provinceCode": "11",                                                                </p>
	<p class="text-ind">		    "cityCode": "010",                                                                   </p>
	<p class="text-ind">		    "state": "1",                                                                        </p>
	<p class="text-ind">		    "stateChgTime": "",                                                                  </p>
	<p class="text-ind">		    "remark": "",                                                                        </p>
	<p class="text-ind">		    "contactNo": "13900000000",                                                          </p>
	<p class="text-ind">		    "email": "test@test.com",                                                            </p>
	<p class="text-ind">		    "custAddress": "",                                                                   </p>
	<p class="text-ind">		    "extInfoList": [                                                                     </p>
	<p class="text-ind-a">		        {                                                                                </p>
	<p class="text-ind-b">		            "extName": "fileId",                                                         </p>
	<p class="text-ind-b">		            "extValue": "101101111111",                                                  </p>
	<p class="text-ind-b">		            "updateFlag": "N"                                                            </p>
	<p class="text-ind-a">		        }                                                                                </p>
	<p class="text-ind">		    ]                                                                                    </p>
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
       <p class="text-ind">"responseHeader":{</p>
       <p class="text-ind-a">"returnCode":"BaaS-000000",",</p>
       <p class="text-ind-a">"returnDesc":""</p>
       <p class="text-ind">}</p>
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
