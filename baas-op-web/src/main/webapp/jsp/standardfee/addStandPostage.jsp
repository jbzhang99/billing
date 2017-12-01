<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>标准资费管理-<c:choose><c:when test="${standardFee!=null}">修改</c:when><c:otherwise>添加</c:otherwise></c:choose>标准资费</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
         
   <div class="management"><!--资费管理外侧-->
    <div class="standard">
    <form id="addForm" >
	    <div class="standard-in">
	    
	    <input type="hidden" id="standardId" name="standardId" value="${standardFee.standardId}"/>
	    <%-- <input type="hidden" id="comments" name="comments" value="${standardFee.comments}"/> --%>
	    <input type="hidden" id="cycleId" name="cycleId" value="${standardFee.cycleId}"/>
	    <input type="hidden" id="priceType" name="priceType" value="${standardFee.priceType}"/>
	    <!-- 
	    <input type="hidden" id="serviceType" name="serviceType" value="${standardFee.serviceType}"/>
	     -->
	    <input type="hidden" id="status" name="status" value="${standardFee.status}"/>
	    <input type="hidden" id="periodsJsonStr" name="periodsJsonStr" value=""/>
	    <!-- 限制只能输入汉字，字母，数字 -->
	    <input type="text" class="int-xlarge" id="priceName" name="priceName" value="${standardFee.priceName}" maxlength="15"
	    onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')">
	    <p><b style="color: #f00">*</b> 标准业务资费名称</p></div>
	    <div class="standard-in"><p><b style="color: #f00">*</b> 标准资费周期配置</p>
	    
	    	<c:forEach items="${standardFee.usageList}" var="usageList">
				<div class="table-cnt" id="standardPeriod1">
			       	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <thead>
			          <tr>
			            <td>业务类型</td>
			            <td>业务类型细分</td>
			            <td>使用量</td>
			            <!-- <td>单位</td> -->
			          </tr>
			         </thead>
			         <tbody>
			          <tr>
			            <td class="min130"><select class="wh100" style="color:#000000" name="serviceType" onchange="pager._getServiceDetail(this)" oldValue="${standardFee.serviceType}"><option value="${usageList.amount}">请选择业务</option></select></td>
			            <td class="min130"><select class="wh100" style="color:#000000" name="subServiceType" oldValue="${usageList.subServiceType}"><option value="">请选择业务</option></select></td>
			            <td><input class="wh40" type="text" style="color:#000000" name="amount" value="${usageList.amount}" readonly><span></td>
			            <!-- <td class="min160"><select class="select-medium" name="unit"><option>请选择业务</option></select></td> -->
			          </tr>
			          
			         </tbody>
			       	 </table>
		        	<span class="steps"><%-- <img src="${_base}/resources/baasop/images/stepa.png" id="addStandCycel"> --%></span>
	       		 </div>
			</c:forEach>
	    	
	    	<c:if test="${standardFee==null}">
	     	<div class="table-cnt" id="standardPeriod1">
		       	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <thead>
		          <tr>
		            <td>业务类型</td>
		            <td>业务类型细分</td>
		            <td>使用量</td>
		            <!-- <td>单位</td> -->
		          </tr>
		         </thead>
		         <tbody>
		          <tr>
		            <td class="min130"><select class="wh100" style="color:#000000" name="serviceType"  onchange="pager._getServiceDetail(this)" oldValue=""><option value="">请选择业务</option></select></td>
		            <td class="min130"><select class="wh100" style="color:#000000" name="subServiceType" oldValue=""><option value="">请选择业务</option></select></td>
		            <td><input class="wh40" type="text" style="color:#000000" name="amount" value="1" readonly><span></td>
		            <!-- <td class="min160"><select class="select-medium" name="unit"><option>请选择业务</option></select></td> -->
		          </tr>
		          
		         </tbody>
		       	 </table>
	        	<span class="steps"><%-- <img src="${_base}/resources/baasop/images/stepa.png" id="addStandCycel"> --%></span>
	        </div>
	        </c:if>
	        <%-- <div class="table-cnt" id="standardPeriod2">
	       <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        <thead>
	          <tr>
	            <td>业务类型</td>
	            <td>业务类型细分</td>
	            <td>使用量</td>
	            <td>单位</td>
	          </tr>
	         </thead>
	         <tbody>
	          <tr>
	            <td class="min130"><select class="wh100" name="serviceType"><option>请选择业务</option></select></td>
	            <td class="min130"><select class="wh100" name="subServiceType"><option>请选择业务</option></select></td>
	            <td><input class="wh40" type="text" name="amount"></td>
	            <td class="min160"><select class="select-medium" name="unit"><option>请选择业务</option></select></td>
	          </tr>
	           
	         </tbody>
	        </table>
	        <span class="stepc"><img src="${_base}/resources/baasop/images/stepb.png"></span>
	        </div> --%>
	
	        </div>
	        <div class="standard-in"><select class="select-small" id="unit" name="unit" oldValue="${standardFee.usageList[0].unit}"><option value="">请选择</option></select><p><b style="color: #f00">*</b> 标准资费单位配置</p></div>
	        <div class="standard-in"><input type="text" class="int-medium" id="price" name="price" value="${standardFee.price}" maxlength="10" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')"><span class="yuan">元</span><p><b style="color: #f00">*</b> 标准资费价格配置</p></div>
        	<div class="standard-in"><textarea type="text" cols="40" rows="4" maxlength="150" style="height:100px;width:500px;" class="int-medium" id="comments" name="comments">${standardFee.comments}</textarea><p>资费描述</p></div>
        </form>
        <div class="standard-in"><input type="button" value="取消" onclick="window.location.href='${_base}/standardFee/list';" class="next-btn"><input type="button" value="保存" id="saveForm" class="next-btn next-btn-hover"></div>
    </div>
   </div>
   </div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
var pager;
(function () {
	seajs.use('app/jsp/standardfee/addStandPostage', function (ConfigLoadPager) {
		pager = new ConfigLoadPager({element: document.body});
		pager.render();
	});
})();
</script>

</html>

