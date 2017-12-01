<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>优惠策略管理</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	<div class="management"><!--资费管理外侧-->
		<div class="management-cnt"><!--资费管理内容-->
 		  <!--查询区域-->
          <div class="nav-form">
          	<form id="queryForm" action="">
            <ul>
             <li>
             	<p class="word">优惠活动ID</p>
				<p><input type="text" id="productId" name="productId" class="int-medium"> </p>
             </li>
             <li>
              <p class="word">优惠活动名称</p>
              <p><input type="text" id="productName" name="productName" class="int-medium"> </p>
             </li>
            </ul>
            <ul>
             <li>
              <p class="word">优惠类型</p>
              <p>
              	<select id="discountType" name="discountType" class="select-medium"> 
               		<option value="">请选择</option>
              	</select>
              </p>
             </li>
             <li>
             </li>
            </ul>
            <ul>
             <li>
              <p class="word">生效日期</p>
              <p><input id="effectDate_be" name="effectDate" class="int-medium" type="text" readonly /><A href="javascript:void(0);" id="effectDate" ><i class="icon-calendar date-nothing"></i> </A></p>
             </li>
             <li>
              <p class="word">失效日期</p>
              <p><input id="expireDate_be" name="expireDate" class="int-medium" type="text" readonly /><A href="javascript:void(0);" id="expireDate" ><i class="icon-calendar date-nothing"></i> </A></p>
             </li>
             <li class="btn-margin"><input type="button" id="BTN_SEARCH" value="搜  索" class="bass-btn nav-form-btn"></li>
            </ul>
            </form>
           </div>
           <!--查询区域结束-->
          
           <div class="management-cnt—border"><!--虚线--></div>
           
           <div class="nav-tplist"><!--查询结果-->
            <div class="nav-tplist-title"><!--查询结果标题-->
	            <div class="title-left"><i class="icon-th-list"></i>优惠活动信息</div>
	            <div class="title-right">
		            <p class="plus"><a href="${_base}/billDiscountMaintain/toAdd"><i class="icon-plus"></i></a></p>
		            <p class="plus-word"><a href="${_base}/billDiscountMaintain/toAdd">新建优惠活动</a></p>
	            </div>
            </div><!--查询结果标题结束-->
            
           <!--查询结果列表-->
           <div class="nav-tplist-table">
			<table width="100%" border="0">
			  <tr class="tr-backgrond">                                                                                                            
			    <td class="left-border">序号</td>                                                                                                                                 
			    <td>优惠活动ID</td>
			    <td>优惠类型</td>
			    <td>优惠活动（包）名称</td>
			    <td>优惠规则</td>
			    <td>生失效时间</td>
			    <td class="right-border">操作</td>
			  </tr>
			  <tbody id="billDiscountData"></tbody>
			  <script id="billDiscountListTemple" type="text/template">
				  <tr>
				    <td class="left-border">{{:#index+1}}</td>
				    <td>{{:discountId}}</td>
				    <td>{{:discountTypeDesc}}</td>
				    <td>{{:discountName}}</td>
				    <td>{{:discountRuleStr}}</td>
				    <td>{{:~timestampToDate('yyyy/MM/dd', effectDate)}}-{{:~timestampToDate('yyyy/MM/dd', expireDate)}}</td>
				    <td class="right-border">
						<a href="${_base}/billDiscountMaintain/toUpdate?productId={{:discountId}}&dt={{:discountType}}&flag=v"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看</a>
						{{if status==1}}
							<a href="javascript:void(0);" status="{{:status}}" class="updFlag"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
						{{else}}
							<a href="${_base}/billDiscountMaintain/toUpdate?productId={{:discountId}}&dt={{:discountType}}&flag=u"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
						{{/if}}
						<a href="javascript:void(0);" productId="{{:discountId}}" status="{{:status}}" class="delFlag"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a>
					</td>
				  </tr>
			  </script>
			</table>
           </div>
		   <!--分页-->
		   <div>
			 <nav style="text-align: right">
				<ul id="pagination-ul">
				</ul>
			 </nav>
		   </div>
		   <!--分页-->
          </div>
		</div>
	</div>
	
	<!--弹出层-->
	<div id="tcc_div" style="display:none; right:320px; top:530px;" class="table-pop">
	  <div class="pop-close" id="CLOSE_POP" ><img src="${_base}/resources/baasop/images/close.png"></div>
	  <div class="pop-title">更改产品状态</div>
	  <input name="productId" style="display:none;" >
	  <input name="phoneNum" value="${phoneNum }" style="display:none;" >
	  <div class="pop-cnt">
	   <ul>
	    <li>
	     <p class="name">手机号码</p>
	     <p><span class="pop-text-blue">${fn:substring(phoneNum, 0, 3)} **** ${fn:substring(phoneNum, 7, 12)}</span>系统默认将验证码发送到该手机！</p>
	    </li>
	    <li>
	     <p class="name">资费状态</p>
	     <p>
	     	<span><input type="radio" name="feeStatus" value="2" checked="checked" class="pop-radio" >待生效</span>
	     	<span><input type="radio" name="feeStatus" value="1" class="pop-radio" >生效</span>
	     </p>
	    </li>
	    <li>
	     <p class="name">验证码</p>
	     <p>
	     	<input type="text" id="phoneVerifyCode" class="int-small pop-int-small">
	     	<input class="button" id="PHONE_IDENTIFY" type="button" value="获取验证码" style=" height:30px; margin-left:20px; padding:0 10px;">
			<i id="showSmsMsg" style="font-size:smaller; color:red;"></i>
	     </p>
	    </li>
	    <li>
	     <p class="name">&nbsp;</p>
	     <p><input id="BTN_UPDATE_STATUS" type="button" class="pop-btn bass-btn" value="确 认"></p>
	    </li>
	   </ul>
	  </div>
	</div>
	<!--弹出层结束-->
</div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
var pager;
(function () {
	seajs.use('app/jsp/billdiscountmaintain/billDiscountList', function (BillDiscountPager) {
		var pager = new BillDiscountPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>
