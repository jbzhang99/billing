<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>详单查询</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	<div class="management"><!--资费管理外侧-->
		<div class="management-cnt"><!--资费管理内容-->
			<!--查询区域-->
			<div class="nav-form">
	             <ul>
		             <li>
		            	<p class="word">客户名称</p>
		             	<p><input type="text" class="int-medium" id="custName"> </p>
		             </li>
		             <li>
		             	<p class="word">客户等级</p>
		             	<p><select id="custGrade" class="select-medium"> 
		             		<option value="">请选择</option>
		             	</select> </p>
		             </li>
		             <li>
		             	<p class="word">服务资源号</p>
		             	<p><input type="text" class="int-medium" id="serviceId"> </p>
		             </li>
		              
	             </ul>
	             <ul>
		           
		             <li>
		             	<p class="word"><span class="f00">*</span>客户证件号</p>
		             	<p>
		             	<input type="text" class="int-medium" id="idNumber" >
		             	</p>
		             </li>
		               <li>
		             	<p class="word">订单生成月份</p>
		             	<p id="queryTimeEvent"><input type="text" class="int-medium" id="queryTimeQ" readonly/><a href="javascript:void(0);"><i class="icon-calendar"></i> </a></p>
		             </li>
		             <li class="btn-margin"><input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH">
		            <p>
		             
		              <span class="regsiter-note" id="idNumberMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="idNumberMsg"></span>
						</span>
		             </p>
		             </li>
		              
		             
	             </ul>
			</div><!--查询区域结束-->
	          
			<div class="management-cnt—border"><!--虚线--></div>
	           
			<div class="nav-tplist"><!--查询结果-->
	            
	
				<!--查询结果列表-->
				<div class="nav-tplist-table">
					<table width="100%" border="0">
					  <tr class="tr-backgrond">                                                                                                            
					                                                                                                                              
					    <td>序号</td>
					    <td>客户名称</td>
					    <td>客户等级</td>
					    <td>资源号码</td>
					    <td class="right-border">操作</td>
					  </tr>
					  <tbody id="listData"></tbody>
					  <!-- 定义JsRender模版 -->
					  <script id="listDataTmpl" type="text/x-jsrender">
					  <tr>
					    <td>{{:#index + 1}}</td>
					    <td>{{:custName}}</td>
					    <td> {{:custGradeVal}}</td>
					    <td>{{:serviceId}}</td>
					    <td class="right-border">
							<a custId="{{:custId}}" subsId="{{:subsId}}" custName="{{:custName}}" custGrade="{{:custGradeVal}}" serviceId="{{:serviceId}}" class="DOWNLOAD_BTN" href="javascript:void(0);">下载明细</a>
						</td>
					  </tr>
					  </script>
					</table>
	           	</div>
				<!--查询结果列表结束-->
				
				<!--分页-->
				<div id="pageview">
				 <nav style="text-align: right">
				<ul id="pagination-ul">

						</ul></nav></div>
				<!--分页结束-->
			</div>
		</div>
	</div>
</div>
<!--中间部分结束-->


   
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/billdetail/billDetailList', function (BillDetailPager) {
		var pager = new BillDetailPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>

