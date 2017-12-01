<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!--tab标签第4块 begin-->
<div id="bd_form" class="Discount-cnt4" style="display:none">
    <div class="management-title step2">请配置保底优惠产品</div>
    <div class="configure">
     <div class="tab-form flag">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠活动名称</p>
        <p><input type="text" name="productName" tip="优惠活动名称" class="int-xlarge nonull-flag"></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>保底价</p>
        <p>
        	<input type="text" name="bottomAmount" tip="保底价" style="padding-left:4px;" class="int-medium mana nonull-flag"><span style="float:left; padding: 0 10px 0 0;">元</span>
        	<select name="relatedSubject" tip="保底费用归属的账单科目" class="select-small nonull-flag">
        		<option value="">请选择保底费用归属的账单科目</option>
        	</select>
        </p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>生效日期</p>
        <p><input type="text" id="bd_effectDate_be" name="effectDate" tip="生效日期" class="int-medium nonull-flag" readonly><span id="bd_effectDate" class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>失效日期</p>
        <p><input type="text" id="bd_expireDate_be" name="expireDate" tip="失效日期" class="int-medium nonull-flag" readonly><span id="bd_expireDate" class="icon-calendar" style="position:relative;left:-35px;"></span></p>
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
        <p><input id="bd_btn_bill_subject_query" type="button" value="查询" class="next-btn next-btn-hover"></p>
       </li>
      </ul>
     </div>
     <div class="configure-table">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
      	<tr>
	        <td width="100"><input id="bd_bs_checkAll" type="checkbox" class="checkbox">全选</td>
	        <td>账单科目ID</td>
	        <td>账单科目名称</td>
	        <td>账单科目描述</td>
      	</tr>
      </thead>
      <tbody id="bd_billSubjectData"></tbody>
      <script id="bd_billSubjectDataTmpl" type="text/template">
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
			<ul id="bd_billSubject-page"></ul><!-- 分页 -->
		</nav>
  	</div>
     </div>
     <div id="bd_selectedBillSubject" class="configure-cnts">
      <p>已选择参加活动的产品</p>
     </div>
    </div>
    <div class="configure-btn">
     <input type="button" value="返回" class="next-btn cancel"><input type="button" id="bd_btn_bill_discount_save" value="保存" class="next-btn next-btn-hover">
    </div>
</div>
<!--tab标签第4块 end-->