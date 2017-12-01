<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>查看账单明细</title>
    <%@ include file="/inc/inc.jsp"%>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

  <div class="wrapper">
   <div class="management"><!--资费管理外侧-->
      <div class="nav-tplist-title peiz-title">
            <div class="title-left">查看账单明细</div>
            </div>
   <div class="management-cnt">
    <div class="nav-form">
          <div id="showData"></div>
          <script id="billDetailListTmpl" type="text/x-jsrender">      
                <ul>
                 <li>
                 <p class="word">客户名称：</p>
                 <p class="ctn-a">{{:custName}}</p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">客户等级：</p>
                 <p class="ctn-a">{{:custGrade}}</p>
                 </li>
                 </ul>
                  <ul>
                 <li>
                 <p class="word">账单生成月份：</p>
                 <p class="ctn-a" >{{:queryTime}}</p>
                 </li>
                 </ul>
                  <ul>
                 <li>
                 <p class="word">账单科目明细：</p>
                 <div style=" width:500px; float:left;">
                 <div class="nav-tplist-table table-height" >
                     <table width="100%" border="0">
                      <tr class="tr-backgrond">                                                                                                            
                        <td width="30%" class="left-border">服务号码</td>
                        <td width="35%">账单科目名称</td>
                        <td width="35%">实际应缴金额（元）</td>
                      </tr>
                      {{if serviceList != null && serviceList.length>0}}
						{{for serviceList}}
	                      <tr>
	                        <td>{{:serviceId}}</td>
							<td  colspan=2 width="70%">
								{{for subjectDetailList}}
									<table  width="100%" class="inner-table">
	                        			<td width="50%">{{:subjectName}}</td>
	                        			<td width="50%" class="right-border">{{:~liToYuan(subjectFee)}}</td>
									</table>
	                        	{{/for}}
							</td>
	                      </tr>
						{{/for}}
                      {{/if}}
                     </table>
           </div>
           </div>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">账单合计金额：</p>
                 <p class="ctn-a">{{:~liToYuan(orgFee)}}  元</p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">实际应缴金额：</p>
                 <p class="ctn-a">{{:~liToYuan(totalFee)}}  元</p>
                 </li>
                 </ul>
                </div>
           </script>     
   		 <div class="configure-btn-ctn pad130">
           
           <div class="configure-btn-ctn-left configure-margin">
           <ul>
           <li><input type="button" class="peiz-btn-cancel" value="返   回" onclick="window.history.back(-1)"/></li>
           <li><input id="exportBtn" type="button" class="bass-btn peiz-btn" value="导  出"/></li>
           </ul>
          </div>
         </div>         
   </div>
   </div>
   </div>
  <!--底部-->
<%@ include file="/inc/foot.jsp"%>
<script type="text/javascript">
		var custId = "${custId}";
		var queryTime = "${queryTime}";
		(function () {
			seajs.use('app/jsp/billsearch/queryBillDetail', function (QueryBillDetailPager) {
				var pager = new QueryBillDetailPager({element: document.body});
				pager.render();
			});
		})();
	</script>
<!--底部结束-->
</body>
</html>
