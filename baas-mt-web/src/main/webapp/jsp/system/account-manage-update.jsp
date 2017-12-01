<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
<%@ include file="/inc/inc.jsp"%>
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>系统管理-账号管理-修改</title>
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
                 <p class="word">账号名称</p>
                 <p>bella</p>
                 </li>
                 </ul>
           
                 <ul>
                 <li>
                 <p class="word">账号级别</p>
                 <p><select class="int-medium">
                   <option>hash</option>
                 </select></p>
                 </li>
                 </ul>
                 
                 <ul>
                 <li>
                 <p class="word">邮箱地址</p>
                 <p><input type="text" class="int-medium" ></p>
                 </li>
                 </ul>
                 
                 
                 <ul>
                 <li>
                 <p class="word">手机号码</p>
                 <p><input type="text" class="int-medium" ></p>
                 </li>
                 </ul>
            
                 <ul>
                 <li>
                 <p class="word">微信</p>
                 <p><input type="text" class="int-medium" ></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">微博</p>
                 <p><input type="text" class="int-medium" ></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">QQ</p>
                 <p><input type="text" class="int-medium" ></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">生效时间</p>
                 <p><input type="text" class="int-medium" ><i class="icon-calendar"></i></p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">失效时间</p>
                 <p><input type="text" class="int-medium" ><i class="icon-calendar"></i></p>
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
