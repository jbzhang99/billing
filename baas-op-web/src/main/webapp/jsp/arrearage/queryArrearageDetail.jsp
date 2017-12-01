<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
 <%@ include file="/inc/inc.jsp"%>
 <!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>查看欠费明细</title>
    <script type="text/javascript">
		var pager;
		var custId = "${custId}";
		(function () {
			seajs.use('app/jsp/arrearage/queryDetailArrearge', function (QueryArrearageDetailPager) {
				pager = new QueryArrearageDetailPager({element: document.body});
				pager.render();
				//pager._renderListData(${oweDetailInfo});
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
      <div class="nav-tplist-title peiz-title">
            <div class="title-left">查看欠费明细</div>
            </div>
   <div class="management-cnt">
    <div class="nav-form">
    	<div id="showData"></div>
                 <%-- <ul>
                 <li>
                 <p class="word">客户名称</p>
                 <p><input type="text" class="int-medium" value ="${oweDetailInfo.custName}" readonly></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">客户等级</p>
                 <p><input type="text" class="int-medium" value ="${oweDetailInfo.custGrade}" readonly></p>
                 </li>
                 </ul>
                  <ul>
                 <li>
                 <p class="word">欠费开始时间</p>
                 <p><input type="text" class="int-medium" value ="${oweDetailInfo.unsettledMonth}" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
                 </li>
                 </ul>
                  <ul>
                 <li>
                 <p class="word">欠费明细</p>
                 <div style=" width:500px; float:left;">
                 <div class="nav-tplist-table table-height" >
                     <table width="100%" border="0">
                      <tr class="tr-backgrond"> 
                       <td class="left-border">欠费时间</td>                                                                                                           
                        <td >服务号码</td>
                        <td>账单科目名称</td>
                        <td>欠费金额（元）</td>
                      </tr>
                      <tbody id="oweDetailList"></tbody> --%>
                     <%-- <c:forEach items="${oweDetailInfo.oweDetailShowInfoList}" var="i">
	                   <tr>
	                       <td class="left-border">${i.date}</td>
	                       <td colspan=3 width="75%">
	                        <c:forEach items="${i.oweDetailInfoList}" var="cg">
		                   		<table  width="100%" class="inner-table">
		                   			<tr>
			                   			<td>${cg.serviceNum}</td>
			                   			<td colspan=2 width="70%">
			                   			<c:forEach items="${cg.chargeDetailInfos}" var="cgg">
				                        	<table  width="100%" class="inner-table">
				                        		<tr>
						                        	<td width="50%">${cgg.subjectName}</td>
						                        	<td width="50%" class="right-border"><%=com.ai.baas.op.web.util.AmountUtil.changeL2Y(%>${cgg.subjectName}<%=)%></td>
						                        </tr>
				                        	</table>
			                        	</c:forEach>
			                        	</td>
		                        	</tr>
		                        </table>
	                        </c:forEach>
	                        </td>
	                    </tr>
	                 </c:forEach> --%>  
                     <script id="oweDetailListTmpl" type="text/x-jsrender">
				<ul>
                 <li>
                 <p class="word">客户名称</p>
                 <p><input type="text" class="int-medium" value ="{{:custName}}" readonly></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">客户等级</p>
                 <p><input type="text" class="int-medium" value ="{{:custGrade}}" readonly></p>
                 </li>
                 </ul>
                  <ul>
                 <li>
                 <p class="word">欠费开始时间</p>
                 <p><input type="text" class="int-medium" value ="{{:unsettledMonth}}" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
                 </li>
                 </ul>
                  <ul>
                 <li>
                 <p class="word">欠费明细</p>
                 <div style=" width:500px; float:left;">
                 <div class="nav-tplist-table table-height" >
                     <table width="100%" border="0">
                      <tr class="tr-backgrond"> 
                       <td width="25%" class="left-border">欠费时间</td>                                                                                                           
                        <td  width="25%">服务号码</td>
                        <td width="25%">账单科目名称</td>
                        <td width="25%">欠费金额（元）</td>
                      </tr>
					{{if oweDetailShowInfoList!=null}}
					{{if oweDetailShowInfoList.length >= 1}}
						{{for oweDetailShowInfoList}}
							<tr>
								<td class="left-border">{{:date}}</td>
								<td  colspan=3 width="75%">
									{{for oweDetailInfoList}}
										<table  width="100%" class="inner-table">
											<tr>
			                   					<td>{{:serviceNum}}</td>
			                   					<td colspan=2 width="70%">
												{{for chargeDetailInfos}}
				                        			<table  width="100%" class="inner-table">
				                        			<tr>
						                        		<td width="50%">{{:subjectName}}</td>
						                        		<td width="50%" class="right-border">{{:~liToYuan(balance)}}</td>
						                        	</tr>
				                        			</table>
			                        			{{/for}}
			                        			</td>
		                        			</tr>
										</table>
									{{/for}}
								</td>
							</tr>
						{{/for}}
					{{/if}}
					{{/if}}
			</table>
           </div>
           </div>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">合计欠费金额</p>
                 <p><input type="text" class="int-medium" value="{{:~liToYuan(balance)}}元" readonly></p>
                 </li>
                 </ul>
					 </script>     
                     <%-- </table>
           </div>
           </div>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">合计欠费金额</p>
                 <p><input type="text" class="int-medium" value="${oweDetailInfo.balance}元" readonly></p>
                 </li>
                 </ul> --%>
                </div>
   		 <div class="configure-btn-ctn pad130">
           
           <div class="configure-btn-ctn-left configure-margin">
           <ul>
           <li><input type="button" class="peiz-btn-cancel" id="BTN_BACK" value="返   回"></li>
           <li><input type="button" id="BTN_EXPORT" class="bass-btn peiz-btn" value="导  出"></li>
           </ul>
          </div>
         </div>         
   </div>
   </div>
  <%@ include file="/inc/foot.jsp"%>
</body>
</html>
