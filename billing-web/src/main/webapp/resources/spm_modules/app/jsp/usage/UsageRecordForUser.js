define('app/jsp/usage/UsageRecordForUser', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog'),
        moment = require('moment/2.9.0/moment'),
        UsageRecord = require('app/jsp/usage/UsageRecord');

    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var UsageRecordForUserPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        Implements:UsageRecord,
        attrs: {
        },
        //重写父类
        setup: function () {
            UsageRecordForUserPager.superclass.setup.call(this);
            this._initSelect();
            this._bindCalendar();
        },
        _exportUsageRecords:function(){
            var _this = this;
            var data = $(".query :input,.query select").serializeArray();
            var sheetName = $('#serviceType').val()+"-"+$('#beginDate').val() +"_"+ $('#endDate').val();
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/usage/searchRecordsForUser",
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
        verify:function () {
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
        }
    });

    module.exports = UsageRecordForUserPager;
});
