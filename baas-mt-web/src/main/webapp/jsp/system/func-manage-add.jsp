<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<%@ include file="/inc/inc.jsp"%>
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>系统管理-功能管理 -添加</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束--> 
  
  <div class="wrapper">
         
<div class="management"><!--外侧-->
            <div class="nav-tplist-title peiz-title">
            <div class="title-left">基本信息</div>
            </div>

         
          <div class="management-cnt">
          <!--查询区域-->
               <div class="nav-form wih50">
                 <ul>
                  <li>
                 <p class="word">功能名称</p>
                 <p><input type="text" class="int-medium"></p>
                 </li>
                 </ul>
            	<ul>
                  <li>
	                 <p class="word">父级功能名称</p>
	                 <p><input type="text" class="int-medium"></p>
	              </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">功能URL</p>
                 <p><input type="text" class="int-medium"></p>
                 </li>
                 </ul>
                 
                 <ul>
                 <li>
                 <p class="word">功能类型</p>
                 <p><input type="text" class="int-medium"></p>
                 </li>
                 </ul>
                 
                 
                  <ul>
                 <li>
                 <p class="word">功能css样式</p>
                 <p><input type="text" class="int-medium"></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">功能图标</p>
                 <p><input type="text" class="int-medium"></p>
                 </li>
                 </ul>
                 
                   <ul>
                 <li>
                 <p class="word">生效时间</p>
                  <p><input type="text" class="int-medium"><i class="icon-calendar"></i></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">失效时间</p>
                 <p><input type="text" class="int-medium"><i class="icon-calendar"></i></p>
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
         <div class="management-cnt—border"><!--虚线--></div>
         </div>
   

  
   
  </div>
  <div class="footer">©2016 版权所有 亚信集团股份有限公司 京ICP备11005544号-15 京公网安备110108007119号</div>
</body>
</html>
