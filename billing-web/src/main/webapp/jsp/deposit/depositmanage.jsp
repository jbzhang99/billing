<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html>
<head>
<%@ include file="/inc/inc.jsp"%>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
<title>充值管理</title>
<link href="${_base}/resources/citicbilling/css/font-awesome.css"
	rel="stylesheet" type="text/css">
<link href="${_base}/resources/citicbilling/css/content.css"
	rel="stylesheet" type="text/css">
<script src="${_base}/resources/citicbilling/js/jquery-1.11.3.min.js"></script>
<script src="${_base}/resources/citicbilling/js/index.js"></script>
</head>
<body>
	<!--充值弹出框-->
	<div id="pop_recharge_id" class="pop-recharge">
		<div class="pop-bg"></div>
		<div class="pop-cnt">
			<div class="pop-title" id="cg-Form">充值</div>
			<div class="pop-close">
				<a href="javascript:;"><img
					src="${_base}/resources/citicbilling/images/close.png"></a>
			</div>
			<div class="recharge-list">
				<form id="depositform">
					<table class="table">
						<input id="showView_accountId" class="int-large"
							name="showView_accountId" value="${ accountId}" type="hidden">

						<ul>

							<li>
								<p>充值金额（元）</p> <input id="amount" class="int-large"
								name="amount" value="${amount }" id="amount" type="text"
								onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')">
							</li>
							<li>
								<p>充值日期</p> <span id="depositTimeSpan"><input class="int-large" type="text"
									id="depositTime" name="depositTime" readonly unselectable="on"><i
									class="icon-calendar"></i></span>
							</li>
							<li>
								<p>&nbsp;</p>
								<button id="chargeBtn" class="btn-query">提交</button>
							</li>
						</ul>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!--充值弹出框END-->
	<div class="main-right">
		<div class="rechar-title">
			<ul>
				<li class="active"><a href="javascript:;" id="depositQuery">充值</a></li>
				<li><a href="javascript:;" id="depositRecordQuery">充值查询</a></li>
			</ul>
		</div>
		<div class="main1">
			<div class="query" id="depositCondition">
				<ul>
					<li>
						<p>租户名称</p> <span><input class="int-large" type="text"
							name="orgName"></span>
					</li>
					<li>
						<p>银行账号</p> <span><input class="int-large" type="text"
							name="bankAccount"></span>
					</li>
					<li>
						<button class="btn-query mgn-left" id="depositQueryBtn">查
							询</button>
					</li>
				</ul>
			</div>
			<div class="query-list">
				<table class="table">
					<thead>
						<tr>
							<td>序号</td>
							<td>租户名称</td>
							<td>银行账号</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="depositData"></tbody>
				</table>
			</div>
			<nav class="page">
				<ul class="pagination" id="pagination"></ul>
			</nav>
		</div>
		<script id="depositDataTmpl" type="text/x-jsrender">
    <tr {{if (#index+1)%2==0}} class="bg-block" {{/if}}>
        <td>{{:index}}</td>
        <td>{{:orgName}}</td>
        <td>{{:bankAccount}}</td>
        <td><a href="javascript:;" onclick="pager._showView({{:acctID}});"><i class="icon-credit-card"></i>充值</a></td>
    </tr>
</script>
		<div class="main2">
			<div class="query" id="depositRecordCondition">
				<ul>
					<li>
						<p>租户名称</p> <span><input class="int-large" type="text" name="orgName"></span>
					</li>
					<li>
						<p>银行账号</p> <span><input class="int-large" type="text" name="bankAccount"></span>
					</li>
				</ul>
				<ul>
					<li>
						<p>充值时间</p> <span id="startTimeSpan"><input class="int-large" type="text"
							id="beginTime" name="beginTime" readonly unselectable="on"><i
							class="icon-calendar"></i></span> <b>至</b> <span id="endTimeSpan"><input
							class="int-large" type="text" id="endTime" name="endTime"
							readonly unselectable="on"><i class="icon-calendar"></i></span>
					</li>
					<p>
						<span id="showDateMsg" style="color: red;"></span>
					</p>
				</ul>
				<ul>
					<li>
						<p>&nbsp;</p>
						<button class="btn-query" id="depositRecordQueryBtn">查 询</button>
					</li>
				</ul>
			</div>
			<div class="query-list">
				<table class="table">
					<thead>
						<tr>
							<td>序号</td>
							<td>租户名称</td>
							<td>银行账号</td>
							<td>充值金额(元)</td>
							<td>充值日期</td>
						</tr>
					</thead>
					<tbody id="depositRecordData"></tbody>
				</table>
			</div>
			<nav class="page">
				<ul class="pagination" id="pagination1"></ul>
			</nav>
		</div>
<script id="depositRecordDataTmpl" type="text/x-jsrender">
    <tr {{if (#index+1)%2==0}} class="bg-block" {{/if}}>
        <td>{{:index}}</td>
        <td>{{:record.tenantName}}</td>
        <td>{{:record.bandSerialCode}}</td>
		<td>{{:~liToYuan(record.totalAmount)}}</td>
        <td>{{:~timestampToDate('yyyy-MM-dd', record.payTime)}}</td>
    </tr>
</script>
	</div>
</body>
<script type="text/javascript">
	var pager;
	(function() {
		seajs.use('app/jsp/deposit/depositManage',
				function(DepositManagePager) {
					pager = new DepositManagePager({
						element : document.body
					});
					pager.render();
				});
	})();
</script>
</html>
