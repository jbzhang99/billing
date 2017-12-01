<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>查看定价策略</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>

	<div class="main-right" style="float:left;">
	<div class="zuhu-title">
		<ul>
			<li>查看定价策略</li>
		</ul>
	</div>
		<div class="tab-form">
      <ul>
      	<input type="hidden" name="policyId" readonly value="${strategyVO.policyId}">
      	<input type="hidden" id="oldPolicyName" readonly name="oldPolicyName" value="${strategyVO.policyName}" class="int-xlarge">
       <li>
        <p class="tab-form-name"><i>*</i>策略名称</p>
        <p><input type="text" name="policyName" readonly value="${strategyVO.policyName}" class="int-xlarge"></p>
       </li>
       <div class="varList">
       		<c:if test="${strategyVO.variableRecordVos!=null}">
       			<c:forEach items="${strategyVO.variableRecordVos}" var="varVo" varStatus="status">
                  	<li class="varTemp">
				        <p class="tab-form-name"><i>*</i><c:if test="${status.index==0}">请确定变量名称</c:if></p>
				        <p><input type="text" name="varName" readonly value="${varVo.varName}" class="int-small"></p>
				        <p><input type="text" name="varCode" readonly value="${varVo.varCode}" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" class="int-small" placeholder="变量编码"></p>
				        <p><select defaultUnitid="${varVo.unitId}" disabled class="int-small var-unit"></select></p>
				        <p class="var-type-p" style="display:none"><select defaultVarType="${varVo.varType}" disabled class="int-small var-type"></select></p>
			       </li>
               	</c:forEach>
       		</c:if>
       	   
       </div>
        <li>
        <p class="tab-form-name"><i>*</i>请选择策略类型</p>
        <p><select name="policyType" defaultPolicyType="${strategyVO.policyVo.policyType}" disabled class="int-xlarge"></select></p>
       </li>
 
       <li>
        <p class="tab-form-name">请配置定价价格：</p>
       </li>
       <div class="detailList">
       	<c:if test="${strategyVO.policyVo!=null && strategyVO.policyVo.variableVOs!=null}">
	       <c:forEach items="${strategyVO.policyVo.variableVOs}" var="varVo" varStatus="status">
		       	<li class="detailTemp">
		       	<div class="factors <c:if test="${status.index==0}">first-factors</c:if>">
		       		<c:forEach items="${varVo.factorVos}" var="factorVo" varStatus="fstatus">
				       	<div class="factor">
				       		<input type="hidden" name="fvarName" value="${factorVo.varName}">
				    		<input type="hidden" name="fvarCode" value="${factorVo.varCode}">
				    		<input type="hidden" name="fvarType" value="${factorVo.varType}">
				    		<input type="hidden" name="fvarUnitId" value="${factorVo.varUnitId}">
				    		<input type="hidden" name="fvarUnitName" value="${factorVo.varUnitName}">
				    		<input type="hidden" name="fvarUnitCode" value="${factorVo.varUnitCode}">
				    		<c:if test="${fstatus.index>0}"><p>&nbsp;&nbsp;&nbsp;</p></c:if>
					       	<p class="<c:if test="${fstatus.index==0}">tab-form-name </c:if> tab-var-name"><i>*</i>${factorVo.varName}：</p>
					       	<c:choose>
				        	<c:when test="${factorVo.varType=='INTERVAL'}">
				        		<div class="factor-value-p"><p><input type="text" readonly name="factorValueStart" value="${factorVo.factorValueStart}" class="int-mini"></p><p>~</p><p><input type="text" name="factorValueEnd" readonly value="${factorVo.factorValueEnd}" class="int-mini"></p></div>
				        	</c:when>
				        	<c:otherwise>
				        		<div class="factor-value-p"><input type="text" readonly name="factorValue" value="${factorVo.factorValue}" class="int-mini"></div>
				        	</c:otherwise>
				        	</c:choose>
					        <p class="tab-unit-name">${factorVo.varUnitName}</p>
				        </div>
			        </c:forEach>
		        </div>
		        <p><i>*</i>价格为</p>
		        <p><input type="text" name="price" value="${varVo.price}" readonly class="int-mini "></p>
		        <p><select name="priceType" defaultPriceType="${varVo.priceType}" disabled class="int-mini price-type"></select></p>
	        	<p>说明</p>
	        	<p><input type="text" name="comments" value="${varVo.comments}" class="int-mini"></p>
		       </li>
	       </c:forEach>
	       </c:if>
       </div>
      
       <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button class="btn-query" onclick="javascript:window.history.go(-1);">返 回</button></p>
       </li>
       
      </ul>
     </div>
     </div>

	<div id="varTemp" class="varTemp" style="display:none;">
		<li>
	        <p class="tab-form-name"><i>*</i></p>
	        <p><input type="text" name="varName" class="int-medium"></p>
	        <p><input type="text" name="varCode" class="int-medium" placeholder="变量编码"></p>
	        <p><select class="int-mini"></select></p>
	        <p><a href="#" class="delVar"><img src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
	    </li>
    </div>
    <div id="detailTemp" class="detailTemp" style="display:none;">
    	<li>
	    	<div class="factors">
	    		<c:forEach items="${strategyVO.variableRecordVos}" var="varVo" varStatus="status">
			    	<div class="factor">
			    		<input type="hidden" name="fvarName" value="${varVo.varName}">
			    		<input type="hidden" name="fvarCode" value="${varVo.varCode}">
			    		<input type="hidden" name="fvarUnitId" value="${varVo.unitId}">
			    		<input type="hidden" name="fvarUnitName" value="${varVo.unitName}">
			    		<input type="hidden" name="fvarUnitCode" value="${varVo.unitCode}">
				       	<p class="<c:if test="${status.index==0}">tab-form-name </c:if> tab-var-name"><i>*</i>${varVo.varName}：</p>
				        <p><input type="text" name="factorValue" class="int-mini"></p>
				        <p class="tab-unit-name">${varVo.unitName}</p>
			        </div>
		        </c:forEach>
	        </div>
	        <p><i>*</i>价格为</p>
	        <p><input type="text" name="price" class="int-mini "></p>
	        <p><select name="priceType" defaultPriceType="${varVo.priceType}" disabled class="int-mini price-type"></select></p>
	        	<p>说明</p>
	        	<p><input type="text" name="comments" value="${varVo.comments}" class="int-mini"></p>
        <p><a href="#" class="delDetail"><img src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
       </li>
	</div>
	<div class="factor factorTemp" style="display:none;">
		<input type="hidden" name="fvarName">
   		<input type="hidden" name="fvarCode">
   		<input type="hidden" name="fvarUnitId">
   		<input type="hidden" name="fvarUnitName">
   		<input type="hidden" name="fvarUnitCode">
       	<p class="tab-var-name"><i>*</i>无：</p>
        <p><input type="text" name="factorValue" class="int-mini"></p>
        <p class="tab-unit-name">无</p>
    </div>

<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/strategy/strategyShow', function (StrategyShowPager) {
                var pager = new StrategyShowPager({element: document.body});
                pager.render();
            });
        })
    </script>
</body>
</html>