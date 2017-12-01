<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <title>结算处理结果上传</title>
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
                <p class="word">上传文件类型</p>
                 <p>
	                 <select id="dataType" class="select-medium"> 
	                   <option value="">请选择</option>
	                   <option value="order">业务流水文件</option>
	                   <option value="bill">第三方账单文件</option>
	                 </select> 
                 	<span class="regsiter-note" id="dataTypeMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="dataTypeMsg"></span>
				  	</span>
                 </p>
                 </li>
                 </ul>
                 <ul>
                 <li>
                 <p class="word">账期</p>
                 <p id="accountPeriodEvent">
                 	<input id="accountPeriod" type="text" class="int-medium" readonly><A href="javascript:void(0)"><i class="icon-calendar"></i> </A>
                 </p>
                 	<span class="regsiter-note" id="accountPeriodMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="accountPeriodMsg"></span>
				  	</span>
                 </li>
                 
                 </ul>
                 <ul>
                <li>
                 <p class="word">文件流水类型</p>
                 <p>
	                 <select id="dataObj" class="select-medium"> 
	                 	<option value="">请选择</option>
	                 </select> 
	                 <span class="regsiter-note" id="dataObjMsgDiv" style="display:none">
					     <i class="icon-caret-left"></i><img src="${_base}/resources/baasop/images/error.png">
					     <span id="dataObjMsg"></span>
				  	</span>
                 </p>
                 </li>
                 
                 
                 </ul>
                 <ul>
                  <li class="btn-margin"><p class="word">&nbsp;</p><p><a href="javascript:void(0);">选择本地上传文件<input id="uploadFile" type="file" class="flie"></a><br>请选择Zip格式文件包，上传文件不能超过10M</p></li>
                 </ul>
               
                </div><!--查询区域结束-->
          
           <div class="management-cnt—border"><!--虚线--></div>
           
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
             <!--<div class="title-right">
            <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
            <p class="plus-word"><a href="#">添加优惠产品</a></p>
            </div>-->
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
             <table width="100%" border="0">
          <tr class="tr-backgrond">                                                                                                            
            <td width="5%" class="left-border">序号</td>                                                                                                                                 
            <td width="20%">文件名</td>
            <td width="10%">上传文件类型</td>
            <td width="10%">结算流水类型</td>
            <td width="7%">账期</td>
            <td width="13%">上传时间</td>
            <td width="10%">处理状态</td>
            <td width="15%">文件异常原因</td>
            <td width="10%" class="right-border">操作</td>
          </tr>
       	  <tbody id="importlogList"></tbody>
        </table>
        </div>
          <div id="pagination" style="text-align: right">
		  </div>
          </div>
          <div id="uploadFileMsg" class="upload-pop" style="display:none">
           <ul>
            <li class="up-left">上传文件</li>
            <li id="fileName" class="up-right"></li>
           </ul>
           <ul>
            <li class="up-left">文件大小</li>
            <li id="fileSize" class="up-right"></li>
           </ul>
           <div class="up-btn">
            <a class="up-no" id="closeBtn">放 弃</a>
            <a id="uploadBtn">确 定</a>
           </div>
           <p id="fileTypeError">文件格式错误，请重新选择Zip格式文件包</p>
           <p id="fileSizeError">文件大小超出范围(10M)，请重新选择</p>
          </div>
         </div>

  </div>
  <%@ include file="/inc/foot.jsp"%>
  <script id="importlogListTmpl" type="text/x-jsrender">
		 <tr>
            <td class="left-border">{{:index}}</td>
            <td>{{:impFileName}}</td>
            <td>{{:dataTypeName}}</td>
            <td>{{:objectIdName}}</td>
            <td>{{:billTimeMsg}}</td>
            <td>{{:~timesToFmatter(importTime)}}</td>
            <td>{{:stateName}}</td>
            <td>{{:exceptMsg}}</td>
            <td class="right-border">
				{{if state=='4'}}
					<a href="${_base}/account/download?rstFileName={{:rstFileName}}&rstFileUrl={{:rstFileUrl}}"><i><img src="${_base}/resources/baasop/images/guanl.png"></i>下载文件</a>
				{{else}}
					<a class="disable"><i><img src="${_base}/resources/baasop/images/guanl_disable.png"></i>下载文件</a>
				{{/if}}
			</td>
          </tr>
  </script>
  <script type="text/javascript">
  	var pager;
  	var serviceTypeList = $.parseJSON('${serviceTypeList}');
	(function () {
		seajs.use('app/jsp/account/uploadAccount', function (UploadAccountPager) {
			pager = new UploadAccountPager({element: document.body});
			pager.render();
		});
	})();
  </script>
</body>
</html>
