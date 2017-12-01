<%@page import="java.util.Date"%>
<%@page import="com.ai.opt.sso.client.filter.SSOClientUtil"%>
<%@page import="com.ai.paas.ipaas.ccs.IConfigClient"%>
<%@page import="com.ai.opt.sdk.components.ccs.CCSClientFactory"%>
<%@page import="com.ai.baas.pt.web.constants.BaaSPTConstants"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String _base = request.getContextPath();
	String _baaspt=_base+"/resources/baaspt";
    request.setAttribute("_base", _base);
    request.setAttribute("_baaspt", _baaspt);
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "No-cache");
    
    //运营控制台
    String baas_op_web_url="#";
    IConfigClient ccs=CCSClientFactory.getDefaultConfigClient();
    if(ccs.exists(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY)){
    	baas_op_web_url=ccs.get(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY);
    }
    request.setAttribute("baas_op_web_url", baas_op_web_url);
    
    //注册 
    String baas_uac_reg_url=SSOClientUtil.getCasServerUrlPrefixRuntime(request)+"/reg/toRegister";
    //账户中心
    String baas_uac_center_url=SSOClientUtil.getCasServerUrlPrefixRuntime(request)+"/center/baseInfo/getAccountInfo";
    
    request.setAttribute("baas_uac_reg_url", baas_uac_reg_url);
    request.setAttribute("baas_uac_center_url", baas_uac_center_url);
%>
<script>
    var _base = "${_base}";
</script>
<script src="${_base}/resources/spm_modules/jquery/1.9.1/jquery.js"></script>
<script src="${_base}/resources/spm_modules/bootstrap/dist/js/bootstrap.js"></script>
<script src="${_base}/resources/spm_modules/seajs/2.3.0/dist/sea.js"></script>
<script src="${_base}/resources/spm_modules/seajs/seajs-css.js"></script>
<script src="${_base}/resources/spm_modules/app/core/config.js"></script>

<img id="img_logincheck" style="display:none;" src="${_base}/logincheck?req=<%=new Date().getTime() %>">
