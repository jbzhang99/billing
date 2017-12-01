<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>批价错单管理</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">
		<div class="management">
			<div class="nav-tplist-title peiz-title">
				<div class="title-left">批价错单管理</div>
			</div>


			<div class="management-cnt">
				<!--查询区域-->
				<div class="nav-form">
					<form id="queryForm" action="">
						<ul>
							<li>
								<p class="word">业务类型</p>
								<p>
									<select class="select-medium" id="serviceType" name="serviceType"></select>
								</p>
							</li>
							<li>
								<p class="word">错单编码</p>
								<p>
									<select class="select-medium" id="failCode" name="failCode"></select>
								</p>
							</li>
							<li class="btn-margin"><p><input type="button" value="查  询"
															 class="bass-btn nav-form-btn" id="BTN_SEARCH"></p>
							</li>
						</ul>
					</form>
				</div><!--查询区域结束-->
				<div class="management-cnt—border"><!--虚线--></div>
				<div class="nav-tplist"><!--查询结果-->
					<div class="nav-tplist-title"><!--查询结果标题-->
						<div class="title-left"><i class="icon-th-list"></i>错单查询结果</div>
					</div><!--查询结果标题结束-->
					<div class="nav-tplist-table"><!--查询结果列表-->
						<table width="100%" border="0">
							<tr class="tr-backgrond">
								<td class="left-border"><input id="checkAll" type="checkbox" class="int-checkbox1"> 全选
								</td>
								<td>业务类型</td>
								<td>错单编码</td>
								<td>错单异常信息</td>
								<td>错单记录</td>
								<td>错单处理断点</td>
								<td class="right-border">操作</td>
							</tr>
							<tbody id="failedBillsData"></tbody>
						</table>
						<div>
							<nav style="text-align: right">
								<ul id="pagination-ul">
								</ul>
							</nav>
						</div>
					</div>
					<div class="configure-btn-ctn">
						<div class="configure-btn-ctn-left">
							<ul>
								<li><input type="button" class="bass-btn peiz-btn resend-bill" value="重处理"></li>
								<li><input type="button" class="bass-btn peiz-btn delete-bill" value="删  除"></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!--弹出层-->
	<div class="table-pop" style="display: none;right: 300px;top:80px;width:700px;height:520px;position: fixed;z-index: 33">
		<div class="pop-close" onclick="$(this).parent('.table-pop').hide(200);$('#selectedBill').removeAttr('id');">
			<img src="${_base}/resources/baasmt/images/close.png"></div>
		<div class="pop-title">编辑错单信息</div>
		<div class="nav-tplist-table nav-tplist-table-none-border" style="height:370px;"><!--查询结果列表-->
			<table width="100%" border="0" id="billDetail"></table>
		</div>
		<div class="configure-btn-ctn">
			<div class="configure-btn-ctn-left">
				<ul>
					<li><input type="button"
							   onclick="$(this).parents('.table-pop').hide(200);$('#selectedBill').removeAttr('id');"
							   class="peiz-btn-cancel" value="取  消"></li>
					<li><input type="button" class="bass-btn peiz-btn" id="resendEditBill" value="重处理"></li>
				</ul>
			</div>
		</div>
	</div>
	<!--弹出层结束-->
	<!--中间部分结束-->
	<!--底部-->
	<%@ include file="/inc/foot.jsp"%>
	<!--底部结束-->
	<script type="text/javascript">
		var pager;
		(function () {
			seajs.use('app/jsp/exceptions/priceFailedBillList', function (PriceFailedBillListPager) {
				pager = new PriceFailedBillListPager({element: document.body});
				pager.render();
			});
		})();
	</script>
	<script id="failedListsTemple" type="text/template">
		<tr name="failedList">
			<td class="left-border">
			<input type="checkbox" name="bsn" value="{{:bsn}}" tabid="{{:bsn}}" tabname="{{:bsn}}" class="int-checkbox"></td>
			<input type="hidden" name="id" value="{{:id}}">
			<td>{{:serviceType}}</td>
			<td>{{:failCode}}</td>
			<td>{{:~subStrLessThan30(failReason)}}</td>
			<td>{{:~subStrLessThan30(failPacket)}}</td>
			<td>{{:failStep}}</td>
			<td class="right-border">
				<a class="bill-edit" href="javascript:void(0);"><i class="icon-pencil"></i>编辑错单</a>
			</td>
		</tr>
	</script>
</body>
</html>

