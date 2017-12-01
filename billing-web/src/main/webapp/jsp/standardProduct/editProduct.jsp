<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>修改标准产品</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
       $(function () {
           if(localStorage){
               localStorage.setItem("productId",'${productId}');
           }
            seajs.use('app/jsp/standardProduct/editProduct', function (EditProductPager) {
                var pager = new EditProductPager({element: document.body});
                pager.render();
            });
        })
    </script>
</head>
<body>
<div class="main-right">
    <div class="zuhu-title">
        <ul>
            <li>修改标准产品定价</li>
        </ul>
    </div>
    <div class="tab-form" id="productInfo">
        <ul>
            <li>
                <input type="hidden" name="productId" value="${productId}" class="int-xlarge">
                <p class="tab-form-name"><i>*</i>请选择一级类目</p>
                <p><select class="int-xlarge" name="mainProductId" id="primaryCategory" disabled></select></p>
            </li>
            <li>
                <p class="tab-form-name"><i>*</i>请选择二级类目</p>
                <p><select class="int-xlarge" name="categoryId" id="secondaryCategory" disabled></select></p>
            </li>
            <li>
                <p class="tab-form-name"><i>*</i>产品名称</p>
                <p><input type="text" name="mainProductName" info="产品名称" class="int-xlarge"><span id="tip" style="margin: auto 5px;"></span></p>
            </li>
            <li>
                <p class="tab-form-name"><i>*</i> 产品计费周期</p>
                <p><select class="int-xlarge" name="billingCycle" id="billCycle"></select></p>
            </li>
            <li>
                <p class="tab-form-name"><i>*</i>定价策略</p>
                <p><input type="text" readonly name="strategyName" info="定价策略" class="int-xlarge"><input type="hidden" name="pricePolicy" class="int-xlarge"></p>
                <p><button class="btn-query colo-fff" id="selectStrategy">修改定价策略</button></p>
            </li>
            <li>
                <p class="tab-form-name">&nbsp;</p>
                <p><button class="btn-query" id="save">修 改</button></p>
                <p><button class="btn-query" id="cancel">取 消</button></p>
            </li>
        </ul>
    </div>
</div>
<!--弹出框-->
<div class="pop-bill" id="policyList" style="display:none">
    <div class="pop-bg"></div>

    <div class="popbill-cnt">
        <div class="pop-title">选择定价策略</div>
        <div class="pop-close"><a href="javascript:void(0);" onclick="$('#policyList').hide();"><img src="${_base}/resources/citicbilling/images/close.png"></a></div>
        <div class="bill-list">
            <table class="table">
                <thead>
                <tr>
                    <td>策略ID</td>
                    <td>策略名称</td>
                    <td>策略类型</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="strategyData"></tbody>
            </table>
            <nav class="page">
                <ul class="pagination" id="pagination-ul"></ul>
            </nav>
        </div>
    </div>
</div>
<!--弹出框END-->
<script id="strategyListTemple" type="text/x-jsrender">
	<tr>
		<td>{{:policyId}}</td>
			<td>{{:policyName}}</td>
			<td>枚举</td>
			<td>
			<a class="view-strategy" href="javascript:void(0);" policyId="{{:policyId}}" policyName="{{:policyName}}">选择</a>
			</td>
	</tr>
</script>
</body>
</html>