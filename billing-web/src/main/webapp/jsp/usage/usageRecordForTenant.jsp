<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1" />
    <title>详单查询</title>
    <%@ include file="/inc/inc.jsp"%>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            seajs.use('app/jsp/usage/UsageRecordForTenant', function (UsageRecordPager) {
                var pager = new UsageRecordPager({element: document.body,base:"${_base}"});
                pager.render();
            });
        })
    </script>
</head>
<body>
<div class="main-right">
    <div class="zuhu-title">
        <ul>
            <li>详单查询</li>
        </ul>
    </div>
    <div class="query">
        <ul>
            <li>
                <p>服务来源</p>
                <span><select class="set-large" id="serviceSource" name="serviceSource"></select></span>
            </li>
        </ul>
        <ul>
            <li>
                <p>服务名称</p>
                <span><select class="set-large" id="serviceType" name="serviceType"></select></span>
            </li>
        </ul>
        <ul>
            <li>
                <p>用户ID</p>
                <span><input class="int-large" type="text" name="userId"></span>
            </li>
        </ul>
        <ul>
            <li>
                <p>用户名称</p>
                <span><input class="int-large" type="text" name="userName"></span>
            </li>
        </ul>
        <ul>
            <li>
                <p>使用时间</p>
                <span><input class="int-large" type="text" id="beginDate" name="beginDate" readonly><i class="icon-calendar"></i></span>
                <b>至</b>
                <span><input class="int-large" type="text" id="endDate" name="endDate" readonly><i class="icon-calendar"></i></span>
            </li>
        </ul>
        <%--<ul>
            <li>
                <p>计量粒度</p>
                <span><select class="set-large" id="measureUnit" name="measureUnit"></select></span>
            </li>
        </ul>--%>
        <ul>
            <li>
                <p>验证码</p>
                <span><input class="int-large" type="text" id="captcha" name="captcha"></span><span class="mar-left"><img src="${_base}/captcha/getImageVerifyCode" id="captchaImg"></span><a class="mar-leftx" href="javascript:void(0);" id="changeCaptcha">看不清楚，换一换</a>
            </li>
        </ul>
        <ul>
            <li>
                <p>&nbsp;</p>
                <button class="btn-query" id="recordExport">导 出</button>
            </li>
        </ul>
    </div>
</div>
</body>
</html>