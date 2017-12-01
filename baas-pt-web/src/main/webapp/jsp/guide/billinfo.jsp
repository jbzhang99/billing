<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<!--Support IE Text -->
<!doctype html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
<%@ include file="/inc/inc.jsp"%>
<title>接入指南</title>
<meta name="viewport" content="width=device-width; initial-scale=1; user-scalable=1;" />
<link href="${_base}/resources/baaspt/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/frame.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/global.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/modular.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/baas-pt.css" rel="stylesheet" type="text/css">
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
				<li><A id="sv_billinfo" href="${_base }/guide/billinfo" class="hover-bj">账单查询接口</A></li>
				<li><A id="sv_useageinfo" href="${_base }/guide/useageinfo">使用量查询接口</A></li>
            <li><A id="sv_feeowed" href="${_base }/guide/feeowed">欠费查询接口</A></li>
             </ul>
         </div>
         <!--右侧-->
         <div class="help-introduce-right">
           
             <div class="zhina-bj"> <!--悬浮-->
             <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li>接口介绍  >>  账单信息查询接口</li>
                 </ul>
                 <ul class="right">
                 		<c:choose>
                		<c:when test="${empty sessionScope.user_session_key.accountId || 0==sessionScope.user_session_key.accountId}">
                 	   		<li><A href="${_base }/guide/download/chk/billinfo"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
                 		</c:when>
	                    <c:otherwise>
	                    	<li><A href="${_base }/guide/download/billinfo"><span><img src="${_baaspt }/images/dow.png"></span><span>下载接口介绍</span></A></li>
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
                     <li class="word">租户系统发起调用<br>实现租户系统内客户账单查询服务。</li>
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
                    <td>srv_baas_billinfo_qry </td>
                  </tr>
                  <tr>
                    <td class="bj">调用入口</td>
                    <td>http://{api.rop.asiainfo.com}/occ/http/srv_baas_billinfo_qry</td>
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
                    <td>账单查询请求 </td>
                    <td>tradeSeq </td>
                    <td>BillQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>组成：</br>租户ID + YYMMDDHH24MISS</br> + SSS(毫秒) + 9位序列号</td>
                  </tr>                   
                  <tr>
                    <td>租户ID</td>
                    <td>账单查询请求 </td>
                    <td>tenantId </td>
                    <td>BillQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                   <tr>
                    <td>外部客户ID</td>
                    <td>账单查询请求 </td>
                    <td>extCustId </td>
                    <td>BillQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>   
                   <tr>
                    <td>服务标识</td>
                    <td>账单查询请求 </td>
                    <td>serviceId </td>
                    <td>BillQuery </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>
                    </td>
                  </tr>   
                  <tr>
                    <td>是否分页</td>
                    <td>账单查询请求 </td>
                    <td>paging </td>
                    <td>BillQuery </td>
                    <td>Y</td>
                    <td>NUMBER(1)</td>
                    <td>是否分页 <br>
					NO：表示不支持分页查询全部<br>
					YES：表示支持分页按照分页参数查询
                    </td>
                  </tr>   
                  <tr>
                    <td>页码</td>
                    <td>账单查询请求 </td>
                    <td>pageNumber </td>
                    <td>BillQuery </td>
                    <td>N</td>
                    <td>NUMBER(9)</td>
                    <td>
                    </td>
                  </tr>   
                  <tr>
                    <td>每页条数</td>
                    <td>账单查询请求 </td>
                    <td>pagecountNumber </td>
                    <td>BillQuery </td>
                    <td>N</td>
                    <td>NUMBER(9)</td>
                    <td>
                    </td>
                  </tr>   
                   <tr>
                    <td>查询开始时间</td>
                    <td>账单查询请求 </td>
                    <td>queryStartTime </td>
                    <td>BillQuery </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>查询开始时间，</br>格式YYYYMMDDHH24MISS，<br>例如：20151001000000。<br>
返回的结果是从这个开始时间<br>到结束账期的所有账期的账单。
<br>缺省是系统时间


					</td>
                  </tr>   
                  
                  <tr>
                    <td>查询结束时间</td>
                    <td>账单查询请求 </td>
                    <td>queryEndTime </td>
                    <td>BillQuery </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td>缺省是系统时间,格式同上
					</td>
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
                    <td>responseMessage </td>
                    <td>Y</td>
                    <td>VARCHAR(14)</td>
                    <td>BaaS-000000成功；其他失败</td>
                  </tr>                   
                  <tr>
                    <td>消息流水</td>
                    <td>响应报文头</td>
                    <td>tradeSeq </td>
                    <td>responseMessage </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>等于请求报文中的消息流水</td>
                  </tr>                   
                  <tr>
                    <td>租户ID</td>
                    <td>响应报文头</td>
                    <td>tenantId </td>
                    <td>responseMessage </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>                   
                  <tr>
                    <td>外部客户ID</td>
                    <td>响应报文头</td>
                    <td>extCustId </td>
                    <td>responseMessage </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>                   
                  <tr>
                    <td>服务标识列表</td>
                    <td>响应报文头</td>
                    <td>serviceIdList </td>
                    <td>responseMessage </td>
                    <td>*</td>
                    <td>list</td>
                    <td>可以是空链表</td>
                  </tr> 
                   <tr>
                    <td>服务标识ID</td>
                    <td>响应报文头</td>
                    <td>serviceId</td>
                    <td>serviceIDList </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td></td>
                  </tr>                  
                  <tr>
                    <td>账单列表</td>
                    <td>响应报文头</td>
                    <td>billList </td>
                    <td>serviceIDList </td>
                    <td>*</td>
                    <td>list</td>
                    <td>可以是空链表，按账期的账单列表</td>
                  </tr>   
                  <tr>
                    <td>账期</td>
                    <td>响应报文头</td>
                    <td>billDuration </td>
                    <td>billList </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>月账期：YYYYMM</td>
                  </tr>                 
                  <tr>
                    <td>原始费用</td>
                    <td>响应报文头</td>
                    <td>orgFee </td>
                    <td>billList </td>
                    <td>N</td>
                    <td>NUMBER(32)</td>
                    <td></td>
                  </tr>                 
                  <tr>
                    <td>优惠费用</td>
                    <td>响应报文头</td>
                    <td>disFee </td>
                    <td>billList </td>
                    <td>N</td>
                    <td>NUMBER(32)</td>
                    <td>折扣费用，单位：厘，整数。<br>计费中心在接口中转换时<br>不足1厘的小数部分四舍五入</td>
                  </tr>                 
                  <tr>
                    <td>调整费用</td>
                    <td>响应报文头</td>
                    <td>adjustFee </td>
                    <td>billList </td>
                    <td>N</td>
                    <td>NUMBER(32)</td>
                    <td>调整费用，单位：厘，整数。</br>计费中心在接口中转换时不足1厘的</br>小数部分四舍五入。</br>正数为调减、负数为调增，0为未调整。</td>
                  </tr>                 
                  <tr>
                    <td>总费用</td>
                    <td>响应报文头</td>
                    <td>totalfee </td>
                    <td>billList </td>
                    <td>Y</td>
                    <td>NUMBER(32)</td>
                    <td>该用户本月账单总额。<br>单位：厘，整数。<br>计费中心在接口中转换时<br>不足1厘的小数部分四舍五入</td>
                  </tr>                 
                  <tr>
                    <td>账单明细列表</td>
                    <td>响应报文头</td>
                    <td>subjcetDetailList </td>
                    <td>billList </td>
                    <td>*</td>
                    <td>List</td>
                    <td>可以是空链表，按科目的维度列出账单明细</td>
                  </tr>                 
                  <tr>
                    <td>科目</td>
                    <td>响应报文头</td>
                    <td>subjectId </td>
                    <td>subjcetDetailList </td>
                    <td>Y</td>
                    <td>VARCHAR(32)</td>
                    <td>按服务方约定填写</td>
                  </tr>                 
                  <tr>
                    <td>科目原始费用</td>
                    <td>响应报文头</td>
                    <td>subjectOrgFee </td>
                    <td>subjcetDetailList </td>
                    <td>N</td>
                    <td>NUMBER(32)</td>
                    <td></td>
                  </tr>                 
                  <tr>
                    <td>科目优惠费用</td>
                    <td>响应报文头</td>
                    <td>subjectDisFee </td>
                    <td>subjcetDetailList </td>
                    <td>N</td>
                    <td>NUMBER(32)</td>
                    <td>单位：厘，整数。<br>计费中心在接口中转换时<br>不足1厘的小数部分四舍五入</td>
                  </tr>                 
                  <tr>
                    <td>科目调整费用</td>
                    <td>响应报文头</td>
                    <td>subjcetAdjustFee </td>
                    <td>subjcetDetailList </td>
                    <td>N</td>
                    <td>NUMBER(32)</td>
                    <td>单位：厘，整数。<br>计费中心在接口中转换时<br>不足1厘的小数部分四舍五入</td>
                  </tr>                 
                  <tr>
                    <td>科目费用</td>
                    <td>响应报文头</td>
                    <td>subjectFee </td>
                    <td>subjcetDetailList </td>
                    <td>Y</td>
                    <td>NUMBER(32)</td>
                    <td>单位：厘，整数。<br>计费中心在接口中转换时<br>不足1厘的小数部分四舍五入</td>
                  </tr>                 
                  <tr>
                    <td>当前页码</td>
                    <td>响应报文头</td>
                    <td>pageNumber </td>
                    <td>responseMessage </td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td>当前页码</td>
                  </tr>                 
                  <tr>
                    <td>总页码</td>
                    <td>响应报文头</td>
                    <td>totalCount </td>
                    <td>responseMessage </td>
                    <td>N</td>
                    <td>VARCHAR(32)</td>
                    <td>总条数(billList的条数)</td>
                  </tr>                 
                  <tr>
                    <td>查询开始时间</td>
                    <td>响应报文头</td>
                    <td>queryStartTime </td>
                    <td>responseMessage </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td></td>
                  </tr>                 
                  <tr>
                    <td>查询结束时间</td>
                    <td>响应报文头</td>
                    <td>queryEndTime </td>
                    <td>responseMessage </td>
                    <td>N</td>
                    <td>VARCHAR(14)</td>
                    <td></td>
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
       
       
	<p>		POST http://{api.rop.asiainfo.com}/occ/http/srv_baas_billinfo_qry?[TOKEN] HTTP/1.1   </p>
	<p>		Accept-Encoding: gzip,deflate                                                        </p>
	<p>		Content-Type: application/json                                                       </p>
	<p>		sign: 123123                                                                         </p>
	<p>		appkey: xxxde52a0axxxaefa93c6xxxx7bxxx60                                             </p>
	<p>		Content-Length: #JSON消息长度                                                        </p>
	<p>		{                                                                                    </p>
	<p class="text-ind">		    "tradeSeq":"MVNE20160401121212000100000003",                                     </p>
	<p class="text-ind">		    "tenantId":"MVNE",                                                               </p>
	<p class="text-ind">		    "extCustId":"10010111",                                                          </p>
	<p class="text-ind">		    "serviceId":"1001",                                                              </p>
	<p class="text-ind">		    "paging":"YES",                                                                  </p>
	<p class="text-ind">		    "pageNumber":"2",                                                                </p>
	<p class="text-ind">		    "pagecountNumber":"10",                                                          </p>
	<p class="text-ind">		    "queryStartTime":"20151001000000",                                               </p>
	<p class="text-ind">		    "queryEndTime":"20161001000000"                                                  </p>
	<p>		}                                                                                    </p>
       
       
       </div>
        <div class="dashed"></div>
       <div class="help-introduce-right-tit border-none-none">
                 <ul class="left">
                     <li class="word">返回示列:</li>
                 </ul>
       </div> 
       <div class="shilie">
       
	<p>			{                                                                  </p>
	<p class="text-ind">			    "returnCode": "BaaS-000000",                                   </p>
	<p class="text-ind">			    "tradeSeq": "MVNE20160401121212000100000003",                  </p>
	<p class="text-ind">			    "tenantId": "MVNE",                                            </p>
	<p class="text-ind">			    "extCustId": "10010111",                                       </p>
	<p class="text-ind">			    "serviceIdList": [                                             </p>
	<p class="text-ind-a">			        {                                                          </p>
	<p class="text-ind-b">			            "serviceId": "1001",                                   </p>
	<p class="text-ind-b">			            "billList": [                                          </p>
	<p class="text-ind-c">			                {                                                  </p>
	<p class="text-ind-d">			                    "billDuration": "201601",                      </p>
	<p class="text-ind-d">			                    "orgFee": "99000",                             </p>
	<p class="text-ind-d">			                    "disFee": "0",                                 </p>
	<p class="text-ind-d">			                    "adjustFee": "0",                              </p>
	<p class="text-ind-d">			                    "totalfee": "99000",                           </p>
	<p class="text-ind-d">			                    "subjcetDetailList": [                         </p>
	<p class="text-ind-e">			                       {                                          </p>
	<p class="text-ind-f">			                            "subjectId": "2010111",                </p>
	<p class="text-ind-f">			                            "subjectOrgFee": "99000",              </p>
	<p class="text-ind-f">			                            "subjectDisFee": "0",                  </p>
	<p class="text-ind-f">			                            "subjcetAdjustFee": "0",               </p>
	<p class="text-ind-f">			                            "subjectFee": "99000"                  </p>
	<p class="text-ind-e">			                        }                                          </p>
	<p class="text-ind-d">			                    ]                                              </p>
	<p class="text-ind-c">			                }                                                  </p>
	<p class="text-ind-b">			            ]                                                      </p>
	<p class="text-ind-a">			        }                                                          </p>
	<p class="text-ind">			    ],                                                             </p>
	<p class="text-ind">			    "pageNumber": "1",                                             </p>
	<p class="text-ind">			    "totalCount": "1",                                             </p>
	<p class="text-ind">			    "queryStartTime": "20151001000000",                            </p>
	<p class="text-ind">			    "queryEndTime": "20161001000000"                               </p>
	<p>			}                                                                  </p>
       

       </div>
       
       </div>  
             
         </div>
 
     </div>
 </div>






<!--footer-->
<%@ include file="/inc/foot.jsp"%>

</body>
</html>
