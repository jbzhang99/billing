<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>标准资费管理-查看标准资费</title>
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
	    <!-- 限制只能输入汉字，字母，数字 -->
	    <input type="text" class="int-xlarge" id="priceName" name="priceName" value="${standardFee.priceName}" disabled
	    onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')">
	    <p>标准业务资费名称</p></div>
	    <div class="standard-in"><p>标准资费周期配置</p>
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
		            <td class="min130"><select class="wh100" name="serviceType" oldValue="${standardFee.serviceType}" disabled><option value="">请选择业务</option></select></td>
		            <td class="min130"><select class="wh100" name="subServiceType" oldValue="${usageList.subServiceType}" disabled><option value="">请选择业务</option></select></td>
		            <td><input class="wh40" type="text" name="amount" value="1" disabled onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"><span></td>
		            <!-- <td class="min160"><select class="select-medium" name="unit"><option>请选择业务</option></select></td> -->
		          </tr>
		          
		         </tbody>
		       	 </table>
	        	<span class="steps"><%-- <img src="${_base}/resources/baasop/images/stepa.png" id="addStandCycel"> --%></span>
	        </div>
	       </c:forEach>
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
	        <div class="standard-in"><select class="select-small" id="unit" name="unit" oldValue="${standardFee.usageList[0].unit}" disabled><option value="">请选择</option><option value="2">2</option></select><p>标准资费单位配置</p></div>
	        <div class="standard-in"><input type="text" class="int-medium" id="price" name="price" disabled value="${standardFee.price}" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')"><span class="yuan">元</span><p>标准资费价格配置</p></div>
        	<div class="standard-in"><textarea type="text" cols="40" rows="4" maxlength="150" style="height:100px;width:500px;" class="int-medium" id="comments" name="comments" disabled>${standardFee.comments}</textarea><p>资费描述</p></div>
        </form>
        <div class="standard-in"><input type="button" value="返回" onclick="window.location.href='${_base}/standardFee/list';" class="next-btn"></div>
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

