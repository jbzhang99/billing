define('app/jsp/product/viewProduct', function (require, exports, module) {
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
    var wd=1;
    //定义页面组件类
    var ViewProPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #addwd":"addWeidu",
            "click #savePro":"saveProduct",
            "click #cancelBtn":"_goBack"
           // "change #tradeType":"_changeSelect"
        },
        //重写父类
        setup: function () {
        	ViewProPager.superclass.setup.call(this);
        	 this._initTradeSelect();
        	// this._productSelect();
         //  this._initFunction();
           this._bindAddBranch(); //添加分支
           this._bindDelBranch();
           this._bindInitPage();
        },
        _goBack:function(){
        	window.location.href= _base + "/product/productList";
        },
        _bindInitPage:function(){
        	var _this=this;
        	$.ajax({
				type : "post",
				processing : false,
				url : _base+ "/product/getProduct",
				dataType : "json",
				data : {
					mainProCode:this.get('mainProId'),
					mainTag:this.get('mainTag')
					},
				message : "正在加载数据..",
				success : function(data) {
				//开始回显数据
					var dt=data.data;
				if(dt.product){
					//首先固定行业类型和主产品类型
					var product=dt.product;
					$("#tradeType").val(product.tradeCode);
					//$("#tradeType").change();
					_this._changeSelect(product.mainProductCode);
					//$("#mainPro").val(product.mainProductCode);
					$("#tradeType").attr("disabled","disabled");
					$("#mainPro").attr("disabled","disabled");
					
					//循环显示维度和分支
					
					$.each(dt.product.dimensions,function(index,e){
						var this1=this;
						var temp=_this.get("temp").clone(true);
						temp.find('.wdId').text(e.dimensionSeq);
		        		temp.find("input[name='dimensionSeq']").val(e.dimensionSeq);
		        		
		        		temp.find("input[name='dimensionName']").val(e.dimensionName);
		        		temp.find("input[name='dimensionCode']").val(e.dimensionCode);
		        		temp.find("input[name='dimensionId']").val(e.dimensionId);
		        		$(".weidu").append(temp.show());
		        		$.each(e.branchs,function(index,b){
		        			
		            		var branch=_this.get("branch").clone(true);
		            		//temp.find("input[name='dimensionSeq']").val(e.dimensionSeq);
		            		branch.find("input[name='branchId']").val(b.branchId);
		            		branch.find("input[name='branchName']").val(b.branchName);
		            		branch.find("input[name='branchCode']").val(b.branchCode);
		            		
		            		$(temp).append(branch.show());
		            		
							
						});
					});
					
					
					
					
					
					
					
					
				}	
					
					
					
				}
					
			});
        	
        	
        	
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
						$("#tradeType").append('<option pid="'+paramId+'" value="' + paramCode
										+ '">' + paramName
										+ '</option>');
					})
					
					
					
				}
				
			});

        },
        _changeSelect:function(proCode){
        	$("#mainPro").html("");
        	$("#mainPro").append('<option value="">请选择</option>');
        	if($("#tradeType option:selected").val()!=""){
        		//var start=$.trim($("#tradeType  option:selected").val()).indexOf("|")+1;
        		var parentId=$("#tradeType option:selected").attr('pid');
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
    						$("#mainPro").append('<option pid="'+paramId+'" value="' + paramCode
    										+ '">' + paramName
    										+ '</option>');
    					})
    					$("#mainPro").val(proCode);
    				}
    			});
        	}
        },
        _initFunction:function(){
        	console.log($(".weidu").find("input").serializeArray());
        	console.log($(".weidu").find("nobr").text());
        },
        addWeidu:function(){
        	wd++;
        	if(wd<=4){
        		var temp=this.get("temp").clone(true);
        		temp.find('.wdId').text(wd);
        		temp.find("input[name='dimensionSeq']").val(wd);
            	$(".weidu").append(temp.show());
        	}else{
        		alert("最多添加四个维度");
        	}
        	
        },
        _bindAddBranch:function(){
        	var _this=this;
        	
        	$(".addBr").bind("click",function(){
        		var this1=this;
        		var branch=_this.get("branch").clone(true);
        		$(this1).parent().parent().parent().append(branch.show());
        	})
        },
        _bindDelBranch:function(){
        	var _this=this;
        	$(".delBranch").bind("click",function(){
        		var this1=this;
        		$(this1).parent().parent().parent().remove();
        	})
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
		},
        saveProduct:function(){
        	//首先需要将维度和分支用obj给圈起来
        	var _this=this;
        	var obj={};
        	var wdArr = new Array();
        	obj.tradeName=$("#tradeType option:selected").text();
        	obj.tradeCode=$("#tradeType option:selected").val()
        	obj.mainProductName=$("#mainPro option:selected").text();
            obj.mainProductCode=$("#mainPro option:selected").val();
        	//判断字段为空，需要遍历所有input然后一个个去提示
            var validateFlag=true;
            $(".weidu").find('.weidus').find("input[name!='branchId']").each(function(){
            	var that = this;
            	if($(this).val()==""){
            		var tip = $(this).attr("tip");
            		var d = Dialog({
						content:tip,
						okValue:"确定",
						ok:function () {
							this.close();
							$(that).focus();
							return;
						}
					});
					d.show();
					validateFlag = false;
					return false;
            	}
            })
            if(validateFlag){//只有验证通过的情况下才会组装数据和发送ajax请求
            	$(".weidu").find('.weidus').each(function(){
            		var wd=_this.serializeObjectToJson($(this).find("input[name!='branchName'][name!='branchCode'][name!='branchId']").serializeArray());
            		var branchArr=new Array();
            		$(this).find(".branchTemp").each(function(){
            			var fz=_this.serializeObjectToJson($(this).find("input[name!='dimensionName'][name!='dimensionCode'][name!='dimensionSeq']").serializeArray());
            			branchArr.push(fz);
            		})
            		wd.branchs=branchArr;
            		wdArr.push(wd);
            	});
            	obj.dimensions=wdArr;
                console.log(JSON.stringify(obj));
            	ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/product/updateProduct",
                    processing: true,
                    message: "正在加载，请等待...",
                    data:{product:JSON.stringify(obj)},
                    success: function(data){
                   	 console.log("ok");
                    }
                });
            }
        	
        }
      
    });

    module.exports = ViewProPager;
});
