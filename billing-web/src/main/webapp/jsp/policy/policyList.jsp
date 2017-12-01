<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>信控规则管理</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

<div class="main-right">
	<div class="zuhu-title">
		<ul>
			<li>信控规则管理</li>
		</ul>
	</div>
		<div class="query">
		<form id="queryForm" action="">
			<ul>
				<li>
					<p>信控规则ID</p>
					<span><input class="int-large" type="text" id="ruleId" name="ruleId"></span>
				</li>
				<li>
					<p>信控规则名称</p>
					<span><input class="int-large" type="text" id="ruleName" name="ruleName"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>租户ID</p>
					<span><input class="int-large" type="text" id="custId" name="custId"></span>
				</li>
				<li>
					<p>租户名称</p>
					<span><input class="int-large" type="text" id="custName" name="custName"></span>
				</li>
			</ul>
		</form>
		<ul>
			<li>
				<p>&nbsp;</p>
				<button class="btn-query" id="submit">查  询</button>
			</li>
		</ul>
		</div>
		<div class="query-list">
			<div class="cnt-export">
				<ul>                                                                                                                                                 
					<li class="right"><button id="addPolicy" class="btn-query"><i class=" icon-plus" ></i>新建信控规则</button></li>
				</ul>
			</div>
			<table class="table">
				<thead>
					<tr>
						<td>序号</td>
						<td>信控规则ID </td>
						<td>租户ID</td>
						<td>租户名称</td>
						<td>信控规则名称</td>
						<td>信控正常状态值</td>
						<td>信控催缴状态值</td>
						<td>信控欠费状态值</td>
						<td>信控规则描述</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="policyData"></tbody>
			</table>
		</div>
		<nav class="page">
        	<ul class="pagination" id="pagination-ul"></ul>
    	</nav>
</div>
<script id="policyListTemple" type="text/x-jsrender">
	<tr class="policy-record {{if (#index+1)%2==0}}bg-block{{/if}}">
<input type="hidden" name="extCustId" value="{{:extcustId}}"/>
						<td>{{:index}}</td>                                                                                                     
						<td>{{:policyid}}</td>
						<td>{{:extcustId}}</td>
						<td>{{:extcustName}}</td>
						<td>{{:policyName}}</td>
						<td>可用余额>{{:~liToYuan(warnoffBalance)}}</td>
						<td>{{:~liToYuan(stopCeil)}}≤可用余额≤{{:~liToYuan(warnoffBalance)}} </td>
						<td>可用余额＜{{:~liToYuan(stopCeil)}}</td>
						<td>{{:policydescribe}}</td>
						<td><a class="view-policy" href="javascript:void(0);">查看&nbsp;</a><a class="edit-policy" href="javascript:void(0);">编辑&nbsp;</a><a class="del-policy" href="javascript:void(0);">删除</a></td>
					</tr>
</script>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/policy/policyList', function (PolicyPager) {
                var pager = new PolicyPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>