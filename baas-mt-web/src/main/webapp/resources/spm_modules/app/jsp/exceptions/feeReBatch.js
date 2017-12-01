define('app/jsp/exceptions/feeReBatch', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    Calendar = require('arale-calendar/1.1.2/index-month'),
    AjaxController = require('opt-ajax/1.0.0/index');
    
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");
    require("bootstrap-paginator/bootstrap-paginator.min");
    
    require("twbs-pagination/jquery.twbsPagination.min");
    require("opt-paging/aiopt.pagination");
    
    var reBatchFlag = true;
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var FeeRebatchPager = Widget.extend({
    	
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
            "click #reBatchFee":"_reBatchFee"
        },
    	//重写父类
    	setup: function () {
    		FeeRebatchPager.superclass.setup.call(this);
    		this._initPage();
    		this._getServiceType();
    		this._getFallBackType();
    		this._bindCalendar();
    	},
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("异常管理","费用重批");
      		//左侧菜单选中样式
      		$("#mnu_except_mng").addClass("current");
      	},
      	_bindCalendar:function(){
      		var calendar = new Calendar({trigger: '#queryTimeEvent',output:"#accountPeriod"});
      		var startDate = this._getStartDate();
    		var endDate = this._getEndDate();
      		//设置选择范围
      		calendar.range([startDate,endDate]);
      		//设置初始焦点
      		calendar.focus(endDate);
      	},
      //获取选择范围开始时间
      	_getStartDate:function(){
      		var sysDate = new Date();
  			var year = sysDate.getFullYear();    //获取完整的年份(4位,1970-????)
  			var month = sysDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
  			if(month>=1){
  				var startMonth = month-1;
  				startMonth = "0"+startMonth;
  				return year+"-"+startMonth+"-01";
  			}else{
  				var startMonth = 12+(month-1);
  				if(startMonth <10){
  					startMonth = "0"+startMonth;
  				}
  				var startYear = year-1;
  				return startYear+"-"+startMonth+"-01";
  			}
      	},
      	//获取选择范围终止时间
      	_getEndDate:function(){
      		var sysDate = new Date();
  			var year = sysDate.getFullYear();    //获取完整的年份(4位,1970-????)
  			var month = sysDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
  			var endMonth = month;
  			var endYear = year;
  			
  			if(endMonth<10){
  				endMonth = "0"+endMonth;
  			}
  			return endYear+"-"+endMonth;
      	},
      	_getFallBackType: function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getFallBackType', function(data){
    			var json = eval(data);
				$
						.each(
								json,
								function(index, item) {
									// 循环获取数据
									var paramName = json[index].paramName;
									var paramCode = json[index].paramCode;
									$("#fallBackType")
											.append('<option cid="'+json[index].id+'" value="'+paramCode+'">'+paramName+'</option>');
								});
				$("#fallBackType")
						.append("<label id='accesstype_error'></label>");
    		});
    	},
    	_getServiceType: function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getFeeServiceType', function(data){
    			var json = eval(data);
				$
						.each(
								json,
								function(index, item) {
									// 循环获取数据
									var paramName = json[index].paramName;
									var paramCode = json[index].paramCode;
									$("#serviceType")
										.append('<option cid="'+json[index].id+'" value="'+paramCode+'">'+paramName+'</option>');
								});
				$("#serviceType")
						.append("<label id='accesstype_error'></label>");
    		});
    	},
    	_searchBtnClick: function(){
    		
    		if($('#fallBackType').val()==""){
    			var d = Dialog({
                    content:"回退类型不能为空",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if($('#tenantId').val()==""){
    			var d = Dialog({
                    content:"租户不能为空",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if($('#accountPeriod').val()==""){
    			var d = Dialog({
                    content:"请选择账期",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if($('#serviceType').val()==""){
    			var d = Dialog({
                    content:"请选择业务类型",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		
    		var _this = this;
    		this._getQueryParams();    		
    		var url = _base+"/feeReBatch/getList";
    		//分页
    		$("#pagination-ul").runnerPagination({
	 			url: url,
	 			method: "POST",
	 			dataType: "json",
	 			processing: true,
	            data: $('#queryForm').serializeArray(),
	           	pageSize: FeeRebatchPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	            message: "正在为您查询数据..",
	            render: function (data) {
	            	var serviceType = $('#serviceType').val();
	            	var template;
	            	if("VOICE"==serviceType){
            			$('#voiceTab').show();
            			$('#flowTab').hide();
            			template = $.templates("#voiceListsTemple");
            		}else{
            			$('#voiceTab').hide();
            			$('#flowTab').show();
            			template = $.templates("#flowListsTemple");
            		}
	            	if(data != null && data != 'undefined' && data.length>0){
    					var htmlOutput = template.render(data);
    					$("#feeReBatchData").html(htmlOutput);
    					reBatchFlag = true;
	            	}else{
    					$("#feeReBatchData").html("没有搜索到相关信息");
    					reBatchFlag = false;
	            	}
	            }
    		});
    	},
    	//获取查询参数
		_getQueryParams:function(){
			$('#fallBackTypeQ').val(jQuery.trim($("#fallBackType").val()));
			$('#tenantIdQ').val(jQuery.trim($("#tenantId").val()));
			$('#accountPeriodQ').val(jQuery.trim($("#accountPeriod").val()));
			$('#serviceTypeQ').val(jQuery.trim($("#serviceType").val()));
			$('#serviceIdQ').val(jQuery.trim($("#serviceId").val()));
		},
    	_reBatchFee:function(){
    		var _this = this;
    		if($('#fallBackType').val()==""){
    			var d = Dialog({
                    content:"回退类型不能为空",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if($('#tenantId').val()==""){
    			var d = Dialog({
                    content:"租户不能为空",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if($('#accountPeriod').val()==""){
    			var d = Dialog({
                    content:"请选择账期",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if($('#serviceType').val()==""){
    			var d = Dialog({
                    content:"请选择业务类型",
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
    		}
    		if(!reBatchFlag){
    			Dialog({
					width: '200px',
					height: '50px',
					content: "未查出数据",
					okValue:"确定",
                    ok:function(){
                    	this.close;
                    }
				}).showModal();
    			return;
    		}
    		$.ajax({
				url : _base+"/feeReBatch/reBatchFee",
				type : "post",
				dataType: "json",
    	        showWait: true,
    	        async: false,
    	        message: "正在加载数据..",
    	        data: $('#queryparam').serializeArray(),
				success : function(data) {
					var msg = "操作失败,"+data.statusInfo;
    	        	if(data.data){
    	        		msg = "操作成功";
    	        	}
					Dialog({
                        content: msg,
                        ok:function(){
                            this.close();
                        }
                    }).showModal();
                    _this._searchBtnClick();
				}
			});
    	},
    	setSelectValue: function(url, func){
    		$
			.ajax({
				url : url,
				type : "post",
				async : true,
				dataType : "html",
				timeout : "10000",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					func(data);
				}
			});
    	}
    	
    });
    
    module.exports = FeeRebatchPager
});

