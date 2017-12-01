<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>新建标准产品</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
       $(function () {
           if(localStorage){
               localStorage.setItem("productId",'${productId}');
           }
            seajs.use('app/jsp/standardProduct/viewProduct', function (ViewProductPager) {
                var pager = new ViewProductPager({element: document.body});
                pager.render();
            });
        })
    </script>
</head>
<body>
<div class="main-right">
    <div class="zuhu-title">
        <ul>
            <li>新建标准产品定价</li>
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
                <p><input type="text" name="mainProductName" info="产品名称" class="int-xlarge" readonly></p>
            </li>
            <li>
                <p class="tab-form-name"><i>*</i> 产品计费周期</p>
                <p><select class="int-xlarge" name="billingCycle" id="billCycle" disabled></select></p>
            </li>
            <li>
                <p class="tab-form-name"><i>*</i>定价策略</p>
                <p><input type="text" readonly name="strategyName" info="定价策略" class="int-xlarge"><input type="hidden" name="pricePolicy" class="int-xlarge"></p>
                <p><button class="btn-query colo-fff" id="selectStrategy">查看定价策略</button></p>
            </li>
            <li>
                <p class="tab-form-name">&nbsp;</p>
                <p><button class="btn-query" id="cancel">返 回</button></p>
            </li>
        </ul>
    </div>
</div>
</body>
</html>