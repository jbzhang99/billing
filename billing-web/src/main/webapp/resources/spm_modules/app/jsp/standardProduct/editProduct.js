define('app/jsp/standardProduct/editProduct', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog');

    require("bootstrap-paginator/bootstrap-paginator.min");
    require("twbs-pagination/jquery.twbsPagination.min");
    require('opt-paging/aiopt.pagination');

    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");

    var viewProduct = require("app/jsp/standardProduct/viewProduct");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var EditProductPager = Widget.extend({
        Implements:viewProduct,
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #cancel":"cancel",
            "click #selectStrategy":"selectStrategy",
            "change #primaryCategory":"changeSecondaryCategory",
            "blur input[name='mainProductName']":"getStandardProductByName",
            "click #save":"saveStandardProduct"
        },
        //重写父类
        setup: function () {
            EditProductPager.superclass.setup.call(this);
            this._initSelect();
            this.getStandardProduct();
        },
        cancel:function () {
            window.location.href=_base+"/standardProduct/toStandardProductList";
        },
        selectStrategy:function () {
            $("#policyList").show();
            $("#pagination-ul").runnerPagination({
                url: _base+"/strategy/getList",
                method: "get",
                dataType: "json",
                processing: true,
                data : {
                    "related" : "1",
                },
                pageSize: EditProductPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#strategyListTemple");
                        var htmlOut = template.render(data);
                        $("#strategyData").html(htmlOut);
                    }else{
                        $("#strategyData").html("未搜索到信息");
                    }
                }
            });
        },
        _initSelect:function () {
            //一级类目
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/standardProduct/getCategoryList",
                processing: true,
                message: "正在加载，请等待...",
                async:false,
                data:{
                    categoryLevel:1
                },
                success: function(data){
                    if(data&&data.data&&data.statusCode=='1'){
                        $("#primaryCategory").empty();
                        var categories = data.data;
                        $.each(categories,function (index,item) {
                            $("#primaryCategory").append("<option value='"+item.categoryId+"'>"+item.categoryName+"</option>");
                        });
                    }else{
                        var d = Dialog({
                            content:"一级类目下拉初始化失败",
                            ok:function(){
                                this.close();
                            }
                        });
                        d.show();
                    }
                }
            });
            $("#primaryCategory").trigger("change");
            //周期
            ajaxController.ajax({
                type: "get",
                dataType : "json",
                url: _base+"/param/getCycleType",
                async:false,
                processing: true,
                message: "正在加载，请等待...",
                success: function(data){
                    if(data){
                        $.each(data,function (index,item) {
                            $("#billCycle").append("<option value='"+item.paramCode+"'>"+item.paramName+"</option>");
                        });
                    }else{
                        var d = Dialog({
                            content:"计费周期下拉初始化失败",
                            ok:function(){
                                this.close();
                            }
                        });
                        d.show();
                    }
                }
            });
            //代理监听
            $("#strategyData").delegate(".view-strategy","click",function(){
                var strategyId = $(this).attr("policyId");
                var strategyName = $(this).attr("policyName");
                $("input[name='strategyName']").val(strategyName);
                $("input[name='pricePolicy']").val(strategyId);
                $('#policyList').hide();
            });
        },
        saveStandardProduct:function(){
            var _this = this;
            if(_this.verify()){
                $("#productInfo select").removeAttr("disabled");
                var data = $("#productInfo :input,#productInfo select").serializeArray();
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/standardProduct/editStandardProduct",
                    processing: true,
                    message: "正在保存，请等待...",
                    data:data,
                    success: function(data){
                        if(data&&data.statusCode=='1'){
                            window.location.href = _base + '/standardProduct/toStandardProductList';
                        }else{
                            var d = Dialog({
                                content:data.statusInfo,
                                ok:function(){
                                    this.close();
                                }
                            });
                            d.showModal();
                        }
                    }
                });
            }
        },
        getStandardProductByName:function(){
            var productName = $.trim($("input[name='mainProductName']").val());
            var productId = $("input[name='productId']").val();
            if(productName!=""){
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/standardProduct/getStandardProductByName",
                    async:false,
                    data:{
                        productName:productName
                    },
                    success: function(data){
                        if(data&&data.data){
                            var product = data.data;
                            if(product.id!=productId){
                                $("#tip").text("产品名称重复");
                                $("#tip").css("color","red");
                                return false;
                            }else{
                                $("#tip").text("名称可用");
                                $("#tip").css("color","green");
                            }
                        }else{
                            $("#tip").text("名称可用");
                            $("#tip").css("color","green");
                        }
                    }
                });
            }
            return true;
        },
        verify:function () {
            var flag = true;
            $("#productInfo").find("input:not(:hidden)").each(function () {
                var val = $.trim($(this).val());
                if(val==''){
                    var info = $(this).attr("info");
                    var curr = $(this);
                    var d = Dialog({
                        content:info+"不能为空",
                        ok:function(){
                            this.close();
                            curr.focus();
                        }
                    });
                    d.show();
                    flag = false;
                    return false;
                }
            });
            var productName = $.trim($("input[name='mainProductName']").val());
            var productId = $("input[name='productId']").val();
            if(productName!=""){
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/standardProduct/getStandardProductByName",
                    async:false,
                    data:{
                        productName:productName
                    },
                    success: function(data){
                        if(data&&data.data){
                            var product = data.data;
                            if(product.id!=productId){
                                $("#tip").text("产品名称重复");
                                $("#tip").css("color","red");
                                var d = Dialog({
                                    content:"产品名称不能重复",
                                    ok:function(){
                                        this.close();
                                    }
                                });
                                d.show();
                                flag = false;
                            }else{
                                $("#tip").text("名称可用");
                                $("#tip").css("color","green");
                                flag = true;
                            }
                        }else{
                            $("#tip").text("名称可用");
                            $("#tip").css("color","green");
                            flag = true;
                        }
                    }
                });
            }
            return flag;
        }
    });

    module.exports = EditProductPager;
});
