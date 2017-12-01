<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>修改产品目录</title>
    <%@ include file="/inc/inc.jsp"%>
    
</head>
<body>
<div class="main-right" style="float:left;">
	<div class="zuhu-title">
		<ul>
			<li>修改产品目录</li>
		</ul>
	</div>
		<div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>请选择行业类型</p>
        <p><select id="tradeType" class="int-xlarge"><option value="">请选择</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>供应商</p>
        <p><input type="text" disabled name="supplierName" value="${supplierName}" class="int-xlarge"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>主产品名称</p>
        <p><select id="mainPro"  class="int-xlarge"><option value="">请选择</option></select></p>
        <input type="text" id="mainTag" hidden>
       </li>
       <div class="weidu">
      <%--  <div class="weidus">
       <li>
        <p class="tab-form-name">请配置第<nobr style="color:#666;" class="wdId">1</nobr>级定价维度:</p>
       </li>
       <li>
       	<p class="tab-form-name"><i>*</i>定价维度:</p>
        <p><input type="text" class="int-small dim" name="dimensionName" tip="维度名称不能为空"></p>
        <p><i>*</i>维度编码</p>
        <p><input type="text" name="dimensionCode" tip="维度编码不能为空" class="int-small"></p>
         <p><input type="text" name="dimensionSeq"  value="1" class="int-small" style="display:none;"></p>
        <!-- <p><a href="javascript:void(0);"   class="addBr red">添加分支</a></p> -->
        <p><a href="javascript:void(0);" title="添加分支" class="addBr"><img src="${_base}/resources/citicbilling/images/stepa.png" ></a></p>
       </li>
      
       </div> --%>
       </div>
       

      <!--  <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button id="addwd" class="btn-query colo-fff">继续添加</button></p>
       </li> -->
        <li>
       		<p class="tab-form-name">&nbsp;</p>
       		<p><button class="btn-query" id="savePro">确定</button></p>
       		<p><button class="btn-query" id="cancelBtn">取 消</button></p>
       </li>
      </ul>
     </div>
   
     
      <div class="wdTemp weidus" style="display:none;">
       <li>
        <p class="tab-form-name">请配置第<nobr style="color:#666;" class="wdId">i</nobr>级定价维度:</p>
       </li>
       <li>
       	<p class="tab-form-name"><i>*</i>定价维度:</p>
        <p><input type="text" class="int-small " name="dimensionName" tip="维度名称不能为空"></p>
        <p><i>*</i>维度编码</p>
        <p><input type="text" class="int-small " name="dimensionCode" tip="维度编码不能为空"></p>
        <!-- <p><a href="javascript:void(0);"   class="addBr red">添加分支</a></p> -->
         <p><input type="text" name="dimensionSeq" class="int-small" style="display:none;"></p>
          <p><input type="text" name="dimensionId" class="int-small" style="display:none;"></p>
        <p><a href="javascript:void(0);" title="添加分支"  class="addBr"><img  src="${_base}/resources/citicbilling/images/stepa.png" ></a></p>
       </li>
      
       </li>
       
       </div>
       <div class="branchTemp" style="display:none;">
       <li>
       	<p class="tab-form-name-small">&nbsp;</p>
       	<p class="tab-form-name"><i>*</i>分支:</p>
       	 <p><input type="text" class="int-small" name="branchId"  style="display:none;"></p>
        <p><input type="text" class="int-small" name="branchName" tip="分支名称呢不能为空" ></p>
        <p><i>*</i>分支编码</p>
        <p><input type="text" class="int-small" name="branchCode" tip="分支编码不能为空"></p>
        <!-- <p><a href="javascript:void(0);"  class="addBr red">再添加一个</a></p> -->
        <p><a href="javascript:void(0);" title="取消" class="delBranch"><img  src="${_base}/resources/citicbilling/images/stepb.png"></a></p>
       </li>
       </div>
        
</body>
<script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/product/editCustomProduct', function (EditProPager) {
                var pager = new EditProPager({
                	element: document.body,
                	temp:$(".wdTemp"),
                	branch:$(".branchTemp"),
                	mainProId:"<c:out value="${proId}"/>",
                	mainTag:"<c:out value="${mainTag}"/>"
                	
                });
                pager.render();
            });
        })
    </script>
</html>