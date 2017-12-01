<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>定价元素查看</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

	<div class="main-right" style="float:left;">
	<div class="zuhu-title">
		<ul>
			<li>新建定价</li>
		</ul>
	</div>
		<div class="tab-form">
      <ul>
      	<input type="hidden" id="categoryId" name="categoryId" value="${categoryId}"/>
       <li>
        <p class="tab-form-name"><i>*</i>请选择行业类型</p>
        <p><select id="tradeType" disabled class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择主产品目录</p>
        <p><select id="mainPro" disabled class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择子产品目录</p>
        <p><select id="subPro" disabled class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择计费模式</p>
        <p><select id="mode" disabled class="int-xlarge"><option unid="-1">请选择</option></select></p>
        <p><i>*</i>计费模式编码</p>
        <p><input type="text" name="modeCode" disabled class="int-small"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择计费周期</p>
        <p><select id="cycle" disabled class="int-xlarge"><option unid="-1">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name">请配置定价元素：</p>
       </li>
       <div id="elementList">
       	   <div class="elementTemp" id="fristElement">
		       <li>
		       	<input type="hidden" name="policyId">
		       	<p class="tab-form-name"><i>*</i>元素名：</p>
		       	<p><select name="specTypeName" disabled  class="int-xlarge spec-type-name"><option value="">请选择</option></select></p>
		        <p><i>*</i>定价策略名称：</p>
		        <p><input type="text" name="policyName" class="int-small spec-type-name" readonly></p>
		       </li>
	       </div>
       </div>
        
        <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button class="btn-query" onclick="javascript:window.location.href='${_base}/priceElement/toPriceElementList';" >返 回</button></p>
       </li>
      </ul>
     </div>
     </div>
     
    <div id="elementTemp" class="elementTemp" style="display:none;">
		<li>
			<input type="hidden" name="policyId">
	       	<p class="tab-form-name"><i>*</i>元素名：</p>
	        <p><select name="specTypeName" disabled class="int-xlarge spec-type-name"><option value="">请选择</option></select></p>
	        <p><i>*</i>定价策略名称：</p>
	        <p><input type="text" name="policyName" class="int-small " readonly></p>
       </li>
    </div> 

<script id="strategyListTemple" type="text/x-jsrender">
	<tr>
		<td>{{:policyId}}</td>
			<td>{{:policyName}}</td>
			<td>枚举</td>
			<td>
			<a class="view-strategy" href="javascript:void(0);" policyId="{{:policyId}}" policyName="{{:policyName}}">选择</a>
			</td>
	</tr>
</script>

<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/element/priceElementShow', function (PriceElementShowPager) {
                var pager = new PriceElementShowPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>