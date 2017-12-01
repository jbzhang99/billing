define('app/jsp/element/priceElementList', function (require, exports, module) {
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
    require("app/util/ajaxfileupload");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var PriceElementPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
        	"click #submit":"_submitFrom",
        	"click #elementAdd":"_addElement"
        },
        //重写父类
        setup: function () {
        	PriceElementPager.superclass.setup.call(this);
        	this._initCycleType();
        	this._submitFrom();
        },
        _submitFrom:function(){
        	var _this = this;
//            $("#pagination-ul").runnerPagination({
//                url: _base+"/priceElement/getList",
//                method: "POST",
//                dataType: "json",
//                processing: true,
//                data : $('#queryForm').serializeArray(),
//                pageSize: PriceElementPager.DEFAULT_PAGE_SIZE,
//                visiblePages:5,
//                message: "正在为您查询数据..",
//                render: function (data) {
//                    if(data&&data.length>0){
//                        var template = $.templates("#elementListTemple");
//                        var htmlOut = template.render(data);
//                        $("#elementData").html(htmlOut);
//                        _this._bindElement();
//                    }else{
//                        $("#elementData").html("未搜索到信息");
//                    }
//                }
//            });
            
    		ajaxController.ajax({
			
	 			url: _base+"/priceElement/getList",
	 			method: "POST",
	 			dataType: "json",
	 			processing: true,
	 			data : $('#queryForm').serializeArray(),
	            message: "正在为您查询数据..",
	            success: function (data) {
	            	var data = data.data.result;
	            	if(data&&data.length>0){
	                    var template = $.templates("#elementListTemple");
	                    var htmlOut = template.render(data);
	                    $("#elementData").html(htmlOut);
	                    _this._bindElement();
	                }else{
	                    $("#elementData").html("未搜索到信息");
	                }
	            }
			});
        },
        _initCycleType:function(){
        	var this_ = this;

			$.ajax({
				type : "post",
				processing : false,
				url : _base + "/param/getCycleType",
				dataType : "json",
				data : {},
				message : "正在加载数据..",
				success : function(data) {
					var json = eval(data);
					$('#cycle').each(function(index){
						var _this = this;
						$.each(
								json,
								function(index, item) {
									// 循环获取数据
									$(_this)
											.append('<option unid="'+json[index].id+'" value="'+json[index].paramCode+'" unname="'+json[index].paramName+'">'+json[index].paramName+'</option>');
								});
						$(_this)
								.append("<label id='accesstype_error'></label>");
					});

				}
			});
        },
        _bindElement:function(){
        	var _this = this;
        	$(".view-element").bind("click", function(){
        		var categoryId =  $(this).parents(".element-record").find("input[name='categoryId']").val();
        		window.location.href = _base+"/priceElement/toElementShow?categoryId="+categoryId;
			});
        	$(".edit-element").bind("click", function(){
        		var categoryId =  $(this).parents(".element-record").find("input[name='categoryId']").val();
			    window.location.href = _base+"/priceElement/toElementUpdate?categoryId="+categoryId;
			});
        	$(".del-element").bind("click", function(){
        		var mainProductId =  $(this).parents(".element-record").find("input[name='mainProductId']").val();
        		var categoryId =  $(this).parents(".element-record").find("input[name='categoryId']").val();
        		var d = Dialog({
                    content:"确定删除？",
                    ok:function(){
                    	$.ajax({
        					type : "post",
        					processing : false,
        					url : _base+"/priceElement/delElement",
        					dataType : "json",
        					data : {
        						"mainProductId":mainProductId,
        						"categoryId":categoryId
        					},
        					message : "处理中..",
        					success : function(data) {
    							var d = Dialog({
    		                         content:data.statusInfo,
    		                         ok:function(){
    		                        	 _this._submitFrom();
    		                             this.close();
    		                         }
    		                     });
    		                     d.showModal();
        					}
        				});
                        this.close;
                    },
                    okValue:"确定",
                    cancel:function(){
                    	this.close;
                    },
                    cancelValue:"取消"
                });
                d.showModal();
			});
        },
        _addElement:function(){
        	window.location.href = _base+"/priceElement/toPriceElementAdd";
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

    module.exports = PriceElementPager;
});
