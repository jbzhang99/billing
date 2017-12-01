<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--tab标签第1块 begin-->
<div id="mz_form" class="Discount-cnt1">
    <div class="management-title step2">请配置优惠信息</div>
    <div class="configure">
     <div class="tab-form flag">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠活动名称</p>
        <p><input type="text" name="discountName" tip="优惠活动名称" placeholder="优惠活动名称，不超过30个字符" class="int-xlarge nonull-flag"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>优惠规则</p>
        <p>
        	<input type="text" name="fullCostAmount" tip="优惠规则" class="int-medium mana nonull-flag price-input"><span class="man">满</span>
        	<select class="select-mini" name="fullCostUnitId"></select>
        </p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>生效日期</p>
        <p><input type="text" id="mz_effectDate_be" name="effectDate" tip="生效日期" class="int-medium nonull-flag" readonly><span id="mz_effectDate" class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>失效日期</p>
        <p><input type="text" id="mz_expireDate_be" name="expireDate" tip="失效日期" class="int-medium nonull-flag" readonly><span id="mz_expireDate" class="icon-calendar" style="position:relative;left:-35px;"></span></p>
       </li>
       <li>
        <p class="tab-form-name">备注</p>
        <p><textarea name="remark" placeholder="不超过150个字符" class="int-text"></textarea></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="management-title step3">请选择参与优惠活动的产品</div>
    <div class="configure">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">产品ID</p>
        <p><input type="text" name="mainProductId" class="int-medium"></p>
        <p class="tab-form-name">产品名称</p>
        <p><input type="text" name="mainProductName" class="int-medium"></p>
        <p><input id="mz_btn_bill_subject_query" type="button" value="查询" class="next-btn next-btn-hover"></p>
       </li>
       <li><p class="tab-form-name"><input name="allPrdDiscount" type="checkbox" class="checkbox" style="float:right;margin-top:15px;"></p><p>全部产品参与</p></li>
      </ul>
     </div>
     <div class="configure-table">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <thead>
	      <tr>
	        <td width="100"><input id="mz_bs_checkAll" type="checkbox" class="checkbox">全选</td>
	        <td>产品ID</td>
	        <td>产品名称</td>
	        <td>子产品ID</td>
	        <td>子产品名称</td>
	        <td>计费周期</td>
	      </tr>
      </thead>
      <tbody id="mz_billSubjectData"></tbody>
      <script id="mz_billSubjectDataTmpl" type="text/template">
	      <tr>
	         <td><input type="checkbox" id="{{:categoryId}}" name="bs_checkbox" class="checkbox"></td>
	         <td>{{:mainProductId}}</td>
			 <td>{{:mainProductName}}</td>
			 <td>{{:categoryId}}</td>
	         <td>{{:categoryName}}</td>
	         <td>{{if billingCycle=='WEEK'}}周{{else billingCycle=='DAY'}}日{{else billingCycle=='MONTH'}}月{{else billingCycle=='YEAR'}}年{{/if}}</td>
	      </tr>
	  </script>
    </table>
     </div>
     <div id="mz_selectedBillSubject" class="configure-cnts">
      	<p>已选择参加活动的产品</p>
     </div>
    </div>
    
    <div class="management-title step4">请配置满赠活动的赠品</div>
    <input type="hidden" name="giftType" value="xj">
    <div class="configure">
     <div class="configure-tab">
      <p pvalue="yw" style="display:none">赠送业务类型</p>
      <p pvalue="xj" class="current">赠送现金</p>
      <p pvalue="xnb" style="display:none">赠送虚拟货币</p>
      <p pvalue="yhq">赠送优惠券</p>
     </div>
     <div class="configure-tab-cnt1" style="display:none">
     <div class="tab-form" id="mz_sp-form">
      <ul>
       <li>
        <p class="tab-form-name">产品ID</p>
        <p><input type="text" name="productId" class="int-medium"></p>
        <p class="tab-form-name">产品名称</p>
        <p><input type="text" name="productName" class="int-medium"></p>
        <p><input type="button" id="mz_btn_salable_product_query" value="查询" class="next-btn next-btn-hover"></p>
       </li>
       <!-- 
       <li>
        <p class="tab-form-name">价格范围</p>
        <p>
        	<input type="text" id="price-start" name="priceStart" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" class="int-mini">
        	<i style="color:#666; float:left;">至</i>
        	<input type="text" id="price-end" name="priceEnd" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" class="int-mini">
       	</p>
       </li>
        -->
      </ul>
     </div>
     <div class="configure-table">
	     <table width="100%" border="0" cellspacing="0" cellpadding="0">
	     <thead>
	      <tr>
	        <td width="100"><input type="checkbox" id="mz_sp_checkAll" class="checkbox">全选</td>
	        <td>产品ID</td>
	        <td>产品名称</td>
	        <td>子产品ID</td>
	        <td>子产品名称</td>
	        <td>计费周期</td>
	      </tr>
	      </thead>
	      <tbody id="mz_salableProductData"></tbody>
	      <script id="mz_salableProductDataTmpl" type="text/x-jsrender">
		      <tr>
		         <td><input type="checkbox" id="{{:categoryId}}" name="sp_checkbox" class="checkbox"></td>
	        	 <td>{{:mainProductId}}</td>
				 <td>{{:mainProductName}}</td>
				 <td>{{:categoryId}}</td>
	        	 <td>{{:categoryName}}</td>
	        	 <td>{{if billingCycle=='WEEK'}}周{{else billingCycle=='DAY'}}日{{else billingCycle=='MONTH'}}月{{else billingCycle=='YEAR'}}年{{/if}}</td>
			</tr>
		  </script>
	    </table>
    	<!-- <div>
			<nav style="text-align: right">
				<ul id="mz_salableProduct-page"></ul>
			</nav>
		</div> -->
     </div>
     <div id="mz_selectedSalableProduct" class="configure-cnts">
      <p>已选择参加活动的产品</p>
     </div>
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>赠送业务生效方式</p>
        <p><select id="mz_gift_active_mode" name="activeMode" class="int-medium"></select></p>
       </li>
       <li class="effect-date" style="display:none">
        <p class="tab-form-name"><i>*</i>赠送业务生效时间</p>
        <p><input type="text" id="mz_effectiveDate_be" name="giftEffectDate" readonly="true" class="int-medium"><span id="mz_effectiveDate" class="icon-calendar"></span></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>赠送业务周期</p>
        <p><select id="mz_gift_active_period" name="activePeriod" class="int-medium"></select></p>
       </li>
       <li>
       	<i>*注：赠送业务生效方式选择指定日期赠送业务的生效时间为必填项</i>
       </li>
      </ul>
     </div>
    </div>
    <div class="configure-tab-cnt2">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">赠送现金金额</p>
        <p><input type="text" name="cashAmount" class="int-medium price-input">元</p>
       </li>
       <li>
        <p class="tab-form-name">现金到账日期</p>
        <p><select class="int-medium" name="activeMode"></select></p>
       </li>
       <li class="effect-date" style="display:none">
        <p class="tab-form-name"><i>*</i>赠送现金到账时间</p>
        <p><input type="text" id="xj_effectiveDate_be" name="giftEffectDate" readonly="true" class="int-medium"><span id="xj_effectiveDate" class="icon-calendar"></span></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="configure-tab-cnt3" style="display:none">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">赠送虚拟币个数</p>
        <p><input type="text" name="virtualCoinsNum" class="int-medium price-input" onkeyup="value=value.replace(/[^0-9]/g,'')" onpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')"></p>
       </li>
       <li>
        <p class="tab-form-name">虚拟币到账日期</p>
        <p><select class="int-medium" name="activeMode"></select></p>
       </li>
       <li class="effect-date" style="display:none">
        <p class="tab-form-name"><i>*</i>赠送虚拟币到账时间</p>
        <p><input type="text" id="xnb_effectiveDate_be" name="giftEffectDate" readonly="true" class="int-medium"><span id="xnb_effectiveDate" class="icon-calendar"></span></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="configure-tab-cnt4" style="display:none">
     <div class="tab-form" id="mz_yhq-form">
      <ul>
       <li>
        <p class="tab-form-name">优惠券ID</p>
        <p><input type="text" name="couponId" class="int-medium"></p>
        <p class="tab-form-name">优惠券名称</p>
        <p><input type="text" name="couponName" class="int-medium"></p>
       </li>
       <li>
        <p class="tab-form-name">优惠方式</p>
        <p style="padding-right:8px;"><select id="couponConType" name="couponConType" class="int-medium"></select></p>
        <!-- <p class="tab-form-name">优惠券面值</p>
        <p>
        	<input type="text" id="price-start" name="priceStart" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" class="int-mini">
        	<i style="color:#666; float:left;">至</i>
        	<input type="text" id="price-end" name="priceEnd" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" class="int-mini">
       	</p> -->
       	<p><input type="button" id="mz_btn_coupon_product_query" value="查询" class="next-btn next-btn-hover"></p>
       </li>
      </ul>
     </div>
     <div class="configure-table">
	     <table width="100%" border="0" cellspacing="0" cellpadding="0">
	     <thead>
	      <tr>
	        <td width="100"><input type="checkbox" id="mz_yhq_checkAll" class="checkbox">全选</td>
	        <td>优惠券ID</td>
	        <td>优惠券名称</td>
	        <td>优惠券类型</td>
	        <td>适用产品</td>
	        <td>优惠方式</td>
	        <td>最高减免金额</td>
	        <td>优惠券有效期</td>
	      </tr>
	      </thead>
	      <tbody id="mz_couponProductData"></tbody>
	      <script id="mz_couponProductDataTmpl" type="text/x-jsrender">
		      <tr>
		        <td><input type="checkbox" id="{{:couponId}}" name="yhq_checkbox" class="checkbox"></td>
		        <td>{{:couponId}}</td>
		        <td>{{:couponName}}</td>
		        <td>
							{{if couponType == 'ALL'}}
								全场通用
							{{else couponType==="APPOINT"}}
								指定产品
							{{/if}}
						</td>
				<td>
							{{if couponType == 'ALL'}}
								所有产品
							{{else}}
								{{:productName}}
							{{/if}}
						</td>
				<td>{{:conditionValue}}</td>
				<td>{{if !topMoney}}无{{else}}{{:topMoney}}元{{/if}}</td>
		        <td>{{:~timestampToDate('yyyy/MM/dd', activeTime)}}-{{:~timestampToDate('yyyy/MM/dd', inactiveTime)}}</td>
		      </tr>
		  </script>
	    </table>
    	<div>
			<nav style="text-align: right">
				<ul id="mz_couponProduct-page"></ul>
			</nav>
		</div>
     </div>
     <div id="mz_selectedCouponProduct" class="configure-cnts">
      <p>已选择参加活动的优惠券</p>
     </div>
     
    </div>
    </div>
    <div class="configure-btn">
     <input type="button" value="取消" class="next-btn cancel"><input type="button" id="mz_btn_bill_discount_save" value="保存" class="next-btn next-btn-hover">
    </div>
</div>
<!--tab标签第1块 end-->
