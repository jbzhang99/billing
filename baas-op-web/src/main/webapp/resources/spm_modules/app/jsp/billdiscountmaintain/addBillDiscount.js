define('app/jsp/billdiscountmaintain/addBillDiscount', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Calendar = require('arale-calendar/1.1.2/index-debug'),
    Dialog = require("artDialog/src/dialog"),
    //Validator = require('arale-validator/0.10.2/index-debug'),
    moment = require('moment/2.9.0/moment'),
    AjaxController = require('opt-ajax/1.0.0/index');
    
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("app/util/jsviews-ext");
    require("bootstrap-paginator/bootstrap-paginator.min");
    require("twbs-pagination/jquery.twbsPagination.min");
    require("opt-paging/aiopt.pagination");
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    
    var viewOrUpdateflag = null;
    var giftProductIds = null;
    var mz_effectDate_calendar, mz_expireDate_calendar, mz_effectiveDate_calendar, 
	  	mj_effectDate_calendar, mj_expireDate_calendar,
	  	dz_effectDate_calendar, dz_expireDate_calendar,
	  	bd_effectDate_calendar, bd_expireDate_calendar,
	  	fd_effectDate_calendar, fd_expireDate_calendar = null;
    
    String.prototype.startWith = function(compareStr){
		return this.indexOf(compareStr) == 0;
		};
    
    //定义页面组件类
    var AddBillDiscountPager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 6
    	},
    	//事件代理
    	events: {
    		//key的格式: 事件+空格+对象选择器;value:事件方法
    		"click .cancel":"_goBackBtnClick",										//返回方法
            "click #mz_bs_checkAll":"_mz_bscheckall",									//满赠-账单科目全选
            "click #mz_sp_checkAll":"_mz_spcheckall",									//满赠-赠品全选
            "click #mz_yhq_checkAll":"_mz_yhqcheckall",									//满赠-优惠券
            "click #mz_btn_bill_subject_query":"_mz_searchBillSubjectBtnClick",			//满赠-账单科目查询
            "click #mz_btn_salable_product_query":"_mz_searchSalableProductBtnClick",	//满赠-赠品查询
            "click #mz_btn_coupon_product_query":"_mz_searchCouponProductBtnClick",		//满赠-优惠券查询
            "click #mz_btn_bill_discount_save":"_mz_saveBillDiscountClick",				//满赠-保存
            	
        	"click #mj_bs_checkAll":"_mj_bscheckall",							//满减-账单科目全选
        	"click #mj_btn_bill_subject_query":"_mj_searchBillSubjectBtnClick",	//满减-账单科目查询
        	"click #mj_btn_bill_discount_save":"_mj_saveBillDiscountClick",		//满减-保存
        	
    		"click #dz_bs_checkAll":"_dz_bscheckall",							//折扣-账单科目全选
    		"click #dz_btn_bill_subject_query":"_dz_searchBillSubjectBtnClick",	//折扣-账单科目查询
    		"click #dz_btn_bill_discount_save":"_dz_saveBillDiscountClick",		//折扣-保存
    			
			"click #bd_bs_checkAll":"_bd_bscheckall",							//保底-账单科目全选
			"click #bd_btn_bill_subject_query":"_bd_searchBillSubjectBtnClick",	//保底-账单科目查询
			"click #bd_btn_bill_discount_save":"_bd_saveBillDiscountClick",		//保底-保存
				
			"click #fd_bs_checkAll":"_fd_bscheckall",							//封顶-账单科目全选
			"click #fd_btn_bill_subject_query":"_fd_searchBillSubjectBtnClick",	//封顶-账单科目查询
			"click #fd_btn_bill_discount_save":"_fd_saveBillDiscountClick"		//封顶-保存
			
        },
    	setup: function () {
    		AddBillDiscountPager.superclass.setup.call(this);
    		viewOrUpdateflag = $("input[name='viewOrUpdateflag']").val();
    		giftProductIds = "";
    		this._initPage();
    		this._bindEvents();
    		this._bindCalendar();
//    		this._getRelatedSubjecSelect();	//下拉菜单：加载关联科目（满减、保底、封顶）
    		this._getCostUnit();	//获得优惠单位（满赠、满减）
    		this._getDiscountUnit();		//获得优惠类型
    		this._getReduceType();			//获得优惠券类型
    		this._getDiscountRullType();	//获得优惠规则类型（满减）
//    		this._mz_getServiceType();		//下拉菜单：满赠-加载业务类型（满减）
//    		this._mz_getGroupBillingType();	//下拉菜单：满赠-加载计费类型（满减）
    		this._mz_getGiftActiveMode();	//下拉菜单：加载赠送业务生效方式（满减）
    		this._mz_getGiftActivePeriod();	//下拉菜单：加载赠送业务周期（满减）
    		this._dz_bindTimePeriodClick();	//折扣：选择时段
    		if(viewOrUpdateflag != null && viewOrUpdateflag != ''){
    			this._getBillDiscountInfo();//数据回填（查看、修改）
    		}else{
    			this._clearHistory();
    		}
    		//this._bindValidator();
    	},
    	_bindEvents: function(){
    		var _this = this;
    		$('#API_KEY').bind('keypress',function(event){
				if(event.keyCode == "13"){
				}
			});
    		
    		this._bindAllPrdDiscount($("#mz_form"), "mz");
    		this._bindAllPrdDiscount($("#mj_form"), "mj");
    		this._bindAllPrdDiscount($("#dz_form"), "dz");
    		
    		this._bindInputLimit();
    		this._bindNumLimit();
    	},
    	_bindAllPrdDiscount:function(form, discountType){
    		$(form).find("input[name='allPrdDiscount']").bind('click', function(event){
    			var checked = $(this).prop("checked");
    			var checkbox = $("#"+discountType+"_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
    			var div = $(this).parents(".configure").find('.configure-table');
    			if(checked){
        			$(div).hide();
        			$('#'+discountType+'_selectedBillSubject').hide();
        			$(form).find("input[name='mainProductId']").val('');
        			$(form).find("input[name='mainProductName']").val('');
    			}else{
    				$(div).show();
        			$('#'+discountType+'_selectedBillSubject').show();
    			}
			});
    	},
    	_bindInputLimit: function(){
    		$(".price-input").on('keyup', function (event) {
        	    var $amountInput = $(this);
        	    //响应鼠标事件，允许左右方向键移动 
        	    event = window.event || event;
        	    if (event.keyCode == 37 | event.keyCode == 39) {
        	        return;
        	    }
        	    //先把非数字的都替换掉，除了数字和. 
        	    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
        	        //只允许一个小数点              
        	        replace(/^\./g, "").replace(/\.{2,}/g, ".").
        	        //只能输入小数点后两位
        	        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d\d\d).*$/, '$1$2.$3'));
	        	    while($amountInput.val().startWith("00")){
	        	    	$amountInput.val($amountInput.val().replace("00", "0"));
	        	    }
	        	    if($amountInput.val().startWith("0") && !$amountInput.val().startWith("0.")){
	        	    	$amountInput.val("0");
	        	    }
        	        });
    		
        	$(".price-input").on('blur', function () {
        	    var $amountInput = $(this);
        	    //最后一位是小数点的话，移除
        	    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
        	});
    	},
    	_bindNumLimit: function(){
    		$(".price-num-input").on('keyup', function (event) {
        	    var $amountInput = $(this);
        	    //响应鼠标事件，允许左右方向键移动 
        	    event = window.event || event;
        	    if (event.keyCode == 37 | event.keyCode == 39) {
        	        return;
        	    }
        	    //先把非数字的都替换掉，除了数字和. 
        	    $amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
        	        //只允许一个小数点              
        	        replace(/^\./g, "").replace(/\.{2,}/g, ".").
        	        //只能输入小数点后两位
        	        replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d\d\d\d\d).*$/, '$1$2.$3'));
	        	    while($amountInput.val().startWith("00")){
	        	    	$amountInput.val($amountInput.val().replace("00", "0"));
	        	    }
	        	    if($amountInput.val().startWith("0") && !$amountInput.val().startWith("0.")){
	        	    	$amountInput.val("0");
	        	    }
	        	    if($amountInput.val()>=2 && "."!=$amountInput.val().charAt(1)){
	        	    	$amountInput.val($amountInput.val().charAt(0));
	        	    }
        	        });
    		
        	$(".price-input").on('blur', function () {
        	    var $amountInput = $(this);
        	    //最后一位是小数点的话，移除
        	    $amountInput.val(($amountInput.val().replace(/\.$/g, "")));
        	});
    	},
    	_initPage: function(){
    		//左侧菜单选中样式
      		$("#mnu_discount_mng").addClass("current");
         	//导航
    		if($("input[name='discountId']").val()){
    			if($("input[name='viewOrUpdateflag']").val() == 'v'){
    				setBreadCrumb("优惠策略管理","查看优惠策略");
    			}else{
    				setBreadCrumb("优惠策略管理","修改优惠策略");
    			}
    		}else{
    			setBreadCrumb("优惠策略管理","新增优惠策略");
    		}
    		
    		$("#mj_form,#mz_form").find("input[name='allPrdDiscount']").prop("checked",false);
    		
    		$(".configure-tab p").click(function () {
               $("#mz_form").find("input[name='giftType']").val($(this).attr("pvalue"));
            });
    		
    		$(".configure-tab p").click(function () {
                $(this).parent().children('.configure-tab p').each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
    		
			 $(".configure-tab p").click(function () {
			    var index=$(this).parent().children('.configure-tab p').index(this);
			      if(index==0){
			      $('.configure-tab-cnt1').show();
			      $('.configure-tab-cnt2').hide();
				  $('.configure-tab-cnt3').hide();
				  $('.configure-tab-cnt4').hide();
			   }
			  if(index==1){
			      $('.configure-tab-cnt1').hide();
			      $('.configure-tab-cnt2').show();
				  $('.configure-tab-cnt3').hide();
				  $('.configure-tab-cnt4').hide();
			   }
			   if(index==2){
			      $('.configure-tab-cnt1').hide();
			      $('.configure-tab-cnt2').hide();
				  $('.configure-tab-cnt3').show();
				  $('.configure-tab-cnt4').hide();
			   }
			   if(index==3){
			      $('.configure-tab-cnt1').hide();
			      $('.configure-tab-cnt2').hide();
				  $('.configure-tab-cnt3').hide();
				  $('.configure-tab-cnt4').show();
			   }
			  });
      	},
      	_getLength:function(str){
      		var byteLen=0,len=str.length;
      	　　if(str){
      	　　　　for(var i=0; i<len; i++){
      	　　　　　　if(str.charCodeAt(i)>255){
      	　　　　　　　　byteLen += 2;
      	　　　　　　}
      	　　　　　　else{
      	　　　　　　　　byteLen++;
      	　　　　　　}
      	　　　　}
      	　　　　return byteLen;
      	　　}
      	　　else{
      	　　　　return 0;
      	　　}
      	},
      	_clearHistory:function(){
      		$("#mj_bs_checkAll").prop("checked", false);
      		$("#mz_bs_checkAll").prop("checked", false);
      		$("#mz_sp_checkAll").prop("checked", false);
      		$("#dz_bs_checkAll").prop("checked", false);
      	},
      	_checkDate: function(startTime, endTime){
			var sTime = $(startTime).val();
			var dTime = $(endTime).val();
			if(sTime!="" && dTime!=""){
			      //js判断日期
			    var begin = moment(sTime,"YYYY-MM-DD");
				var end = moment(dTime,"YYYY-MM-DD");
				if(end.diff(begin)<0){
					Dialog({
						width : '200px',
						height : '50px',
						content : "失效日期不能小于生效日期!",
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
    	//绑定日期控件
    	_bindCalendar: function(){
    		//满赠-生效日期
    		mz_effectDate_calendar = new Calendar({
    			trigger: '#mz_effectDate_be',
    			output: '#mz_effectDate_be',
    			align: {selfXY: [0, 0], baseElement: '#mz_effectDate_be', baseXY: [0, '100%']}
    		});
    		//满赠-失效日期
    		mz_expireDate_calendar = new Calendar({
    			trigger: '#mz_expireDate_be',
    			output: '#mz_expireDate_be',
    			align: {selfXY: [0, 0], baseElement: '#mz_expireDate_be', baseXY: [0, '100%']}
    		});
    		//满赠-赠送业务生效时间
    		mz_effectiveDate_calendar = new Calendar({
    			trigger: '#mz_effectiveDate_be',
    			output: '#mz_effectiveDate_be',
    			align: {selfXY: [0, 0], baseElement: '#mz_effectiveDate_be', baseXY: [0, '100%']
        		}
    		});
    		mz_effectiveDate_calendar = new Calendar({
    			trigger: '#xj_effectiveDate_be',
    			output: '#xj_effectiveDate_be',
    			align: {selfXY: [0, 0], baseElement: '#xj_effectiveDate_be', baseXY: [0, '100%']
        		}
    		});
    		mz_effectiveDate_calendar = new Calendar({
    			trigger: '#xnb_effectiveDate_be',
    			output: '#xnb_effectiveDate_be',
    			align: {selfXY: [0, 0], baseElement: '#xnb_effectiveDate_be', baseXY: [0, '100%']
        		}
    		});
    		
    		//满减-生效日期
    		mj_effectDate_calendar = new Calendar({
    			trigger: '#mj_effectDate_be',
    			output: '#mj_effectDate_be',
    			align: {selfXY: [0, 0], baseElement: '#mj_effectDate_be', baseXY: [0, '100%']}
    		});
    		//满减-失效日期
    		mj_expireDate_calendar = new Calendar({
    			trigger: '#mj_expireDate_be',
    			output: '#mj_expireDate_be',
    			align: {selfXY: [0, 0], baseElement: '#mj_expireDate_be', baseXY: [0, '100%']}
    		});
    		
    		//折扣-折扣日期
    		/**new Calendar({
    			trigger: '#dz_discountDate',
    			output: '#dz_discountDate_be',
    			align: {selfXY: [0, 0], baseElement: '#dz_discountDate_be', baseXY: [0, '100%']}
    		});*/
    		//折扣-生效日期
    		dz_effectDate_calendar = new Calendar({
    			trigger: '#dz_effectDate_be',
    			output: '#dz_effectDate_be',
    			align: {selfXY: [0, 0], baseElement: '#dz_effectDate_be', baseXY: [0, '100%']}
    		});
    		///折扣-失效日期
    		dz_expireDate_calendar = new Calendar({
    			trigger: '#dz_expireDate_be',
    			output: '#dz_expireDate_be',
    			align: {selfXY: [0, 0], baseElement: '#dz_expireDate_be', baseXY: [0, '100%']}
    		});
    		
    		//保底-生效日期
    		bd_effectDate_calendar = new Calendar({
    			trigger: '#bd_effectDate_be',
    			output: '#bd_effectDate_be',
    			align: {selfXY: [0, 0], baseElement: '#bd_effectDate_be', baseXY: [0, '100%']}
    		});
    		///保底-失效日期
    		bd_expireDate_calendar = new Calendar({
    			trigger: '#bd_expireDate_be',
    			output: '#bd_expireDate_be',
    			align: {selfXY: [0, 0], baseElement: '#bd_expireDate_be', baseXY: [0, '100%']}
    		});
    		
    		//封顶-生效日期
    		fd_effectDate_calendar = new Calendar({
    			trigger: '#fd_effectDate_be',
    			output: '#fd_effectDate_be',
    			align: {selfXY: [0, 0], baseElement: '#fd_effectDate_be', baseXY: [0, '100%']}
    		});
    		///封顶-失效日期
    		fd_expireDate_calendar = new Calendar({
    			trigger: '#fd_expireDate_be',
    			output: '#fd_expireDate_be',
    			align: {selfXY: [0, 0], baseElement: '#fd_expireDate_be', baseXY: [0, '100%']}
    		});
    		
    		$(".icon-calendar").each(function () {
    			new Calendar({
					trigger: $(this),
					output:$(this).parent().find("input"),
					align: {
						selfXY: [0, 0],
						baseElement:$(this).parent().find("input"),
						baseXY: [0, '100%']
					}
				});
			});
    	},
    	_goBackBtnClick: function() {
    		window.history.back(-1);//返回
    	},
    	//公共方法，加载关联科目（满减、保底、封顶）
		_getRelatedSubjecSelect: function() {
			this._setSelectValue(_base + '/param/getRelatedProductList', {subjectType:'21'}, function(data){
				if(data){
					var data = eval(data);
					$.each(data, function(index, item) {
						// 循环获取数据
						var subjectName = data[index].subjectName;
						var subjectId = data[index].subjectId;
						$("#mj_form, #bd_form, #fd_form").find("select[name='relatedSubject']").append('<option value="'+subjectId+'">'+subjectName+'</option>');
					});
				}
			});
		},
		//公共方法，加载关联科目（满减、保底、封顶）
		_getCostUnit: function() {
			this._setSelectValue(_base + '/param/getCostUnit', {}, function(data){
				if(data){
					var data = eval(data);
					$.each(data, function(index, item) {
						// 循环获取数据
						var subjectName = data[index].paramName;
						var subjectId = data[index].paramCode;
						$("#mj_form,#mz_form").find("select[name='fullCostUnitId']").append('<option value="'+subjectId+'">'+subjectName+'</option>');
					});
				}
			});
		},
		_getDiscountUnit: function() {
			this._setSelectValue(_base + '/param/getDiscountUnit', {}, function(data){
				if(data){
					var data = eval(data);
					$.each(data, function(index, item) {
						// 循环获取数据
						var subjectName = data[index].paramName;
						var subjectId = data[index].paramCode;
						$("#mj_form,#mz_form").find("select[name='discountUnitId']").append('<option value="'+subjectId+'">'+subjectName+'</option>');
					});
				}
			});
		},
		_getReduceType: function(){
			this._setSelectValue(_base + '/param/getReduceType', {}, function(data){
				if(data){
					var data = eval(data);
					$.each(data, function(index, item) {
						// 循环获取数据
						var subjectName = data[index].paramName;
						var subjectId = data[index].paramCode;
						$("#mz_form").find("select[name='couponConType']").append('<option value="'+subjectId+'">'+subjectName+'</option>');
					});
				}
			});
		},
		_getDiscountRullType: function() {
			this._setSelectValue(_base + '/param/getDiscountRullType', {}, function(data){
				if(data){
					var data = eval(data);
					$.each(data, function(index, item) {
						// 循环获取数据
						var subjectName = data[index].paramName;
						var subjectId = data[index].paramCode;
						$("#mj_form").find("select[name='discountRullType']").append('<option value="'+subjectId+'">'+subjectName+'</option>');
					});
				}
			});
		},
    	//公共方法，加载下拉菜单
    	_setSelectValue: function(url, data, callback){
			$.ajax({
					url : url,
					type : "post",
					async : false,
					data : data,
					dataType : "html",
					timeout : "10000",
					success : function(data) {
						callback.call(this, data);
					}
				});
		},
		//公共方法，保存前校验
		_saveValidateFunc: function(formId) {
			var _this_ = this;
			var flag = true;
			$.each($(formId).find(".nonull-flag"), function(i){
				var _this = this;
				if($.trim($(this).val())==""){
					_this_._showDialog($(this).attr("tip")+"不能为空", $(_this))
					flag = false;
					return flag;
				}
			});
			return flag;
		},
		//公共方法，页面弹框
		_showDialog: function(contentStr, focusId) {
			Dialog({
 				width: '200px',
 				height: '50px',
				content: contentStr,
				okValue: "确定",
				ok:function () {
					this.close();
					if(focusId!='' && focusId!=null){
						$(focusId).focus();
					}
				}
			}).showModal();
		},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满赠-账单科目 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//满赠-账单科目全选
    	_mz_bscheckall: function() {
    		var _this = this;
    		var checkbox = $("#mz_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
			var chkFlag = $("#mz_bs_checkAll").prop("checked");
			checkbox.prop("checked", chkFlag);
			if(chkFlag){
				$.each(checkbox, function(i, iteam){
					_this._mz_showSelectedBillSubject(this);//展示所选的账单科目
				});
			}else{
				$('#mz_selectedBillSubject').find("table").remove();
			}
         },
         //满赠-账单科目绑定复选框选中事件
         _mz_bindBillSubjectClick: function() {
        	 var _this = this;
        	 $('#mz_billSubjectData').find("input[name='bs_checkbox']").bind('click', function(){
     			var chkFlag = $(this).prop("checked");//true选中，false取消
     			if(chkFlag){
     				 _this._mz_showSelectedBillSubject(this);//展示所选的账单科目
     			}else{
     				var id = $(this).attr("id");
     				$('#mz_selectedBillSubject').find("input[id='"+id+"']").parent().parent().parent().parent().remove();
     			}
     			_this._mz_bindCheckboxRevise();
        	 });
         },
         _mz_initBillSubjectProduct: function() {
             var _this = this;
             $('#mz_selectedBillSubject').find("input[name='bs_checkbox']").each(function(){
                var id = $(this).attr("id");
                $("#mz_billSubjectData").find("input[id='"+id+"']").prop("checked", true);
             });
            _this._mz_bindCheckboxRevise();
           },
         //满赠-展示所选的账单科目
         _mz_showSelectedBillSubject: function(curr) {
        	if($('#mz_selectedBillSubject').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
        		//展示所选的账单科目
        		var trHtml = $(curr).parent().parent('tr').clone();
        		trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
        		trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
        		var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
        		tableHtml.append(trHtml);
        		$('#mz_selectedBillSubject').append(tableHtml);
        		
        		//绑定方法：删除选中的账单科目
        		this._mz_bindBillSubjectRemove();
        	}
         },
         //满赠-删除选中的账单科目
         _mz_bindBillSubjectRemove: function() {
        	 var _this = this;
        	 if(viewOrUpdateflag != 'v'){
        		 $('.img_remove_class').bind('click', function(){
        			 $(this).parent().parent().parent().parent().remove();
        			 var id = $(this).parent().parent().find("input").attr("id");
        			 $("#mz_billSubjectData").find("input[id='"+id+"']").prop("checked", false);
        			 _this._mz_bindCheckboxRevise();
        		 });
        	 }
         },
         _mz_bindCheckboxRevise: function(){
        	 var index = 0, count = 0;
        	 var checkbox = $("#mz_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
        	 $.each(checkbox, function(i, iteam){
					if($(this).prop("checked")){
						count++;
					}
					index ++;
				});
        	 if(index!=0){
        		 if(index == count){
            		 $("#mz_bs_checkAll").prop("checked", true);
            	 }else{
            		 $("#mz_bs_checkAll").prop("checked", false);
            	 }
        	 }
         },
    	//满赠-账单科目查询
    	_mz_searchBillSubjectBtnClick: function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			if(!this._saveValidateFunc("#mz_form")){return false;}//不为空校验
    			if(!_this._checkDate($('#mz_effectDate_be'),$('#mz_expireDate_be'))){
            		return false;
            	}
    			var discountId = "";
    			if(viewOrUpdateflag != null && viewOrUpdateflag != ''){
    				discountId = $("input[name='discountId']").val().trim();
        		}
    			$("#mz_billSubject-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data: {
    					discountId:discountId,
    					mainProductId: $('#mz_form').find("input[name='mainProductId']").val().trim(),
    					mainProductName: $('#mz_form').find("input[name='mainProductName']").val().trim(),
    					effectDate:$("#mz_effectDate_be").val(),
    					expireDate:$("#mz_expireDate_be").val()
    				},
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages: 5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data != null && data != 'undefined' && data.length>0){
    						var template = $.templates("#mz_billSubjectDataTmpl");
    						var htmlOutput = template.render(data);
    						$("#mz_billSubjectData").html(htmlOutput);
    						_this._mz_bindBillSubjectClick();//账单科目绑定复选框选中事件
    						_this._mz_initBillSubjectProduct();
    					}else{
    						$("#mz_billSubjectData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满赠-账单科目  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满赠-赠品查询 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//满赠-赠品全选
    	_mz_spcheckall: function() {
    		var _this = this;
    		var checkbox = $("#mz_salableProductData").find('input[type=checkbox][name=sp_checkbox]');
    		var chkFlag = $("#mz_sp_checkAll").prop("checked");
			checkbox.prop("checked", chkFlag);
			if(chkFlag){
				$.each(checkbox, function(i, iteam){
					_this._mz_showSelectedSalableProduct(this);//展示所选的赠品
				});
			}else{
				$('#mz_selectedSalableProduct').find("table").remove();
			}
         },
         //满赠-赠品绑定复选框选中事件
         _mz_bindSalableProductClick: function() {
        	 var _this = this;
        	 $('#mz_salableProductData').find("input[name='sp_checkbox']").bind('click', function(){
     			var chkFlag = $(this).prop("checked");//true选中，false取消
     			if(chkFlag){
     				_this._mz_showSelectedSalableProduct(this);
     			}else{
     				var id = $(this).attr("id");
     				$('#mz_selectedSalableProduct').find("input[id='"+id+"']").parent().parent().parent().parent().remove();
     			}
     			_this._mzyw_bindCheckboxRevise();
        	 });
         },
         //满赠-展示所选的赠品
         _mz_showSelectedSalableProduct: function(curr) {
        	if($('#mz_selectedSalableProduct').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
        		//展示所选的赠品
        		var trHtml = $(curr).parent().parent('tr').clone();
        		trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
        		trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
        		var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
        		tableHtml.append(trHtml);
        		$('#mz_selectedSalableProduct').append(tableHtml);
        		
        		//绑定方法：删除选中的赠品
        		this._mz_bindSalableProductRemove();
        	}
         },
         //满赠-删除选中的赠品
         _mz_bindSalableProductRemove: function() {
        	 var _this = this;
        	 $('.img_remove_class').bind('click', function(){
        		 $(this).parent().parent().parent().parent().remove();
        		 var id = $(this).parent().parent().find("input").attr("id");
    			 $("#mz_salableProductData").find("input[id='"+id+"']").prop("checked", false);
    			 _this._mzyw_bindCheckboxRevise();
        	 });
         },
         _mzyw_bindCheckboxRevise: function(){
        	 var index = 0, count = 0;
        	 var checkbox = $("#mz_salableProductData").find('input[type=checkbox][name=sp_checkbox]');
        	 $.each(checkbox, function(i, iteam){
					if($(this).prop("checked")){
						count++;
					}
					index ++;
				});
        	 if(index!=0){
        		 if(index == count){
            		 $("#mz_sp_checkAll").prop("checked", true);
            	 }else{
            		 $("#mz_sp_checkAll").prop("checked", false);
            	 }
        	 }
         },
         
         
       //满赠-优惠券全选
     	_mz_yhqcheckall: function() {
     		var _this = this;
     		var checkbox = $("#mz_couponProductData").find('input[type=checkbox][name=yhq_checkbox]');
     		var chkFlag = $("#mz_yhq_checkAll").prop("checked");
// 			checkbox.prop("checked", chkFlag);
     		checkbox.each(function(){
    			if(!$(this).attr("disabled")){
    				$(this).prop("checked", chkFlag);
    			}
    		})
 			if(chkFlag){
 				$.each(checkbox, function(i, iteam){
 					if(!$(this).attr("disabled")){
 						_this._mz_showSelectedCouponProduct(this);//展示所选的赠品
 	    			}
 				});
 			}else{
 				$.each(checkbox, function(i, iteam){
 					_this._mz_removeSelectedCouponProduct(this);//展示所选的赠品
 				});
 			}
          },
          //满赠-赠品绑定复选框选中事件
          _mz_bindCouponProductClick: function() {
         	 var _this = this;
         	 $('#mz_couponProductData').find("input[name='yhq_checkbox']").bind('click', function(){
      			var chkFlag = $(this).prop("checked");//true选中，false取消
      			if(chkFlag){
      				_this._mz_showSelectedCouponProduct(this);
      			}else{
      				var id = $(this).attr("id");
      				$('#mz_selectedCouponProduct').find("input[id='"+id+"']").parent().parent().parent().parent().remove();
      			}
      			_this._mzyhq_bindCheckboxRevise();
         	 });
          },
          _mz_initCouponProduct: function() {
          	 var _this = this;
          	 $('#mz_selectedCouponProduct').find("input[name='yhq_checkbox']").each(function(){
          		var id = $(this).attr("id");
          		$("#mz_couponProductData").find("input[id='"+id+"']").prop("checked", true);
          	 });
          	_this._mzyhq_bindCheckboxRevise();
           },
           _mz_initBoundCouponProduct:function(){
        	   var _this = this;
        	   $.ajax({
   				type: "post",
   				processing: false,
   				url: _base + "/billDiscountMaintain/queryEffectiveCoupon",
   				dataType: "json",
   				data: { },
   				message: "正在加载数据..",
   				success: function (data) {
   					var vo = data.data;
   					if(vo!=null){
   						$('#mz_couponProductData').find("input[name='yhq_checkbox']").each(function(){
   							var id = $(this).attr("id");
   							var flag = false;
   		             		if(giftProductIds!=null && giftProductIds!=""){
   		             			for(var i=0; i<giftProductIds.length; i++){
   		             				if(id == giftProductIds[i].coupon.couponId){
   		             					flag = true;
   		             				}
   		             			}
   		             		}
   		             		if(!flag && $.inArray(id,vo)>=0){
   		             			$(this).prop("disabled" ,true);
	             			}
   		             	 });
   					}
				}
   				}); 
           },
          //满赠-展示所选的赠品
          _mz_showSelectedCouponProduct: function(curr) {
         	if($('#mz_selectedCouponProduct').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
         		//展示所选的赠品
         		var trHtml = $(curr).parent().parent('tr').clone();
         		trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
         		trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
         		var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
         		tableHtml.append(trHtml);
         		$('#mz_selectedCouponProduct').append(tableHtml);
         		
         		//绑定方法：删除选中的赠品
         		this._mz_bindCouponProductRemove();
         	}
          },
          _mz_removeSelectedCouponProduct: function(curr) {
        	  $('#mz_selectedCouponProduct').find('#'+$(curr).attr('id')).parent().parent().parent().parent().remove();
            },
          //满赠-删除选中的赠品
          _mz_bindCouponProductRemove: function() {
         	 var _this = this;
         	 $('.img_remove_class').bind('click', function(){
         		 $(this).parent().parent().parent().parent().remove();
         		 var id = $(this).parent().parent().find("input").attr("id");
     			 $("#mz_couponProductData").find("input[id='"+id+"']").prop("checked", false);
     			 _this._mzyhq_bindCheckboxRevise();
         	 });
          },
          _mzyhq_bindCheckboxRevise: function(){
         	 var index = 0, count = 0;
         	 var checkbox = $("#mz_couponProductData").find('input[type=checkbox][name=yhq_checkbox]');
         	 $.each(checkbox, function(i, iteam){
 					if($(this).prop("checked")){
 						count++;
 					}
 					index ++;
 				});
         	 if(index!=0){
         		 if(index == count){
             		 $("#mz_yhq_checkAll").prop("checked", true);
             	 }else{
             		 $("#mz_yhq_checkAll").prop("checked", false);
             	 }
         	 }
          },
         
         
    	//满赠-赠品查询（可销售产品）
    	_mz_searchSalableProductBtnClick: function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			$("#mz_salableProduct-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedProductList",//"/salableProduct/getSalableProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data : $("#mz_sp-form :input, #mz_sp-form select").serializeArray(),
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages:5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data&&data.length>0){
    						var template = $.templates("#mz_salableProductDataTmpl");
    						var htmlOut = template.render(data);
    						$("#mz_salableProductData").html(htmlOut);
    						_this._mz_bindSalableProductClick();//赠品绑定复选框选中事件
    					}else{
    						$("#mz_salableProductData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	_mz_searchCouponProductBtnClick:function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			$("#mz_couponProduct-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedCouponList",//"/salableProduct/getSalableProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data : $("#mz_yhq-form :input, #mz_yhq-form select").serializeArray(),
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages:5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data&&data.length>0){
    						var template = $.templates("#mz_couponProductDataTmpl");
    						var htmlOut = template.render(data);
    						$("#mz_couponProductData").html(htmlOut);
    						_this._mz_bindCouponProductClick();//赠品绑定复选框选中事件
    						_this._mz_initCouponProduct();
    						_this._mz_initBoundCouponProduct();
    					}else{
    						$("#mz_couponProductData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	//满赠-加载业务类型下拉菜单
    	_mz_getServiceType: function() {
			this._setSelectValue(_base + '/param/getServiceType', null, function(data){
				$("#serviceType").html('<option value="">请选择</option>');
				if(data){
					var json = eval(data);
					$.each(json,function(index, item) {
						// 循环获取数据
						var paramName = json[index].paramName;
						var paramCode = json[index].paramCode;
						$("#serviceType").append('<option cid="'+json[index].id+'" value="'+paramCode+'">'+paramName+'</option>');
					});
				}
			});
		},
    	//满赠-加载计费类型下拉菜单
		_mz_getGroupBillingType: function() {
			this._setSelectValue(_base + '/param/getGroupBillingType', null, function(data){
				if(data){
					var json = eval(data);
					$.each(json,function(index, item) {
						// 循环获取数据
						var paramName = json[index].paramName;
						var paramCode = json[index].paramCode;
						if(paramCode != 'STEP_GROUP_TYPE'){//阶梯产品不能赠送
							$("#groupBillingType").append('<option value="'+paramCode+'">'+paramName+'</option>');
						}
					});
				}
			});
		},
		//满赠-加载赠送业务生效方式下拉菜单
		_mz_getGiftActiveMode: function() {
			this._setSelectValue(_base + '/param/getGiftActiveMode', null, function(data){
				if(data){
					var json = eval(data);
					$.each(json,function(index, item) {
						// 循环获取数据
						var paramName = json[index].paramName;
						var paramCode = json[index].paramCode;
						$("#mz_form").find("select[name='activeMode']").append('<option value="'+paramCode+'">'+paramName+'</option>');
					});
					
					$("#mz_form").find("select[name='activeMode']").change(function(){
						var value = $(this).find("option:selected").attr("value");
						if('special_date' == value){
							$(this).parents('.tab-form').find("li[class='effect-date']").show();
						}else{
							$(this).parents('.tab-form').find("li[class='effect-date']").hide();
						}
					})
				}
			});
		},
		//满赠-加载赠送业务周期下拉菜单
		_mz_getGiftActivePeriod: function() {
			this._setSelectValue(_base + '/param/getGiftActivePeriod', null, function(data){
				if(data){
					var json = eval(data);
					$.each(json,function(index, item) {
						// 循环获取数据
						var paramName = json[index].paramName;
						var paramCode = json[index].paramCode;
						$("#mz_gift_active_period").append('<option value="'+paramCode+'">'+paramName+'</option>');
					});
				}
			});
		},
        //＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满赠-赠品查询  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
      	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满赠-保存方法 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
		//满赠-保存方法
      	_mz_saveBillDiscountClick: function() {
     		if(!this._saveValidateFunc("#mz_form")){return false;}//不为空校验
     		
     		var successFlag = true;
      		if(this._getLength($('#mz_form').find("input[name='discountName']").val())>30){
      			successFlag = false;
      			Dialog({
 					width: '200px',
 					height: '50px',
 					content: "优惠活动名称不能超过30个字符",
 					okValue:"确定",
                    ok:function(){
                    	this.close();
                    	$('#mz_form').find("input[name='discountName']").focus();
                    }
 				}).showModal();
      		}
      		if(!successFlag){
      			return false;
      		}
      		if(this._getLength($('#mz_form').find("textarea[name='remark']").val())>150){
      			successFlag = false;
      			Dialog({
 					width: '200px',
 					height: '50px',
 					content: "优惠活动名称不能超过150个字符",
 					okValue:"确定",
                    ok:function(){
                    	this.close();
                    	$('#mz_form').find("textarea[name='remark']").focus();
                    }
 				}).showModal();
      		}
      		if(!successFlag){
      			return false;
      		}
      		
      		//获取选中的账单科目
      		var allPrdDiscount = "0";
      		var billSubjectMap;
      		var billSubjectMapStr = null;
      		if(!$('#mz_form').find("input[name='allPrdDiscount']").prop("checked")){
      			billSubjectMap = $('#mz_selectedBillSubject').find("input[name='bs_checkbox']").map(function(){return $(this).attr('id')}).get();
      			billSubjectMapStr = JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, '');
      		}else{
      			allPrdDiscount = "1";
      		}
     		if(allPrdDiscount=="0" && billSubjectMap.length < 1){
     			this._showDialog("请选择参与优惠活动的产品", '#mz_btn_bill_subject_query');
     			return false;
     		}
     		
     		if(!this._checkDate($('#mz_effectDate_be'),$('#mz_expireDate_be'))){
        		return false;
        	}
     		
     		var giftType = $("#mz_form").find("input[name='giftType']").val();
     		var giftProductIdList, giftActiveMode, giftEffectDate, giftActivePeriod, cashAmount, virtualCoinsNum;
     		if("yw" == giftType){
     			//获取选中的赠品
         		var salableProductMap = $('#mz_selectedSalableProduct').find("input[name='sp_checkbox']").map(function(){return $(this).attr('id')}).get();
         		if(salableProductMap.length < 1){
         			this._showDialog("满赠活动的赠品不能为空", '#mz_btn_salable_product_query');
         			return false;
         		}
         		giftProductIdList = JSON.stringify(salableProductMap).replace('[', '').replace(']', '').replace(/"/g, '');
         		giftActiveMode = $("#mz_form").find(".configure-tab-cnt1").find("select[name='activeMode']").val();
         		giftEffectDate = $('#mz_form').find(".configure-tab-cnt1").find("input[name='giftEffectDate']").val();
         		//满赠送业务生效方式选择指定日期赠送业务的生效时间为必填项
         		if('special_date' == giftActiveMode){
         			if(giftEffectDate == '' || giftEffectDate == null){
         				this._showDialog("您选择了指定日期赠送业务，所以赠送业务生效时间为必填项", null);
         				$('#mz_form').find(".configure-tab-cnt1").find("input[name='giftEffectDate']").focus();
             			return false;
         			}
         		}
         		giftActivePeriod = $('#mz_form').find(".configure-tab-cnt1").find("select[name='activePeriod']").val();
     		}else if("xj" == giftType){
     			cashAmount = $("#mz_form").find(".configure-tab-cnt2").find("input[name='cashAmount']").val();
     			giftActiveMode = $("#mz_form").find(".configure-tab-cnt2").find("select[name='activeMode']").val();
         		giftEffectDate = $('#mz_form').find(".configure-tab-cnt2").find("input[name='giftEffectDate']").val();
         		//满赠送业务生效方式选择指定日期赠送业务的生效时间为必填项
         		if('special_date' == giftActiveMode){
         			if(giftEffectDate == '' || giftEffectDate == null){
         				this._showDialog("您选择了指定日期赠送业务，所以赠送业务生效时间为必填项", null);
         				$('#mz_form').find(".configure-tab-cnt2").find("input[name='giftEffectDate']").focus();
             			return false;
         			}
         		}
     		}else if("xnb" == giftType){
     			virtualCoinsNum = $("#mz_form").find(".configure-tab-cnt3").find("input[name='virtualCoinsNum']").val();
     			giftActiveMode = $("#mz_form").find(".configure-tab-cnt3").find("select[name='activeMode']").val();
         		giftEffectDate = $('#mz_form').find(".configure-tab-cnt3").find("input[name='giftEffectDate']").val();
         		//满赠送业务生效方式选择指定日期赠送业务的生效时间为必填项
         		if('special_date' == giftActiveMode){
         			if(giftEffectDate == '' || giftEffectDate == null){
         				this._showDialog("您选择了指定日期赠送业务，所以赠送业务生效时间为必填项", null);
         				$('#mz_form').find(".configure-tab-cnt3").find("input[name='giftEffectDate']").focus();
             			return false;
         			}
         		}
     		}else if("yhq" == giftType){
     			var salableProductMap = $('#mz_selectedCouponProduct').find("input[name='yhq_checkbox']").map(function(){return $(this).attr('id')}).get();
         		if(salableProductMap.length < 1){
         			this._showDialog("优惠券不能为空", '#mz_btn_salable_product_query');
         			return false;
         		}
         		giftProductIdList = JSON.stringify(salableProductMap).replace('[', '').replace(']', '').replace(/"/g, '');
     		}
     		
     		var paramData = {
     			discountType: 'mz',
     			discountId: $("input[name='discountId']").val().trim(),
     			discountName: $('#mz_form').find("input[name='discountName']").val().trim(),
             	fullCostAmount: $('#mz_form').find("input[name='fullCostAmount']").val().trim(),
             	fullCostUnitId: $('#mz_form').find("select[name='fullCostUnitId']").val().trim(),
             	effectDate: $('#mz_form').find("input[name='effectDate']").val().trim(),
             	expireDate: $('#mz_form').find("input[name='expireDate']").val().trim(),
             	remark: $('#mz_form').find("textarea[name='remark']").val().trim(),
             	allPrdDiscount:allPrdDiscount,
             	discountProductList: billSubjectMapStr,//账单科目列表
//             	billSubjectList: JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, ''),//账单科目列表
             	giftType:giftType,
             	giftProductIdList: giftProductIdList,//赠品列表
             	giftActiveMode: giftActiveMode,
             	giftEffectDate: giftEffectDate,
             	giftActivePeriod: giftActivePeriod,
             	cashAmount: cashAmount,
             	virtualCoinsNum: virtualCoinsNum
     		};
         	
     		ajaxController.ajax({
                 type: "post",
                 dataType : "json",
                 url: _base + "/billDiscountMaintain/save",
                 processing: true,
                 message: "正在加载，请等待...",
                 data: paramData,
                 success: function(data){
                     if(data){
                     	Dialog({
         					width: '200px',
         					height: '50px',
         					content: data.statusInfo,
         					okValue:"确定",
                            ok:function(){
                            	if(data.data=='000000'){
                            		window.history.back(-1);//返回
                            	}
                            }
         				}).showModal();
                     }
                 }
             });
     	},
     	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满赠-保存方法  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
     	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
     	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满减-账单科目 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//满减-账单科目全选
    	_mj_bscheckall: function() {
    		var _this = this;
    		var checkbox = $("#mj_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
			var chkFlag = $("#mj_bs_checkAll").prop("checked");
			checkbox.prop("checked", chkFlag);
			if(chkFlag){
				$.each(checkbox, function(i, iteam){
					_this._mj_showSelectedBillSubject(this);//展示所选的账单科目
				});
			}else{
				$('#mj_selectedBillSubject').find("table").remove();
			}
         },
         //满减-账单科目绑定复选框选中事件
         _mj_bindBillSubjectClick: function() {
        	 var _this = this;
        	 $('#mj_billSubjectData').find("input[name='bs_checkbox']").bind('click', function(){
     			var chkFlag = $(this).prop("checked");//true选中，false取消
     			if(chkFlag){
     				 _this._mj_showSelectedBillSubject(this);//展示所选的账单科目
     			}else{
     				var id = $(this).attr("id");
     				$('#mj_selectedBillSubject').find("input[id='"+id+"']").parent().parent().parent().parent().remove();
     			}
     			_this._mj_bindCheckboxRevise();
        	 });
         },
         _mj_initBillSubjectProduct: function() {
             var _this = this;
             $('#mj_selectedBillSubject').find("input[name='bs_checkbox']").each(function(){
                var id = $(this).attr("id");
                $("#mj_billSubjectData").find("input[id='"+id+"']").prop("checked", true);
             });
            _this._mj_bindCheckboxRevise();
           },
         //满减-展示所选的账单科目
         _mj_showSelectedBillSubject: function(curr) {
        	if($('#mj_selectedBillSubject').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
        		//展示所选的账单科目
        		var trHtml = $(curr).parent().parent('tr').clone();
        		trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
        		trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
        		var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
        		tableHtml.append(trHtml);
        		$('#mj_selectedBillSubject').append(tableHtml);
        		
        		//绑定方法：删除选中的账单科目
        		this._mj_bindBillSubjectRemove();
        	}
         },
         //满减-删除选中的账单科目
         _mj_bindBillSubjectRemove: function() {
        	 var _this = this;
        	 if(viewOrUpdateflag != 'v'){
        		 $('.img_remove_class').bind('click', function(){
        			 $(this).parent().parent().parent().parent().remove();
        			 var id = $(this).parent().parent().find("input").attr("id");
        			 $("#mj_billSubjectData").find("input[id='"+id+"']").prop("checked", false);
        			 _this._mj_bindCheckboxRevise();
        		 });
        	 }
         },
         _mj_bindCheckboxRevise: function(){
        	 var index = 0, count = 0;
        	 var checkbox = $("#mj_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
        	 $.each(checkbox, function(i, iteam){
					if($(this).prop("checked")){
						count++;
					}
					index ++;
				});
        	 if(index!=0){
        		 if(index == count){
            		 $("#mj_bs_checkAll").prop("checked", true);
            	 }else{
            		 $("#mj_bs_checkAll").prop("checked", false);
            	 }
        	 }
         },
    	 //满减-账单科目查询
         _mj_searchBillSubjectBtnClick: function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			if(!this._saveValidateFunc("#mj_form")){return false;}//不为空校验
    			if(!_this._checkDate($('#mj_effectDate_be'),$('#mj_expireDate_be'))){
            		return false;
            	}
    			var discountId = "";
    			if(viewOrUpdateflag != null && viewOrUpdateflag != ''){
    				discountId = $("input[name='discountId']").val().trim();
        		}
    			$("#mj_billSubject-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data: {
    					discountId:discountId,
    					mainProductId: $('#mj_form').find("input[name='mainProductId']").val().trim(),
    					mainProductName: $('#mj_form').find("input[name='mainProductName']").val().trim(),
    					effectDate:$("#mj_effectDate_be").val(),
    					expireDate:$("#mj_expireDate_be").val()
    				},
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages: 5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data != null && data != 'undefined' && data.length>0){
    						var template = $.templates("#mj_billSubjectDataTmpl");
    						var htmlOutput = template.render(data);
    						$("#mj_billSubjectData").html(htmlOutput);
    						_this._mj_bindBillSubjectClick();//账单科目绑定复选框选中事件
    						_this._mj_initBillSubjectProduct();
    					}else{
    						$("#mj_billSubjectData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满减-账单科目  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满减-保存方法 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
     	//满减-保存方法
      	_mj_saveBillDiscountClick: function() {
      		if(!this._saveValidateFunc("#mj_form")){return false;}//不为空校验
      		var successFlag = true;
      		if(this._getLength($('#mj_form').find("input[name='discountName']").val())>30){
      			successFlag = false;
      			Dialog({
 					width: '200px',
 					height: '50px',
 					content: "优惠活动名称不能超过30个字符",
 					okValue:"确定",
                    ok:function(){
                    	this.close();
                    	$('#mj_form').find("input[name='discountName']").focus();
                    }
 				}).showModal();
      		}
      		if(!successFlag){
      			return false;
      		}
      		if(this._getLength($('#mj_form').find("textarea[name='remark']").val())>150){
      			successFlag = false;
      			Dialog({
 					width: '200px',
 					height: '50px',
 					content: "备注不能超过150个字符",
 					okValue:"确定",
                    ok:function(){
                    	this.close();
                    	$('#mj_form').find("textarea[name='remark']").focus();
                    }
 				}).showModal();
      		}
      		if(!successFlag){
      			return false;
      		}
      		
     		//获取选中的账单科目
      		var allPrdDiscount = "0";
      		var billSubjectMap;
      		var billSubjectMapStr = null;
      		if(!$('#mj_form').find("input[name='allPrdDiscount']").prop("checked")){
      			billSubjectMap = $('#mj_selectedBillSubject').find("input[name='bs_checkbox']").map(function(){return $(this).attr('id')}).get();
      			billSubjectMapStr = JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, '');
      		}else{
      			allPrdDiscount = "1";
      		}
     		if(allPrdDiscount=="0" && billSubjectMap.length < 1){
     			this._showDialog("请选择参与优惠活动的产品", '#mj_btn_bill_subject_query');
     			return false;
     		}
     		
     		if(!this._checkDate($('#mj_effectDate_be'),$('#mj_expireDate_be'))){
        		return false;
        	}
     		
     		var paramData = {
     			discountType: 'mj',
     			discountId: $("input[name='discountId']").val().trim(),
     			discountName: $('#mj_form').find("input[name='discountName']").val().trim(),
             	fullCostAmount: $('#mj_form').find("input[name='fullCostAmount']").val().trim(),
             	fullCostUnitId: $('#mj_form').find("select[name='fullCostUnitId']").val().trim(),
             	discountRullType: $('#mj_form').find("select[name='discountRullType']").val().trim(),
             	discountAmount: $('#mj_form').find("input[name='discountAmount']").val().trim(),
             	discountUnitId: $('#mj_form').find("select[name='discountUnitId']").val().trim(),
             	effectDate: $('#mj_form').find("input[name='effectDate']").val().trim(),
             	expireDate: $('#mj_form').find("input[name='expireDate']").val().trim(),
             	remark: $('#mj_form').find("textarea[name='remark']").val().trim(),
             	allPrdDiscount:allPrdDiscount,
             	discountProductList: billSubjectMapStr//账单科目列表
//             	relatedSubjectList: $('#mj_form').find("select[name='relatedSubject']").val().trim()//满减关联的账单科目
     		};
     		
     		ajaxController.ajax({
                 type: "post",
                 dataType : "json",
                 url: _base + "/billDiscountMaintain/save",
                 processing: true,
                 message: "正在加载，请等待...",
                 data: paramData,
                 success: function(data){
                     if(data){
                     	Dialog({
         					width: '200px',
         					height: '50px',
         					content: data.statusInfo,
         					okValue:"确定",
                            ok:function(){
                            	if(data.data=='000000'){
                            		window.history.back(-1);//返回
                            	}
                            }
         				}).showModal();
                     }
                 }
             });
     	},
     	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝满减-保存方法  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝折扣-账单科目 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
     	//折扣-选择时段
    	_dz_bindTimePeriodClick: function() {
    		var _this = this;
    		$('#dz_form').find("input[name='preferentialRules']").bind('click', function(){
    			if($(this).prop("checked")){
    				$('#dz_form').find("input[name='startTime'], input[name='endTime']").removeAttr("readOnly");
    			}else{
    				$('#dz_form').find("input[name='startTime'], input[name='endTime']").val('').attr("readOnly", true);
    			}
    		});
    	},
     	//折扣-账单科目全选
    	_dz_bscheckall: function() {
    		var _this = this;
    		var checkbox = $("#dz_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
    		var chkFlag = $("#dz_bs_checkAll").prop("checked");
    		checkbox.prop("checked", chkFlag);
    		if(chkFlag){
    			$.each(checkbox, function(i, iteam){
    				_this._dz_showSelectedBillSubject(this);//展示所选的账单科目
    			});
    		}else{
				$('#dz_selectedBillSubject').find("table").remove();
			}
    	},
    	//折扣-账单科目绑定复选框选中事件
    	_dz_bindBillSubjectClick: function() {
    		var _this = this;
    		$('#dz_billSubjectData').find("input[name='bs_checkbox']").bind('click', function(){
    			var chkFlag = $(this).prop("checked");//true选中，false取消
    			if(chkFlag){
    				_this._dz_showSelectedBillSubject(this);//展示所选的账单科目
    			}else{
     				var id = $(this).attr("id");
     				$('#dz_selectedBillSubject').find("input[id='"+id+"']").parent().parent().parent().parent().remove();
     			}
     			_this._dz_bindCheckboxRevise();
    		});
    	},
    	_dz_initBillSubjectProduct: function() {
            var _this = this;
            $('#dz_selectedBillSubject').find("input[name='bs_checkbox']").each(function(){
               var id = $(this).attr("id");
               $("#dz_billSubjectData").find("input[id='"+id+"']").prop("checked", true);
            });
           _this._dz_bindCheckboxRevise();
          },
    	//折扣-展示所选的账单科目
    	_dz_showSelectedBillSubject: function(curr) {
    		if($('#dz_selectedBillSubject').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
    			//展示所选的账单科目
    			var trHtml = $(curr).parent().parent('tr').clone();
    			trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
    			trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
    			var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
    			tableHtml.append(trHtml);
    			$('#dz_selectedBillSubject').append(tableHtml);
    			
    			//绑定方法：删除选中的账单科目
    			this._dz_bindBillSubjectRemove();
    		}
    	},
    	//折扣-删除选中的账单科目
    	_dz_bindBillSubjectRemove: function() {
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			$('.img_remove_class').bind('click', function(){
    				$(this).parent().parent().parent().parent().remove();
    				 var id = $(this).parent().parent().find("input").attr("id");
        			 $("#dz_billSubjectData").find("input[id='"+id+"']").prop("checked", false);
        			 _this._dz_bindCheckboxRevise();
    			});
    		}
    	},
    	_dz_bindCheckboxRevise: function(){
       	 var index = 0, count = 0;
       	 var checkbox = $("#dz_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
       	 $.each(checkbox, function(i, iteam){
					if($(this).prop("checked")){
						count++;
					}
					index ++;
				});
       	 if(index!=0){
       		 if(index == count){
           		 $("#dz_bs_checkAll").prop("checked", true);
           	 }else{
           		 $("#dz_bs_checkAll").prop("checked", false);
           	 }
       	 }
        },
    	//折扣-账单科目查询
    	_dz_searchBillSubjectBtnClick: function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			if(!this._saveValidateFunc("#dz_form")){return false;}//不为空校验
    			if(!_this._checkDate($('#dz_effectDate_be'),$('#dz_expireDate_be'))){
            		return false;
            	}
    			var discountId = "";
    			if(viewOrUpdateflag != null && viewOrUpdateflag != ''){
    				discountId = $("input[name='discountId']").val().trim();
        		}
    			$("#dz_billSubject-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data: {
    					discountId:discountId,
    					mainProductId: $('#dz_form').find("input[name='mainProductId']").val().trim(),
    					mainProductName: $('#dz_form').find("input[name='mainProductName']").val().trim(),
    					effectDate:$("#dz_effectDate_be").val(),
    					expireDate:$("#dz_expireDate_be").val()
    				},
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages: 5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data != null && data != 'undefined' && data.length>0){
    						var template = $.templates("#dz_billSubjectDataTmpl");
    						var htmlOutput = template.render(data);
    						$("#dz_billSubjectData").html(htmlOutput);
    						_this._dz_bindBillSubjectClick();//账单科目绑定复选框选中事件
    						_this._dz_initBillSubjectProduct();
    					}else{
    						$("#dz_billSubjectData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝折扣-账单科目  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝折扣-保存方法 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//折扣-保存方法
    	_dz_saveBillDiscountClick: function() {
    		if(!this._saveValidateFunc("#dz_form")){return false;}//不为空校验
    		
    		var successFlag = true;
      		if(this._getLength($('#dz_form').find("input[name='discountName']").val())>30){
      			successFlag = false;
      			Dialog({
 					width: '200px',
 					height: '50px',
 					content: "优惠活动名称不能超过30个字符",
 					okValue:"确定",
                    ok:function(){
                    	this.close();
                    	$('#dz_form').find("input[name='discountName']").focus();
                    }
 				}).showModal();
      		}
      		if(!successFlag){
      			return false;
      		}
      		if(this._getLength($('#dz_form').find("textarea[name='remark']").val())>150){
      			successFlag = false;
      			Dialog({
 					width: '200px',
 					height: '50px',
 					content: "备注不能超过150个字符",
 					okValue:"确定",
                    ok:function(){
                    	this.close();
                    	$('#dz_form').find("textarea[name='remark']").focus();
                    }
 				}).showModal();
      		}
      		if(!successFlag){
      			return false;
      		}
    		
    		//获取选中的账单科目
      		var allPrdDiscount = "0";
      		var billSubjectMap;
      		var billSubjectMapStr = null;
      		if(!$('#dz_form').find("input[name='allPrdDiscount']").prop("checked")){
      			billSubjectMap = $('#dz_selectedBillSubject').find("input[name='bs_checkbox']").map(function(){return $(this).attr('id')}).get();
      			billSubjectMapStr = JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, '');
      		}else{
      			allPrdDiscount = "1";
      		}
     		if(allPrdDiscount=="0" && billSubjectMap.length < 1){
     			this._showDialog("请选择参与优惠活动的产品", '#dz_btn_bill_subject_query');
     			return false;
     		}
     		
     		if(!this._checkDate($('#dz_effectDate_be'),$('#dz_expireDate_be'))){
        		return false;
        	}
    		
    		var paramData = {
				discountType: 'dz',
				discountId: $("input[name='discountId']").val().trim(),
				discountName: $('#dz_form').find("input[name='discountName']").val().trim(),
				discountPercent: $('#dz_form').find("input[name='discountPercent']").val().trim(),
				effectDate: $('#dz_form').find("input[name='effectDate']").val().trim(),
				expireDate: $('#dz_form').find("input[name='expireDate']").val().trim(),
				remark: $('#dz_form').find("textarea[name='remark']").val().trim(),
				allPrdDiscount:allPrdDiscount,
             	discountProductList: billSubjectMapStr//账单科目列表
//				billSubjectList: JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, '')//账单科目列表
    		};
    		
    		ajaxController.ajax({
    			type: "post",
    			dataType : "json",
    			url: _base + "/billDiscountMaintain/save",
    			processing: true,
    			message: "正在加载，请等待...",
    			data: paramData,
    			success: function(data){
    				if(data){
    					Dialog({
    						width: '200px',
    						height: '50px',
    						content: data.statusInfo,
         					okValue:"确定",
                            ok:function(){
                            	if(data.data=='000000'){
                            		window.history.back(-1);//返回
                            	}
                            }
    					}).showModal();
    				}
    			}
    		});
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝折扣-保存方法  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝保底-账单科目 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//保底-账单科目全选
    	_bd_bscheckall: function() {
    		var _this = this;
    		var checkbox = $("#bd_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
    		var chkFlag = $("#bd_bs_checkAll").prop("checked");
    		checkbox.prop("checked", chkFlag);
    		if(chkFlag){
    			$.each(checkbox, function(i, iteam){
    				_this._bd_showSelectedBillSubject(this);//展示所选的账单科目
    			});
    		}
    	},
    	//保底-账单科目绑定复选框选中事件
    	_bd_bindBillSubjectClick: function() {
    		var _this = this;
    		$('#bd_billSubjectData').find("input[name='bs_checkbox']").bind('click', function(){
    			var chkFlag = $(this).prop("checked");//true选中，false取消
    			if(chkFlag){
    				_this._bd_showSelectedBillSubject(this);//展示所选的账单科目
    			}
    		});
    	},
    	//保底-展示所选的账单科目
    	_bd_showSelectedBillSubject: function(curr) {
    		if($('#bd_selectedBillSubject').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
    			//展示所选的账单科目
    			var trHtml = $(curr).parent().parent('tr').clone();
    			trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
    			trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
    			var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
    			tableHtml.append(trHtml);
    			$('#bd_selectedBillSubject').append(tableHtml);
    			
    			//绑定方法：删除选中的账单科目
    			this._bd_bindBillSubjectRemove();
    		}
    	},
    	//保底-删除选中的账单科目
    	_bd_bindBillSubjectRemove: function() {
    		if(viewOrUpdateflag != 'v'){
    			$('.img_remove_class').bind('click', function(){
    				$(this).parent().parent().parent().parent().remove();
    			});
    		}
    	},
    	//保底-账单科目查询
    	_bd_searchBillSubjectBtnClick: function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			$("#bd_billSubject-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data: {
    					subjectId: $('#bd_form').find("input[name='subjectId']").val().trim(),
    					subjectName: $('#bd_form').find("input[name='subjectName']").val().trim()
    				},
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages: 5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data != null && data != 'undefined' && data.length>0){
    						var template = $.templates("#bd_billSubjectDataTmpl");
    						var htmlOutput = template.render(data);
    						$("#bd_billSubjectData").html(htmlOutput);
    						_this._bd_bindBillSubjectClick();//账单科目绑定复选框选中事件
    					}else{
    						$("#bd_billSubjectData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝保底-账单科目  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝保底-保存方法 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//保底-保存方法
    	_bd_saveBillDiscountClick: function() {
    		if(!this._saveValidateFunc("#bd_form")){return false;}//不为空校验
    		//获取选中的账单科目
    		var billSubjectMap = $('#bd_selectedBillSubject').find("input[name='bs_checkbox']").map(function(){return $(this).attr('id')}).get();
    		if(billSubjectMap.length < 1){
     			this._showDialog("请选择参与优惠活动的产品", '#bd_btn_bill_subject_query');
     			return false;
     		}
     		
    		var paramData = {
				discountType: 'bd',
     			productId: $("input[name='productId']").val().trim(),
				productName: $('#bd_form').find("input[name='productName']").val().trim(),
				bottomAmount: $('#bd_form').find("input[name='bottomAmount']").val().trim(),
				effectDate: $('#bd_form').find("input[name='effectDate']").val().trim(),
				expireDate: $('#bd_form').find("input[name='expireDate']").val().trim(),
				remark: $('#bd_form').find("textarea[name='remark']").val().trim(),
				billSubjectList: JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, ''),//账单科目列表
				relatedSubjectList: $('#bd_form').find("select[name='relatedSubject']").val().trim()//保底关联的账单科目
    		};
    		
    		ajaxController.ajax({
    			type: "post",
    			dataType : "json",
    			url: _base + "/billDiscountMaintain/save",
    			processing: true,
    			message: "正在加载，请等待...",
    			data: paramData,
    			success: function(data){
    				if(data){
    					Dialog({
    						width: '200px',
    						height: '50px',
    						content: data.statusInfo,
         					okValue:"确定",
                            ok:function(){
                            	if(data.data=='000000'){
                            		window.history.back(-1);//返回
                            	}
                            }
    					}).showModal();
    				}
    			}
    		});
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝保底-保存方法  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝封顶-账单科目 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//封顶-账单科目全选
    	_fd_bscheckall: function() {
    		var _this = this;
    		var checkbox = $("#fd_billSubjectData").find('input[type=checkbox][name=bs_checkbox]');
    		var chkFlag = $("#fd_bs_checkAll").prop("checked");
    		checkbox.prop("checked", chkFlag);
    		if(chkFlag){
    			$.each(checkbox, function(i, iteam){
    				_this._fd_showSelectedBillSubject(this);//展示所选的账单科目
    			});
    		}
    	},
    	//封顶-账单科目绑定复选框选中事件
    	_fd_bindBillSubjectClick: function() {
    		var _this = this;
    		$('#fd_billSubjectData').find("input[name='bs_checkbox']").bind('click', function(){
    			var chkFlag = $(this).prop("checked");//true选中，false取消
    			if(chkFlag){
    				_this._fd_showSelectedBillSubject(this);//展示所选的账单科目
    			}
    		});
    	},
    	//封顶-展示所选的账单科目
    	_fd_showSelectedBillSubject: function(curr) {
    		if($('#fd_selectedBillSubject').find('#'+$(curr).attr('id')).length <= 0){//不存在，才添加
    			//展示所选的账单科目
    			var trHtml = $(curr).parent().parent('tr').clone();
    			trHtml.children().first().hide();//trHtml.children().first().remove();//删除第一个选择框
    			trHtml.append($('<td><img class="img_remove_class" src="'+_base+'/resources/baasop/images/stepclose.png"></td>'));//增加最后一个删除的差号
    			var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
    			tableHtml.append(trHtml);
    			$('#fd_selectedBillSubject').append(tableHtml);
    			
    			//绑定方法：删除选中的账单科目
    			this._fd_bindBillSubjectRemove();
    		}
    	},
    	//封顶-删除选中的账单科目
    	_fd_bindBillSubjectRemove: function() {
    		if(viewOrUpdateflag != 'v'){
    			$('.img_remove_class').bind('click', function(){
    				$(this).parent().parent().parent().parent().remove();
    			});
    		}
    	},
    	//封顶-账单科目查询
    	_fd_searchBillSubjectBtnClick: function(){
    		var _this = this;
    		if(viewOrUpdateflag != 'v'){
    			$("#fd_billSubject-page").runnerPagination({
    				url: _base + "/billDiscountMaintain/getRelatedProductList",
    				method: "POST",
    				dataType: "json",
    				processing: true,
    				data: {
    					subjectId: $('#fd_form').find("input[name='subjectId']").val().trim(),
    					subjectName: $('#fd_form').find("input[name='subjectName']").val().trim()
    				},
    				pageSize: AddBillDiscountPager.DEFAULT_PAGE_SIZE,
    				visiblePages: 5,
    				message: "正在为您查询数据..",
    				render: function (data) {
    					if(data != null && data != 'undefined' && data.length>0){
    						var template = $.templates("#fd_billSubjectDataTmpl");
    						var htmlOutput = template.render(data);
    						$("#fd_billSubjectData").html(htmlOutput);
    						_this._fd_bindBillSubjectClick();//账单科目绑定复选框选中事件
    					}else{
    						$("#fd_billSubjectData").html("没有搜索到相关信息");
    					}
    				}
    			});
    		}
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝封顶-账单科目  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝封顶-保存方法 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//封顶-保存方法
    	_fd_saveBillDiscountClick: function() {
    		if(!this._saveValidateFunc("#fd_form")){return false;}//不为空校验
    		//获取选中的账单科目
    		var billSubjectMap = $('#fd_selectedBillSubject').find("input[name='bs_checkbox']").map(function(){return $(this).attr('id')}).get();
    		if(billSubjectMap.length < 1){
     			this._showDialog("请选择参与优惠活动的产品", '#fd_btn_bill_subject_query');
     			return false;
     		}
    		
    		var paramData = {
				discountType: 'fd',
     			productId: $("input[name='productId']").val().trim(),
				productName: $('#fd_form').find("input[name='productName']").val().trim(),
				topAmount: $('#fd_form').find("input[name='topAmount']").val().trim(),
				effectDate: $('#fd_form').find("input[name='effectDate']").val().trim(),
				expireDate: $('#fd_form').find("input[name='expireDate']").val().trim(),
				remark: $('#fd_form').find("textarea[name='remark']").val().trim(),
				billSubjectList: JSON.stringify(billSubjectMap).replace('[', '').replace(']', '').replace(/"/g, ''),//账单科目列表
				relatedSubjectList: $('#fd_form').find("select[name='relatedSubject']").val().trim()//封顶关联的账单科目
    		};
    		
    		ajaxController.ajax({
    			type: "post",
    			dataType : "json",
    			url: _base + "/billDiscountMaintain/save",
    			processing: true,
    			message: "正在加载，请等待...",
    			data: paramData,
    			success: function(data){
    				if(data){
    					Dialog({
    						width: '200px',
    						height: '50px',
    						content: data.statusInfo,
         					okValue:"确定",
                            ok:function(){
                            	if(data.data=='000000'){
                            		window.history.back(-1);//返回
                            	}
                            }
    					}).showModal();
    				}
    			}
    		});
    	},
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝封顶-保存方法  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝全部-修改方法 begin ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	//数据回填（查看、修改）
    	_getBillDiscountInfo: function(){
    		var _this = this;
    		var productId = $("input[name='discountId']").val().trim();
    		if(productId){
    			$.ajax({
    				type: "post",
    				processing: false,
    				url: _base + "/billDiscountMaintain/getByProductId",
    				dataType: "json",
    				data: {productId: productId },
    				message: "正在加载数据..",
    				success: function (data) {
    					if(data && data.statusCode==1){
    						var vo = data.data;
    						var discountType = vo.discountType;//优惠类型
    						
    						_this._showDiscountCnt(discountType);
    						
    						var formId = '#' + discountType + '_form';
    						if(viewOrUpdateflag == 'v'){
    							$('#' + discountType + '_btn_bill_discount_save').remove();//remove保存按钮
    							$(formId).find(".flag").find("input[name='discountName']").val(vo.discountName).attr('readonly', true);
    							$(formId).find(".flag").find("input[name='fullCostAmount']").val(vo.fullCostAmount).attr('readonly', true);
    							$(formId).find(".flag").find("input[name='discountAmount']").val(vo.discountAmount).attr('readonly', true);
    							$(formId).find(".flag").find("select[name='fullCostUnitId']").val(vo.fullCostUnitId).attr('disabled', true);
    							$(formId).find(".flag").find("select[name='discountRullType']").val(vo.discountRullType).attr('disabled', true);
    							$(formId).find(".flag").find("select[name='discountUnitId']").val(vo.discountUnitId).attr('disabled', true);
    							$(formId).find(".flag").find("input[name='discountPercent']").val(vo.discountPercent).attr('readonly', true);
    							$(formId).find(".flag").find("input[name='bottomAmount']").val(vo.bottomAmount).attr('readonly', true);
    							$(formId).find(".flag").find("input[name='topAmount']").val(vo.topAmount).attr('readonly', true);
    							$(formId).find(".flag").find("select[name='relatedSubject']").val(vo.relatedSubjectId).attr('disabled', true);
    							$(formId).find(".flag").find("input[name='effectDate']").val(_this._timestampToDate('yyyy-MM-dd', vo.effectDate));
    							$(formId).find(".flag").find("input[name='expireDate']").val(_this._timestampToDate('yyyy-MM-dd', vo.expireDate));
    							$(".icon-calendar").unbind();
    							$(formId).find(".flag").find("textarea[name='remark']").val(vo.remark).attr('readonly', true);
    							_this._generateBillSubjectDiv(discountType, vo.discountProductList);//账单科目
    							if("1"==vo.allPrdDiscount){
    								$(formId).find("input[name='allPrdDiscount']").prop("checked", true);
    								$(formId).find("input[name='allPrdDiscount']").attr('disabled', true);
    								$("#"+discountType+"_selectedBillSubject").parent().find(".configure-table").hide();
        							$("#"+discountType+"_selectedBillSubject").hide();
    							}else{
    								$("#"+discountType+"_selectedBillSubject").parent().find(".tab-form, .configure-table").hide();
    							}
    							
    							if(discountType == "mz"){
    								_this._generategiftProductDiv(discountType, vo.giftType, vo.giftProductList);//赠品
    								
    								if("yw"==vo.giftType){
										  $('.configure-tab-cnt1').show();
									      $('.configure-tab-cnt2').hide();
										  $('.configure-tab-cnt3').hide();
										  $('.configure-tab-cnt4').hide();
										  $('.configure-tab').find("p[pvalue='xj']").remove();
										  $('.configure-tab').find("p[pvalue='xnb']").remove();
										  $('.configure-tab').find("p[pvalue='yhq']").remove();
										  $('.configure-tab-cnt1').find("select[name='activeMode']").val(vo.giftActiveMode);
										  if('special_date' == vo.giftActiveMode){
												$('.configure-tab-cnt1').find("li[class='effect-date']").show();
												$('.configure-tab-cnt1').find("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
											}else{
												$('.configure-tab-cnt1').find("li[class='effect-date']").hide();
											}
										  $('.configure-tab-cnt1').find("select[name='activeMode']").val(vo.giftActiveMode);
										  $("#mz_sp-form").hide();
										  $(".configure-tab-cnt1 .configure-table").hide();
										  $("#mz_effectiveDate_be").unbind();
	  								}else if("xj"==vo.giftType){
	  									$('.configure-tab-cnt1').hide();
										      $('.configure-tab-cnt2').show();
											  $('.configure-tab-cnt3').hide();
											  $('.configure-tab-cnt4').hide();
											  $('.configure-tab').find("p[pvalue='yw']").remove();
											  $('.configure-tab').find("p[pvalue='xj']").addClass("current");
											  $('.configure-tab').find("p[pvalue='xnb']").remove();
											  $('.configure-tab').find("p[pvalue='yhq']").remove();
											  $('.configure-tab-cnt2').find("input[name='cashAmount']").val(vo.cashAmount).attr('disabled', true);;
											  $('.configure-tab-cnt2').find("select[name='activeMode']").val(vo.giftActiveMode);
											  if('special_date' == vo.giftActiveMode){
													$('.configure-tab-cnt2').find("li[class='effect-date']").show();
													$('.configure-tab-cnt2').find("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
												}else{
													$('.configure-tab-cnt2').find("li[class='effect-date']").hide();
												}
		  								}else if("xnb"==vo.giftType){
	  									$('.configure-tab-cnt1').hide();
										      $('.configure-tab-cnt2').hide();
											  $('.configure-tab-cnt3').show();
											  $('.configure-tab-cnt4').hide();
											  $('.configure-tab').find("p[pvalue='yw']").remove();
											  $('.configure-tab').find("p[pvalue='xj']").remove();
											  $('.configure-tab').find("p[pvalue='yhq']").remove();
											  $('.configure-tab').find("p[pvalue='xnb']").addClass("current");
											  $('.configure-tab-cnt3').find("input[name='virtualCoinsNum']").val(vo.virtualCoinsNum).attr('disabled', true);;
											  $('.configure-tab-cnt3').find("select[name='activeMode']").val(vo.giftActiveMode);
											  if('special_date' == vo.giftActiveMode){
													$('.configure-tab-cnt3').find("li[class='effect-date']").show();
													$('.configure-tab-cnt3').find("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
												}else{
													$('.configure-tab-cnt3').find("li[class='effect-date']").hide();
												}		
		  								}else if("yhq"==vo.giftType){
		  									$('.configure-tab-cnt1').hide();
											      $('.configure-tab-cnt2').hide();
												  $('.configure-tab-cnt3').hide();
												  $('.configure-tab-cnt4').show();
												  $('.configure-tab').find("p[pvalue='yw']").remove();
												  $('.configure-tab').find("p[pvalue='xj']").remove();
												  $('.configure-tab').find("p[pvalue='xnb']").remove();
												  $('.configure-tab').find("p[pvalue='yhq']").addClass("current");
												  $("#mz_yhq-form").hide();
												  $(".configure-tab-cnt4 .configure-table").hide();
		  								}
    								$(".configure-tab p").unbind("click");
    								$("select[name='activeMode']").val(vo.giftActiveMode).attr('disabled', true);
    								$("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
    								$("select[name='activePeriod']").val(vo.giftActivePeriod).attr('disabled', true);
    								mz_effectDate_calendar.disable();
    								mz_expireDate_calendar.disable();
    								mz_effectiveDate_calendar.disable();
    							}else if(discountType == "mj"){
    								mj_effectDate_calendar.disable();
    								mj_expireDate_calendar.disable();
    							}else if(discountType == "dz"){
    								$(formId).find("input[name='preferentialRules']").attr('disabled', true);
    								$(formId).find("input[name='startTime']").val(vo.startTime).attr('readonly', true);
    								$(formId).find("input[name='endTime']").val(vo.endTime).attr('readonly', true);
    								dz_effectDate_calendar.disable();
    								dz_expireDate_calendar.disable();
    							}else if(discountType == "bd"){
    								bd_effectDate_calendar.disable();
    								bd_expireDate_calendar.disable();
    							}else if(discountType == "fd"){
    								fd_effectDate_calendar.disable();
    								fd_expireDate_calendar.disable();
    							}
    							
    						}else{
    							$(formId).find(".flag").find("input[name='discountName']").val(vo.discountName);
    							$(formId).find(".flag").find("input[name='fullCostAmount']").val(vo.fullCostAmount);
    							$(formId).find(".flag").find("input[name='discountAmount']").val(vo.discountAmount);
    							$(formId).find(".flag").find("input[name='discountPercent']").val(vo.discountPercent);
    							$(formId).find(".flag").find("input[name='bottomAmount']").val(vo.bottomAmount);
    							$(formId).find(".flag").find("input[name='topAmount']").val(vo.topAmount);
    							$(formId).find(".flag").find("select[name='relatedSubject']").val(vo.relatedSubjectId);
    							$(formId).find(".flag").find("select[name='fullCostUnitId']").val(vo.fullCostUnitId);
    							$(formId).find(".flag").find("select[name='discountRullType']").val(vo.discountRullType);
    							$(formId).find(".flag").find("select[name='discountUnitId']").val(vo.discountUnitId);
    							$(formId).find(".flag").find("input[name='effectDate']").val(_this._timestampToDate('yyyy-MM-dd', vo.effectDate));
    							$(formId).find(".flag").find("input[name='expireDate']").val(_this._timestampToDate('yyyy-MM-dd', vo.expireDate));
    							$(formId).find(".flag").find("textarea[name='remark']").val(vo.remark);
    							$(formId).find("input[name='giftType']").val(vo.giftType);
    							if("1"==vo.allPrdDiscount){
    								$(formId).find("input[name='allPrdDiscount']").trigger("click");
    							}
    							_this._generateBillSubjectDiv(discountType, vo.discountProductList);//账单科目
    							
    							if(discountType == "dz"){
    								$(formId).find("input[name='startTime']").val(vo.startTime);
    								$(formId).find("input[name='endTime']").val(vo.endTime);
    							}
    							
    							if(discountType == "mz"){
    								if("yhq"==vo.giftType){
    									giftProductIds = vo.giftProductList;
    								}
    								_this._generategiftProductDiv(discountType, vo.giftType, vo.giftProductList);//赠品
    								if("yw"==vo.giftType){
										  $('.configure-tab-cnt1').show();
									      $('.configure-tab-cnt2').hide();
										  $('.configure-tab-cnt3').hide();
										  $('.configure-tab-cnt4').hide();
										  $('.configure-tab-cnt1').find("select[name='activeMode']").val(vo.giftActiveMode);
										  if('special_date' == vo.giftActiveMode){
												$('.configure-tab-cnt1').find("li[class='effect-date']").show();
												$('.configure-tab-cnt1').find("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
											}else{
												$('.configure-tab-cnt1').find("li[class='effect-date']").hide();
											}
										  $('.configure-tab-cnt1').find("select[name='activeMode']").val(vo.giftActiveMode);
    								}else if("xj"==vo.giftType){
    									$('.configure-tab-cnt1').hide();
									      $('.configure-tab-cnt2').show();
										  $('.configure-tab-cnt3').hide();
										  $('.configure-tab-cnt4').hide();
										  $('.configure-tab').find("p[pvalue='yw']").removeClass("current");
										  $('.configure-tab').find("p[pvalue='xj']").addClass("current");
										  $('.configure-tab-cnt2').find("input[name='cashAmount']").val(vo.cashAmount);
										  $('.configure-tab-cnt2').find("select[name='activeMode']").val(vo.giftActiveMode);
										  if('special_date' == vo.giftActiveMode){
												$('.configure-tab-cnt2').find("li[class='effect-date']").show();
												$('.configure-tab-cnt2').find("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
											}else{
												$('.configure-tab-cnt2').find("li[class='effect-date']").hide();
											}
    								}else if("xnb"==vo.giftType){
    									$('.configure-tab-cnt1').hide();
									      $('.configure-tab-cnt2').hide();
										  $('.configure-tab-cnt3').show();
										  $('.configure-tab-cnt4').hide();
										  $('.configure-tab').find("p[pvalue='yw']").removeClass("current");
										  $('.configure-tab').find("p[pvalue='xnb']").addClass("current");
										  $('.configure-tab-cnt3').find("input[name='virtualCoinsNum']").val(vo.virtualCoinsNum);
										  $('.configure-tab-cnt3').find("select[name='activeMode']").val(vo.giftActiveMode);
										  if('special_date' == vo.giftActiveMode){
												$('.configure-tab-cnt3').find("li[class='effect-date']").show();
												$('.configure-tab-cnt3').find("input[name='giftEffectDate']").val(vo.giftEffectDate!=null ? vo.giftEffectDate.substring(0, 10) : null);
											}else{
												$('.configure-tab-cnt3').find("li[class='effect-date']").hide();
											}
    								}else if("yhq"==vo.giftType){
    									$('.configure-tab-cnt1').hide();
									      $('.configure-tab-cnt2').hide();
										  $('.configure-tab-cnt3').hide();
										  $('.configure-tab-cnt4').show();
										  $('.configure-tab').find("p[pvalue='xj']").removeClass("current");
										  $('.configure-tab').find("p[pvalue='yhq']").addClass("current");
    								}
    							}
    						}
    						
    					}
    				}
    			}); 
    		}
    	},
    	//生成账单科目div（查看、修改）
    	_generateBillSubjectDiv :function(discountType, discountProductList){
    		var _this = this;
    		var subjectList = eval(discountProductList);
	        if(subjectList){
	        	$.each(subjectList, function(i){
	        		var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
	        		var trHtml = $("<tr></tr>");
	        		var td1 = $("<td style='display: none;'><input type='checkbox' id='"+this.categoryId+"' name='bs_checkbox' class='checkbox'></td>");
		   			var td2 = $("<td>"+this.mainProductId+"</td>");
	        		var td3 = $("<td>"+this.mainProductName+"</td>");
	        		var td4 = $("<td>"+this.categoryId+"</td>");
	        		var td5 = $("<td>"+this.categoryName+"</td>");
	        		var billingCycle = "";
	        		if(this.billingCycle=='WEEK'){
	        			billingCycle = "周";
	        		}else if(this.billingCycle=='DAY'){
	        			billingCycle = "日";
	        		}else if(this.billingCycle=='MONTH'){
	        			billingCycle = "月";
	        		}else if(this.billingCycle=='YEAR'){
	        			billingCycle = "年";
	        		}
	        		var td6 = $("<td>"+billingCycle+"</td>");
	        		var td7 = $("<td><img class='img_remove_class' src='"+_base+"/resources/baasop/images/stepclose.png'></td>");
	        		trHtml.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);
	        		tableHtml.append(trHtml);
	        		$("#"+discountType+"_selectedBillSubject").append(tableHtml);
	        	});
	        	_this._mz_bindBillSubjectRemove();//绑定删除方法
	        	_this._mj_bindBillSubjectRemove();
	        	_this._dz_bindBillSubjectRemove();
	        }
    	},
    	//生成赠品div（查看、修改）
    	_generategiftProductDiv :function(discountType, giftType, giftProductList){
    		var _this = this;
    		var productList = eval(giftProductList)
			if(productList){
				$.each(productList, function(i){
					var tableHtml = $("<table width='100%' border='0' cellspacing='0' cellpadding='0'></table>");
	        		var trHtml = $("<tr></tr>");
					if("yw"==giftType){
						var td1 = $("<td style='display: none;'><input type='checkbox' id='"+this.categoryId+"' name='sp_checkbox' class='checkbox'></td>");
						var td2 = $("<td>"+this.categoryId+"</td>");
		        		var td3 = $("<td>"+this.categoryName+"</td>");
		        		var billingCycle = "";
		        		if(this.billingCycle=='WEEK'){
		        			billingCycle = "周";
		        		}else if(this.billingCycle=='DAY'){
		        			billingCycle = "日";
		        		}else if(this.billingCycle=='MONTH'){
		        			billingCycle = "月";
		        		}else if(this.billingCycle=='YEAR'){
		        			billingCycle = "年";
		        		}
		        		var td4 = $("<td>"+billingCycle+"</td>");
		        		var td5 = $("<td><img class='img_remove_class' src='"+_base+"/resources/baasop/images/stepclose.png'></td>");
		        		trHtml.append(td1).append(td2).append(td3).append(td4).append(td5);
		        		tableHtml.append(trHtml);
		        		$("#"+discountType+"_selectedSalableProduct").append(tableHtml);
					}else if("yhq"==giftType){
						var td1 = $("<td style='display: none;'><input type='checkbox' id='"+this.singleCoupon.couponId+"' name='yhq_checkbox' class='checkbox'></td>");
						var td2 = $("<td>"+this.singleCoupon.couponId+"</td>");
		        		var td3 = $("<td>"+this.singleCoupon.couponName+"</td>");
		        		var billingCycle = "";
		        		if(this.singleCoupon.couponType=='ALL'){
		        			billingCycle = "全场通用";
		        		}else if(this.singleCoupon.couponType=='APPOINT'){
		        			billingCycle = "指定产品";
		        		}
		        		var td4 = $("<td>"+billingCycle+"</td>");
		        		var productName = "";
		        		if(this.singleCoupon.couponType=='ALL'){
		        			productName = "所有产品";
		        		}else{
		        			productName = this.singleCoupon.productName;
		        		}
		        		var td5 = $("<td>"+productName+"</td>");
		        		var td6 = $("<td>"+this.singleCoupon.conditionValue+"</td>");
		        		var topMoney = "无";
		        		if(this.singleCoupon.topMoney!=null && this.singleCoupon.topMoney!=""){
		        			topMoney = this.singleCoupon.topMoney + "元";
		        		}
		        		var td7 = $("<td>"+topMoney+"</td>");
		        		var td8 = $("<td>"+_this._timestampToDate('yyyy-MM-dd', this.singleCoupon.activeTime)+"-"+_this._timestampToDate('yyyy-MM-dd', this.singleCoupon.inactiveTime)+"</td>");
		        		var td9 = $("<td><img class='img_remove_class' src='"+_base+"/resources/baasop/images/stepclose.png'></td>");
		        		trHtml.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
		        		tableHtml.append(trHtml);
		        		$("#"+discountType+"_selectedCouponProduct").append(tableHtml);
					}
				});
				_this._mz_bindCouponProductRemove();//绑定删除方法
			}
    	},
    	//格式化时间
    	_timestampToDate :function(format, timestamp){
			if(timestamp!=null){
				return (new Date(parseFloat(timestamp))).format(format);
			}else{
				return null;
			}
		},
		//当前tab切换（查看、修改）
		_showDiscountCnt: function(index){
			if(index=='mz'){
				$('.Discountb, .Discountc, .Discountd, .Discounte').parent().remove();
				$('.Discount-cnt2, .Discount-cnt3, .Discount-cnt4, .Discount-cnt5').remove();
    			$('.Discount-cnt1').show();
			}
			if(index=='mj'){
				$('.Discounta, .Discountc, .Discountd, .Discounte').parent().remove();
				$('.Discount-cnt1, .Discount-cnt3, .Discount-cnt4, .Discount-cnt5').remove();
				$('.Discount-cnt2').show();
			}
			if(index=='dz'){
				$('.Discounta, .Discountb, .Discountd, .Discounte').parent().remove();
				$('.Discount-cnt1, .Discount-cnt2, .Discount-cnt4, .Discount-cnt5').remove();
				$('.Discount-cnt3').show();
			}
			if(index=='bd'){
				$('.Discounta, .Discountb, .Discountc, .Discounte').parent().remove();
				$('.Discount-cnt1, .Discount-cnt2, .Discount-cnt3, .Discount-cnt5').remove();
				$('.Discount-cnt4').show();
			}
			if(index=='fd'){
				$('.Discounta, .Discountb, .Discountc, .Discountd').parent().remove();
				$('.Discount-cnt1, .Discount-cnt2, .Discount-cnt3, .Discount-cnt4').remove();
				$('.Discount-cnt5').show();
			}
    	}
    	//＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝全部-修改方法  end  ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    	/**_bindValidator: function(){
   		    var validator = new Validator({
   		        element: '#discount-step2',
   		        onFormValidated: function(err, results, form) {
   		            window.console && console.log && console.log(err, results, form);
   		        },
   		        failSilently: true
   		    });
   		    
   		    validator.addItem({
   		        element: '#discountName',
   		        required: true,
   		        rule: 'minlength{min: 5} maxlength{max:20}',
   		        alertFlag: true
   		    })
   		    .addItem({
   		        element: '#effectiveDate',
   		        required: true,
   		        errormessage:"请选择生失效日期"
   		    })
    	}*/
    });
    
    module.exports = AddBillDiscountPager
});