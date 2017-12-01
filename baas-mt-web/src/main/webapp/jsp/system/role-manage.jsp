<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
<%@ include file="/inc/inc.jsp"%>
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
    <title>配置管理</title>
    
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束--> 
  
  <div class="wrapper">
         
<div class="management positionr"><!--资费管理外侧-->
           

         
          <div class="management-cnt"><!--资费管理内容-->
          <!--查询区域-->
               <div class="nav-form">
                 <ul>
                  <li>
                 <p class="word">租户id</p>
                 <p><input type="text" class="int-medium"> </p>
                 </li>
                  <li>
                 <p class="word">角色名称</p>
                 <p><input type="text" class="int-medium"></p>
                 </li>
                 <li class="btn-margin"><p><input type="button" value="搜  索" class="bass-btn nav-form-btn"></p></li>
                 </ul>
            
                </div><!--查询区域结束-->
          
           <div class="management-cnt—border"><!--虚线--></div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>查询结果列表</div>
            
            <div class="title-right margrt">
            <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
            <p class="plus-word"><a href="#">增加角色</a></p>
            </div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
     <table width="100%" border="0">
  <tr class="tr-backgrond">                                                                                                            
    <td class="left-border">选择</td>                                                                                                                                 
    <td>租户</td>
    <td>角色id</td>
	<td>角色名称</td>
	<td>角色描述</td>
	<td>生效时间</td>
	<td>失效时间</td>
    <td class="right-border">操作</td>
  </tr>                                                                                                                                                                      

  <tr>
    <td class="left-border"><input type="checkbox"  class="int-checkbox"></td>
    <td>XXX</td>                                                                                         
    <td>XXX</td>
    <td>主账号</td>
    <td>13289876767</td>
    <td>已激活</td>
    <td></td>
    <td class="right-border"><a href="#"><i class="icon-search"></i>详情</a><a href="#"><i class="icon-pencil"></i>修改基本信息</a><a href="#"><i class=" icon-remove"></i>删除</a></td>
  </tr>
  <tr>
    <td class="left-border"><input type="checkbox"  class="int-checkbox"></td>
    <td>XXX</td>                                                                                         
    <td>XXX</td>
    <td>主账号</td>
    <td>13289876767</td>
    <td>已激活</td>
     <td></td>
    <td class="right-border"><a href="#"><i class="icon-search"></i>详情</a><a href="#"><i class="icon-pencil"></i>修改基本信息</a><a href="#"><i class=" icon-remove"></i>删除</a></td>
  </tr>
  <tr>
    <td class="left-border"><input type="checkbox"  class="int-checkbox"></td>
    <td>XXX</td>                                                                                         
    <td>XXX</td>
    <td>主账号</td>
    <td>13289876767</td>
    <td>已激活</td>
     <td></td>
    <td class="right-border"><a href="#"><i class="icon-search"></i>详情</a><a href="#"><i class="icon-pencil"></i>修改基本信息</a><a href="#"><i class=" icon-remove"></i>删除</a></td>
  </tr>
  <tr>
    <td class="left-border"><input type="checkbox"  class="int-checkbox"></td>
    <td>XXX</td>                                                                                         
    <td>XXX</td>
    <td>主账号</td>
    <td>13289876767</td>
    <td>已激活</td>
     <td></td>
    <td class="right-border"><a href="#"><i class="icon-search"></i>详情</a><a href="#"><i class="icon-pencil"></i>修改基本信息</a><a href="#"><i class=" icon-remove"></i>删除</a></td>
  </tr>
  <tr>
    <td class="left-border"><input type="checkbox"  class="int-checkbox"></td>
    <td>XXX</td>                                                                                         
    <td>XXX</td>
    <td>主账号</td>
    <td>13289876767</td>
    <td>已激活</td>
     <td></td>
    <td class="right-border"><a href="#"><i class="icon-search"></i>详情</a><a href="#"><i class="icon-pencil"></i>修改基本信息</a><a href="#"><i class=" icon-remove"></i>删除</a></td>
  </tr>

 </table>
 </div>
           
           <div class="configure-btn-ctn">
           
               <div class="paging-large">
 				 <ul>
                <li> <a href="#">&lt;</a> </li>
    		     <li class="active"> <a href="#">1 </a> </li>
    			  <li> <a href="#">2 </a> </li>
   				  <li> <a href="#">3 </a> </li>
    			  <li> <a href="#">4 </a> </li>
                <li> <span>…… </span> </li>
                <li> <a href="#">20 </a> </li>
   				  <li> <a href="#">&gt;</a> </li>
 				  </ul>
			  </div>
           
           
          </div>
         </div>
         
         
         </div>
   
  </div>
  </div>
  <div class="footer">©2016 版权所有 亚信集团股份有限公司 京ICP备11005544号-15 京公网安备110108007119号</div>
</body>
</html>
