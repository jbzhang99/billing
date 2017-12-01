<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>信控规则管理</title>
    <%@ include file="/inc/inc.jsp"%>
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
            <!--<div class="title-left"><i class="icon-th-list"></i>账单信息</div>-->
            <div class="title-right">
            <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
            <p class="plus-word"><a href="${_base}/creditControl/standard/add">新建信控规则</a></p>
            </div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
     <table width="100%" border="0">
  <tr class="tr-backgrond">                                                                                                            
    <td width="8%" class="left-border">信控规则ID</td>
    <td width="15%">信控规则名称</td>
    <td width="15%">信控正常状态值</td>
    <td width="15%">信控催缴状态值</td>
    <td width="15%">信控欠费状态值</td>
    <td width="22%">信控规则描述</td>
    <td width="10%">操作</td>
  </tr>
  <tbody id="standardData"></tbody>
 </table>
 </div>
 <!-- 分页 -->
 <div id="pageview" style="text-align: right"></div>
  
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
    <td class="left-border">{{:ruleId}}</td>
    <td>{{:ruleName}}</td>
    <td>可用余额＞{{:pressPayment}}</td>
    <td>0≤可用余额≤{{:pressPayment}} </td>
    <td>可用余额＜0</td>
    <td>{{:description}}</td>
    <td class="right-border">
		<a href="${_base}/creditControl/standard/edit?ruleId={{:ruleId}}"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
		<a href="javascript:void(0);" onclick="pager._deleteStandard('{{:ruleId}}')"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a>	
	</td>
  </tr>
  </script>
  <script type="text/javascript">
  	var pager;
	(function () {
		seajs.use('app/jsp/creditcontrol/searchStandardList', function (SearchStandardListPager) {
			pager = new SearchStandardListPager({element: document.body});
			pager.render();
		});
	})();
	</script>
</body>
</html>
