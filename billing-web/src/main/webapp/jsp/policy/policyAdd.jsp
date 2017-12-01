<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>新建信控规则</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

	<div class="main-right" style="float:left;">
		<div class="zuhu-title">
		<ul>
			<li>新建信控规则</li>
		</ul>
		</div>
		<div class="tab-form">
		<form id="policyForm">
	      <ul>
	      <input type="hidden" name="custIds" >
	       <li>
	        <p class="tab-form-name"><i>*</i>信控规则名称</p>
	        <p><input name="ruleName" type="text" class="int-xxlarge"></p>
	       </li>
	        <li>
	        <p class="tab-form-name"><i>*</i>请配置租户信息</p>
	        <p><input name="custId" type="text" class="int-xxlarge"></p>
	       </li>
	        <li>
	        <p class="tab-form-name"><i>*</i>催缴值金额(元)</p>
	        <p><input name="pressPayment" type="text" class="int-xlarge"></p>
	       </li>
	       <li>
	        <p class="tab-form-name">信控规则描述</p>
	        <p><textarea name="description" class="textarea-xxlarge"></textarea></p>
	       </li>
	        
	      </ul>
	      </form>
	      <ul>
	      <li>
	       		<p class="tab-form-name">&nbsp;</p>
	       		<p><button class="btn-query" id="savePolicy">保 存</button></p>
	       		<p><button class="btn-query" onclick="javascript:window.location.href='${_base}/policy/toPolicyList';">取 消</button></p>
	       </li>
	       </ul>
	    </div>
		
	</div>

<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/policy/policyAdd', function (PolicyAddPager) {
                var pager = new PolicyAddPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>