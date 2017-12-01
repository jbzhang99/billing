define('app/jsp/deposit/depositManage', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    Paging = require('paging/0.0.1/paging-debug'),
    Calendar = require('arale-calendar/1.1.2/index'),
	Validator = require('arale-validator/0.10.2/index'),
	moment = require('moment/2.9.0/moment'),
    AjaxController = require('opt-ajax/1.0.0/index');
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("bootstrap-paginator/bootstrap-paginator.min");
    require("app/util/jsviews-ext");
    
    require("opt-paging/aiopt.pagination");
    require("twbs-pagination/jquery.twbsPagination.min");
    require("jquery/validate/jquery.validate.min");
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var DepositManagePager = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 6
    	},
    	//事件代理
    	events: {

//    		"click #chargeBtn":"_deposit",
    		"click #depositQueryBtn":"_searchAcctList",
    		"click #depositQuery":"_searchAcctList",
    		"click #depositRecordQueryBtn":"_searchRecord",
    		"click #depositRecordQuery":"_searchRecord",
    		"click .pop-close":"_closePop"
        },
    	//重写父类
    	setup: function () {
    		DepositManagePager.superclass.setup.call(this);
    		this._searchAcctList();
    		this._bindCalendar();
    		this._init();
    	},

    	//充值
    	_showView:function(accountId){
//  		alert(accountId);
    		//
    		$('#showView_accountId').val(accountId);
    		$('#pop_recharge_id').show();
    		$('#amount').val('');
    		$('#depositTime').val('');
    		

    		
    	},
    	_closePop:function(){
    		$("label.error[for='amount']").remove();
    		$("label.error[for='depositTime']").remove();
    		$('.pop-recharge').hide();
    	},
    	_init:function(){
    		var _this = this;
    		$("#depositform").validate( {
        		// Rules for form validation
        		rules : {
        			amount : {
        				required : true,
        				maxlength : 12
        			},
        			depositTime : {
        				required : true,
        			}
    				
        			
        		},
        		submitHandler : function(form) {
        			_this._deposit();
        		},
        		// Messages for form validation
        		messages : {
        			amount : {
        				required : '请输入充值金额',
        				maxlength : '金额最多输入12位'
        			},
        			depositTime : {
        				required : '请输入充值时间',
        			}
        		},
        		onfocusout: false
        	});
    	},

    	
    	_bindCalendar:function(){
			var startCalendar = new Calendar({trigger: '#startTimeSpan'});
			var endCalendar = new Calendar({trigger: '#endTimeSpan'});
			var timeCalendar = new Calendar({trigger: '#depositTimeSpan'});
		},
		_checkDate: function(){
			var sTime = $("#beginTime").val();
			var dTime = $("#endTime").val();
			if(sTime!="" && dTime!=""){
			      //js判断日期
			    var begin = moment(sTime,"YYYY-MM-DD");
				var end = moment(dTime,"YYYY-MM-DD");
				if(end.diff(begin)<0){
					Dialog({
						title : '提示',
						width : '200px',
						height : '50px',
						content : "开始时间要在结束时间之前!",
						okValue: "确定",
						ok:function(){
							this.close();
						}
					}).showModal();
			         return false;
			      }else{
			    	  $('#showDateMsg').text("");
			    	  return true;
			      }
			}else{
				return true;
			}
		},
    	_deposit:function(){
    		var checkNum = true;
    		var msg = "";
    		var num = $("#amount").val();
            if(!isNaN(num)){
                var dot = num.indexOf(".");
                if(dot != -1){
                    var dotCnt = num.substring(dot+1,num.length);
                    if(dotCnt.length > 2){
                    	msg = "小数位不能超过2位";
                    	checkNum = false;
                    }
                }
            }else{
            	msg = "数字不合法！";
            	checkNum = false;
            }
            if(!checkNum){
            	var d = Dialog({
					content:msg,
					okValue:"确定",
					mask: true,  
					ok:function () {
						this.close();
					}
				});
            	d.show();
            }else{
            	ajaxController.ajax({
					type: "post",
					dataType: "json",
					processing: true,
					message: "充值中，请等待...",
					url: _base+"/deposit/toDeposit",
					data:{"amount":$('#amount').val(),
						  "accountId":$('#showView_accountId').val(),
						  "depositTime":$('#depositTime').val()},
					success: function(data){
						
						if(data.payserialcode=='000000'){
							var df = Dialog({
								content:"充值成功",
								okValue:"确定",
								mask: true,  
								ok:function () {
									this.close();
									$('#pop_recharge_id').hide();
								}
							});
							df.show();
						}else{
							var df = Dialog({
								content:"充值失败",
								okValue:"确定",
								mask: true,  
								ok:function () {
									this.close();
								}
							});
							df.show();
						}

					}
				});
            }
    	},
    	
        _searchAcctList:function(){
/*        	alert('aaaa');*/
            var data = $("#depositCondition :input").serializeArray();
            $("#pagination").runnerPagination({
            	
                url: _base+"/deposit/toSearch",
                method: "POST",
                dataType: "json",
                processing: true,
                data : data,
                pageSize: DepositManagePager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
///*                	alert('====>'+data);*/
                    if(data&&data.length>0){
                        var template = $.templates("#depositDataTmpl");
                        var htmlOut = template.render(data);
                        $("#depositData").html(htmlOut);
                    }else{
                        $("#depositData").html("未搜索到信息");
                    }
                }
            });
        },
    	 _searchRecord:function(){
    		 if(!this._checkDate()){
         		return;
         	 }
			 var data = $("#depositRecordCondition :input").serializeArray();
			 $("#pagination1").runnerPagination({
				 url: _base+"/deposit/toSearchRecord",
				 method: "POST",
				 dataType: "json",
				 processing: true,
				 data : data,
				 pageSize: DepositManagePager.DEFAULT_PAGE_SIZE,
				 visiblePages:5,
				 message: "正在为您查询数据..",
				 render: function (data) {
					 if(data&&data.length>0){
						 var template = $.templates("#depositRecordDataTmpl");
						 var htmlOut = template.render(data);
						 $("#depositRecordData").html(htmlOut);
					 }else{
						 $("#depositRecordData").html("未搜索到信息");
					 }
				 }
			 });
		 }
    	

      	
    	
    });
    
    module.exports = DepositManagePager
});

