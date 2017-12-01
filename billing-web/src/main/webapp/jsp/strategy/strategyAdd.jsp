<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>新建定价策略</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

	<div class="main-right" style="float:left;">
	<div class="zuhu-title">
		<ul>
			<li>新建定价策略</li>
		</ul>
	</div>
		<div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>策略名称</p>
        <p><input type="text" name="policyName" maxlength="32" class="int-xlarge"></p>
       </li>
       <div class="varList">
       	   <li class="varTemp">
	        <p class="tab-form-name"><i>*</i>请确定变量名称</p>
	        <p><input type="text" name="varName" maxlength="32" class="int-small" placeholder="变量名称"></p>
	        <p><input type="text" name="varCode" maxlength="32" class="int-small" placeholder="变量编码"></p>
	        <p><select class="int-small var-unit"></select></p>
	        <p class="var-type-p" style="display:none"><select class="int-small var-type"></select></p>
	        <p class="add-policy-var"><a href="#" class="addVar"><img src="${_base}/resources/citicbilling/images/stepa.png"></a></p>
	        <p><a href="#" class="delVar"><img src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
	       </li>
       </div>
        <li>
        <p class="tab-form-name"><i>*</i>请选择策略类型</p>
        <p><select name="policyType" class="int-xlarge"></select></p>
       </li>
 
       <li>
        <p class="tab-form-name">请配置定价价格：</p>
       </li>
       <div class="detailList">
	       <li>
	       	<div class="factors first-factors">
		       	<div class="factor">
		       		<input type="hidden" name="fvarName">
		    		<input type="hidden" name="fvarCode">
		    		<input type="hidden" name="fvarType">
		    		<input type="hidden" name="fvarUnitId">
		    		<input type="hidden" name="fvarUnitName">
		    		<input type="hidden" name="fvarUnitCode">
			       	<p class="tab-form-name tab-var-name"><i>*</i>：</p>
			        <div class="factor-value-p"><input type="text" name="factorValue" maxlength="20" class="int-mini"></div>
			        <p class="tab-unit-name"></p>
		        </div>
	        </div>
	        <p><i>*</i>价格为</p>
	        <p class="price-p"><input type="text" name="price" maxlength="125" class="int-mini price-input"></p>
	        <p><select name="priceType" class="int-mini price-type"></select></p>
	        <p>说明</p>
	        <p><input type="text" name="comments" maxlength="20" class="int-mini"></p>
	        <p><a href="#" class="addDetail"><img src="${_base}/resources/citicbilling/images/stepa.png"></a></p>
	       </li>
       </div>
      
        <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button class="btn-query" id="strategy-submit">保 存</button></p>
       		<p><button class="btn-query" onclick="javascript:window.location.href='${_base}/strategy/toStrategyList';">取 消</button></p>
       </li>
       
      </ul>
     </div>
     </div>

	<div id="varTemp" class="varTemp" style="display:none;">
		<li>
	        <p class="tab-form-name"><i>*</i></p>
	      	<p><input type="text" maxlength="32" name="varName" class="int-small" placeholder="变量名称"></p>
	        <p><input type="text" maxlength="32" name="varCode" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" class="int-small" placeholder="变量编码"></p>
	        <p><select class="int-small var-unit"></select></p>
	        <p class="var-type-p" style="display:none"><select class="int-small var-type"></select></p>
	        <p class="add-policy-var"></p>
	        <p><a href="#" class="delVar"><img src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
	    </li>
    </div>
    <div id="detailTemp" class="detailTemp" style="display:none;">
    	<li>
	    	<div class="factors">
		    	<div class="factor">
		    		<input type="hidden" name="fvarName">
		    		<input type="hidden" name="fvarCode">
		    		<input type="hidden" name="fvarType">
		    		<input type="hidden" name="fvarUnitId">
		    		<input type="hidden" name="fvarUnitName">
		    		<input type="hidden" name="fvarUnitCode">
			       	<p class="tab-form-name tab-var-name"><i>*</i>：</p>
			        <div class="factor-value-p"><input type="text" maxlength="20" name="factorValue" class="int-mini"></div>
			        <p class="tab-unit-name"></p>
		        </div>
	        </div>
	        <p><i>*</i>价格为</p>
	        <p class="price-p"><input type="text" name="price" maxlength="125" class="int-mini price-input"></p>
	        <p><select name="priceType" class="int-mini price-type"></select></p>
	        <p>说明</p>
	        <p><input type="text" maxlength="20" name="comments" class="int-mini"></p>
        <p><a href="#" class="delDetail"><img src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
       </li>
	</div>
	<div class="factor factorTemp" style="display:none;">
		<input type="hidden" name="fvarName">
   		<input type="hidden" name="fvarCode">
   		<input type="hidden" name="fvarType">
   		<input type="hidden" name="fvarUnitId">
   		<input type="hidden" name="fvarUnitName">
   		<input type="hidden" name="fvarUnitCode">
   		<p>&nbsp;&nbsp;&nbsp;</p>
       	<p class="tab-var-name"><i>*</i>：</p>
        <div class="factor-value-p"><input type="text" maxlength="20" name="factorValue" class="int-mini"></div>
        <p class="tab-unit-name"></p>
    </div>
    <div id="policyAddVar" style="display:none;">
		<a href="#" class="addVar"><img src="${_base}/resources/citicbilling/images/stepa.png"></a>
    </div>
    <div id="priceNum" style="display:none;">
    	<input type="text" name="price" maxlength="125" class="int-mini price-input">
    </div>
    <div id="priceText" style="display:none;">
    	<input type="text" name="price" maxlength="125" class="int-small" display="可以输入价格和公式">
    </div>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/strategy/strategyAdd', function (StrategyAddPager) {
                var pager = new StrategyAddPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>