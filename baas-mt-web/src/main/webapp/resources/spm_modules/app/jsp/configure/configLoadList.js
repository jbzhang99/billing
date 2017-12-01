define('app/jsp/configure/configLoadList', function (require, exports, module) {
    'use strict';
    var $ = require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    AjaxController = require('opt-ajax/1.0.0/index');
    
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    
    require("twbs-pagination/jquery.twbsPagination.min");
    require("opt-paging/aiopt.pagination");
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    
    //定义页面组件类
    var ConfigLoadPager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 6
    	},
    	//事件代理
    	events: {
    		//key的格式: 事件+空格+对象选择器;value:事件方法
    		//查询
            "click #BTN_SEARCH":"_searchBtnClick",    
            //全选
            "click #checkAll":"_checkall",    
            //批量加载
            "click #btnBatchRefreshLoader":"_refreshLoader",    
            //批量释放
            "click #btnBatchShmDelete":"_shmDelete",         
            //单个删除
            "click #btnSingleDeleteTable":"_deleteTable",
            "click #batchShmDelete":"_deleteFileds"
        },
    	//重写父类
    	setup: function () {
    		ConfigLoadPager.superclass.setup.call(this);
    		this._initPage();
    		this._bindEvents();
    		this._searchBtnClick();
    	},   
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("配置管理","DSHM加载配置");
      		//左侧菜单选中样式
      		$("#mnu_config_mng").addClass("current");
      	},
    	_bindEvents: function(){
    		var _this = this;
    		$('#tabName').bind('keypress', function(event){
				if(event.keyCode == "13"){
					_this._searchBtnClick();	
				}
			});
    	},
    	_searchBtnClick: function(){
    		var url = _base+"/config/loadcfg/getList";
    		$("#pagination-ul").runnerPagination({
	 			url: url,
	 			method: "POST",
	 			dataType: "json",
	 			processing: true,
	            data: {    	            	
	            	tabName:$('#tabName').val()//搜索框里输入的值
	            },
	           	pageSize: ConfigLoadPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	            message: "正在为您查询数据..",
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#configDataTmpl");
    					var htmlOutput = template.render(data);
    					$("#configData").html(htmlOutput);
	            	}else{
    					$("#configData").html("没有搜索到相关信息");
	            	}
	            }
    		});
    	},
    	_checkall: function() {
			var chkflag=$("#checkAll").prop("checked");
			$('input[type=checkbox][name=configId]').prop("checked",chkflag);
         },
         _searchKey:function(curr){
        	window.location.href=_base+"/config/basic/key?tableName="+$(curr).attr('tabname')+"&tableId="+$(curr).attr('tabid')+"&indexKey="+$(curr).attr('tkey');
         },
         _getFields:function(curr){
        	// console.log("进入到函数里边");
        	//$(curr).attr('title','123，tenantid，moust，ttr，ooo'); 
        	 var tabid = $(curr).attr('tabid');
 			 var tabname = $(curr).attr('tabname');
 			$.ajax({
				type : "POST",
				url :_base+"/config/loadcfg/getFields",
				data: {
					tabId : tabid,
					tabName : tabname
				},
				processing: true,
				message : "正在处理中，请稍候...",
				success : function(data) {
					var d=data.data;
					if(d.fields){
						$(curr).attr('title',d.fields); 
					}
				}
			}); 
 			  
 			  
         },
    	//加载、批量加载
    	_refreshLoader:function(flag, curr){
    		
    		var tabidArray = new Array();
    		var tabnameArray = new Array();
    		if(flag == 1){//单个加载
    			tabidArray[0] = $(curr).attr('tabid');
    			tabnameArray[0] = $(curr).attr('tabname');
    		}else{//批量加载
    			$('input:checkbox[name=configId]:checked').each(function(i){
    				tabidArray[i] = $(this).attr('tabid');//["AAA","BBB"]
    				tabnameArray[i] = $(this).attr('tabname');
    			});
    		}
    		
    		if(tabidArray.length>0 || tabnameArray.length>0){
    			$.ajax({
    				type : "POST",
    				url :_base+"/config/loadcfg/refreshLoader",
    				data: {
    					tabIds : JSON.stringify(tabidArray),
    					tabNames : JSON.stringify(tabnameArray)
    				},
    				processing: true,
    				message : "正在处理中，请稍候...",
    				success : function(data) {
    					var str = (data.data==1?'表成功加载到缓存中':'由于参数错误表未加载到缓存中');
    					Dialog({
    					    title: '提示',
    					    width: '200px',
    	        	        height: '50px',
    					    content: data.statusInfo + "，" + str
    					}).show();
    				}
    			});
    		}
    		
    	},//end of _refreshLoader
    	//批量删除
       _deleteFileds:function(curr){
    	   var tabidArray = new Array();
   		   var tabnameArray = new Array();
   		$('input:checkbox[name=configId]:checked').each(function(i){
			tabidArray[i] = $(this).attr('tabid');//["AAA","BBB"]
			tabnameArray[i] = $(this).attr('tabname');
		});
   		
   		if(tabidArray.length>0 || tabnameArray.length>0){
   			new Dialog({
    	        title: '提示',
    	        content: '确定要删除该表吗?',
    	        width: '300px',
    	        height: '60px',
    	        //hasMask: false,  //没有遮罩
    	        okValue: '确定',
    	        ok: function () {
    	            this.title('提交中…');
    	            $.ajax({
    	            	type : "POST",
					url :_base+"/config/loadcfg/shmFieldsDelete",
					data: {
					tabIds : JSON.stringify(tabidArray),
					tabNames : JSON.stringify(tabnameArray)
					},
					processing: true,
					message : "正在处理中，请稍候...",
					success : function(data) {
					var str = (data.data=="000000"?'删除成功':'由于参数错误表未能成功删除');
					Dialog({
					    title: '提示',
					    width: '200px',
	        	        height: '50px',
					    content: data.statusInfo + "，" + str
					}).show();
					if(data.data=="000000"){
						pager._searchBtnClick();//刷新页面
					}
					
				}
			});
    	 },
    	 cancelValue: '取消',
	     cancel: function () {}
	    }).show();
		}else{
			Dialog({
			    title: '提示',
			    width: '200px',
    	        height: '50px',
			    content: "请选择要删除的选项！"
			}).show();
			
		}
   		
    	},
    	//释放、批量释放
    	_shmDelete: function(flag, curr){
    		
    		var tabidArray = new Array();
    		var tabnameArray = new Array();
    		
    		if(flag == 1){//单个释放
    			tabidArray[0] = $(curr).attr('tabid');
    			tabnameArray[0] = $(curr).attr('tabname');
    		}else{//批量释放
    			$('input:checkbox[name=configId]:checked').each(function(i){
    				tabidArray[i] = $(this).attr('tabid');//["AAA","BBB"]
    				tabnameArray[i] = $(this).attr('tabname');
    			});
    		}
    		
    		if(tabidArray.length>0 || tabnameArray.length>0){
    			$.ajax({
    				type : "POST",
    				url :_base+"/config/loadcfg/shmDelete",
    				data: {
    					tabIds : JSON.stringify(tabidArray),
    					tabNames : JSON.stringify(tabnameArray)
    				},
    				processing: true,
    				message : "正在处理中，请稍候...",
    				success : function(data) {
    					var str = (data.data==1?'释放缓存成功':'由于参数错误表未能成功释放缓存');
    					Dialog({
    					    title: '提示',
    					    width: '200px',
    	        	        height: '50px',
    					    content: data.statusInfo + "，" + str
    					}).show();
    				}
    			});
    		}
    		
    	},//end of _shmDelete
    	
    	//删除
    	_deleteTable: function(curr){
    		var _this = this;
    		new Dialog({
    	        title: '提示',
    	        content: '确定要删除该表吗?',
    	        width: '300px',
    	        height: '60px',
    	        //hasMask: false,  //没有遮罩
    	        okValue: '确定',
    	        ok: function () {
    	            this.title('提交中…');
    	            $.ajax({
        				type : "POST",
        				url :_base+"/config/loadcfg/deleteTable",
        				data: {
        					tabId : $(curr).attr('tabid'),
        					tabName : $(curr).attr('tabname')
        				},
        				processing: true,
        				message : "正在处理中，请稍候...",
        				success : function(data) {
        					pager._searchBtnClick();//刷新页面
        					var str = (data.data=='000000'?'表删除成功':'由于参数错误表未能成功删除表');
        					Dialog({
        					    title: '提示',
        					    width: '200px',
        	        	        height: '50px',
        					    content: data.statusInfo + "，" + str
        					}).show();
        				}
        			});
    	        },
    	        cancelValue: '取消',
    	        cancel: function () {}
    	    }).show();
    	}//end of _deleteTable
    	
    	
    	
    	
    	
    });
    
    module.exports = ConfigLoadPager
});

