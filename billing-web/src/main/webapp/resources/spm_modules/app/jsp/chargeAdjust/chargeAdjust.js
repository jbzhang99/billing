define('app/jsp/chargeAdjust/chargeAdjust', function (require, exports, module) {
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
    require("jquery/validate/jquery.validate.min");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var ChargeAdjustPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 10
        },
        //事件代理
        events: {
            "blur input[name=amount]":"judgeAmount"
        },
        //重写父类
        setup: function () {
            ChargeAdjustPager.superclass.setup.call(this);
            this._queryFeeType();
            this._initPage();
        },
        judgeAmount:function () {
            if($.trim($('input[name="amount"]').val())!=""){
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/chargeAdjust/judgeAmount",
                    data:{
                        acctId:$('input[name="acctId"]').val(),
                        billMonth:$('input[name="billMonth"]').val(),
                        subjectId:$("#feeType").val(),
                        amount:$('input[name="amount"]').val(),
                        adjustFlag:$('input[name="adjustFlag"]:checked').val()
                    },
                    success: function(data){
                        if(data&&data.statusCode==0){
                            var d = Dialog({
                                content:data.statusInfo,
                                ok:function(){
                                    this.close();
                                }
                            });
                            d.showModal();
                        }
                    }
                });
            }
        },
        _queryFeeType:function () {
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/chargeAdjust/queryChargeList",
                processing: true,
                message: "正在加载，请等待...",
                data:{
                	acctId:$('input[name="acctId"]').val(),
                    billMonth:$('input[name="billMonth"]').val()
				},
                success: function(data){
                    if(data&&data.data){
                        $.each(data.data,function (i,item) {
                            if(i==0){
                                $("#feeType").append("<option value='"+item.subjectId+"' selected>"+item.subjectName+"</option>");
                            }else{
                                $("#feeType").append("<option value='"+item.subjectId+"'>"+item.subjectName+"</option>");
                            }
                        });
                    }else{
                        var d = Dialog({
                            content:data.statusInfo,
                            ok:function(){
                                this.close();
                            }
                        });
                        d.showModal();
					}
                }
            });
        },
        _initPage:function () {
        	var _this = this;
        	
        	// 金额验证
        	jQuery.validator.addMethod("isMoney", function(value, element) {
        	  var money = /^[1-9]\d*\.\d+|0\.\d*[1-9]\d*$/; //数字
        	  return this.optional(element) || (money.test(value));
        	}, "请输入正确的金额");

        	$("#adjustForm").validate( {
        		// Rules for form validation
        		rules : {
                    amount : {
        				required : true,
        				isMoney : true
        			},
                    adjustReason : {
        				minlength : 3,
        				maxlength : 100
        			}
        		},
        		submitHandler : function(form) {
                    if($.trim($('input[name="amount"]').val())!=""){
                        ajaxController.ajax({
                            type: "post",
                            dataType : "json",
                            url: _base+"/chargeAdjust/judgeAmount",
                            data:{
                                acctId:$('input[name="acctId"]').val(),
                                billMonth:$('input[name="billMonth"]').val(),
                                subjectId:$("#feeType").val(),
                                amount:$('input[name="amount"]').val(),
                                adjustFlag:$('input[name="adjustFlag"]:checked').val()
                            },
                            success: function(data){
                                if(data&&data.data=='1'){
                                    var d = Dialog({
                                        content:data.statusInfo,
                                        ok:function(){
                                            this.close();
                                        }
                                    });
                                    d.showModal();
                                }else {
                                    _this._adjustCharge();
                                }
                            }
                        });
                    }
        		},
        		// Messages for form validation
        		messages : {
                    amount : {
        				required : '请输入调整金额',
        				isMoney : '请输入正确金额'
        			},
                    adjustReason : {
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入100个字符'
        			}
        		},
                onfocusout: false
        	});
        },
        _adjustCharge:function(){
			 ajaxController.ajax({
	             type: "post",
	             dataType : "json",
	             url: _base+"/chargeAdjust/adjustCharge",
	             processing: true,
	             message: "正在加载，请等待...",
	             data:{
                     acctId:$('input[name="acctId"]').val(),
                     billMonth:$('input[name="billMonth"]').val(),
                     subjectId:$("#feeType").val(),
                     amount:$('input[name="amount"]').val(),
                     adjustReason:$.trim($("textarea").val()),
                     adjustFlag:$('input[name="adjustFlag"]:checked').val()
                 },
	             success: function(data){
	                 if(data){
	                     var d = Dialog({
	                         content:data.statusInfo,
	                         ok:function(){
	                             this.close();
                                 window.location.href=_base+"/chargeAdjust/toSearchLedger";
	                         }
	                     });
	                     d.showModal();
	                 }
	             }
	         });
        }
    });

    module.exports = ChargeAdjustPager;
});
