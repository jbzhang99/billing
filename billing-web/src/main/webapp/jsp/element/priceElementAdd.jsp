<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>新建定价元素</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

<!--弹出框-->
<div class="pop-bill" id="policyList" style="display:none">
	<div class="pop-bg"></div>
	
	<div class="popbill-cnt">
		<div class="pop-title">选择定价策略</div>
		<div class="pop-close"><a href="javascript:void(0);" onclick="$('#policyList').hide();"><img src="${_base}/resources/citicbilling/images/close.png"></a></div>
		<div class="bill-list">
			<table class="table">
				<thead>
					<tr>
						<td>策略ID</td>
						<td>策略名称</td>
						<td>策略类型</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="strategyData"></tbody>
			</table>
			<nav class="page">
        	<ul class="pagination" id="pagination-ul"></ul>
    		</nav>
		</div>
	</div>
</div>
<!--弹出框END-->

	<input type="hidden" id="mainProductId" value="${mainProductId}">
	<input type="hidden" id="mainProductName" value="${mainProductName}">
	<div class="main-right" style="float:left;">
	<div class="zuhu-title">
		<ul>
			<li>新建定价</li>
		</ul>
	</div>
		<div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>请选择行业类型</p>
        <p><select id="tradeType" class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择主产品目录</p>
        <p><select id="mainPro"  class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择子产品目录</p>
        <p><select id="subPro"  class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择计费模式</p>
        <p><select id="mode" class="int-xlarge"><option unid="-1">请选择</option></select></p>
        <p><i>*</i>计费模式编码</p>
        <p><input type="text" name="modeCode" class="int-small"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>请选择计费周期</p>
        <p><select id="cycle" class="int-xlarge"><option unid="-1">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name">请配置定价元素：</p>
       </li>
       <div id="elementList">
       	   <div class="elementTemp">
		       <li>
		       	<input type="hidden" name="policyId">
		       	<p class="tab-form-name"><i>*</i>元素名：</p>
		       	<p><select name="specTypeName"  class="int-xlarge spec-type-name"><option value="">请选择</option></select></p>
		        <p><i>*</i>定价策略名称：</p>
		        <p><input type="text" name="policyName" class="int-small spec-type-name" readonly></p>
		        <p class="red policy-select" style="cursor:pointer;">选择定价策略</p>
		       </li>
	       </div>
       </div>
        
       <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button class="btn-query colo-fff" id="addElement">继续添加</button></p>
       </li>
        <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button class="btn-query" id="element-submit">保 存</button></p>
       		<p><button class="btn-query" onclick="javascript:window.location.href='${_base}/priceElement/toPriceElementList';" >取 消</button></p>
       </li>
      </ul>
     </div>
     </div>
     
    <div id="elementTemp" class="elementTemp" style="display:none;">
		<li>
			<input type="hidden" name="policyId">
	       	<p class="tab-form-name"><i>*</i>元素名：</p>
	        <p><select name="specTypeName"  class="int-xlarge spec-type-name"><option value="">请选择</option></select></p>
	        <p><i>*</i>定价策略名称：</p>
	        <p><input type="text" name="policyName" class="int-small " readonly></p>
	        <p class="red policy-select" style="cursor:pointer;">选择定价策略</p>
	        <p><a href="#" class="delElement"><img src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
       </li>
    </div> 

<script id="strategyListTemple" type="text/x-jsrender">
	<tr>
		<td>{{:policyId}}</td>
			<td>{{:policyName}}</td>
			<td>
				{{if policyType == 'STEP'}}
									阶梯
								{{else policyType == 'BRACKET'}}
									分档
								{{else policyType == 'EXPR'}}
									表达式
								{{else}}
									穷举
								{{/if}}
			</td>
			<td>
			<a class="view-strategy" href="javascript:void(0);" policyId="{{:policyId}}" policyName="{{:policyName}}">选择</a>
			</td>
	</tr>
</script>

<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/element/priceElementAdd', function (PriceElementAddPager) {
                var pager = new PriceElementAddPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>