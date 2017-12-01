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
<div class="pop-export">
	<div class="pop-bg"></div>
	<div class="pop-cnt">
		<div class="pop-title">导入</div>
		<div class="pop-close"><a href="javascript:;"><img onclick="$('.pop-export').hide();$('#txt').val('文件域');$('#f').val('');" src="${_base}/resources/citicbilling/images/close.png"></a></div>
		<div class="export-list">
			<ul>
				<li>
					<p>选择要导入的文件</p>
					<form method="post" action="" enctype="multipart/form-data">
						<input type="text" id="txt" name="txt" class="input" value="文件域" disabled="disabled" />
						<input type="button" onMouseMove="f.style.pixelLeft=event.x-60;f.style.pixelTop=this.offsetTop;" value="浏览" size="30" onClick="f.click()" class="liulan">
						<input type="file" id="f" onChange="txt.value=this.value" name="f" class="files"  size="1" hidefocus>
					</form>
				</li>
				<li class="angle">
					<button class="btn-query" id="import">导 入</button>
					<button class="btn-query" onclick="$('.pop-export').hide();">取 消</button>
				</li>
			</ul>
		</div>
	</div>
</div>
<!--弹出框END-->
<div class="main-right">
	<div class="zuhu-title">
		<ul>
			<li>发票管理</li>
		</ul>
	</div>
		<div class="query">
			<form id="queryForm" action="">
				<ul>
					<li>
						<p>租户名称</p>
						<span><input class="int-large" type="text" id="companyName" name="companyName"></span>
					</li>
				</ul>
				<ul>
					<li>
						<p>起止时间</p>
						<span id="startTimeSpan"><input class="int-large" type="text" id="startTime" name="startTime" readonly unselectable="on"><i class="icon-calendar"></i></span>
						<b>至</b>
						<span id="endTimeSpan"><input class="int-large" type="text" name="endTime" id="endTime" readonly unselectable="on"><i class="icon-calendar"></i></span>
					</li>
					<li>
						<input type="hidden" name="byMonth" id="byMonth" value="1">
						<p><input class="int-checkbox" type="checkbox" id="byMonthChecked">按账期</p>
						<span><select class="set-large" id="billMonth" name="billMonth"></select></span>
					</li>
				</ul>
				<ul>
					<li>
						<p>发票是否已寄出</p>
						<input type="hidden" name="status" id="status"> 
						<span><input class="int-checkbox" type="checkbox" id="checkedStatus1">是</span>
						<span class="mar-left"><input class="int-checkbox" type="checkbox" id="checkedStatus2">否</span>
					</li>
				</ul>
			</form>
			<ul>
				<li>
					<p>&nbsp;</p>
					<button class="btn-query" id="submit">查 询</button>
				</li>
			</ul>
		</div>
		
		<div class="query-list">
			<div class="cnt-export">
				<ul>
					<li class="right">
						<span><a href="#" id="BTN_IMPORT"><i class="icon-download-alt"></i>导入列表信息</a></span>
						<span><a href="#" id="BTN_EXPORT"><i class="icon-upload-alt"></i>导出列表信息</a></span>
					</li>
				</ul>
			</div>
			
			<table class="table">
				<thead>
					<tr>
						<td>序号</td>
						<td>租户名称</td>
						<td>账期</td>
						<td>账单金额（元）</td>
						<td>发票信息</td>
						<td>快递单号</td>
						<td>寄出时间</td>
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
						<td>{{:record.custName}}</td>
						<td>{{:record.billMonth}}</td>
						<td>{{:~liToYuan(record.totalAmount)}}</td>
						<td><i class="icon-search"></i><a class="query-button" invoiceId="{{:record.invoiceId}}" href="javascript:void(0);">查看</a></td>
						<td>{{:record.expressNo}} </td>
						<td>{{:~timestampToDate('yyyy年MM月dd日', record.sendTime)}}</td>
						<td>{{if record.status=='1' }}是{{else}}否{{/if}}</td>
					</tr>
</script>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/invoice/invoice', function (InvoicePager) {
                var pager = new InvoicePager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>