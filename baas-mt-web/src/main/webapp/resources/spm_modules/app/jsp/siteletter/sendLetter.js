define('app/jsp/siteletter/sendLetter', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    AjaxController = require('opt-ajax/1.0.0/index');
    
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");
    require("bootstrap-paginator/bootstrap-paginator.min");
    
    require("twbs-pagination/jquery.twbsPagination.min");
    require("opt-paging/aiopt.pagination");
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var SendLetterPager = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 10
    	},
    	//事件代理
    	events: {
    		"click .word-add":"_addTenant"
        },
    	//重写父类
    	setup: function () {
    		SendLetterPager.superclass.setup.call(this);
    		this._initPage();
    		this._loadSiteLetter();
    	},
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("站内信管理","已发送");
      		//左侧菜单选中样式
      		$("#mnu_except_mng").addClass("current");
      	},
      	_addTenant:function(){
      		$('.nav-tplist-eject').show();
    		ajaxController.ajax({
	 			url: _base+"/siteLetter/getTenantInfos",
	 			method: "POST",
	 			dataType: "json",
	 			processing: true,
	            data: {    	            	
	            	tenantId:$('#tenantId').val(),
	            	serviceType:$('#serviceType').val(),
	            	errorCode:$('#errorCode').val()
	            },
	            message: "正在为您查询数据..",
	            success: function (data) {
	            	var data = data.data;
	            	if(data != null && data != 'undefined'){
	            		var template = $.templates("#failedListsTemple");
						var htmlOutput = template.render(data);
						$("#failedBillsData").html(htmlOutput);
						_this._bindRefresh();
	            	}else{
						$("#failedBillsData").html("没有搜索到相关信息");
	            	}
	            }
    		});
      	},
      	_loadSiteLetter: function(){
    		var _this = this;
    		var url = _base+"/siteLetter/getList";
    		//分页
    		$("#pagination-ul").runnerPagination({
	 			url: url,
	 			method: "POST",
	 			dataType: "json",
	 			processing: true,
	           	pageSize: SendLetterPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	            message: "正在为您查询数据..",
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#siteLetterListTemple");
    					var htmlOutput = template.render(data);
    					$("#siteMailData").html(htmlOutput);
    					$("#showMsg").html("已发送（共"+data[0].totalCount+"封）");
	            	}else{
    					$("#siteMailData").html("没有搜索到相关信息");
	            	}
	            }
    		});
    	}
    	
    });
    
    module.exports = SendLetterPager
});

