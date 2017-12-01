<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/inc/inc.jsp"%>
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>欠费查询</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
 <div class="wrapper">
	<div class="management"><!--资费管理外侧-->
         
          <div class="management-cnt"><!--资费管理内容-->
          <!--查询区域-->
               <div class="nav-form" id="cg-Form">
                 <ul>
                 <li>
                <p class="word">客户名称</p>
                 <p><input type="text" class="int-medium" id="custName" name="custName"> </p>
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
	                 	<p class="word">欠费开始时间</p>
	                 	<p><input type="text" class="int-medium" id="startDate" name="startDate" readonly><A href="javascript:void(0);"><i class="icon-calendar" id="scleraId"></i> </A></p>
	                 </li>
	                 <li>
	                 	<p class="word">欠费结束时间</p>
	                 <p><input type="text" class="int-medium" name="endDate" id="endDate" readonly><A href="javascript:void(0);"><i class="icon-calendar" id="ecleraId"></i> </A></p>
	                 </li>
	                 <li><span id="showDateMsg" style="color:red;"></span></li>
                 </ul>
                 	
                 	<ul>
	                  <li>
	                 <p class="word">欠费金额</p>
	                 <p><input type="text" class="int-mini" id="bottomAmount" name="bottomAmount"> </p>
	                 <p>~</p>
	                 <p><input type="text" class="int-mini" id="topAmount" name="topAmount"> </p>
	                 <span id="showAmonutMsg" style="color:red;"></span>
	                 </li>
	                 <li class="btn-margin"><input type="button" value="搜  索" id="BTN_SEARCH" class="bass-btn nav-form-btn"></li>
	                </ul>
               
                </div><!--查询区域结束-->
         
           <div class="management-cnt—border"><!--虚线--></div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>欠费信息</div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
		     <table width="100%" border="0">
				  <tr class="tr-backgrond">                                                                                                            
				    <td class="left-border">客户名称</td>
				    <td>客户等级</td>
				    <td>欠费开始时间</td>
				    <td>欠费金额（元）</td>
				    <td>操作</td>
				  </tr>
		  		  <tbody id="arrearageData"></tbody>
			</table>
           </div>
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" id="BTN_EXPORT" class="bass-btn peiz-btn" value="导出"></li>
           </ul>
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
  <script type="text/javascript">
			var pager;
			(function () {
				seajs.use('app/jsp/arrearage/queryArrearage', function (QueryArrearagePager) {
					pager = new QueryArrearagePager({element: document.body});
					pager.render();
				});
			})();
		</script>
		<script id="arrearageTemple" type="text/template">
				<tr>
		   				<td class="left-border">{{:custName}}</td>
						<td>{{:custGrade}}</td>
		   				<td>{{:unsettledMonth}}</td>
					    <td>{{:~liToYuan(balance)}}</td>
						<td class="right-border"> 
						<a href="${_base}/arrearage/toDtailArrearage?custId={{:custId}}" >
						<i><img src="${_base}/resources/baasop/images/chak.png"></i>查看明细</a>
						</td>
					</tr>
						   
	    </script>
  <%@ include file="/inc/foot.jsp"%>
</body>
</html>
