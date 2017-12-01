define('app/jsp/configure/basicService', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Calendar = require('arale-calendar/1.1.2/index'),
	Select = require('arale-select/0.11.1/index'),
	Paging = require('paging/0.0.1/paging'),
    AjaxController=require('opt-ajax/1.0.0/index');
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("bootstrap-paginator/bootstrap-paginator.min");
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    
    //定义页面组件类
    var DiscProductPager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 20
    	},
    	//事件代理
    	events: {
    		//key的格式: 事件+空格+对象选择器;value:事件方法
            "click #BTN_SEARCH":"_searchBtnClick"         
        },
    	//重写父类
    	setup: function () {
    		DiscProductPager.superclass.setup.call(this);
    		this._bindEvents();
    		this._bindCalendar();
    		this._bindSelect();
    		this._bindPaging();
    		//初始化执行搜索
    		this._searchAPIDocs(1,DiscProductPager.DEFAULT_PAGE_SIZE);
    	},
    	//日期
    	_bindCalendar: function(){
    		new Calendar({trigger: '#date-nothing'});
    	},
    	//下拉（orderOpen.jsp）
    	_bindSelect:function(){
    		/**
    		$.ajax({
    			url : '../discountProduct/initSelect',
    			type : "post",
    			async : true,
    			dataType : "json",
    			timeout : "10000",
    			error : function() {
    				alert("服务加载出错");
    			},
    			success : function(data) {
    				var jsonData = data.data;
    				var selObj = $("#selectId");
    				selObj.empty();
    				$.each(jsonData, function(index, item) {
    					var value = $.trim(jsonData[index].value);
    					var name = $.trim(jsonData[index].name);
    					selObj.append("<option value='" + value + "'>" + name + "</option>");
    				});
    			}
    		});
    		*/
    		$.ajax({
		        url:'../discountProduct/initSelect',
		        type: 'get',
		        dataType: 'json'
		    }).done(function (data) {
		    	var jsonData = data.data;
				var selObj = $("#selectId");
				selObj.empty();
				$.each(jsonData, function(index, item) {
					var value = $.trim(jsonData[index].value);
					var name = $.trim(jsonData[index].name);
					selObj.append("<option value='" + value + "'>" + name + "</option>");
				});
		    })
    	},
    	//分页控件
    	_bindPaging:function(){
    		seajs.use(["paging/0.0.1/index.css"]);
    		var goto = function (page) {
    		    $.ajax({
    		        url:_base+'/demo/paging',
    		        type: 'get',
    		        dataType: 'json'
    		    }).done(function (data) {

    		        // 模拟分页数据 S
    		        $.each(data.view, function () {
    		            this.name = this.name + '-' + new Date().getTime()
    		        })
    		        // 模拟分页数据 E

    		        var listhtml = Paging.mustache.render($('#listDataTmpl').html(), data)

    		        var paginghtml = Paging.render({
    		            currentPage: page,
    		            pageCount: data.pagecount,
    		            link:''
    		        })

    		        $('#listData').html(listhtml)
    		        $('#pageview').html(paginghtml)
    		    })
    		}
    		goto(1)//默认显示首页

    		$('div').on('click', 'a.ui-paging-item,a.ui-paging-next,a.ui-paging-prev', function () {
    		    var $this = $(this);
    		    var page = $this.attr('href')
    		    goto(page)
    		    return false
    		}).on('click', '.ui-paging-goto', function () {
    		    var $this = $(this)
    		    var $input = $this.prev('.ui-paging-which').find('input')
    		    var page = $input.val()
    		    // 必须填入数字
    		    page = parseInt(page, 10)
    		    if (isNaN(page)) {
    		        page = 1
    		    }
    		    $input.val(page)
    		    goto(page)
    		    return false
    		})
    	},
    	//构造分页容器
    	_buildPaginationContainer: function(currentPage,totalPages){
			var _this = this;
			var options = {
				itemTexts: function (type, page, current) {
                    switch (type) {
	                    case "first":
	                        return "首页";
	                    case "prev":
	                        return "上一页";
	                    case "next":
	                        return "下一页";
	                    case "last":
	                        return "尾页";
	                    case "page":
	                        return page;
                    }
                },
    			alignment:"center",
				totalPages: totalPages,
			    currentPage: currentPage,
			    numberOfPages:8,
			    onPageClicked: function(e,originalEvent,type,page){
			    	e.stopImmediatePropagation();
			    	var currentTarget = $(e.currentTarget);
			    	var oldpages = currentTarget.bootstrapPaginator("getPages");
					_this._searchAPIDocs(page,DiscProductPager.DEFAULT_PAGE_SIZE);
			    }
			}
			$('#pagination-content').bootstrapPaginator(options);
		},
    	_bindEvents: function(){
    		var _this = this;
    		$('#API_KEY').bind('keypress',function(event){
				if(event.keyCode == "13"){
					_this._searchAPIDocs(1,DiscProductPager.DEFAULT_PAGE_SIZE);
				}
			});
    	},
    	_searchBtnClick: function(){
    		this._searchAPIDocs(1,DiscProductPager.DEFAULT_PAGE_SIZE);	
    	},
    	_searchAPIDocs: function(pageNo,pageSize){//搜索API
    		var _this = this;
    		var apiKey=$("#API_KEY").val();
    		var owner=this.get("owner");
    		var ownerType=this.get("ownerType");
			var p = {
				keywords: $.trim(apiKey),
				owner: owner,
				ownerType: ownerType,
				pageInfo: {
					pageNo: pageNo,
					pageSize: pageSize
				}
			};
    		ajaxController.ajax({
    			type: "post",
    			processing: false,
    			message: "正在为您搜索结果，请稍候...",
    			url: _base+"/demo/paging",
    			data: {
				},
    			success: function(data){
    				var d=eval(data);
    			}
    		})
    	}
    });
    
    module.exports = DiscProductPager
});
