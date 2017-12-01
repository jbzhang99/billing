define('app/jsp/bill/tenantBillSearch', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog'),
        moment = require('moment/2.9.0/moment');

    require("bootstrap-paginator/bootstrap-paginator.min");
    require("twbs-pagination/jquery.twbsPagination.min");
    require('opt-paging/aiopt.pagination');
        
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var TenantBillSearchPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #billQuery":"searchBillList"
        },
        //重写父类
        setup: function () {
            TenantBillSearchPager.superclass.setup.call(this);
            this._initSelect();
            this.searchBillList();
        },
        _initSelect:function () {
            //初始化支付状态
            $("#payState").append("<option value='1'>已支付</option>");
            $("#payState").append("<option value='0'>待支付</option>");
            //初始化账单月
            for(var i=0;i<6;i++){
                var time = moment().subtract(i,"month");
                $("#billMonth").append("<option value='"+time.format("YYYYMM")+"'>"+time.format("YYYY[年]MM[月]")+"</option>");
            }
        },
        searchBillList:function(){
            var data = $("#billCondition :input,#billCondition select").serializeArray();
            $("#pagination").runnerPagination({
                url: _base+"/bill/tenantBillSearch",
                method: "post",
                dataType: "json",
                data : data,
                processing: true,
                pageSize: TenantBillSearchPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#billDataTmpl");
                        var htmlOut = template.render(data);
                        $("#billData").html(htmlOut);
                    }else{
                        $("#billData").html("未搜索到信息");
                    }
                }
            });
        },
        serializeObjectToJson:function(obj){
            var o={};
            $.each(obj,function(index,e){
                if(o[e.name]){
                    if(!o[e.name].push){
                        o[e.name] = [o[e.name]];
                    }
                    o[e.name].push(e.value||'');
                }else{
                    o[e.name] = e.value||'';
                }
            });
            return o;
        }
    });

    module.exports = TenantBillSearchPager;
});
