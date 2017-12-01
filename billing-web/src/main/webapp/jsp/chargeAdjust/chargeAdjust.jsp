<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>调账</title>
    <%@ include file="/inc/inc.jsp"%>
	<style>
		.tab-form ul li p{float: none}
	</style>
	<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/chargeAdjust/chargeAdjust', function (ChargeAdjustPager) {
                var pager = new ChargeAdjustPager({element: document.body});
                pager.render();
            });
        })
	</script>
</head>
<body>
	<div class="main-right" style="float:left;">
		<div class="zuhu-title">
		<ul>
			<li>调账</li>
		</ul>
		</div>
		<div class="tab-form row">
			<input type="hidden" name="acctId" value="${acctId}">
			<input type="hidden" name="billMonth" value="${billMonth}">
			<div class="col-md-4">
				<ul style="float: right">
					<li>
						<p>费用类型</p>
						<select size="9" class="set-large" id="feeType" name="feeType"></select>
					</li>
				</ul>
			</div>
			<div class="col-md-8">
				<form id="adjustForm">
					<ul>
						<li>
							<p class="tab-form-name">&nbsp;</p>
							<p>
								<span ><input name="adjustFlag" value="0" type="radio" checked>调增</span>
								<span class="col-md-offset-1"><input name="adjustFlag" value="1" type="radio">调减</span>
							</p>
						</li>
						<li>
							<p class="tab-form-name">调整金额(元)</p>
							<p><input name="amount" placeholder="请输入正数" type="text" class="int-xlarge"></p>
						</li>
						<li>
							<p class="tab-form-name">调整原因</p>
							<p><textarea name="adjustReason" class="textarea-xxlarge"></textarea></p>
						</li>
					</ul>
					<p class="col-md-offset-7"><button class="btn-query" id="adjustSubmit" style="margin-top: 5px;">提交</button></p>
				</form>
			</div>
	    </div>
		
	</div>
</body>
</html>