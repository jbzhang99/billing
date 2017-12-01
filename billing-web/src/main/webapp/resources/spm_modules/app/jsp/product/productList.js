define('app/jsp/product/productList', function (require, exports, module) {
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
    var ProListPager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        Statics: {
            DEFAULT_PAGE_SIZE: 5
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
        	"click #productSearch":"_searchProducts",
        	"click #productAdd":"_toAddPage"	
        		
        		
        		
        },
        //重写父类
        setup: function () {
        	ProListPager.superclass.setup.call(this);
          // this._getProList();
        	this._bindDel();
        	this._bindEdit();
        	this. _bindView();
        	this._searchProducts();
        },
        _bindEdit:function(){
        	
        	$(".edit_product").bind("click",function() {
        		var _this=this;
        		window.location.href= _base + "/product/toEditPage?mainProId="+$(_this).attr('mainId')+"&mainTag="+$(_this).attr('mainTag');
        	});
        },
       _bindView:function(){
        	$(".view_product").bind("click",function() {
        		var _this=this;
        		window.location.href= _base + "/product/toViewPage?mainProId="+$(_this).attr('mainId')+"&mainTag="+$(_this).attr('mainTag');
        	});
        },
        _bindDel:function(){
        	var this_ = this;
			$(".del_product").bind("click",function() {
					var _this = this;
					Dialog({  
								content : "确定要删除吗？",
								okValue : "确定",
								ok : function() {
									$.ajax({
											type : "post",
											processing : false,
											url : _base + "/product/delProduct",
											dataType : "json",
											data : {
												mainProId : $(_this).attr('mainId'),
												mainTag : $(_this).attr('mainTag')
											},
											message : "正在加载数据..",
											success : function(data) {
											Dialog({
											title : '提示',
											width : '200px',
											height : '50px',
											content : data.statusInfo,
											kValue : "确定",
											ok : function() {
													this.close;
												    this_._searchProducts(1,ProListPager.DEFAULT_PAGE_SIZE);
													}
												}).showModal();}
														});
											},
											cancelValue : "取消",
											cancel : function() {
												this.close;
											}
										}).showModal();

							})
        },
        _toAddPage:function(){
        	window.location.href=_base+"/product/defineProList";
        },
        _searchProducts:function(){
        	var mainProCode=$("#mainProId").val();
        	var mainProName=$("#mainProName").val();
        	
        	var _this = this;
            $("#pagination-ul").runnerPagination({
                url: _base+"/product/getProducts",
                method: "POST",
                dataType: "json",
                processing: true,
                data:  {
                	mainProId:mainProCode,
        	        mainProName:mainProName		
				},
                pageSize: ProListPager.DEFAULT_PAGE_SIZE,
                visiblePages:5,
                message: "正在为您查询数据..",
                render: function (data) {
                	
                    if(data&&data.length>0){
                        var template = $.templates("#productListTemple");
                        var htmlOut = template.render(data);
                        $("#productData").html(htmlOut);
                        _this._bindDel();
                        _this._bindEdit();
                        _this._bindView();
                    }else{
                        $("#productData").html("未搜索到信息");
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

    module.exports = ProListPager;
});
