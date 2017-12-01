<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String _base = request.getContextPath();
    request.setAttribute("_base", _base);
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "No-cache");
%>
<script>
    var _base = "${_base}";
</script>

<script src="${_base}/resources/spm_modules/jquery/1.9.1/jquery.js"></script>
<script src="${_base}/resources/spm_modules/bootstrap/dist/js/bootstrap.js"></script>
<script src="${_base}/resources/spm_modules/seajs/2.3.0/dist/sea.js"></script>
<script src="${_base}/resources/spm_modules/seajs/seajs-css.js"></script>
<script src="${_base}/resources/spm_modules/app/core/config.js"></script>

<link rel="stylesheet" type="text/css" href="${_base}/resources/baasop/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${_base}/resources/baasop/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="${_base}/resources/baasop/css/frame.css">
<link rel="stylesheet" type="text/css" href="${_base}/resources/baasop/css/global.css">
<link rel="stylesheet" type="text/css" href="${_base}/resources/baasop/css/modular.css">
<script type="text/javascript" src="${_base}/resources/baasop/js/frame.js" ></script>
<script type="text/javascript" src="${_base}/resources/baasop/js/comp.js" ></script>

<!-- baas-op -->
<link rel="stylesheet" type="text/css" href="${_base}/resources/baasop/css/baas-op.css">

<style type="text/css">
    .index-reset:hover{background-color:#eeeeee;}
</style>
