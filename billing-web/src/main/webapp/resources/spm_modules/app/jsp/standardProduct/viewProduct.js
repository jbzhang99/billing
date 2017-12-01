define('app/jsp/standardProduct/viewProduct', function (require, exports, module) {
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

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var ViewProductPager = Widget.extend({
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
            "change #primaryCategory":"changeSecondaryCategory",
            "click #selectStrategy":"selectStrategy"
        },
        //重写父类
        setup: function () {
            ViewProductPager.superclass.setup.call(this);
            this._initSelect();
            this.getStandardProduct();
        },
        cancel:function () {
            window.location.href=_base+"/standardProduct/toStandardProductList";
        },
        selectStrategy:function () {
            var policyId = "";
            if(localStorage){
                var policyId = localStorage.getItem("policyId");
                if(policyId==""){
                    policyId = $("input[name='pricePolicy']").val();
                }
            }
            if(policyId!=""){
                window.location.href = _base+"/strategy/toStrategyShow?policyId="+policyId;
            }
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
                processing: true,
                message: "正在加载，请等待...",
                async:false,
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
        },
        changeSecondaryCategory:function () {
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/standardProduct/getCategoryList",
                processing: true,
                message: "正在加载，请等待...",
                async:false,
                data:{
                    parentId:$("#primaryCategory").val(),
                    categoryLevel:2
                },
                success: function(data){
                    if(data&&data.data&&data.statusCode=='1'){
                        $("#secondaryCategory").empty();
                        var categories = data.data;
                        $.each(categories,function (index,item) {
                            $("#secondaryCategory").append("<option value='"+item.categoryId+"'>"+item.categoryName+"</option>");
                        });
                    }else{
                        var d = Dialog({
                            content:"二级类目下拉初始化失败",
                            ok:function(){
                                this.close();
                            }
                        });
                        d.show();
                    }
                }
            });
        },
        getStandardProduct:function(){
            var _this = this;
            var productId;
            if(localStorage){
                var productId = localStorage.getItem("productId");
                if(productId==""){
                    productId = $("input[name='productId']").val();
                }
            }
            if(productId!=""){
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/standardProduct/getStandardProduct/"+productId,
                    processing: true,
                    message: "正在加载，请等待...",
                    success: function(data){
                        if(data&&data.data&&data.statusCode=='1'){
                            var product = data.data;
                            $("#primaryCategory").val(product.mainProductId);
                            $("#primaryCategory").trigger("change");
                            $("#secondaryCategory").val(product.categoryId);
                            $("input[name='mainProductName']").val(product.mainProductName);
                            $("#billCycle").val(product.billingCycle);
                            $("input[name='pricePolicy']").val(product.pricePolicy);
                            if(localStorage){
                                localStorage.setItem("policyId",product.pricePolicy);
                            }
                            _this.getPricePolicyInfo(product.pricePolicy);
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
        getPricePolicyInfo:function(policyId){
            if(policyId!=""){
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/standardProduct/getPricePolicyInfo/"+policyId,
                    processing: true,
                    message: "正在加载，请等待...",
                    success: function(data){
                        if(data&&data.data&&data.statusCode=='1'){
                            $("input[name='strategyName']").val(data.data.policyName);
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
        }
    });

    module.exports = ViewProductPager;
});
