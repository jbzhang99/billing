define('app/jsp/strategy/strategyList', function (require, exports, module) {
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
    var StrategyPager = Widget.extend({
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
            "click #strategyAdd":"_addStrategy",
            "click #BTN_EXPORT":"_exportTxt",
            "click #BTN_IMPORT":"_openDiv",
            "click #import":"_importTxt"
        },
        //重写父类
        setup: function () {
            StrategyPager.superclass.setup.call(this);
            this._initPage();
        },
        _initPage:function () {
        	this._setPolicyType();
            this._submitFrom();
        },
        _submitFrom:function(){
        	var _this = this;
            $("#pagination-ul").runnerPagination({
                url: _base+"/strategy/getList",
                method: "POST",
                dataType: "json",
                processing: true,
                data : $('#queryForm').serializeArray(),
                pageSize: StrategyPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#strategyListTemple");
                        var htmlOut = template.render(data);
                        $("#strategyData").html(htmlOut);
                        _this._bindStrategy();
                    }else{
                        $("#strategyData").html("未搜索到信息");
                    }
                }
            });
        },
        _bindStrategy:function(){
        	var _this = this;
        	$(".view-strategy").bind("click", function(){
        		var policyId =  $(this).parents(".strategy-record").find("input[name='policyId']").val();
        		window.location.href = _base+"/strategy/toStrategyShow?policyId="+policyId;
			});
        	$(".edit-strategy").bind("click", function(){
        		var policyId =  $(this).parents(".strategy-record").find("input[name='policyId']").val();
			    window.location.href = _base+"/strategy/toStrategyUpdate?policyId="+policyId;
			});
        	$(".del-strategy").bind("click", function(){
        		var policyId =  $(this).parents(".strategy-record").find("input[name='policyId']").val();
        		
        		ajaxController.ajax({
   	             type: "post",
   	             dataType : "json",
   	             url: _base+"/priceElement/checkExistPolicyId",
   	             processing: true,
   	             message: "正在加载，请等待...",
   	             data : {
   	            	 "policyId" : policyId
   	            	 },
   	             success: function(data){
   	                 if(data.data.responseHeader.isSuccess){
   	                	var d = Dialog({
  	                         content:'该策略已经关联定价元素，不能删除',
  	                         ok:function(){
  	                             this.close();
  	                         }
  	                     });
  	                     d.showModal();
   	                 }else{
   	                	var d = Dialog({
   	                     content:"确定删除？",
   	                     ok:function(){
   	                     	$.ajax({
   	         					type : "post",
   	         					processing : false,
   	         					url : _base+"/strategy/delStrategy",
   	         					dataType : "json",
   	         					data : {
   	         						"policyId":policyId
   	         					},
   	         					message : "处理中..",
   	         					success : function(data) {
   	     							var d = Dialog({
   	     		                         content:data.statusInfo,
   	     		                         ok:function(){
   	     		                        	 _this._submitFrom();
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
   	                }
   	             }
   	         });
			});
        	$(".export-strategy").bind("click", function(){
        		var policyId =  $(this).parents(".strategy-record").find("input[name='policyId']").val();
			    window.location.href = _base+"/strategy/exportTxt?policyId="+policyId;
			});
        },
        _openDiv:function(){
        	$('.pop-export').show();
        },
        _importTxt:function(){
        	var _this = this;
        	if(!this._checkUploadFile()){
        		return false;
        	}
        	$.ajaxFileUpload({  
                url : _base+"/strategy/importTxt",
                secureuri : false, 
                fileElementId : 'f',
                type : 'post',  
                dataType : 'text',  
                success : function(data){
                	data = jQuery.parseJSON(jQuery(data).text());
                	Dialog({
    					title : '提示',
    					width : '180px',
    					height : '50px',
    					content : data.statusInfo,
    					okValue: "确定",
    					ok:function(){
    						this.close();
    						$('.pop-export').hide();
    						$('#txt').val('文件域');
    						$('#f').val('');
    						_this._submitFrom();
    					}
    				}).showModal();
                },  
                error : function(data, status, e){  
                	
                }  
            });  
        },
        _checkUploadFile:function(){
            var filepath = $("#txt").val();
            var extStart = filepath.lastIndexOf(".");
            var ext = filepath.substring(extStart, filepath.length).toUpperCase();
            if (ext != ".TXT") {
           	 Dialog({
					title : '提示',
					width : '200px',
					height : '50px',
					content : "只允许上传txt文件!",
					okValue: "确定",
					ok:function(){
						$("#txt").val("文件域")
	                    $("#f").text("");
						this.close();
					}
				}).showModal();
                
                return false;
            } else { 
           	 var file = document.getElementById("f").files;    
                var size = file[0].size;  
                if(size>2097152){  
               	 Dialog({
	 						title : '提示',
	 						width : '200px',
	 						height : '50px',
	 						content : "所选择的文件太大，图片大小最多支持2M!",
	 						okValue: "确定",
	 						ok:function(){
	 							$("#txt").val("文件域")
	 		                    $("#f").text("");
	 							this.close();
	 						}
	 					}).showModal();
                     return false;  
                 }   
            }
            return true;
       },
        _exportTxt:function(){
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
        _addStrategy:function(){
        	window.location.href = _base+"/strategy/toStrategyAdd";
        },
        _setPolicyType: function() {
    		$.ajax({
				url : _base + '/param/getPolicyType',
				type : "post",
				async : true,
				dataType : "json",
				timeout : "10000",
				error : function() {
					alert("服务加载出错");
				},
				success : function(data) {
					var json = eval(data);
					var obj = $("select[name='policyType']");
						$(obj).html('');
						$(obj).append('<option unid="-1" value="" uncode="" unname="">请选择</option>');
						$.each(
								json,
								function(index, item) {
									// 循环获取数据
									$(obj)
											.append('<option unid="'+json[index].id+'" value="'+json[index].paramCode+'" unname="'+json[index].paramName+'">'+json[index].paramName+'</option>');
								});
						$(obj)
								.append("<label id='accesstype_error'></label>");
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

    module.exports = StrategyPager;
});
