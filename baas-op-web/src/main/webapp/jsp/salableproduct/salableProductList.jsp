<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <%@ include file="/inc/inc.jsp"%>
    <title>套餐产品列表</title>
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
            <div class="nav-form" id="sp-form">
                <ul>
                    <li>
                        <p class="word">产品ID</p>

                        <p><input type="text" name="productId" class="int-medium"></p>
                    </li>
                    <li>
                        <p class="word">产品名称</p>

                        <p><input type="text" name="productName" class="int-medium"></p>
                    </li>
                    <li>
                        <p class="word">业务类型</p>

                        <p><select name="serviceType" id="serviceType" class="select-medium">
                        </select></p>

                    </li>
                    <li>
                        <p class="word">计费类型</p>

                        <p><select name="billingType" id="groupBillingType" class="select-medium">
                        </select></p>

                    </li>
                </ul>
                <ul>
                    <li>
                        <p class="word">生效开始日期</p>

                        <p><input type="text" class="int-medium" name="activeDate" id="beginTime" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
                    </li>

                    <li>
                        <p class="word">生效截止日期</p>

                        <p><input type="text" class="int-medium" name="invalidDate" id="endTime" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
                    </li>

                   <%-- <li>
                        <p class="word">产品价格</p>

                        <p><input type="text" style="ime-mode:disabled;" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" class="int-mini" name="priceStart" id="price-start">~</p>

                        <p><input type="text" style="ime-mode:disabled;" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" class="int-mini" name="priceEnd" id="price-end"></p>
                    </li>--%>
                    <li class="btn-margin"><input type="button" id="sp-search" value="搜  索" class="bass-btn nav-form-btn"></li>

                </ul>

            </div><!--查询区域结束-->


            <div class="management-cnt—border"><!--虚线--></div>


            <div class="nav-tplist"><!--查询结果-->

                <div class="nav-tplist-title"><!--查询结果标题-->
                    <div class="title-left"><i class="icon-th-list"></i>套餐产品信息</div>
                    <div class="title-right">
                        <p class="plus"><a href="#"><i class="icon-plus"></i></a></p>

                        <p class="plus-word"><a href="${_base}/salableProduct/toAdd">添加套餐产品</a></p>
                    </div>
                </div><!--查询结果标题结束-->

                <div class="nav-tplist-table"><!--查询结果列表-->

                    <table width="100%" border="0">
                        <tr class="tr-backgrond">
                            <td class="left-border">序号</td>
                            <td>产品ID</td>
                            <td>产品名称</td>
                            <td>产品计费类型</td>
                            <td>业务类型细分</td>
                            <td>单位</td>
                            <td>使用量</td>
                            <td>单次／周期</td>
                            <td>单价／总价（元）</td>
                            <td>生失效日期</td>
                            <td>状态</td>
                            <td class="right-border">操作</td>
                        </tr>
                        <tbody id="productData"></tbody>
                    </table>
                </div>

                <div>
                    <nav style="text-align: right">
                        <ul id="pagination">
                        </ul>
                    </nav>
                </div>

            </div>

        </div>

    </div>
</div>
<!--中间部分结束-->
<!--弹出层-->
<div class="table-pop" id="setvalid" style="display: none;right: 300px;top:80px;position: fixed;">
    <div class="pop-close"><img src="${_base}/resources/baasop/images/close.png"></div>
    <div class="pop-title">更改资费状态</div>
    <input type="hidden" name="productId" value="">
    <input type="hidden" name="phoneNum" value="${sessionScope.user_session_key.phone}">
    <div class="pop-cnt">
        <ul>
            <li>
                <p class="name">手机号码</p>

                <p><span class="pop-text-blue" id="phoneNum">${fn:substring(sessionScope.user_session_key.phone, 0, 3)} **** ${fn:substring(sessionScope.user_session_key.phone, 7, 12)}</span>系统将验证码默认发送到该手机！</p>
            </li>
            <li>
                <p class="name">资费状态</p>

                <p>
                    <span><input type="radio" name="feeStatus" id="invalid" value="INACTIVE" class="pop-radio">待生效</span>
                    <span><input type="radio" name="feeStatus" id="valid" value="ACTIVE" class="pop-radio">生效</span>
                </p>
            </li>
            <li>
                <p class="name">验证码</p>

                <p>
                    <input type="text" id="phoneVerifyCode" class="int-small pop-int-small">
                    <input class="button" id="PHONE_IDENTIFY" type="button" value="获取验证码" style=" height:30px; margin-left:20px; padding:0 10px;">
                    <i id="showSmsMsg" style="font-size:1px;color:red;"></i>
                </p>
            </li>
            <li>
                <p class="name">&nbsp;</p>

                <p><input id="BTN_UPDATE_STATUS" type="button" class="pop-btn bass-btn" value="确 认"></p>
            </li>
        </ul>
    </div>
</div>
<!--弹出层结束-->
<script id="productDataTmpl" type="text/x-jsrender">
    <tr>
        <td class="left-border">{{:#index + 1}}</td>
        <td class="productId">{{:productId}}</td>
        <td>{{:productName}}</td>
        <td class="billingType" billingType="{{:billingType}}">{{:billingTypeName}}</td>
        <td>
            {{if serviceInfoList.length > 1}}
                <table width="100%" border="0" class="height-small">
                    {{for serviceInfoList}}
                        <tr>
                            <td>{{:serviceDetailDesc}}</td>
                        </tr>
                    {{/for}}
                </table>
            {{else}}
                {{for serviceInfoList}}
                    {{:serviceDetailDesc}}
                {{/for}}
            {{/if}}
        </td>
        <td>
            {{if serviceInfoList.length > 1}}
                <table width="100%" border="0" class="height-small">
                    {{for serviceInfoList}}
                    <tr>
                        <td>{{:usageAmountName}}</td>
                    </tr>
                    {{/for}}
                </table>
            {{else}}
                {{for serviceInfoList}}
                    {{:usageAmountName}}
                {{/for}}
            {{/if}}
        </td>
        <td>
            {{if serviceInfoList.length > 1}}
                <table width="100%" border="0" class="height-small">
                    {{for serviceInfoList ~billingType=billingType}}
                        <tr>
                            {{if 'STANDARD_GROUP_TYPE'==~billingType}}
                                <td>{{:amountEnd}}</td>
                            {{else }}
                                <td>{{:amountStart}}-{{:amountEnd}}</td>
                            {{/if}}
                        </tr>
                    {{/for}}
                </table>
            {{else}}
                {{for serviceInfoList ~billingType=billingType}}
                    {{if 'STANDARD_GROUP_TYPE'==~billingType}}
                        {{:amountEnd}}
                    {{else}}
                        {{:amountStart}}-{{:amountEnd}}
                    {{/if}}
                {{/for}}
            {{/if}}
        </td>
        <td>
            {{if serviceInfoList.length > 1}}
                <table width="100%" border="0" class="height-small">
                    {{for serviceInfoList}}
                    <tr>
                        <td>1个月</td>
                    </tr>
                    {{/for}}
                </table>
            {{else}}
                {{for serviceInfoList}}
                    1个月
                {{/for}}
            {{/if}}
        </td>
        <td>
            {{if serviceInfoList.length > 1}}
                {{if ('STANDARD_GROUP_TYPE'==billingType&&'NO'==pricingType)||'STANDARD_GROUP_TYPE'!=billingType}}
                    <table width="100%" border="0" class="height-small">
                        {{for serviceInfoList}}
                            <tr>
                                <td>{{:price}}</td>
                            </tr>
                        {{/for}}
                    </table>
                {{else}}
                    {{:totalPrice}}
                {{/if}}
            {{else}}
                {{if 'STANDARD_GROUP_TYPE'==billingType&&'YES'==pricingType}}
                    {{:totalPrice}}
                {{else}}
                    {{for serviceInfoList}}
                        {{:price}}
                    {{/for}}
                {{/if}}
            {{/if}}
        </td>
        <td>{{:~timestampToDate('yyyy/MM/dd', activeDate)}}-{{:~timestampToDate('yyyy/MM/dd', invalidDate)}}</td>
        <td class="pop-re">
            <input type="hidden" name="status" value="{{:status}}">
            {{if status=='INACTIVE' }}
            <a href="javascript:void(0);" class="force-blue sp-setvalid">
                <i><img src="${_base}/resources/baasop/images/shezhi.png"></i>
                待生效
            </a>
            {{else}}
            <a href="javascript:void(0);" class="force sp-setvalid">
                <i><img src="${_base}/resources/baasop/images/shezhi.png"></i>
                生效
            </a>
            {{/if}}
        </td>
        <td class="right-border">
            <%--<a href="#"><i><img src="${_base}/resources/baasop/images/chak.png"></i>查看</a>--%>
            <a href="javascript:void(0);" class="updateProduct"><i><img src="${_base}/resources/baasop/images/bianji.png"></i>编辑</a>
            <a href="javascript:void(0);" class="delProduct"><i><img src="${_base}/resources/baasop/images/shanchu.png"></i>删除</a><br>
            <a href="${_base}/salableProduct/toRelateSubject?productId={{:productId}}&billingType={{:billingType}}" style="margin:5px auto 0px;display:inline-block;">关联详单科目</a>
        </td>
    </tr>
</script>
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>
<script type="text/javascript">
    (function () {
        seajs.use('app/jsp/salableproduct/salableProductList', function (SalableProductListPager) {
            var pager = new SalableProductListPager({element: document.body});
            pager.render();
        });
    })();
</script>
</html>

