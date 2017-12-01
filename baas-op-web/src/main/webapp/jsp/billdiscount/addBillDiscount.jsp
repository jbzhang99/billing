<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>账单级优惠-新建优惠产品</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	<div class="management">
    <div class="management-title step1">请选择优惠产品类型</div>
    <input name="productId" value="${productId }" style="display:none;">
    <input name="viewOrUpdateflag" value="${flag }" style="display:none;">
    
    <!-- tab begin -->
    <div class="Discount">
     <div class="Discount-tta" ${not empty flag && dt!='mz' ? "style='display:none;'" : ""}>
     <ul class="Discounta ${not empty flag && dt=='mz' ? 'current' : 'current'}">
      <p>满赠优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
     <div class="Discount-ttb" ${not empty flag && dt!='mj' ? "style='display:none;'" : ""}>
     <ul class="Discountb ${not empty flag && dt=='mj' ? 'current' : ''}">
      <p>满减优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
     <div class="Discount-ttc" ${not empty flag && dt!='dz' ? "style='display:none;'" : ""}>
     <ul class="Discountc ${not empty flag && dt=='dz' ? 'current' : ''}">
      <p>限时折扣优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
     <div class="Discount-tta" ${not empty flag && dt!='bd' ? "style='display:none;'" : ""}>
     <ul class="Discountd ${not empty flag && dt=='bd' ? 'current' : ''}">
      <p>保底优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
     <div class="Discount-ttb" ${not empty flag && dt!='fd' ? "style='display:none;'" : ""}>
     <ul class="Discounte ${not empty flag && dt=='fd' ? 'current' : ''}">
      <p>封顶优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
    </div>
    <!-- tab end -->
    
    <!--tab标签第1块 begin-->
	<%@ include file="/jsp/billdiscount/addbilldiscount/addMZ.jsp"%>
    <!--tab标签第1块 end-->
    
    <!--tab标签第2块 begin-->
	<%@ include file="/jsp/billdiscount/addbilldiscount/addMJ.jsp"%>
    <!--tab标签第2块 end-->
    
    <!--tab标签第3块 begin-->
	<%@ include file="/jsp/billdiscount/addbilldiscount/addDZ.jsp"%>
    <!--tab标签第3块 end-->
    
    <!--tab标签第4块 begin-->
	<%@ include file="/jsp/billdiscount/addbilldiscount/addBD.jsp"%>
    <!--tab标签第4块 end-->
    
    <!--tab标签第5块 begin-->
	<%@ include file="/jsp/billdiscount/addbilldiscount/addFD.jsp"%>
    <!--tab标签第5块 end-->
    
   </div>
</div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/billdiscount/addBillDiscount', function (AddBillDiscountPager) {
		var pager = new AddBillDiscountPager({element: document.body});
		pager.render();
	});
})();
</script>

</html>