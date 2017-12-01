<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
</head>
<body>
<h1>菜单</h1>
<a href="${pageContext.servletContext.contextPath}/bill/toBillSearch">账单查询(运营)</a><br>
<a href="${pageContext.servletContext.contextPath}/bill/toTenantBillSearch">账单查询(租户)</a><br>
<a href="${pageContext.servletContext.contextPath}/deposit/toDepositManage">充值管理</a><br>
<a href="${pageContext.servletContext.contextPath}/invoice/toInvoiceList">发票管理</a><br>
<a href="${pageContext.servletContext.contextPath}/invoice/toUserInvoiceList">发票列表</a><br>
<a href="${pageContext.servletContext.contextPath}/invoice/toInvoiceInfoUpdate">发票信息</a><br>
<a href="${pageContext.servletContext.contextPath}/inOutFund/queryInOutFund">收支明细(运营)</a><br>
<a href="${pageContext.servletContext.contextPath}/inOutFund/queryInOutFundForTenant">收支明细(租户)</a><br>
<a href="${pageContext.servletContext.contextPath}/account/accountViewForSystemManage">账务总览(运营)</a><br>
<a href="${pageContext.servletContext.contextPath}/account/accountViewForTenantManage">账务总览(租户)</a><br>
<a href="${pageContext.servletContext.contextPath}/policy/toPolicyList">信控规则管理</a><br>
<a href="${pageContext.servletContext.contextPath}/usage/toUsageRecordExport">详单查询（运营）</a><br>
<a href="${pageContext.servletContext.contextPath}/usage/toTenantUsageRecordExport">详单查询（租户）</a><br>
<a href="${pageContext.servletContext.contextPath}/usage/toUserUsageRecordExport">详单查询（用户）</a><br>
<a href="${pageContext.servletContext.contextPath}/product/productList">定义产品目录</a><br>
<a href="${pageContext.servletContext.contextPath}/customProduct/productList">定义产品目录(中信定制)</a><br>
<a href="${pageContext.servletContext.contextPath}/strategy/toStrategyList">定价策略管理</a><br>
<a href="${pageContext.servletContext.contextPath}/priceElement/toPriceElementList">定价元素管理</a><br>
<a href="${pageContext.servletContext.contextPath}/standardProduct/toStandardProductList">标准产品定价</a><br>
<a href="${pageContext.servletContext.contextPath}/chargeAdjust/toSearchLedger">调账查询</a><br>
</body>
</html>