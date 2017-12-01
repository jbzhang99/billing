<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/inc/inc.jsp"%>
<title>查看明细级优惠</title>
</head>

<body>
<!--头部和菜单-->
<%@ include file="/inc/head.jsp"%>
<!--头部和菜单结束-->

<!--中间部分-->
<div class="wrapper">
	
	<div class="management"><!--资费管理外侧-->
   
    <div class="management-title">优惠产品类型</div>
    <div class="Discount">
    <div class="Discount-ttf" >
     <ul class="Discounta" id="MZ" hidden="hidden">
      <p>满赠优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
     <div class="Discount-ttg" >
     <ul class="Discountb" id="MJ" hidden="hidden">
      <p>满减优惠</p>
      <span>产品的简单概况，可实现的计费规则等</span>
      <img src="${_base}/resources/baasop/images/steps.png">
     </ul>
     </div>
    </div>
    
    <!--tab标签第1块-->
    <div class="Discount-cnt1" id="content1" hidden="hidden">
    <div class="management-title">满赠优惠产品</div>
    <div class="configure">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠活动名称</p>
        <p><input type="text" class="int-xlarge" id="activityName" readonly></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>优惠规则</p>
        <p><input type="text" id="ruleAmount" class="int-medium mana" readonly><span class="man">满</span><select id="pUnit" class="select-small" readonly><option value="">请选择单位</option></select></p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>生效日期</p>
        <p><input type="text" id="pOnTime" class="int-medium" readonly><span class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>失效日期</p>
        <p><input type="text" id="pOffTime" class="int-medium" readonly><span class="icon-calendar" style="position:relative;left:-35px;"></span></p>
       </li>
       <li>
        <p class="tab-form-name">备注</p>
        <p><textarea id="comments" class="int-text" readOnly></textarea></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="management-title">参与优惠活动的信息</div>
    <div class="configure">
     <!-- <div class="tab-form">
      <ul>
       <li>
       <p class="tab-form-name">产品Id</p>
        <p><input type="text" id="proId" class="int-medium"></p>
        <p class="tab-form-name">产品名称</p>
        <p><input type="text" id="productName" class="int-medium"></p>
        
       </li>
      <li>
      
        <p class="tab-form-name">业务类型</p>
        <p><select class="int-medium" id="serviceType"><option value="">请选择</option></select></p>
        <p><input type="button" value="查询" id="PP_BTN" class="next-btn next-btn-hover"></p></li>
      </ul>
     </div> -->
     <div class="configure-table">
    
   
     </div>
     
     <div id="selectedProduct" class="configure-cnts">
      <p>已选择参加活动的产品</p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
      <tr>
        <td>产品ID</td>
        <td>产品名称</td>
        <td>业务类型</td>
        <td>计费类型</td>
        <td>单价／总价</td>
        <td>状态</td>
      </tr>
      </thead>
      <tbody id="listData">
      <!-- 参与产品列表 -->
      </tbody>
       <script id="listDataTmpl" type="text/x-jsrender">
					  <tr>
					   
					    <td>{{:productId}}</td>
					    <td>{{:productName}}</td>
					     <td style="padding:0px">
                       {{if usageList.length > 1}}
                        <table width="100%" border="0" class="height-small">
                       {{for usageList}}
                         <tr>
                        <td >
                          {{if serviceType=='VOICE'}}
								语音
                         {{else serviceType=='SEAT'}}
								坐席
						 {{else serviceType=='STREAM'}}
							       数据
						  {{/if}}
						</td>
                    </tr>
                    {{/for}}
                </table>
                  {{else}}
                      {{for usageList}}
                        {{if serviceType=='VOICE'}}
								<span style="margin:15px">语音</span>
                         {{else serviceType=='SEAT'}}
								<span style="margin:15px">坐席</span>
						 {{else serviceType=='STREAM'}}
							      <span style="margin:15px"> 数据</span>
						  {{/if}}
                 {{/for}}
               {{/if}}
			
						</td>
					    <td>{{if billingType=='STANDARD_GROUP_TYPE'}}
								标准组合计费
                         {{else billingType=='STEP_GROUP_TYPE'}}
								阶梯组合计费
						  {{/if}}
						</td>
					   <td>{{:totalPrice}}</td>
					    <td class="right-border">
						{{if status=='INOPERATIVE'}}
								待生效
                         {{else status=='ACTIVE'}}
								有效
						  {{/if}}
						</td>
					  </tr>
		</script>
    </table>
     </div>
    </div>

    <div class="management-title">满赠活动的赠品</div>
	  <div class="configure">
     <div class="configure-tab">
      <p class="current">赠送业务类型</p>
     <!--  <p>赠送现金</p>
      <p>赠送虚拟货币</p> -->
     </div>
     <div class="configure-tab-cnt1">
   <!--   <div class="tab-form">
     <ul>
       <li>
       <p class="tab-form-name">产品Id</p>
        <p><input type="text" id="pId" class="int-medium"></p>
        <p class="tab-form-name">产品名称</p>
        <p><input type="text" id="pName" class="int-medium"></p>
        
       </li>
      <li>
      
        <p class="tab-form-name">业务类型</p>
        <p><select class="int-medium" id="sType"><option value="">请选择</option></select></p>
        <p><input type="button" value="查询" class="next-btn next-btn-hover"></p></li>
      </ul>
     </div> -->
     <div class="configure-table">
    
     <div id="selectedProduct1" class="configure-cnts">
      <p>已选择参加活动的产品</p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
      <tr>
       
        <td>产品ID</td>
        <td>产品名称</td>
        <td>业务类型</td>
        <td>计费类型</td>
        <td>单价／总价</td>
        <td>状态</td>
      </tr>
      </thead>
     
				
     </div>
      <tbody id="listData1">
    <!--  赠品产品列表 -->
      </tbody>
      <script id="listDataTmpl1" type="text/x-jsrender">
					  <tr>
					   
					    <td>
							{{:productId}}						
                         </td>
					    <td>{{:productName}}</td>
					    <td style="padding:0px">
                       {{if usageList.length > 1}}
                        <table width="100%" border="0" class="height-small">
                       {{for usageList}}
                         <tr>
                        <td >
                          {{if serviceType=='VOICE'}}
								语音
                         {{else serviceType=='SEAT'}}
								坐席
						 {{else serviceType=='STREAM'}}
							       数据
						  {{/if}}
						</td>
                    </tr>
                    {{/for}}
                </table>
                  {{else}}
                      {{for usageList}}
                        {{if serviceType=='VOICE'}}
								<span style="margin:15px">语音</span>
                         {{else serviceType=='SEAT'}}
								<span style="margin:15px">坐席</span>
						 {{else serviceType=='STREAM'}}
							      <span style="margin:15px"> 数据</span>
						  {{/if}}
                 {{/for}}
               {{/if}}
			
						</td>
					    <td>
							{{if billingType=='STANDARD_GROUP_TYPE'}}
								标准组合计费
                         {{else billingType=='STEP_GROUP_TYPE'}}
								阶梯组合计费
						  {{/if}}</td>
					   <td>{{:totalPrice}}</td>
					    <td class="right-border">
						{{if status=='INACTIVE'}}
								待生效
                         {{else status=='ACTIVE'}}
								有效
						  {{/if}}
						</td>
					  </tr>
		  </script>
    </table>
     </div>
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>赠送业务生效方式</p>
        <p><select id="activeType" class="int-medium"><option>请选择</option></select></p>
        <p class="tab-form-name">赠送业务生效时间</p>
        <p><input type="text" id="gOntime" class="int-medium"><span class="icon-calendar" style="position:relative;left:-35px;"></span></p>
       </li>
       <li>
        <p class="tab-form-name" ><i>*</i>赠送业务周期</p>
        <p><select id="cycleUnit" class="int-medium"><option value="">请选择</option></select></p>
       
       </li>
      </ul>
     </div>
    </div>
    
  
    </div>
    
    <div class="configure-btn">
     <input type="button"  value="返回" class="next-btn BACK_BTN">
    </div>
    </div>
  </div>  
 
    
 <!-- =======================下边是满减================================= -->
    
  
    
    
    
    <!--tab标签第2块-->
    <div class="Discount-cnt2" id="content2" hide="hidden">
    <div class="management-title ">满减优惠产品</div>
    <div class="configure">
     <div class="tab-form">
      <ul>
       <li>
        <p class="tab-form-name"><i>*</i>优惠活动名称</p>
        <p><input type="text" id="programName" class="int-xlarge" readOnly></p>
       </li>
       <li>
        <p class="tab-form-name">优惠规则</p>
        <p><input type="text" class="int-medium mana" id="rruleAmount" readOnly><span class="man">满</span><select id="mj_unit" class="select-small"><option value="">请选择单位</option></select><span class="jian">减</span><input type="text" id="mj_reduceAmount" class="int-medium" readOnly>元</p>
       </li>
       <li>
        <p class="tab-form-name"><i>*</i>生效日期</p>
        <p><input type="text" id="rActiveTime" class="int-medium" readOnly><span class="icon-calendar"></span></p>
        <p class="tab-form-name"><i>*</i>失效日期</p>
        <p><input type="text" id="rInvalidTime" class="int-medium" readOnly><span class="icon-calendar" style="position:relative;left:-35px;"></span></p>
       </li>
       <li>
        <p class="tab-form-name">备注</p>
        <p><textarea id="rcomments" class="int-text" readOnly></textarea></p>
       </li>
      </ul>
     </div>
    </div>
    <div class="management-title ">参与优惠活动的信息</div>
    <div class="configure">
     
     <div id="mj_products" class="configure-cnts">
      <p>已选择参加活动的产品</p>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <thead>
      <tr>
      
        <td>产品ID</td>
        <td>产品名称</td>
        <td>业务类型</td>
        <td>计费类型</td>
        <td>单价／总价</td>
        <td>状态</td>
      </tr>
      </thead>
      <tbody id="rBody">
     
      </tbody>
      <script id="mj_table" type="text/x-jsrender">
		      <tr>
				
				 <td>{{:productId}}
                 </td>
				<td>{{:productName}}</td>
				 <td style="padding:0px">
                       {{if usageList.length > 1}}
                        <table width="100%" border="0" class="height-small">
                       {{for usageList}}
                         <tr>
                        <td >
                          {{if serviceType=='VOICE'}}
								语音
                         {{else serviceType=='SEAT'}}
								坐席
						 {{else serviceType=='STREAM'}}
							       数据
						  {{/if}}
						</td>
                    </tr>
                    {{/for}}
                </table>
                  {{else}}
                      {{for usageList}}
                        {{if serviceType=='VOICE'}}
								<span style="margin:15px">语音</span>
                         {{else serviceType=='SEAT'}}
								<span style="margin:15px">坐席</span>
						 {{else serviceType=='STREAM'}}
							      <span style="margin:15px"> 数据</span>
						  {{/if}}
                 {{/for}}
               {{/if}}
			
						</td>
				<td>{{if billingType=='STANDARD_GROUP_TYPE'}}
								标准组合计费
                         {{else billingType=='STEP_GROUP_TYPE'}}
								阶梯组合计费
						  {{/if}}</td>
				<td>{{:totalPrice}}</td>
				<td class="right-border">
				{{if status=='INACTIVE'}}
					待生效
                {{else status=='ACTIVE'}}
					有效
				{{/if}}
				</td>
			</tr>
	 </script>
    </table>
     </div>
    </div>
    
    <div class="configure-btn">
     <input type="button"  value="返回" class="next-btn BACK_BTN">
    </div>
    </div>
    
   </div>
	
</div>
<!--中间部分结束-->
         
<!--底部-->
<%@ include file="/inc/foot.jsp"%>
<!--底部结束-->
</body>

<script type="text/javascript">
(function () {
	seajs.use('app/jsp/preferentialproduct/productView', function (ProductViewPager) {
		var pager = new ProductViewPager({
			element: document.body,
			productId: "<c:out value="${id}"/>",
			detailType:"<c:out value="${detailType}"/>"
			});
		pager.render();
	});
})();
</script>

</html>

