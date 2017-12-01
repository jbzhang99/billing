define('app/jsp/product/definePoductList', function (require, exports, module) {
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
    
    var ff=false; 
    //定义页面组件类
    var DefineProListPager = Widget.extend({
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
           // "click #delDim":"delWeidu",
            "click #savePro":"saveProduct",
            "change #tradeType":"_changeSelect",
            "click #cancelBtn":"_goBack",
          // "blur input[name='branchCode']":"_brReapet"
        },
        //重写父类
        setup: function () {
        	DefineProListPager.superclass.setup.call(this);
        	 this._initTradeSelect();
         //  this._initFunction();
           this._bindAddBranch(); //添加分支
           this._bindDelBranch();
           this._bindDelDim();
         //  this._brReapet();
        },
        _goBack:function(){
        	window.location.href= _base + "/product/productList";
        },
        _brReapet:function(){
        	
        	
        	
        	
        	$("input[name='branchCode']").not("input:hidden").each(function(){//
        		var bArray=new Array();
        		console.log("-------");
    			$(this).parents(".branchTemp").siblings().find("input[name='branchCode']").each(function(){
        			bArray.push($(this).val());
        		})
        		//console.log(bArray);
        		if($.inArray($(this).val(), bArray)!=-1){
        			console.log("重复");
        			ff=false;
        			$("#savePro").css("color","black");
        			//bArray=new Array();
        			return false;
        			
        		}
    		
    		
    			ff=true;
    		
    	
    		
    	})
    	if(ff){
    		$("#savePro").css("color","red");
		}
        	/*$("input[name='branchCode']").bind("blur",function(){//
        		
        			$(this).parents(".branchTemp").siblings().find("input[name='branchCode']").each(function(){
            			bArray.push($(this).val());
            		})
            		
            		if($.inArray($(this).val(), bArray)!=-1){
            			console.log("重复");
            			ff=false;
            			$("#savePro").css("color","black");
            			return;
            			
            		}
        		
        		
        			ff=true;
        		
        	
        		
        	})
        	if(ff){
        		$("#savePro").css("color","red");
    		}*/
        	/*if($.inArray($(_this).val(), bArray)==-1){
    			bArray.push($(_this).val());
    		}else{
    			//var tip = $(this).attr("tip");
        		var d = Dialog({
					content:"重复的分支编码"+$(_this).val(),
					okValue:"确定",
					ok:function () {
						this.close();
						$(_this).focus();
						return;
					}
				});
				d.show();
				validateFlag = false;
				return false;
    		}*/
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
						$("#tradeType").append('<option value="' + paramCode+'|'+paramId
										+ '">' + paramName
										+ '</option>');
					})

				}
			});

        },
        _changeSelect:function(){
        	$("#mainPro").html("");
        	$("#mainPro").append('<option value="">请选择</option>');
        	if($("#tradeType option:selected").val()!=""){
        		var start=$.trim($("#tradeType  option:selected").val()).indexOf("|")+1;
        		var parentId=$("#tradeType option:selected").val().substr(start,$("#tradeType option:selected").val().length);
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
    						$("#mainPro").append('<option value="' + paramCode+'|'+paramId
    										+ '">' + paramName
    										+ '</option>');
    					})

    				}
    			});
        	}
        },
        addWeidu:function(){
        	wd++;
        	
        	
        	if(wd<=4){
        		var temp=this.get("temp").clone(true);
        		temp.find('.wdId').text(wd);
        		temp.find("input[name='dimensionSeq']").val($(this).index()+1);
            	$(".weidu").append(temp.show());
        	}else{
        		wd--;
        		var that = this;
         	   var d = Dialog({
 					content:"最多添加四个维度",
 					okValue:"确定",
 					ok:function () {
 						this.close();
 						$(that).focus();
 						return;
 					}
 				});
 				d.show();
        	  //alert("最多添加四个维度");
        	}
        	
        },
        delWeidu:function(){
        	Dialog({    width : '200px',
				height : '50px',
					content : "确定删除？删除维度,对应的分支也会被删除",
					okValue : "确定",
					ok : function() {
						wd--;
			        	if(wd<=4){
			        		var temp=this.get("temp").clone(true);
			        		temp.find('.wdId').text(wd);
			        		temp.find("input[name='dimensionSeq']").val($(this).index()+1);
			            	$(".weidu").append(temp.show());
			        	}else{
			        		var that = this;
			         	   var d = Dialog({
			 					content:"最多添加四个维度",
			 					okValue:"确定",
			 					ok:function () {
			 						this.close();
			 						$(that).focus();
			 						return;
			 					}
			 				});
			 				d.show();
			        	  //alert("最多添加四个维度");
			        	}
								},
								cancelValue : "取消",
								cancel : function() {
									this.close;
								}
							}).showModal();
        	
        	
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
        _bindDelDim:function(){
        	var _this=this;
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	$(".delDim").bind("click",function(){
        		var this1=this;
        		Dialog({   
    					content : "确定删除？删除维度,对应的分支也会被删除",
    					okValue : "确定",
    					ok : function() {
    						$(this1).parent().parent().parent().remove();
    			        	$(".weidu").find('.weidus').each(function(){
    			        		var this2=this;
    			        		//var temp=$(this2).get("temp").clone(true);
    			        		$(this2).find('.wdId').text($(this).index()+1);
    			        		$(this2).find("input[name='dimensionSeq']").val($(this).index()+1);
    			            	//$(".weidu").append(temp.show());
    			        	     });
    			        	         wd--;
    			        	
    						},
    						cancelValue : "取消",
    						cancel : function() {
    							this.close;
    						}
    					}).showModal();
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
        	var validateFlag=true;
        	var wdArr = new Array();
        	obj.tradeCode=$("#tradeType option:selected").val().substr(0,$("#tradeType").val().indexOf("|"));
        	obj.tradeName=$("#tradeType option:selected").text();
        	obj.mainProductName=$("#mainPro option:selected").text();
            obj.mainProductCode=$("#mainPro option:selected").val().substr(0,$("#mainPro").val().indexOf("|"));
           if($("#tradeType option:selected").val()=='' || $("#tradeType option:selected").val()==undefined || $("#tradeType option:selected").val()==null){
        	   var that = this;
        	   var d = Dialog({
					content:"请选择行业类型",
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
           if($("#mainPro option:selected").val()=='' || $("#mainPro option:selected").val()==undefined || $("#mainPro option:selected").val()==null){
        	   var that = this;
        	   var d = Dialog({
					content:"请选择主产品名称",
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
        	//判断字段为空，需要遍历所有input然后一个个去提示
            if(validateFlag){
            	 $(".weidu").find('.weidus').find("input").each(function(){
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
            }
            //判断维度编码重复性
           /*if(validateFlag){
        	   var dArray=new Array();
        	 
        	   $(".weidu").find('.weidus').find("input").each(function(){
           		
        		   if($(this).attr('name')=='dimensionCode'){
        			   
               		if($.inArray($(this).val(), dArray)==-1){
               			dArray.push($(this).val());
               		}else{
               			//var tip = $(this).attr("tip");
                   		var d = Dialog({
       						content:"重复的维度编码"+$(this).val(),
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
               	}
           		
           	});
        	   if(!validateFlag){
        		   return;
        	   }
           }*/
            if(validateFlag){
            	$(".weidu").find('.weidus').each(function(){
                    if($(this).find(".branchTemp").length==0){
                    	var that=this;
              		  var d = Dialog({
          				content:"维度下边必须有分支",
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
                   
                  
                    });
            }
            
            
            
            	 
           
            
            
            
            if(validateFlag){//只有验证通过的情况下才会组装数据和发送ajax请求
            	$(".weidu").find('.weidus').each(function(){
            		var wd=_this.serializeObjectToJson($(this).find("input[name!='branchName'][name!='branchCode']").serializeArray());
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
                    url: _base+"/product/addProduct",
                    processing: true,
                    message: "正在加载，请等待...",
                    data:{product:JSON.stringify(obj)},
                    success: function(data){
                    	 var that = this;
                  	   var d = Dialog({
          					content:data.data.responseHeader.resultMessage,
          					okValue:"确定",
          					ok:function () {
          						this.close();
          						/*$(that).focus();
          						return;*/
          						window.location.href=_base+"/product/productList";
          					}
          				});
                  	 d.showModal();
                    }
                });
            }
        	
        }
      
    });

    module.exports = DefineProListPager;
});