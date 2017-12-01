<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>账单查询</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
    	var pager;
        $(function () {
            seajs.use('app/jsp/account/accountViewForTenantManage', function (AccountViewTenantPager) {
                pager = new AccountViewTenantPager({element: document.body});
                pager.render();
            });
        })
    </script>
</head>
<body>
<div class="main-right">
	<div class="rechar-title">
		<ul>
			<li class="active"><a href="javascript:;">账户总览</a></li>
		</ul>
	</div>
	<div class="main1">
		<div class="invoice" style="display:none"><a href="#">索取发票</a></div>
		<div class="alicloud">
			<ul id="acctInfo">
				
			</ul>
		</div>
	</div>
	<div class="main1" style="height:100%">
		
	</div>
</div>
<script id="acctInfoTmpl" type="text/x-jsrender">
    <li>
					<p>现金余额</p>
					<img src="${_base}/resources/citicbilling/images/ali1.png">
					<span>¥ {{:~liToYuan(data.balance)}}</span>
				</li>
				<li>
					<p>预警阀值</p>
					<img src="${_base}/resources/citicbilling/images/ali2.png">
					<span class="red">¥ {{:data.warning}}</span>
				</li>
				<li>
					<p>可索取发票总额</p>
					<img src="${_base}/resources/citicbilling/images/ali3.png">
					<span>¥ {{:~liToYuan(data.invoice)}}</span>
				</li>
</script>
</body>
</html>
