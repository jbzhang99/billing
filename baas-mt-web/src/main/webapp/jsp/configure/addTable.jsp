<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>DSHM加载新表</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	<div class="management"><!--资费管理外侧-->



		<div class="management-cnt"><!--资费管理内容-->
			<!--查询区域-->
			<div class="nav-form">
				<ul>
					<li>
						<p class="word">选择数据库</p>
						<p><select class="int-medium" id="dbSelect"></select></p>
					</li>
					<li>
						<p class="word">选择加载表</p>
						<p><select class="int-medium" id="tableSelect"></select></p>
					</li>

				</ul>

			</div><!--查询区域结束-->

			<div class="management-cnt—border"><!--虚线--></div>


			<div class="nav-tplist"><!--查询结果-->

				<div class="nav-tplist-title"><!--查询结果标题-->
					<div class="title-left"><i class="icon-th-list"></i>搜索结果列表</div>
					<%--<div class="title-right-search">
						<p><input type="text" class="int180"> </p>
						<p><i class="icon-search"></i></p>
					</div>--%>
				</div><!--查询结果标题结束-->

				<div class="nav-tplist-table"><!--查询结果列表-->

					<table width="100%" border="0" class="table-bj">
						<tr class="tr-backgrond">
							<td class="left-border" width="10%"><input type="checkbox" id="checkAll">选择</td>
							<td>操作</td>
							<td>字段名称</td>
							<td>数据类型</td>
							<td>是否支持索引</td>
							<td>是否主键</td>
							<td class="right-border">是否作为索引键</td>
						</tr>
						<tbody id="tableFieldData"></tbody>
					</table>
				</div>

				<div class="configure-btn-ctn">

					<div class="configure-btn-ctn-left">
						<ul>
							<li><input type="button" class="peiz-btn-cancel" value="取消"></li>
							<li><input type="button" id="submitAddTable" class="bass-btn peiz-btn" value="提交"></li>
						</ul>

					</div>

					<%--<div class="paging-large">
						<ul>
							<li> <a href="#">&lt;</a> </li>
							<li class="active"> <a href="#">1 </a> </li>
							<li> <a href="#">2 </a> </li>
							<li> <a href="#">3 </a> </li>
							<li> <a href="#">4 </a> </li>
							<li> <span>…… </span> </li>
							<li> <a href="#">20 </a> </li>
							<li> <a href="#">&gt;</a> </li>
						</ul>
					</div>--%>


				</div>
			</div>


		</div>




	</div>
</div>
<!--中间部分结束-->

<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>
<script id="tableFieldDataTmpl" type="text/x-jsrender">
	<tr>
		<td class="left-border"><input type="checkbox"  class="int-checkbox1 b selectFlag"></td>
		<td><input type="button" style="width: 40px;height: 20px;background-color: rgb(28, 154, 236);color: rgb(255, 255, 255);" class="editFlag" value="编辑"></td>
		<td class="fieldName">{{:fieldName}}</td>
		<td><span>{{:fieldType}}</span></td>
		<td>
			<p><select class="select-small-float supportIndexSelect">
			<option value="true">是</option>
			<option value="false" selected>否</option>
			</select></p>
			<span>否</span>
		</td>
		<td>
			<p><select class="select-small-float isPrimarySelect">
			<option value="true">是</option>
			<option value="false" selected>否</option>
			</select></p>
			<span>否</span>
		</td>
		<td class="right-border">
			<p><select class="select-small-float asIndexSelect">
			<option value="true">是</option>
			<option value="false"  selected>否</option>
			</select></p>
			<span>否</span>
		</td>
	</tr>
</script>
<script type="text/javascript">
(function () {
	seajs.use('app/jsp/configure/addTable', function (AddTablePager) {
		var pager = new AddTablePager({element: document.body});
		pager.render();
	});
})();
</script>
</html>

