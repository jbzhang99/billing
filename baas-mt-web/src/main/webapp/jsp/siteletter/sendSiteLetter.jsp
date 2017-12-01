<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>站内信管理-编辑站内信</title>
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
                 <p><input type="text" class="int-xxxlarge"><img class="word-add" src="${_base}/resources/baasmt/images/add.png"></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">标题</p>
                 <p><input type="text" class="int-xxxlarge"></p>
                 </li>
                 </ul>
                 
                 <ul>
                 <li>
                 <p class="word">重要程度</p>
                 <p><span class="radio-zy"><input type="radio" name="radio" checked="checked">重要</span><span class="radio-zy"><input type="radio" name="radio">较重要</span><span class="radio-zy"><input type="radio" name="radio">一般</span></p>
                 </li>
                 </ul>
                 
                 
                 <ul>
                 <li>
                 <p class="word">内容</p>
                 <p><textarea class="inp-text"></textarea></p>
                 </li>
                 </ul>
               </div>
         <div class="configure-btn-ctn pad130">
           
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" class="peiz-btn-cancel" value="取  消"></li>
           <li><input type="button" class="bass-btn peiz-btn" value="确  定"></li>
           </ul>
          </div>
         </div>
         
         
   <div class="nav-tplist-eject" style="display:none">
    <div class="eject-bg"></div>
    <div class="eject-wap eject-mail">
    <div class="eject-cnt">
     <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>请选择租户</div>
            
            <div class="nav-form martop">
                 <ul>
                  <li>
                 <p class="word">租户账号</p>
                 <p><input type="text" class="int-medium"> </p>
                 </li>
                  <li>
                  <li>
                 <p class="word">租户ID</p>
                 <p><input type="text" class="int-medium"> </p>
                 </li>
                  <li>
                 <p class="word">账号状态</p>
                 <p><select class="select-medium"></select> </p>
                 </li>
                 <li class="btn-margin"><p><input type="button" value="搜  索" class="bass-btn nav-form-btn"></p></li>
                 </ul>
            
                </div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table "><!--查询结果列表-->
           	<table width="100%" border="0">
		  	<tr class="tr-backgrond"> 
		    <td class="left-border" width="10%"><input type="checkbox" class="int-checkbox1" checked=""> 全选</td>                                                                                                           
		    <td width="40%">租户账号</td>
		    <td width="30%">租户ID</td>
		    <td width="20%" class="right-border">账号状态</td>
		  </tr>
	</table>
    <div class="mail-tbody">
    	
     <table width="100%" border="0">
		
		  <tr>
		    <td width="10%" class="left-border"><input type="checkbox" class="int-checkbox1" checked=""></td>
		    <td width="40%">显示租户账号</td>
		    <td width="30%">123123213</td>
		    <td width="20%" class="right-border">已签约</td>
		  </tr>
    </table>
    </div>
 </div>
           <div class="configure-btn-ctn">
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" class="peiz-btn-cancel" value="取  消"></li>
           <li><input type="button" class="bass-btn peiz-btn" value="确定"></li>
           </ul>
           </div>
           </div>
          </div>
         </div>
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
			seajs.use('app/jsp/siteletter/sendLetter', function (SendLetterPager) {
				pager = new SendLetterPager({element: document.body});
				pager.render();
			});
		})();
	</script>

	<script id="siteLetterListTemple" type="text/template">
		  
		  
	</script>
</body>
</html>

