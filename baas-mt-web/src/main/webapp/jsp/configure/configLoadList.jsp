<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>DSHM加载配置</title>
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
                 <p class="word">表名</p>
                 <p><input type="text" class="int-medium" id="tabName" name="tabName"> </p>
                 </li>
                 <li class="btn-margin"><p><input type="button" value="搜  索" class="bass-btn nav-form-btn" id="BTN_SEARCH"></p></li>
                 </ul>
			</div>
           <!--查询区域结束-->
          
           <div class="management-cnt—border"><!--虚线--></div>
           
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <div class="title-left"><i class="icon-th-list"></i>搜索结果列表</div>
            <div class="title-right">
            <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>
            <p class="plus-word" style="padding-right: 13px"><a href="${_base}/config/loadcfg/toAdd">增加新表</a></p>
             
           <%--  <p class="plus-word"><a href="${_base}/config/basic/key">缓存key查询</a></p> --%>
            </div>
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
		     <table width="100%" border="0">
				  <tr class="tr-backgrond">                                                                                                            
				    <td class="left-border"><input id="checkAll" type="checkbox"  class="int-checkbox1"> 全选</td>                                                                                                                                 
				    <td>表名</td>
				    <td>tableId</td>
				    <td>查询关键字</td>
				    <td>数据链接信息</td>
				    <td class="right-border">操作</td>
				  </tr>                                                                                                                                                                      
				  <tbody id="configData"></tbody>
				
				  <!-- 定义JsRender模版 -->
				  <script id="configDataTmpl" type="text/x-jsrender">
					<tr>
						<td class="left-border"><input type="checkbox" name="configId" tabid="{{:tableId}}" tabname="{{:tableName}}" class="int-checkbox"></td>
		   				<td><a href='javascript:void(0);' tabid="{{:tableId}}" tabname="{{:tableName}}" onmouseover ="pager._getFields(this);">{{:tableName}}</a></td>
						<td>{{:tableId}}</td>
		   				<td title="{{:indexKey}} ">{{if indexKey.length > 40 }}{{:indexKey.substring(0,40)}}...{{else}}{{:indexKey}}{{/if}}   </td>
		   				<td>{{:dbConnect}}</td>
		   				<td class="right-border">
							<a href="javascript:void(0);" tabid="{{:tableId}}" tabname="{{:tableName}}" onclick="pager._refreshLoader(1, this)"><i title="加载" class=" icon-repeat"></i>加载</a>
							<a href="javascript:void(0);" tabid="{{:tableId}}" tabname="{{:tableName}}" onclick="pager._shmDelete(1, this)"><i title="释放" class="icon-resize-horizontal"></i>释放</a>
							<a href="javascript:void(0);" tabid="{{:tableId}}" tabname="{{:tableName}}" onclick="pager._deleteTable(this)"><i title="删除" class=" icon-remove"></i>删除</a>
                            <a href="javascript:void(0);" tabid="{{:tableId}}" tabname="{{:tableName}}" tkey="{{:indexKey}}" onclick="pager._searchKey(this)"><i title="缓存key查询" class=" icon-search"></i>查询</a>
						</td>
					</tr>
				  </script>
			 </table>
           </div>
           
           <div class="configure-btn-ctn">
	         <div class="configure-btn-ctn-left">
	           <ul>
		           <li><input id="btnBatchRefreshLoader" type="button" class="bass-btn peiz-btn" value="批量加载"></li>
		           <li><input id="btnBatchShmDelete" type="button" class="bass-btn peiz-btn" value="批量释放"></li>
		            <li><input id="batchShmDelete" type="button" class="bass-btn peiz-btn" value="批量删除"></li>
	           </ul>
	         </div>
	         
	         <!--分页-->
			 <div>
				<nav style="text-align: right">
					<ul id="pagination-ul">
					</ul>
				</nav>
			 </div>
			 <!--分页-->
			 
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
	seajs.use('app/jsp/configure/configLoadList', function (ConfigLoadPager) {
		pager = new ConfigLoadPager({element: document.body});
		pager.render();
	});
})();
</script>
</html>

