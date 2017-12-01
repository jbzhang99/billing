<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<!--Support IE Text -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
<%@ include file="/inc/inc.jsp"%>
<title>首页</title>
<meta name="viewport" content="width=device-width, initial-scale=0.5, minimum-scale=0.5,, maximum-scale=0.5,,user-scalable=no" id="viewport" />
<link href="${_base}/resources/baaspt/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/frame.css" rel="stylesheet" type="text/css">
<link href="${_base}/resources/baaspt/css/global.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${_base}/resources/baaspt/css/jquery.fullPage.min.css"/>
<script src="${_base}/resources/baaspt/js/jquery-1.11.3.min.js"></script>
<script src="${_base}/resources/baaspt/js/jquery.fullPage.min.js"></script>
<script src="${_base}/resources/baaspt/js/comp.js"></script>

<script>
	$(function(){
//			$("img").lazyload({
//				placeholder : "images/loading.gif",
//				effect: "fadeIn"
//			});
			$('#dowebok').fullpage({
//             sectionsColor: ['#0581B2', '#4BBFC3', '#7BAABE','#daa520'],
//                //设置每一屏幕的背景颜色
				scrollingSpeed:1000,
				// 设置滚动的花费时间
				easingcss3:'ease-in-out',
				//运动曲线
				continuousVertical: true,
				//到达最后一屏后，是否回到首屏
				'navigation': true,
				// 右侧显示小圆点

				/*顶部导航栏*/
				anchors:['page1','page2','page3','page4'],
				// 值为绑定菜单的类名或ID名，需先设置achors才能生效
				menu:'#menu',
				/*控制首屏*/
				afterLoad: function(anchorLink, index){
					if(index==1){
						$('.section1').removeClass('comeout');
					}
				},

				//来判断当前的屏幕
				onLeave: function(index, nextIndex, direction){
					if(index==1){
						$('.section1 .twotp').animate({marginRight:'0'},700,function(){$('.section1 .two-nr').animate({marginLeft:'0'},500);});
					}
					else if(index==2){
						$('.section2 .three-nr').animate({marginRight:'0'},1000);
						$('.section2 .threetp').animate({marginLeft:'0'},1000);
					}
					else if(index==3){
						$('.section3 .two-nr').animate({marginLeft:'0'},700,function(){$('.section3 .twotp').animate({marginRight:'0'},500);});
					}
					else if(index==4){
						$('.section4');
					}
					else if(index==5){
						$('.section5');
					}
					if(nextIndex==0){ 
						$('.section5');
					}
					else if(nextIndex==2){
						$('.section1 .twotp').animate({marginRight:'0'},700,function(){$('.section1 .two-nr').animate({marginLeft:'0'},500);});
					}
					else if(nextIndex==3){
						$('.section2 .three-nr').animate({marginRight:'0'},1000);
						$('.section2 .threetp').animate({marginLeft:'0'},1000);
					}
					else if(nextIndex==4){
						$('.section3 .two-nr').animate({marginLeft:'0'},700,function(){$('.section3 .twotp').animate({marginRight:'0'},500);});
					}
					else if(nextIndex==5){
						$('.section4');
					}
				},



			
			});
		});
</script>
</head>
<body>

<!--banner start-->
<div id="dowebok">

   <!--第一屏-->
	<div class="section comeout">
     <div class="one-cnt">
     <div class="topnav">
        <div class="topnav-main">
            <%@ include file="/inc/topnav-main-ul.jsp"%>
        </div>
	 </div>
     <div class="subnav-index"><!-- 导航-->
            <%@ include file="/inc/subnav-ul.jsp"%>
        </div>
     <div class="one-cntr">
      <p><img src="${_baaspt }/images/onetp.png"></p>
      <span>为企业复杂业务和多方合作而生的专业在线计费平台</span>
      <a href="${baas_op_web_url }">免费试用</a>
     </div>
     </div>
	</div>
    
    <!--第二屏-->
   <div class="section section1 comeout">
    <div class="two-cnt">
     <div class="cnt-center">
      <div class="two-nr">
       <p>复杂计费核算</p>
       <span>不再成为您业务创新的瓶颈</span>
       <dl>
        <dt><img src="${_baaspt }/images/icon1.png"></dt>
        <dd>根据业务发展可实时调整计费策略<br>可视化配置实时生效</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon2.png"></dt>
        <dd>针对标准资费、组合套餐、活动优惠<br>提供十二种基础计费能力，支持上百种组合策略计费</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon3.png"></dt>
        <dd> 二十年核心技术沉淀<br>高性能计费引擎提供秒级实时计费服务</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon4.png"></dt>
        <dd>大数据业务保障<br>提供价值估算、信用度计算、能力计算等价值能力分析服务</dd>
       </dl>
      </div>
      <div class="twotp"><img src="${_baaspt }/images/twotp.png"></div>
      </div>
    </div>
   </div>
   
   <!--第三屏-->
	<div class="section section2 comeout">
    <div class="three-cnt">
     <div class="cnt-center">
      <div class="three-nr">
       <p>自动化多边对账与结算服务</p>
       <span>将大大降低您的运营成本</span>
       <dl>
        <dt><img src="${_baaspt }/images/icon5.png"></dt>
        <dd>混合结算规则配置<br>充分满足您的对账、核销需求</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon6.png"></dt>
        <dd>提供多种数据模型<br>个性化您的详单和报表</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon7.png"></dt>
        <dd>支持自动化归集<br>降低事务性工作投入</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon8.png"></dt>
        <dd>多渠道对账管理<br>让每一笔业务“有始有终”</dd>
       </dl>
      </div>
      <div class="threetp"><img src="${_baaspt }/images/threetp.png"></div>
      </div>
    </div>
   </div>
   
   <!--第四屏-->
	<div class="section section3 comeout">
     <div class="for-cnt">
       <div class="cnt-center">
      <div class="two-nr">
       <p>智能的账务管理</p>
       <span>让您的经营数据化繁为简</span>
       <dl>
        <dt><img src="${_baaspt }/images/icon9.png"></dt>
        <dd>账务报表随需而定<br>让您远离定制费的烦忧</dd>
       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon10.png"></dt>
        <dd>账单多维度分析<br>帮您透过现象看本质</dd>

       </dl>
       <dl>
        <dt><img src="${_baaspt }/images/icon11.png"></dt>
        <dd>多终端覆盖<br>随时随地了解企业的经营情况</dd>
       </dl>
      </div>
      <div class="twotp"><img src="${_baaspt }/images/fortp.png"></div>
      </div>
     </div>
	</div>
    
    <!--第五屏-->
    <div class="section section4 comeout">
     <div class="five-cnt">
      <div class="cnt-center">
       <div class="five-text">
        <p>从传输链路到业务数据的全方位安全加固</p>
        <span>只为让您放心</span>
       </div>
       <div class="dl-list">
        <dl class="dllist1">
         <dt></dt>
         <dd><span>数据加密</span></dd>
         <dd>数据传输与存储加密</dd>
        </dl>
        <dl class="dllist2">
         <dt></dt>
         <dd><span>数据隔离</span></dd>
         <dd>支持数据独立存储<br>互不影响</dd>
        </dl>
        <dl class="dllist3">
         <dt></dt>
         <dd><span>访问控制</span></dd>
         <dd>网络安全防护<br>高可靠的认证体系</dd>
        </dl>
        <dl class="dllist4">
         <dt></dt>
         <dd><span>容灾机制</span></dd>
         <dd>数据异地备份<br>提供不间断应用服务</dd>
        </dl>
       </div>
      </div>
     </div>
	</div>
    
    <!--第六屏-->
    <div class="section section5 comeout">
     <div class="six-cnt">
      <div class="cnt-centern">
       <div class="consul-title">
        <p>产品咨询</p>
        <span>请填写以下信息，我们会尽快给您回复</span>
       </div>
       <div class="consul-cnt">
        <ul>
         <li>
         	<input id="phone" type="text" placeholder="手机号"/>
         	<label id="phoneMsg" style="display:none;">手机号格式错误</label>
         </li>
         <li class="teright">
         	<input id="email" type="text" placeholder="邮箱地址"/>
         	<label id="emailMsg" style="display:none;">邮箱格式错误</label>
         </li>
         <li><input id="tenantName" maxlength="20" type="text" placeholder="企业名称"/></li>
         <li class="teright"><select id="tenantType"><option value="">行业类型</option></select></li>
         <li class="textarea">
         	<textarea id="message" maxlength="800" placeholder="请输入产品咨询内容："></textarea>
         	<label id="messageMsg" style="display:none;">请输入咨询内容</label>
         </li>
         <li class="erma"><img src="${_baaspt }/images/ma.png"></li>
         <li style="line-height:1px; height:1px"></li><li></li>
         <li><button id="submitBtn">提 交</button></li>
        </ul>
       </div>
      </div>
      <%@ include file="/inc/foot.jsp"%>
     </div>
	</div>
</div>
<%@ include file="/inc/foot.jsp"%>
 <script type="text/javascript">
 	var industryList = $.parseJSON('${industryList}');
		(function() {
			seajs.use([ 'app/js/home' ], function(HomePager) {
				var pager = new HomePager({
					element : document.body
				});
				pager.render();
			});
		})(); 
  </script>
</body>
</html>
