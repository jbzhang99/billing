define('app/jsp/coupon/addCoupon', function (require, exports, module) {
    'use strict';
    var $ = require('jquery'), 
	Widget = require('arale-widget/1.2.0/widget'), 
	Calendar = require('arale-calendar/1.1.2/index-debug'), 
	Select = require('arale-select/0.11.1/index'), 
	Dialog = require("artDialog/src/dialog"),
	AjaxController = require('opt-ajax/1.0.0/index');
	require("jsviews/jsrender.min");
	require("jsviews/jsviews.min");
	require("bootstrap-paginator/bootstrap-paginator.min");
	require("twbs-pagination/jquery.twbsPagination.min");
	require("opt-paging/aiopt.pagination");
	require("app/util/jsviews-ext");
	
	
    //定义页面组件类
    var AddCouponPager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 5
    	},
    	
    	//事件代理
    	events: {
    		//key的格式: 事件+空格+对象选择器;value:事件方法
    		"click #SAVEP_BTN":"_addCoupon",
    		"change #useType":"_changeType",
    		"click #BACK_BTN":"_goBack",
    		//"click #showm":"_showmodel"
    		"click #CLOSE_POP":"_closeModel",
    		"click #CLOSE_BTN":"_closeModel",
    		"click #BIND_BTN":"_bindProduct",
    		"click #BTN_SEARCH":"_searchProduct",
    		
    		
        },
      
		_initPage: function(){
			//左侧菜单选中样式
      		$("#mnu_coupon_mng").addClass("current");
      		//面包屑导航
      		setBreadCrumb("优惠券管理","添加优惠券");
      		
      	},
    	//重写父类
    	setup: function () {
    		AddCouponPager.superclass.setup.call(this);
    	//	this._bindEvents();
    		//初始化执行搜索,查询可销售产品
    		this._couponTypeClick();
    		this._showmodel();
    		this._initPage();
    		this._bindCalendar();
    		this._getUseSeclec();
    		this._getRuleSeclec();
    		this._getModelSeclec();
    		this._getReduceSeclec();
    		this._bindInputLimit();
    		this._bindInputLimit1();
    		this._changeType();
    	//	this._searchProduct();
    	},
    	_showmodel : function() {
    		var _th=this;
			$("#showm").bind("click", function() {
				var _this = this;
				$('#popModel').show();
				$("#productName").val("");
				$("#productId").val("");
				$('input[type=radio][name=pp_checkbox]').removeAttr("checked");
				_th._searchProduct();

			})
		},
		_closeModel:function(){
			$('#popModel').hide();
			/*$("#proName").text("");
		    $("#prodId").val("");*/
		   
		},
    	// 日期
		_bindCalendar : function() {
			
			new Calendar({
				trigger : '#activeTime'
			});
			new Calendar({
				trigger : '#inactiveTime'
			});
			$(".icon-calendar").each(function () {
				new Calendar({
					trigger: $(this),
					output:$(this).parent().siblings("input"),
					align: {
						selfXY: [0, 0],
						baseElement:$(this).parent().siblings("input"),
						baseXY: [0, '100%']
					}
				});
			});
		},
		_bindProduct:function(){
			var _this=this;
			 
		     var checked=$('input[type=radio][name=pp_checkbox]:checked').val();
		     if(checked==null){
		    	 var that = this;
	        		var d = Dialog({
						content:"请选择要指定的子目录",
						okValue:"确定",
						ok:function () {
							this.close();
							$(that).focus();
							return;
						}
					});
					d.show();
					return;
		     }
		     $("#proName").text("");
		     $("#prodId").val("");
			 $("#proName").append('<i>&nbsp;'+"已选择:"+'</i><span id="chioce">'+$.trim($('input[type=radio][name=pp_checkbox]:checked').parent().parent('tr').children('td').get(2).innerHTML)+'</span>');
			 $("#prodId").val($('input[type=radio][name=pp_checkbox]:checked').parent().parent('tr').children('td').get(1).innerHTML);
			
			$('#popModel').hide();
			$("#proName").show();
		},
		 _bindProductClick1: function() {
        	 var _this = this;
        	 
        	/* $('input[type=radio][name=pp_checkbox]').bind('click', function(){
        		
        		    $("#proName").text("");
     				$("#proName").append('<i>&nbsp;'+"已选择:"+'</i><span id="chioce">'+$.trim($(this).parent().parent('tr').children('td').get(2).innerHTML)+'</span>');
     				$("#prodId").val($.trim($(this).parent().parent('tr').children('td').get(1).innerHTML));
        	 });*/
         },
         _couponTypeClick: function() {
        	 var _this = this;
        	 
        	 $('input[type=radio][name=couponType]').bind('click', function(){
        		 var couponType=$("input[name='couponType']:checked").val();
     			if(couponType==="ALL"){
     				
     				$("#bindP").hide();
     				$("#proName").text("");
     				$("#prodId").val("");
     			}else if(couponType==="APPOINT"){
     				$("#bindP").show();
     			}
        	 });
         },
		_changeType:function(){
			//对使用条件进行隐藏和显示
			var useType=$("#useType").val();
			if(useType==="FULLREDUCE"){
				//做显示操作
				$("#reach_unit").show();
				$("#dhao").show();
				$("#reachAmount").show();
				$("#man").show();
			}else{
				//做隐藏操作
				$("#reach_unit").hide();
				$("#dhao").hide();
				$("#reachAmount").hide();
				$("#man").hide();
			}
			
		},
		_searchProduct:function(){
			var _this = this;
			//var url =  _base+ "/coupon/getProductList";
			var url =  _base+ "/coupon/getMainProList";
			$("#pagination-ul").runnerPagination(
							{
								url : url,
								method : "POST",
								dataType : "json",
								processing : true,
								data : {
									productName:$("#productName").val(),
									productId:$("#productId").val()
								},
								pageSize : AddCouponPager.DEFAULT_PAGE_SIZE,
								visiblePages :5,
								message : "正在为您查询数据..",
								render : function(data) {
									if (data != null
											&& data != 'undefined'
											&& data.length > 0) {
										var template = $.templates("#listDataTmpl");
										var htmlOutput = template.render(data);
										$("#listData").html(htmlOutput);
										//_this._bindProductClick1();
										
									} else {
										$("#listData").html(null);
										$("#listData").html("没有搜索到相关信息");
									}
								}
							});
		},
    	_getUseSeclec:function(){
    			var this_=this;
    				$.ajax({
    					type : "post",
    					processing : false,
    					url : _base+ "/coupon/getSelect",
    					dataType : "json",
    					data : {
    						paramType:"REDUCE_TYPE"
    						},
    					message : "正在加载数据..",
    					success : function(data) {
    						var d=data.data.paramList;
    						$.each(d,function(index,item){
    							var paramName = d[index].paramName;
    							var paramCode = d[index].paramCode;
    							$("#useType").append('<option value="'+paramCode+'">'+paramName+'</option>');
    						})
    					}
    				});
    	},
    	_getRuleSeclec:function(){
			var this_=this;
				$.ajax({
					type : "post",
					processing : false,
					url : _base+ "/coupon/getSelect",
					dataType : "json",
					data : {
						paramType:"FULL_UNIT"
						},
					message : "正在加载数据..",
					success : function(data) {
						var d=data.data.paramList;
						$.each(d,function(index,item){
							var paramName = d[index].paramName;
							var paramCode = d[index].paramCode;
							$("#reach_unit").append('<option value="'+paramCode+'">'+paramName+'</option>');
						})
					}
				});
    	},
    	_getModelSeclec:function(){
			var this_=this;
				$.ajax({
					type : "post",
					processing : false,
					url : _base+ "/coupon/getSelect",
					dataType : "json",
					data : {
						paramType:"REDUCE_MODEL"
						},
					message : "正在加载数据..",
					success : function(data) {
						var d=data.data.paramList;
						$.each(d,function(index,item){
							var paramName = d[index].paramName;
							var paramCode = d[index].paramCode;
							$("#model_unit").append('<option value="'+paramCode+'">'+paramName+'</option>');
						})
					}
				});
    	},
    	_getReduceSeclec:function(){
			var this_=this;
				$.ajax({
					type : "post",
					processing : false,
					url : _base+ "/coupon/getSelect",
					dataType : "json",
					data : {
						paramType:"REDUCE_UNIT"
						},
					message : "正在加载数据..",
					success : function(data) {
						var d=data.data.paramList;
						$.each(d,function(index,item){
							var paramName = d[index].paramName;
							var paramCode = d[index].paramCode;
							$("#dst_unit").append('<option value="'+paramCode+'">'+paramName+'</option>');
						})
					}
				});
    	},
    	_bindInputLimit: function(){
    		$(".price-input").bind('input propertychange', function (event) {
        	    var $amountInput = $(this);
        	    //响应鼠标事件，允许左右方向键移动 
        	    event = window.event || event;
        	    if (event.keyCode == 37 | event.keyCode == 39) {
        	        return;
        	    }
        	    //先把非数字的都替换掉，除了数字和. 
        	    $amountInput.val($amountInput.val().replace(/[^\d]/g, "").replace(/^0*/g, "").
        	        //只允许一个小数点              
        	        replace(/^\./g, ""));
        	            });
        	$(".price-input").on('blur', function () {
        	    var $amountInput = $(this);
        	    //最后一位是小数点的话，移除
        	    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
        	});
    	},
    	_getLength:function(str){
      		var byteLen=0,len=str.length;
      	　　if(str){
      	　　　　for(var i=0; i<len; i++){
      	　　　　　　if(str.charCodeAt(i)>255){
      	　　　　　　　　byteLen += 2;
      	　　　　　　}
      	　　　　　　else{
      	　　　　　　　　byteLen++;
      	　　　　　　}
      	　　　　}
      	　　　　return byteLen;
      	　　}
      	　　else{
      	　　　　return 0;
      	　　}
      	},
      	_goBack:function(){
			var this_=this;
			
			window.location.href=_base+"/coupon/couponList";
		},
    	_bindInputLimit1: function(){
    		$(".price-input1").bind('input propertychange', function (event) {
        	    var $amountInput = $(this);
        	    //响应鼠标事件，允许左右方向键移动 
        	    //响应鼠标事件，允许左右方向键移动 
        	    event = window.event || event;
        	    if (event.keyCode == 37 | event.keyCode == 39) {
        	        return;
        	    }
        	    //先把非数字的都替换掉，除了数字和. 
        	    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").replace(/^0\d*/g, "0").
        	        //只允许一个小数点              
        	        replace(/^\./g, "").replace(/\.{2,}/g, ".").
        	        //只能输入小数点后两位
        	        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d\d\d).*$/, '$1$2.$3'));
        	            });
        	$(".price-input").on('blur', function () {
        	    var $amountInput = $(this);
        	    //最后一位是小数点的话，移除
        	    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
        	});
    	},
    	
    	_addCoupon:function(){
    		var this_=this;
    		var validateFlag=true;
    		var conditionValue;
    		//var reachunit;
    		var couponConType=$("#useType").val();
    		var reachAmount;
    		if($("#useType").val()=="IMREDUCE"){
    			conditionValue=$("#dst_value").val()+$("#dst_unit").find("option:selected").text()
    			reachAmount="0";
    		}else{
    			reachAmount=$("#reachAmount").val();
    			conditionValue="满"+$("#reachAmount").val()+$("#reach_unit").find("option:selected").text()+","+$("#model_unit").find("option:selected").text()+$("#dst_value").val()+$("#dst_unit").find("option:selected").text();
    		}
    		var couponType=$("input[name='couponType']:checked").val();
    		var productId=$("#prodId").val();
    	
    		var couponVale=$("#dst_value").val()+$("#dst_unit").find("option:selected").text();
    		var couponName=$("#couponName").val();
    		var couponAmount=$("#couponAmount").val();
    		var couponValue="";
    		var activeTime=$("#activeTime").val();
    		var inactiveTime=$("#inactiveTime").val();
    		var reachUnit=$("#reach_unit").val();
    		var dstTypeUnit=$("#model_unit").val();
    		var dstUnit=$("#dst_unit").val();
    		var dstValue=$("#dst_value").val();
    		var dstUnit=$("#dst_unit").val();
    		if(validateFlag){
    			if(couponType==="APPOINT"&&$.trim($("#prodId").val())===""){
    				var that = this;
            		var d = Dialog({
						content:"请选择要指定的子目录",
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
    		if(validateFlag){
    			var couponLen=this._getLength(couponName);
            	if(couponLen>50){
            		var that = this;
            		var d = Dialog({
						content:"优惠券名称不能超过50个字符",
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
    			$("#rule").find("input,select").not("input:hidden,select:hidden,#top_money").each(function(){
        			var that = this;
                	if($(this).val().trim()==""){
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
        		});
    			
    			if(couponAmount>100000){
    				var that = this;
                	
                		var d = Dialog({
    						content:"优惠券数量不能大于10万",
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
    		if(validateFlag){
    		if($("#inactiveTime").val().substring(0,7).replace("-","")-$("#activeTime").val().substring(0,7).replace("-","")>3){
    			var that = this;
            	
            		
            		var d = Dialog({
						content:"优惠券时长不能超过三个月",
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
    		if($("#inactiveTime").val().substring(0,7).replace("-","")-$("#activeTime").val().substring(0,7).replace("-","")==3
    				&&$("#inactiveTime").val().substring(8,10)-$("#activeTime").val().substring(8,10)>=0){
    			var that = this;
            	
        		
        		var d = Dialog({
					content:"优惠券时长不能超过三个月",
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
    		if(validateFlag){
            	if(($("#inactiveTime").val().replace("-","").replace("-",""))-($("#activeTime").val().replace("-","").replace("-",""))<0){
        			var that = this;
                	
                		
                		var d = Dialog({
    						content:"开始日期不能大于结束日期",
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
    		if(validateFlag){
    			$.ajax({
    				type : "post",
    				processing : false,
    				url : _base+ "/coupon/add",
    				dataType : "json",
    				data : {
    					couponType:couponType,
    				    couponName:couponName,
    			        couponAmount:couponAmount,
    			        couponValue:couponVale,
    			        activeTime:activeTime,
    			        inactiveTime:inactiveTime,
    			        couponConType:couponConType,
    			        reachAmount:reachAmount,
    			        reachUnit:reachUnit,
    			        dstTypeUnit:dstTypeUnit,
    			        dstValue:dstValue,
    			        dstUnit:dstUnit,
    			        conditionValue:conditionValue,
    			        topMoney:$("#top_money").val(),
    			        productId:productId,
    			        productName:$("#chioce").text()
    				},
    				message : "正在加载数据..",
    				success : function(data) {
    					if(data.statusCode=="1"){
    						window.location.href=_base+ "/coupon/couponList";
    					}
    				}
    			});
    		}
			
    	}
    });
    
    module.exports = AddCouponPager
});
