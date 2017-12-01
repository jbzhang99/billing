define('app/jsp/invoice/invoiceUser', function (require, exports, module) {
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
    var exportFlag = true;
    //定义页面组件类
    var InvoiceUserPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
        },
        //重写父类
        setup: function () {
            InvoiceUserPager.superclass.setup.call(this);
            this._submitInvoiceFrom();
        },
		_submitInvoiceFrom:function(){
			var _this = this;
            $("#pagination-ul").runnerPagination({
                url: _base+"/invoice/getUserInvoiceList",
                method: "POST",
                dataType: "json",
                processing: true,
                pageSize: InvoiceUserPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#invoiceListTemple");
                        var htmlOut = template.render(data);
                        $("#invoiceData").html(htmlOut);
                        _this._bindQueryInvoice();
                        exportFlag = true;
                    }else{
                        $("#invoiceData").html("未搜索到信息");
                        exportFlag = false;
                    }
                }
            });
        },
        _bindQueryInvoice:function(){
        	$(".query-button").bind("click", function(){
				var _this = this;
				var pDiv = $(_this).parents(".invoiceRecord").find(":hidden");
				var str = "";
				pDiv.each(function(){
					var val = $.trim($(this).val());
					val = encodeURI(val);   
					val = encodeURI(val);
					str += $(this).attr('name')+"="+val+"&";
				})
				str = str.substring(0,str.length-1);
			    window.open(_base+"/invoice/toInvoiceInfo?"+str);
			});
        },
      //存储查询参数，用于excel导出
		_getQueryParams:function(){
			this.companyNameQ = jQuery.trim($("#companyName").val());
			this.startTimeQ = jQuery.trim($("#startTime").val());
			this.endTimeQ = jQuery.trim($("#endTime").val());
			this.byMonthQ = jQuery.trim($("#byMonth").val());
			this.billMonthQ = jQuery.trim($("#billMonth").val());
			this.statusQ = jQuery.trim($("#status").val());
		},
      //导出到excel
		_exportToExcel:function(){
			if(exportFlag){
				var param = 'companyName='+this.companyNameQ + '&startTime='+this.startTimeQ 
				+ '&endTime='+this.endTimeQ + '&byMonth='+this.byMonthQ
				+ '&billMonth='+this.billMonthQ + '&status='+this.statusQ;
				window.location.href = _base + '/invoice/exportToExcel?' + param;
			}else{
				Dialog({
					width: '200px',
					height: '50px',
					content: "无导出数据,请查询数据后再操作",
					okValue:"确定",
                    ok:function(){
                    	this.close;
                    }
				}).showModal();
			}
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

    module.exports = InvoiceUserPager;
});
