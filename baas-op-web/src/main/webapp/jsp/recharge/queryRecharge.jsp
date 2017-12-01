<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/inc/inc.jsp"%>
  <!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>充值缴费查询</title>
    
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
 	 <input type="hidden" id="totalcount" name="totalcount" />
	 <input type="hidden" id="maxRow" name="maxRow" value="${requestScope.maxRow}"/>
<!--头部和菜单结束-->
<div class="wrapper">
<div class="management"><!--资费管理外侧-->
         
          <div class="management-cnt"><!--资费管理内容-->
          <!--查询区域-->
               <div class="nav-form" id="cg-Form">
                 <ul>
	                 <li>
	                	<p class="word">客户名称</p>
	                 	<p><input type="text" class="int-medium" id="custName" name="custName"> 
	                 	</p>
	                 </li>
	                 <li>
		                 <p class="word">客户等级</p>
		                 <p>
			                 <select class="select-medium" id="custGrade" name="custGrade"> 
			                 	<option value="">请选择</option>
			                 </select> 
		                 </p>
	                 </li>
                 </ul>
                 <ul>
	                <li>
	                 	<p class="word">缴费充值流水号</p>
	                 	<p><input type="text" class="int-medium" id="paySerialCode" name="paySerialCode"> </p>
	                 </li>
	                 <li>
	                 	<p class="word">缴费充值开始日期</p>
	                 	<p><input type="text" class="int-medium" name="startTime" id="beginTime" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
	                 </li>
	                 <li>
	                 	<p class="word">缴费充值结束日期</p>
	                 	<p><input type="text" class="int-medium" name="endTime" id="endTime" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
	                 </li>
	                 <p><span id="showDateMsg" style="color:red;"></span></p>
	                <li>
		                 <p class="word">充值金额</p>
		                 <p><input type="text" class="int-mini" name="bottomAmount" id="bottomAmount"> </p>
		                 <p>~</p>
		                 <p><input type="text" class="int-mini" name="topAmount" id="topAmount"> </p>
	                 	<span id="showAmonutMsg" style="color:red;"></span>
	                 </li>
	                 <li class="btn-margin">
	                 	<input type="button" value="搜  索" id="charge-search"class="bass-btn nav-form-btn">
	                 </li>
                 </ul>
            
                </div><!--查询区域结束-->
           <div class="management-cnt—border"><!--虚线-->
           </div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>充值缴费信息</div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
     <table width="100%" border="0">
	  <tr class="tr-backgrond">                                                                                                            
	    <td class="left-border">缴费充值流水号</td>
	    <td>客户名称</td>
	    <td>客户等级</td>
	    <td>缴费充值日期</td>
	    <td>缴费充值金额（元）</td>
	  </tr>
	   <tbody id="chargeData"></tbody>
</table>
           </div>
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" class="bass-btn peiz-btn"  id="export" value="导出"></li>
           </ul>
           </div>
             <!--分页-->
					 <div>
		 				 <nav style="text-align: right">
							<ul id="pagination">
							</ul>
						</nav>
					  </div>
					 <!--分页-->
          </div>
 
         </div>

  </div>
  <script type="text/javascript">
			var pager;
			(function () {
				seajs.use('app/jsp/recharge/queryRecharge', function (QueryRechargePager) {
					pager = new QueryRechargePager({element: document.body});
					pager.render();
				});
			})();
</script>
		
		<script id="chargeListTemple" type="text/template">
				<tr>
		   				<td class="left-border">{{:peerSerialCode}}</td>
		   				<td>{{:custName}}</td>
						<td>{{:custGrade}}</td>
		   				<td>{{:~timestampToDate('yyyy-MM-dd hh:mm:ss', payTime)}}</td>
						<td>{{:~liToYuan(totalAmount)}}</td>
					</tr>
						   
	    </script>
  <%@ include file="/inc/foot.jsp"%>
</body>
</html>
