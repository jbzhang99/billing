<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>产品目录</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

	<div class="main-right">
		<div class="zuhu-title">
			<ul>
				<li>定价目录管理</li>
			</ul>
		</div>
			<div class="query">
			<form id="queryForm" action="">
				<ul>
					<li>
						<p>主产品ID</p>
						<span><input class="int-large" id="mainProId" type="text"></span>
					</li>
					<li>
						<p>主产品名称</p>
						<span><input class="int-large" id="mainProName" type="text"></span>
					</li>
					
				</ul>
			</form>
			<ul>
			<li>
				<p>&nbsp;</p>
				<button class="btn-query"  id="productSearch">查  询</button>
			</li>
			</ul>
			</div>
			<div class="query-list">
				<div class="cnt-export">
					<ul>                                                                                                                                                 
						<li class="right"><button id="productAdd" class="btn-query"><i class=" icon-plus"></i>新建目录</button></li>
					</ul>
				</div>
				<table class="table table-border">
					<thead>
						<tr>
							<td>序号</td>
							<td>主产品ID</td>
							<td>主产品名称</td>
							
							<td>主产品目录ID</td>
							<td>主产品目录</td>
							
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="productData"></tbody>
				</table>
			</div>
			<nav class="page">
        	<ul class="pagination" id="pagination-ul"></ul>
    		</nav>
	</div>


<script id="productListTemple" type="text/x-jsrender">
	<tr class="strategy-record {{if (#index+1)%2==0}}bg-block{{/if}}">
	
							<td>{{:index}}</td>  
							<td>{{:mainProductCode}}</td>
							<td>{{:mainProductName}}</td>
							<td style="padding:0px">
									<table class="none-border">
									{{for childProducts}}
									{{if #index== #size}}
										<tr class="tr-border">
											<td>{{:categoryId}}</td>
										</tr>
									{{else}}
										<tr class="">
											<td>{{:categoryId}}</td>
										</tr>
									{{/if}}
									{{/for}}
								</table>

							</td>
                   			<td style="padding:0px">
								<table class="none-border">
									{{for childProducts}}
									{{if #index== #size}}
										<tr class="tr-border">
											<td>{{:categoryName}}</td>
										</tr>
									{{else}}
										<tr class="">
											<td>{{:categoryName}}</td>
										</tr>
									{{/if}}
									{{/for}}
								</table>
							</td>
							
							<td>
							<a class="view_product" mainId='{{:mainProductCode}}' mainTag='{{:mainTag}}' href="javascript:void(0);">查看&nbsp;</a>
							<a class="edit_product" mainId='{{:mainProductCode}}' mainTag='{{:mainTag}}' href="javascript:void(0);">编辑&nbsp;</a>
							<a class="del_product"  mainId='{{:mainProductCode}}' mainTag='{{:mainTag}}' href="javascript:void(0);">删除</a>
							</td>
						</tr>
</script>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/product/productList', function (ProListPager) {
                var pager = new ProListPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>