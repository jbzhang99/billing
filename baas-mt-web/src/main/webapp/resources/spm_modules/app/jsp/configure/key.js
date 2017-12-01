define('app/jsp/configure/key', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog"),
    AjaxController=require('opt-ajax/1.0.0/index');
    require("bootstrap-paginator/bootstrap-paginator.min");
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //删除对话框
    var deleteDialog;
    //查询成功标记
    var queryFlag=0;
    
    
    //定义页面组件类
    var KeyPager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	//事件代理
    	events: {
    		//key的格式: 事件+空格+对象选择器;value:事件方法
            "click #searchBtn":"_searchBtnClick" ,
            "click #deleteKeyBtn":"_showDeleteDialog",
            "click #gobackBtn":"_goback"
        },
    	//重写父类
    	setup: function () {
    		KeyPager.superclass.setup.call(this);
    		this._initPage();
    	},
    	_initPage: function(){
      		//面包屑导航
      		setBreadCrumb("配置管理","缓存Key查询与删除");
      		//左侧菜单选中样式
      		$("#mnu_cache_mng").addClass("current");
      		var tableName=this.get('tableName');
      		var tableId=this.get('tableId');
      		var indexKey=this.get('indexKey');
      		if(tableName!=null&&tableName!=""&&tableName!='undefined'&& tableName.length > 0&&tableId!=null&&tableId!='undefined'&&tableId!=""){
      			var keytid=tableName+":"+tableId;
      			$("#keyTable").val(keytid);
      			$("#keyColumn").val(indexKey);
      		}
      		
      	},
    	_showDeleteDialog:function(){
    		if(queryFlag==1){
    			var _this = this;
        		deleteDialog = Dialog({
        	        title: '提示',
        	        content: '确定要删除此Key吗?',
        	        width: '300px',
        	        height: '60px',
        	        okValue: '确定',
        	        ok: function () {
        	            this.title('提交中…');
        	            _this._deleteBtnClick();
        	        },
        	        cancelValue: '取消',
        	        cancel: function () {}
        	    });
        		deleteDialog.show();
    		}else{
    			Dialog({
				    title: '提示',
				    width: '200px',
        	        height: '50px',
				    content: '无此key不能删除！'
				}).show();
    		}
    		
    	},
    	_goback:function(){
    		history.back(-1);
    	},
    	_searchBtnClick:function(){
    		var keyType = $("#keyType").val();
    		var keyTable = $("#keyTable").val();
    		var keyColumn = $("#keyColumn").val();
    		ajaxController.ajax({
    			type: "post",
    			processing: false,
    			message: "正在提交，请稍候...",
    			url: _base+'/config/basic/query',
    			data: {
    				keyType:keyType,
    				keyTable:keyTable,
    				keyColumn:keyColumn
				},
    			success: function(data){
    				if(data.statusCode=="1"){
    					if(data.data==""||typeof(data.data)=="undefined"||data.data==null){
        					$("#keyResult").html("无数据");
        					queryFlag=0;
        				}else{
        					$("#keyResult").html(data.data);
        					queryFlag=1;
        				}
    				}else{
    					$("#keyResult").html(data.statusInfo);
    					queryFlag=0;
    				}
    			}
				
    		})
    	},
    	_deleteBtnClick:function(){
    		var keyType = $("#keyType").val();
    		var keyTable = $("#keyTable").val();
    		var keyColumn = $("#keyColumn").val();
    		ajaxController.ajax({
    			type: "post",
    			processing: false,
    			message: "正在提交，请稍候...",
    			url: _base+'/config/basic/delete',
    			data: {
    				keyType:keyType,
    				keyTable:keyTable,
    				keyColumn:keyColumn
				},
    			success: function(data){
    				if(data.statusCode=="1"){
        					$("#keyResult").html("无数据");
        					Dialog({
        					    title: '提示',
        					    width: '200px',
        	        	        height: '50px',
        					    content: '删除成功！'
        					}).show();
    				}else{
    					Dialog({
    					    title: '提示',
    					    width: '200px',
    	        	        height: '50px',
    					    content: '删除失败！'+data.statusInfo
    					}).show();
    				}
    				
    			}
				
    		})
    	}
    });
    
    module.exports = KeyPager
});
