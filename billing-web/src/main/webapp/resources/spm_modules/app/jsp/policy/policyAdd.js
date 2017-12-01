define('app/jsp/policy/policyAdd', function (require, exports, module) {
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
    var PolicyAddPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 10
        },
        //事件代理
        events: {
        	
        },
        //重写父类
        setup: function () {
            PolicyAddPager.superclass.setup.call(this);
            this._initPage();
        },
        _initPage:function () {
        	var _this = this;
        	
        	// 电话号码验证
        	jQuery.validator.addMethod("isMoney", function(value, element) {
        	  var tel = /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/; //电话号码格式010-12345678
        	  return this.optional(element) || (tel.test(value));
        	}, "请输入正确的金额"); 
        	
        	$("#policyForm").validate( {
        		// Rules for form validation
        		rules : {
        			ruleName : {
        				required : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			custId : {
        				required : true,
        				minlength : 3,
        				maxlength : 40
        			},
        			pressPayment : {
        				required : true,
        				isMoney : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			description : {
        				minlength : 3,
        				maxlength : 100
        			},
        		},
        		submitHandler : function(form) {
        			_this._savePolicy();
        		},
        		// Messages for form validation
        		messages : {
        			ruleName : {
        				required : '请输入规则名称',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			custId : {
        				required : '请输入客户id',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入40个字符'
        			},
        			pressPayment : {
        				required : '请输入催缴值金额',
        				isMoney : '请输入正确金额',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			description : {
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入100个字符'
        			}
        		},
                onfocusout: false
        	});
        },
        _savePolicy:function(){
        	
        	 var custIds = [];
        	 custIds.push($('input[name="custId"]').val());
        	 $('input[name="custIds"]').val(custIds);
        	 
			 ajaxController.ajax({
	             type: "post",
	             dataType : "json",
	             url: _base+"/policy/savePolicy",
	             processing: true,
	             message: "正在加载，请等待...",
	             data:$('#policyForm').serializeArray(),
	             success: function(data){
	                 if(data){
	                     var d = Dialog({
	                         content:data.statusInfo,
	                         ok:function(){
	                             this.close();
	                             window.location.href=_base+"/policy/toPolicyList";
	                         }
	                     });
	                     d.showModal();
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
        }
    });

    module.exports = PolicyAddPager;
});
