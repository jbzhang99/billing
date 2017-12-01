<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>发票信息</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

<div class="main-right">
	<!-- <div class="zuhu-title">
		<ul>
			<li>发票管理</li>
		</ul>
	</div> -->
	<div class="rechar-title">
		<ul>
			<li class="active"><a href="javascript:;">发票信息</a></li>
			<li><a href="javascript:;">联系人信息</a></li>
		</ul>
	</div>
	<div class="main1">
		<div class="query"> 
			<ul>
				<li>
					<p><i>*</i>发票抬头</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.title}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>发票类型</p>
					<c:choose>
					    <c:when test="${invoiceInfo.invoiceType==0}">
					      <span>增值税普通发票</span>
					    </c:when>
					    <c:otherwise>
					      <span>增值税专用发票</span>
					    </c:otherwise>
					</c:choose>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>纳税人识别号</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.taxRegNo}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>开户银行名称</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.bankName}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>开户账号</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.bankAcctNo}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>公司注册地址</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.regAddress}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>注册固定电话</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.regPhone}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>&nbsp;</p>
					<button class="btn-query" onclick="window.open('about:blank','_self'); window.close();" unselectable="on">关 闭</button>
				</li>
			</ul>
		</div>
	</div>
	<div class="main2">
		<div class="query"> 
			<ul>
				<li>
					<p><i>*</i>联系人姓名</p>
					<span><input class="int-large" type="text" value="${invoiceInfo.linkName}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>联系地址</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.address}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>联系电话1</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.mobileNo}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>联系电话2</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.phoneNo}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>邮箱地址</p>
					<span><input class="int-xlarge" type="text" value="${invoiceInfo.email}" readonly unselectable="on"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>&nbsp;</p>
					<button class="btn-query" onclick="window.open('about:blank','_self'); window.close();">关 闭</button>
				</li>
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf-8">
        /* $(function () {
            seajs.use('app/jsp/invoice/invoice', function (InvoicePager) {
                var pager = new InvoicePager({element: document.body});
                pager.render();
            });
        }) */
    </script>
</body>
</html>