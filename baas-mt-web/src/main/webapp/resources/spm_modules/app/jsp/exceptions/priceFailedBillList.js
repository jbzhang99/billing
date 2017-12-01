define('app/jsp/exceptions/priceFailedBillList', function (require, exports, module) {
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
    var PriceFailedBillListPager = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 10
    	},
    	//事件代理
    	events: {
    		//查询
            "click #BTN_SEARCH":"_searchBtnClick",
            "click #checkAll":"_checkAll",
            "click .resend-bill":"_resendBill",
            "click .delete-bill":"_deleteBill",
            "click #resendEditBill":"_resendEditBill"
        },
    	//重写父类
    	setup: function () {
            PriceFailedBillListPager.superclass.setup.call(this);
    		this._initPage();
    		this._getServiceType();
    		this._getFailCode();
    	},
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("异常管理","批价错单管理");
      		//左侧菜单选中样式
      		$("#mnu_except_mng").addClass("current");
      	},
      	_showEditBill:function(){
      		var _this = this;
      		$(".bill-edit").bind("click", function(){
      			var pTag = $(this).parents("[name='failedList']");
      			pTag.attr("id","selectedBill");
      			$('#billDetail').html('');
                ajaxController.ajax({
        	        method: "get",
        	        url:  _base+"/priceFailed/getFailedBillDetail/"+pTag.find(":hidden[name='id']").val(),
        	        dataType: "json",
                    processing: true,
        	        async: false,
        	        message: "正在加载数据..",
        	        success: function (data) {
        	        	 var failBillInfo = JSON.parse(data.data.failPacket);
        	        	 $.each(failBillInfo,function(key,value){
        	        		var html = '<tr>';
        	        		html += '<td class="left-border" name="billkeys" width="40%">'+key+'</td>';
        	        		html += '<td name="billValues">'+value+'</td>';
        	        		html += '</tr>';
        	        		$('#billDetail').append(html);
    	        		 });    
        	        	 _this._bindDblClick();
        	        }
        	    });
      			$('.table-pop').show();
      		});
      	},
      	_bindDblClick:function(){
      		$("td[name=billValues]").dblclick(function(){
      			 if($(this).find('input').length==0){
      				var value = $(this).html(); 
      				$(this).html("<input type='text' class='int-medium-float bill-values' value='"+value+"'>");
         			 $(".bill-values").focus().bind("blur",function() {
         	            var editval = $(this).val();               
         	            $(this).parent().html(editval);
         			 })
      			 }
      		});
      	},
    	_getServiceType: function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getServiceType', function(data){
                $("#serviceType").empty();
				$.each(data,function(index, item) {
					// 循环获取数据
					$("#serviceType").append('<option cid="'+item.id+'" value="'+item.paramCode+'">'+item.paramName+'</option>');
				});
    		});
    	},
    	_getFailCode:function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getFailCode', function(data){
                $("#failCode").append("<option value=''>请选择</option>");
				$.each(data,function(index, item) {
					// 循环获取数据
					$("#failCode").append('<option cid="'+item.id+'" value="'+item.paramCode+'">'+item.paramName+'</option>');
				});
    		});
    	},
    	_checkAll: function() {
			$('input[type=checkbox][name=bsn]').prop("checked",$("#checkAll").prop("checked"));
         },
    	_searchBtnClick: function(){
    		var _this = this;
    		var url = _base+"/priceFailed/getList";
    		var data = $("#queryForm").serializeArray();
    		//分页
    		$("#pagination-ul").runnerPagination({
	 			url: url,
	 			method: "POST",
	 			dataType: "json",
                processing: true,
	            data:data,
	           	pageSize: PriceFailedBillListPager.DEFAULT_PAGE_SIZE,
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
    		var billKeys = new Array();
    		var billValues = new Array();
    		$('td[name=billkeys]').each(function(i){
                billKeys[i] = $(this).html();
			});
    		$('td[name=billValues]').each(function(i){
    			billValues[i] = $(this).html();
			});

    		var billInfo = new Object();
    		$.each(billKeys,function (i,key) {
                billInfo[key] = billValues[i];
            });
    		var id = $('#selectedBill').find(":hidden[name='id']").val();
    		var data = new Object();
    		data.id = id;
    		data.failPacket = JSON.stringify(billInfo);
    		var _this = this;
    		ajaxController.ajax({
    	        method: "POST",
    	        url:  _base+"/priceFailed/resendEditBill",
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
    	_resendBill:function(){
    		var _this = this;
    		var billIdList = [];
    		$('input:checkbox[name=bsn]:checked').each(function(i){
    			var pTag = $(this).parents("[name='failedList']");
                billIdList.push(pTag.find(":hidden[name='id']").val());
			});
    		if(billIdList.length==0){
    			Dialog({
                    content: "请选择数据",
                    ok:function(){
                        this.close();
                        return;
                    }
                }).showModal();
    			return;
    		}
    		ajaxController.ajax({
				url : _base+"/priceFailed/batchResendBill",
				type : "post",
				dataType: "json",
                processing: true,
    	        async: false,
    	        message: "正在加载数据..",
    	        data: {"bills":JSON.stringify(billIdList)},
				success : function(data) {
					if(data){
                        Dialog({
                            content: data.statusInfo,
                            ok:function(){
                                this.close();
                            }
                        }).showModal();
                        _this._searchBtnClick();
					}
				}
			});
    	},
        _deleteBill:function(){
            var _this = this;
            var billIdList = [];
            $('input:checkbox[name=bsn]:checked').each(function(i){
                var pTag = $(this).parents("[name='failedList']");
                billIdList.push(pTag.find(":hidden[name='id']").val());
            });
            if(billIdList.length==0){
                Dialog({
                    content: "请选择数据",
                    ok:function(){
                        this.close();
                        return;
                    }
                }).showModal();
                return;
            }
            ajaxController.ajax({
                url : _base+"/priceFailed/batchDeleteBill",
                type : "post",
                dataType: "json",
                processing: true,
                async: false,
                message: "正在加载数据..",
                data: {"bills":JSON.stringify(billIdList)},
                success : function(data) {
                	if(data){
                        Dialog({
                            content: data.statusInfo,
                            ok:function(){
                                this.close();
                            }
                        }).showModal();
                        _this._searchBtnClick();
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
    
    module.exports = PriceFailedBillListPager
});

