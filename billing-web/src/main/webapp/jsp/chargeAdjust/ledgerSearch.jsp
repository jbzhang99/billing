<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>调账查询</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/chargeAdjust/ledgerSearch', function (LedgerSearchPager) {
                var pager = new LedgerSearchPager({element: document.body});
                pager.render();
            });
        })
    </script>
</head>
<body>
<div class="main-right">
    <div class="zuhu-title">
        <ul>
            <li>调账查询</li>
        </ul>
    </div>
    <div class="query" id="billCondition">
        <ul>
            <li>
                <p>租户名称</p>
                <span><input class="int-large" type="text" name="tenantName"></span>
            </li>
            <li>
                <p>账单月</p>
                <span><select class="set-large" id="billMonth" name="billMonth"></select></span>
            </li>
            <li>
                <p>&nbsp;</p>
                <button class="btn-query" id="billQuery">查 询</button>
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
                <td>账单月</td>
                <td>应缴金额（元）</td>
                <td>调账金额（元）</td>
                <td>支付状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody id="billData"></tbody>
        </table>
    </div>
    <nav class="page">
        <ul class="pagination" id="pagination"></ul>
    </nav>
<script id="billDataTmpl" type="text/x-jsrender">
    <tr {{if (#index+1)%2==0}} class="bg-block" {{/if}}>
        <td>{{:index}}</td>
        <td>{{:extCustId}}</td>
        <td>{{:tenantName}}</td>
        <td>{{:billMonth}}</td>
        <td>{{:~liToYuan(totalAmount)}}</td>
        <td>{{:~liToYuan(adjustAfterwards)}}</td>
        <td>
        {{if balance==0}}
        已支付
        {{else}}
        待支付
        {{/if}}
        </td>
        <td>
        {{if balance>0}}
        <a href="${_base}/chargeAdjust/toAdjustCharge?acctId={{:acctId}}&billMonth={{:billMonth}}">调账</a>
        {{/if}}
        </td>
    </tr>
</script>
</div>
</body>
</html>