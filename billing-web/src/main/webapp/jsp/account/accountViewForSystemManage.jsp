<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>账户总览</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
    	var pager;
        $(function () {
            seajs.use('app/jsp/account/accountViewForSystemManage', function (AccountViewPager) {
                pager = new AccountViewPager({element: document.body});
                pager.render();
            });
        })
    </script>
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
						<ul>

							<li>
								<p>充值金额（元）</p> <input id="amount" class="int-large"
								name="amount" value="${amount }" id="amount" type="text"
								onkeyup="value=value.replace(/[^0-9]/g,'')"
								onpaste="value=value.replace(/[^0-9]/g,'')"
								oncontextmenu="value=value.replace(/[^0-9]/g,'')">
							</li>
							<li>
								<p>充值时间</p> <span id="depositTimeSpan"><input class="int-large" type="text"
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
<div class="main-right">
	<div class="rechar-title">
		<ul>
			<li class="active"><a href="javascript:;">阿里云</a></li>
		</ul>
	</div>
	<div class="main1">
		
		<div class="alicloud">
			<ul>
				<li>
					<p>现金余额</p>
					<img src="${_base}/resources/citicbilling/images/ali1.png">
					<span>¥ ${data.balance }</span>
				</li>
				<li>
					<p>预警阀值</p>
					<img src="${_base}/resources/citicbilling/images/ali2.png">
					<span class="red">¥ ${data.warning }</span>
				</li>
				<li>
					<p>可索取发票总额</p>
					<img src="${_base}/resources/citicbilling/images/ali3.png">
					<span>¥ ${data.invoice }</span>
				</li>
			</ul>
		</div>
	</div>
	<div class="main2">
		
	</div>
</div>

<div class="main-right mgn-top">
	<div class="zuhu-title">
		<ul>
			<li>租户查询</li>
		</ul>
	</div>
		<div class="query" id="queryCondition">
			<ul>
				<li>
					<p>账户ID</p>
					<span><input class="int-large" type="text" id="acctId" name="acctId"></span>
				</li>
				<li>
					<p>租户名称</p>
					<span><input class="int-large" type="text" id="orgName" name="orgName"></span>
				</li>
			</ul>
			<!-- ul>
				<li>
					<p>余额范围</p>
					<span><input class="int-large" type="text">&nbsp;元</span>
					<b>～</b>
					<span><input class="int-large" type="text">&nbsp;元</span>
				</li>
			</ul -->
			<ul>
				<li>
					<p>&nbsp;</p>
					<button class="btn-query"  id="billQuery" name="billQuery">查 询</button>
				</li>
			</ul>
		</div>
		<div class="query-list">
			<table class="table">
				<thead>
					<tr>
						<td>序号</td>
						<td>账户ID</td>
						<td>租户名称</td>
						<td>现金余额（元）</td>
						<td>预警阈值（元）</td>
						<!-- <td>操作</td> -->
					</tr>
				</thead>
				<tbody id="acctInfo">
					
				</tbody>
			</table>
		</div>
		<nav class="page">
	     <ul class="pagination" id="pagination"></ul>
	  </nav>
</div>
<script id="acctInfoTmpl" type="text/x-jsrender">
    <tr {{if (#index+1)%2==0}} class="bg-block" {{/if}}>
        <td>{{:index}}</td>
        <td style="display:none">{{:acctID}}</td>
        <td>{{:acctID}}</td>
        <td>{{:orgName}}</td>
        <td>{{:~liToYuan(balance)}}</td>
        <td>{{:warning}}</td>
    </tr>
</script>

        <!-- <td><a href="javascript:pager._showView({{:acctID}});"><i class="icon-credit-card"></i>充值</a></td> -->
</body>

</html>
