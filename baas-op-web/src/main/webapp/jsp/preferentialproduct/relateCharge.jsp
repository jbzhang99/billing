<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>关联费用科目</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	
	<div class="management"><!--资费管理外侧-->
 
    <!--tab标签第1块-->
   
   
    <div class="configure">
     <div class="tab-form">
      <ul>
       <li>
     
        <p class="tab-form-name"><i>*</i>产品名称</p>
        <p><input type="text" value="${productName}" class="int-xlarge" id="activityName" readOnly></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>产品类型</p>
        <p><input type="text" value="${productType}" class="int-xlarge" id="activityName" readOnly></p>
       </li>
       <li>
       <p class="tab-form-name"><i>*</i>详单科目类型</p>
        <p><select id="chargeType" class="select-small" ><option value="">请选择类型</option></select></p>
       </li>
       
      </ul>
     </div>
    </div>
 
    <div class="configure-btn">
         <input type="button" id="back" value="返回" class="next-btn"><input id="SAVE_BTN" type="button" value="保存" class="next-btn next-btn-hover">
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
	seajs.use('app/jsp/preferentialproduct/relateCharge', function (ChargeProductPager) {
		var pager = new ChargeProductPager({
			element: document.body,
			relateId:"<c:out value="${id}"/>",
			relateName:"<c:out value="${productName}"/>",
			relateType:"<c:out value="${chargeType}"/>"
			});
		pager.render();
	});
})();
</script>

</html>

