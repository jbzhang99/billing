<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>标准资费管理</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">

		<div class="management">
			<!--资费管理外侧-->

			<div class="management-cnt">
				<!--资费管理内容-->
				<!--查询区域-->
				<div class="nav-form">
				<form id="queryForm" action="">
					<ul>
						<li>
							<p class="word">标准资费ID</p>
							<p>
								<input type="text" class="int-medium" id="standardId" name="standardId">
							</p>
						</li>
						<li>
							<p class="word">资费名称</p>
							<p>
								<input type="text" class="int-medium" id="priceName" name="priceName">
							</p>
						</li>
						<li>
							<p class="word">业务类型</p>
							<p>
								<select class="select-medium" id="serviceType" name="serviceType" onchange="pager._getServiceDetail()">
									<option value="">请选择</option>
								</select>
							</p>
						</li>
					</ul>
					<ul>
						<li>
			                 <p class="word">业务类型细分</p>
			                 <p><select class="select-medium" id="serviceTypeDetail" name="serviceTypeDetail"> 
			                 <option value="">请选择</option>
			                 </select> </p>
		                 </li>
						<li>
							<p class="word">资费状态</p>
							<p>
								<select class="select-medium" id="priceState" name="priceState">
									<option value="">请选择</option>
								</select>
							</p>

						</li>
						<li class="btn-margin"><p>
								<input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH">
							</p></li>
					</ul>
				</form>
				</div>
				<!--查询区域结束-->

				<div class="management-cnt—border">
					<!--虚线-->
				</div>


				<div class="nav-tplist">
					<!--查询结果-->

					<div class="nav-tplist-title">
						<!--查询结果标题-->
						<div class="title-left">
							<i class="icon-th-list"></i>标准资费信息
						</div>
						<div class="title-right">
							<p class="plus">
								<a href="${_base}/standardFee/toAdd"><i class="icon-plus" style="line-height:38px;"></i></a>
							</p>
							<p class="plus-word">
								<a href="${_base}/standardFee/toAdd">添加标准资费</a>
							</p>
						</div>
					</div>
					<!--查询结果标题结束-->

					<div class="nav-tplist-table" id="resultListTable">
						<!--查询结果列表-->
						<table width="100%" border="0">
						  <tr class="tr-backgrond">                                                                                                            
						    <td class="left-border">序号</td>
						    <td>资费ID</td>
						    <td>资费名称</td>
						    <td>业务类型</td>
						    <td>业务类型细分</td>
						    <td>使用量</td>
						    <td>单位</td>    
						    <td>单次/周期</td>
						    <td>单价/总价</td>
						    <td>资费描述</td>
						    <td>状态</td>
						    <td class="right-border">操作</td>
						  </tr>
						  
						  <tbody id="standardData"></tbody>
						  
						</table>
						
					</div>
					<!--分页-->
					 <div>
		 				 <nav style="text-align: right">
							<ul id="pagination-ul">
							</ul>
						</nav>
					  </div>
					 <!--分页-->
				</div>
			</div>
		</div>
		<!--中间部分结束-->
		
		<!--弹出层-->
		<div id="tcc_div" class="table-pop" style="display: none;right: 300px;top:180px;position: fixed;">
		  <div class="pop-close" id="CLOSE_POP"><img src="${_base}/resources/baasop/images/close.png"></div>
		  <div class="pop-title">更改资费状态</div>
		  <input name="targetId" value="" style="display:none;" >
		  <input type="hidden" name="phoneNum" value="${sessionScope.user_session_key.phone}">
		  <div class="pop-cnt">
		   <ul>
		    <li>
		     <p class="name">手机号码</p>
		     <p><span class="pop-text-blue">${fn:substring(sessionScope.user_session_key.phone, 0, 3)} **** ${fn:substring(sessionScope.user_session_key.phone, sessionScope.user_session_key.phone.length()-4, sessionScope.user_session_key.phone.length())}</span>系统将验证码默认发送到该手机！</p>
		    </li>
		    <li>
		     <p class="name">资费状态</p>
		     <p>
		     	<span><input type="radio" id="invalid" name="feeStatus" value="INACTIVE" checked="checked" class="pop-radio" >待生效</span>
		     	<span><input type="radio" id="valid" name="feeStatus" value="ACTIVE" class="pop-radio" >生效</span>
		     </p>
		    </li>
		    <li>
		     <p class="name">验证码</p>
		     <p>
		     	<input type="text" id="phoneVerifyCode" class="int-small pop-int-small">
		     	<input class="button" id="PHONE_IDENTIFY" type="button" value="获取验证码" style=" height:30px; margin-left:20px; padding:0 10px;">
		     	<!-- 
		     	<a style="display:none" id="PHONE_IDENTIFY" href="javascript:void(0);" >60S后再次重新发送</a>
		     	<a style="display:none" class="pop-status" href="#">重新发送验证码</a>
		     	 -->
				<i id="showSmsMsg" style="font-size:1px;color:red;"></i>
		     </p>
		    </li>
		    <li>
		     <p class="name">&nbsp;</p>
		     <p><input id="BTN_UPDATE_STATUS" type="button" class="pop-btn bass-btn" value="确 认"></p>
		    </li>
		   </ul>
		  </div>
		</div>
		<!--弹出层结束-->
		
		<!--底部-->
		<%@ include file="/inc/foot.jsp"%>
		<!--底部结束-->
		
		
		<script type="text/javascript">
			var pager;
			(function () {
				seajs.use('app/jsp/standardfee/standPostage', function (StandPostagePager) {
					pager = new StandPostagePager({element: document.body});
					pager.render();
				});
			})();
		</script>
		
		<script id="standardListTemple" type="text/template">
					<tr>
						    <td class="left-border">{{:index}} </td>
						    <td>{{:standardList.standardId}}</td>
						    <td>{{:standardList.priceName}}</td>
							<td>
						        {{:serviceTypeName}}
						    </td>
							<td>
						        {{:subServiceTypeName}}
						    </td>
						    <td>
								{{for standardList.usageList}}
						        		{{:amount}}
								{{/for}}
							</td>
							<td>
						      	{{for standardList.usageList}}
						        		{{:unit}}
								{{/for}}
						    </td>
						    <td>
						    {{:standardList.cycleAmount}}
						    </td>
						    
						    <td>{{:standardList.price}}</td>
							<td title="{{:standardList.comments}}">{{if standardList.comments.length > 10 }}{{:standardList.comments.substring(0,10)}}...{{else}}{{:standardList.comments}}{{/if}}</td>
						   <td><a href="javascript:void(0)" class="sp-setvalid" sid="{{:standardList.standardId}}" status="{{:standardList.status}}"><i><img src="${_base}/resources/baasop/images/shezhi.png"></i>
							{{if standardList.status == 'ACTIVE'}}
								<font class="force">生效</font>
							{{else}}
								<font class="force-blue">待生效</font>
							{{/if}}
							
							</a></td>
						    <td class="right-border"> <a href="${_base}/standardFee/toView?standardId={{:standardList.standardId}}"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看</a>
							<a href="javascript:void(0);" standardId="{{:standardList.standardId}}" status="{{:standardList.status}}" class="edit-button"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
							<a href="javascript:void(0);" standardId="{{:standardList.standardId}}" status="{{:standardList.status}}" comments="{{:standardList.comments}}" class="del-button"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a>
							<a href="javascript:void(0);" standardId="{{:standardList.standardId}}" status="{{:standardList.status}}" class="relate-button">关联详单科目</a></td>
					</tr>
	    </script>
		
</body>
</html>

