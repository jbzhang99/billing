<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>发票管理</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>
<!--弹出框-->
<div class="main-right">
	<div class="zuhu-title">
		<ul>
			<li>发票列表</li>
		</ul>
	</div>
		<div class="query-list">
			<table class="table">
				<thead>
					<tr>
						<td>序号</td>
						<td>账期</td>
						<td>账单金额（元）</td>
						<td>快递单号</td>
						<td>邮寄时间</td>
						<!-- <td>消费明细</td> -->
						<td>发票信息</td>
						<td>发票是否已寄出</td>
					</tr>
				</thead>
				<tbody id="invoiceData"></tbody>
			</table>
		</div>
		<nav class="page">
        	<ul class="pagination" id="pagination-ul"></ul>
    	</nav>
</div>
<script id="invoiceListTemple" type="text/x-jsrender">
<tr class="invoiceRecord {{if (#index+1)%2==0}}bg-block{{/if}}">
<input type="hidden" name="title" value="{{:record.title}}"/>
<input type="hidden" name="issueType" value="{{:record.issueType}}"/>
<input type="hidden" name="invoiceType" value="{{:record.invoiceType}}"/>
<input type="hidden" name="linkName" value="{{:record.linkName}}"/>
<input type="hidden" name="address" value="{{:record.address}}"/>
<input type="hidden" name="postCode" value="{{:record.postCode}}"/>
<input type="hidden" name="mobileNo" value="{{:record.mobileNo}}"/>
<input type="hidden" name="phoneNo" value="{{:record.phoneNo}}"/>
<input type="hidden" name="email" value="{{:record.email}}"/>
<input type="hidden" name="taxRegNo" value="{{:record.taxRegNo}}"/>
<input type="hidden" name="bankName" value="{{:record.bankName}}"/>
<input type="hidden" name="bankAcctNo" value="{{:record.bankAcctNo}}"/>
<input type="hidden" name="regAddress" value="{{:record.regAddress}}"/>
<input type="hidden" name="regPhone" value="{{:record.regPhone}}"/>
<input type="hidden" name="licenseAttachId" value="{{:record.licenseAttachId}}"/>
<input type="hidden" name="licenseAttachType" value="{{:record.licenseAttachType}}"/>
<input type="hidden" name="taxRegAttachId" value="{{:record.taxRegAttachId}}"/>
<input type="hidden" name="taxRegAttachType" value="{{:record.taxRegAttachType}}"/>
<input type="hidden" name="taxpayerAttachId" value="{{:record.taxpayerAttachId}}"/>
<input type="hidden" name="taxpayerAttachType" value="{{:record.taxpayerAttachType}}"/>
<input type="hidden" name="acctId" value="{{:record.acctId}}"/>
<input type="hidden" name="custId" value="{{:record.custId}}"/>
<input type="hidden" name="tenantId" value="{{:record.tenantId}}"/>
<input type="hidden" name="status" value="{{:record.status}}"/>
						<td>{{:index}}</td>                                                                                                
						<td>{{:record.billMonth}}</td>
						<td>{{:~liToYuan(record.totalAmount)}}</td>
						<td>{{:record.expressNo}} </td>
						<td>{{:~timestampToDate('yyyy年MM月dd日', record.sendTime)}}</td>
						
						<td><i class="icon-search"></i><a class="query-button" invoiceId="{{:record.invoiceId}}" href="javascript:void(0);">查看</a></td>
						<td>{{if record.status=='1' }}是{{else}}否{{/if}}</td>
					</tr>
</script>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/invoice/invoiceUser', function (InvoiceUserPager) {
                var pager = new InvoiceUserPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>