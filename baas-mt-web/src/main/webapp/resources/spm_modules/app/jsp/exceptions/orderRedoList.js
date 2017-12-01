define('app/jsp/exceptions/orderRedoList', function (require, exports, module) {
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
    require("jsoneditor/5.1.5/jsoneditor.min.css");
    require("jsoneditor/5.1.5/jsoneditor.min");
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var OrderRedoListPager = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    		editor:null
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 10
    	},
    	//事件代理
    	events: {
    		//查询
            "click #BTN_SEARCH":"_searchBtnClick",
            "click #resendEditBill":"_resendEditBill"
        },
    	//重写父类
    	setup: function () {
            OrderRedoListPager.superclass.setup.call(this);
    		this._initPage();
    		this._getFailCode();
            this._initJsonEditor();
    	},
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("异常管理","订单错单管理");
      		//左侧菜单选中样式
      		$("#mnu_except_mng").addClass("current");
      	},
      	_showEditBill:function(){
      		var _this = this;
      		$(".bill-edit").bind("click", function(){
      			var pTag = $(this).parents("[name='failedList']");
      			pTag.attr("id","selectedBill");
                ajaxController.ajax({
        	        method: "get",
        	        url:  _base+"/orderRedo/getFailedOrderDetail/"+pTag.find(":hidden[name='id']").val(),
        	        dataType: "json",
                    processing: true,
        	        async: false,
        	        message: "正在加载数据..",
        	        success: function (data) {
                         _this.editor.set(data.data.orderJson?JSON.parse(data.data.orderJson):{});
        	        }
        	    });
      			$('.table-pop').show();
      		});
      	},
		_initJsonEditor:function () {
            var options = {
                mode: 'form',
                modes: ['code', 'form', 'text', 'tree', 'view'], // allowed modes
                onError: function (err) {
                    Dialog({
                        content: err.toString(),
                        ok:function(){
                            this.close();
                        }
                    }).show();
                }
            };
            this.editor = new JSONEditor($("#billDetail").get(0), options);
        },
    	_getFailCode:function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getOrderFailCode', function(data){
                $("#failCode").append("<option value=''>请选择</option>");
				$.each(data,function(index, item) {
					// 循环获取数据
					$("#failCode").append('<option cid="'+item.id+'" value="'+item.paramCode+'">'+item.paramName+'</option>');
				});
    		});
    	},
    	_searchBtnClick: function(){
    		var _this = this;
    		var url = _base+"/orderRedo/getList";
    		var data = $("#queryForm").serializeArray();
    		//分页
    		$("#pagination-ul").runnerPagination({
	 			url: url,
	 			method: "POST",
	 			dataType: "json",
                processing: true,
	            data:data,
	           	pageSize: OrderRedoListPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	            message: "正在为您查询数据..",
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#failedListsTemple");
    					var htmlOutput = template.render(data);
    					$("#failedBillsData").html(htmlOutput);
    					_this._showEditBill();
	            	}else{
    					$("#failedBillsData").html("没有搜索到相关信息");
	            	}
	            }
    		});
    	},
    	_resendEditBill:function(){
    		var id = $('#selectedBill').find(":hidden[name='id']").val();
    		var data = new Object();
    		data.id = id;
    		data.orderJson = JSON.stringify(this.editor.get());
    		var _this = this;
    		ajaxController.ajax({
    	        method: "POST",
    	        url:  _base+"/orderRedo/resendEditOrder",
    	        dataType: "json",
                processing: true,
    	        async: false,
    	        data: data,
    	        message: "正在加载数据..",
    	        success: function (data) {
    	        	if(data){
                        Dialog({
                            content: data.statusInfo,
                            ok:function(){
                                this.close();
                                $('#selectedBill').removeAttr('id');
                                $('.table-pop').hide(200);
                                _this._searchBtnClick();
                            }
                        }).showModal();
					}
    	        }
    	    });
    	},
    	setSelectValue: function(url, func){
    		$.ajax({
				url : url,
				type : "post",
				async : true,
				dataType : "json",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					if(data){
                        func(data);
					}
				}
			});
    	}
    	
    });
    
    module.exports = OrderRedoListPager
});

