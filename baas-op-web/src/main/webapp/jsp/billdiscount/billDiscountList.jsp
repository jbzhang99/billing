<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>账单级优惠管理</title>
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
             	<p class="word">优惠产品ID</p>
				<p><input type="text" id="productId" name="productId" class="int-medium"> </p>
             </li>
             <li>
              <p class="word">优惠产品名称</p>
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
	            <div class="title-left"><i class="icon-th-list"></i>账单级优惠信息</div>
	            <div class="title-right">
		            <p class="plus"><a href="${_base}/billDiscount/toAdd"><i class="icon-plus"></i></a></p>
		            <p class="plus-word"><a href="${_base}/billDiscount/toAdd">添加账单级优惠</a></p>
	            </div>
            </div><!--查询结果标题结束-->
            
           <!--查询结果列表-->
           <div class="nav-tplist-table">
			<table width="100%" border="0">
			  <tr class="tr-backgrond">                                                                                                            
			    <td class="left-border">序号</td>                                                                                                                                 
			    <td>优惠产品ID</td>
			    <td>优惠类型</td>
			    <td>优惠产品（包）名称</td>
			    <!--<td>优惠规则</td>-->
			    <td>生失效时间</td>
			    <td>状态</td>
			    <td class="right-border">操作</td>
			  </tr>
			  <tbody id="billDiscountData"></tbody>
			  <script id="billDiscountListTemple" type="text/template">
				  <tr>
				    <td class="left-border">{{:#index+1}}</td>
				    <td>{{:productId}}</td>
				    <td>{{:discountTypeDesc}}</td>
				    <td>{{:productName}}</td>
				    <!--<td>{{:priority}}</td>-->
				    <td>{{:~timestampToDate('yyyy/MM/dd', effectDate)}}-{{:~timestampToDate('yyyy/MM/dd', expireDate)}}</td>
				   	<td class="pop-re"><a productId="{{:productId}}" status="{{:status}}" href="javascript:void(0);" class="force"><i><img src="${_base}/resources/baasop/images/shezhi.png"></i>{{if status==1}}<font style="color:red">生效</font>{{else}}<font style="color:#00f">待生效</font>{{/if}}</a></td>
				    <td class="right-border">
						<a href="${_base}/billDiscount/toUpdate?productId={{:productId}}&dt={{:discountType}}&flag=v"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看</a>
						{{if status==1}}
							<a href="javascript:void(0);" status="{{:status}}" class="updFlag"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
						{{else}}
							<a href="${_base}/billDiscount/toUpdate?productId={{:productId}}&dt={{:discountType}}&flag=u"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
						{{/if}}
						<a href="javascript:void(0);" productId="{{:productId}}" status="{{:status}}" class="delFlag"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a>
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
	seajs.use('app/jsp/billdiscount/billDiscountList', function (BillDiscountPager) {
		var pager = new BillDiscountPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>
