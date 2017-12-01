define('app/js/home', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    Uploader = require('arale-upload/1.2.0/index'),
    AjaxController=require('opt-ajax/1.0.0/index');
    
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("treegrid/js/jquery.treegrid.min");
    require("treegrid/js/jquery.cookie");
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    
    //定义页面组件类
    var HomePager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	//事件代理
    	events: {
    		//key的格式: 事件+空格+对象选择器;value:事件方法
    		"click [id='submitBtn']":"_sendEmail",
    		"input [id='phone']":"_checkPhone",
    		"propertychange [id='phone']":"_checkPhone",
    		"input [id='email']":"_checkEmail",
    		"propertychange [id='email']":"_checkEmail",
    		"propertychange  [id='message']":"_checkMessage",
    		"input  [id='message']":"_checkMessage"
        },
    	//重写父类
    	setup: function () {
    		HomePager.superclass.setup.call(this);
    		this._loadTenantType();
    	},
    	_controlTextLength:function(){
    		$('#tenantName').maxlength({
    			alwaysShow: true
    		});
    		$('#message').maxlength({
    			alwaysShow: true
    		});
    	},
    	_loadTenantType:function(){
    		if(industryList != null && industryList!= undefined && industryList.length>0){
    			var objSelect = document.getElementById("tenantType").options;
    			for(var i=0;i<industryList.length;i++){
    				objSelect.add(new Option(industryList[i].IndustryName,industryList[i].IndustryCode));
    			}
    		}
		},
    	_sendEmail:function(){
    		var _this =this;
    		var isOk = this._checkConsultInfo();
    		if(!isOk){
    			return;
    		}
    		$("#submitBtn").attr("disabled", true);
			$.ajax({
				type : "POST",
				data : {
					phone:jQuery.trim($("#phone").val()),
					email:jQuery.trim($("#email").val()),
					message:jQuery.trim($("#message").val()),
					tenantType:function(){
						var typeValue = $("#tenantType option:selected").val();
						if(typeValue == null || typeValue == ""){
							return "";
						}else{
							return jQuery.trim($("#tenantType option:selected").text());
						}
					},
					tenantName:jQuery.trim($("#tenantName").val())
				},
				dataType: 'json',
				url :_base+"/consult/sendEmail",
				processing: true,
				message : "正在处理中，请稍候...",
				success : function(data) {
					if(data){
						var resultCode = data.statusCode;
						if(resultCode=="1"){
							_this._clearConsultInfo();
						}
						var msgDialog = Dialog({
							title: '提示',
							content: data.statusInfo,
							ok: function(){
								this.close();
							}
						});
						msgDialog.showModal();
					}else{
						var msgDialog = Dialog({
							title: '提示',
							content: "咨询内容提交失败，请稍后再试",
							ok: function(){
								this.close();
							}
						});
		      			msgDialog.showModal();
					}
					$("#submitBtn").removeAttr("disabled");
				}
			});
		},
		_clearConsultInfo:function(){
			$("#phone").val(""),
			$("#email").val(""),
			$("#message").val(""),
			$("#tenantType").val(""),
			$("#tenantName").val("")
		},
		_checkConsultInfo:function(){
			var checkPhone = this._checkPhone();
			var checkEmail = this._checkEmail();
			var checkMessage = this._checkMessage();
			return checkPhone&&checkEmail&&checkMessage;
		},
		//检查验证码
		_checkPhone: function(){
			var phone = jQuery.trim($("#phone").val());
			if(phone != "" && phone != null && phone != undefined){
				if(phone.length == 11 && phone.charAt(0)=="1"){
					this._controlMsgHTML("phoneMsg","");
					this._controlMsgAttr("phoneMsg",1);
					return true;
				}else{
					this._controlMsgHTML("phoneMsg","手机号格式错误");
					this._controlMsgAttr("phoneMsg",2);
					return false;
				}
			}else{
				this._controlMsgHTML("phoneMsg","");
				this._controlMsgAttr("phoneMsg",1);
				return true;
			}
		},
		//检查验证码
		_checkEmail: function(){
			var email = jQuery.trim($("#email").val());
			if(email != "" && email != null && email != undefined){
				if(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(email)){
					this._controlMsgHTML("emailMsg","");
					this._controlMsgAttr("emailMsg",1);
					return true;
				}else{
					this._controlMsgHTML("emailMsg","邮箱格式错误");
					this._controlMsgAttr("emailMsg",2);
					return false;
				}
			}else{
				this._controlMsgHTML("emailMsg","");
				this._controlMsgAttr("emailMsg",1);
				return true;
			}
		},
		//检查验证码
		_checkMessage: function(){
			var message = jQuery.trim($("#message").val());
			if(message == "" || message == null || message == undefined){
				this._controlMsgHTML("messageMsg","请输入咨询内容");
				this._controlMsgAttr("messageMsg",2);
				return false;
			}else{
				this._controlMsgHTML("messageMsg","");
				this._controlMsgAttr("messageMsg",1);
				return true;
			}
		},
		//控制显示内容
		_controlMsgHTML: function(id,msg){
			var doc = document.getElementById(id+"");
			doc.innerHTML=msg;
		},
		//控制显隐属性 1:隐藏 2：显示
		_controlMsgAttr: function(id,flag){
			var doc = document.getElementById(id+"");
			if(flag == 1){
				doc.setAttribute("style","display:none");
			}else if(flag == 2){
				doc.setAttribute("style","display");
			}
		}
    });
    module.exports = HomePager
});
