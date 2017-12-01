<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!--tab标签第2块 begin-->
<div id="mj_form" class="Discount-cnt2" style="display:none">
    <div class="management-title step2">请配置满减优惠产品</div>
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
        	<!-- <select class="select-small"><option>请选择单位</option></select> --><span style="float:left; padding: 0 10px 0 0;">元</span><span class="jian">减</span>
        	<input type="text" name="discountAmount" tip="优惠规则" class="int-medium nonull-flag"><span style="float:left; padding: 0 10px 0 0;">元</span>
        	<select id="relatedSubject" name="relatedSubject" tip="减免费用归属的账单科目" class="select-small nonull-flag">
        		<option value="">请选择减免费用归属的账单科目</option>
        	</select>
        </p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>生效日期</p>
        <p><input type="text" id="mj_effectDate_be" name="effectDate" tip="生效日期" class="int-medium nonull-flag" readonly><span id="mj_effectDate" class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>失效日期</p>
        <p><input type="text" id="mj_expireDate_be" name="expireDate" tip="失效日期" class="int-medium nonull-flag" readonly><span id="mj_expireDate" class="icon-calendar" style="position:relative;left:-35px;"></span></p>
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
        <p><input id="mj_btn_bill_subject_query" type="button" value="查询" class="next-btn next-btn-hover"></p>
       </li>
      </ul>
     </div>
     <div class="configure-table">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
	    <tr>
	        <td width="100"><input id="mj_bs_checkAll" type="checkbox" class="checkbox">全选</td>
	        <td>账单科目ID</td>
	        <td>账单科目名称</td>
	        <td>账单科目描述</td>
      	</tr>
      </thead>
      <tbody id="mj_billSubjectData"></tbody>
      <script id="mj_billSubjectDataTmpl" type="text/template">
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
			<ul id="mj_billSubject-page"></ul><!-- 分页 -->
		</nav>
  	</div>
     </div>
     <div id="mj_selectedBillSubject" class="configure-cnts">
      <p>已选择参加活动的产品</p>
     </div>
    </div>
    
    <div class="configure-btn">
     <input type="button" value="返回" class="next-btn cancel"><input type="button" id="mj_btn_bill_discount_save" value="保存" class="next-btn next-btn-hover">
    </div>
</div>
<!--tab标签第2块 end-->