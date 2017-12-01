define('app/jsp/configure/addTable', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget'),
        Dialog = require("artDialog/src/dialog"),
        AjaxController=require('opt-ajax/1.0.0/index');

    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();

    //定义页面组件类
    var AddTablePager = Widget.extend({
        //属性，使用时由类的构造函数传入
        attrs: {
        },
        //事件代理
        events: {
            //key的格式: 事件+空格+对象选择器;value:事件方法
            "change #dbSelect":"getTableSelectByDb",
            "change #tableSelect":"getFieldsByDbAndTable",
            "click #submitAddTable":"submitAddTable",
            "click #checkAll":"_checkall"
        },
        //重写父类
        setup: function () {
            AddTablePager.superclass.setup.call(this);
            this._initPage();
            this._initDbSelect();
        },

        _initPage: function(){
      		//面包屑导航
      		setBreadCrumb("配置管理","DSHM加载新表");
      		//左侧菜单选中样式
      		$("#mnu_config_mng").addClass("current");
      	},
        _initDbSelect:function(){
            var _this = this;
            ajaxController.ajax({
                type: "get",
                dataType : "json",
                url: _base+"/config/loadcfg/getDbNameList",
                success: function(data){
                    if(data&&data.data){
                        var jsonData = data.data;
                        var dbNameSelect = $("#dbSelect");
                        $.each(jsonData, function(index) {
                            var dbName = $.trim(jsonData[index]);
                            dbNameSelect.append("<option value='" + dbName + "'>" + dbName + "</option>");
                        });
                    }
                    _this.getTableSelectByDb();
                }
            });
        },

        getTableSelectByDb:function(){
            var _this = this;
            var selectedDb = $("#dbSelect").val();
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/config/loadcfg/getTableListByDb",
                data:{
                  dbName: selectedDb
                },
                success: function(data){
                    if(data&&data.data){
                        var jsonData = data.data;
                        var tableNameSelect = $("#tableSelect");
                        tableNameSelect.children().remove();
                        $.each(jsonData, function(index) {
                            var tableName = $.trim(jsonData[index]);
                            tableNameSelect.append("<option value='" + tableName + "'>" + tableName + "</option>");
                        });
                    }
                    _this.getFieldsByDbAndTable();
                }
            });
        },

        getFieldsByDbAndTable:function(){
            var selectedDb = $("#dbSelect").val();
            var selectedTable = $("#tableSelect").val();
            ajaxController.ajax({
                type: "post",
                dataType : "json",
                url: _base+"/config/loadcfg/getFieldsByDbAndTable",
                data:{
                    dbName: selectedDb,
                    tableName:selectedTable
                },
                success: function(data){
                    if(data&&data.data){
                        var jsonData = data.data;
                        var template = $.templates("#tableFieldDataTmpl");
                        var outHtml = template.render(jsonData);
                        $("#tableFieldData").html(outHtml);

                        $(".editFlag").click(function(){
                            var p = $(this).parent("td").siblings().children("p");
                            var span = p.siblings("span");

                            if($(this).val()=='编辑'){
                                $(this).val("确认");
                                p.show();
                                span.hide();
                            }else{
                                $(this).val("编辑");
                                p.hide();
                                span.show();
                            }

                            p.each(function(){
                                var val = $(this).children("select").val();
                                if(val=='true'){
                                    $(this).siblings("span").text("是");
                                }else{
                                    $(this).siblings("span").text("否");
                                }
                            });

                        });

                    }
                },
                failure:function () {
                    $("#tableFieldData").empty();
                },
                error:function () {
                    $("#tableFieldData").empty();
                }
            });
        },

        _checkall: function() {
            var chkflag=$("#checkAll").prop("checked");
            $('.selectFlag').prop("checked",chkflag);
        },

        submitAddTable:function(){
            var selectDb = $("#dbSelect").val();
            var selectTable = $("#tableSelect").val();
            var indexKeyArr = new Array();
            var selectFields = new Array();
            var primaryKeys = new Array();
            $(".selectFlag").each(function(){
                if(this.checked){
                    var isIndexKey = $(this).parent().siblings().children("p").children(".asIndexSelect").val();
                    var selectFieldName = $(this).parent().siblings(".fieldName").text();
                    selectFields.push(selectFieldName);
                    if(isIndexKey=='true'){
                        indexKeyArr.push(selectFieldName);
                    }
                    
                    var isPrimaryKey = $(this).parent().siblings().children("p").children(".isPrimarySelect").val();
                    if(isPrimaryKey=='true'){
                    	primaryKeys.push(selectFieldName);
                    }
                }
            });
            var success = true;
            var msg = "";
            if(indexKeyArr.length == 0){
            	msg = '选择的列中至少有一列要作为索引';
            	success = false;
            }else if(indexKeyArr.length > 15){
            	msg = '选择的索引行最多不能超过15行';
        		success = false;
            }
            if(!success){
                var d = Dialog({
                    content: msg,
                    ok:function(){
                        this.close();
                    }
                });
                d.showModal();
                return;
            }else{
                ajaxController.ajax({
                    type: "post",
                    dataType : "json",
                    url: _base+"/config/loadcfg/addTable",
                    processing: true,
                    message: "正在加载，请等待...",
                    data:{
                        dbName: selectDb,
                        tableName:selectTable,
                        indexKeys:indexKeyArr.toString(),
                        cacheFields:selectFields.toString(),
                        primaryKeys:primaryKeys.toString()
                    },
                    success: function(data){
                        if(data){
                            var d = Dialog({
                                content:"添加成功",
                                ok:function(){
                                    this.close();
                                }
                            });
                            d.showModal();
                        }
                    }
                });
            }
        }
    });

    module.exports = AddTablePager;
});
