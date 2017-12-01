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
	<div class="zuhu-title">
		<ul>
			<li>设置发票信息</li>
		</ul>
	</div>
		
		<div class="query zuhu"> 
			<h3>发票信息：</h3>
			<form id="invoiceForm">		
			<input type="hidden" name="invoiceInfoId" value="${invoiceInfo.invoiceInfoId}">
			<ul>
				<li>
					<p><i>*</i>发票抬头</p>
					<span><input class="int-xlarge" name="title" value="${invoiceInfo.title}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>发票类型</p>
					<span class="zuhu-int1"><input name="invoiceType" value="0" type="radio" <c:if test="${invoiceInfo.invoiceType==0}"> checked="checked" </c:if>>增值税普通发票</span>
					<span class="zuhu-int2"><input name="invoiceType" value="1" type="radio"<c:if test="${invoiceInfo==null || invoiceInfo.invoiceType==1}"> checked="checked" </c:if>>增值税专用发票</span>
				</li>
			</ul>
			<div class="list-hide">
			<ul>
				<li>
					<p><i>*</i>纳税人识别号</p>
					<span><input class="int-xlarge" name="taxRegNo" value="${invoiceInfo.taxRegNo}" type="text" ></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>开户银行名称</p>
					<span><input class="int-xlarge" name="bankName" value="${invoiceInfo.bankName}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>开户账号</p>
					<span><input class="int-xlarge" name="bankAcctNo" onkeyup="value=value.replace(/[^0-9]/g,'')" onpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')" value="${invoiceInfo.bankAcctNo}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>公司注册地址</p>
					<span><input class="int-xxlarge" name="regAddress" value="${invoiceInfo.regAddress}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>注册固定电话</p>
					<span><input class="int-xlarge" name="regPhone" onkeyup="value=value.replace(/[^0-9]/g,'')" onpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')" value="${invoiceInfo.regPhone}" type="text"></span>
				</li>
			</ul>
			</div>
			<div class="zuhu-fp">
				<h3>联系人信息：</h3>
				<ul>
				<li>
					<p><i>*</i>联系人姓名</p>
					<span><input class="int-xlarge" name="linkName" value="${invoiceInfo.linkName}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>联系地址</p>
					<span><input class="int-xxlarge" name="address" value="${invoiceInfo.address}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>联系电话1</p>
					<span><input class="int-xlarge" name="mobileNo" onkeyup="value=value.replace(/[^0-9]/g,'')" onpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')" value="${invoiceInfo.mobileNo}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>联系电话2</p>
					<span><input class="int-xlarge" name="phoneNo" onkeyup="value=value.replace(/[^0-9]/g,'')" onpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')" value="${invoiceInfo.phoneNo}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p><i>*</i>邮箱地址</p>
					<span><input class="int-xlarge" name="email" value="${invoiceInfo.email}" type="text"></span>
				</li>
			</ul>
			<ul>
				<li>
					<p>&nbsp;</p>
					<button class="btn-query">提 交</button>
				</li>
			</ul>
			</div>
			</form>
			
		</div>
		
	</div>

<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/invoice/invoiceAdd', function (InvoiceAddPager) {
                var pager = new InvoiceAddPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>