define('app/jsp/usage/UsageRecord', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog'),
        Calendar = require('arale-calendar/1.1.2/index'),
        moment = require('moment/2.9.0/moment');

    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var UsageRecordPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #recordExport":"verifyData",
            "change #serviceSource":"initServices",
            "click #captchaImg,#changeCaptcha":"changeCaptcha"
        },
        //重写父类
        setup: function () {
            UsageRecordPager.superclass.setup.call(this);
            this._initSelect();
            this._bindCalendar();
        },
        _initSelect:function () {
            ajaxController.ajax({
                type: "get",
                dataType : "json",
                url: _base+"/usage/getSuppliers",
                async:false,
                success: function(data){
                    if(data&&data.statusCode=='1'){
                        var suppilers = data.data;
                        $.each(suppilers,function (index,item) {
                            $("#serviceSource").append("<option value='"+item.id+"'>"+item.paramName+"</option>");
                        });
                    }else{
                        var d = Dialog({
                            content:"供应商下拉初始化失败",
                            ok:function(){
                                this.close();
                            }
                        });
                        d.show();
                    }
                }
            });
            $("#serviceSource").trigger("change");
            //$("#measureUnit").append("<option value='month'>月</option>")
        },
        initServices:function () {
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/usage/getServices",
                data:{
                    parentCode:$("#serviceSource").val()
                },
                success: function(data){
                    if(data&&data.statusCode=='1'){
                        var services = data.data;
                        $.each(services,function (index,item) {
                            $("#serviceType").append("<option value='"+item.paramCode+"'>"+item.paramName+"</option>");
                        });
                    }else{
                        var d = Dialog({
                            content:"供应商服务下拉初始化失败",
                            ok:function(){
                                this.close();
                            }
                        });
                        d.show();
                    }
                }
            });
        },
        _bindCalendar:function () {
            var endRange = moment().subtract(1, "days");
            $(".icon-calendar").siblings("input").each(function(){
                new Calendar({
                    trigger: $(this),
                    range: [null, endRange]
                });
            });
            $(".icon-calendar").each(function(){
                new Calendar({
                    trigger: $(this),
                    output:$(this).siblings("input"),
                    range: [null, endRange],
                    align: {
                        selfXY: [0, 0],
                        baseElement:$(this).siblings("input"),
                        baseXY: [0, '100%']
                    }
                });
            });
            $("#beginDate").val(endRange.format("YYYY-MM-DD"));
            $("#endDate").val(endRange.format("YYYY-MM-DD"));
            //绑定事件
            $(".icon-calendar").siblings("input").bind("change",function(){
                var beginDate = $("#beginDate");
                var endDate = $("#endDate");
                var begin = moment(beginDate.val());
                var end;
                if(endDate.val()==""){
                    end = moment().subtract(1, "days");
                }else{
                    end = moment(endDate.val());
                }
                if($(this).is(beginDate)){
                    if(begin.diff(end)>0){//开始日期大于结束日期
                        if(begin.get("month")!=moment().get("month")){
                            endDate.val(begin.endOf("month").format("YYYY-MM-DD"));
                        }else{
                            endDate.val(endRange.format("YYYY-MM-DD"));
                        }

                    }else{
                        if(begin.get("year")!=end.get("year")||begin.get("month")!=end.get("month")){
                            endDate.val(begin.endOf("month").format("YYYY-MM-DD"));
                        }
                    }
                }else{
                    if(begin.diff(end)>0){
                        beginDate.val(end.startOf("month").format("YYYY-MM-DD"));
                    }else{
                        if(begin.get("year")!=end.get("year")||begin.get("month")!=end.get("month")){
                            beginDate.val(end.startOf("month").format("YYYY-MM-DD"));
                        }
                    }
                }
            });
        },
        _exportUsageRecords:function(){
            var _this = this;
            var data = $(".query :input,.query select").serializeArray();
            var sheetName = $('#serviceType').val()+"-"+$('#beginDate').val() +"_"+ $('#endDate').val();
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/usage/searchRecords",
                processing: true,
                message: "正在加载，请等待...",
                data:data,
                success: function(data){
                    if(data&&data.statusCode=='1'){
                        _this.changeCaptcha();
                        window.location.href = _base + '/usage/exportRecords?sheetName='+sheetName;
                    }else{
                        var d = Dialog({
                            content:data.statusInfo,
                            ok:function(){
                                this.close();
                                window.location.reload();
                            }
                        });
                        d.showModal();
                    }
                }
            });
        },
        verifyData:function () {
            var _this = this;
            if(this.verify()){
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/captcha/verifyCaptcha",
                    async:false,
                    data:{
                        captcha:$.trim($("#captcha").val())
                    },
                    success: function(data){
                        if(data&&data.statusCode=='1'){
                            if(data.data=='1'){
                                _this._exportUsageRecords();
                            }else {
                                var d = Dialog({
                                    content:data.statusInfo,
                                    ok:function(){
                                        this.close();
                                    }
                                });
                                d.show();
                            }
                        }
                    }
                });
            }
        },
        verify:function () {
            var userId = $.trim($("input[name=userId]").val());
            var userName = $.trim($("input[name=userName]").val());
            if(userId==""&&userName==""){
                var d1 = Dialog({
                    content:"请填写租户Id或租户名称",
                    ok:function(){
                        this.close();
                    }
                });
                d1.show();
                return false;
            }
            var captcha = $("#captcha").val();
            if($.trim(captcha)==""){
                var d2 = Dialog({
                    content:"验证码不能为空",
                    ok:function(){
                        this.close();
                    }
                });
                d2.show();
                return false;
            }
            return true;
        },
        changeCaptcha:function () {
            $("#captchaImg").attr("src",this.get("base")+"/captcha/getImageVerifyCode?"+new Date().getTime());
        }
    });

    module.exports = UsageRecordPager;
});
