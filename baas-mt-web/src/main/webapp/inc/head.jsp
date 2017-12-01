<%@page import="com.ai.baas.mt.web.constants.BaaSMTConstants"%>
<%@page import="com.ai.opt.sdk.components.ccs.CCSClientFactory"%>
<%@page import="com.ai.opt.sso.client.filter.SSOClientUtil"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String uacBase=SSOClientUtil.getCasServerUrlPrefixRuntime(request);
	request.setAttribute("_uacBase", uacBase);
	
	String baas_pt_index_url = CCSClientFactory.getDefaultConfigClient().get(BaaSMTConstants.URLConstant.BAAS_PT_INDEX_URL_KEY);
	request.setAttribute("baas_pt_index_url",baas_pt_index_url );
%>
<script type="text/javascript">
function setBreadCrumb(_first_breadcrumb_title,_second_breadcrumb_title){
	$("#first_breadcrumb_title").html(_first_breadcrumb_title);
	$("#second_breadcrumb_title").html(_second_breadcrumb_title);
}
</script>
<div class="header">
   <div class="logo">
    <a href="${baas_pt_index_url }">LOGO</a>
    <!-- <i class="icon-angle-down"></i>  -->
   </div>
   <div class="subnav">
   	<div class="nav">
      <ul>
		<li>
			<a href="javascript:void(0);">配置管理</a>
			<div class="current-cnt" style="display:none">
				<dl>
					<!-- <dt><a href="javascript:void(0);">基础服务</a></dt> -->
					<dt><a href="javascript:void(0);">配置管理</a></dt>
					<dd>
						<a href="${_base}/configCenter/toQueryConfig">配置中心</a>
						<%-- <a href="${_base}/config/basic/key">缓存中心</a> --%>
						<a href="#">消息中心</a>
					</dd>
				<!-- </dl>
				<dl> -->
					<!-- <dt><a href="javascript:void(0);">业务服务</a></dt> -->
					<dd>
						<a href="${_base}/config/loadcfg/list">DSHM加载配置</a>
					</dd>
				</dl> 
			</div>
		</li>
		<li>
			<a href="javascript:void(0);">异常管理</a>
			<div class="current-cnt" style="display:none">
				<dl>
					<dt><a href="javascript:void(0);">错单管理</a></dt>
					<dd>
						<%--<a href="${_base}/failedBills/list">错单管理</a>--%>
						<a href="${_base}/priceFailed/list">批价错单管理</a>
						<a href="${_base}/orderRedo/list">订单错单管理</a>
					</dd>
				</dl>
				<dl>
					<dt><a href="javascript:void(0);">费用管理</a></dt>
					<dd>
						<a href="${_base}/feeReBatch/list">费用重批</a>
					</dd>
				</dl>
			</div>
		</li>
		<li>
			<a href="javascript:void(0);">站内信管理</a>
			<div class="current-cnt" style="display:none">
				<dl>
					<dt><a href="javascript:void(0);">站内信管理</a></dt>
					<dd>
						<a href="${_base}/siteLetter/list">已发送</a>
					</dd>
					<%-- <dd>
						<a href="${_base}/siteLetter/toAdd">编辑站内信</a>
					</dd> --%>
				</dl>
			</div>
		</li>
      </ul>
   	  <!-- 
      <ul>
	      <li id="mnu_config_mng">
	          <a href="javascript:void(0);">配置管理</a>
	          <div class="current-cnt" style="display:none">
	              <a href="${_base}/config/loadcfg/list">DSHM加载配置</a>
	              <%-- <a href="${_base}/config/loadcfg/toAdd">缓存增加新表</a> --%>
	          </div>
	      </li>
	      <li id="mnu_cache_mng">
	          <a href="javascript:void(0);">缓存中心</a>
	          <div class="current-cnt" style="display:none">
	              <a href="${_base}/config/basic/key">缓存Key查询与删除</a>
	          </div>
	      </li>
	      <li id="mnu_except_mng">
	          <a href="javascript:void(0);">异常管理</a>
	          <div class="current-cnt" style="display:none">
	              <a href="${_base}/failedBills/list">错单管理</a>
	          </div>
	      </li>
	       <li id="mnu_sys_mng" style="display:none;">
	          <a href="javascript:void(0);">系统管理</a>
	          <div class="current-cnt" style="display:none">
	              <a href="${_base}/account/toManage">账号管理</a>
	              <a href="${_base}/role/toManage">角色管理</a>
	              <a href="${_base}/func/toManage">功能管理</a>
	              <a href="${_base}/role-func/toRoleFunc">权限管理</a>
	              <a href="${_base}/tenant/toManage">租户管理</a>
	          </div>
	      </li>
      </ul>
   	   -->
   </div>
   </div>
   <div class="breadcrumb">
    <ul>
     <!-- 面包屑一级导航 -->
     <li><span id="first_breadcrumb_title"></span></li>
     <!-- 面包屑二级导航 -->
     <li><i class="icon-angle-right"></i><span id="second_breadcrumb_title"></span></li>
    </ul>
   </div>
   <div class="user">
    <div class="user-cnt">
     <c:choose>
	    <c:when test="${sessionScope.user_session_key.shortNickName==''}">
	      <p><img src="${_base}/resources/baasmt/images/login_user.png"><span>${sessionScope.user_session_key.nickName}</span><i class="icon-angle-down"></i></p>
	    </c:when>
	    <c:when test="${sessionScope.user_session_key.shortNickName==null}">
	        <p><img src="${_base}/resources/baasmt/images/login_user.png"><span>${sessionScope.user_session_key.nickName}</span><i class="icon-angle-down"></i></p>
	    </c:when>
	    <c:otherwise>
	         <p><img src="${_base}/resources/baasmt/images/login_user.png"><span>${sessionScope.user_session_key.shortNickName}</span><i class="icon-angle-down"></i></p>
	    </c:otherwise>
	</c:choose>
     
     <ul style="display:none;">
      <li><a href="${_uacBase}/center/baseInfo/getAccountInfo">个人中心</a></li>
      <li><a href="${_uacBase}/center/password/confirminfo">修改密码</a></li>
      <li><a href="${_base}/ssologout">退出</a></li>
     </ul>
    </div>
   </div>
</div>

