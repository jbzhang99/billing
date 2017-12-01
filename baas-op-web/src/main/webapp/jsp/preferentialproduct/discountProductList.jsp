<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>明细级优惠管理</title>
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
	             <ul>
		             <li>
		            	<p class="word">优惠产品ID</p>
		             	<p><input type="text" class="int-medium" id="productId"> </p>
		             </li>
		             <li>
		             	<p class="word">优惠产品名称</p>
		             	<p><input type="text" class="int-medium" id="pName"> </p>
		             </li>
		              <li>
		             	<p class="word">优惠类型</p>
		             	<p><select id="chargeType" class="select-medium"> 
		             		<option value="">请选择</option>
		             	</select> </p>
		             </li>
	             </ul>
	             <ul>
		           
		             <li>
		             	<p class="word">生效日期</p>
		             	<p><input type="text" class="int-medium" id="activeTime" readonly><A href="#"><i class="icon-calendar"></i> </A></p>
		             </li>
		               <li>
                 <p class="word">失效日期</p>
		             	<p><input  type="text" class="int-medium" id="invalidTime" readonly><A href="#"><i class="icon-calendar"></i> </A></p>
                 </li>
		             <li class="btn-margin"><input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH"></li>
	             </ul>
			</div><!--查询区域结束-->
	          
			<div class="management-cnt—border"><!--虚线--></div>
	           
			<div class="nav-tplist"><!--查询结果-->
	            <div class="nav-tplist-title"><!--查询结果标题-->
		            <div class="title-left"><i class="icon-th-list"></i>优惠产品信息</div>
		            <div class="title-right">
		            <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
		            <p class="plus-word"><a href="${_base}/preferentialProduct/toAdd">添加明细级优惠</a></p>
		            </div>
	            </div><!--查询结果标题结束-->
	
				<!--查询结果列表-->
				<div class="nav-tplist-table">
					<table width="100%" border="0">
					  <tr class="tr-backgrond">                                                                                                            
					                                                                                                                              
					    <td>优惠产品ID</td>
					    <td>优惠类型</td>
					    <td>优惠产品（包）名称</td>
					    <td>优惠规则</td>
					    <td>生失效日期</td>
					    <td>状态</td>
					    <td class="right-border">操作</td>
					  </tr>
					  <tbody id="listData"></tbody>
					  <!-- 定义JsRender模版 -->
					  <script id="listDataTmpl" type="text/x-jsrender">
					  <tr>
					   
					    <td>{{:productId}}</td>
					    <td>
							 {{if proferType=='dr_offer'}}
								满赠
                              {{else proferType=='dr_minus'}}
								满减
							{{/if}}
                         </td>
					    <td>{{:productName}} </td>
					    <td>{{:rule}}</td>
					    <td>{{:~timestampToDate('yyyy-MM-dd', activeDate)}}至{{:~timestampToDate('yyyy-MM-dd', invalidDate)}}</td>
					   <td><a proid={{:productId}}  class="CHANGE" href="javascript:void(0);"><i><img src="${_base}/resources/baasop/images/shezhi.png" ></i>
						 {{if status=='INACTIVE'}}
								<font class="force-blue">待生效</font>
                         {{else status=='ACTIVE'}}
								<font class="force">生效</font>
						  {{/if}}

						</a>
						</td>
					    <td class="right-border">
					    <a href="javascript:void(0);" detailId={{:productId}} detailType={{:proferType}}  priceCode={{:priceCode}} class="DETAIL_BTN"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看</a>
                        <a href="javascript:void(0);" class="EDIT_BTN" editId={{:productId}} priceCode={{:priceCode}} editType={{:proferType}}><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
                        <a productId={{:productId}} class="DEL_BTN" href="javascript:void(0);"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a>
                        <a guanId={{:productId}} guanName="{{:productName}}" class="ATT_BTN" guanType={{:proferType}} href="javascript:void(0);"><i><img src="${_base}/resources/baasop/images/guanl.png"></i>关联费用科目</a>
						</td>
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
		</div>
	</div>
</div>
<!--中间部分结束-->

<!--弹出层-->
<%-- <div class="table-pop"  id="popModel" style="display:none;">
  <div class="pop-close"><img src="${_base}/resources/baasop/images/close.png"></div>
  <div class="pop-title">更改资费状态</div>
  <input id="pId" style="display:none;"/>
  <div class="pop-cnt">
   <ul>
    <li>
     <p class="name">手机号码</p>
     <p><span class="pop-text-blue">185 **** 7956</span>系统将验证码默认发送到该手机！</p>
    </li>
    <li>
     <p class="name">资费状态</p>
     <p><span><input type="radio" name="rd" value="INOPERATIVE" class="pop-radio" checked >待生效</span><span><input type="radio" value="ACTIVE" class="pop-radio" name="rd" >生效</span></p>
    </li>
    <li>
     <p class="name">验证码</p>
     <p>
     	<input type="text" id="phoneVerifyCode" class="int-small pop-int-small">
	     	<input class="button" id="PHONE_IDENTIFY" type="button" value="获取验证码" style=" height:30px; margin-left:20px; padding:0 10px;">
			<i id="showSmsMsg" style="font-size:1px;color:red;"></i>
    </p>
    </li>
    <li>
     <p class="name">&nbsp;</p>
     <p><input type="button" class="pop-btn bass-btn" id="CHANGE_BTN" value="确 认"></p>
    </li>
   </ul>
  </div>
</div> --%>
<!--弹出层结束-->
   <!--弹出层-->
	<div id="popModel" style="display:none; right:30%;left:30%; top:30%;" class="table-pop">
	  <div class="pop-close" id="CLOSE_POP" ><img src="${_base}/resources/baasop/images/close.png"></div>
	  <div class="pop-title">更改产品状态</div>
	  <input id="pId" style="display:none;" >
	  <input name="phoneNum" value="${phoneNum }" style="display:none;" >
	  <div class="pop-cnt">
	   <ul>
	    <li>
	     <p class="name">手机号码</p>
	     <p><span class="pop-text-blue">${fn:substring(phoneNum, 0, 3)} **** ${fn:substring(phoneNum, 7, 12)}</span>系统默认将验证码发送到该手机！</p>
	    </li>
	   
	       <li>
     <p class="name">资费状态</p>
     <p><span><input type="radio" name="rd" value="INACTIVE" class="pop-radio" checked >待生效</span><span><input type="radio" value="ACTIVE" class="pop-radio" name="rd" >生效</span></p>
    </li>
	    <li>
	     <p class="name">验证码</p>
	     <p>
	     	<input type="text" id="phoneVerifyCode" class="int-small pop-int-small">
	     	<input class="button" id="PHONE_IDENTIFY" type="button" value="获取验证码" style=" height:30px; margin-left:20px; padding:0 10px;">
			<i id="showSmsMsg" style="font-size:1px;color:red;"></i>
	     </p>
	    </li>
	    <li>
	     <p class="name">&nbsp;</p>
	     <p><input id="CHANGE_BTN" type="button" class="pop-btn bass-btn" value="确 认"></p>
	    </li>
	   </ul>
	  </div>
	</div>
	<!--弹出层结束-->      
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/preferentialproduct/discountProductList', function (ProductPager) {
		var pager = new ProductPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>

