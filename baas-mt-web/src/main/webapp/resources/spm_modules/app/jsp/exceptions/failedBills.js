define('app/jsp/exceptions/failedBills', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    Calendar = require('arale-calendar/1.1.2/index-debug'),
    AjaxController = require('opt-ajax/1.0.0/index');
    
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");
    require("bootstrap-paginator/bootstrap-paginator.min");
    
    require("twbs-pagination/jquery.twbsPagination.min");
    require("opt-paging/aiopt.pagination");
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var FailedBillsPager = Widget.extend({
    	
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
//            "click .sp-setvalid":"showSetValidDiv"
//            "click .del-button":"_deleteStandFee",
            "click #PHONE_IDENTIFY":"_getPhoneVitentify",
            "click #checkAll":"_checkall",    
            "click .resend-bill":"_resendBill",
            "click #resendEditBill":"_resendEditBill"
        },
    	//重写父类
    	setup: function () {
    		FailedBillsPager.superclass.setup.call(this);
    		this._initPage();
    		this._getServiceType();
    		this._bindCalendar();
    		this._getFailCode();
    	},
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("异常管理","错单管理");
      		//左侧菜单选中样式
      		$("#mnu_except_mng").addClass("current");
      	},
     // 日期
		_bindCalendar : function() {
			new Calendar({
				trigger : '#startTime'
			});
			new Calendar({
				trigger : '#endTime'
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
      	_showEditBill:function(){
      		var _this = this;
      		$(".bill-edit").bind("click", function(){
      			var pTag = $(this).parents("[name='failedList']");
      			pTag.attr("id","selectedBill");
      			$('#billDetail').html('');
      			$.ajax({
        	        method: "POST",
        	        url:  _base+"/failedBills/getFailedBillDetail",
        	        dataType: "json",
        	        showWait: true,
        	        async: false,
        	        data: pTag.find("input[type='hidden']").serializeArray(),
        	        message: "正在加载数据..",
        	        success: function (data) {
        	        	 var data = data.data;
        	        	 $.each(data,function(key,value){     
        	        		var html = '<tr>';
        	        		html += '<td class="left-border" name="billkeys" width="40%">'+key+'</td>';
        	        		html += '<td name="billValues">'+value+'</td>';
        	        		html += '</tr>';
        	        		$('#billDetail').append(html);
    	        		 });    
        	        	 _this._bindDblclick();
        	        }
        	    });
      			$('.table-pop').show();
      		});
      	},
      	_bindDblclick:function(){
      		$("td[name=billValues]").dblclick(function(){
      			 if($(this).find('input').length==0){
      				var value = $(this).html(); 
      				$(this).html("<input type='text' class='int-medium-float bill-values' value='"+value+"'>");
         			 $(".bill-values").focus().bind("blur",function() {
         	            var editval = $(this).val();               
         	            $(this).parent().html(editval);
         			 })
      			 }
      		});
      	},
      	_bindRefresh:function(){
			$(".bill-refresh").bind("click", function(){
				
				var pTag = $(this).parents("[name='failedList']");
	        	$.ajax({
	        	        method: "POST",
	        	        url:  _base+"/failedBills/getFailedBill",
	        	        dataType: "json",
	        	        showWait: true,
	        	        async: false,
	        	        data: pTag.find("input").serializeArray(),
	        	        message: "正在加载数据..",
	        	        success: function (data) {
	        	        	 if(!data.data){
	        	        		 pTag.hide();
	        	        	 }
	        	        	 Dialog({
                                content:"刷新成功" ,
                                ok:function(){
                                    this.close();
                                }
                            }).showModal();
	        	        }
	        	    });
	            });
    	},
    	_getServiceType: function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getServiceType', function(data){
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
    	_getFailCode:function() {
    		var _this = this;
    		this.setSelectValue(_base + '/param/getFailCode', function(data){
    			var json = eval(data);
				$.each(json,function(index, item) {
									// 循环获取数据
									var paramName = json[index].paramName;
									var paramCode = json[index].paramCode;
									$("#errorCode").append('<option cid="'+json[index].id+'" value="'+paramCode+'">'+paramName+'</option>');
								});
				/*$("#serviceType").append("<label id='accesstype_error'></label>");*/
    		});
    	},
    	_checkall: function() {
			var chkflag=$("#checkAll").prop("checked");
			$('input[type=checkbox][name=bsn]').prop("checked",chkflag);
         },
    	_searchBtnClick: function(){
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
    		var url = _base+"/failedBills/getList";
    		var startTime=$("#startTime").val();
    		var endTime=$("#endTime").val();
    		var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
    		var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
    		if(start>end){
    			Dialog({
                    content: "开始时间不能大于结束时间",
                    ok:function(){
                        this.close();
                    }
                }).showModal();
    			return false;
    		}
    		//分页
    		$("#pagination-ul").runnerPagination({
	 			url: url,
	 			method: "POST",
	 			dataType: "json",
	 			processing: true,
	            data: {    	            	
	            	tenantId:$('#tenantId').val(),
	            	serviceType:$('#serviceType').val(),
	            	errorCode:$('#errorCode').val(),
	            	startTime:$("#startTime").val(),
	            	endTime:$("#endTime").val()
	            },
	           	pageSize: FailedBillsPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	            message: "正在为您查询数据..",
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#failedListsTemple");
    					var htmlOutput = template.render(data);
    					$("#failedBillsData").html(htmlOutput);
    					_this._bindRefresh();
    					_this._showEditBill();
	            	}else{
    					$("#failedBillsData").html("没有搜索到相关信息");
	            	}
	            }
    		});
    		
//    		ajaxController.ajax({
//    			
//	 			url: url,
//	 			method: "POST",
//	 			dataType: "json",
//	 			processing: true,
//	            data: {    	            	
//	            	tenantId:$('#tenantId').val(),
//	            	serviceType:$('#serviceType').val(),
//	            	errorCode:$('#errorCode').val()
//	            },
//	            message: "正在为您查询数据..",
//	            success: function (data) {
//	            	var data = data.data;
//	            	if(data != null && data != 'undefined'){
//	            		var template = $.templates("#failedListsTemple");
//    					var htmlOutput = template.render(data);
//    					$("#failedBillsData").html(htmlOutput);
//    					_this._bindRefresh();
//	            	}else{
//    					$("#failedBillsData").html("没有搜索到相关信息");
//	            	}
//	            }
//    		});
    	},
    	_resendEditBill:function(){
    		var billkeysArray = new Array();
    		var billValuesArray = new Array();
    		$('td[name=billkeys]').each(function(i){
    			billkeysArray[i] = $(this).html();
			});
    		$('td[name=billValues]').each(function(i){
    			billValuesArray[i] = $(this).html();
			});
    		
    		var billkeys = JSON.stringify(billkeysArray);
    		var billValues = JSON.stringify(billValuesArray);
    		
    		var data = $('#selectedBill').find("input").serializeArray();
    		data.push({
    			name:'billkeysStr',
    			value:billkeys
    		});
    		data.push({
    			name:'billValuesStr',
    			value:billValues
    		});
    		var _this = this;
    		$.ajax({
    	        method: "POST",
    	        url:  _base+"/failedBills/editResendBill",
    	        dataType: "json",
    	        showWait: true,
    	        async: false,
    	        data: data,
    	        message: "正在加载数据..",
    	        success: function (data) {
    	        	var msg = "操作失败,"+data.statusInfo;
    	        	if(data.data){
    	        		msg = "操作成功";
    	        	}
    	        	Dialog({
    	        		 content: msg,
    	        		 ok:function(){
                             this.close();
                             $('#selectedBill').removeAttr('id');
                             $('.table-pop').hide(200);
                             _this._searchBtnClick();
                         }
 					}).showModal();
    	        }
    	    });
    	},
    	_resendBill:function(){
    		var _this = this;
    		var failedBills = [];
    		var failedBill = {
    			bsn : '',
    			serviceId : '',
    			source : '',
    			failCode:'',
    			sn:'',
    			failStep:'',
    			failDate:'',
    			accountPeriod:'',
    			arrivalTime:''
    		}
    		$('input:checkbox[name=bsn]:checked').each(function(i){
    			var pTag = $(this).parents("[name='failedList']");
    			var bill = {
					bsn : pTag.find("input[name='bsn']").val(),
					source : pTag.find("input[name='source']").val(),
					sn:pTag.find("input[name='sn']").val(),
					failDate:pTag.find("input[name='failDate']").val(),
					tenantId:pTag.find("input[name='tenantId']").val(),
	    			serviceId : pTag.find("input[name='serviceId']").val(),
	    			failedCode:pTag.find("input[name='failedCode']").val(),
	    			failStep:pTag.find("input[name='failStep']").val(),
	    			accountPeriod:pTag.find("input[name='accountPeriod']").val(),
	    			arrivalTime:pTag.find("input[name='arrivalTime']").val()
    			};
    			failedBills.push(bill);
			});
    		if(failedBills.length==0){
    			Dialog({
                    content: "请选择数据",
                    ok:function(){
                        this.close();
                        return;
                    }
                }).showModal();
    			return;
    		}
    		var jsonData = JSON.stringify(failedBills); 
    		$.ajax({
				url : _base+"/failedBills/batchResendBill",
				type : "post",
				dataType: "json",
    	        showWait: true,
    	        async: false,
    	        message: "正在加载数据..",
    	        data: {"jsonData":jsonData},
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
    
    module.exports = FailedBillsPager
});

