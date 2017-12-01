<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<style type="text/css">
.nav-form ul li .icon-calendar {
    position: relative;
    right: 22px;
    top: 10px;
    font-size: 20px;
    color: #666666;
}
</style>
<title>优惠券查询</title>
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
		            	<p class="word">优惠券名称</p>
		             	<p><input type="text" class="int-medium" id="couponName"/> </p>
		             </li>
		             <li>
		             	<p class="word">优惠券ID</p>
		             	<p><input type="text" class="int-medium" id="couponId"/> </p>
		             </li>
		           <li>
		             	<p class="word">优惠券状态</p>
		             	<p><select id="STATUS" class="select-medium"> 
		             		<option value="">全部</option>
		             	</select> </p>
		             </li>
	             </ul>
	             <ul>
		           
		             <li>
		             	<p class="word">建券时间</p>
		             	<p><input type="text" class="int-medium" name="startDate" id="activeTime" readonly/><A href="javascript:void(0);"><i class="icon-calendar" ></i> </A></p>
		             	<p >至&nbsp; &nbsp; </p>
		             	<p><input  type="text" class="int-medium"  name="endDate" id="invalidTime" readonly/><A href="javascript:void(0);"><i class="icon-calendar" ></i> </A></p>
		             </li>
		              
		             <li class="btn-margin"><input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH"/></li>
	             </ul>
			</div><!--查询区域结束-->
	          
			<div class="management-cnt—border"><!--虚线--></div>
	           
			<div class="nav-tplist"><!--查询结果-->
	            <div class="nav-tplist-title"><!--查询结果标题-->
		            <div class="title-left"><i class="icon-th-list"></i>优惠券信息</div>
		            <div class="title-right">
		            <p class="plus"><a href="${_base}/coupon/toAdd"><i class="icon-plus"></i></a></p>
		            <p class="plus-word"><a href="${_base}/coupon/toAdd">添加优惠券</a></p>
		            </div>
	            </div><!--查询结果标题结束-->
	
				<!--查询结果列表-->
				<div class="nav-tplist-table">
					<table width="100%" border="0">
					  <tr class="tr-backgrond">                                                                                                            
					     <td>序号</td>                                                                                                                          
					    <td>优惠券ID</td>
					    <td>优惠券名称</td>
					    <td>优惠券类型</td>
					    <td>适用产品</td>
					     <td>优惠方式</td>
					    <td>最高减免金额</td>
					    <td>优惠券数量</td> 
					    <td>建券日期</td>
					    <td>有效时间</td>
					    <td>状态</td>
					    <td class="right-border">操作</td>
					  </tr>
					  <tbody id="listData"></tbody>
					  <!-- 定义JsRender模版 -->
					  <script id="listDataTmpl" type="text/x-jsrender">
					  <tr>
					   
					    <td>{{:index}}</td>
					    <td>   {{:couponId}}            </td>
					    <td>{{:couponName}} </td>
					    <td>
							{{if couponType == 'ALL'}}
								全场通用
							{{else couponType==="APPOINT"}}
								指定产品
							{{/if}}

						</td>
						<td>
							{{if couponType == 'ALL'}}
								所有产品
							{{else}}
								{{:productName}}
							{{/if}}

						</td>
						<td>{{:conditionValue}}</td>
						<td>
							{{if topMoney !=''}}
								{{:topMoney}}元</td>
							{{else}}
								无
							{{/if}}

						<td>{{:couponAmount}}张</td>
                        
                        <td>{{:~timestampToDate('yyyy-MM-dd', createTime)}}</td>
					    <td>{{:~timestampToDate('yyyy-MM-dd', activeTime)}}至{{:~timestampToDate('yyyy-MM-dd', inactiveTime)}}</td>
					  <td>{{:couponStaName}}</td>
					   <td class="right-border">
					    <a href="javascript:void(0);" couponId='{{:couponId}}'  class="DETAIL_BTN"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看</a>
                       
                        <a class="DEL_BTN" couponId='{{:couponId}}'  href="javascript:void(0);"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a>

                        <a href="javascript:void(0);" couponId='{{:couponId}}' class="DOWNLOAD_BTN" ><i><img src="${_base}/resources/baasop/images/guanl.png"></i>导出</a>
                      
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


   
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/coupon/couponList', function (CouponListPager) {
		var pager = new CouponListPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>

