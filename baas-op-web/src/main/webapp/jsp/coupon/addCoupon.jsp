<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>新建优惠券</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	
	<div class="management"><!--资费管理外侧-->
   
    <div class="management-title step1">请选择优惠券类型</div>
   
     <div class="configure">
     
    	<div class="tab-form">
    	<ul id="couType">
    	  <li>  
    	  <p class="tab-form-name">优惠券类型</p>
    	  <p><label class="radioa"><input value="ALL" name="couponType" type="radio"  checked="checked"/>全场通用</label></p> 
    	  </li>
    	   <li>  
    	  <p class="tab-form-name"></p>
    	  <p><label class="radioa"><input value="APPOINT" name="couponType" type="radio"/>指定产品</label>&nbsp;&nbsp;<a id="bindP" href="javascript:void(0);" hidden><img id="showm" src="${_base}/resources/baasop/images/stepa.png" /></a><span id="proName" hidden></span></p> 
    	  <input type="text"   id="prodId" hidden/> 
    	  </li>
    	  
    	</ul>
         </div>
     </div>
   
    
    
    <div class="management-title step2">请配置优惠券生成规则</div>
    <div class="configure" id="rule">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠券名称</p>
        <p>
        <input type="text" class="int-xlarge" id="couponName" tip="请填写优惠券名称"/>
       
		
        </p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>优惠券数量</p>
        <p>
        <input type="text"  class="int-xlarge price-input" id="couponAmount" tip="请填写优惠券数量" maxlength="6"/>
        </p>
       </li>
       
        <li>
      
       <p class="tab-form-name"> <i>*</i>优惠方式</p>
        <p><select class="int-medium" id="useType" tip="请配置使用类型"></select></p>
       </li>
        <li>
        <p class="tab-form-name"></p>
        <p><input type="text" class="int-medium mana price-input1" tip="请配置优惠规则" id="reachAmount" maxlength="15"/>
        <span id="man" class="man">满</span>
        <select id="reach_unit" tip="请配置优惠规则"  class="select-mini  "></select>
        <select id="model_unit" class="select-mini" tip="请配置优惠规则"></select>
         <input type="text" id="dst_value" tip="请配置优惠规则" class="int-medium price-input1" maxlength="15"/><select id="dst_unit" class="select-mini" tip="请配置优惠规则"></select>
        
        </p>
       
       
       </li>
       <li>
       <p class="tab-form-name">最高减免</p>
        <p>
           <input type="text" id="top_money" tip="请配置最高减免金额"  class="int-mini price-input1" maxlength="15"/>元
        </p>
       
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>有效期</p>
        <p><input type="text" id="activeTime" class="int-medium" tip="请填写有效期" readonly/><A href="javascript:void(0);"><span id="activeTime" class="icon-calendar"></span></A>
        
        </p>
       <p>至&nbsp;</p>
        <p><input type="text" id="inactiveTime" class="int-medium" tip="请填写有效期"  readonly/><A href="javascript:void(0);"><span id="inactiveTime" class="icon-calendar" style="position:relative;left:-35px;"></span></A>
        
        </p>
         <p><i>注：最长为3个月</i></p>
       </li>
       
      </ul>
     </div>
    </div>

    
    <div class="configure-btn">
     <input id="BACK_BTN" type="button" value="取消" class="next-btn" /><input id="SAVEP_BTN" type="button" value="保存" class="next-btn next-btn-hover"/>
    </div>
  
   
   </div>
	
</div>
<!--中间部分结束-->
 <!--弹出层-->
	<div id="popModel" style="display:none; right:30%;left:30%; top:30%;width:650px;height:650px;" class="table-pop">
	  <div class="pop-close" id="CLOSE_POP" ><img src="${_base}/resources/baasop/images/close.png"/></div>
	  <div class="pop-title">选择产品目录</div>
	   	<div class="management-cnt"><!--资费管理内容-->
			<!--查询区域-->
			<div class="nav-form">
	             <ul>
		             <li>
		            	<p >产品ID&nbsp;&nbsp;</p>
		             	<p>&nbsp;&nbsp;<input type="text" class="int-medium" id="productId" /> </p>
		             	<p>&nbsp;&nbsp;产品名称&nbsp;&nbsp;</p>
		             	<p><input type="text" class="int-medium" id="productName"/> </p>
		             </li>
		             <li class="btn-margin"><input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH"/></li>
	             </ul>
	             
			</div><!--查询区域结束-->
	          
	           
			<div class="nav-tplist" style="margin-top:0px;"><!--查询结果-->
				<!--查询结果列表-->
				<div class="nav-tplist-table">
					<table width="100%" border="0">
					  <tr class="tr-backgrond">                                                                                                            
					     <td>序号</td>                                                                                                                          
					    <td>产品ID</td>
					    <td>产品名称</td>
					  </tr>
					  <tbody id="listData"></tbody>
					  <!-- 定义JsRender模版 -->
					  <script id="listDataTmpl" type="text/x-jsrender">
					  <tr>
					    <td><input  name="pp_checkbox" type="radio" />{{:index}}</td>
					    <td>{{:paramCode}} </td>
					    <td>{{:paramName}} </td>
					  </tr>
					  </script>
					</table>
	           	</div>
				<!--查询结果列表结束-->
				<!--分页-->
				<div id="pageview">
				
				 <nav style="text-align: right">
				<ul id="pagination-ul">
						</ul>
				</nav></div>
				<!--分页结束-->
			</div>
			 <div class="configure-btn" style="margin-top: 0px;margin-bottom: 10px;">
         <input type="button" id="CLOSE_BTN" value="取消" class="next-btn CLOSE_BTN"/><input type="button" id="BIND_BTN" value="保存" class="next-btn next-btn-hover"/>
         </div>
		</div>
	  
	</div>
	<!--弹出层结束-->          
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/coupon/addCoupon', function (AddCouponPager) {
		var pager = new AddCouponPager({element: document.body});
		pager.render();
	});
})();
</script>

</html>

