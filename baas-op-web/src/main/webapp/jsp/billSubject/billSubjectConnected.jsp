<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>账户优惠管理</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	<div class="management"><!--资费管理外侧-->
         <div class="nav-tplist-title peiz-title">
         	<div class="title-left"></div>
         </div>
         <div class="management-cnt">
         		<div class="nav-form">
         		<ul>
                 <li>
                 <p class="word">请选择关联费用科目</p>
                 <input  type="hidden" id="subjectId" value= "${subjectInfo.subjectId }"/>
                 <p class="cj-border" id="billSubjectData">
                 </p>
                  <script id="billSubjectDataTmpl" type="text/x-jsrender">
				  		<span class="list-cj" onclick="pager._checkSubject(this)"><a href="javascript:void(0);"  subjectid="{{:subjectId}}">{{:subjectName}}</a></span>
				  </script>
                 <p class="arrow-cj"><a href="javascript:void(0);" id="BTN_ADD">添加<i class="icon-double-angle-right"></i></a><a href="javascript:void(0);" id="BTN_REMOVE">取消<i class="icon-double-angle-left"></i></a>
                 <a href="javascript:void(0);" id="BTN_SAVE">保存</a></p>
                  <p class="cj-border" id="connectSubjectData">
                 </p>
                 
                 </li>
                 </ul>
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
	seajs.use('app/jsp/billSubject/drSubjectConnect', function (BillSubjectConnectPager) {
		pager = new BillSubjectConnectPager({element: document.body});
		pager.render();
	});
})();
</script>

</html>

