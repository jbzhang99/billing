<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>信控规则查看</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

	<div class="main-right" style="float:left;">
		<div class="zuhu-title">
		<ul>
			<li>信控规则查看</li>
		</ul>
		</div>
		<div class="tab-form">
		<form id="policyForm">
	      <ul>
	      <input type="hidden" name="custIds" >
	       <li>
	        <p class="tab-form-name"><i>*</i>信控规则名称</p>
	        <p><input name="ruleName" value="${policyVo.policyName}" type="text" class="int-xxlarge" readonly unselectable="on"></p>
	       </li>
	        <li>
	        <p class="tab-form-name"><i>*</i>请配置租户信息</p>
	        <p><input name="custId" type="text" value="${policyVo.extCustId}" class="int-xxlarge" readonly unselectable="on"></p>
	       </li>
	        <li>
	        <p class="tab-form-name"><i>*</i>催缴值金额(元)</p>
	        <p><input name="pressPayment" type="text"  value="${policyVo.pressPayment}" class="int-xlarge" readonly unselectable="on"></p>
	       </li>
	       <li>
	        <p class="tab-form-name">信控规则描述</p>
	        <p><textarea name="description" class="textarea-xxlarge" readonly unselectable="on">${policyVo.description}</textarea></p>
	       </li>
	        <li>
	       		<p class="tab-form-name">&nbsp;</p>
	       		<p><button class="btn-query" onclick="window.open('about:blank','_self'); window.close();">关 闭</button></p>
	       </li>
	      </ul>
	      </form>
	    </div>
		
	</div>

<script type="text/javascript" charset="utf-8">
       /*  $(function () {
            seajs.use('app/jsp/policy/policyAdd', function (PolicyAddPager) {
                var pager = new PolicyAddPager({element: document.body});
                pager.render();
            });
        }) */
    </script>
</body>
</html>