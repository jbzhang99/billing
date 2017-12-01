<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>定价策略管理</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>

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

<body>

	<div class="main-right">
		<div class="zuhu-title">
			<ul>
				<li>定价策略管理</li>
			</ul>
		</div>
			<div class="query">
			<form id="queryForm" action="">
				<ul>
					<li>
						<p>策略ID</p>
						<span><input class="int-large" name="policyId" type="text"></span>
					</li>
					<li>
						<p>策略名称</p>
						<span><input class="int-large" name="policyName" type="text"></span>
					</li>
					<li>
						<p>策略类型</p>
						<span><select id="policyType" name="policyType" class="set-large"></select></span>
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
						<li class="right"><button class="btn-query" id="BTN_IMPORT">批量导入</button></li>                                                                                                                                            
						<li class="right"><button id="strategyAdd" class="btn-query"><i class=" icon-plus"></i>新建定价策略</button></li>
					</ul>
				</div>
				<table class="table table-border">
					<thead>
						<tr>
							<td>序号</td>
							<td>策略ID</td>
							<td>策略名称</td>
							<td>策略类型</td>
							<td>变量</td>
							<td>单位</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="strategyData"></tbody>
				</table>
			</div>
			<nav class="page">
        	<ul class="pagination" id="pagination-ul"></ul>
    		</nav>
	</div>


<script id="strategyListTemple" type="text/x-jsrender">
	<tr class="strategy-record {{if (#index+1)%2==0}}bg-block{{/if}}">
	<input type="hidden" name="policyId" value="{{:policyId}}"/>
							<td>{{:index}}</td>  
							<td>{{:policyId}}</td>
							<td>{{:policyName}}</td>
							<td>
								{{if policyType == 'STEP'}}
									阶梯
								{{else policyType == 'BRACKET'}}
									分档
								{{else policyType == 'EXPR'}}
									表达式
								{{else}}
									穷举
								{{/if}}
							</td>
                   			<td style="padding:0px">
								<table class="none-border">
									{{for variableRecordVos}}
									{{if #index== #size}}
										<tr class="tr-border">
											<td>{{:varName}}</td>
										</tr>
									{{else}}
										<tr class="">
											<td>{{:varName}}</td>
										</tr>
									{{/if}}
									{{/for}}
								</table>
							</td>
							<td style="padding:0px">
								<table class="none-border">
									{{for variableRecordVos}}
									{{if #index== #size}}
										<tr class="tr-border">
											<td>{{:unitName}}</td>
										</tr>
									{{else}}
										<tr class="">
											<td>{{:unitName}}</td>
										</tr>
									{{/if}}
									{{/for}}
								</table>
							</td>
							<td>
							<a class="view-strategy" href="javascript:void(0);">查看&nbsp;</a>
							<a class="edit-strategy" href="javascript:void(0);">编辑&nbsp;</a>
							<a class="del-strategy" href="javascript:void(0);">删除</a>
							<a class="export-strategy" href="javascript:void(0);">模版导出&nbsp;</a>
							</td>
						</tr>
</script>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/strategy/strategyList', function (StrategyPager) {
                var pager = new StrategyPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>