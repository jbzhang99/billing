define('app/jsp/account/accountViewForSystemManage', function (require, exports, module) {
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

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var AccountViewPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
    		"click #chargeBtn":"_deposit",
            "click #billQuery":"searchAcctList",
    		"click .pop-close":"_closePop"
        },
        //重写父类
        setup: function () {
            AccountViewPager.superclass.setup.call(this);
            this._initSelect();
            this.searchAcctList();
        },
        _initSelect:function () {
            
        },
        searchAcctList:function(){
            var data = $("#queryCondition :input").serializeArray();
            $("#pagination").runnerPagination({
                url: _base+"/account/accountListQuery",
                method: "POST",
                dataType: "json",
                processing: true,
                data : data,
                pageSize: AccountViewPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#acctInfoTmpl");
                        var htmlOut = template.render(data);
                        $("#acctInfo").html(htmlOut);
                    }else{
                        $("#acctInfo").html("未搜索到信息");
                    }
                }
            });
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
        },_showView:function(accountId){
    		//alert(accountId);
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
				}
			);
    	}
    });

    module.exports = AccountViewPager;
});
