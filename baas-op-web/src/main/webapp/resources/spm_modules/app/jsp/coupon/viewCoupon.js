define('app/jsp/coupon/viewCoupon', function (require, exports, module) {
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
    var ViewCouponPager = Widget.extend({
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
    		 "click #BACK_BTN":"_goBack"
        },
      
		_initPage: function(){
			//左侧菜单选中样式
      		$("#mnu_coupon_mng").addClass("current");
      		//面包屑导航
      		setBreadCrumb("优惠券管理","优惠券详情");
      		
      	},
    	//重写父类
    	setup: function () {
    		ViewCouponPager.superclass.setup.call(this);
    		this._initPage();
    		//this._bindCalendar();
    		this._getUseSeclec();
    		this._getRuleSeclec();
    		this._getModelSeclec();
    		this._getReduceSeclec();
    		this._getDetail();
    		
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
    	timestampToDate :function(format, timestamp){
			if(timestamp!=null){
				return (new Date(parseFloat(timestamp))).format(format);
			}else{
				return null;
			}
		},
    	_getDetail:function(){
    		var this_=this;
			$.ajax({
				type : "post",
				processing : false,
				url : _base+ "/coupon/getDetail",
				dataType : "json",
				data : {
					couponId:this.get("couponId")
					},
				message : "正在加载数据..",
				success : function(data) {
				var d=data.data.singleCoupon;
				
				if (d != null && d != 'undefined'){
					
					$("#couponName").val(d.couponName);	
					$("#couponAmount").val(d.couponAmount);
					$("#activeTime").val(this_.timestampToDate('yyyy-MM-dd', d.activeTime));
					$("#inactiveTime").val(this_.timestampToDate('yyyy-MM-dd', d.inactiveTime));
					$("#useType").val(d.couponConType);
					if(d.couponType==="ALL"){
					   $("#couponType").val("全场通用");	
					   $("#chioceProduct").val("所有产品");	
					   
					}else if(d.couponType==="APPOINT"){
						$("#couponType").val("指定产品");
						 $("#chioceProduct").val(d.productName);	
					}
					$("#top_money").val(d.topMoney);
					var con=d.conditonDetail;
					
					if(con != null && con != 'undefined'){
						$("#reachAmount").val(con.reachAmount);
						$("#reach_unit").val(con.reachUnit);
						$("#model_unit").val(con.dstTypeUnit);
						$("#dst_value").val(con.dstValue);
						$("#dst_unit").val(con.dstUnit);
					}
					
				}
				this_._changeType();
				$("#useType").attr("disabled","disabled");
				$("#reach_unit").attr("disabled","disabled");
				$("#model_unit").attr("disabled","disabled");
				$("#dst_unit").attr("disabled","disabled");
				
				
				}
			});
    	},
    	_changeType:function(){
			//对使用条件进行隐藏和显示
			var useType=$("#useType").val();
			if(useType==="FULLREDUCE"){
				//做显示操作
				$("#reach_unit").show();
				
				$("#reachAmount").show();
				$("#man").show();
			}else{
				//做隐藏操作
				$("#reach_unit").hide();
				
				$("#reachAmount").hide();
				$("#man").hide();
			}
			
		},
    	_goBack:function(){
			var this_=this;
			
			window.location.href=_base+"/coupon/couponList";
		},
    	
    	
    });
    
    module.exports = ViewCouponPager
});
