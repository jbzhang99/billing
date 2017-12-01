<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/inc/inc.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>结算处理结果查询</title>
       <script type="text/javascript">
			var pager;
			(function () {
				seajs.use('app/jsp/account/queryResult', function (QueryResultPager) {
					pager = new QueryResultPager({element: document.body});
					pager.render();
				});
			})();
		</script>
</head>
<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
  <div class="wrapper">
         
<div class="management"><!--资费管理外侧-->
         
          <div class="management-cnt"><!--资费管理内容-->

           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
             <table width="100%" border="0">
		          <tr class="tr-backgrond">                                                                                                            
		            <td width="5%" class="left-border">序号</td>                                                                                                                                 
		            <td width="15%">处理结果文件名</td>
		            <td width="15%">上传文件名</td>
		            <td width="10%">上传文件类型</td>
		            <td width="10%">上传文件流水类型</td>
		            <td width="7%">上传文件账期</td>
		            <td width="10%">上传时间</td>
		            <td width="10%">处理完成时间</td>
		            <td width="10%">处理状态</td>
		            <td width="8%" class="right-border">操作</td>
		          </tr>
		         <tbody id="accountData"></tbody>
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
		<script id="accountListTemple" type="text/template">
				<tr>
					    <td class="left-border">{{:index}}</td>
		   				<td >{{:rstFileName}}</td>
						<td>{{:impFileName}}</td>
		   				<td>{{:dataTypeName}}</td>
						<td>{{:objectIdName}}</td>
						<td>{{:billTimeMsg}}</td>
						<td>{{:~timestampToDate('yyyy.MM.dd hh:mm:ss', importTime)}}</td>
						<td>{{:~timestampToDate('yyyy.MM.dd hh:mm:ss', stateChgTime)}}</td>
						<td>{{:stateName}}</td>
						<td class="right-border">
							{{if state=='4'}}
								<a href="${_base}/account/download?rstFileName={{:rstFileName}}&rstFileUrl={{:rstFileUrl}}"><i><img src="${_base}/resources/baasop/images/guanl.png"></i>下载文件</a>
							{{else}}
								<a class="disable"><i><img src="${_base}/resources/baasop/images/guanl_disable.png"></i>下载文件</a>
							{{/if}}						
						</td>
					</tr>
						   
	    </script>
  </div>
  <%@ include file="/inc/foot.jsp"%>
</body>
</html>
