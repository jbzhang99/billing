<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>编辑信控规则</title>
    <%@ include file="/inc/inc.jsp"%>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
<div class="wrapper">
   <div class="management"><!--资费管理外侧-->
      <div class="nav-tplist-title peiz-title">
            <div class="title-left">编辑信控规则</div>
            </div>
   <div class="management-cnt">
    <div class="nav-form">
                 <ul>
                 <li>
                 <p class="word"><span class="f00">*</span>信控规则名称</p>
                 <p>
                 	<input id="ruleId" style="display:none" value="${ruleInfo.ruleId}">
                 	<input id="ruleName" type="text" class="int-medium" value ="${ruleInfo.ruleName}">
                 	<span class="regsiter-note" id="ruleNameMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="ruleNameMsg"></span>
				  	</span>
                 </p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word"><span class="f00">*</span>催缴值金额</p>
                 <p>
                 	<input id="pressPayment" type="text" class="int-medium" value = "${ruleInfo.pressPayment}">
                 	<span class="regsiter-note" id="pressPaymentMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="pressPaymentMsg"></span>
				  	</span>
                 </p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">信控规则描述</p>
                 <p>
                 	<textarea id="description" class="textarea-xxlarge">${ruleInfo.description}</textarea>
                 	<span class="regsiter-note" id="descriptionMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="descriptionMsg"></span>
				  	</span>
                 </p>
                 </li>
                 </ul>
                </div>
   		 <div class="configure-btn-ctn pad130">
           
           <div class="configure-btn-ctn-left configure-margin">
           <ul>
           <li>
           		<input id="editBtn" type="button" class="bass-btn peiz-btn" value="保  存">
           		<input id="cancelBtn" type="button" class="bass-btn peiz-btn" value="取  消">
           	</li>
           </ul>
          </div>
         </div>         
   </div>
   </div>
   </div>
   <!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
<script type="text/javascript">
(function () {
	seajs.use('app/jsp/creditcontrol/editStandard', function (editStandardPager) {
		var pager = new editStandardPager({element: document.body});
		pager.render();
	});
})();
</script>
</body>
</html>
