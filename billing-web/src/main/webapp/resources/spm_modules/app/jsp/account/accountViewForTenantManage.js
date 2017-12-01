define('app/jsp/account/accountViewForTenantManage', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog'),
        moment = require('moment/2.9.0/moment');

    require("bootstrap-paginator/bootstrap-paginator.min");
    require("twbs-pagination/jquery.twbsPagination.min");
    require('opt-paging/aiopt.pagination'), 
        
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var AccountViewTenantPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
        },
        //重写父类
        setup: function () {
            AccountViewTenantPager.superclass.setup.call(this);
            this.searchAcctList();
        },
        searchAcctList:function(){
            $.ajax({
    			url : _base+"/account/accountTenantListQuery",
    			type : "post",
    			async : true,
    			dataType : "json",
    			success : function(data) {
    				if(data!=null && data.data!=null){
                        var template = $.templates("#acctInfoTmpl");
                        var htmlOut = template.render(data);
                        $("#acctInfo").html(htmlOut);
                    }else{
                        $("#acctInfo").html("未搜索到信息");
                    }
    			}
    		});
        },
    });

    module.exports = AccountViewTenantPager;
});
