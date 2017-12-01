<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="zh-cn">
<head>
   <%@ include file="/inc/inc.jsp"%>
<title>配置中心</title>
<script type="text/javascript">
var pager;
(function () {
	seajs.use('app/jsp/configcenter/queryConfig', function (QueryConfigPager) {
		pager = new QueryConfigPager({element: document.body});
		pager.render();
	});
})();
</script>
</head>

<body>

<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
<div class="wrapper">     
<div class="management"><!--资费管理外侧-->
           

         
          <div class="management-cnt"><!--资费管理内容-->
          
           <div class="nav-tplist"><!--查询结果-->
           
            <div class="nav-tplist-title"><!--查询结果标题-->
            <!-- <div class="title-left">路径：/baas/opt </div> -->
            </div><!--查询结果标题结束-->
            
           <div class="nav-tplist-table"><!--查询结果列表-->
    
		     <table width="100%" border="0">
		          <tr class="tr-backgrond">                                                                                                            
		            <td>工程名称</td>
		            <td>工程编码</td>
		            <td>配置中心编码</td>
		            <td class="right-border">操作</td>
		          </tr>
		          <tr>
		            <td>中信单点登录</td>
		            <td id="project_name">citic-uac-web</td>
		            <td id="ccs_name">aiopt-baas-citic-uac</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-citic-uac');">配置</a></td>
		          </tr>
		          <tr>
		            <td>中信计费web</td>
		            <td id="project_name">citic-billing-web</td>
		            <td id="ccs_name">aiopt-baas-citic-billing-web</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-citic-billing-web');">配置</a></td>
		          </tr>
		          <tr>
		            <td>门户web</td>
		            <td id="project_name">baas-pt-web</td>
		            <td id="ccs_name">aiopt-baas-pt-web</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-pt-web');">配置</a></td>
		          </tr>
		          <tr>
		            <td>账户体系web</td>
		            <td id="project_name">baas-uac-web</td>
		            <td id="ccs_name">aiopt-baas-uac-web</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-uac-web');">配置</a></td>
		          </tr>
		          <tr>
		            <td>租户控制台web</td>
		            <td id="project_name">baas-op-web</td>
		            <td id="ccs_name">aiopt-baas-op-web</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-op-web');">配置</a></td>
		          </tr>
		          <tr>
		            <td>运维控制台web</td>
		            <td id="project_name">baas-mt-web</td>
		            <td id="ccs_name">aiopt-baas-mt-web</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-mt-web');">配置</a></td>
		          </tr>
		          <tr>
		            <td>账户体系后场</td>
		            <td id="project_name">opt-uac</td>
		            <td id="ccs_name">aiopt-baas-uac</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-uac');">配置</a></td>
		          </tr>
		          <tr>
		            <td>共享内存后场</td>
		            <td id="project_name">baas-dshm</td>
		            <td id="ccs_name">aiopt-baas-dshm</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-dshm');">配置</a></td>
		          </tr>
		          <tr>
		            <td>计费批价后场</td>
		            <td id="project_name">baas-bmc</td>
		            <td id="ccs_name">aiopt-baas-bmc</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-bmc');">配置</a></td>
		          </tr>
		          <tr>
		            <td>计费服务</td>
		            <td id="project_name">baas-bmc-service</td>
		            <td id="ccs_name">aiopt-baas-bmc-service</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-bmc-service');">配置</a></td>
		          </tr>
		          <tr>
		            <td>订购和使用量批处理</td>
		            <td id="project_name">baas-batch-order-client</td>
		            <td id="ccs_name">aiopt-baas-batch-client</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-batch-client');">配置</a></td>
		          </tr>
		          <tr>
		            <td>账务处理后场</td>
		            <td id="project_name">baas-amc</td>
		            <td id="ccs_name">aiopt-baas-amc</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-amc');">配置</a></td>
		          </tr>
		          <tr>
		            <td>结算处理后场</td>
		            <td id="project_name">baas-smc</td>
		            <td id="ccs_name">aiopt-baas-smc</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-smc');">配置</a></td>
		          </tr>
		          <tr>
		            <td>欠费处理后场</td>
		            <td id="project_name">baas-omc</td>
		            <td id="ccs_name">aiopt-baas-omc</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-omc');">配置</a></td>
		          </tr>
		          <tr>
		            <td>数据采集后场</td>
		            <td id="project_name">baas-rtm</td>
		            <td id="ccs_name">aiopt-baas-rtm</td>
		            <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-rtm');">配置</a></td>
		          </tr>
				 <tr>
					 <td>系统管理后场</td>
					 <td id="project_name">opt-sys</td>
					 <td id="ccs_name">aiopt-baas-sys</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-sys');">配置</a></td>
				 </tr>
				 <tr>
					 <td>baas定时任务</td>
					 <td id="project_name">baas-job</td>
					 <td id="ccs_name">aiopt-baas-job</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-job');">配置</a></td>
				 </tr>
				 <tr>
					 <td>baas-abm</td>
					 <td id="project_name">baas-abm</td>
					 <td id="ccs_name">aiopt-baas-abm</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-abm');">配置</a></td>
				 </tr>
				 <tr>
					 <td>baas-res-deposit-client</td>
					 <td id="project_name">baas-res-deposit-client</td>
					 <td id="ccs_name">aiopt-baas-abm</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-res-deposit-client');">配置</a></td>
				 </tr>
				 <tr>
					 <td>baas-batch-res-deposit-client</td>
					 <td id="project_name">baas-batch-res-deposit-client</td>
					 <td id="ccs_name">aiopt-baas-batch-res-deposit-client</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-batch-res-deposit-client');">配置</a></td>
				 </tr>
				 <tr>
					 <td>baas-prd</td>
					 <td id="project_name">baas-prd</td>
					 <td id="ccs_name">baas-prd</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-prd');">配置</a></td>
				 </tr>
				 <tr>
					 <td>baas-batch-deduct-client</td>
					 <td id="project_name">baas-batch-deduct-client</td>
					 <td id="ccs_name">aiopt-baas-batch-deduct-client</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-batch-deduct-client');">配置</a></td>
				 </tr>
				 <tr>
					 <td>信控处理服务</td>
					 <td id="project_name">baas-ccp</td>
					 <td id="ccs_name">aiopt-baas-ccp</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-ccp');">配置</a></td>
				 </tr>
				 <tr>
					 <td>包费计算服务</td>
					 <td id="project_name">baas-pkgfee</td>
					 <td id="ccs_name">aiopt-baas-pkgfee</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-pkgfee');">配置</a></td>
				 </tr>
				 <tr>
					 <td>支付</td>
					 <td id="project_name">baas-pay</td>
					 <td id="ccs_name">aiopt-baas-pay</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-pay');">配置</a></td>
				 </tr>
				 <tr>
					 <td>优惠</td>
					 <td id="project_name">baas-dst</td>
					 <td id="ccs_name">aiopt-baas-dst</td>
					 <td class="right-border"><a href="javascript:void(0);" id="config" onclick="pager._search('aiopt-baas-dst');">配置</a></td>
				 </tr>
		 	</table>
 		</div>
</div>
</div> 
  </div>
  <div class="footer">©2016 版权所有 亚信集团股份有限公司 京ICP备11005544号-15 京公网安备110108007119号</div>
</body>
</html>
