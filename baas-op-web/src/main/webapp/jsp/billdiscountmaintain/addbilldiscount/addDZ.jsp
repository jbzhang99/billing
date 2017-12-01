<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--tab标签第3块 begin-->
<div id="dz_form" class="Discount-cnt3" style="display:none">
    <div class="management-title step2">请配置优惠信息</div>
    <div class="configure">
     <div class="tab-form flag">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠活动名称</p>
        <p><input type="text" name="discountName" tip="优惠活动名称" placeholder="优惠活动名称，不超过30个字符" class="int-xlarge nonull-flag"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>折扣</p>
        <p><input type="text" name="discountPercent" tip="折扣" style="padding-left:4px;" class="int-medium mana nonull-flag price-num-input"><span style="float:left; padding: 0 5px 0 0;">折</span><i style="font-size:smaller; float:left;">（格式：0-10之间的小数）</i></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>活动生效日期</p>
        <p><input type="text" id="dz_effectDate_be" name="effectDate" tip="生效日期" class="int-medium nonull-flag" readonly><span id="dz_effectDate" class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>活动失效日期</p>
        <p><input type="text" id="dz_expireDate_be" name="expireDate" tip="失效日期" class="int-medium nonull-flag" readonly><span id="dz_expireDate" class="icon-calendar" style="position:relative;left:-35px;"></span></p>
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
        <p><input id="dz_btn_bill_subject_query" type="button" value="查询" class="next-btn next-btn-hover"></p>
       </li>
       <li><p class="tab-form-name"><input name="allPrdDiscount" type="checkbox" class="checkbox" style="float:right;margin-top:15px;"></p><p>全部产品参与</p></li>
      </ul>
     </div>
     <div class="configure-table">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
      	<tr>
	        <td width="100"><input id="dz_bs_checkAll" type="checkbox" class="checkbox">全选</td>
	        <td>产品ID</td>
	        <td>产品名称</td>
	        <td>子产品ID</td>
	        <td>子产品名称</td>
	        <td>计费周期</td>
      	</tr>
      </thead>
      <tbody id="dz_billSubjectData"></tbody>
      <script id="dz_billSubjectDataTmpl" type="text/template">
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
     <div id="dz_selectedBillSubject" class="configure-cnts">
      <p>已选择参加活动的产品</p>
     </div>
    </div>
    
    <div class="configure-btn">
     <input type="button" value="取消" class="next-btn cancel"><input type="button" id="dz_btn_bill_discount_save" value="保存" class="next-btn next-btn-hover">
    </div>
</div>
<!--tab标签第3块 end-->