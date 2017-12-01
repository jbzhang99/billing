<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>账单查询</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/bill/billSearch', function (BillSearchPager) {
                var pager = new BillSearchPager({element: document.body});
                pager.render();
            });
        })
    </script>
</head>
<body>
<div class="main-right">
    <div class="zuhu-title">
        <ul>
            <li>账单查询</li>
        </ul>
    </div>
    <div class="query" id="billCondition">
        <ul>
            <li>
                <p>租户名称</p>
                <span><input class="int-large" type="text" name="custName"></span>
            </li>
            <li>
                <p>租户ID</p>
                <span><input class="int-large" type="text" name="custId"></span>
            </li>
        </ul>
        <ul>
            <li>
                <p>支付状态</p>
                <span><select class="set-large" id="payState" name="payState"><option value="">请选择</option></select></span>
            </li>
            <li>
                <p>账单月</p>
                <span><select class="set-large" id="billMonth" name="billMonth"></select></span>
            </li>
        </ul>
        <ul>
            <li>
                <p>账单金额</p>
                <span><input class="int-large" type="text" onkeyup="value=value.replace(/[^0-9]/g,'');value = value.substring(0,15);" onpaste="value=value.replace(/[^0-9]/g,'');value = value.substring(0,15);" oncontextmenu = "value=value.replace(/[^0-9]/g,'');value = value.substring(0,15);" id="billFeeMin" name="billFeeMin">&nbsp;元</span>
                <b>～</b>
                <span><input class="int-large" type="text" onkeyup="value=value.replace(/[^0-9]/g,'');value = value.substring(0,15);" onpaste="value=value.replace(/[^0-9]/g,'');value = value.substring(0,15);" oncontextmenu = "value=value.replace(/[^0-9]/g,'');value = value.substring(0,15);" id="billFeeMax" name="billFeeMax">&nbsp;元</span>
            </li>
        </ul>
        <ul>
            <li>
                <p>&nbsp;</p>
                <button class="btn-query" id="billQuery">查 询</button>
            </li>
            <li>
                <span style="color: #ff0000;margin-left: 20px;">温馨提示：<a style="color: #ff0000;" href="${_base}/invoice/toInvoiceInfoUpdate">请点击此处及时完善贵公司的发票信息</a></span>
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
                <td>账期月</td>
                <td>本期分摊金额（元）</td>
                <td>账单应缴金额（元）</td>
                <td>实收金额（元）</td>
                <td>支付状态</td>
                <td>备注</td>
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
        <td>{{:billChargeVo.extCustId}}</td>
        <td>{{:billChargeVo.custName}}</td>
        <td>{{:billChargeVo.billMonth}}</td>
        <td>{{:~liToYuan(billChargeVo.apportionment)}}</td>
        <td>{{:~liToYuan(billChargeVo.adjustFee)}}</td>
        <td>{{:~liToYuan(billChargeVo.proceeds)}}</td>
        <td>
        {{if billChargeVo.balanceFee==0}}
        已支付
        {{else}}
        待支付
        {{/if}}
        </td>
        <td>{{:billChargeVo.subjectName}}</td>
    </tr>
</script>
</div>
</body>
</html>