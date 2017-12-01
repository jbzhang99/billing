<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>账单科目管理</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
<div class="management"><!--资费管理外侧-->
         
          <div class="management-cnt"><!--资费管理内容-->
          <!--查询区域-->
          <div class="nav-form">
                     <ul>
                         <li>
                         <p class="word">账单科目ID</p>
                         <p><input type="text" class="int-medium" id="subjectId" name="subjectId"> </p>
                         </li>
                          <li>
                         <p class="word">账单科目名称</p>
                         <p><input type="text" class="int-medium" id="subjectName" name="subjectName"> </p>
                         </li>
                     <li class="btn-margin"><input type="button" value="搜  索"  id="BTN_SEARCH" class="bass-btn nav-form-btn"></li>
                  </ul>
                  <ul>
                 </ul>
                </div><!--查询区域结束-->
                <div class="management-cnt—border"><!--虚线--></div>
                <div class="nav-tplist"><!--查询结果-->
                	<div class="nav-tplist-title"><!--查询结果标题-->
                		<div class="title-left"><i class="icon-th-list"></i>账单科目管理</div>
                		<div class="title-right">
                			<p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
                			 <p class="plus-word"><a href="${_base}/billSubject/toAdd">添加科目项</a></p>
                		</div>
                	</div>
                	<div class="nav-tplist-table"><!--查询结果列表-->
                		 <table width="100%" border="0">
                		 	<tr class="tr-backgrond">
                		 		<td class="left-border">账单科目ID</td>                                                                                                                                 
							    <td>账单科目名称</td>
							    <td>账单科目描述</td>
							    <td class="right-border">操作</td>
							    <tbody id="billSubjectData"></tbody>
				
				  <!-- 定义JsRender模版 -->
				  <script id="billSubjectDataTmpl" type="text/x-jsrender">
					<tr>
		   				<td class="left-border">{{:subjectId}}</td>
		   				<td>{{:subjectName}}</td>
						<td title="{{:subjectDesc}}">{{if subjectDesc.length > 30 }}{{:subjectDesc.substring(0,30)}}...{{else}}{{:subjectDesc}}{{/if}}</td>
		   				<td class="right-border">
							<!-- <a href="javascript:void(0);" subjectid="{{:subjectId}}" subjectname="{{:subjectName}}" onclick="pager._viewSubject(this)"><i><img src="../resources/baasop/images/chak.png"></i>查看</a>-->
							<a href="javascript:void(0);" subjectid="{{:subjectId}}" subjectname="{{:subjectName}}" onclick="pager._editSubject(this)"><i><img src="../resources/baasop/images/bianji.png"></i>编辑</a>		
							<a href="javascript:void(0);" subjectid="{{:subjectId}}" subjectname="{{:subjectName}}" onclick="pager._deleteSubject( this)"><i><img src="../resources/baasop/images/shanchu.png"></i>删除</a>
							<a href="javascript:void(0);" subjectid="{{:subjectId}}" subjectname="{{:subjectName}}" industryCode="{{:industryCode}}" onclick="pager._connectedSubject(this)"><i><img src="../resources/baasop/images/guanl.png"></i>关联详单科目</a>
						</td>
					</tr>
				  </script>
                		 	</tr>
                		 </table>
                	</div>
                	<div id="pageview" style="text-align: right">分页</div>
                </div>
          </div>
		</div>
		</div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
var pager;
(function () {
	seajs.use('app/jsp/billSubject/billSubjectList', function (BillSubjectPager) {
		pager = new BillSubjectPager({element: document.body});
		pager.render();
	});
})();
</script>

</html>

