<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width; initial-scale=0.8;  user-scalable=0;" />
    <%@ include file="/inc/inc.jsp"%>
    <title>套餐产品修改</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->
<style type="text/css">
    span.tipMsg{margin-left: 10px;color: #bbb;}
</style>
<!--中间部分-->
<div class="wrapper">
    <div class="management" id="productInfo"></div>
</div>
<!--中间部分结束-->
<div class="tab-cnt-table bigTableDiv" style="display: none">
    <div class="tab-cnt-wz">
        <p>2</p>
    </div>
    <div class="tab-table-bg">
        <div class="table-bg-title">
            <ul>
                <li class="left">关联产品使用量配置</li>
                <li class="right"><img src="${_base}/resources/baasop/images/stepclose.png"></li>
            </ul>
        </div>
        <div class="table-cnt">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                    <td>业务类型</td>
                    <td>业务类型细分</td>
                    <td>使用量</td>
                    <td>单位</td>
                    <td>周期选择</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="min130"><select class="wh100 serviceType" name="serviceType"></select></td>
                    <td class="min130"><select class="wh100 serviceTypeDetail" name="serviceTypeDetail"></select></td>
                    <td><input class="wh40 sp_require" type="text" tip="使用量" name="amountStart" onkeyup="value=value.replace(/[^\d]/g,'')"><span>-</span><input class="wh40 sp_require" type="text" tip="使用量" name="amountEnd" onkeyup="value=value.replace(/[^\d]/g,'')"></td>
                    <td class="min160"><select class="select-medium" name="unit"></select></td>
                    <td class="min220"><input class="wh50 sp_require" tip="周期" type="text" name="cycleAmount" readonly value="1" onkeyup="value=value.replace(/[^\d]/g,'')"><select class="wh50" name="cycleType"><option value="MONTH">月</option></select></td>
                </tr>

                </tbody>
            </table>
            <span class="steps"><img src="${_base}/resources/baasop/images/stepa.png"></span>
        </div>
        <div class="table-cnt" style="display: none">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                    <td>业务类型</td>
                    <td>业务类型细分</td>
                    <td>使用量</td>
                    <td>单位</td>
                    <td>周期选择</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="min130"><select class="wh100 serviceType" name="serviceType"></select></td>
                    <td class="min130"><select class="wh100 serviceTypeDetail" name="serviceTypeDetail"></select></td>
                    <td><input class="wh40 sp_require" tip="使用量" type="text" name="amountStart" onkeyup="value=value.replace(/[^\d]/g,'')"><span>-</span><input class="wh40 sp_require" type="text" tip="使用量" name="amountEnd" onkeyup="value=value.replace(/[^\d]/g,'')"></td>
                    <td class="min160"><select class="select-medium" name="unit"></select></td>
                    <td class="min220"><input class="wh50 sp_require" tip="周期" type="text" name="cycleAmount" readonly value="1" onkeyup="value=value.replace(/[^\d]/g,'')"><select class="wh50" name="cycleType"><option value="MONTH">月</option></select></td>
                </tr>
                </tbody>
            </table>
            <span class="stepc"><img src="${_base}/resources/baasop/images/stepb.png"></span>
        </div>

        <div class="UnitPrice">
            <span class="priceDesc">单价</span><input type="text" tip="单价" name="price" class="int-medium sp_require" onkeyup="value=value.replace(/[^\0-9\.]/g,'')"><span class="UnitPricey">/元</span>
        </div>
    </div>
</div>

<div class="tab-cnt-table bigPriceTableDivTmpl" style="display: none">
    <div class="tab-cnt-wz">
        <p>2</p>
    </div>
    <div class="tab-table-bg">
        <div class="table-bg-title">
            <ul>
                <li class="left">关联产品使用量配置</li>
                <li class="right"><img src="${_base}/resources/baasop/images/stepclose.png"></li>
            </ul>
        </div>
        <div class="table-cnt">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                    <td>业务类型</td>
                    <td>业务类型细分</td>
                    <td>单价</td>
                    <td>单位</td>
                    <td>周期选择</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="min130"><select class="wh100 serviceType" name="serviceType"></select></td>
                    <td class="min130"><select class="wh100 serviceTypeDetail" name="serviceTypeDetail"></select></td>
                    <td><input class="wh50 sp_require" type="text" tip="单价" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" name="price"> 元</td>
                    <td class="min160"><select class="select-medium" name="unit"></select></td>
                    <td class="min220"><input class="wh50 sp_require" type="text" tip="周期" name="cycleAmount" value="1" readonly onkeyup="value=value.replace(/[^\d]/g,'')"><select class="wh50" name="cycleType"><option value="MONTH">月</option></select></td>
                </tr>
                </tbody>
            </table>
            <span class="steps"><img src="${_base}/resources/baasop/images/stepa.png"></span>
        </div>
        <div class="table-cnt" style="display: none">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                    <td>业务类型</td>
                    <td>业务类型细分</td>
                    <td>单价</td>
                    <td>单位</td>
                    <td>周期选择</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="min130"><select class="wh100 serviceType" name="serviceType"></select></td>
                    <td class="min130"><select class="wh100 serviceTypeDetail" name="serviceTypeDetail"></select></td>
                    <td><input class="wh50 sp_require" type="text" tip="单价" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" name="price"> 元</td>
                    <td class="min160"><select class="select-medium" name="unit"></select></td>
                    <td class="min220"><input class="wh50 sp_require" type="text" tip="周期" name="cycleAmount" value="1" readonly onkeyup="value=value.replace(/[^\d]/g,'')"><select class="wh50" name="cycleType"><option value="MONTH">月</option></select></td>
                </tr>
                </tbody>
            </table>
            <span class="stepc"><img src="${_base}/resources/baasop/images/stepb.png"></span>
        </div>
    </div>
</div>
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
<script id="productDataTmpl" type="text/x-jsrender">
    <div class="management-title step1">请选择产品计费类型</div>
    <div class="management-tab">
        {{if billingType=='STANDARD_GROUP_TYPE'}}
        <div class="management-tab-left">
            <ul class="mana-tab-l current">
                <p>标准组合产品</p>
                <span>产品的简单概况，可实现的计费规则等</span>
                <img src="${_base}/resources/baasop/images/steps.png">
            </ul>
        </div>
        {{else}}
        <div class="management-tab-right">
            <ul class="mana-tab-r current">
                <p>阶梯组合产品</p>
                <span>产品的简单概况，可实现的计费规则等</span>
                <img src="${_base}/resources/baasop/images/steps.png">
            </ul>
        </div>
        {{/if}}
    </div>

    <div class="cnt-two">
        <div class="management-title step2">请配置产品计费信息</div>
        <div class="management-tab-cnt">
            <div class="tab-cnt-title">
                产品计费类型为
                {{if billingType=='STEP_GROUP_TYPE'}}
                <span>阶梯组合产品</span>
                {{else}}
                <span>标准组合产品</span>
                {{/if}}
                <input type="hidden" name="billingType" value="{{:billingType}}">
                <input type="hidden" name="productId" value="{{:productId}}">
            </div>
            <div class="tab-cnt-input">
                <span>产品批价类型为</span>
                <input type="hidden" name="pricingType" value="{{:pricingType}}">
                {{if billingType=='STEP_GROUP_TYPE'}}
                    {{if pricingType=='NO'}}
                        <span> 按照阶梯单价批价</span>
                    {{else}}
                        <span> 按照阶梯总价批价</span>
                    {{/if}}
                {{else}}
                    {{if pricingType=='YES'}}
                        <span> 按照组合总价批价</span>
                    {{else}}
                        <span> 按照产品使用量批价</span>
                    {{/if}}
                {{/if}}
            </div>
            <div class="tab-cnt-input">
                <span><b>*</b>产品（包）名称</span><input type="text" tip="产品（包）名称" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" oncontextmenu="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" tip="产品（包）名称" maxlength="15" name="productName" class="int-xlarge sp_require" value="{{:productName}}"><span class="tipMsg">产品包名称不能超过15个汉字长度</span>
            </div>
            <div class="tab-cnt-input">
                <span><b>*</b>产品使用量配置</span>
            </div>
            {{for majorProductAmount ~billingType=billingType ~pricingType=pricingType}}
            <div class="tab-cnt-table main-product">
                <div class="tab-cnt-wz">
                    <p>{{:#index + 1}}</p>
                </div>
                <div class="tab-table-bg">
                    <div class="table-bg-title">
                        <ul>
                            <li class="left">产品{{:#index + 1}}使用量配置</li>
                            <li class="right"><img src="${_base}/resources/baasop/images/stepclose.png"></li>
                        </ul>
                    </div>
                    <div class="table-cnt">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <td>业务类型</td>
                                <td>业务类型细分</td>
                                {{if ~billingType=='STANDARD_GROUP_TYPE'&&~pricingType=='NO'}}
                                    <td>单价</td>
                                {{else}}
                                    <td>使用量</td>
                                {{/if}}
                                <td>单位</td>
                                <td>周期选择</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <input type="hidden" name="serviceId" value="{{:serviceId}}">
                                <td class="min130"><select class="wh100 serviceType" name="serviceType"></select></td>
                                <td class="min130"><select class="wh100 serviceTypeDetail" name="serviceTypeDetail"></select></td>
                                {{if ~billingType=='STANDARD_GROUP_TYPE'&&~pricingType=='NO'}}
                                    <td><input class="wh50 sp_require" type="text" tip="单价" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" name="price" value="{{:price}}"> 元</td>
                                {{else}}
                                    <td><input class="wh40 sp_require" tip="使用量" type="text" name="amountStart" value="{{:amountStart}}" onkeyup="value=value.replace(/[^\d]/g,'')"><span>-</span><input class="wh40 sp_require" tip="使用量" type="text" name="amountEnd" value="{{:amountEnd}}" onkeyup="value=value.replace(/[^\d]/g,'')"></td>
                                {{/if}}
                                <td class="min160"><select class="select-medium" name="unit"></select></td>
                                <td class="min220"><input class="wh50 sp_require" tip="周期" type="text" readonly value="1" name="cycleAmount" onkeyup="value=value.replace(/[^\d]/g,'')"><select class="wh50" name="cycleType"><option value="MONTH">月</option></select></td>
                            </tr>
                            </tbody>
                        </table>
                        <span class="steps"><img src="${_base}/resources/baasop/images/stepa.png"></span>
                    </div>
                    {{if ~billingType=='STEP_GROUP_TYPE'}}
                        <div class="UnitPrice">
                            {{if ~pricingType=='NO'}}
                                <span>单价</span><input type="text" tip="单价" value="{{:price}}" name="price" class="int-medium sp_require" onkeyup="value=value.replace(/[^\0-9\.]/g,'')"><span class="UnitPricey">/元</span>
                            {{else}}
                                <span>总价</span><input type="text" tip="总价" value="{{:price}}" name="price" class="int-medium sp_require" onkeyup="value=value.replace(/[^\0-9\.]/g,'')"><span class="UnitPricey">/元</span>
                            {{/if}}
                        </div>
                    {{/if}}
                </div>
            </div>
            {{/for}}
            <div class="Increase"><a href="javascript:void(0);"><img src="${_base}/resources/baasop/images/steptj.png">增加关联产品</a></div>

            <div class="tab-form">
                <ul>
                    {{if billingType=='STEP_GROUP_TYPE'&&pricingType=='NO'}}
                    <li>
                        <p class="tab-form-name">阶梯价格是否相同</p>
                        {{if isPriceEqual=='1'}}
                        <p class="cnt-checkbox"><span><input type="checkbox" name="isPriceEqual" value="1" checked>是</span></p>
                        {{else}}
                        <p class="cnt-checkbox"><span><input type="checkbox" name="isPriceEqual" value="1">是</span></p>
                        {{/if}}
                    </li>
                    {{/if}}
                    <li>
                        {{if billingType=='STANDARD_GROUP_TYPE'&&pricingType=='YES'}}
                            <p class="tab-form-name"><i>*</i>总价</p>
                            <p><input type="text" name="totalPrice" value="{{:totalPrice}}" class="int-medium sp_require" tip="总价" onkeyup="value=value.replace(/[^\0-9\.]/g,'')"><span class="UnitPricey">/元</span></p>
                        {{/if}}
                    </li>
                    <li>
                        <p class="tab-form-name"><i>*</i>生效日期</p>
                        <p><input type="text" tip="生效日期" name="activeDate" value="{{:~timestampToDate('yyyy-MM-dd', activeDate)}}" class="int-medium nsp-calendar sp_require" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
                    </li>
                    <li>
                        <p class="tab-form-name"><i>*</i>失效日期</p>
                        <p><input type="text" tip="失效日期" name="invalidDate" value="{{:~timestampToDate('yyyy-MM-dd', invalidDate)}}" class="int-medium nsp-calendar sp_require" readonly><A href="javascript:void(0);"><i class="icon-calendar"></i> </A></p>
                    </li>
                    <li><p class="red">注：＊为必填项</p></li>
                    <li><p class="tab-form-name">&nbsp;</p><input type="button" value="取消" class="next-btn cancel"><input type="button" value="保存" class="next-btn next-btn-hover save"></li>
                </ul>
            </div>
        </div>
    </div>

</script>
</body>
<script type="text/javascript">
    (function () {
        seajs.use('app/jsp/salableproduct/updateSalableProduct', function (UpdateSalableProductPager) {
            var pager = new UpdateSalableProductPager({
                element: document.body,
                tableDiv:$(".bigTableDiv"),
                bigPriceTableDiv:$(".bigPriceTableDivTmpl"),
                productId:'${productId}',
                billingType:'${billingType}'
            });
            pager.render();
        });
    })();
</script>
</html>

