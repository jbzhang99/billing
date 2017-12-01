<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--tab标签第1块 begin-->
<div id="mz_form" class="Discount-cnt1">
    <div class="management-title step2">请配置满赠优惠产品</div>
    <div class="configure">
     <div class="tab-form flag">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠活动名称</p>
        <p><input type="text" name="productName" tip="优惠活动名称" class="int-xlarge nonull-flag"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>优惠规则</p>
        <p>
        	<input type="text" name="fullCostAmount" tip="优惠规则" class="int-medium mana nonull-flag"><span class="man">满</span>
        	<!-- <select class="select-small"><option>请选择单位</option></select> --><span style="float:left; padding: 0 10px 0 0;">元</span>
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
        <p><textarea name="remark" class="int-text"></textarea></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="management-title step3">请选择参与优惠活动的信息</div>
    <div class="configure">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">账单科目ID</p>
        <p><input type="text" name="subjectId" class="int-medium"></p>
        <p class="tab-form-name">账单科目名称</p>
        <p><input type="text" name="subjectName" class="int-medium"></p>
        <p><input id="mz_btn_bill_subject_query" type="button" value="查询" class="next-btn next-btn-hover"></p>
       </li>
      </ul>
     </div>
     <div class="configure-table">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <thead>
	      <tr>
	        <td width="100"><input id="mz_bs_checkAll" type="checkbox" class="checkbox">全选</td>
	        <td>账单科目ID</td>
	        <td>账单科目名称</td>
	        <td>账单科目描述</td>
	      </tr>
      </thead>
      <tbody id="mz_billSubjectData"></tbody>
      <script id="mz_billSubjectDataTmpl" type="text/template">
	      <tr>
	        <td><input type="checkbox" id="{{:subjectId}}" name="bs_checkbox" class="checkbox"></td>
	        <td>{{:subjectId}}</td>
	        <td>{{:subjectName}}</td>
	        <td>{{:subjectDesc}}</td>
	      </tr>
	  </script>
    </table>
    <div>
		<nav style="text-align: right">
			<ul id="mz_billSubject-page"></ul><!-- 分页 -->
		</nav>
  	</div>
     </div>
     <div id="mz_selectedBillSubject" class="configure-cnts">
      	<p>已选择参加活动的账单</p>
     </div>
    </div>
    
    <div class="management-title step4">请配置满赠活动的赠品</div>
    <div class="configure">
     <div class="configure-tab">
      <p class="current" style="width:100%;">赠送业务类型</p>
     <!-- 
      <p>赠送现金</p>
      <p>赠送虚拟货币</p>
      -->
     </div>
     <div class="configure-tab-cnt1">
     <div class="tab-form" id="mz_sp-form">
      <ul>
       <li>
        <p class="tab-form-name">产品ID</p>
        <p><input type="text" name="productId" class="int-medium"></p>
        <p class="tab-form-name">产品名称</p>
        <p><input type="text" name="productName" class="int-medium"></p>
       </li>
       <li>
        <p class="tab-form-name">业务类型</p>
        <p style="padding-right:8px;"><select id="serviceType" name="serviceType" class="int-medium"></select></p>
        <p class="tab-form-name">计费类型</p>
        <p><select id="groupBillingType" name="billingType" class="int-medium"></select></p>
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
	        <td>计费类型</td>
	        <td>业务类型</td>
	        <td>业务类型细分</td>
	        <td>单价／总价</td>
	      </tr>
	      </thead>
	      <tbody id="mz_salableProductData"></tbody>
	      <script id="mz_salableProductDataTmpl" type="text/x-jsrender">
		      <tr>
		        <td><input type="checkbox" id="{{:productId}}" name="sp_checkbox" class="checkbox"></td>
		        <td>{{:productId}}</td>
		        <td>{{:productName}}</td>
		        <td>{{:billingTypeName}}</td>
		        <td style="padding:0px;">
            		<table width="100%" border="0" class="height-small">
						{{if serviceInfoList.length > 1}}
                			{{for serviceInfoList}}
                    			<tr>
                        			<td style="height:30px;">{{:serviceTypeDesc}}</td>
                    			</tr>
                			{{/for}}
						{{else}}
                			{{for serviceInfoList}}
                    			<tr>
                        			<td>{{:serviceTypeDesc}}</td>
                    			</tr>
                			{{/for}}
						{{/if}}
            		</table>
        		</td>
		        <td style="padding:0px;">
            		<table width="100%" border="0" class="height-small">
						{{if serviceInfoList.length > 1}}
                			{{for serviceInfoList}}
                    			<tr>
                        			<td style="height:30px;">{{:serviceDetailDesc}}</td>
                    			</tr>
                			{{/for}}
						{{else}}
                			{{for serviceInfoList}}
                    			<tr>
                        			<td>{{:serviceDetailDesc}}</td>
                    			</tr>
                			{{/for}}
						{{/if}}
            		</table>
        		</td>
		        <td style="padding:0px;">
        			{{if 'STANDARD_GROUP_TYPE'==billingType}}
            			{{:totalPrice}}
        			{{else}}
            			<table width="100%" border="0" class="height-small">
							{{if serviceInfoList.length > 1}}
                				{{for serviceInfoList}}
                					<tr>
                    					<td style="height:30px;">{{:price}}</td>
                					</tr>
                				{{/for}}
							{{else}}
                				{{for serviceInfoList}}
                					<tr>
                    					<td>{{:price}}</td>
                					</tr>
                				{{/for}}
        					{{/if}}
            			</table>
        			{{/if}}
        		</td>
		      </tr>
		  </script>
	    </table>
    	<div>
			<nav style="text-align: right">
				<ul id="mz_salableProduct-page"></ul>
			</nav>
		</div>
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
       <li>
        <p class="tab-form-name">赠送业务生效时间</p>
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
    <div class="configure-tab-cnt2" style="display:none">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">赠送先进金额</p>
        <p><input type="text" class="int-medium">元</p>
       </li>
       <li>
        <p class="tab-form-name">先进到账日期</p>
        <p><select class="int-medium"><option>请选择</option></select></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="configure-tab-cnt3" style="display:none">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name">赠送虚拟币</p>
        <p><input type="text" class="int-medium"></p>
       </li>
       <li>
        <p class="tab-form-name">虚拟币到账日期</p>
        <p><select class="int-medium"><option>请选择</option></select></p>
       </li>
      </ul>
     </div>
    </div>
    </div>
    <div class="configure-btn">
     <input type="button" value="返回" class="next-btn cancel"><input type="button" id="mz_btn_bill_discount_save" value="保存" class="next-btn next-btn-hover">
    </div>
</div>
<!--tab标签第1块 end-->
