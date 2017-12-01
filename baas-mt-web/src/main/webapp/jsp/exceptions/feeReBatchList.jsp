<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>异常管理-费用重批</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">
				<div class="management"><!--资费管理外侧-->
           <div class="nav-tplist-title peiz-title">
            <div class="title-left">费用重批</div>
            </div>

         
          <div class="management-cnt">
          <!--查询区域-->
               <div class="nav-form">
               <form id="queryForm" action="">
	                 <ul>
	                 <li>
	                 <p class="word"><i style="color: #f00">* </i>回退类型</p>
	                 <p><select class="select-medium" id="fallBackType" name="fallBackType"><option value="">请选择</option></select></p>
	                 </li>
	                 <li>
		                 <p class="word"><i style="color: #f00">* </i>租户</p>
		                 <p><input type="text" id="tenantId" name="tenantId" class="int-medium"> </p>
	                 </li>
	                 <li>
	                 <p class="word"><i style="color: #f00">* </i>账期</p>
	                 <p id="queryTimeEvent"><input type="text" class="int-medium" id="accountPeriod" name="accountPeriod" readonly/><a href="javascript:void(0);"><i class="icon-calendar"></i> </a></p>
	                 </li>
	                 <li>
	                 <p class="word"><i style="color: #f00">* </i>业务类型</p>
	                 <p><select class="select-medium" id="serviceType" name="serviceType"><option value="">请选择</option></select> </p>
	                 </li>
	                 <li>
	                 <p class="word">服务号</p>
	                 <p><input type="text" id="serviceId" name="serviceId" class="int-medium"> </p>
	                 </li>
	                 <li class="btn-margin"><p><input type="button" value="搜  索"  class="bass-btn nav-form-btn" id="BTN_SEARCH"></p></li>
	                 </ul>
            	</form>
            	<form id="queryparam" style="display:none">
            		<input type="hidden" id="fallBackTypeQ" name="fallBackType"/>
            		<input type="hidden" id="tenantIdQ" name="tenantId"/>
            		<input type="hidden" id="accountPeriodQ" name="accountPeriod"/>
            		<input type="hidden" id="serviceTypeQ" name="serviceType"/>
            		<input type="hidden" id="serviceIdQ" name="serviceId"/>
            	</form>
                </div><!--查询区域结束-->
          
           <div class="management-cnt—border"><!--虚线--></div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>重批记录查询结果</div>
            
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
		     <table width="100%" border="0">
				  <tr class="tr-backgrond" id="flowTab" style="display:none">                                                                                                            
				    <td class="left-border">客户号</td> 
				    <td>用户号</td>
				    <td>上行流量（单位：K）</td>   
				    <td>下行流量（单位：K）</td>                                                                                                                      
				    <td>账期</td>
				    <td>总流量（单位：K）</td>
				    <td class="right-border">费用（单位：分）</td>
				  </tr>
				  
				  <tr class="tr-backgrond" id="voiceTab">                                                                                                            
				    <td class="left-border">客户号</td>
				    <td>用户号</td>
				    <td>主叫号码</td>   
				    <td>被叫号码</td>                                                                                                                      
				    <td>账期</td>
				    <td>起始时间</td>
				    <td>通话时长（单位：秒）</td>
				    <td class="right-border">费用（单位：分）</td>
				  </tr>                                                                                                                                                                      
				
				 <tbody id="feeReBatchData"></tbody>
				
		 	</table>
		 </div>
           <div class="configure-btn-ctn">
           <div class="configure-btn-ctn-left">
           <ul>
          
           <li><input type="button" class="bass-btn peiz-btn" id="reBatchFee" value="批量重批"></li>
            
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
	
	
	<!--中间部分结束-->
	<!--底部-->
	<%@ include file="/inc/foot.jsp"%>
	<!--底部结束-->
	
	
	<script type="text/javascript">
		var pager;
		(function () {
			seajs.use('app/jsp/exceptions/feeReBatch', function (FeeRebatchPager) {
				pager = new FeeRebatchPager({element: document.body});
				pager.render();
			});
		})();
	</script>
	
	<script id="flowListsTemple" type="text/template">
		 		<tr>
				    <td class="left-border">{{:custNo}}</td>                                                                                  
				    <td>{{:userNo}}</td>
				    <td>{{:feePacket.gprs_up}}</td>
				    <td>{{:feePacket.gprs_down}}</td>
				    <td>{{:accountPeriod}}</td>
				    <td>{{:feePacket.total_flow}}</td>
				    <td class="right-border pop-re">{{:feePacket.fee}}</td>
				  </tr>
	    </script>
	<script id="voiceListsTemple" type="text/template">
				<tr>
    				<td class="left-border">{{:custNo}}</td>
    				<td>{{:userNo}}</td>
    				<td>{{:feePacket.call_number}}</td>
    				<td>{{:feePacket.called_number}}</td>
    				<td>{{:accountPeriod}}</td>
    				<td>{{:feePacket.start_time}}</td>
    				<td>{{:feePacket.duration}}</td>
   	 				<td class="right-border pop-re">{{:feePacket.fee}}</td>
  				</tr>
	    </script>
</body>
</html>

