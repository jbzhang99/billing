define('app/jsp/invoice/invoice', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog'),
        Calendar = require('arale-calendar/1.1.2/index-month'),
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
    var InvoicePager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #submit":"_submitInvoiceFrom",
            "click #BTN_EXPORT":"_exportToExcel",
            "click #BTN_IMPORT":"_openDiv",
            "click #import":"_importExcel"
        },
        //重写父类
        setup: function () {
            InvoicePager.superclass.setup.call(this);
            this._initPage();
            this._bindByMonth();
        },
        _initPage:function () {
            //初始下拉菜单数据
//        	var timesStart = moment("20160201", "YYYYMMDD");
//        	var timesEnd = moment().subtract(0,"month");
//        	var num = timesEnd.diff(timesStart, 'months')
//        	$("#billMonth").html("");
//            for(var i=0;i<=num;i++){
//                var time = moment().subtract(i,"month");
//                $("#billMonth").append("<option value='"+time.format("YYYYMM")+"'>"+time.format("YYYY[年]MM[月]")+"</option>");
//            }
            $("#billMonth").html("");
            for(var i=0;i<6;i++){
                var time = moment().subtract(i,"month");
                $("#billMonth").append("<option value='"+time.format("YYYYMM")+"'>"+time.format("YYYY[年]MM[月]")+"</option>");
            }
            
            //初始化日历选择
            $('#byMonthChecked').prop("checked",false);
            $('#billMonth').attr("disabled","disabled");
            $('#startTime').removeAttr("disabled");
    		$('#endTime').removeAttr("disabled");
            $("#byMonthChecked").click(function(){
            	if($(this).prop("checked")){
            		$('#billMonth').removeAttr("disabled");
            		$('#startTime').attr("disabled","disabled");
            		$('#endTime').attr("disabled","disabled");
            		$('#byMonth').val('1');
            	}else{
            		$('#billMonth').attr("disabled","disabled");
            		$('#startTime').removeAttr("disabled");
            		$('#endTime').removeAttr("disabled");
            		$('#byMonth').val('0');
            	}
            });
            $('#byMonth').val('0');
            $('#checkedStatus1').prop("checked",true);
            $('#checkedStatus2').prop("checked",true);
            this._bindCalendar();
            this._submitInvoiceFrom();
        },
        _openDiv:function(){
        	$('.pop-export').show();
        },
        _importExcel:function(){
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
             if (ext != ".XLSX" && ext != ".XLS") {
            	 Dialog({
					title : '提示',
					width : '200px',
					height : '50px',
					content : "只允许上传excel文件!",
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
        _bindCalendar:function(){
			var startCalendar = new Calendar({trigger: '#startTimeSpan'});
			var endCalendar = new Calendar({trigger: '#endTimeSpan'});
			startCalendar.range([this._getStartDate(),this._getEndDate()]);
			endCalendar.range([this._getStartDate(),this._getEndDate()]);
			startCalendar.focus(this._getStartDate());
			endCalendar.focus(this._getEndDate());
			$('#startTime').val(this._getStartDate().substring(0,7));
			$('#endTime').val(this._getEndDate().substring(0,7));
		},
		//获取选择范围开始时间
      	_getStartDate:function(){
      		var sysDate = new Date();
  			var year = sysDate.getFullYear();    //获取完整的年份(4位,1970-????)
  			var month = sysDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
  			if(month>=6){
  				var startMonth = month-5;
  				startMonth = "0"+startMonth;
  				return year+"-"+startMonth+"-01";
  			}else{
  				var startMonth = 12+(month-5);
  				if(startMonth <10){
  					startMonth = "0"+startMonth;
  				}
  				var startYear = year-1;
  				return startYear+"-"+startMonth+"-01";
  			}
      	},
		//获取选择范围终止时间
      	_getEndDate:function(){
      		var sysDate = new Date();
  			var year = sysDate.getFullYear();    //获取完整的年份(4位,1970-????)
  			var month = sysDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
//  			if(month>=1){
//  				var startMonth = month-1;
//  				startMonth = "0"+startMonth;
//  				return year+"-"+startMonth+"-01";
//  			}else{
//  				var startMonth = 12+(month-1);
//  				if(startMonth <10){
//  					startMonth = "0"+startMonth;
//  				}
//  				var startYear = year-1;
//  				return startYear+"-"+startMonth+"-01";
//  			}
  			if(month <10){
  				month = "0"+month;
				}
  			return year+"-"+month+"-01";
      	},
		_checkDate: function(){
			var sTime = $("#startTime").val();
			var dTime = $("#endTime").val();
			if(sTime!="" && dTime!=""){
			    //js判断日期
			    var begin = moment(sTime,"YYYY-MM");
				var end = moment(dTime,"YYYY-MM");
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
			}
			var byMonth = $('#byMonth').val();
			if('0'==byMonth && (sTime=="" || dTime=="")){
				Dialog({
					title : '提示',
					width : '200px',
					height : '50px',
					content : "开始时间和结束时间不能为空!",
					okValue: "确定",
					ok:function(){
						this.close();
					}
				}).showModal();
		         return false;
			}
			return true;
		},
		_bindByMonth:function(){
			
		},
		_submitInvoiceFrom:function(){
			var _this = this;
			if('0'==$('#byMonth').val()){
				if(!this._checkDate()){
					return false;
				}
			}
			if($('#checkedStatus1').prop("checked") && $('#checkedStatus2').prop("checked")){
				$('#status').val("");
			}else if(!$('#checkedStatus1').prop("checked") && !$('#checkedStatus2').prop("checked")){
				$('#status').val("2");
			}else if($('#checkedStatus1').prop("checked")){
				$('#status').val("1");
			}else{
				$('#status').val("0");
			}
			this._getQueryParams();
            $("#pagination-ul").runnerPagination({
                url: _base+"/invoice/getList",
                method: "POST",
                dataType: "json",
                processing: true,
                data : $('#queryForm').serializeArray(),
                pageSize: InvoicePager.DEFAULT_PAGE_SIZE,
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

    module.exports = InvoicePager;
});
