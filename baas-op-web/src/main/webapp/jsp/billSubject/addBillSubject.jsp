<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title><c:choose><c:when test="${subjectInfo!=null}">编辑</c:when><c:otherwise>新增</c:otherwise></c:choose>账单科目</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	<div class="management"><!--资费管理外侧-->
	<div class="nav-tplist-title peiz-title">
	
		<div class="management-cnt"><!--资费管理内容-->
			<!--查询区域-->
			<div class="nav-form">
				<ul>
					<li>
		                 <p class="word"><span class="f00">*</span>账单科目名称</p>
		                 <p><input type="text" id="subjectName"  class="int-medium" value="${subjectInfo.subjectName }"></p>
		                 <input type ="hidden" id="subjectId" name="subjectId" value ="${subjectInfo.subjectId }" />
	                 </li>
				</ul>
				<ul>
	                 <li>
		                 <p class="word">账单科目描述</p>
		                 <p><textarea id="subjectDesc" class="textarea-xxlarge" maxlength="255">${subjectInfo.subjectDesc }</textarea></p>
	                 </li>
                 </ul>
			</div><!--查询区域结束-->

			<div class="configure-btn-ctn pad130">
           
	           <div class="configure-btn-ctn-left configure-margin">
	           <ul>
							<li><input type="button" id="submitBtn" class="bass-btn peiz-btn" value="提交"></li>
	           <li><input type="button" id="cancelBtb" class="peiz-btn-cancel" value="返回"></li>
	           </ul>
	          </div>
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
<script type="text/javascript">
(function () {
	seajs.use('app/jsp/billSubject/addBillSubject', function (AddBillSubjectPager) {
		var pager = new AddBillSubjectPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>

