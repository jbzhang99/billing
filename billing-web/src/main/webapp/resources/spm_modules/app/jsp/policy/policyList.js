define('app/jsp/policy/policyList', function (require, exports, module) {
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

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var PolicyPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #submit":"_submitFrom",
            "click #addPolicy":"_addPolicy"
        },
        //重写父类
        setup: function () {
            PolicyPager.superclass.setup.call(this);
            this._initPage();
        },
        _initPage:function () {
            this._submitFrom();
        },
        _submitFrom:function(){
        	var _this = this;
            $("#pagination-ul").runnerPagination({
                url: _base+"/policy/getList",
                method: "POST",
                dataType: "json",
                processing: true,
                data : $('#queryForm').serializeArray(),
                pageSize: PolicyPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#policyListTemple");
                        var htmlOut = template.render(data);
                        $("#policyData").html(htmlOut);
                        _this._bindPolicy();
                    }else{
                        $("#policyData").html("未搜索到信息");
                    }
                }
            });
        },
        _bindPolicy:function(){
        	$(".view-policy").bind("click", function(){
        		var custId =  $(this).parents(".policy-record").find("input[name='extCustId']").val();
			    window.open(_base+"/policy/toPolicyShow?custId="+custId);
			});
        	$(".edit-policy").bind("click", function(){
        		var custId =  $(this).parents(".policy-record").find("input[name='extCustId']").val();
			    window.location.href = _base+"/policy/toPolicyUpdate?custId="+custId;
			});
        	$(".del-policy").bind("click", function(){
        		var custId =  $(this).parents(".policy-record").find("input[name='extCustId']").val();
        		
        		var d = Dialog({
                    content:"确定删除？",
                    ok:function(){
                    	$.ajax({
        					type : "post",
        					processing : false,
        					url : _base+"/policy/delPolicy",
        					dataType : "json",
        					data : {
        						"custId":custId
        					},
        					message : "处理中..",
        					success : function(data) {
    							var d = Dialog({
    		                         content:data.statusInfo,
    		                         ok:function(){
    		                             this.close();
    		                         }
    		                     });
    		                     d.showModal();
        					}
        				});
                        this.close;
                    },
                    okValue:"确定",
                    cancel:function(){
                    	this.close;
                    },
                    cancelValue:"取消"
                });
                d.showModal();
			});
        },
        _addPolicy:function(){
        	window.location.href = _base+"/policy/toPolicyAdd";
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

    module.exports = PolicyPager;
});
