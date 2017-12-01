define('app/jsp/coupon/couponList',function(require, exports, module) {
			'use strict';
			var $ = require('jquery'), 
			Widget = require('arale-widget/1.2.0/widget'), 
			Calendar = require('arale-calendar/1.1.2/index-debug'),
			Select = require('arale-select/0.11.1/index'), 
			Dialog = require("artDialog/src/dialog"),

			// Paging = require('paging/0.0.1/paging'),
			AjaxController = require('opt-ajax/1.0.0/index'), SendMessageUtil = require("app/util/sendMessage");
			require("jsviews/jsrender.min");
			require("jsviews/jsviews.min");
			require("bootstrap-paginator/bootstrap-paginator.min");
			require("twbs-pagination/jquery.twbsPagination.min");
			require("opt-paging/aiopt.pagination");
			require("app/util/jsviews-ext");

			// 实例化AJAX控制处理对象
			// var ajaxController = new AjaxController();

			// 定义页面组件类
			var CouponListPager = Widget.extend({
						Implements : SendMessageUtil,

						// 属性，使用时由类的构造函数传入
						attrs : {},
						Statics : {
							DEFAULT_PAGE_SIZE : 6
						},
						// 事件代理
						events : {
							// key的格式: 事件+空格+对象选择器;value:事件方法
							"click #BTN_SEARCH" : "_getPageList",
							// "click #CHANGE_BTN" : "_changeStatus",
							// "click #DEL_BTN" : "_delCoupon",
						  //  "click #DETAIL_BTN" : "_couponDetail" //查看详情
							/*"click #PHONE_IDENTIFY" : "_getPhoneVerifyCode",// 此方法在SendMessageUtil里
							"click .pop-close" : "_closeMessage"// 此方法在SendMessageUtil里*/	
						},
						_initPage : function() {
							// 左侧菜单选中样式
							$("#mnu_coupon_mng").addClass("current");
							// 面包屑导航
							setBreadCrumb("优惠券管理", "优惠券管理");
							
						},
						// 重写父类
						setup : function() {
							CouponListPager.superclass.setup.call(this);
							this._initPage();
							this._bindCalendar();
							this._getPageList();
							this._delCoupon();
							this._couponDetail();
							this._getCouponStatus();
							
						},
						// 日期
						_bindCalendar : function() {
							new Calendar({
								trigger : '#activeTime'
							});
							new Calendar({
								trigger : '#invalidTime'
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
						_getCouponStatus:function(){
							var _this=this;
							$.ajax({
								type : "post",
								processing : false,
								url : _base+ "/coupon/getSelect",
								dataType : "json",
								data : {
									paramType:"COUPON_STATUS"
									},
								message : "正在加载数据..",
								success : function(data) {
									var d=data.data.paramList;
									$.each(d,function(index,item){
										var paramName = d[index].paramName;
										var paramCode = d[index].paramCode;
										$("#STATUS").append('<option value="'+paramCode+'">'+paramName+'</option>');
									})
								}
							});
						},
						_getPageList:function(){
		    				var _this = this;
							var url =  _base+ "/coupon/getPageList";
							$("#pagination-ul")
									.runnerPagination(
											{
												url : url,
												method : "POST",
												dataType : "json",
												processing : true,
												data : {
													couponName:$("#couponName").val(),
													couponId:$("#couponId").val(),
													startTime:$("#activeTime").val(),
													endTime:$("#invalidTime").val(),
													status:$("#STATUS").val()
												},
												pageSize : CouponListPager.DEFAULT_PAGE_SIZE,
												visiblePages :5,
												message : "正在为您查询数据..",
												render : function(data) {
													if (data != null
															&& data != 'undefined'
															&& data.length > 0) {
														var template = $.templates("#listDataTmpl");
														var htmlOutput = template.render(data);
														$("#listData").html(htmlOutput);
														_this._couponDetail();
														_this._delCoupon();
														_this. _bindDownLoad();
														
													} else {
														$("#listData").html(null);
														$("#listData").html("没有搜索到相关信息");
													}
												}
											});
		    				
		    				
		    				
						},
						_delCoupon:function(){
							var this_ = this;
							$(".DEL_BTN").bind("click",function() {
								var _this = this;
								Dialog({    
										content : "确定删除？",
										okValue : "确定",
										ok : function() {
											
											$.ajax({
												type : "post",
												processing : false,
												url : _base + "/coupon/delCoupon",
												dataType : "json",
												data : { 
													couponId : $(_this).attr('couponId')
												},
												message : "正在加载数据..",
												success : function(data) {
												Dialog({
												content : "删除成功",
												kValue : "确定",
												ok : function() {
														this.close;
													    this_._getPageList();
														}
													}).showModal();
												}
												});
								        	  
								        
													},
													cancelValue : "取消",
													cancel : function() {
														this.close;
													}
												}).showModal();
							});
								
						},
						_couponDetail:function(){
							
							var this_ = this;

							$(".DETAIL_BTN").bind("click",function() {
								var _this = this;
							    window.location.href= _base+ "/coupon/toView?couponId="+ $(_this).attr('couponId');
												
								})
						    },
						    _bindDownLoad:function(){
								//下载月详细数据
								$(".DOWNLOAD_BTN").bind("click",function(){
									var _this=this;
									window.location.href=_base+ "/coupon/downLoadCoupon?couponId="+ $(_this).attr('couponId');
								})
								
							}
						
						
						
					});

			module.exports = CouponListPager
		});
