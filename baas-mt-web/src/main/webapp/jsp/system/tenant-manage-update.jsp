<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
<%@ include file="/inc/inc.jsp"%>
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>系统管理-租户管理-修改</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->  
<div class="wrapper">
         
<div class="management"><!--外侧-->
            <div class="nav-tplist-title peiz-title">
            <div class="title-left">修改基本信息</div>
            </div>

         
          <div class="management-cnt">
          <!--查询区域-->
               <div class="nav-form">
               	<ul>
	                  <li>
	                 <p class="word">租户id</p>
	                 <p>12234</p>
	                 </li>
                 </ul>
                  <ul>
	                  <li>
	                 <p class="word">状态</p>
	                 <p>已签合同</p>
	                 </li>
                 </ul>
                 <ul>
	                  <li>
	                 <p class="word">创建人ID</p>
	                 <p>COCO</p>
	                 </li>
                 </ul>
                 <ul>
                  	<li>
                 		<p class="word">租户名称</p>
                 		<p><input type="text" class="int-medium"></p>
                 	</li>
                 </ul>
                </div><!--查询区域结束-->  
        
           
           <div class="configure-btn-ctn pad130">
           
           <div class="configure-btn-ctn-left">
           <ul>
           <li><input type="button" class="peiz-btn-cancel" value="取  消"></li>
           <li><input type="button" class="bass-btn peiz-btn" value="确  定"></li>
           </ul>
           
           
        
           
            
           
          </div>
         </div>
         
         
         </div>
   

  
   
  </div>
  <div class="footer">©2016 版权所有 亚信集团股份有限公司 京ICP备11005544号-15 京公网安备110108007119号</div>
</body>
</html>
