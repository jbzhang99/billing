<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>标准产品定价</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
       $(function () {
            seajs.use('app/jsp/standardProduct/productList', function (ProductListPager) {
                var pager = new ProductListPager({element: document.body});
                pager.render();
            });
        });
    </script>
</head>
<body>
<div class="main-right">
    <div class="zuhu-title">
        <ul>
            <li>标准产品定价</li>
        </ul>
    </div>
    <div class="query">
        <ul>
            <li>
                <p>产品ID</p>
                <span><input name="productId" class="int-large" type="text"></span>
            </li>
            <li>
                <p>服务名称</p>
                <span><input name="productName" class="int-large" type="text"></span>
            </li>
            <li>
                <p>产品计费周期</p>
                <span><select name="billingCycle" id="billCycle" class="set-large"></select></span>
            </li>
            <li>
                <button class="btn-query" id="query">查  询</button>
            </li>
        </ul>

    </div>
    <div class="query-list">
        <div class="cnt-export">
            <ul>
                <li class="right"><button class="btn-query" id="newPro"><i class=" icon-plus"></i>新建标准产品</button></li>
            </ul>
        </div>
        <table class="table table-border" id="productList" style="display: none">
            <thead>
                <tr>
                    <td>序号</td>
                    <td>产品ID</td>
                    <td>产品一级目录</td>
                    <td>产品二级目录</td>
                    <td>产品名称</td>
                    <td>规格</td>
                    <td>价格（元）</td>
                    <td>计费周期</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody id="productData"></tbody>
        </table>
    </div>
    <div class="query-list wcx-center" style="display: none">
        <img src="${_base}/resources/citicbilling/images/wcx.png" />
    </div>
    <nav class="page">
        <ul class="pagination" id="pagination-ul"></ul>
    </nav>
</div>
<!--弹出框END-->
<script id="productListTemple" type="text/x-jsrender">
    {{if #index%2==1 }}
	<tr class="bg-block">
	{{else}}
	<tr>
	{{/if}}
        <td>{{:index}}</td>
        <td>{{:id}}</td>
        <td>{{:primaryCategoryName}}</td>
        <td>{{:secondCategoryName}}</td>
        <td>{{:mainProductName}}</td>
        <td style="padding:0px">
            <table class="none-border">
                {{for priceVOs}}
                <tr>
                    <td>{{:specName}}</td>
                </tr>
                {{/for}}
            </table>
        </td>
        <td style="padding:0px">
            <table class="none-border">
                {{for priceVOs}}
                <tr>
                    <td>{{:price}}</td>
                </tr>
                {{/for}}
            </table>
        </td>
        <td>{{:billingCycle}}</td>
        <td><a href="${_base}/standardProduct/toViewStandardProduct/{{:id}}">查看</a><a href="${_base}/standardProduct/toEditStandardProduct/{{:id}}">编辑</a><a href="javascript:void(0);" class="p_del" productId="{{:categoryId}}">删除</a></td>
    </tr>
</script>
</body>
</html>