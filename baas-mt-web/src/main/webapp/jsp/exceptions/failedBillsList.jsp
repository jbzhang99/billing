<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>错单管理</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">
				<div class="management">
		           <div class="nav-tplist-title peiz-title">
		            <div class="title-left">错单管理</div>
		            </div>
		
		         
		          <div class="management-cnt">
		          <!--查询区域-->
		               <div class="nav-form">
		               <form id="queryForm" action="">
		                 <ul>
		                  <li>
		                 <p class="word">租户</p>
		                 <p><input type="text" id="tenantId" name="tenantId" class="int-medium"> </p>
		                 </li>
		                  <li>
		                 <p class="word">业务类型</p>
		                 <p><select class="select-medium" id="serviceType" name="serviceType"><option value="">请选择</option></select> </p>
		                 </li>
		                  <li>
		                 <p class="word">错单编码</p>
		                 <p><select class="select-medium" id="errorCode" name="serviceType"><option value="">请选择</option></select> </p>
		                <!--  <p><input type="text" class="int-medium" id="errorCode" name="errorCode"> </p> -->
		                 </li>
		             <li>
		             	<p class="word">生成时间</p>
		             	<p><input type="text" class="int-medium" id="startTime" readonly><A  href="javascript:void(0);"><i id="startTimeIcon" class="icon-calendar"></i> </A></p>
		             </li>
		           <li>至</li> 
		               <li>
		             	<p><input  type="text" class="int-medium" id="endTime" readonly><A  href="javascript:void(0);"><i id="endTimeIcon" class="icon-calendar"></i> </A></p>
                      </li>
		                 <li class="btn-margin"><p><input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH"></p></li>
		                 </ul>
		            	</form>
		                </div><!--查询区域结束-->
		          
		           <div class="management-cnt—border"><!--虚线--></div>
		           
		           
		           <div class="nav-tplist"><!--查询结果-->
		           
		            <div class="nav-tplist-title"><!--查询结果标题-->
		            <div class="title-left"><i class="icon-th-list"></i>错单查询结果</div>
		            
		            </div><!--查询结果标题结束-->
		            
		           <div class="nav-tplist-table"><!--查询结果列表-->
		    
		     <table width="100%" border="0">
		  <tr class="tr-backgrond">                                                                                                            
		    <td class="left-border"> <input id="checkAll" type="checkbox"  class="int-checkbox1"> 全选</td>
		    <td>租户</td>
		    <td>业务类型</td>   
		    <td>错单编码</td>        
		    <!--                                                                                                               
		    <td>错单字段下标</td>
		     -->
		    <td>错单产生时间</td>
		    <td>错单处理断点</td>
		    <td class="right-border">操作</td>
		  </tr>                                                                                                                                                                      
		
		  <tbody id="failedBillsData"></tbody>
		  
		  <!-- 
		   <tr>
		    <td class="left-border"><input type="checkbox"  class="int-checkbox1"></td>
		    <td>亚信在线</td>
		    <td>语音</td>
		    <td>1435243626</td>
		    <td>1435243626</td>
		    <td>112，778，4444，6677，432</td>
		    <td>查重BOLT</td>
		    <td class="right-border"><a href="#"><i class="icon-pencil"></i>编辑错单</a><a href="#"><i class="icon-refresh"></i>刷新</a></td>
		  </tr>
		   -->
		   
		
		 </table>
		 </div>
		           
		           <div class="configure-btn-ctn">
		           
		           <div class="configure-btn-ctn-left">
		           <ul>
		           <!-- 
		           <li><input type="button" class="bass-btn peiz-btn" value="全  选"></li>
		            -->
		           <li><input type="button" class="bass-btn peiz-btn resend-bill" value="重处理"></li>
		           </ul>
		           
		           
		           </div>
		           
               <!--分页-->
               <!-- 
				 <div>
					<nav style="text-align: right">
						<ul id="pagination-ul">
						</ul>
					</nav>
				 </div>
				  -->
				 <!--分页-->
		           
		           
		          </div>
		         </div>
		         
		         </div>
		   
		  </div>
	</div>
	
	<!--弹出层-->
	<div class="table-pop" style="display: none;right: 300px;top:80px;width:700px;height:520px;position: fixed;">
		  <div class="pop-close" onclick="$(this).parent('.table-pop').hide(200);$('#selectedBill').removeAttr('id');"><img src="${_base}/resources/baasmt/images/close.png"></div>
		  <div class="pop-title">编辑错单信息</div>
		  <div class="nav-tplist-table nav-tplist-table-none-border" style="height:370px;"><!--查询结果列表-->
		    <table width="100%" border="0" id="billDetail">
		    <!-- 
		     <tr>
		    <td class="left-border" width="40%">key1</td>
		    <td><input type="text" class="int-medium-float"> </td>
		    </tr>
		     -->
		    
		    </table>
		  
		  </div>
		  <div class="configure-btn-ctn">
		           
		           <div class="configure-btn-ctn-left">
		           <ul>
		           <li><input type="button" onclick="$(this).parents('.table-pop').hide(200);$('#selectedBill').removeAttr('id');" class="peiz-btn-cancel" value="取  消"></li>
		           <li><input type="button" class="bass-btn peiz-btn" id="resendEditBill" value="重处理"></li>
		           </ul>
		           
		           
		           </div>
		           <!-- 
		               <div class="paging-large paging-large-margin">
		 				 <ul>
		                <li> <a href="#">&lt;</a> </li>
		    		     <li class="active"> <a href="#">1 </a> </li>
		    			  <li> <a href="#">2 </a> </li>
		   				  <li> <a href="#">3 </a> </li>
		    			  <li> <a href="#">4 </a> </li>
		                <li> <span>…… </span> </li>
		                <li> <a href="#">20 </a> </li>
		   				  <li> <a href="#">&gt;</a> </li>
		 				  </ul>
					  </div>
		            -->
		            <div>
						<nav style="text-align: right">
							<ul id="pagination-ul">
							</ul>
						</nav>
					</div>
		           
		          </div>
		 </div>
 		<!--弹出层结束-->
	
	<!--中间部分结束-->
	<!--底部-->
	<%@ include file="/inc/foot.jsp"%>
	<!--底部结束-->
	
	
	<script type="text/javascript">
		var pager;
		(function () {
			seajs.use('app/jsp/exceptions/failedBills', function (FailedBillsPager) {
				pager = new FailedBillsPager({element: document.body});
				pager.render();
			});
		})();
	</script>
	
	<script id="failedListsTemple" type="text/template">
		 <tr name="failedList">
		    <td class="left-border"><input type="checkbox" name="bsn" value="{{:bsn}}" tabid="{{:bsn}}" tabname="{{:bsn}}" class="int-checkbox"></td>
			<input type="hidden" name="bsn" value="{{:bsn}}">
			<input type="hidden" name="source" value="{{:source}}">
			<input type="hidden" name="sn" value="{{:sn}}">
			<input type="hidden" name="failDate" value="{{:failDate}}">
			<input type="hidden" name="tenantId" value="{{:tenantId}}">
			<input type="hidden" name="serviceId" value="{{:serviceId}}">
			<input type="hidden" name="failedCode" value="{{:failCode}}">
			<input type="hidden" name="failStep" value="{{:failStep}}">
			<input type="hidden" name="accountPeriod" value="{{:accountPeriod}}">
			<input type="hidden" name="arrivalTime" value="{{:arrivalTime}}">

			
		    <td>{{:tenantId}}</td>
		    <td>{{:serviceTypeName}}</td>
		    <td>{{:failCode}}</td>
			<!-- <td>{{:sn}}</td>  -->
		    <td>{{:~timestampToDate('yyyy.MM.dd hh:mm:ss', failBillDate)}}</td>
		    <td>{{:failStep}}</td>
		    <td class="right-border"><a class="bill-edit" href="javascript:void(0);"><i class="icon-pencil"></i>编辑错单</a><a class="bill-refresh" href="javascript:void(0);"><i class="icon-refresh"></i>刷新</a></td>
		  </tr>
	    </script>
</body>
</html>

