<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>站内信管理-已发送</title>
</head>

<body>
	<!--头部和菜单-->
	<%@ include file="/inc/head.jsp"%>
	<!--头部和菜单结束-->

	<!--中间部分-->
	<div class="wrapper">
		<div class="management"><!--资费管理外侧-->
		   <div class="management-cnt"><!--资费管理内容-->
		      <div class="title-left" id="showMsg">已发送（共封）</div>
		      <div class="nav-tplist-table"><!--查询结果列表-->
		    
		  <table width="100%" border="0" class="mail-table" id="resultListTable">
			  <tr class="tr-backgrond">                                                                                                            
			    <td class="left-border">主题</td> 
			    <td>收件人</td>
			    <td>重要程度</td>   
			    <td>状态</td>                                                                                                                      
			    <td class="right-border">发布时间</td>
			  </tr>                                                                                                                                                                      
		  
		  	<tbody id="siteMailData"></tbody>
		 </table>
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
	
	<!--中间部分结束-->
	<!--底部-->
	<%@ include file="/inc/foot.jsp"%>
	<!--底部结束-->
	
	
	<script type="text/javascript">
		var pager;
		(function () {
			seajs.use('app/jsp/siteletter/siteLetter', function (SiteLetterPager) {
				pager = new SiteLetterPager({element: document.body});
				pager.render();
			});
		})();
	</script>

	<script id="siteLetterListTemple" type="text/template">
		  <tr>
		    <td class="left-border right-border theone" colspan="5">
				{{if keyStr == '1'}}
					今天（{{:stationMailVos.length}}封）
				{{else keyStr == '0'}}
					昨天（{{:stationMailVos.length}}封）
				{{else}}
					更早（{{:stationMailVos.length}}封）
				{{/if}}
			</td>                                                                                  
		  </tr>

		  {{for stationMailVos}}
			{{if isRead == '1'}}
				<tr>
			{{else}}
				<tr class="mail-no">
			{{/if}}
		    <td class="left-border" width="420"><span class="mail-gg"><a href="${_base}/siteLetter/toView?mailId={{:mailId}}">{{:title}}</a></span> </td>
		    <td>{{:recipientName}}</td>
		    <td>
				{{if level == 'important'}}
					重要
				{{else level == 'middle'}}
					较重要
				{{else}}
					一般
				{{/if}}
			</td>
		    <td><span class="red">
				{{if isRead == '0'}}
					对方未读
				{{else}}
					对方已读
				{{/if}}
			</span></td>
		    <td class="right-border pop-re">{{:~timestampToDate('yyyy-MM-dd hh:mm:ss', sendtime)}}</td>
		    </tr>        		
		  {{/for}}
		  
	</script>
</body>
</html>

