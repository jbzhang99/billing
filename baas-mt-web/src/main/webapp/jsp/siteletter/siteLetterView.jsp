<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>站内信管理-查看详情</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
         
   <div class="management"><!--资费管理外侧-->
           

         
          <div class="management-cnt"><!--资费管理内容-->

         <div class="nav-form">           
                 <ul>
                 <li>
                 <p class="word">收件人</p>
                 <p><input type="text" class="int-xxxlarge" value="${siteMailVo.recipientName}" readonly></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">标题</p>
                 <p><input type="text" class="int-xxxlarge" value="${siteMailVo.title}" readonly></p>
                 </li>
                 </ul>
                 
                 <ul>
                 <li>
                 <p class="word">重要程度</p>
                 <p><span class="radio-zy"><input type="radio" disabled name="radio" <c:if test="${siteMailVo.level=='important'}"> checked="checked" </c:if>>重要</span><span class="radio-zy"><input type="radio" disabled <c:if test="${siteMailVo.level=='middle'}"> checked="checked" </c:if> name="radio">较重要</span><span class="radio-zy"><input type="radio" disabled <c:if test="${siteMailVo.level=='normal'}"> checked="checked" </c:if> name="radio">一般</span></p>
                 </li>
                 </ul>
                 
                 
                 <ul>
                 <li>
                 <p class="word">内容</p>
                 <p><textarea class="inp-text" readonly>${siteMailVo.content}</textarea></p>
                 </li>
                 </ul>
               </div>
         <div class="configure-btn-ctn pad130">
           
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" class="peiz-btn-cancel" value="返  回" onclick="javascript:window.location.href='${_base}/siteLetter/list'"></li>
           </ul>
          </div>
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
		//面包屑导航
		setBreadCrumb("站内信管理","查看详情");
		//左侧菜单选中样式
		$("#mnu_except_mng").addClass("current");
})();
</script>

</html>

