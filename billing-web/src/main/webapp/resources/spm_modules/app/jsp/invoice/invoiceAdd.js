define('app/jsp/invoice/invoiceAdd', function (require, exports, module) {
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
    var InvoiceAddPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 10
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
//            "click .btn-query":"_submitInvoiceFrom",
            "click .zuhu-int1":"_hiddenDiv",
            "click .zuhu-int2":"_showDiv"
        },
        //重写父类
        setup: function () {
            InvoiceAddPager.superclass.setup.call(this);
            this._initPage();
        },
        _initPage:function () {
        	var _this = this;
        	var invoiceType = $('input[name="invoiceType"]:checked').val();
        	if(invoiceType=='1'){
        		this._showDiv();
        	}else{
        		this._hiddenDiv();
        	}
        	
        	// 手机号码验证
        	jQuery.validator.addMethod("isMobile", function(value, element) {
        		var length = value.length;
        		var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        		return this.optional(element) || (length == 11 && mobile.test(value));
        	}, "请正确填写您的手机号码");

        	// 电话号码验证
        	jQuery.validator.addMethod("isTel", function(value, element) {
        	  var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678
        	  return this.optional(element) || (tel.test(value));
        	}, "请正确填写您的电话号码"); 
        	
        	$("#invoiceForm").validate( {
        		// Rules for form validation
        		rules : {
        			title : {
        				required : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			taxRegNo : {
        				required : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			bankName : {
        				required : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			bankAcctNo : {
        				required : true,
        				minlength : 3,
        				maxlength : 32
        			},
        			regAddress : {
        				required : true,
        				minlength : 3,
        				maxlength : 50
        			},
        			regPhone : {
        				required : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			linkName : {
        				required : true,
        				minlength : 3,
        				maxlength : 10
        			},
        			address : {
        				required : true,
        				minlength : 3,
        				maxlength : 50
        			},
        			mobileNo : {
        				required : true,
        				minlength : 3,
        				maxlength : 20
        			},
        			phoneNo : {
        				minlength : 3,
        				maxlength : 20
        			},
        			email : {
        				required : true,
        				email:true,
        				minlength : 3,
        				maxlength : 50
        			}
        			
        		},
        		submitHandler : function(form) {
        			_this._submitInvoiceFrom();
        		},
        		// Messages for form validation
        		messages : {
        			title : {
        				required : '请输入发票抬头',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			taxRegNo : {
        				required : '请输入纳税人识别号',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			bankName : {
        				required : '请输入开户银行名称',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			bankAcctNo : {
        				required : '请输入开户账号',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入32个字符'
        			},
        			regAddress : {
        				required : '请输入公司注册地址',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入50个字符'
        			},
        			regPhone : {
        				required : '请输入注册固定电话',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			linkName : {
        				required : '请输入联系人姓名',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入10个字符'
        			},
        			address : {
        				required : '请输入联系地址',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入50个字符'
        			},
        			mobileNo : {
        				required : '请输入手机号码',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			phoneNo : {
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入20个字符'
        			},
        			email : {
        				required : '请输入邮箱地址',
        				email:'邮箱格式不正确',
        				minlength : '至少输入3个字符',
        				maxlength : '最多输入50个字符'
        			}
        		},
                onfocusout: false
        	});
        },
        _hiddenDiv:function(){
        	var invoiceType = $('input[name="invoiceType"]:checked').val();
        	if(invoiceType=='0'){
        		$('.list-hide').hide();
        	}
        },
        _showDiv:function(){
        	var invoiceType = $('input[name="invoiceType"]:checked').val();
        	if(invoiceType=='1'){
        		$('.list-hide').show();
        	}
        },
		_submitInvoiceFrom:function(){
			 ajaxController.ajax({
                 type: "post",
                 dataType : "json",
                 url: _base+"/invoice/saveInvoice",
                 processing: true,
                 message: "正在加载，请等待...",
                 data:$('#invoiceForm').serializeArray(),
                 success: function(data){
                	 console.log(data);
                     if(data){
                         var d = Dialog({
                             content:data.statusInfo,
                             ok:function(){
                                 this.close();
                                 window.location.reload();
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

    module.exports = InvoiceAddPager;
});
