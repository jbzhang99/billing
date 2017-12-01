<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--tab标签第3块 begin-->
<div id="dz_form" class="Discount-cnt3" style="display:none">
    <div class="management-title step2">请配置限时折扣优惠产品</div>
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
        	<!-- 
        	<input type="checkbox" name="preferentialRules" style="float:left; top:10px;" class="checkbox"><span style="float:left; padding: 0 5px 0 0;">日期</span><input type="text" id="dz_discountDate_be" name="discountDate" class="int-medium" readonly><span id="dz_discountDate" style="left:385px;" class="icon-calendar"></span>
        	 -->
        	<input type="checkbox" name="preferentialRules" checked="checked" style="float:left; top:10px;" class="checkbox">
        	<span style="float:left; padding: 0 5px 0 0;">时段：</span>
        	<input type="text" name="startTime" tip="时段" onkeyup="value=value.replace(/[^\0-9\:]/g,'').trim()" maxlength="8" class="int-mini nonull-flag">
        	<i style="color:#666; float:left;">~</i>
        	<input type="text" name="endTime" tip="时段" onkeyup="value=value.replace(/[^\0-9\:]/g,'').trim()" maxlength="8" class="int-mini nonull-flag">
        	<i style="font-size:smaller; float:left;">（格式：10:30:00~12:00:00）</i>
        </p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>折扣</p>
        <p><input type="text" name="discountPercent" tip="折扣" onkeyup="value=value.replace(/[^\0-9\.]/g,'').trim()" style="padding-left:4px;" class="int-medium mana nonull-flag"><span style="float:left; padding: 0 5px 0 0;">折</span><i style="font-size:smaller; float:left;">（格式：0-1之间的小数）</i></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>生效日期</p>
        <p><input type="text" id="dz_effectDate_be" name="effectDate" tip="生效日期" class="int-medium nonull-flag" readonly><span id="dz_effectDate" class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>失效日期</p>
        <p><input type="text" id="dz_expireDate_be" name="expireDate" tip="失效日期" class="int-medium nonull-flag" readonly><span id="dz_expireDate" class="icon-calendar" style="position:relative;left:-35px;"></span></p>
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
        <p><input id="dz_btn_bill_subject_query" type="button" value="查询" class="next-btn next-btn-hover"></p>
       </li>
      </ul>
     </div>
     <div class="configure-table">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
      	<tr>
	        <td width="100"><input id="dz_bs_checkAll" type="checkbox" class="checkbox">全选</td>
	        <td>账单科目ID</td>
	        <td>账单科目名称</td>
	        <td>账单科目描述</td>
      	</tr>
      </thead>
      <tbody id="dz_billSubjectData"></tbody>
      <script id="dz_billSubjectDataTmpl" type="text/template">
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
			<ul id="dz_billSubject-page"></ul><!-- 分页 -->
		</nav>
  	</div>
     </div>
     <div id="dz_selectedBillSubject" class="configure-cnts">
      <p>已选择参加活动的产品</p>
     </div>
    </div>
    
    <div class="configure-btn">
     <input type="button" value="返回" class="next-btn cancel"><input type="button" id="dz_btn_bill_discount_save" value="保存" class="next-btn next-btn-hover">
    </div>
</div>
<!--tab标签第3块 end-->