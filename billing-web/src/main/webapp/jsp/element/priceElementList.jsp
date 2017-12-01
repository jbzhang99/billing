<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>定价元素管理</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

<div class="main-right">
	<div class="zuhu-title">
		<ul>
			<li>定价元素管理</li>
		</ul>
	</div>
		<div class="query">
			<form id="queryForm" action="">
			<ul>
				<li>
					<p>主产品ID</p>
					<span><input class="int-large" name="mainProductId" type="text"></span>
				</li>
				<li>
					<p>主产品名称</p>
					<span><input class="int-large" name="mainProductName" type="text"></span>
				</li>
				<li>
					<p>计费周期</p>
					<span><select class="set-large" id="cycle" name="billingCycle"><option value="">请选择</option></select></span>
				</li>
			</ul>
			</form>
			<ul>
			<li>
				<p>&nbsp;</p>
				<button class="btn-query" id="submit">查  询</button>
			</li>
			</ul>
		</div>
		<div class="query-list">
			<div class="cnt-export">
				<ul>                                                                                                                                                 
					<li class="right">
						<button id="elementAdd" class="btn-query"><i class=" icon-plus"></i>新建定价元素</button>
						<!-- <button class="btn-query">导出</button> -->
					</li>
				</ul>
			</div>
			<table class="table table-border">
				<thead>
					<tr>
						<td>序号</td>
						<td>主产品ID</td>
						<td>主产品名称</td>
						<td>产品子目录名称</td>
						<td>定价元素</td>
						<td>计费周期 </td>
						<td>定价策略 </td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="elementData"></tbody>
			</table>
		</div>
		<!-- <nav class="page">
        	<ul class="pagination" id="pagination-ul"></ul>
   		</nav> -->
</div>

<script id="elementListTemple" type="text/x-jsrender">
			<tr class="element-record {{if (#index+1)%2==0}}bg-block{{/if}}"> 
			<input type="hidden" name="mainProductId" value="{{:product.mainProductId}}"/>
			<input type="hidden" name="categoryId" value="{{:product.categoryId}}"/>     
						<td>{{:index}}</td>                                                                                               
						<td>{{:product.mainProductId}}</td>
						<td>{{:product.mainProductName}}</td>
						<td>{{:product.categoryName}}</td>
						<td style="padding:0px">
							<table class="none-border">
								{{for product.elements}}
								<tr>
									<td>{{:specTypeName}}</td>
								</tr>
								{{/for}}
							</table>	
						</td>
						<td style="padding:0px">
							<table class="none-border">
								{{for product.elements}}
								<tr>
									<td>{{if billingCycle=='WEEK'}}周{{else billingCycle=='DAY'}}日{{else billingCycle=='MONTH'}}月{{else billingCycle=='YEAR'}}年{{/if}}</td>
								</tr>
								{{/for}}
							</table>	
						</td>
						<td style="padding:0px">
							<table class="none-border">
								{{for product.elements}}
								<tr>
									<td>{{:pricePolicyName}}</td>
								</tr>
								{{/for}}
							</table>	
						</td>
						<td>
							<a class="view-element" href="javascript:void(0);">查看&nbsp;</a>
							<a class="edit-element" href="javascript:void(0);">编辑&nbsp;</a>
							<a class="del-element" href="javascript:void(0);">删除</a>
						</td>
					</tr>
</script>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/element/priceElementList', function (PriceElementPager) {
                var pager = new PriceElementPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>