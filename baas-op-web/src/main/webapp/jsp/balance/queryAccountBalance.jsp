<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
	  <%@ include file="/inc/inc.jsp"%>
	  <!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>账户余额查询</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
<div class="wrapper">        
	<div class="management"><!--资费管理外侧-->
         
          <div class="management-cnt"><!--资费管理内容-->
          <!--查询区域-->
               <div class="nav-form" id="ba-form">
                 <ul>
                 <input type="hidden" id="custNameQ" name="custNameQ">
                 <input type="hidden" id="custGradeQ" name="custGradeQ">
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
                 <li class="btn-margin"><input type="button" value="搜  索" id="BTN_SEARCH" class="bass-btn nav-form-btn"></li>
                 </ul>
                
                </div><!--查询区域结束-->
           <div class="management-cnt—border"><!--虚线--></div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>账户余额信息</div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
		     <table width="100%" border="0">
			  <tr class="tr-backgrond">                                                                                                            
			    <td class="left-border">客户名称</td>
			    <td>客户等级</td>
			    <td>查询日期</td>
			    <td>可用账户余额（元）</td>
			  </tr>
		  	  <tbody id="balanceData"></tbody>
		    </table>
           </div>
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" class="bass-btn peiz-btn" value="导出"></li>
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
  <%@ include file="/inc/foot.jsp"%>
	<script type="text/javascript">
			var pager;
			(function () {
				seajs.use('app/jsp/balance/queryBalance', function (QueryBalancePager) {
					pager = new QueryBalancePager({element: document.body});
					pager.render();
				});
			})();
		</script>
		<script id="balanceListTemple" type="text/template">
				<tr>
		   				<td class="left-border">{{:custName}}</td>
						<td>{{:custGrade}}</td>
		   				<td>{{:~timestampToDate('yyyy-MM-dd hh:mm:ss', queryTime)}}</td>
						<td>{{:usableAmount}}</td>
					</tr>
						   
	    </script>
</body>
</html>
