<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>缓存Key查询与删除</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">
		<div class="management">
			<!--外侧-->
			<div class="nav-tplist-title peiz-title">
				<div class="title-left">缓存Key查询与删除</div>
			</div>


			<div class="management-cnt">
				<!--查询区域-->
				<div class="nav-form">
					<ul>
						<li>
							<p class="word">类型</p>
							<p>
								<select class="int-medium" id="keyType">
									<option>hash</option>
								</select>
							</p>
						</li>
					</ul>

					<ul>
						<li>
							<p class="word">Key</p>
							<p>
								<input type="text" class="int-medium" id="keyTable" />
							</p>
						</li>
						<li>
							<p>
								<input type="text" class="int-medium" id="keyColumn" />
							</p>
						</li>
					</ul>
					<ul>
						<li class="btn-margin"><p>
								<input type="button" value="搜  索" id="searchBtn"
									class="bass-btn nav-form-btn key-butn">
							</p></li>
					</ul>

				</div>
				<!--查询区域结束-->
				<div class="key-tplist">
					<ul>
						<li>查询结果</li>
						<li id="keyResult"></li>
					</ul>
				</div>
				<div class="configure-btn-ctn">

					<div class="configure-btn-ctn-left">
						<ul>
							<li><input type="button" id="gobackBtn" class="peiz-btn-cancel"
								value="返   回"></li>
							<li><input type="button" class="bass-btn peiz-btn"
								value="删   除" id="deleteKeyBtn"></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--中间部分结束-->
		<!--底部-->
		<%@ include file="/inc/foot.jsp"%>
		<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/configure/key', function (KeyPager) {
		var pager = new KeyPager({
			element: document.body,
			tableName:"<c:out value="${tableName}"/>",
			tableId:"<c:out value="${tableId}"/>",
			indexKey:"<c:out value="${indexKey}"/>"
		
		});
		pager.render();
	});
})();
</script>
</html>

