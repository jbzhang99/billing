<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
<title>租户管理员收支明细</title>
<%@ include file="/inc/inc.jsp"%>
<script type="text/javascript">
	(function() {
		seajs.use('app/jsp/inoutfund/inoutfundForTanent', function(InoutfundForTanentPager) {
			var pager = new InoutfundForTanentPager({
				element : document.body,
			});
			pager.render();
		});
	})();
</script>
<!--[if IE]>
<script src=”http://html5shiv.googlecode.com/svn/trunk/html5.js”></script>
< ![endif]-->
</head>
<body>
	<div class="main-right">
		<!-- <div class="rechar-title">
			<ul>
				<li class="active"><a href="javascript:;">中信云</a></li>
				<li><a href="javascript:;">阿里云</a></li>
				<li><a href="javascript:;">SmartCloud</a></li>
			</ul>
		</div> -->
		<div class="main1">
			<div class="query" id="inOutQuery">
				<ul>
					<li>
						<p>交易类型</p> <span><select class="set-large" id="optType"
							name="optType"><option value=''>请选择</option></select></span>

					</li>
					<li>
						<p>起止时间</p> <span id="startTimeSpan"><input class="int-large" type="text"
							id="startTime" name="startTime" readonly unselectable="on"><i
							class="icon-calendar"></i></span> <b>至</b> <span id="endTimeSpan"><input
							class="int-large" type="text" name="endTime" id="endTime"
							readonly unselectable="on"><i class="icon-calendar"></i></span>
					</li>
				</ul>
				<ul>
					<li>
						<p>&nbsp;</p>
						<button class="btn-query" id="inOutFundQuery">查 询</button>
					</li>
				</ul>
			</div>
			<div class="query-list">
				<table class="table">
					<thead>
						<tr>
							<td>序号</td>
							<td>租户ID</td>
							<td>租户名称</td>
							<td>银行流水号</td>
							<td>交易类型</td>
							<td>交易日期</td>
							<td>交易金额（元）</td>
							<td>备注说明</td>
						</tr>
					</thead>
					<tbody id="inOutFundList">

					</tbody>
				</table>
			</div>

		</div>
	</div>
	<nav class="page">
	<ul class="pagination" id="pagination-ul"></ul>
	</nav>
	</div>
	</div>
	<script id="inOutFundTmpl" type="text/x-jsrender">
                      <tr>
						<td>{{:index}}</td>
						<td>{{:fundSerialInfo.extCustId}}</td>
						<td>{{:fundSerialInfo.tenantName}}</td>
						<td>{{:fundSerialInfo.bandSerialCode}}</td>
						<td>{{:fundSerialInfo.optType}}</td>
						<td>{{:~timestampToDate('yyyy-MM-dd', fundSerialInfo.payTime)}}</td>
						<td>{{:~liToYuan(fundSerialInfo.totalAmount)}}</td>
						<td>{{:fundSerialInfo.remark}}</td>
					</tr>
       </script>
	</div>
	<div class="main2">1</div>
	<div class="main3">2</div>
	</div>
</body>

</html>