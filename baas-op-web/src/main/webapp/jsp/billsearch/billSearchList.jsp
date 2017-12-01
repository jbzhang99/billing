<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>账单查询</title>
    <%@ include file="/inc/inc.jsp"%>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
  <div class="wrapper">
         
<div class="management"><!--资费管理外侧-->
         
          <div class="management-cnt"><!--资费管理内容-->
          <!--查询区域-->
               <div class="nav-form">
                 <ul>
                 <li>
                <p class="word">客户名称</p>
                 <p><input id="custNameQ" type="text" class="int-medium"/> </p>
                 </li>
                 <li>
                 <p class="word">客户等级</p>
                 <p><select id="custGradeQ" class="select-medium"> 
                 	<option value="">请选择</option> 
                 </select> </p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word"><span class="f00">*</span>账单生成月份</p>
                 <p id="queryTimeEvent"><input type="text" class="int-medium" id="queryTimeQ" readonly/><a href="javascript:void(0);"><i class="icon-calendar"></i> </a></p>
                 </li>
                 <li class="btn-margin">
                 	<p></p>
                 	<p>
                 		<input id="searchBtn" type="button" value="搜  索" class="bass-btn nav-form-btn"/>
                 		<span class="regsiter-note" id="queryMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png"/>
					     <span id="queryMsg"></span>
				  		</span>
                	</p>
                </li>
                </ul>
            
                </div><!--查询区域结束-->
          
           <div class="management-cnt—border"><!--虚线--></div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>账单信息</div>
             <!--<div class="title-right">
            <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
            <p class="plus-word"><a href="#">添加优惠产品</a></p>
            </div>-->
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
     <table width="100%" border="0">
  <tr class="tr-backgrond">                                                                                                            
    <!--<td width="5%" class="left-border">选择</td>  -->                                                                                                                               
    <td width="20%" class="left-border">客户名称</td>
    <td width="10%">客户等级</td>
    <td width="15%">账单生成月份</td>
    <td width="15%">账单合计金额（元）</td>
    <td width="15%">账单优惠金额（元）</td>
    <td width="15%">实际应缴金额（元）</td>
    <td width="10%" class="right-border">操作</td>
  </tr>
  <tbody id="billListData"></tbody>
</table>
           </div>
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input id="exportBtn" type="button" class="bass-btn peiz-btn" value="导出"/></li>
           </ul>
           </div>
           
           <div id="pagination" style="text-align: right">
		  </div>
  
          </div>
 
         </div>

  </div>
  </div>
 <!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
<!-- 定义JsRender模版 -->
  <script id="standardDataTmpl" type="text/x-jsrender">
  <tr>
    <!--<td class="left-border"><input type="checkbox" class="int-checkbox1 b"></td>-->
    <td id="custName{{:#index}}" class="left-border">{{:custName}}</td>
    <td id="custGrade{{:#index}}">{{:custGrade}}</td>
    <td id="billDuration{{:#index}}">{{:billDuration}}</td>
    <td id="orgFee{{:#index}}">{{:~liToYuan(orgFee)}}</td>
    <td id="disFee{{:#index}}">{{:~liToYuan(disFee)}}</td>
    <td id="totalFee{{:#index}}">{{:~liToYuan(totalFee)}}</td>
    <td class="right-border">
		<a href="javascript:void(0);" onclick="pager._searchBillDetil('{{:billDuration}}','{{:custId}}')"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看明细</a>
	</td>
  </tr>
  </script>
  <script type="text/javascript">
  	var pager;
  	var excelMaxRow = ${excelMaxRow};
  	var billCount;
	(function () {
		seajs.use('app/jsp/billsearch/searchBillList', function (SearchBillListPager) {
			pager = new SearchBillListPager({element: document.body});
			pager.render();
		});
	})();
	</script>
</body>
</html>
