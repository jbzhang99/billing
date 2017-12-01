<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>套餐产品-关联详单科目</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
         
   <div class="management"><!--资费管理外侧-->
         <div class="nav-tplist-title peiz-title">
             <c:choose>
                 <c:when test="${salableProductRelated.billingType=='STANDARD_GROUP_TYPE'}">
                     <div class="title-left">标准组合产品关联科目</div>
                 </c:when>
                 <c:otherwise>
                     <div class="title-left">阶梯组合产品关联科目</div>
                 </c:otherwise>
             </c:choose>
         </div>
         <div class="management-cnt">
         		<div class="nav-form" id="addForm">
         		<input type="hidden" id="productId" name="productId" value="${salableProductRelated.productId}"/>
         		<input type="hidden" id="billingType" name="billingType" value="${salableProductRelated.billingType}"/>
         		<ul>
                 <li>
                 <p class="word">产品名称</p>
                 <p><input  type="text" class="select-medium" id="productName" value= "${salableProductRelated.productName}" disabled/></p>
                 </li>
                 </ul>
         		<ul>
                 <li>
                 <p class="word">关联费用科目</p>
                 <p><select class="select-small" id="subjectCode" name="subjectCode" oldValue="${salableProductRelated.subjectCode}"><option value="">请选择</option></select></p>
                 </li>
                 </ul>
         		</div>
         </div>
         <div class="standard-in"><input type="button" value="取消" onclick="window.location.href='${_base}/salableProduct/list';" class="next-btn"><input type="button" value="保存" id="saveForm" class="next-btn next-btn-hover"></div>
	</div>
   </div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/salableproduct/relateSubject', function (RelateSubjectPager) {
		var pager = new RelateSubjectPager({element: document.body});
		pager.render();
	});
})();
</script>

</html>

