<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>优惠券详情</title>
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
    	<ul>
    	  <li>  
    	  <p class="tab-form-name">优惠券类型</p>
    	  <p> <input type="text" class="int-xlarge" id="couponType" maxlength="32" readonly/></p> 
    	  </li>
    	   <li>  
    	  <p class="tab-form-name">适用产品</p>
    	  <p> <input type="text" class="int-xlarge" id="chioceProduct" maxlength="32" readonly/></p> 
    	  </li>
    	   
    	</ul>
         </div>
     </div>
   
    
    
    <div class="management-title step2">请配置优惠券生成规则</div>
    <div class="configure">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">优惠券名称</p>
        <p>
        <input type="text" class="int-xlarge" id="couponName" maxlength="32" readonly/>
       
		
        </p>
       </li>
       <li>
        <p class="tab-form-name">优惠券数量</p>
        <p>
        <input type="text" class="int-xlarge" id="couponAmount" maxlength="32" readonly/>
       
		
        </p>
       </li>
       
        <li>
      
        <p class="tab-form-name">优惠方式</p>
        <p><select class="int-medium" id="useType" ><option value="">请选择</option></select></p>
       </li>
        <li>
        <p class="tab-form-name"></p>
        <p><input type="text" class="int-medium mana" id="reachAmount" readonly/>
        <span class="man" id="man">满</span>
        <select id="reach_unit" class="select-mini"></select>
      <select id="model_unit" class="select-mini"></select>
       <input type="text" id="dst_value" class="int-medium" readonly/><select id="dst_unit" class="select-mini"></select>
        
        </p>
       </li>
        <li>
       <p class="tab-form-name">最高减免</p>
        <p>
           <input type="text" id="top_money" tip="请配置最高减免金额"  class="int-mini" readonly/>元
        </p>
       
       </li>
       <li>
        <p class="tab-form-name">有效期</p>
        <p><input type="text" id="activeTime" class="int-medium" readonly/></A>
        
        </p>
       <p>至&nbsp;</p>
        <p><input type="text" id="inactiveTime" class="int-medium"  readonly/><A href="javascript:void(0);"></A>
        
        </p>
        
       </li>
       
      </ul>
     </div>
    </div>

    
    <div class="configure-btn">
     <input type="button" value="返回" id="BACK_BTN" class="next-btn BACK_BTN"/>
    </div>
  
   
   </div>
	
</div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/coupon/viewCoupon', function (ViewCouponPager) {
		var pager = new ViewCouponPager({
			element: document.body,
			couponId: "<c:out value="${couponId}"/>",
		});
		pager.render();
	});
})();
</script>

</html>

