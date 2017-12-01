define('app/jsp/element/priceElementAdd', function (require, exports, module) {
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
    var PriceElementAddPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        	elementTemp:$("#elementTemp"),
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
        	"change #tradeType":"_changeSelect",
        	"change #mainPro":"_changeMainProSelect",
//        	"change #subPro":"_changeSpecTypeList",
        	"click #element-submit":"_saveElement"
        },
        //重写父类
        setup: function () {
        	PriceElementAddPager.superclass.setup.call(this);
        	this._initFunction();
        },
        _initFunction:function(){
       	 this._bindPolicySelect();
         this._initTradeSelect();
         this._initCycleType();
         this._initBillingMode();
         this._bindAddElement();
         this._bindDelElement();
       	 $('input').val("");
       },
        _initTradeSelect:function(){
        	var this_ = this;

			$.ajax({
				type : "post",
				processing : false,
				url : _base + "/product/getTradeType",
				dataType : "json",
				data : {},
				message : "正在加载数据..",
				success : function(data) {
					var d = data.data.paramList;
					$.each(d, function(index, item) {
						var paramName = d[index].paramName;
						var paramCode = d[index].paramCode;
						var paramId= d[index].id;
						$("#tradeType").append('<option unid="'+paramId+'" uncode="'+paramCode+'" unname="'+paramName+'">'+paramName+'</option>');
					})

				}
			});

        },
        _changeSpecTypeList:function(){
        	var this_ = this;

			$.ajax({
				type : "post",
				processing : false,
				url : _base + "/priceElement/getSpecTypeList",
				dataType : "json",
				data : {categoryId:$("#mainPro option:selected").attr("unid")},
				message : "正在加载数据..",
				success : function(data) {
					$(".spec-type-name").each(function(){
						var _this = this;
						$(this).empty();
						$(this).append('<option unid="-1" uncode="" unname="">请选择</option>');
						$.each(data, function(index, item) {
							var paramName = data[index].specTypeName;
							var paramCode = data[index].specTypeId;
							var paramId= data[index].id;
							$(_this).append('<option unid="'+paramId+'" uncode="'+paramCode+'" unname="'+paramName+'">'+paramName+'</option>');
						});
					})
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
											.append('<option unid="'+json[index].id+'" uncode="'+json[index].paramCode+'" unname="'+json[index].paramName+'">'+json[index].paramName+'</option>');
								});
						$(_this)
								.append("<label id='accesstype_error'></label>");
					});

				}
			});
        },
        _initBillingMode:function(){
        	var this_ = this;

			$.ajax({
				type : "post",
				processing : false,
				url : _base + "/param/getBillingMode",
				dataType : "json",
				data : {},
				message : "正在加载数据..",
				success : function(data) {
					var json = eval(data);
					$('#mode').each(function(index){
						var _this = this;
						$.each(
								json,
								function(index, item) {
									// 循环获取数据
									$(_this)
											.append('<option unid="'+json[index].id+'" uncode="'+json[index].paramCode+'" unname="'+json[index].paramName+'">'+json[index].paramName+'</option>');
								});
						$(_this)
								.append("<label id='accesstype_error'></label>");
					});

				}
			});
        },
        _bindPolicySelect:function(){
        	var _this = this;
    		$(".policy-select").bind("click",function(){
    			var _thisObj = this;
    			$("#policyList").show();
    			$("#pagination-ul").runnerPagination({
                    url: _base+"/strategy/getList",
                    method: "POST",
                    dataType: "json",
                    processing: true,
                    data : {
   	            	 "related" : "1"
	            	 },
                    pageSize: PriceElementAddPager.DEFAULT_PAGE_SIZE,
                    visiblePages:5,
                    message: "正在为您查询数据..",
                    render: function (data) {
                        if(data&&data.length>0){
                            var template = $.templates("#strategyListTemple");
                            var htmlOut = template.render(data);
                            $("#strategyData").html(htmlOut);
                            _this._bindSelectPolicy(_thisObj);
                        }else{
                            $("#strategyData").html("未搜索到信息");
                        }
                    }
                });
        	})
        },
        _bindSelectPolicy:function(elementObj){
        	$(".view-strategy").bind("click", function(){
        		var policyId = $(this).attr("policyId");
        		var policyName = $(this).attr("policyName");
        		$(elementObj).parent().find("input[name='policyName']").val(policyName);
        		$(elementObj).parent().find("input[name='policyId']").val(policyId);
        		$('#policyList').hide();
        	})
        },
        _bindAddElement:function(){
    		var _this = this;
    		$("#addElement").bind("click",function(){
    			
        		var branch=_this.get("elementTemp").clone(true);
        		$("#elementList").append(branch.show());
        	})
        },
        _bindDelElement:function(){
        	$(".delElement").bind("click",function(){
        		$(this).parents(".elementTemp").remove();
        	})
        },
        _changeSelect:function(){
        	$("#mainPro").html("");
        	$("#mainPro").append('<option value="" unid="-1">请选择</option>');
        	if($("#tradeType option:selected").val()!=""){
        		var parentId=$("#tradeType option:selected").attr("unid");
        		$.ajax({
    				type : "post",
    				processing : false,
    				url : _base + "/product/getMainProduct",
    				dataType : "json",
    				data : {parentId:parentId},
    				message : "正在加载数据..",
    				success : function(data) {
    					var d = data.data.paramList;
    					$.each(d, function(index, item) {
    						var paramName = d[index].paramName;
    						var paramCode = d[index].paramCode;
    						var paramId= d[index].id;
    						$("#mainPro").append('<option unid="'+paramId+'" uncode="'+paramCode+'" unname="'+paramName+'">'+paramName+'</option>');
    						
    					})

    				}
    			});
        	}
        },
        _changeMainProSelect:function(){
        	$("#subPro").html("");
        	$("#subPro").append('<option value="" unid="-1">请选择</option>');
        	var _this = this;
        	if($("#mainPro option:selected").val()!=""){
        		var parentId=$("#mainPro option:selected").attr('uncode');
        		$.ajax({
    				type : "post",
    				processing : false,
    				url : _base + "/priceElement/getSubsProduct",
    				dataType : "json",
    				data : {mainProCode:parentId},
    				message : "正在加载数据..",
    				success : function(data) {
    					$.each(data, function(index, item) {
    						var paramName = data[index].categoryName;
    						var paramId= data[index].categoryId;
    						$("#subPro").append('<option unid="'+paramId+'" unname="'+paramName+'">'+paramName+'</option>');
    					})

    				}
    			});
        		
        		this._changeSpecTypeList();
        	}
        },
        _saveElement:function(){
        	
        	var msg = "";
        	var _this = this;
        	var success = true;
        	
        	if($("#tradeType").find("option:selected").attr("unid")=="-1"){
    			msg = "请选择行业类型";
    			_this._showDialog(msg);
    			success = false;
    			return success;
    		}
        	if($("#mainPro").find("option:selected").attr("unid")=="-1"){
    			msg = "请选择主产品目录";
    			_this._showDialog(msg);
    			success = false;
    			return success;
    		}
        	if($("#subPro").find("option:selected").attr("unid")=="-1"){
    			msg = "请选择子产品目录";
    			_this._showDialog(msg);
    			success = false;
    			return success;
    		}
        	if($("#mode").find("option:selected").attr("unid")=="-1"){
    			msg = "请选择计费模式";
    			_this._showDialog(msg);
    			success = false;
    			return success;
    		}
        	if($("input[name='modeCode']").val()=="" || $("input[name='modeCode']").val()==null ){
    			msg = "请输入计费模式编码";
    			_this._showDialog(msg);
    			success = false;
    			return success;
    		}
        	if($("#cycle").find("option:selected").attr("unid")=="-1"){
    			msg = "请选择计费周期";
    			_this._showDialog(msg);
    			success = false;
    			return success;
    		}
        	$('#elementList .elementTemp').each(function(){
        		if($(this).find("select").find("option:selected").attr("unid")=="-1"){
        			msg = "未选择元素名";
        			_this._showDialog(msg);
        			success = false;
        			return success;
        		}
        		if($(this).find("input[name='policyName']").val()==""){
        			msg = "请选择定价策略";
        			_this._showDialog(msg);
        			success = false;
        			return success;
        		}
        	});
        	if(!success){
        		return;
        	}
        	
        	var elements = [];
        	var specTypeIds = [];
        	$("#elementList").find(".elementTemp").each(function(){
        		if(specTypeIds.indexOf($(this).find("select").find("option:selected").attr("unid"))<0){
        			specTypeIds.push($(this).find("select").find("option:selected").attr("unid"));
    			}else{
    				msg = "元素名不能重复";
    				_this._showDialog(msg);
    				success = false;
        			return success;
    			}
        		var element = {
        				billingCycle : $("#cycle").find("option:selected").attr("uncode"),
        				specTypeId : $(this).find("select").find("option:selected").attr("uncode"),
        				specTypeName : $(this).find("select").find("option:selected").attr("unname"),
        				pricePolicy : $(this).find("input[name='policyId']").val(),
        				pricePolicyName : $(this).find("input[name='policyName']").val(),
    	        	};
        		elements.push(element);
        	});
        	if(!success){
        		return;
        	}
        	
        	var categoryId = $("#subPro").find("option:selected").attr("unid");
        	ajaxController.ajax({
	             type: "post",
	             dataType : "json",
	             url: _base+"/priceElement/checkCategoryId",
	             processing: true,
	             message: "正在加载，请等待...",
	             data : {
	            	 "categoryId" : $("#subPro").find("option:selected").attr("unid")
	            	 },
	             success: function(data){
	                 if(data.data.responseHeader.isSuccess){
	                	 ajaxController.ajax({
	        	             type: "post",
	        	             dataType : "json",
	        	             url: _base+"/priceElement/saveElement",
	        	             processing: true,
	        	             message: "正在加载，请等待...",
	        	             data : {
	        	            	 "tradeCode" : $("#tradeType").find("option:selected").attr("uncode"),
	        	            	 "mainProductId" : $("#mainPro").find("option:selected").attr("uncode"),
	        	            	 "mainProductName" : $("#mainPro").find("option:selected").attr("unname"),
	        	            	 "categoryId" : $("#subPro").find("option:selected").attr("unid"),
	        	            	 "billingCycle" : $("#cycle").find("option:selected").attr("uncode"),
	        	            	 "billingMode" : $("#mode").find("option:selected").attr("uncode"),
	        	            	 "modeCode" : $("input[name='modeCode']").val(),
	        	            	 "elementListStr" : JSON.stringify(elements)
	        	            	 },
	        	             success: function(data){
	        	            	 var d = Dialog({
		               					content:data.data.responseHeader.resultMessage,
		               					okValue:"确定",
		               					ok:function () {
		               						this.close();
		               						/*$(that).focus();
		               						return;*/
		               					 window.location.href=_base+"/priceElement/toPriceElementList";
		               					}
		               				});
		        	            	d.showModal();
	        	             }
	        	         });
	                 }else{
	                	 var d = Dialog({
	                         content:'子产品已存在，不能重复添加',
	                         ok:function(){
	                             this.close();
	                         }
	                     });
	                     d.showModal();
	                 }
	             }
	         });
        },
        _showDialog:function(msg){
    		var d = Dialog({
                content:msg,
                ok:function(){
                    this.close();
                }
            });
            d.showModal();
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

    module.exports = PriceElementAddPager;
});
