define('app/jsp/standardProduct/productList', function (require, exports, module) {
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
    var ProductListPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #newPro":"toNewStandardProduct",
            "change #primaryCategory":"changeSecondaryCategory",
            "click #query":"queryStandardProduct"
        },
        //重写父类
        setup: function () {
            ProductListPager.superclass.setup.call(this);
            this._initSelect();
            this.queryStandardProduct();
        },
        toNewStandardProduct:function () {
            window.location.href=_base+"/standardProduct/toAddProduct";
        },
        queryStandardProduct:function () {
            var data = $(".query").find("input,select").serializeArray();
            $("#pagination-ul").runnerPagination({
                url: _base+"/standardProduct/getProductList",
                method: "post",
                dataType: "json",
                data:data,
                showWait:true,
                pageSize: ProductListPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        $(".wcx-center").hide();
                        $("#productList").show();
                        var template = $.templates("#productListTemple");
                        var htmlOut = template.render(data);
                        $("#productData").html(htmlOut);
                        $(".page").show();
                    }else{
                        $("#productList").hide();
                        $(".page").hide();
                        $(".wcx-center").show();
                    }
                }
            });
        },
        _initSelect:function () {
            //实例
            ajaxController.ajax({
                type: "get",
                dataType : "json",
                url: _base+"/param/getCycleType",
                success: function(data){
                    if(data){
                        $("#billCycle").empty();
                        $("#billCycle").append("<option value=''>请选择</option>");
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
            //时间代理
            $("#productData").delegate(".p_del","click",function(){
                var productId = $(this).attr("productId");
                var d = Dialog({
                    content:"确认删除？",
                    okValue:"确定",
                    cancelValue:"取消",
                    ok:function(){
                        ajaxController.ajax({
                            type: "get",
                            dataType : "json",
                            url: _base+"/standardProduct/deleteStandardProduct/"+productId,
                            processing: true,
                            message: "正在删除，请等待...",
                            success: function(data){
                                if(data&&data.statusCode=='1'){
                                    window.location.reload();
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
                    },
                    cancel:function () {
                        this.close();
                    }
                });

                d.show();
            });
        }
    });

    module.exports = ProductListPager;
});
