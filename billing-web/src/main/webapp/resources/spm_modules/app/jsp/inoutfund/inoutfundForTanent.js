define('app/jsp/inoutfund/inoutfundForTanent', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        AjaxController=require('opt-ajax/1.0.0/index'),
        Dialog=require('artDialog/src/dialog'),
        Calendar = require('arale-calendar/1.1.2/index'),
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
    var InoutfundForTanentPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 6
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "click #inOutFundQuery":"queryFundList"
        },
        
        
        //重写父类
        setup: function () {
        	InoutfundForTanentPager.superclass.setup.call(this);
            this._initSelect();
            this._bindCalendar();
            this._bindByMonth();
            this._init();
        },
        
        _init:function(){
        	this.queryFundList();
		},
        _bindCalendar:function(){
			var startCalendar = new Calendar({trigger: '#startTimeSpan'});
			var endCalendar = new Calendar({trigger: '#endTimeSpan'});
//			startCalendar.range(['2016-02-01',this._getEndDate()]);
//			endCalendar.range(['2016-02-01',this._getEndDate()]);
		},
		//获取选择范围终止时间
      	_getEndDate:function(){
      		var sysDate = new Date();
  			var year = sysDate.getFullYear();    //获取完整的年份(4位,1970-????)
  			var month = sysDate.getMonth()+1;   //获取当前月份(0-11,0代表1月)
  			//var day = sysDate.getDay; 
  			var endMonth = month;
  			var endYear = year;
  			//var endDay = day;
  			
  			if(endMonth<10){
  				endMonth = "0"+endMonth;
  			}
  			return endYear+"-"+endMonth;
      	},
		_checkDate: function(){
			var sTime = $("#startTime").val();
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
		_bindByMonth:function(){
			
		},
        _initSelect:function () {
            //初始化交易类型
            $("#optType").append("<option value='1'>存入</option>");
            $("#optType").append("<option value='2'>扣款</option>");
        },
        queryFundList:function(){
        	if(!this._checkDate()){
        		return;
        	}
            var data = $("#inOutQuery :input,#inOutQuery select").serializeArray();
            $("#pagination-ul").runnerPagination({
                url: _base+"/inOutFund/queryInOutFundForTenantList",
                method: "POST",
                dataType: "json",
                processing: true,
                data : data,
                pageSize: InoutfundForTanentPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                    if(data&&data.length>0){
                        var template = $.templates("#inOutFundTmpl");
                        var htmlOut = template.render(data);
                        $("#inOutFundList").html(htmlOut);
                    }else{
                        $("#inOutFundList").html("未搜索到信息");
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

    module.exports = InoutfundForTanentPager;
});
