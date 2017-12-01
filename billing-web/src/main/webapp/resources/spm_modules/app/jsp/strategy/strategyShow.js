define('app/jsp/strategy/strategyShow', function (require, exports, module) {
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
    require("jquery/validate/jquery.validate.min");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var StrategyShowPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        	varTemp:$("#varTemp"),
        	detailTemp:$(".detailTemp"),
        	factorTemp:$(".factorTemp"),
        	policyAddVar:$("#policyAddVar")
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 10
        },
        //事件代理
        events: {

        },
        //重写父类
        setup: function () {
            StrategyShowPager.superclass.setup.call(this);
            this._initFunction();
        },
        _initFunction:function(){
        	 this._setPolicyVarUnit();
        	 this._setPolicyType();
        	 this._setVarType();
        	 this._setPriceType();
        	
//        	 $('input').val("");
        },
        _bindPolicyTypeChange:function(){
        	if("ENUM" == $("select[name='policyType']").val()){
        		$(".var-type-p").hide();
        		$("select.var-type").each(function(){
        			$(this).find("option[uncode='SINGLE']").prop("selected", true);
        			$(this).trigger("change");
        		});
        	}else{
        		$(".var-type-p").show();
        		this._bindFactorValueDisabled();
        		this._bindFactorValueChange();
        	}
        },
        _bindAddVar:function(){
    		var _this = this;
    		$(".addVar").bind("click",function(){
    			
    			if($(this).parents(".varList").children().length>=6){
    				var d = Dialog({
                        content: "变量名称最多添加6个",
                        ok:function(){
                            this.close();
                        }
                    });
                    d.showModal();
                    return;
    			}
        		var branch=_this.get("varTemp").clone(true);
        		$(this).parents(".varList").append(branch.show());
        		
        		$(".factors").each(function(){
        			var fbranch=_this.get("factorTemp").clone(true);
        			$(this).append(fbranch.show());
            	});
        		if("ENUM" != $("select[name='policyType']").find("option:selected").val()){
        			_this._bindFactorValueDisabled();
        			_this._bindFactorValueChange();
				}
        	})
        },
        _bindFactorValueDisabled:function(){
        	$(".detailList").find(".detailTemp").each(function(index){
        		if(index>0){
        			$(this).find("input[name='factorValue']").attr("disabled", true);
        		}
        	});
        },
        _bindFactorValueChange:function(){
        	$(".first-factors").find(".factor").each(function(index){
        		$(this).find("input[name='factorValue']").unbind("input propertychange");
        		$(this).find("input[name='factorValue']").bind("input propertychange",function(){
        			var value = $(this).val();
        			$(".detailList").find(".detailTemp").each(function(findex){
        				if(findex>0){
        					$(this).find(".factors").children().eq(index).find("input[name='factorValue']").val(value);
        				}
                	});
            	});
        	});
        },
        _bindDelVar:function(){
        	var pThis =this;
        	$(".delVar").bind("click",function(){
        		var _this=this;
        		
        		if($(".varList").children().length==1){
        			var d = Dialog({
                        content:"至少有一个变量名称",
                        ok:function(){
                            this.close;
                        },
                    });
                    d.showModal();
        		}else{
        			var d = Dialog({
                        content:"确定删除？定价价格对应的列也会被删除",
                        ok:function(){
                        	var parent = $(_this).parents(".varTemp");
                    		var index = $(parent).index();
                    		if(index==0){
                    			var branch=pThis.get("policyAddVar").clone(true);
                    			$(".varList").children().eq(index+1).find(".tab-form-name").html("<i>*</i>请确定变量名称");
                    			$(".varList").children().eq(index+1).find(".add-policy-var").append(branch.show());
                    		}
                    		$(parent).remove();
                    		
                    		$(".factors").each(function(){
                    			if(index==0){
                    				$(this).children().eq(index+1).find(".tab-var-name").addClass("tab-form-name");
                        		}
                    			$(this).children().eq(index).remove();
                        	});
                    		if("ENUM" != $("select[name='policyType']").find("option:selected").val()){
                    			pThis._bindFactorValueDisabled();
                    			pThis._bindFactorValueChange();
            				}
                            this.close;
                        },
                        okValue:"确定",
                        cancel:function(){
                        	this.close;
                        },
                        cancelValue:"取消"
                    });
                    d.showModal();
        		}
        	})
        },
        _bindAddDetail:function(){
    		var _this = this;
    		$(".addDetail").bind("click",function(){
        		var branch=$("#detailTemp").clone(true);
        		$(this).parents(".detailList").append(branch.show());
        	})
        },
        _bindDelDetail:function(){
        	var _this=this;
        	$(".delDetail").bind("click",function(){
        		var this1=this;
        		$(this1).parents(".detailTemp").remove();
        	})
        },
        _bindVarNameChange:function(){
        	var _this=this;
       	 $(document).on('change',"input[name='varName']",function(){
       		 
       		var parent = $(this).parents(".varTemp");
        		var index = $(parent).index();
       		var varName = $(this).val();
        		$(".factors").each(function(){
       			var factor = $(this).children().eq(index);
       			$(factor).find('.tab-var-name').html("<i>*</i>"+varName);
       			$(factor).find("input[name='fvarName']").val(varName);
           	});
       	 })
       	 
       	 $(document).on('change',"input[name='varCode']",function(){
       		 
       		var parent = $(this).parents(".varTemp");
        		var index = $(parent).index();
       		var varName = $(this).val();
        		$(".factors").each(function(){
       			var factor = $(this).children().eq(index);
       			$(factor).find("input[name='fvarCode']").val(varName);
           	});
       	 })
       	 
       	 $(document).on('change',"select.var-unit",function(){
       		 
       		var parent = $(this).parents(".varTemp");
          		var index = $(parent).index();
         		var unitName = $(this).val();
         		var option = $(this).find("option:selected");
         		var unitId = $(option).attr("unid");
         		var uncode = $(option).attr("uncode");
         		var unname = $(option).attr("unname");
          		$(".factors").each(function(){
         			var factor = $(this).children().eq(index);
         			$(factor).find('.tab-unit-name').html(unitName);
         			$(factor).find("input[name='fvarUnitId']").val(unitId);
         			$(factor).find("input[name='fvarUnitName']").val(unname);
         			$(factor).find("input[name='fvarUnitCode']").val(uncode);
             	});
       	 })
       	 
       	 $(document).on('change',"select.var-type",function(){
       		var intervalNums = 0;
       		var success = true;
       		$("select.var-type").each(function(){
       			if("INTERVAL"==$(this).find("option:selected").attr("uncode")){
       				intervalNums++;
       			}
       			if(intervalNums>1){
       				_this._showDialog("区间类型对多只能选择一个");
       				success = false;
       				return success;
       			}
       		});
       		if(success){
       			var parent = $(this).parents(".varTemp");
              		var index = $(parent).index();
              		var uncode = $(this).find("option:selected").attr("uncode");
              		var html = "";
              		if("ENUM" != $("select[name='policyType']").val() && "INTERVAL"==uncode){
              			html = "<p><input type='text' name='factorValueStart' class='int-mini'></p><p>~</p><p><input type='text' name='factorValueEnd' class='int-mini'></p>";
              		}else{
              			html = "<input type='text' name='factorValue' class='int-mini'>";
              		}
              		$(".factors").each(function(){
             			var factor = $(this).children().eq(index);
             			var p = $(factor).find(".factor-value-p");
             			$(factor).find("input[name='fvarType']").val(uncode);
             			$(p).html(html);
                 	});
              		if("ENUM" != $("select[name='policyType']").find("option:selected").val()){
            			_this._bindFactorValueDisabled();
            			_this._bindFactorValueChange();
    				}
       		}else{
       			$(this).find("option[uncode='SINGLE']").prop("selected", true);
       		}
       	 })
        },
        _setPolicyVarUnit: function() {
    		$.ajax({
				url : _base + '/param/getPolicyVarUnit',
				type : "post",
				async : true,
				dataType : "json",
				timeout : "10000",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					var json = eval(data);
					$('select.var-unit').each(function(index){
						var _this = this;
						$(_this)
						.append('<option unid="-1" uncode="" unname="">无</option>');
						var defaultUnitid = $(_this).attr('defaultUnitid');
						$.each(
								json,
								function(index, item) {
									var selectedStr = "";
									if(defaultUnitid==json[index].id){
										selectedStr = 'selected="selected"';
									}
									// 循环获取数据
									$(_this)
											.append('<option unid="'+json[index].id+'" uncode="'+json[index].paramCode+'" unname="'+json[index].paramName+'" '+selectedStr+'>'+json[index].paramName+'</option>');
								});
						$(_this)
								.append("<label id='accesstype_error'></label>");
					});
				}
			});
    	},
    	_setPolicyType: function() {
    		var _this = this;
    		$.ajax({
				url : _base + '/param/getPolicyType',
				type : "post",
				async : true,
				dataType : "json",
				timeout : "10000",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					var json = eval(data);
					var obj = $("select[name='policyType']");
//						$(obj).append('<option unid="-1" uncode="" unname="">无</option>');
					var defaultPolicyType = $(obj).attr('defaultPolicyType');
						$.each(
								json,
								function(index, item) {
									var selectedStr = "";
									if(defaultPolicyType==json[index].paramCode){
										selectedStr = 'selected="selected"';
									}
									// 循环获取数据
									$(obj)
											.append('<option unid="'+json[index].id+'" value="'+json[index].paramCode+'" unname="'+json[index].paramName+'" '+selectedStr+'>'+json[index].paramName+'</option>');
								});
						$(obj)
								.append("<label id='accesstype_error'></label>");
						if("ENUM" != defaultPolicyType){
							$(".var-type-p").show();
						}
				}
			});
    	},
    	_setVarType: function() {
    		$.ajax({
				url : _base + '/param/getVarType',
				type : "post",
				async : true,
				dataType : "json",
				timeout : "10000",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					var json = eval(data);
					$('select.var-type').each(function(index){
						var _this = this;
						var defaultVarType = $(_this).attr('defaultVarType');
						$.each(
								json,
								function(index, item) {
									var selectedStr = "";
									if(defaultVarType==json[index].paramCode){
										selectedStr = 'selected="selected"';
									}
									// 循环获取数据
									$(_this)
											.append('<option unid="'+json[index].id+'" uncode="'+json[index].paramCode+'" unname="'+json[index].paramName+'" '+selectedStr+'>'+"变量类型-"+json[index].paramName+'</option>');
								});
						$(_this)
								.append("<label id='accesstype_error'></label>");
					});
//					$("input[name='fvarType']").val("SINGLE");
				}
			});
    	},
    	_setPriceType: function() {
    		$.ajax({
				url : _base + '/param/getPriceType',
				type : "post",
				async : true,
				dataType : "json",
				timeout : "10000",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					var json = eval(data);
					$('select.price-type').each(function(index){
						var _this = this;
//						$(_this)
//						.append('<option unid="-1" uncode="" unname="">请选择变量单位</option>');
						var defaultPriceType = $(_this).attr('defaultPriceType');
						$.each(
								json,
								function(index, item) {
									// 循环获取数据
									var selectedStr = "";
									if(defaultPriceType==json[index].paramCode){
										selectedStr = 'selected="selected"';
									}
									$(_this)
											.append('<option unid="'+json[index].id+'" uncode="'+json[index].paramCode+'" unname="'+json[index].paramName+'" '+selectedStr+'">'+json[index].paramName+'</option>');
								});
						$(_this)
								.append("<label id='accesstype_error'></label>");
					});
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

    module.exports = StrategyShowPager;
});
